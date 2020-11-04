package org.thingsboard.gateway.extensions.mqtt.client.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.thingsboard.gateway.service.data.RpcCommandResponse;

import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;

/**
 * Created by ashvayka on 07.03.17.
 */
@Data
@Slf4j
public class MqttRpcResponseMessageListener implements IMqttMessageListener {

    private final int requestId;
    private final String deviceName;
    private final BiConsumer<String, RpcCommandResponse> consumer;

    @Override
    public void messageArrived(String topic, MqttMessage msg) throws Exception {
        RpcCommandResponse response = new RpcCommandResponse();
        response.setRequestId(requestId);
        response.setDeviceName(deviceName);
        response.setData(new String(msg.getPayload(), StandardCharsets.UTF_8));
        consumer.accept(topic, response);
    }
}
