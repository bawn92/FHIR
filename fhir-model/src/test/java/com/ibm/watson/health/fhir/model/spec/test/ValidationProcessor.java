/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.spec.test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.watson.health.fhir.model.resource.OperationOutcome;
import com.ibm.watson.health.fhir.model.resource.Resource;
import com.ibm.watson.health.fhir.model.resource.OperationOutcome.Issue;
import com.ibm.watson.health.fhir.model.util.FHIRUtil;
import com.ibm.watson.health.fhir.model.validation.FHIRValidator;

/**
 * Strategy to process resources using the {@link FHIRValidator}
 * @author rarnold
 *
 */
public class ValidationProcessor implements IExampleProcessor {
    private static final Logger logger = Logger.getLogger(ValidationProcessor.class.getName());
    
    /* (non-Javadoc)
     * @see com.ibm.watson.health.fhir.model.spec.test.IExampleProcessor#process(java.lang.String, com.ibm.watson.health.fhir.model.resource.Resource)
     */
    @Override
    public void process(String jsonFile, Resource resource) throws Exception {
        List<OperationOutcome.Issue> issues = FHIRValidator.validator(resource).validate();
        if (!issues.isEmpty()) {
            List<String> issueStrings = new ArrayList<String>();
            for (Issue issue : issues) {
                String details = "<missing details>";
                if (issue.getDetails() != null && issue.getDetails().getText() != null) {
                    details = issue.getDetails().getText().getValue();
                }
                String locations = issue.getExpression().stream()
                    .flatMap(loc -> Stream.of(loc.getValue()))
                    .collect(Collectors.joining(","));
                issueStrings.add(details + " (" + locations + ")");
            }
            
            // Only errors or worse should result in a failure.
            boolean includesFailure = false;
            for (OperationOutcome.Issue issue: issues) {
                if (FHIRUtil.isFailure(issue.getSeverity())) {
                    includesFailure = true;
                }
            }

            if (includesFailure) {
                // we want the test to fail
                throw new Exception("Input resource failed validation: \n\t" + String.join("\n\t", issueStrings));
            }
            else {
                logger.fine("Validation issues on '" + jsonFile + "' [INFO]: \n\t" + String.join("\n\t", issueStrings));
            }
        }
    }

}
