package org.thingsboard.gateway.extensions.mqtt.client.conf.credentials;

import lombok.Data;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.util.StringUtils;

/**
 * Created by ashvayka on 23.01.17.
 */
@Data
public class BasicCredentials implements MqttClientCredentials {

    private String username;
    private String password;

    @Override
    public void configure(MqttConnectOptions clientOptions) {
        clientOptions.setUserName(username);
        if (!StringUtils.isEmpty(password)) {
            clientOptions.setPassword(password.toCharArray());
        }
    }
}
