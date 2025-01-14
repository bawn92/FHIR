/**
 * (C) Copyright IBM Corp. 2016,2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.provider;

import static com.ibm.watson.health.fhir.model.util.FHIRUtil.buildOperationOutcome;
import static com.ibm.watson.health.fhir.model.util.FHIRUtil.buildOperationOutcomeIssue;
import static com.ibm.watson.health.fhir.provider.util.FHIRProviderUtil.buildResponse;
import static com.ibm.watson.health.fhir.provider.util.FHIRProviderUtil.getMediaType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.ibm.watson.health.fhir.config.FHIRConfigHelper;
import com.ibm.watson.health.fhir.config.FHIRConfiguration;
import com.ibm.watson.health.fhir.core.FHIRMediaType;
import com.ibm.watson.health.fhir.model.format.Format;
import com.ibm.watson.health.fhir.model.generator.FHIRGenerator;
import com.ibm.watson.health.fhir.model.generator.exception.FHIRGeneratorException;
import com.ibm.watson.health.fhir.model.parser.FHIRParser;
import com.ibm.watson.health.fhir.model.parser.exception.FHIRParserException;
import com.ibm.watson.health.fhir.model.resource.Resource;
import com.ibm.watson.health.fhir.model.type.IssueSeverity;
import com.ibm.watson.health.fhir.model.type.IssueType;

@Consumes({ FHIRMediaType.APPLICATION_FHIR_JSON, MediaType.APPLICATION_JSON, FHIRMediaType.APPLICATION_FHIR_XML, MediaType.APPLICATION_XML })
@Produces({ FHIRMediaType.APPLICATION_FHIR_JSON, MediaType.APPLICATION_JSON, FHIRMediaType.APPLICATION_FHIR_XML, MediaType.APPLICATION_XML })
public class FHIRProvider implements MessageBodyReader<Resource>, MessageBodyWriter<Resource> {
    private static final Logger log = Logger.getLogger(FHIRProvider.class.getName());

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpHeaders requestHeaders;
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Resource.class.isAssignableFrom(type);
    }

    @Override
    public Resource readFrom(Class<Resource> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
        InputStream entityStream) throws IOException, WebApplicationException {
        log.entering(this.getClass().getName(), "readFrom");
        try {
            return FHIRParser.parser(getFormat(mediaType)).parse(entityStream);
        } catch (FHIRParserException e) {
            log.log(Level.WARNING, "an error occurred during resource deserialization", e);
            String acceptHeader = httpHeaders.getFirst(HttpHeaders.ACCEPT);
            Response response = buildResponse(
                buildOperationOutcome(Collections.singletonList(
                    buildOperationOutcomeIssue(IssueSeverity.ValueSet.ERROR, IssueType.ValueSet.INVALID, "FHIRProvider: " + e.getMessage(), e.getPath()))), getMediaType(acceptHeader));
            throw new WebApplicationException(response);
        } finally {
            log.exiting(this.getClass().getName(), "readFrom");
        }
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Resource.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(Resource t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
        OutputStream entityStream) throws IOException, WebApplicationException {
        log.entering(this.getClass().getName(), "writeTo");
        try {
            FHIRGenerator.generator(getFormat(mediaType), isPretty(requestHeaders)).generate(t, entityStream);
        } catch (FHIRGeneratorException e) {
            log.log(Level.WARNING, "an error occurred during resource serialization", e);
            Response response = buildResponse(
                buildOperationOutcome(Collections.singletonList(
                    buildOperationOutcomeIssue(IssueSeverity.ValueSet.FATAL, IssueType.ValueSet.EXCEPTION, "FHIRProvider: " + e.getMessage(), e.getPath()))), mediaType);
            throw new WebApplicationException(response);
        } finally {
            log.exiting(this.getClass().getName(), "writeTo");
        }
    }

    protected static boolean isPretty(HttpHeaders httpHeaders) {
        // Header evaluation
        String headerValue = httpHeaders.getHeaderString(FHIRConfiguration.DEFAULT_PRETTY_RESPONSE_HEADER_NAME);
        if (headerValue != null) {
            if (Boolean.parseBoolean(headerValue)) {
                //explicitly on in the header
                return true;
            } else if (headerValue.toLowerCase().equals("false")) {
                //explicitly off in the header.  ignore header value if it doesn't specify "true" or false"
                return false;
            }
        }

        // Config evaluation (default false)
        return FHIRConfigHelper.getBooleanProperty(FHIRConfiguration.PROPERTY_DEFAULT_PRETTY_PRINT, false);
    }

    @Override
    public long getSize(Resource t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    private Format getFormat(MediaType mediaType) {
        if (mediaType != null) {
            if (mediaType.isCompatible(FHIRMediaType.APPLICATION_FHIR_JSON_TYPE) || 
                    mediaType.isCompatible(FHIRMediaType.APPLICATION_JSON_TYPE)) {
                return Format.JSON;
            } else if (mediaType.isCompatible(FHIRMediaType.APPLICATION_FHIR_XML_TYPE) || 
                    mediaType.isCompatible(FHIRMediaType.APPLICATION_XML_TYPE)) {
                return Format.XML;
            }
        }
        return null;
    }
}
