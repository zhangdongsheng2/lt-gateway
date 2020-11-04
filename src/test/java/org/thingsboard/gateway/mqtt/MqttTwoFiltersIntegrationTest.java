package org.thingsboard.gateway.mqtt;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.thingsboard.gateway.AbstractGatewayMqttIntegrationTest;
import org.thingsboard.gateway.mqtt.simulators.MqttTestClient;
import org.thingsboard.gateway.util.IoUtils;
import org.thingsboard.gateway.util.JsonUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ActiveProfiles("mqtt-two-filters")
public class MqttTwoFiltersIntegrationTest extends AbstractGatewayMqttIntegrationTest {

    @Autowired
    private MqttTestClient deviceSimulator;

    @Autowired
    private TestMqttHandler testMqttHandler;

    @Test
    public void testMqttTwoFiltersOnSameTopic() throws Exception {
        deviceSimulator.publish("sensor/1110", IoUtils.getResourceAsString("mqtt/mqtt-1110-publish.json").getBytes(), 0);
        deviceSimulator.publish("sensor/1111", IoUtils.getResourceAsString("mqtt/mqtt-1111-publish.json").getBytes(), 0);

        Thread.sleep(10000);
        List<String> receivedConnectMessages = testMqttHandler.getValues("v1/gateway/connect");
        assertNotNull("recievedConnectMessage was expected to be non-null", receivedConnectMessages);

        assertEquals("Exactly 2 connect messages were expected", 2, receivedConnectMessages.size());
        assertEquals(IoUtils.getResourceAsString("mqtt/connect-1110.json"), receivedConnectMessages.get(0));
        assertEquals(IoUtils.getResourceAsString("mqtt/connect-1111.json"), receivedConnectMessages.get(1));

        List<String> recievedTelemetryMessages = testMqttHandler.getValues("v1/gateway/telemetry");
        assertNotNull("recievedTelemetryMessage was expected to be non-null", recievedTelemetryMessages);
        assertEquals("Exactly 2 telemetry message were expected", 2, recievedTelemetryMessages.size());

        String expectedTelemetry1110 = IoUtils.getResourceAsString("mqtt/mqtt-1110-result.json");
        String actualTelemetry1110 = recievedTelemetryMessages.get(0);

        JsonUtils.assertWithoutTimestamp("Device 1110", expectedTelemetry1110, actualTelemetry1110);

        String expectedTelemetry1111 = IoUtils.getResourceAsString("mqtt/mqtt-1111-result.json");
        String actualTelemetry1111 = recievedTelemetryMessages.get(1);

        JsonUtils.assertWithoutTimestamp("Device 1111", expectedTelemetry1111, actualTelemetry1111);

    }

}
