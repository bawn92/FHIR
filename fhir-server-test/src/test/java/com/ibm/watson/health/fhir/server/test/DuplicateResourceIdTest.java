/**
 * (C) Copyright IBM Corp. 2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.server.test;

import static com.ibm.watson.health.fhir.model.type.String.string;
import static org.testng.AssertJUnit.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.testng.annotations.Test;

import com.ibm.watson.health.fhir.client.FHIRParameters;
import com.ibm.watson.health.fhir.client.FHIRResponse;
import com.ibm.watson.health.fhir.model.resource.Bundle;
import com.ibm.watson.health.fhir.model.resource.Contract;
import com.ibm.watson.health.fhir.model.resource.Device;
import com.ibm.watson.health.fhir.model.resource.Device.UdiCarrier;
import com.ibm.watson.health.fhir.model.type.Id;
import com.ibm.watson.health.fhir.model.type.Reference;

/**
 * Contains tests for checking correct REST API behavior in the situation where
 * more than one FHIR Resource can have the same logical id. This can happen
 * when the updateCreateEnabled option is set to true. A FHIR Client can create
 * different resources (via the update API) with the same logical id.
 * 
 * @author markd
 *
 */
public class DuplicateResourceIdTest extends FHIRServerTestBase {

    private Device device;
    private Contract contract;
    private String deviceUdi;
    private String contractSubject;

    /**
     * Creates a Device and a Contract resource, each with the same logical id.
     * 
     * @throws Exception
     */
    @Test(groups = { "server-basic" })
    public void testCreateDupIdResources() throws Exception {

        String dupId = UUID.randomUUID().toString();

        FHIRResponse response;

        // Create a Device resource using the previously generated id
        this.deviceUdi = UUID.randomUUID().toString();
        Device device = readResource(Device.class, "Device.json");
        device = device.toBuilder().id(Id.of(dupId))
                .udiCarrier(UdiCarrier.builder().deviceIdentifier(string(this.deviceUdi)).build()).build();

        response = client.update(device);
        assertResponse(response, Response.Status.CREATED.getStatusCode());
        this.device = device;

        // Create a Contract resource with the same id as the Device
        this.contractSubject = "Patient/" + UUID.randomUUID().toString();
        Contract contract = readResource(Contract.class, "Contract.json");

        List<Reference> subjectList = new ArrayList<Reference>();

        for (int i = 0; i < contract.getSubject().size(); i++) {
            Reference ref = contract.getSubject().get(i);
            if (i == 0) {
                ref = Reference.builder().reference(string(this.contractSubject)).build();
            }
            subjectList.add(ref);
        }

        contract = contract.toBuilder().subject(subjectList).id(Id.of(dupId)).build();

        response = client.update(contract);
        assertResponse(response, Response.Status.CREATED.getStatusCode());
        this.contract = contract;
    }

    /**
     * Tests the read API for resources with the same logical id.
     * 
     * @throws Exception
     */
    @Test(enabled = true, groups = { "server-basic" }, dependsOnMethods = { "testCreateDupIdResources" })
    public void testReadDupIdResources() throws Exception {

        FHIRResponse response;

        // Call the 'read' API to retrieve the previously created Device and verify it.
        response = client.read("Device", this.device.getId().getValue());
        assertResponse(response, Response.Status.OK.getStatusCode());
        Device responseDevice = response.getResource(Device.class);
        assertResourceEquals(this.device, responseDevice);

        // Call the 'read' API to retrieve the previously created Contract and verify
        // it.
        response = client.read("Contract", this.contract.getId().getValue());
        assertResponse(response, Response.Status.OK.getStatusCode());
        Contract responseContract = response.getResource(Contract.class);
        assertResourceEquals(this.contract, responseContract);
    }

    /**
     * Tests the version read API for resources with the same logical id.
     * 
     * @throws Exception
     */
    @Test(enabled = true, groups = { "server-basic" }, dependsOnMethods = { "testCreateDupIdResources" })
    public void testVReadDupIdResources() throws Exception {

        FHIRResponse response;

        // Call the 'vread' API to retrieve the previously created Device and verify it.
        response = client.vread("Device", this.device.getId().getValue(), "1");
        assertResponse(response, Response.Status.OK.getStatusCode());
        Device responseDevice = response.getResource(Device.class);
        assertResourceEquals(this.device, responseDevice);

        // Call the 'read' API to retrieve the previously created Contract and verify
        // it.
        response = client.vread("Contract", this.contract.getId().getValue(), "1");
        assertResponse(response, Response.Status.OK.getStatusCode());
        Contract responseContract = response.getResource(Contract.class);
        assertResourceEquals(this.contract, responseContract);
    }

    /**
     * Tests the search API for resources with the same logical id.
     * 
     * @throws Exception
     */
    @Test(enabled = true, groups = { "server-basic" }, dependsOnMethods = { "testCreateDupIdResources" })
    public void testSearchDupIdResources() throws Exception {

        FHIRResponse response;
        FHIRParameters searchParms;
        Bundle responseBundle;

        // Do a search on the previously created Device. Ensure only 1 result is
        // returned and that it validates.
        searchParms = new FHIRParameters();
        searchParms.addSinglevaluedParameter("udi-di", this.deviceUdi);
        response = client.search("Device", searchParms);
        assertResponse(response, Response.Status.OK.getStatusCode());
        responseBundle = response.getResource(Bundle.class);
        assertEquals(1, responseBundle.getTotal().getValue().intValue());
        Device responseDevice = (Device) responseBundle.getEntry().get(0).getResource();
        assertResourceEquals(this.device, responseDevice);

        // Do a search on the previously created Contract. Ensure only 1 result is
        // returned and that it validates.
        searchParms = new FHIRParameters();
        searchParms.addSinglevaluedParameter("patient", this.contractSubject);
        response = client.search("Contract", searchParms);
        assertResponse(response, Response.Status.OK.getStatusCode());
        responseBundle = response.getResource(Bundle.class);
        assertEquals(1, responseBundle.getTotal().getValue().intValue());
        Contract responseContract = (Contract) responseBundle.getEntry().get(0).getResource();
        assertResourceEquals(this.contract, responseContract);
    }

    /**
     * Tests the history API for resources with the same logical id.
     * 
     * @throws Exception
     */
    @Test(enabled = true, groups = { "server-basic" }, dependsOnMethods = { "testCreateDupIdResources" })
    public void testHistoryDupIdResources() throws Exception {

        FHIRResponse response;
        Bundle responseBundle;

        // Do a history on the previously created Device. Ensure only 1 result is
        // returned and that it validates.
        response = client.history("Device", this.device.getId().getValue(), null);
        assertResponse(response, Response.Status.OK.getStatusCode());
        responseBundle = response.getResource(Bundle.class);
        assertEquals(1, responseBundle.getTotal().getValue().intValue());
        Device responseDevice = (Device) responseBundle.getEntry().get(0).getResource();
        assertResourceEquals(this.device, responseDevice);

        // Do a history on the previously created Contract. Ensure only 1 result is
        // returned and that it validates.
        response = client.history("Contract", this.contract.getId().getValue(), null);
        assertResponse(response, Response.Status.OK.getStatusCode());
        responseBundle = response.getResource(Bundle.class);
        assertEquals(1, responseBundle.getTotal().getValue().intValue());
        Contract responseContract = (Contract) responseBundle.getEntry().get(0).getResource();
        assertResourceEquals(this.contract, responseContract);
    }

}
