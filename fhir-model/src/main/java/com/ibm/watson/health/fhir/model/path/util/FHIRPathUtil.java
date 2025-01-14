/*
 * (C) Copyright IBM Corp. 2019
 * 
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.model.path.util;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibm.watson.health.fhir.model.path.ClassInfo;
import com.ibm.watson.health.fhir.model.path.ClassInfoElement;
import com.ibm.watson.health.fhir.model.path.FHIRPathBooleanValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathIntegerValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathNode;
import com.ibm.watson.health.fhir.model.path.FHIRPathNumberValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathPrimitiveValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathQuantityNode;
import com.ibm.watson.health.fhir.model.path.FHIRPathStringValue;
import com.ibm.watson.health.fhir.model.path.FHIRPathType;
import com.ibm.watson.health.fhir.model.path.SimpleTypeInfo;
import com.ibm.watson.health.fhir.model.path.TupleTypeInfo;
import com.ibm.watson.health.fhir.model.path.TupleTypeInfoElement;
import com.ibm.watson.health.fhir.model.util.ModelSupport;
import com.ibm.watson.health.fhir.model.util.ModelSupport.ElementInfo;

public final class FHIRPathUtil {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "$index", 
        "$this", 
        "$total", 
        "and", 
        "as", 
        "contains", 
        "day", 
        "days", 
        "div", 
        "false", 
        "hour", 
        "hours", 
        "implies", 
        "in", 
        "is", 
        "millisecond", 
        "milliseconds", 
        "minute", 
        "minutes", 
        "mod", 
        "month", 
        "months", 
        "or", 
        "seconds", 
        "true", 
        "week", 
        "weeks", 
        "xor", 
        "year", 
        "years", 
        "second"
    ));
    
    private FHIRPathUtil() { }
    
    public static boolean isKeyword(String identifier) {
        return KEYWORDS.contains(identifier);
    }
    
    public static String delimit(String identifier) {
        return String.format("`%s`", identifier);
    }
    
    public static BigDecimal getDecimal(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asNumberValue().decimal();
    }
    
    public static Integer getInteger(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asNumberValue().asIntegerValue().integer();
    }
    
    public static String getString(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asStringValue().string();
    }
    
    public static Boolean getBoolean(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asBooleanValue()._boolean();
    }
    
    public static TemporalAccessor getDateTime(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asDateTimeValue().dateTime();
    }
    
    public static TemporalAccessor getTime(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asTimeValue().time();
    }
    
    public static boolean hasPrimitiveValue(Collection<FHIRPathNode> nodes) {
        if (isSingleton(nodes)) {
            FHIRPathNode node = getSingleton(nodes);
            return (node instanceof FHIRPathPrimitiveValue) || node.hasValue();
        }
        return false;
    }
    
    public static FHIRPathPrimitiveValue getPrimitiveValue(Collection<FHIRPathNode> nodes) {
        if (!hasPrimitiveValue(nodes)) {
            throw new IllegalArgumentException();
        }
        FHIRPathNode node = getSingleton(nodes);
        if (node instanceof FHIRPathPrimitiveValue) {
            return (FHIRPathPrimitiveValue) node;
        }
        return node.getValue();
    }
    
    public static FHIRPathStringValue getStringValue(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asStringValue();
    }
    
    public static FHIRPathIntegerValue getIntegerValue(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asNumberValue().asIntegerValue();
    }
    
    public static FHIRPathNumberValue getNumberValue(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asNumberValue();
    }
    
    public static FHIRPathBooleanValue getBooleanValue(Collection<FHIRPathNode> nodes) {
        return getPrimitiveValue(nodes).asBooleanValue();
    }
    
    public static boolean hasBooleanValue(Collection<FHIRPathNode> nodes) {
        return hasPrimitiveValue(nodes) && getPrimitiveValue(nodes).isBooleanValue();
    }
    
    public static boolean hasNumberValue(Collection<FHIRPathNode> nodes) {
        return hasPrimitiveValue(nodes) && getPrimitiveValue(nodes).isNumberValue();
    }
    
    public static boolean hasStringValue(Collection<FHIRPathNode> nodes) {
        return hasPrimitiveValue(nodes) && getPrimitiveValue(nodes).isStringValue();
    }
    
    public static boolean evaluatesToBoolean(Collection<FHIRPathNode> nodes) {
        return hasBooleanValue(nodes) || isSingleton(nodes);
    }
    
    public static boolean isTrue(Collection<FHIRPathNode> nodes) {
        if (hasBooleanValue(nodes)) {
            return getBooleanValue(nodes).isTrue();
        }
        return isSingleton(nodes);
    }
    
    public static boolean isFalse(Collection<FHIRPathNode> nodes) {
        return !isTrue(nodes);
    }

    public static boolean isSingleton(Collection<FHIRPathNode> nodes) {
        return nodes.size() == 1;
    }

    public static FHIRPathNode getSingleton(Collection<FHIRPathNode> nodes) {
        if (!isSingleton(nodes)) {
            throw new IllegalArgumentException();
        }
        if (nodes instanceof List) {
            List<?> list = (List<?>) nodes;
            return (FHIRPathNode) list.get(0);
        }
        return nodes.iterator().next();
    }
    
    public static Collection<FHIRPathNode> singleton(FHIRPathNode node) {
        return singletonList(node);
    }
    
    public static Collection<FHIRPathNode> empty() {
        return emptyList();
    }
    
    public static boolean hasValueAndUnit(FHIRPathQuantityNode quantityNode) {
        return quantityNode.getQuantityValue() != null && quantityNode.getQuantityUnit() != null;
    }
    
    public static TemporalAccessor getTemporalAccessor(Temporal temporal, Class<?> targetType) {
        if (temporal.getClass().equals(targetType)) {
            return temporal;
        }
        if (Year.class.equals(targetType)) {
            return Year.from(temporal);
        } else if (YearMonth.class.equals(targetType)) {
            return YearMonth.from(temporal);
        } else if (LocalDate.class.equals(targetType)) {
            return LocalDate.from(temporal);
        } else if (ZonedDateTime.class.equals(targetType)){
            return ZonedDateTime.from(temporal);
        }
        throw new IllegalArgumentException();
    }
    
    public static Temporal getTemporal(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof Year) {
            Year year = (Year) temporalAccessor;
            return year.atMonth(1).atDay(1);
        } else if (temporalAccessor instanceof YearMonth) {
            YearMonth yearMonth = (YearMonth) temporalAccessor;
            return yearMonth.atDay(1);
        } else if (temporalAccessor instanceof LocalDate) {
            return (LocalDate) temporalAccessor;
        } else if (temporalAccessor instanceof ZonedDateTime) {
            return (ZonedDateTime) temporalAccessor;
        } else if (temporalAccessor instanceof LocalTime) {
            return (LocalTime) temporalAccessor;
        } else if (temporalAccessor instanceof OffsetTime) {
            return (OffsetTime) temporalAccessor;
        }
        throw new IllegalArgumentException();
    }

    public static TemporalAmount getTemporalAmount(FHIRPathQuantityNode quantityNode) {
        if (!hasValueAndUnit(quantityNode)) {
            throw new IllegalArgumentException();
        }
        int value = quantityNode.getQuantityValue().intValue();
        String unit = quantityNode.getQuantityUnit();        
        switch (unit) {
        case "year":
        case "years":
            return Period.ofYears(value);            
        case "month":
        case "months":
            return Period.ofMonths(value);            
        case "week":
        case "weeks":
            return Period.ofWeeks(value);            
        case "day":
        case "days":
            return Period.ofDays(value);            
        case "hour":
        case "hours":
            return Duration.ofHours(value);            
        case "minute":
        case "minutes":
            return Duration.ofMinutes(value);            
        case "second":
        case "seconds":
            return Duration.ofSeconds(value);            
        case "millisecond":
        case "milliseconds":
            return Duration.ofMillis(value);
        default:
            throw new IllegalArgumentException();
        }
    }

    public static SimpleTypeInfo buildSimpleTypeInfo(FHIRPathType type) {
        return new SimpleTypeInfo(type.namespace(), type.getName(), "System.Any");
    }

    public static TupleTypeInfo buildTupleTypeInfo(Class<?> modelClass) {
        List<TupleTypeInfoElement> element = new ArrayList<>();
        for (ElementInfo elementInfo : ModelSupport.getElementInfo(modelClass)) {
            if (elementInfo.isDeclaredBy(modelClass)) {
                element.add(buildTupleTypeInfoElement(elementInfo));
            }
        }
        return new TupleTypeInfo(element);
    }

    public static TupleTypeInfoElement buildTupleTypeInfoElement(ElementInfo elementInfo) {
        FHIRPathType type = FHIRPathType.from(elementInfo.getType());
        if (elementInfo.isRepeating()) {
            return new TupleTypeInfoElement(elementInfo.getName(), "List<" + type.namespace() + "." + type.getName() + ">", false);
        }
        return new TupleTypeInfoElement(elementInfo.getName(), type.namespace() + "." + type.getName());
    }

    public static ClassInfo buildClassInfo(FHIRPathType type) {
        List<ClassInfoElement> element = new ArrayList<>();
        Class<?> modelClass = type.modelClass();
        for (ElementInfo elementInfo : ModelSupport.getElementInfo(modelClass)) {
            if (elementInfo.isDeclaredBy(modelClass)) {
                element.add(buildClassInfoElement(elementInfo));
            }
        }
        return new ClassInfo(type.namespace(), type.getName(), type.baseType().namespace() + "." + type.baseType().getName(), element);
    }

    public static ClassInfoElement buildClassInfoElement(ElementInfo elementInfo) {
        FHIRPathType type = FHIRPathType.from(elementInfo.getType());
        String typeName;
        if (FHIRPathType.isSystemType(type) || 
                FHIRPathType.getSystemTypes().stream()
                    .map(t -> t.getName())
                    .anyMatch(name -> name.equalsIgnoreCase(type.getName()))) {
            typeName = type.namespace() + "." + type.getName();
        } else {
            typeName = type.getName();
        }
        if (elementInfo.isRepeating()) {
            return new ClassInfoElement(elementInfo.getName(), "List<" + typeName + ">", false);
        }
        return new ClassInfoElement(elementInfo.getName(), typeName);
    }
}