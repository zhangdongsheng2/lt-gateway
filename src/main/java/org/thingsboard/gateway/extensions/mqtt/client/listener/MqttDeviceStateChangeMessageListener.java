package org.thingsboard.gateway.extensions.mqtt.client.listener;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.util.StringUtils;
import org.thingsboard.gateway.extensions.mqtt.client.conf.mapping.DeviceStateChangeMapping;
import org.thingsboard.gateway.util.converter.AbstractJsonConverter;

import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ashvayka on 07.03.17.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class MqttDeviceStateChangeMessageListener extends AbstractJsonConverter implements IMqttMessageListener {

    private final DeviceStateChangeMapping mapping;
    private final BiConsumer<String, String> deviceNameConsumer;
    private Pattern deviceNameTopicPattern;

    @Override
    public void messageArrived(String topic, MqttMessage msg) throws Exception {
        try {
            String deviceName;
            String deviceType = null;
            if (!StringUtils.isEmpty(mapping.getDeviceNameTopicExpression())) {
                deviceName = eval(topic);
            } else {
                String data = new String(msg.getPayload(), StandardCharsets.UTF_8);
                DocumentContext document = JsonPath.parse(data);
                deviceName = eval(document, mapping.getDeviceNameJsonExpression());
            }

            if (!StringUtils.isEmpty(mapping.getDeviceTypeTopicExpression())) {
                deviceType = eval(topic);
            } else if (!StringUtils.isEmpty(mapping.getDeviceTypeJsonExpression())) {
                String data = new String(msg.getPayload(), StandardCharsets.UTF_8);
                DocumentContext document = JsonPath.parse(data);
                deviceType = eval(document, mapping.getDeviceTypeJsonExpression());
            }

            if (deviceName != null) {
                deviceNameConsumer.accept(deviceName, deviceType);
            }
        } catch (Exception e) {
            log.error("Failed to convert msg", e);
        }
    }

    private String eval(String topic) {
        if (deviceNameTopicPattern == null) {
            deviceNameTopicPattern = Pattern.compile(mapping.getDeviceNameTopicExpression());
        }
        Matcher matcher = deviceNameTopicPattern.matcher(topic);
        while (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
