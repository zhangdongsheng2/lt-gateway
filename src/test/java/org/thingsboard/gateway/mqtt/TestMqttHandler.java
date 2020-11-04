package org.thingsboard.gateway.mqtt;

import io.netty.buffer.ByteBuf;
import org.thingsboard.mqtt.MqttHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Valerii Sosliuk on 5/9/2018.
 */
public class TestMqttHandler implements MqttHandler {

    private Map<String, List<String>> payloads;

    public TestMqttHandler() {
        payloads = new HashMap<>();
    }

    public void cleanup() {
        payloads.clear();
    }

    @Override
    public void onMessage(String topic, ByteBuf payload) {
        List<String> payloads = this.payloads.get(topic);
        if (payloads == null) {
            payloads = new ArrayList<>();
            this.payloads.put(topic, payloads);
        }
        payloads.add(payload.toString(StandardCharsets.UTF_8));
    }

    public List<String> getValues(String topic) {
        return payloads.get(topic);
    }
}
