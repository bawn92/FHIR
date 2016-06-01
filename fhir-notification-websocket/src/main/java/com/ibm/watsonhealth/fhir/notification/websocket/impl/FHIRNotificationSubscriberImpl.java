/**
 * (C) Copyright IBM Corp. 2016,2017,2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.notification.websocket.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;
import com.ibm.watsonhealth.fhir.notification.FHIRNotificationSubscriber;
import com.ibm.watsonhealth.fhir.notification.exception.FHIRNotificationException;
import com.ibm.watsonhealth.fhir.notification.util.FHIRNotificationEvent;
import com.ibm.watsonhealth.fhir.notification.util.FHIRNotificationUtil;

public class FHIRNotificationSubscriberImpl implements FHIRNotificationSubscriber {
    private static final Logger log = java.util.logging.Logger.getLogger(FHIRNotificationSubscriberImpl.class.getName());
    private Session session = null;

    public FHIRNotificationSubscriberImpl(Session session) {
        this.session = session;
    }

    public void notify(FHIRNotificationEvent event) throws FHIRNotificationException {
        log.entering(this.getClass().getName(), "notify");
        try {
            String message = FHIRNotificationUtil.toJsonString(event);
            log.fine("Publishing websocket notification on session [id=" + session.getId() + "], message:" + message);
            session.getAsyncRemote().sendText(message);
        } catch (Exception e) {
            String msg = "Error sending message to websocket: " + session.getId();
            log.log(Level.SEVERE, msg, e);
            throw new FHIRNotificationException(msg, e);
        } finally {
            log.exiting(this.getClass().getName(), "notify");
        }
    }
}