#!/usr/bin/env sh
###############################################################################
# (C) Copyright IBM Corp. 2016, 2018, 2019
#
# SPDX-License-Identifier: Apache-2.0
###############################################################################

echo "
Executing $0 to deploy the fhir-server web application...
"

# Determine the location of this script.
# basedir=`dirname "$0"`
cd $(dirname $0); basedir="$(pwd)/"

# Default liberty install location
UNZIP_LOC=`unset CDPATH; cd "$basedir/.." && pwd`
unset WLP_ROOT

# Allow user to override default install location
if [ $# -gt 0 ]; then
    WLP_ROOT=$1
else
    echo "Syntax: $0 <WLP_ROOT>"
    exit 1
fi

echo "Deploying fhir-server in existing Websphere Liberty runtime location: ${WLP_ROOT}"

# Validate the WLP root directory.
if [ ! -d "${WLP_ROOT}" ]; then
    echo -n "
The specified Websphere Liberty installation directory does not exist: ${WLP_ROOT}
Please specify the location of an existing WLP runtime. "
    exit 2
fi

# Make sure we actually have Liberty installed there.
if [ ! -f "${WLP_ROOT}/bin/server" ]; then
    echo -n "
Invalid installation directory specified for WebSphere Liberty runtime: ${WLP_ROOT}
Please specify the location of an existing WLP runtime."
    exit 3
fi

# If our server already exists, then remove it.
if [ -d "${WLP_ROOT}/usr/servers/fhir-server" ]; then
    echo -n "Removing existing fhir-server definition..."
    rm -fr ${WLP_ROOT}/usr/servers/fhir-server
fi

# Create our server
echo -n "
Creating Websphere Liberty server definition for fhir-server... "
${WLP_ROOT}/bin/server create fhir-server
rc=$?
if [ $rc != 0 ]; then
    echo "Error creating server definition: $rc"
    exit $rc
else
    echo "done!"
fi

# Copy our server assets
echo -n "
Deploying fhir-server assets to server runtime environment... "
cp -pr ${basedir}/fhir/server/* ${WLP_ROOT}
rc=$?
if [ $rc != 0 ]; then
    echo "Error deploying fhir-server assets to server runtime environment: $rc"
    exit $rc
else
    echo "done!"
fi


echo "

The FHIR Server has been successfully deployed to the
existing Websphere Liberty runtime located at: ${WLP_ROOT}

1) You can start and stop the server with these commands:
   ${WLP_ROOT}/bin/server start fhir-server
   ${WLP_ROOT}/bin/server stop fhir-server
2) The FHIR Server User's Guide can be found at https://github.com/ibm/FHIR.
"

exit 0
