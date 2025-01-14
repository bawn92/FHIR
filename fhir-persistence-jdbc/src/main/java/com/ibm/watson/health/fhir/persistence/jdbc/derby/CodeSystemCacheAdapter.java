/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.jdbc.derby;

import com.ibm.watson.health.fhir.persistence.exception.FHIRPersistenceException;
import com.ibm.watson.health.fhir.persistence.jdbc.dao.api.CodeSystemDAO;
import com.ibm.watson.health.fhir.persistence.jdbc.dao.api.ICodeSystemCache;

/**
 * @author rarnold
 *
 */
public class CodeSystemCacheAdapter implements ICodeSystemCache {
    
    private final CodeSystemDAO delegate;
    
    public CodeSystemCacheAdapter(CodeSystemDAO delegate) {
        this.delegate = delegate;
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.fhir.persistence.jdbc.dao.api.ICodeSystemCache#readOrAddCodeSystem(java.lang.String)
     */
    @Override
    public int readOrAddCodeSystem(String codeSystem) throws FHIRPersistenceException {
        return delegate.readOrAddCodeSystem(codeSystem);
    }

}
