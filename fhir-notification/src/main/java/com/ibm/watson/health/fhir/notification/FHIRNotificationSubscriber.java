/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watson.health.fhir.notification;

import com.ibm.watson.health.fhir.notification.exception.FHIRNotificationException;

public interface FHIRNotificationSubscriber {
    /**
     * Notify subscriber of an event
     * @param event
     * @throws FHIRNotificationException
     */
    void notify(FHIRNotificationEvent event) throws FHIRNotificationException;
}
