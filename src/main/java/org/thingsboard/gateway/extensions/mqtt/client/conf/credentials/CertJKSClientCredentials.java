package org.thingsboard.gateway.extensions.mqtt.client.conf.credentials;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@Data
public class CertJKSClientCredentials implements MqttClientCredentials {
    @Override
    public void configure(MqttConnectOptions clientOptions) {
        throw new RuntimeException("JKS client credentials are not supported yet!");
    }
}
