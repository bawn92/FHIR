/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.swagger.generator;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.xml.bind.annotation.XmlElement;

import com.ibm.watsonhealth.fhir.model.BackboneElement;
import com.ibm.watsonhealth.fhir.model.Bundle;
import com.ibm.watsonhealth.fhir.model.BundleEntry;
import com.ibm.watsonhealth.fhir.model.DomainResource;
import com.ibm.watsonhealth.fhir.model.ElementDefinition;
import com.ibm.watsonhealth.fhir.model.ObjectFactory;
import com.ibm.watsonhealth.fhir.model.Quantity;
import com.ibm.watsonhealth.fhir.model.Resource;
import com.ibm.watsonhealth.fhir.model.SearchParameter;
import com.ibm.watsonhealth.fhir.model.StructureDefinition;
import com.ibm.watsonhealth.fhir.model.util.FHIRUtil;
import com.ibm.watsonhealth.fhir.model.util.FHIRUtil.Format;
import com.ibm.watsonhealth.fhir.search.util.SearchUtil;

public class FHIRSwaggerGenerator {
    private static final JsonBuilderFactory factory = Json.createBuilderFactory(null);
    private static final Map<Class<?>, StructureDefinition> structureDefinitionMap = buildStructureDefinitionMap();
    
    public static void main(String[] args) throws Exception {
        /*
        for (Class<?> key : structureDefinitionMap.keySet()) {
            StructureDefinition structureDefinition = structureDefinitionMap.get(key);
            System.out.println("key: " + key + ", value: " + structureDefinition.getUrl().getValue());
        }
        */
        
        Filter filter = null;
        if (args.length == 1) {
            filter = createFilter(args[0]);
        } else {
            filter = createAcceptAllFilter();
        }
 //     filter = createFilter("Patient(create);Observation(create);QuestionnaireResponse(create);RiskAssessment(read)");
 //     filter = createFilter("Patient(create,read,vread,update,delete,search,history)");

        JsonObjectBuilder swagger = factory.createObjectBuilder();
        swagger.add("swagger", "2.0");
        
        JsonObjectBuilder info = factory.createObjectBuilder();
        info.add("title", "FHIR REST API");
        info.add("description", "IBM Watson Health Cloud FHIR Server API");
        info.add("version", "0.1");
        swagger.add("info", info);
        
        swagger.add("basePath", "/fhir-server/api");
        
        JsonObjectBuilder paths = factory.createObjectBuilder();
        JsonObjectBuilder definitions = factory.createObjectBuilder();
        
        List<String> classNames = getClassNames();
        for (String className : classNames) {
            Class<?> modelClass = Class.forName("com.ibm.watsonhealth.fhir.model." + className);
            if (DomainResource.class.isAssignableFrom(modelClass) && filter.acceptResourceType(modelClass)) {
                generatePaths(modelClass, paths, filter);
            }
            generateDefinition(modelClass, definitions);
        }
        
        // FHIR metadata operation
        JsonObjectBuilder path = factory.createObjectBuilder();
        generateMetadataPathItem(path);
        paths.add("/metadata", path);
        
        // TODO: FHIR batch operation
        
        // TODO: FHIR transaction operation
        
        swagger.add("paths", paths);
        JsonObjectBuilder parameters = factory.createObjectBuilder();
        generateParameters(parameters, filter);
        JsonObject parametersObject = parameters.build();
        if (!parametersObject.isEmpty()) {
            swagger.add("parameters", parametersObject);
        }
        swagger.add("definitions", definitions);
        
        Map<String, Object> config = new HashMap<String, Object>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory factory = Json.createWriterFactory(config);
        JsonWriter writer = factory.createWriter(System.out);
        writer.writeObject(swagger.build());
    }
    
    private static Map<Class<?>, StructureDefinition> buildStructureDefinitionMap() {
        Map<Class<?>, StructureDefinition> structureDefinitionMap = new HashMap<Class<?>, StructureDefinition>();
        try {
            populateStructureDefinitionMap(structureDefinitionMap, "profiles-resources.xml");
            populateStructureDefinitionMap(structureDefinitionMap, "profiles-types.xml");
        } catch (Exception e) {
            throw new Error(e);
        }
        return structureDefinitionMap;
    }
    
    private static void populateStructureDefinitionMap(Map<Class<?>, StructureDefinition> structureDefinitionMap, String structureDefinitionFile) throws Exception {
        InputStream stream = FHIRSwaggerGenerator.class.getClassLoader().getResourceAsStream(structureDefinitionFile);
        Bundle bundle = FHIRUtil.read(Bundle.class, Format.XML, stream);
        for (BundleEntry entry : bundle.getEntry()) {
            StructureDefinition structureDefinition = entry.getResource().getStructureDefinition();
            if (structureDefinition != null) {
                String className = structureDefinition.getName().getValue();
                className = className.substring(0, 1).toUpperCase() + className.substring(1);
                Class<?> modelClass = Class.forName("com.ibm.watsonhealth.fhir.model." + className);
                structureDefinitionMap.put(modelClass, structureDefinition);
            }
        }
    }

    private static void generateParameters(JsonObjectBuilder parameters, Filter filter) {
        if (filter.acceptOperation("read") || filter.acceptOperation("vread") || filter.acceptOperation("update") || filter.acceptOperation("delete") || filter.acceptOperation("history")) {
            JsonObjectBuilder id = factory.createObjectBuilder();
            id.add("name", "id");
            id.add("description", "logical identifier");
            id.add("in", "path");
            id.add("required", true);
            id.add("type", "string");
            parameters.add("idParam", id);
        }
        if (filter.acceptOperation("vread")) {
            JsonObjectBuilder vid = factory.createObjectBuilder();
            vid.add("name", "vid");
            vid.add("description", "version identifier");
            vid.add("in", "path");
            vid.add("required", true);
            vid.add("type", "string");
            parameters.add("vidParam", vid);
        }
        if (filter.acceptOperation("search")) {
            for (SearchParameter searchParameter : SearchUtil.getSearchParameters(Resource.class)) {
                JsonObjectBuilder parameter = factory.createObjectBuilder();
                String name = searchParameter.getName().getValue();
                parameter.add("name", name);
                parameter.add("description", searchParameter.getDescription().getValue());
                parameter.add("in", "query");
                parameter.add("required", false);
                parameter.add("type", "string");
                parameters.add(name + "Param", parameter);
            }
        }
    }
    
    private static void generatePaths(Class<?> modelClass, JsonObjectBuilder paths, Filter filter) {
        JsonObjectBuilder path = factory.createObjectBuilder();       
        // FHIR create operation
        if (filter.acceptOperation(modelClass, "create")) {
            generateCreatePathItem(modelClass, path);
        }
        // FHIR search operation
        if (filter.acceptOperation(modelClass, "search")) {
            generateSearchPathItem(modelClass, path);
        }
        JsonObject pathObject = path.build();
        if (!pathObject.isEmpty()) {
            paths.add("/" + modelClass.getSimpleName(), pathObject);
        }

        path = factory.createObjectBuilder();
        // FHIR vread operation
        if (filter.acceptOperation(modelClass, "vread")) {
            generateVreadPathItem(modelClass, path);
        }
        pathObject = path.build();
        if (!pathObject.isEmpty()) {
            paths.add("/" + modelClass.getSimpleName() + "/{id}/_history/{vid}", pathObject);
        }
        
        path = factory.createObjectBuilder();
        // FHIR read operation
        if (filter.acceptOperation(modelClass, "read")) {
            generateReadPathItem(modelClass, path);
        }
        // FHIR update operation
        if (filter.acceptOperation(modelClass, "update")) {
            generateUpdatePathItem(modelClass, path);
        }
        // FHIR delete operation
        if (filter.acceptOperation(modelClass, "delete")) {
            generateDeletePathItem(modelClass, path);
        }
        pathObject = path.build();
        if (!pathObject.isEmpty()) {
            paths.add("/" + modelClass.getSimpleName() + "/{id}", pathObject);
        }
        
        // FHIR history operation
        path = factory.createObjectBuilder();
        if (filter.acceptOperation(modelClass, "history")) {
            generateHistoryPathItem(modelClass, path);
        }
        pathObject = path.build();
        if (!pathObject.isEmpty()) {
            paths.add("/" + modelClass.getSimpleName() + "/{id}/_history", pathObject);
        }
    }
    
    private static void generateCreatePathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder post = factory.createObjectBuilder();
        
        post.add("summary", "Create" + getArticle(modelClass) + modelClass.getSimpleName() + " resource");
        post.add("operationId", "create" + modelClass.getSimpleName());
        
        JsonArrayBuilder consumes = factory.createArrayBuilder();
        consumes.add("application/json+fhir");
        post.add("consumes", consumes);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        JsonObjectBuilder bodyParameter = factory.createObjectBuilder();
        
        bodyParameter.add("name", "body");
        bodyParameter.add("in", "body");
        bodyParameter.add("required", true);
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/" + modelClass.getSimpleName());
        bodyParameter.add("schema", schema);
        
        parameters.add(bodyParameter);
        post.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Create " + modelClass.getSimpleName() + " operation successful");
        responses.add("201", response);
        post.add("responses", responses);
        
        path.add("post", post);
    }

    private static void generateReadPathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder get = factory.createObjectBuilder();
        
        get.add("summary", "Read" + getArticle(modelClass) + modelClass.getSimpleName() + " resource");
        get.add("operationId", "read" + modelClass.getSimpleName());
        
        JsonArrayBuilder produces = factory.createArrayBuilder();
        produces.add("application/json+fhir");
        get.add("produces", produces);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        JsonObjectBuilder idParamRef = factory.createObjectBuilder();
        idParamRef.add("$ref", "#/parameters/idParam");
        parameters.add(idParamRef);
        
        get.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Read " + modelClass.getSimpleName() + " operation successful");
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/" + modelClass.getSimpleName());
        
        response.add("schema", schema);
        responses.add("200", response);
        get.add("responses", responses);
        
        path.add("get", get);
    }

    private static void generateVreadPathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder get = factory.createObjectBuilder();
        
        get.add("summary", "Read specific version of" + getArticle(modelClass) + modelClass.getSimpleName() + " resource");
        get.add("operationId", "vread" + modelClass.getSimpleName());
        
        JsonArrayBuilder produces = factory.createArrayBuilder();
        produces.add("application/json+fhir");
        get.add("produces", produces);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        JsonObjectBuilder idParamRef = factory.createObjectBuilder();
        idParamRef.add("$ref", "#/parameters/idParam");
        parameters.add(idParamRef);
        
        JsonObjectBuilder vidParamRef = factory.createObjectBuilder();
        vidParamRef.add("$ref", "#/parameters/vidParam");
        parameters.add(vidParamRef);
        
        get.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Versioned read " + modelClass.getSimpleName() + " operation successful");
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/" + modelClass.getSimpleName());
        
        response.add("schema", schema);
        responses.add("200", response);
        get.add("responses", responses);
        
        path.add("get", get);
    }

    private static void generateUpdatePathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder put = factory.createObjectBuilder();
        
        put.add("summary", "Update an existing " + modelClass.getSimpleName() + " resource");
        put.add("operationId", "update" + modelClass.getSimpleName());
        
        JsonArrayBuilder consumes = factory.createArrayBuilder();
        consumes.add("application/json+fhir");
        put.add("consumes", consumes);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        
        JsonObjectBuilder idParamRef = factory.createObjectBuilder();
        idParamRef.add("$ref", "#/parameters/idParam");
        parameters.add(idParamRef);
        
        JsonObjectBuilder bodyParameter = factory.createObjectBuilder();
        bodyParameter.add("name", "body");
        bodyParameter.add("in", "body");
        bodyParameter.add("required", true);
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/" + modelClass.getSimpleName());
        bodyParameter.add("schema", schema);
        
        parameters.add(bodyParameter);
        put.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Update " + modelClass.getSimpleName() + " operation successful");
        responses.add("200", response);
        put.add("responses", responses);
        
        path.add("put", put);
    }

    private static void generateDeletePathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder delete = factory.createObjectBuilder();
        
        delete.add("summary", "Delete" + getArticle(modelClass) + modelClass.getSimpleName() + " resource");
        delete.add("operationId", "delete" + modelClass.getSimpleName());
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        
        JsonObjectBuilder idParamRef = factory.createObjectBuilder();
        idParamRef.add("$ref", "#/parameters/idParam");
        parameters.add(idParamRef);
        
        delete.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Delete " + modelClass.getSimpleName() + " operation successful");
        responses.add("200", response);
        delete.add("responses", responses);
        
        path.add("delete", delete);
    }

    private static void generateSearchPathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder get = factory.createObjectBuilder();
        
        get.add("summary", "Search for " + modelClass.getSimpleName() + " resources");
        get.add("operationId", "search" + modelClass.getSimpleName());
        
        JsonArrayBuilder produces = factory.createArrayBuilder();
        produces.add("application/json+fhir");
        get.add("produces", produces);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        
        generateSearchParameters(modelClass, parameters);
        
        get.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "Search " + modelClass.getSimpleName() + " operation successful");
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/Bundle");
        
        response.add("schema", schema);
        responses.add("200", response);
        get.add("responses", responses);
        
        path.add("get", get);
    }

    @SuppressWarnings("unchecked")
    private static void generateSearchParameters(Class<?> modelClass, JsonArrayBuilder parameters) {
        List<SearchParameter> searchParameters = new ArrayList<SearchParameter>(SearchUtil.getSearchParameters(Resource.class));
        searchParameters.addAll(SearchUtil.getSearchParameters((Class<? extends Resource>) modelClass));
        for (SearchParameter searchParameter : searchParameters) {
            JsonObjectBuilder parameter = factory.createObjectBuilder();
            String name = searchParameter.getName().getValue();
            if (name.startsWith("_")) {
                parameter.add("$ref", "#/parameters/" + name + "Param");
            } else {
                parameter.add("name", name);
                parameter.add("description", searchParameter.getDescription().getValue());
                parameter.add("in", "query");
                parameter.add("type", "string");
                parameter.add("required", false);
            }
            parameters.add(parameter);
        }
    }

    private static void generateHistoryPathItem(Class<?> modelClass, JsonObjectBuilder path) {
        JsonObjectBuilder get = factory.createObjectBuilder();
        
        get.add("summary", "Return the history for" + getArticle(modelClass) + modelClass.getSimpleName() + " resource");
        get.add("operationId", "history" + modelClass.getSimpleName());
        
        JsonArrayBuilder produces = factory.createArrayBuilder();
        produces.add("application/json+fhir");
        get.add("produces", produces);
        
        JsonArrayBuilder parameters = factory.createArrayBuilder();
        JsonObjectBuilder idParamRef = factory.createObjectBuilder();
        idParamRef.add("$ref", "#/parameters/idParam");
        parameters.add(idParamRef);
        
        get.add("parameters", parameters);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "History " + modelClass.getSimpleName() + " operation successful");
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/Bundle");
        
        response.add("schema", schema);
        responses.add("200", response);
        get.add("responses", responses);
        
        path.add("get", get);
    }

    private static void generateMetadataPathItem(JsonObjectBuilder path) {
        JsonObjectBuilder get = factory.createObjectBuilder();
        
        get.add("summary", "Get the FHIR conformance statement for this endpoint");
        get.add("operationId", "metadata");
        
        JsonArrayBuilder produces = factory.createArrayBuilder();
        produces.add("application/json+fhir");
        get.add("produces", produces);
        
        JsonObjectBuilder responses = factory.createObjectBuilder();
        
        JsonObjectBuilder response = factory.createObjectBuilder();
        response.add("description", "metadata operation successful");
        
        JsonObjectBuilder schema = factory.createObjectBuilder();
        schema.add("$ref", "#/definitions/Conformance");
        
        response.add("schema", schema);
        responses.add("200", response);
        get.add("responses", responses);
        
        path.add("get", get);
    }

    private static void generateDefinition(Class<?> modelClass, JsonObjectBuilder definitions) throws Exception {
        if (!isEnumerationWrapperClass(modelClass) && !hasAdapterClass(modelClass)) {
            JsonObjectBuilder definition = factory.createObjectBuilder();
            JsonObjectBuilder properties = factory.createObjectBuilder();
            JsonArrayBuilder required = factory.createArrayBuilder();
            
            StructureDefinition structureDefinition = getStructureDefinition(modelClass);
            /*
            if (!BackboneElement.class.isAssignableFrom(modelClass)) {
                String description = structureDefinition.getDifferential().getElement().get(0).getDefinition().getValue();
                definition.add("description", description);
            }
            */
            for (Field field : modelClass.getDeclaredFields()) {
                XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                if (xmlElement != null) {
                    if (xmlElement.required()) {
                        if (!"##default".equals(xmlElement.name())) {
                            required.add(xmlElement.name());
                        } else {
                            required.add(field.getName());
                        }
                    }
                }
                generateProperty(structureDefinition, modelClass, field, properties);
            }
            
            Class<?> superClass = modelClass.getSuperclass();
            if (superClass != null && "com.ibm.watsonhealth.fhir.model".equals(superClass.getPackage().getName())) {
                JsonArrayBuilder allOf = factory.createArrayBuilder();
                
                JsonObjectBuilder ref = factory.createObjectBuilder();
                ref.add("$ref", "#/definitions/" + superClass.getSimpleName());
                allOf.add(ref);
                
                JsonObjectBuilder wrapper = factory.createObjectBuilder();
                wrapper.add("type", "object");
                wrapper.add("properties", properties);
                allOf.add(wrapper);

                definition.add("allOf", allOf);
            } else {
                definition.add("type", "object");
                if (Resource.class.equals(modelClass)) {
                    definition.add("discriminator", "resourceType");
                }
                definition.add("properties", properties);
            }
            
            JsonArray requiredArray = required.build();
            if (!requiredArray.isEmpty()) {
                definition.add("required", requiredArray);
            }
            
            definitions.add(modelClass.getSimpleName(), definition);
        }
    }
    
    private static StructureDefinition getStructureDefinition(Class<?> modelClass) {
        StructureDefinition structureDefinition = structureDefinitionMap.get(modelClass);
        return (structureDefinition != null) ? structureDefinition : getEnclosingStructureDefinition(modelClass);
    }
    
    private static StructureDefinition getEnclosingStructureDefinition(Class<?> modelClass) {
        StructureDefinition structureDefinition = null;
        int nameLength = 0;
        for (Class<?> key : structureDefinitionMap.keySet()) {
            if (modelClass.getSimpleName().startsWith(key.getSimpleName()) && key.getSimpleName().length() > nameLength) {
                structureDefinition = structureDefinitionMap.get(key);
                nameLength = key.getSimpleName().length();
            }
        }
        return structureDefinition;
    }

    private static void generateProperty(StructureDefinition structureDefinition, Class<?> modelClass, Field field, JsonObjectBuilder properties) throws Exception {
        JsonObjectBuilder property = factory.createObjectBuilder();
        
        boolean many = false;
        
        String fieldName = field.getName();
        XmlElement xmlElement = field.getAnnotation(XmlElement.class);
        if (xmlElement != null) {
            if (!"##default".equals(xmlElement.name())) {
                fieldName = xmlElement.name();
            }
        }
        
        Type fieldType = field.getType();
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            fieldType = parameterizedType.getActualTypeArguments()[0];
            many = true;
        }
        
        Class<?> fieldClass = (Class<?>) fieldType;
        ElementDefinition elementDefinition = getElementDefinition(structureDefinition, modelClass, fieldName, fieldClass);
        String description = null;
        if (elementDefinition != null) {
            description = elementDefinition.getDefinition().getValue();
        }
        
        if (isEnumerationWrapperClass(fieldClass)) {
            property.add("type", "string");
            JsonArrayBuilder constants = factory.createArrayBuilder();
            Class<?> enumClass = Class.forName(fieldClass.getName() + "List");
            for (Object constant : enumClass.getEnumConstants()) {
                Method method = constant.getClass().getMethod("value");
                String value = (String) method.invoke(constant);
                constants.add(value);
            }
            property.add("enum", constants);
        } else if (hasAdapterClass(fieldClass)) {
            Class<?> adapterClass = getAdapterClass(fieldClass);
            ParameterizedType parameterizedType = (ParameterizedType) adapterClass.getGenericSuperclass();
            Class<?> valueType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
//          Class<?> boundType = (Class<?>) parameterizedType.getActualTypeArguments()[1];
//          System.out.println("valueType: " + valueType + ", boundType: " + boundType);
            if (String.class.equals(valueType)) {
                property.add("type", "string");
            } else if (Integer.class.equals(valueType) || BigInteger.class.equals(valueType)) {
                property.add("type", "integer");
            } else if (Double.class.equals(valueType) || BigDecimal.class.equals(valueType)) {
                property.add("type", "number");
            } else if (Boolean.class.equals(valueType)) {
                property.add("type", "boolean");
            } else if (Resource.class.equals(valueType)) {
                property.add("$ref", "#/definitions/Resource");
            }
        } else if (String.class.equals(fieldClass)) {
            property.add("type", "string");
        } else {
            property.add("$ref", "#/definitions/" + fieldClass.getSimpleName());
        }
        
        if (description != null) {
            property.add("description", description);
        }        
        
        if (many) {
            JsonObjectBuilder wrapper = factory.createObjectBuilder();
            wrapper.add("type", "array");
            wrapper.add("items", property);
            properties.add(fieldName, wrapper);
        } else {
            properties.add(fieldName, property);
        }
    }
    
    private static ElementDefinition getElementDefinition(StructureDefinition structureDefinition, Class<?> modelClass, String fieldName, Class<?> fieldClass) {
        String structureDefinitionName = structureDefinition.getName().getValue();
        String path = structureDefinitionName;
        
        String pathEnding = fieldName;
        if (BackboneElement.class.isAssignableFrom(modelClass) && !BackboneElement.class.equals(modelClass)) {
            String modelClassName = modelClass.getSimpleName();
            modelClassName = modelClassName.substring(structureDefinitionName.length());
            modelClassName = modelClassName.substring(0, 1).toLowerCase() + modelClassName.substring(1);
            
            if (Character.isDigit(modelClassName.charAt(modelClassName.length() - 1))) {
                modelClassName = modelClassName.substring(0, modelClassName.length() - 1);
            }
            
            path += "." + modelClassName;
            pathEnding = modelClassName + "." + fieldName;
        }
        
        path += "." + fieldName;
        
        for (ElementDefinition elementDefinition : structureDefinition.getDifferential().getElement()) {
            String elementDefinitionPath = elementDefinition.getPath().getValue();
            if (elementDefinitionPath.endsWith("[x]")) {
                if (Quantity.class.isAssignableFrom(fieldClass)) {
                    elementDefinitionPath = elementDefinitionPath.replace("[x]", "Quantity");
                } else {
                    elementDefinitionPath = elementDefinitionPath.replace("[x]", fieldClass.getSimpleName());
                }
            }
            if (elementDefinitionPath.equals(path) || (elementDefinitionPath.startsWith(structureDefinitionName) && elementDefinitionPath.endsWith(pathEnding))) {
                return elementDefinition;
            }
        }
        
        return null;
    }

    private static List<String> getClassNames() {
        List<String> classNames = new ArrayList<String>();
        for (Method method : ObjectFactory.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.startsWith("create") && method.getParameterCount() == 0) {
                String className = methodName.substring("create".length());
                classNames.add(className);
            }
        }
        Collections.sort(classNames);
        return classNames;
    }

    private static boolean isEnumerationWrapperClass(Class<?> type) {
        try {
            Class.forName(type.getName() + "List");
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    private static Class<?> getAdapterClass(Class<?> modelClass) {
        try {
            if (modelClass.getPackage().getName().startsWith("com.ibm.watsonhealth.fhir.model")) {
                Class<?> adapterClass = Class.forName("com.ibm.watsonhealth.fhir.model.adapters." + modelClass.getSimpleName() + "Adapter");
                return adapterClass;                
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    private static boolean hasAdapterClass(Class<?> modelClass) {
        return getAdapterClass(modelClass) != null;
    }

    private static class Filter {
        private Map<String, List<String>> filterMap = null;
        
        public Filter(Map<String, List<String>> filterMap) {
            this.filterMap = filterMap;
        }
        
        public boolean acceptResourceType(Class<?> resourceType) {
            return filterMap.containsKey(resourceType.getSimpleName());
        }
        
        /*
        public boolean acceptOperation(String resourceType, String operation) {
            return filterMap.get(resourceType).contains(operation);
        }
        */
        
        public boolean acceptOperation(Class<?> resourceType, String operation) {
            return filterMap.get(resourceType.getSimpleName()).contains(operation);
        }
        
        public boolean acceptOperation(String operation) {
            for (String resourceType : filterMap.keySet()) {
                List<String> operationList = filterMap.get(resourceType);
                if (operationList.contains(operation)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static Filter createFilter(String filterString) {
        return new Filter(parseFilterString(filterString));
    }

    private static Map<String, List<String>> parseFilterString(String filterString) {
        Map<String, List<String>> filterMap = new HashMap<String, List<String>>();
        for (String component : filterString.split(";")) {
            String resourceType = component.substring(0, component.indexOf("("));
            String operations = component.substring(component.indexOf("(") + 1, component.indexOf(")"));            
            List<String> operationList = new ArrayList<String>();
            for (String operation : operations.split(",")) {
                operationList.add(operation);
            }
            filterMap.put(resourceType, operationList);
        }
        return filterMap;
    }

    private static Filter createAcceptAllFilter() throws Exception {
        return new Filter(buildAcceptAllFilterMap());
    }

    // build filter map for all domain resources and operations
    private static Map<String, List<String>> buildAcceptAllFilterMap() throws Exception {
        Map<String, List<String>> filterMap = new HashMap<String, List<String>>();
        for (String className : getClassNames()) {
            Class<?> modelClass = Class.forName("com.ibm.watsonhealth.fhir.model." + className);
            if (DomainResource.class.isAssignableFrom(modelClass)) {
                String resourceType = className;
                List<String> operationList = Arrays.asList("create", "read", "vread", "update", "delete", "search", "history", "batch", "transaction");
                filterMap.put(resourceType, operationList);
            }
        }
        return filterMap;
    }
    
    private static String getArticle(Class<?> modelClass) {
        List<String> prefixes = Arrays.asList("A", "E", "I", "O", "Un");
        for (String prefix : prefixes) {
            if (modelClass.getSimpleName().startsWith(prefix)) {
                return " an ";
            }
        }
        return " a ";
    }
}