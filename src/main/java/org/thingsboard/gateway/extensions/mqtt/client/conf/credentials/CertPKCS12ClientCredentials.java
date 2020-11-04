package org.thingsboard.gateway.extensions.mqtt.client.conf.credentials;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

@Data
public class CertPKCS12ClientCredentials implements MqttClientCredentials {
    @Override
    public void configure(MqttConnectOptions clientOptions) {
        throw new RuntimeException("PKCS12 client credentials are not supported yet!");
    }
}
