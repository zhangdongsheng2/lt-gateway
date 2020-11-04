package org.thingsboard.gateway.extensions.mqtt.client.conf.credentials;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * Created by ashvayka on 23.01.17.
 */
public class AnonymousCredentials implements MqttClientCredentials {

    @Override
    public void configure(MqttConnectOptions clientOptions) {

    }
}
