package org.thingsboard.gateway.service;

/**
 * Created by ashvayka on 29.09.17.
 */
public interface TenantManagerService {

    void processRequest(String converterId, String token, String body) throws Exception;

}
