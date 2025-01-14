/**
 * (C) Copyright IBM Corp. 2016,2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.operation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.health.fhir.exception.FHIROperationException;
import com.ibm.watson.health.fhir.model.resource.OperationDefinition;
import com.ibm.watson.health.fhir.model.resource.OperationOutcome;
import com.ibm.watson.health.fhir.model.resource.Parameters;
import com.ibm.watson.health.fhir.model.resource.Resource;
import com.ibm.watson.health.fhir.model.type.IssueType;
import com.ibm.watson.health.fhir.model.type.OperationParameterUse;
import com.ibm.watson.health.fhir.model.type.ResourceType;
import com.ibm.watson.health.fhir.model.util.FHIRUtil;
import com.ibm.watson.health.fhir.operation.context.FHIROperationContext;
import com.ibm.watson.health.fhir.rest.FHIRResourceHelpers;

public abstract class AbstractOperation implements FHIROperation {

    protected OperationDefinition definition = null;
    
    public AbstractOperation() {
        definition = buildOperationDefinition();
    }
    
    @Override
    public OperationDefinition getDefinition() {
        return definition;
    }

    @Override
    public String getName() {
        if (definition.getCode() == null) return null;
        else return definition.getCode().getValue();
    }

    @Override
    public final Parameters invoke(FHIROperationContext operationContext, Class<? extends Resource> resourceType, String logicalId, String versionId,
        Parameters parameters, FHIRResourceHelpers resourceHelper) throws FHIROperationException {
        validateOperationContext(operationContext, resourceType);
        validateInputParameters(operationContext, resourceType, logicalId, versionId, parameters);
        Parameters result = doInvoke(operationContext, resourceType, logicalId, versionId, parameters, resourceHelper);
        validateOutputParameters(result);
        return result;
    }

    protected abstract OperationDefinition buildOperationDefinition();

    protected int countParameters(Parameters parameters, String name) {
        return getParameters(parameters, name).size();
    }

    protected abstract Parameters doInvoke(FHIROperationContext operationContext, Class<? extends Resource> resourceType, String logicalId, String versionId,
        Parameters parameters, FHIRResourceHelpers resourceHelper) throws FHIROperationException;

    protected Parameters.Parameter getParameter(Parameters parameters, String name) {
        for (Parameters.Parameter parameter : parameters.getParameter()) {
            if (name.equals(parameter.getName().getValue())) {
                return parameter;
            }
        }
        return null;
    }
    
    protected List<OperationDefinition.Parameter> getParameterDefinitions(OperationParameterUse use) {
        List<OperationDefinition.Parameter> parameterDefinitions = new ArrayList<OperationDefinition.Parameter>();
        OperationDefinition definition = getDefinition();
        for (OperationDefinition.Parameter parameter : definition.getParameter()) {
            if (use.getValue().equals(parameter.getUse().getValue())) {
                parameterDefinitions.add(parameter);
            }
        }
        return parameterDefinitions;
    }

    protected List<Parameters.Parameter> getParameters(Parameters parameters, String name) {
        List<Parameters.Parameter> result = new ArrayList<Parameters.Parameter>();
        for (Parameters.Parameter parameter : parameters.getParameter()) {
            if (parameter.getName() != null && name.equals(parameter.getName().getValue())) {
                result.add(parameter);
            }
        }
        return result;
    }
    
    
    protected List<String> getResourceTypeNames() {
        List<String> resourceTypeNames = new ArrayList<String>();
        OperationDefinition definition = getDefinition();
        for (ResourceType type : definition.getResource()) {
            resourceTypeNames.add(type.getValue());
        }
        return resourceTypeNames;
    }
    
    protected void validateInputParameters(FHIROperationContext operationContext, Class<? extends Resource> resourceType, String logicalId, String versionId, Parameters parameters) throws FHIROperationException {
        validateParameters(parameters, OperationParameterUse.IN);
    }

    protected void validateOperationContext(FHIROperationContext operationContext, Class<? extends Resource> resourceType) throws FHIROperationException {
        OperationDefinition definition = getDefinition();
        switch (operationContext.getType()) {
        case INSTANCE:
            if (definition.getInstance().getValue() == false) {
                String msg = "Operation context INSTANCE is not allowed for operation: '" + getName() + "'";
                throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
            }
            break;
        case RESOURCE_TYPE:
            if (definition.getType().getValue() == false) {
                String msg = "Operation context RESOURCE_TYPE is not allowed for operation: '" + getName() + "'";
                throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
            } else {
                String resourceTypeName = resourceType.getSimpleName();
                List<String> resourceTypeNames = getResourceTypeNames();
                if (!resourceTypeNames.contains(resourceTypeName) && !resourceTypeNames.contains("Resource")) {
                    String msg = "Resource type: '" + resourceTypeName + "' is not allowed for operation: '" + getName() + "'";
                    throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
                }
            }
            break;
        case SYSTEM:
            if (definition.getSystem().getValue() == false) {
                String msg = "Operation context SYSTEM is not allowed for operation: '" + getName() + "'";
                throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
            }
            break;
        default:
            break;
        }
    }

    protected void validateOutputParameters(Parameters result) throws FHIROperationException {
        validateParameters(result, OperationParameterUse.OUT);
    }
    
    protected void validateParameters(Parameters parameters, OperationParameterUse use) throws FHIROperationException {        
        String direction = OperationParameterUse.IN.equals(use) ? "input" : "output";

        // Retrieve the set of parameters from the OperationDefinition matching the specified use (in/out).
        List<OperationDefinition.Parameter> opDefParameters = getParameterDefinitions(use);

        // Verify that each defined parameter is specified appropriately in the Parameters object.
        for (OperationDefinition.Parameter parameterDefinition : opDefParameters) {
            String name = parameterDefinition.getName().getValue();
            int min = parameterDefinition.getMin().getValue();
            String max = parameterDefinition.getMax().getValue();
            int count = countParameters(parameters, name);
            if (count < min) {
                String msg = "Missing required " + direction + " parameter: '" + name + "'";
                throw buildExceptionWithIssue(msg, IssueType.ValueSet.REQUIRED);
            }
            if (!"*".equals(max)) {
                int maxValue = Integer.parseInt(max);                
                if (count > maxValue) {
                    String msg = "Number of occurrences of " + direction + " parameter: '" + name + "' greater than allowed maximum: " + maxValue;
                    throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
                }
            }
            if (count > 0) {
                List<Parameters.Parameter> inputParameters = getParameters(parameters, name);
                for (Parameters.Parameter inputParameter : inputParameters) {
                    String parameterValueTypeName = inputParameter.getResource() != null? 
                            inputParameter.getResource().getClass().getName() : inputParameter.getValue().getClass().getName();
                    String parameterDefinitionTypeName = parameterDefinition.getType().getValue();
                    parameterDefinitionTypeName = parameterDefinitionTypeName.substring(0, 1).toUpperCase() + parameterDefinitionTypeName.substring(1);
                    try {
                        Class<?> parameterValueType, parameterDefinitionType;
                        parameterValueType = Class.forName(parameterValueTypeName);

                        if (FHIRUtil.isStandardResourceType(parameterDefinitionTypeName)){
                            parameterDefinitionType = Class.forName("com.ibm.watson.health.fhir.model.resource." + parameterDefinitionTypeName);
                        } else {
                            parameterDefinitionType = Class.forName("com.ibm.watson.health.fhir.model.type." + parameterDefinitionTypeName);
                        }
                        
                        if (!parameterDefinitionType.isAssignableFrom(parameterValueType)) {
                            String msg = "Invalid type: '" + parameterValueTypeName + "' for " + direction + " parameter: '" + name + "'";
                            throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
                        }
                    } catch (FHIROperationException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new FHIROperationException("An unexpected error occurred during type checking", e);
                    }
                    /*
                    if (!parameterValueTypeName.equalsIgnoreCase(parameterDefinition.getType().getValue())) {
                        throw new FHIROperationException("Invalid type: '" + parameterValueTypeName + "' for " + direction + " parameter: '" + name + "'");
                    }
                    */
                }
            }
        }
        
        // Next, verify that each parameter contained in the Parameters object is defined
        // in the OperationDefinition.   This will root out any extaneous parameters included
        // in the Parameters object.
        for (Parameters.Parameter p : parameters.getParameter()) {
            String name = p.getName().getValue();
            
            OperationDefinition.Parameter opDefParameter = findOpDefParameter(opDefParameters, name);
            if (opDefParameter == null) {
                // Avoid throwing the exception for an OUT parameter called "return".
                if (!(OperationParameterUse.OUT.equals(use) && "return".equals(name))) {
                    String msg = "Unexpected " + direction + " parameter found: " + name;
                    throw buildExceptionWithIssue(msg, IssueType.ValueSet.INVALID);
                }
            }
        }
    }

    /**
     * Returns the OperationDefinitionParameter with the specified name or null if it wasn't found.
     * @param opDefParameters the list of parameters from the OperationDefinition
     * @param name the name of the parameter to find
     * @return
     */
    protected OperationDefinition.Parameter findOpDefParameter(List<OperationDefinition.Parameter> parameters, String name) {
        for (OperationDefinition.Parameter p : parameters) {
            if (p.getName().getValue().equals(name)) {
                return p;
            }
        }
        return null;
    }
    
    protected FHIROperationException buildExceptionWithIssue(String msg, IssueType.ValueSet issueType) throws FHIROperationException {
        return buildExceptionWithIssue(msg, issueType, null);
    }
    
    protected FHIROperationException buildExceptionWithIssue(String msg, IssueType.ValueSet issueType, Throwable cause) throws FHIROperationException {
        OperationOutcome.Issue ooi = FHIRUtil.buildOperationOutcomeIssue(msg, issueType);
        return new FHIROperationException(msg, cause).withIssue(ooi);
    }
}
