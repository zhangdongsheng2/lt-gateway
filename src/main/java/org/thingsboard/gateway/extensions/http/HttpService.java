package org.thingsboard.gateway.extensions.http;

import org.thingsboard.gateway.extensions.ExtensionService;

public interface HttpService extends ExtensionService {

    void processRequest(String converterId, String token, String body) throws Exception;
}
