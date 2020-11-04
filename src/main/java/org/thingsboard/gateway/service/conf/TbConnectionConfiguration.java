package org.thingsboard.gateway.service.conf;

import lombok.Data;
import org.thingsboard.gateway.service.gateway.MqttGatewaySecurityConfiguration;

/**
 * Created by ashvayka on 18.01.17.
 */
@Data
public class TbConnectionConfiguration {

    private String host;
    private int port;
    private long retryInterval;
    private long connectionTimeout;
    private int maxInFlight;
    private int maxQueueSize;
    private int incomingQueueWarningThreshold;
    private MqttGatewaySecurityConfiguration security;

}
