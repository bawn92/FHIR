/**
 * (C) Copyright IBM Corp. 2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.jdbc.exception;

import java.util.Collection;

import com.ibm.watson.health.fhir.model.resource.OperationOutcome;

/**
 * This exception class is thrown when Foreign Key violations are encountered while attempting to access data in the FHIR DB.
 * @author markd
 *
 */
public class FHIRPersistenceFKVException extends FHIRPersistenceDataAccessException {

    private static final long serialVersionUID = 4303342119803229856L;

    public FHIRPersistenceFKVException(String message) {
        super(message);
    }

    public FHIRPersistenceFKVException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public FHIRPersistenceFKVException withIssue(OperationOutcome.Issue... issues) {
        super.withIssue(issues);
        return this;
    }
    
    @Override
    public FHIRPersistenceFKVException withIssue(Collection<OperationOutcome.Issue> issues) {
        super.withIssue(issues);
        return this;
    }

}
