/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.database.utils.model;

import com.ibm.watson.health.database.utils.api.IDatabaseTypeAdapter;

/**
 * @author rarnold
 *
 */
public class CharColumn extends ColumnBase {
    private final int size;

    /**
     * @param name
     */
    public CharColumn(String name, int size, boolean nullable) {
        super(name, nullable);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.fhir.schema.model.ColumnBase#getTypeInfo()
     */
    @Override
    public String getTypeInfo(IDatabaseTypeAdapter translator) {
        return "CHAR(" + size + ")";
    }

}
