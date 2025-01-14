/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.ibm.watson.health.fhir.model.format.Format;

/**
 * This class is used to manage runtime configuration for the FHIR model APIs.
 */
public final class FHIRModel {
    /**
     * The format (JSON or XML) to use with the toString method
     */
    public static final String PROPERTY_TO_STRING_FORMAT = "com.ibm.watson.health.fhir.model.toStringFormat";
    
    /**
     * The number of spaces to use when indenting (pretty printing must be enabled)
     */
    public static final String PROPERTY_TO_STRING_INDENT_AMOUNT = "com.ibm.watson.health.fhir.model.toStringIndentAmount";
    
    /**
     * Used to determine whether the toString method return value should be formatted
     */
    public static final String PROPERTY_TO_STRING_PRETTY_PRINTING = "com.ibm.watson.health.fhir.model.toStringPrettyPrinting";
    
    private static final Format DEFAULT_TO_STRING_FORMAT = Format.JSON;
    private static final int DEFAULT_TO_STRING_INDENT_AMOUNT = 2;
    private static final boolean DEFAULT_TO_STRING_PRETTY_PRINTING = true;

    private static final Map<String, Object> properties = new ConcurrentHashMap<>();
   
    private FHIRModel() { }
   
    public static void setToStringFormat(Format format) {
        setProperty(PROPERTY_TO_STRING_FORMAT, format);
    }
    
    public static Format getToStringFormat() {
        return getPropertyOrDefault(PROPERTY_TO_STRING_FORMAT, DEFAULT_TO_STRING_FORMAT, Format.class);
    }
    
    public static void setToStringIndentAmount(int indentAmount) {
        setProperty(PROPERTY_TO_STRING_INDENT_AMOUNT, indentAmount);
    }
    
    public static int getToStringIndentAmount() {
        return getPropertyOrDefault(PROPERTY_TO_STRING_INDENT_AMOUNT, DEFAULT_TO_STRING_INDENT_AMOUNT, Integer.class);
    }
    
    public static void setToStringPrettyPrinting(boolean prettyPrinting) {
        setProperty(PROPERTY_TO_STRING_PRETTY_PRINTING, prettyPrinting);
    }
   
    public static boolean getToStringPrettyPrinting() {
        return getPropertyOrDefault(PROPERTY_TO_STRING_PRETTY_PRINTING, DEFAULT_TO_STRING_PRETTY_PRINTING, Boolean.class);
    }
   
    public static void setProperty(String name, Object value) {
        properties.put(requireNonNull(name), requireNonNull(value));
    }
   
    public static Object removeProperty(String name) {
        return properties.remove(requireNonNull(name));
    }
   
    public static <T> T removeProperty(String name, Class<T> type) {
        return requireNonNull(type).cast(removeProperty(name));
    }
   
    public static Object getProperty(String name) {
        return properties.get(requireNonNull(name));
    }
   
    public static Object getPropertyOrDefault(String name, Object defaultValue) {
        return properties.getOrDefault(requireNonNull(name), requireNonNull(defaultValue));
    }
   
    public static <T> T getProperty(String name, Class<T> type) {
        return requireNonNull(type).cast(getProperty(name));
    }
   
    public static <T> T getPropertyOrDefault(String name, T defaultValue, Class<T> type) {
        return requireNonNull(type).cast(getPropertyOrDefault(name, defaultValue));
    }
   
    public static Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(properties);
    }
   
    public static Set<String> getPropertyNames() {
        return Collections.unmodifiableSet(properties.keySet());
    }
}
