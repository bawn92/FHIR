/**
 * (C) Copyright IBM Corp. 2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.test;

import static org.testng.AssertJUnit.fail;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ibm.watson.health.fhir.config.FHIRConfiguration;
import com.ibm.watson.health.fhir.config.FHIRRequestContext;
import com.ibm.watson.health.fhir.persistence.FHIRPersistence;
import com.ibm.watson.health.fhir.persistence.exception.FHIRPersistenceException;
import com.ibm.watson.health.fhir.persistence.exception.FHIRPersistenceNotSupportedException;
import com.ibm.watson.health.fhir.persistence.helper.FHIRPersistenceHelper;
import com.ibm.watson.health.fhir.persistence.helper.PersistenceHelper;

/**
 * Tests associated with the FHIRPersistenceHelper class.
 */
public class FHIRPersistenceHelperTest {
    
    @BeforeClass
    public void setup() {
        FHIRConfiguration.setConfigHome("target/test-classes");
    }
    
    @Test
    public void test1() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        FHIRPersistence persistence = helper.getFHIRPersistenceImplementation();
        assertNotNull(persistence);
        assertEquals(MockPersistenceImpl.class.getName(), persistence.getClass().getName());
        
        assertFalse(persistence.isDeleteSupported());
    }
    
    @Test
    public void test2() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        FHIRPersistence persistence = helper.getFHIRPersistenceImplementation("persistenceFactoryName");
        assertNotNull(persistence);
        assertEquals(MockPersistenceImpl.class.getName(), persistence.getClass().getName());
    }
    
    @Test
    public void test3() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        assertFalse(helper.isValidFHIRPersistenceImplementation("foo"));
    }
    
    @Test(expectedExceptions = {FHIRPersistenceNotSupportedException.class})
    public void test4() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        FHIRPersistence persistence = helper.getFHIRPersistenceImplementation();
        assertNotNull(persistence);
        
        assertFalse(persistence.isDeleteSupported());
        persistence.delete(null, null, null);
    }
    
    @Test(expectedExceptions = {FHIRPersistenceException.class})
    public void test5() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        helper.getFHIRPersistenceImplementation("badFactoryName");
        fail("Expected exception was not thrown!");
    }
    
    @Test(expectedExceptions = {FHIRPersistenceException.class})
    public void test6() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        helper.getFHIRPersistenceImplementation("notAFactoryName");
        fail("Expected exception was not thrown!");
    }
    
    @Test(expectedExceptions = {FHIRPersistenceException.class})
    public void test7() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        helper.getFHIRPersistenceImplementation("exceptionFactoryName");
        fail("Expected exception was not thrown!");
    }
    
    @Test(expectedExceptions = {FHIRPersistenceException.class})
    public void test8() throws Exception {
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        helper.getFHIRPersistenceImplementation("noFactoryProperty");
        fail("Expected exception was not thrown!");
    }
    
    @Test(expectedExceptions = {FHIRPersistenceException.class})
    public void test9() throws Exception {
        FHIRRequestContext.set(new FHIRRequestContext("syntax-error"));
        PersistenceHelper helper = new FHIRPersistenceHelper();
        
        helper.getFHIRPersistenceImplementation("noFactoryProperty");
        fail("Expected exception was not thrown!");
    }
}
