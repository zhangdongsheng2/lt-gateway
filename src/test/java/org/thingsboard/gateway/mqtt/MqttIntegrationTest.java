package org.thingsboard.gateway.mqtt;

import org.json.JSONException;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.thingsboard.gateway.AbstractGatewayMqttIntegrationTest;
import org.thingsboard.gateway.mqtt.simulators.MqttTestClient;
import org.thingsboard.gateway.util.IoUtils;
import org.thingsboard.gateway.util.JsonUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MqttIntegrationTest extends AbstractGatewayMqttIntegrationTest {

    @Autowired
    private MqttTestClient deviceSimulator;

    @Autowired
    private TestMqttHandler testMqttHandler;

    @Test
    public void testSendAndReceiveSimpleJson() throws IOException, InterruptedException, JSONException {

        try {
            deviceSimulator.publish("sensor/SN-001/temperature",
                    IoUtils.getResourceAsString("mqtt/single-value-publish.json").getBytes(), 0);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Thread.sleep(10000);
        List<String> receivedConnectMessage = testMqttHandler.getValues("v1/gateway/connect");
        assertNotNull("receivedConnectMessage was expected to be non-null", receivedConnectMessage);
        assertEquals("Only one connect message was expected", 1, receivedConnectMessage.size());
        assertEquals(IoUtils.getResourceAsString("mqtt/connect-SN-001.json"), receivedConnectMessage.get(0));

        List<String> receivedTelemetryMessage = testMqttHandler.getValues("v1/gateway/telemetry");
        assertNotNull("receivedTelemetryMessage was expected to be non-null", receivedTelemetryMessage);
        assertEquals("Only one telemetry message was expected", 1, receivedTelemetryMessage.size());

        String expectedTelemetryJson = IoUtils.getResourceAsString("mqtt/single-value-result.json");
        String actualTelemetryJson = receivedTelemetryMessage.get(0);

        JsonUtils.assertWithoutTimestamp("SN-001", expectedTelemetryJson, actualTelemetryJson);
    }

}
