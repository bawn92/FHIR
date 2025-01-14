/*
 * (C) Copyright IBM Corp. 2019
 * 
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.path.function.registry;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.ibm.watson.health.fhir.model.path.function.AllFalseFunction;
import com.ibm.watson.health.fhir.model.path.function.AllTrueFunction;
import com.ibm.watson.health.fhir.model.path.function.AnyFalseFunction;
import com.ibm.watson.health.fhir.model.path.function.AnyTrueFunction;
import com.ibm.watson.health.fhir.model.path.function.CheckModifiersFunction;
import com.ibm.watson.health.fhir.model.path.function.ChildrenFunction;
import com.ibm.watson.health.fhir.model.path.function.CombineFunction;
import com.ibm.watson.health.fhir.model.path.function.ConformsToFunction;
import com.ibm.watson.health.fhir.model.path.function.ContainsFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToBooleanFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToDateFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToDateTimeFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToDecimalFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToIntegerFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToQuantityFunction;
import com.ibm.watson.health.fhir.model.path.function.ConvertsToStringFunction;
import com.ibm.watson.health.fhir.model.path.function.CountFunction;
import com.ibm.watson.health.fhir.model.path.function.DescendantsFunction;
import com.ibm.watson.health.fhir.model.path.function.DistinctFunction;
import com.ibm.watson.health.fhir.model.path.function.EmptyFunction;
import com.ibm.watson.health.fhir.model.path.function.EndsWithFunction;
import com.ibm.watson.health.fhir.model.path.function.ExcludeFunction;
import com.ibm.watson.health.fhir.model.path.function.ExtensionFunction;
import com.ibm.watson.health.fhir.model.path.function.FHIRPathFunction;
import com.ibm.watson.health.fhir.model.path.function.FirstFunction;
import com.ibm.watson.health.fhir.model.path.function.GetValueFunction;
import com.ibm.watson.health.fhir.model.path.function.HasValueFunction;
import com.ibm.watson.health.fhir.model.path.function.HtmlChecksFunction;
import com.ibm.watson.health.fhir.model.path.function.IndexOfFunction;
import com.ibm.watson.health.fhir.model.path.function.IntersectFunction;
import com.ibm.watson.health.fhir.model.path.function.IsDistinctFunction;
import com.ibm.watson.health.fhir.model.path.function.ItemFunction;
import com.ibm.watson.health.fhir.model.path.function.LastFunction;
import com.ibm.watson.health.fhir.model.path.function.LengthFunction;
import com.ibm.watson.health.fhir.model.path.function.LowerFunction;
import com.ibm.watson.health.fhir.model.path.function.MatchesFunction;
import com.ibm.watson.health.fhir.model.path.function.MemberOfFunction;
import com.ibm.watson.health.fhir.model.path.function.NotFunction;
import com.ibm.watson.health.fhir.model.path.function.NowFunction;
import com.ibm.watson.health.fhir.model.path.function.ReplaceFunction;
import com.ibm.watson.health.fhir.model.path.function.ReplaceMatchesFunction;
import com.ibm.watson.health.fhir.model.path.function.ResolveFunction;
import com.ibm.watson.health.fhir.model.path.function.SingleFunction;
import com.ibm.watson.health.fhir.model.path.function.SkipFunction;
import com.ibm.watson.health.fhir.model.path.function.SliceFunction;
import com.ibm.watson.health.fhir.model.path.function.StartsWithFunction;
import com.ibm.watson.health.fhir.model.path.function.SubsetOfFunction;
import com.ibm.watson.health.fhir.model.path.function.SubstringFunction;
import com.ibm.watson.health.fhir.model.path.function.SubsumedByFunction;
import com.ibm.watson.health.fhir.model.path.function.SubsumesFunction;
import com.ibm.watson.health.fhir.model.path.function.SupersetOfFunction;
import com.ibm.watson.health.fhir.model.path.function.TailFunction;
import com.ibm.watson.health.fhir.model.path.function.TakeFunction;
import com.ibm.watson.health.fhir.model.path.function.ToBooleanFunction;
import com.ibm.watson.health.fhir.model.path.function.ToCharsFunction;
import com.ibm.watson.health.fhir.model.path.function.ToDateFunction;
import com.ibm.watson.health.fhir.model.path.function.ToDateTimeFunction;
import com.ibm.watson.health.fhir.model.path.function.ToDecimalFunction;
import com.ibm.watson.health.fhir.model.path.function.ToIntegerFunction;
import com.ibm.watson.health.fhir.model.path.function.ToQuantityFunction;
import com.ibm.watson.health.fhir.model.path.function.ToStringFunction;
import com.ibm.watson.health.fhir.model.path.function.TodayFunction;
import com.ibm.watson.health.fhir.model.path.function.TypeFunction;
import com.ibm.watson.health.fhir.model.path.function.UnionFunction;
import com.ibm.watson.health.fhir.model.path.function.UpperFunction;

public class FHIRPathFunctionRegistry {
    private static final FHIRPathFunctionRegistry INSTANCE = new FHIRPathFunctionRegistry();
    private Map<String, FHIRPathFunction> functionMap = new ConcurrentHashMap<>();
    
    private FHIRPathFunctionRegistry() {
        registerFunctions();
    }

    public static FHIRPathFunctionRegistry getInstance() {
        return INSTANCE;
    }
    
    public void register(FHIRPathFunction function) {
        functionMap.put(function.getName(), function);
    }
    
    public FHIRPathFunction getFunction(String functionName) {
        return functionMap.get(functionName);
    }
    
    public Set<String> getFunctionNames() {
        return Collections.unmodifiableSet(functionMap.keySet());
    }
    
    private void registerFunctions() {
        register(new AllFalseFunction());
        register(new AllTrueFunction());
        register(new AnyFalseFunction());
        register(new AnyTrueFunction());
        register(new CheckModifiersFunction());
        register(new ChildrenFunction());
        register(new CombineFunction());
        register(new ConformsToFunction());
        register(new ContainsFunction());
        register(new ConvertsToBooleanFunction());
        register(new ConvertsToDateFunction());
        register(new ConvertsToDateTimeFunction());
        register(new ConvertsToDecimalFunction());
        register(new ConvertsToIntegerFunction());
        register(new ConvertsToQuantityFunction());
        register(new ConvertsToStringFunction());
        register(new CountFunction());
        register(new DescendantsFunction());
        register(new DistinctFunction());
        register(new EmptyFunction());
        register(new EndsWithFunction());
        register(new ExcludeFunction());
        register(new ExtensionFunction());
        register(new FirstFunction());
        register(new GetValueFunction());
        register(new HasValueFunction());
        register(new HtmlChecksFunction());
        register(new IndexOfFunction());
        register(new IntersectFunction());
        register(new IsDistinctFunction());
        register(new ItemFunction());
        register(new LastFunction());
        register(new LengthFunction());
        register(new LowerFunction());
        register(new MatchesFunction());
        register(new MemberOfFunction());
        register(new NotFunction());
        register(new NowFunction());
        register(new ReplaceFunction());
        register(new ReplaceMatchesFunction());
        register(new ResolveFunction());
        register(new SingleFunction());
        register(new SkipFunction());
        register(new SliceFunction());
        register(new StartsWithFunction());
        register(new SubsetOfFunction());
        register(new SubstringFunction());
        register(new SubsumedByFunction());
        register(new SubsumesFunction());
        register(new SupersetOfFunction());
        register(new TailFunction());
        register(new TakeFunction());
        register(new ToBooleanFunction());
        register(new ToCharsFunction());
        register(new ToDateFunction());
        register(new ToDateTimeFunction());
        register(new ToDecimalFunction());
        register(new ToIntegerFunction());
        register(new ToQuantityFunction());
        register(new ToStringFunction());
        register(new TodayFunction());
        register(new TypeFunction());
        register(new UnionFunction());
        register(new UpperFunction());
    }
}