/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.database.utils.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ibm.watson.health.database.utils.api.IDatabaseAdapter;

/**
 * Represents the ROW type used to pass parameters to the add_resource stored procedures
 *         CREATE OR REPLACE TYPE <schema>.t_str_values AS ROW (parameter_name_id INTEGER, str_value VARCHAR(511 OCTETS), str_value_lcase   VARCHAR(511 OCTETS))

 * @author rarnold
 *
 */
public class RowType extends BaseObject {
    private final List<ColumnBase> columns = new ArrayList<>();
    
    public RowType(String schemaName, String typeName, int version, Collection<ColumnBase> cols) {
        super(schemaName, typeName, DatabaseObjectType.TYPE, version);
        this.columns.addAll(cols);
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.database.utils.model.IDatabaseObject#apply(com.ibm.watson.health.database.utils.api.IDatabaseAdapter)
     */
    @Override
    public void apply(IDatabaseAdapter target) {
        target.createRowType(getSchemaName(), getObjectName(), columns);
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.database.utils.model.IDatabaseObject#drop(com.ibm.watson.health.database.utils.api.IDatabaseAdapter)
     */
    @Override
    public void drop(IDatabaseAdapter target) {
        target.dropType(getSchemaName(), getObjectName());
    }
    
    /**
     * Builder pattern
     * @author rarnold
     *
     */
    public static class Builder extends ColumnDefBuilder {
        private String schemaName;
        
        private String typeName;
        
        private int version = 1;
        
        /**
         * Setter for the schema name
         * @param schemaName
         * @return
         */
        public Builder setSchemaName(String schemaName) {
            this.schemaName = schemaName;
            return this;
        }

        /**
         * Setter for the version of this particular change
         * @param v
         * @return
         */
        public Builder setVersion(int v) {
            this.version = v;
            return this;
        }

        /**
         * Setter for the name of the table being built
         * @param typeName
         * @return
         */
        public Builder setTypeName(String typeName) {
            this.typeName = typeName;
            return this;
        }
        
        /**
         * Build the immutable table object based on the current configuration
         * @return
         */
        public RowType build() {
            if (this.typeName == null) {
                throw new IllegalStateException("No type name provided");
            }
            
            // Our schema objects are immutable by design, so all initialization takes place
            // through the constructor
            return new RowType(this.schemaName, this.typeName, version, buildColumns());
        }
        
    }
}
