package org.thingsboard.gateway.extensions.mqtt.client.conf;

import lombok.Data;

import java.util.List;

/**
 * Created by ashvayka on 23.01.17.
 */
@Data
public class MqttClientConfiguration {

    List<MqttBrokerConfiguration> brokers;

}
