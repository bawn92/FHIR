/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.cli.invoker;

import javax.json.JsonObject;

import com.ibm.watson.health.fhir.model.resource.Resource;

/**
 * This class is the OperationInvoker implementation for the 'validate' operation.
 * 
 * @author padams
 */
public class ValidateInvoker extends OperationInvoker {

    /* (non-Javadoc)
     * @see com.ibm.watson.health.fhir.cli.OperationInvoker#invoke(com.ibm.watson.health.fhir.cli.InvocationContext)
     */
    @Override
    public void doInvoke(InvocationContext ic) throws Exception {
        Object resource = ic.getRequestResourceWithExcp();
        if (resource instanceof Resource) {
            response = client.validate((Resource)resource, requestHeaders);
        } else {
            response = client.validate((JsonObject)resource, requestHeaders);
        }
    }
}
