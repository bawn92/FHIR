/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.path.function;

import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.empty;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.getStringValue;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.hasStringValue;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.singleton;

import java.util.Collection;
import java.util.List;

import com.ibm.watson.health.fhir.model.path.FHIRPathNode;
import com.ibm.watson.health.fhir.model.path.evaluator.FHIRPathEvaluator.EvaluationContext;

public class ReplaceFunction extends FHIRPathAbstractFunction {
    @Override
    public String getName() {
        return "replace";
    }

    @Override
    public int getMinArity() {
        return 2;
    }

    @Override
    public int getMaxArity() {
        return 2;
    }
    
    @Override
    public Collection<FHIRPathNode> apply(EvaluationContext evaluationContext, Collection<FHIRPathNode> context, List<Collection<FHIRPathNode>> arguments) {
        if (!hasStringValue(context)) {
            return empty();
        }
        return singleton(getStringValue(context).replace(getStringValue(arguments.get(0)), getStringValue(arguments.get(1))));
    }
}
