/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.jdbc.test.spec;

import com.ibm.watson.health.fhir.model.resource.Resource;
import com.ibm.watson.health.fhir.model.spec.test.ResourceComparatorVisitor;
import com.ibm.watson.health.fhir.persistence.exception.FHIRPersistenceException;
import com.ibm.watson.health.fhir.persistence.util.ResourceFingerprintVisitor;
import com.ibm.watson.health.fhir.persistence.util.SaltHash;

/**
 * Create the resource using the persistence
 * @author rarnold
 *
 */
public abstract class BaseOperation implements ITestResourceOperation {
    
    /**
     * Perform a comparison between the original resource and new value returned by the persistence
     * layer
     * @param tc
     * @param resource
     * @param newResource
     * @throws FHIRPersistenceException
     */
    protected void check(TestContext tc, Resource resource, Resource newResource, String oper) throws FHIRPersistenceException {
        // Fingerprint the new resource using the same salt and make sure it matches
        SaltHash originalFingerprint = tc.getOriginalFingerprint();
        ResourceFingerprintVisitor v = new ResourceFingerprintVisitor(originalFingerprint.getSalt());
        newResource.accept(newResource.getClass().getSimpleName(), v);
        
        if (!v.getSaltAndHash().equals(originalFingerprint)) {
            
            // Let's run the comparator so that we can report on any difference between the resources
            ResourceComparatorVisitor originals = new ResourceComparatorVisitor();
            tc.getResource().accept(tc.getResource().getClass().getSimpleName(), originals);
            
            ResourceComparatorVisitor others = new ResourceComparatorVisitor();
            newResource.accept(newResource.getClass().getSimpleName(), others);
            
            // Perform a bi-directional comparison of values in the maps
            ResourceComparatorVisitor.compare(originals.getValues(), others.getValues());
            
            throw new FHIRPersistenceException("Fingerprint mismatch after " + oper + " for " + resource.getClass().getSimpleName());
        }
        
    }
}
