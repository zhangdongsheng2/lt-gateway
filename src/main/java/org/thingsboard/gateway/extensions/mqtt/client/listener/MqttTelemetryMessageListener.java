package org.thingsboard.gateway.extensions.mqtt.client.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.thingsboard.gateway.extensions.mqtt.client.conf.mapping.MqttDataConverter;
import org.thingsboard.gateway.service.data.DeviceData;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by ashvayka on 24.01.17.
 */
@Data
@Slf4j
public class MqttTelemetryMessageListener implements IMqttMessageListener {

    private final Consumer<List<DeviceData>> consumer;
    private final MqttDataConverter converter;

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        try {
            consumer.accept(converter.convert(topic, message));
        } catch (Exception e) {
            log.info("[{}] Failed to decode message: {}", topic, Arrays.toString(message.getPayload()), e);
        }
    }
}
