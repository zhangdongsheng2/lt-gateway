package org.thingsboard.gateway.mqtt.simulators;

import io.netty.buffer.Unpooled;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.thingsboard.mqtt.MqttClient;
import org.thingsboard.mqtt.MqttClientConfig;
import org.thingsboard.mqtt.MqttHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * Created by Valerii Sosliuk on 5/9/2018.
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
@Data
public class MqttTestClient {

    private MqttClient mqttClient;
    private MqttHandler defaultHandler;

    @Value("${test.host:localhost}")
    private String host;
    @Value("${mqtt.broker.external.port:7883}")
    private int port;
    @Value("${mqtt.timeout:10000}")
    private long timeout;

    public MqttTestClient(MqttHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    @PostConstruct
    public void init() {
        MqttClientConfig mqttClientConfig = new MqttClientConfig();
        mqttClient = MqttClient.create(mqttClientConfig, defaultHandler);
        mqttClient.setEventLoop(new NioEventLoopGroup());
        try {
            mqttClient.connect(host, port).get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void subscribe(String topic, MqttHandler handler) {
        try {
            mqttClient.on(topic, handler).get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void publish(String topic, byte[] payload, int qos) {
        mqttClient.publish(topic, Unpooled.wrappedBuffer(payload), MqttQoS.valueOf(qos));
    }

    @PreDestroy
    public void tearDown() {
        mqttClient.disconnect();
    }
}
