package org.thingsboard.gateway.extensions.mqtt.client.conf;

import lombok.Data;
import org.thingsboard.gateway.extensions.mqtt.client.conf.credentials.MqttClientCredentials;
import org.thingsboard.gateway.extensions.mqtt.client.conf.mapping.*;

import java.util.List;

/**
 * Created by ashvayka on 23.01.17.
 */
@Data
public class MqttBrokerConfiguration {
    private String host;
    private int port;
    private boolean ssl;
    private String clientId;
    private String truststore;
    private String truststorePassword;
    private long retryInterval;
    private MqttClientCredentials credentials;
    private List<MqttTopicMapping> mapping;
    private List<DeviceConnectMapping> connectRequests;
    private List<DeviceDisconnectMapping> disconnectRequests;
    private List<AttributeRequestsMapping> attributeRequests;
    private List<AttributeUpdatesMapping> attributeUpdates;
    private List<ServerSideRpcMapping> serverSideRpc;
}
