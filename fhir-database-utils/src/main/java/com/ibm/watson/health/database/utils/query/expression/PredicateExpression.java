/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.database.utils.query.expression;

/**
 * @author rarnold
 *
 */
public class PredicateExpression extends Predicate {
    
    // The string expression we represent
    private String expr;
    
    public PredicateExpression(String boolExpr) {
        this.expr = boolExpr;
    }
}
