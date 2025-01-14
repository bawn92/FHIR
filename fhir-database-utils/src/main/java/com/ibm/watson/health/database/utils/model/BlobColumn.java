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
public class BlobColumn extends ColumnBase {
    private final long size;
    
    private final int inlineSize;

    /**
     * @param name
     */
    public BlobColumn(String name, long size, int inlineSize, boolean nullable) {
        super(name, nullable);
        this.size = size;
        this.inlineSize = inlineSize;
    }

    public long getSize() {
        return size;
    }

    public int getInlineSize() {
        return inlineSize;
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.fhir.schema.model.ColumnBase#getTypeInfo()
     */
    @Override
    public String getTypeInfo(IDatabaseTypeAdapter adapter) {
        return adapter.blobClause(size, inlineSize);
    }

}
