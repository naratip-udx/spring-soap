package com.github.naratipudx.consuming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ClientConnector extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(ClientConnector.class);

    public Object callWebService(String url, Object request) {
        log.info("Requesting information for " + url);
        return getWebServiceTemplate().marshalSendAndReceive(url, request);
    }

    public Object callWebService(String url, Object request, String callback) {
        log.info("Requesting information for " + url + " and callback " + callback);
        return getWebServiceTemplate().marshalSendAndReceive(url, request, new SoapActionCallback(callback));
    }
}
