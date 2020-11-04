package org.thingsboard.gateway.extensions.mqtt.client.conf.mapping;

import lombok.Data;

/**
 * Created by ashvayka on 23.01.17.
 */
@Data
public class MqttTopicMapping {

    private String topicFilter;
    private MqttDataConverter converter;

}
