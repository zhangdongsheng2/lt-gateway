package org.thingsboard.gateway.extensions.opc.conf;

import lombok.Data;
import org.thingsboard.gateway.extensions.opc.conf.identity.IdentityProviderConfiguration;
import org.thingsboard.gateway.util.KeystoreConfiguration;
import org.thingsboard.gateway.extensions.opc.conf.mapping.DeviceMapping;

import java.util.List;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class OpcUaServerConfiguration {

    private String applicationName;
    private String applicationUri;
    private String host;
    private int port;
    private int scanPeriodInSeconds;
    private int timeoutInMillis;
    private String security;
    private IdentityProviderConfiguration identity;
    private KeystoreConfiguration keystore;
    private List<DeviceMapping> mapping;

}
