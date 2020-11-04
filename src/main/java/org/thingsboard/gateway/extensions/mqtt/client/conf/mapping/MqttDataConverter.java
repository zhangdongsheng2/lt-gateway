package org.thingsboard.gateway.extensions.mqtt.client.conf.mapping;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.thingsboard.gateway.service.data.DeviceData;

import java.util.List;

/**
 * Created by ashvayka on 23.01.17.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MqttJsonConverter.class, name = "json")
})
public interface MqttDataConverter {

    List<DeviceData> convert(String topic, MqttMessage msg) throws Exception;

}
