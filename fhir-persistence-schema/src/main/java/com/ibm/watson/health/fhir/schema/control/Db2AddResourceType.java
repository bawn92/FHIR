/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.schema.control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.ibm.watson.health.database.utils.api.IDatabaseStatement;
import com.ibm.watson.health.database.utils.api.IDatabaseTranslator;
import com.ibm.watson.health.database.utils.common.DataDefinitionUtil;

/**
 * DAO command to add a resource type. If it already exists, we get back the existing id
 * @author rarnold
 *
 */
public class Db2AddResourceType implements IDatabaseStatement {
    private final String schemaName;
    private final String resourceType;

    // The database assigned id for this resource type (for the current tenant)
    private int resourceTypeId = -1;

    /**
     * Public constructor
     * @param schemaName
     * @param resourceType
     */
    public Db2AddResourceType(String schemaName, String resourceType) {
        this.schemaName = schemaName;
        this.resourceType = resourceType;
    }

    /**
     * Getter for the resource type id allocated by the database
     * @return
     */
    public int getResourceTypeId() {
        return this.resourceTypeId;
    }

    /* (non-Javadoc)
     * @see com.ibm.watson.health.database.utils.api.IDatabaseStatement#run(com.ibm.watson.health.database.utils.api.IDatabaseTranslator, java.sql.Connection)
     */
    @Override
    public void run(IDatabaseTranslator translator, Connection c) {
        final String proc = DataDefinitionUtil.getQualifiedName(schemaName, "ADD_RESOURCE_TYPE");
        final String sql = "CALL " + proc + "(?, ?)";

        try (CallableStatement cs = c.prepareCall(sql)) {
            cs.setString(1, resourceType);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            this.resourceTypeId = cs.getInt(2);
        }
        catch (SQLException x) {
            throw translator.translate(x);
        }

    }

}
