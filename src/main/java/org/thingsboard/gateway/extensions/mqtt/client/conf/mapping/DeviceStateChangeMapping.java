package org.thingsboard.gateway.extensions.mqtt.client.conf.mapping;

import lombok.Data;
import lombok.ToString;

/**
 * Created by ashvayka on 07.03.17.
 */
@Data
@ToString
public class DeviceStateChangeMapping {
    private String topicFilter;
    private String deviceNameJsonExpression;
    private String deviceNameTopicExpression;

    private String deviceTypeJsonExpression;
    private String deviceTypeTopicExpression;

}
