/**
 * (C) Copyright IBM Corp. 2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.search.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.ibm.watson.health.fhir.model.resource.Patient;
import com.ibm.watson.health.fhir.search.context.FHIRSearchContext;
import com.ibm.watson.health.fhir.search.exception.FHIRSearchException;
import com.ibm.watson.health.fhir.search.util.SearchUtil;

/**
 * This testng test class contains methods that test the parsing of the search result _elements parameter in the
 * SearchUtil class.
 * 
 * @author markd
 * @author pbastide
 *
 */
public class ElementsParameterParseTest extends BaseSearchTest {

    @Test(expectedExceptions = { FHIRSearchException.class })
    public void testInvalid_singleElement() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=_id";

        queryParameters.put("_elements", Collections.singletonList("_id"));
        SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString);
    }

    @Test
    public void testFake_singleElement() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=_id";

        queryParameters.put("_elements", Collections.singletonList("bogus"));
        FHIRSearchContext context = SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString);
        assertNotNull(context);
        assertTrue(context.getElementsParameters() == null || context.getElementsParameters().size() == 0);

        String selfUri = SearchUtil.buildSearchSelfUri("http://example.com/" + resourceType.getSimpleName(), context);
        assertFalse(selfUri.contains(queryString), selfUri + " contains unexpected " + queryString);
    }

    @Test(expectedExceptions = { FHIRSearchException.class })
    public void testFake_singleElement_strict() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=_id";

        queryParameters.put("_elements", Collections.singletonList("bogus"));
        SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString, false);
    }

    @Test
    public void testFake_multiElements() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=id,contact,bogus,name";

        queryParameters.put("_elements", Arrays.asList("id", "contact", "bogus", "name"));
        FHIRSearchContext context = SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString);
        assertNotNull(context);
        assertNotNull(context.getElementsParameters());
        assertEquals(3, context.getElementsParameters().size());

        String selfUri = SearchUtil.buildSearchSelfUri("http://example.com/" + resourceType.getSimpleName(), context);
        assertTrue(selfUri.contains("id"), selfUri + " does not contain expected elements param 'id'");
        assertTrue(selfUri.contains("contact"), selfUri + " does not contain expected elements param 'contact'");
        assertTrue(selfUri.contains("name"), selfUri + " does not contain expected elements param 'name'");
        assertFalse(selfUri.contains("bogus"), selfUri + " contains unexpected elements param 'bogus'");
    }

    @Test(expectedExceptions = { FHIRSearchException.class })
    public void testFake_multiElements_strict() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=id,contact,bogus,name";

        queryParameters.put("_elements", Arrays.asList("id", "contact", "bogus", "name"));
        SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString, false);
    }

    @Test
    public void testValid_singleElement() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=name";

        queryParameters.put("_elements", Arrays.asList("name"));
        FHIRSearchContext context = SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString);
        assertNotNull(context);
        assertNotNull(context.getElementsParameters());
        assertEquals(1, context.getElementsParameters().size());
        assertEquals("name", context.getElementsParameters().get(0));

        String selfUri = SearchUtil.buildSearchSelfUri("http://example.com/" + resourceType.getSimpleName(), context);
        assertTrue(selfUri.contains(queryString), selfUri + " does not contain expected " + queryString);
    }

    @Test
    public void testValidMultiElements() throws Exception {
        Map<String, List<String>> queryParameters = new HashMap<>();
        Class<Patient> resourceType = Patient.class;
        String queryString = "&_elements=name,photo,animal,identifier";

        queryParameters.put("_elements", Arrays.asList("name", "photo", "animal", "identifier"));
        FHIRSearchContext context = SearchUtil.parseQueryParameters(resourceType, queryParameters, queryString);
        assertNotNull(context);
        assertNotNull(context.getElementsParameters());

        /*
         * parseElementsParameter is currently skipping animal. [name, photo, animal, identifier] Skipping unknown
         * element name: animal It's not throwing an exception because it's lenient. The reason in DSTU2 Patient.animal
         * exists. In R4, status is deleted https://www.hl7.org/fhir/patient.html
         */

        assertEquals(context.getElementsParameters().size(), 3);
        for (String element : context.getElementsParameters()) {
            assertTrue(queryParameters.get("_elements").contains(element));
        }

        String selfUri = SearchUtil.buildSearchSelfUri("http://example.com/" + resourceType.getSimpleName(), context);
        assertTrue(selfUri.contains("name"), selfUri + " does not contain expected elements param 'name'");
        assertTrue(selfUri.contains("photo"), selfUri + " does not contain expected elements param 'photo'");
        assertFalse(selfUri.contains("animal"), selfUri + " does not contain expected elements param 'animal'");
        assertTrue(selfUri.contains("identifier"), selfUri + " does not contain expected elements param 'identifier'");
    }

}
