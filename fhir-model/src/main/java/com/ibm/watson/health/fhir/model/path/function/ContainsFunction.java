/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.path.function;

import static com.ibm.watson.health.fhir.model.path.evaluator.FHIRPathEvaluator.SINGLETON_FALSE;
import static com.ibm.watson.health.fhir.model.path.evaluator.FHIRPathEvaluator.SINGLETON_TRUE;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.empty;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.getStringValue;
import static com.ibm.watson.health.fhir.model.path.util.FHIRPathUtil.hasStringValue;

import java.util.Collection;
import java.util.List;

import com.ibm.watson.health.fhir.model.path.FHIRPathNode;
import com.ibm.watson.health.fhir.model.path.evaluator.FHIRPathEvaluator.EvaluationContext;

public class ContainsFunction extends FHIRPathAbstractFunction {
    @Override
    public String getName() {
        return "contains";
    }

    @Override
    public int getMinArity() {
        return 1;
    }

    @Override
    public int getMaxArity() {
        return 1;
    }
    
    @Override
    public Collection<FHIRPathNode> apply(EvaluationContext evaluationContext, Collection<FHIRPathNode> context, List<Collection<FHIRPathNode>> arguments) {
        if (!hasStringValue(context)) {
            return empty();
        }
        return getStringValue(context).contains(getStringValue(arguments.get(0))) ? SINGLETON_TRUE : SINGLETON_FALSE;
    }
}
