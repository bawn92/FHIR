/**
 * (C) Copyright IBM Corp. 2016,2017,2018,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FHIRConfiguration {
    private static final Logger log = Logger.getLogger(FHIRConfiguration.class.getName());

    /**
     * This class serves up a singleton instance of ConfigurationService containing the FHIR Server's configuration.
     */
    public static final String CONFIG_LOCATION = "config";
    public static final String CONFIG_FILE_BASENAME = "fhir-server-config.json";
    public static final String DEFAULT_TENANT_ID = "default";
    public static final String DEFAULT_DATASTORE_ID = "default";

    // Configuration properties used by various FHIR Server components.
    public static final String PROPERTY_VIRTUAL_RESOURCES_ENABLED = "fhirServer/virtualResources/enabled";
    public static final String PROPERTY_ALLOWABLE_VIRTUAL_RESOURCE_TYPES = "fhirServer/virtualResources/allowableResourceTypes";
    public static final String PROPERTY_USER_DEFINED_SCHEMATRON_ENABLED = "fhirServer/core/userDefinedSchematronEnabled";
    public static final String PROPERTY_TRUSTSTORE_LOCATION = "fhirServer/core/truststoreLocation";
    public static final String PROPERTY_TRUSTSTORE_PASSWORD = "fhirServer/core/truststorePassword";
    public static final String PROPERTY_OAUTH_REGURL = "fhirServer/oauth/regUrl";
    public static final String PROPERTY_OAUTH_AUTHURL = "fhirServer/oauth/authUrl";
    public static final String PROPERTY_OAUTH_TOKENURL = "fhirServer/oauth/tokenUrl";
    public static final String PROPERTY_AUDIT_SERVICE_CLASS_NAME = "fhirServer/audit/serviceClassName";
    public static final String PROPERTY_AUDIT_SERVICE_PROPERTIES = "fhirServer/audit/serviceProperties";
    public static final String PROPERTY_AUDIT_PATIENT_ID_EXTURL = "fhirServer/audit/patientIdExtensionUrl";
    public static final String PROPERTY_AUDIT_RESOURCE_NAME_EXTURL = "fhirServer/audit/resourceNameExtensionUrl";
    public static final String PROPERTY_ENCRYPTION = "fhirServer/encryption";
    public static final String PROPERTY_UPDATE_CREATE_ENABLED = "fhirServer/persistence/common/updateCreateEnabled";
    public static final String PROPERTY_USE_UUIDS = "fhirServer/persistence/jpa/useUUIDs";
    public static final String PROPERTY_SCHEMA_NAME = "fhirServer/persistence/jpa/schemaName";
    public static final String PROPERTY_NOTIFICATION_RESOURCE_TYPES = "fhirServer/notifications/common/includeResourceTypes";
    public static final String PROPERTY_WEBSOCKET_ENABLED = "fhirServer/notifications/websocket/enabled";
    public static final String PROPERTY_KAFKA_ENABLED = "fhirServer/notifications/kafka/enabled";
    public static final String PROPERTY_KAFKA_TOPICNAME = "fhirServer/notifications/kafka/topicName";
    public static final String PROPERTY_KAFKA_CONNECTIONPROPS = "fhirServer/notifications/kafka/connectionProperties";
    public static final String PROPERTY_PERSISTENCE_FACTORY = "fhirServer/persistence/factoryClassname";
    public static final String PROPERTY_CLOUDANT_URL = "fhirServer/persistence/cloudant/url";
    public static final String PROPERTY_CLOUDANT_USERNAME = "fhirServer/persistence/cloudant/username";
    public static final String PROPERTY_CLOUDANT_PWD = "fhirServer/persistence/cloudant/pwd";
    public static final String PROPERTY_CLOUDANT_DBNAME = "fhirServer/persistence/cloudant/dbName";
    public static final String PROPERTY_WHCLSF_ROUTER = "fhirServer/persistence/whclsfRouter";
    public static final String PROPERTY_JDBC_BOOTSTRAP_DB = "fhirServer/persistence/jdbc/bootstrapDb";
    public static final String PROPERTY_JDBC_SCHEMA_TYPE = "fhirServer/persistence/jdbc/schemaType";
    public static final String PROPERTY_JDBC_DATASOURCE_JNDINAME = "fhirServer/persistence/jdbc/dataSourceJndiName";
    public static final String PROPERTY_JDBC_ENABLE_CODE_SYSTEMS_CACHE = "fhirServer/persistence/jdbc/enableCodeSystemsCache";
    public static final String PROPERTY_JDBC_ENABLE_PARAMETER_NAMES_CACHE = "fhirServer/persistence/jdbc/enableParameterNamesCache";
    public static final String PROPERTY_JDBC_ENABLE_RESOURCE_TYPES_CACHE = "fhirServer/persistence/jdbc/enableResourceTypesCache";
    public static final String PROPERTY_TENANT_ID_HEADER_NAME = "fhirServer/core/tenantIdHeaderName";
    public static final String PROPERTY_DATASTORE_ID_HEADER_NAME = "fhirServer/core/datastoreIdHeaderName";
    public static final String PROPERTY_DEFAULT_TENANT_ID = "fhirServer/core/defaultTenantId";
    public static final String PROPERTY_DEFAULT_PRETTY_PRINT = "fhirServer/core/defaultPrettyPrint";
    public static final String PROPERTY_JSON_PARSER_LENIENT = "fhirServer/core/jsonParserLenient";
    public static final String PROPERTY_JSON_PARSER_VALIDATING = "fhirServer/core/jsonParserValidating";
    public static final String PROPERTY_DATASOURCES = "fhirServer/persistence/datasources";
    public static final String PROPERTY_SEARCH_PARAMETER_FILTER = "fhirServer/searchParameterFilter";
    public static final String PROPERTY_AUTHFILTER_ENABLED = "fhirServer/authFilter/enabled";
    public static final String PROPERTY_AUTHORIZED_CLIENT_CERT_CLIENT_CN = "fhirServer/authFilter/authorizedClientCertClientCN";
    public static final String PROPERTY_AUTHORIZED_CLIENT_CERT_ISSUER_OU = "fhirServer/authFilter/authorizedClientCertIssuerOU";

    public static final String PROPERTY_REPL_INTERCEPTOR_ENABLED = "fhirServer/whclsfRouter/replicationInterceptorEnabled";
    public static final String PROPERTY_RESOURCE_TYPES_REQUIRING_SUBJECT_ID = "fhirServer/whclsfRouter/resourceNamesRequiringPatientId";
    public static final String PROPERTY_STUDY_SCOPED_RESOURCES = "fhirServer/whclsfRouter/resourceNamesRequiringConsentEnforcement";
       
    public static final String DEFAULT_TENANT_ID_HEADER_NAME = "X-FHIR-TENANT-ID";
    public static final String DEFAULT_DATASTORE_ID_HEADER_NAME = "X-FHIR-DSID";
    public static final String DEFAULT_PRETTY_RESPONSE_HEADER_NAME = "X-FHIR-FORMATTED";

    public static final String FHIR_SERVER_DEFAULT_CONFIG = "config/default/fhir-server-config.json";

    
    // Optional "home directory" for config files.  Defaults to current directory.
    private static String configHome = "";

    private static FHIRConfiguration _instance = new FHIRConfiguration();

    public static FHIRConfiguration getInstance() {
        return _instance;
    }

    /**
     * This is our in-memory cache of PropertyGroup's keyed by tenant-id.
     */
    private TenantSpecificPropertyGroupCache configCache = new TenantSpecificPropertyGroupCache();
    
    /**
     * This method is used to configure an explicit top-level directory where FHIR Server configuration
     * information is expected to reside.
     * For example, by calling this method with value "/mydir", then we'd expect
     * to find config files whose names are of the form:  "/mydir/config/<tenant-id>/fhir-server-config.json".
     * The default location for config files is the current working directory (i.e. "" - the empty string).
     * @param s the new config home directory name 
     */
    public static void setConfigHome(String s) {
        if (s == null) {
            s = "";
        }
        if (!s.isEmpty() && !s.endsWith("/")) {
            s += "/";
        }
        
        configHome = s;
    }
    
    /**
     * Returns the "home" directory for FHIR Server configuration information (this directory will contain
     * the "config" directory, etc.).   
     * The default value of this property is "" which is interpretted to mean the current working directory
     * (which for a running FHIR Server will be $WLP_HOME/wlp/usr/servers/fhir-server).
     */
    public static String getConfigHome() {
        return configHome;
    }

    /**
     * Retrieves the FHIR Server configuration and returns it as a PropertyGroup.
     * 
     * @throws FileNotFoundException
     */
    public PropertyGroup loadConfiguration() throws Exception {
        return loadConfigurationForTenant(DEFAULT_TENANT_ID);
    }

    /**
     * Loads the configuration for the specified tenant id.
     * 
     * @param tenantId
     *            a shortname representing the tenant whose configuration will be loaded
     * @return the top-level property group representing this tenant's configuration
     * @throws Exception
     */
    public PropertyGroup loadConfigurationForTenant(String tenantId) throws Exception {
        // log.entering(this.getClass().getName(), "loadConfigurationForTenant", tenantId);
        try {
            return configCache.getCachedObjectForTenant(tenantId);
        } finally {
            // log.exiting(this.getClass().getName(), "loadConfigurationForTenant");
        }
    }

    /**
     * Clears the entire cache of configuration objects. This can be used perhaps during testing when you need to clear
     * and re-load the configuration.
     */
    public void clearConfiguration() {
        synchronized (configCache) {
            configCache.clearCache();
        }
    }
    
    /**
     * This method returns the list of tenant id's for which a configuration exists.
     * @return
     */
    public List<String> getConfiguredTenants() {
        log.entering(this.getClass().getName(), "getConfiguredTenants");

        try {
            List<String> result = new ArrayList<>();

            // 'configDir' represents the directory that contains the tenant ids
            // Example: "/opt/ibm/fhir-server/wlp/usr/servers/fhir-server/config".
            File configDir = new File(getConfigHome() + CONFIG_LOCATION);
            log.fine("Listing tenant id's rooted at directory: " + configDir.getName());

            // List the directories within 'configDir' that contain a fhir-server-config.json file.
            for (File f : configDir.listFiles()) {
                // For a directory, let's verify that a config exists within it.
                // If yes, then add the name of the directory to the result list, as that
                // represents a tenant id.
                if (f.isDirectory()) {
                    File configFile = new File(f, CONFIG_FILE_BASENAME);
                    if (configFile.exists() && configFile.isFile()) {
                        result.add(f.getName());
                    }
                }
            }
            
            log.fine("Returning list of tenant ids: " + result.toString());

            return result;
        } finally {
            log.exiting(this.getClass().getName(), "getConfiguredTenants");
        }
    }
}
