/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.persistence.helper;

import static com.ibm.watson.health.fhir.model.type.String.string;

import java.util.ArrayList;
import java.util.List;

import com.ibm.watson.health.fhir.model.resource.SearchParameter;
import com.ibm.watson.health.fhir.model.type.Code;
import com.ibm.watson.health.fhir.model.type.Decimal;
import com.ibm.watson.health.fhir.model.type.Markdown;
import com.ibm.watson.health.fhir.model.type.PublicationStatus;
import com.ibm.watson.health.fhir.model.type.Range;
import com.ibm.watson.health.fhir.model.type.ResourceType;
import com.ibm.watson.health.fhir.model.type.SearchParamType;
import com.ibm.watson.health.fhir.model.type.SimpleQuantity;
import com.ibm.watson.health.fhir.model.type.Uri;

/**
 * Helper class to create {@link SearchParameter} model objects
 * 
 * @author rarnold
 *
 */
public class SearchParameterHelper {

    public static final String dummyUri = "http://test.ibm.com/dummy";
    public static final String description = "test description";
    public static final String code = "test-code";

    private SearchParameterHelper() {
        // No Op
    }

    public static SearchParameter makeTestParameter(String name) {
        List<ResourceType> base = new ArrayList<>();
        base.add(ResourceType.PATIENT);

        return SearchParameter.builder().url(Uri.of(dummyUri)).name(string(name)).status(PublicationStatus.ACTIVE).description(Markdown.of(description)).code(Code.of(code)).base(base).type(SearchParamType.STRING).build();

    }

    /**
     * Create a {@link SimpleQuantity} model object with the given values
     *
     * @param code
     * @param unit
     * @param system
     * @param value
     * @return
     */
    public static SimpleQuantity simpleQuantity(String code, String unit, String system, Number value) {
        return SimpleQuantity.builder().code(Code.of(code)).unit(com.ibm.watson.health.fhir.model.type.String.of(unit)).system(Uri.of(system)).value(Decimal.of(value)).build();
    }

    /**
     * Create a range with a high limit but no low limit
     * 
     * @param code
     * @param unit
     * @param system
     * @param value
     * @return
     */
    public static Range range(String code, String unit, String system, Number value) {
        return Range.builder().high(simpleQuantity(code, unit, system, value)).build();
    }
}
