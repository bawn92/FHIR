/**
 * (C) Copyright IBM Corp. 2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibm.watson.health.fhir.exception.FHIRException;
import com.ibm.watson.health.fhir.model.resource.OperationOutcome;
import com.ibm.watson.health.fhir.model.type.Id;
import com.ibm.watson.health.fhir.model.util.FHIRUtil;

public class FHIROperationException extends FHIRException {
    private static final long serialVersionUID = 1L;

    private List<OperationOutcome.Issue> issues = new ArrayList<>();

    /**
     * @see Exception#Exception(String)
     */
    public FHIROperationException(String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     */
    public FHIROperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public OperationOutcome buildOperationOutcome() {
        Id probeId = Id.builder().value(getUniqueId()).build();
        return FHIRUtil.buildOperationOutcome(getIssues()).toBuilder().id(probeId).build();
    }

    public List<OperationOutcome.Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<OperationOutcome.Issue> issues) {
        this.issues = issues;
    }
    
    public FHIROperationException withIssue(OperationOutcome.Issue... issues) {
        if (issues != null) {
            for (OperationOutcome.Issue issue : issues) {
                getIssues().add(issue);
            }    
        }
        return this;
    }
    
    public FHIROperationException withIssue(Collection<OperationOutcome.Issue> issues) {
        if (issues != null) {
            getIssues().addAll(issues);
        }
        return this;
    }
}
