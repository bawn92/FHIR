/**
 * (C) Copyright IBM Corp. 2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.jdbc.test;

import java.util.Properties;

import com.ibm.watson.health.fhir.persistence.FHIRPersistence;
import com.ibm.watson.health.fhir.persistence.jdbc.impl.FHIRPersistenceJDBCNormalizedImpl;
import com.ibm.watson.health.fhir.persistence.test.common.AbstractQueryAuditEventTest;


public class JDBCNormQueryAuditEventTest extends AbstractQueryAuditEventTest {
    
    private Properties testProps;
    
    public JDBCNormQueryAuditEventTest() throws Exception {
        this.testProps = readTestProperties("test.normalized.properties");
    }
    
    @Override
    public void bootstrapDatabase() throws Exception {
        // No longer required, because the test group depends on an initialization group
    }
    
    @Override
    public FHIRPersistence getPersistenceImpl() throws Exception {
        return new FHIRPersistenceJDBCNormalizedImpl(this.testProps);
    }
}
