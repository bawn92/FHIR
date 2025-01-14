/**
 * (C) Copyright IBM Corp. 2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.jdbc.test;

import static org.testng.AssertJUnit.assertNotNull;

import java.sql.Connection;
import java.util.Properties;

import org.testng.annotations.Test;

import com.ibm.watson.health.fhir.persistence.jdbc.dao.api.FHIRDbDAO;
import com.ibm.watson.health.fhir.persistence.jdbc.dao.impl.FHIRDbDAOBasicImpl;

/**
 * This class tests the basic functions of the FHIR DB Data Access Object class.
 * @author markd
 *
 */
public class FHIRDBDAOTest {

    public FHIRDBDAOTest() {
        super();
    }
    
    /**
     * Tests acquiring a connection to a Derby FHIR database using the embedded Derby driver. 
     * If the database does not exist prior to the test, it should be created.
     * @throws Exception
     */
    @Test(groups = {"jdbc"})
    @SuppressWarnings("rawtypes")
    public void testGetDerbyConnection() throws Exception {
        
        Properties props = new Properties();
        props.setProperty(FHIRDbDAO.PROPERTY_DB_DRIVER, "org.apache.derby.jdbc.EmbeddedDriver");
        props.setProperty(FHIRDbDAO.PROPERTY_DB_URL, "jdbc:derby:target/fhirDB;create=true");
        FHIRDbDAO dao = new FHIRDbDAOBasicImpl(props);
        Connection connection = dao.getConnection();
        assertNotNull(connection);
    }
    
    /**
     * Tests acquiring a connection to an existing DB2 FHIR database. 
     * NOTE: This test will remain commented out. Since it has a specific dependency on an existing DB2 database, it will fail when run as part of the
     * maven build for the project. It can uncommented and run as a standalone testNg test, provided a DB2 FHIR database is pre-defined.
     * @throws Exception
     */
    //@Test(groups = {"jdbc"})
    @SuppressWarnings("rawtypes")
    public void testGetDB2Connection() throws Exception {
        
        Properties props = new Properties();
        props.setProperty(FHIRDbDAO.PROPERTY_DB_DRIVER, "com.ibm.db2.jcc.DB2Driver");
        props.setProperty(FHIRDbDAO.PROPERTY_DB_URL, "jdbc:db2://localhost:50000/fhirdb");
        props.setProperty(FHIRDbDAO.PROPERTY_DB2_USER, "user");
        props.setProperty(FHIRDbDAO.PROPERTY_DB2_PSWD, "password");
        
        FHIRDbDAO dao = new FHIRDbDAOBasicImpl(props);
        Connection connection = dao.getConnection();
        assertNotNull(connection);
    }
    

}
