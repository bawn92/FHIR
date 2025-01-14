/*
 * (C) Copyright IBM Corp. 2019
 * 
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.path.visitor;

import com.ibm.watson.health.fhir.model.path.FHIRPathBooleanValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathDateTimeValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathDecimalValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathElementNode;
import com.ibm.watson.health.fhir.model.path.FHIRPathIntegerValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathQuantityNode;
import com.ibm.watson.health.fhir.model.path.FHIRPathResourceNode;
import com.ibm.watson.health.fhir.model.path.FHIRPathStringValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathTimeValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathTypeInfoNode;

public class FHIRPathNodeVisitorAdapter<T> extends FHIRPathAbstractNodeVisitor<T> {
    @Override
    protected void doVisit(T param, FHIRPathBooleanValue value) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathDateTimeValue value) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathDecimalValue value) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathElementNode node) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathIntegerValue value) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathQuantityNode node) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathResourceNode node) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathStringValue value) {
        // do nothing
    }

    @Override
    protected void doVisit(T param, FHIRPathTimeValue value) {
        // do nothing
    }
    
    @Override
    protected void doVisit(T param, FHIRPathTypeInfoNode node) {
        // do nothing
    }
}