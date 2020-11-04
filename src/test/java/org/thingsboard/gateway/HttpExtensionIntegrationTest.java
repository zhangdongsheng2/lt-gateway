package org.thingsboard.gateway;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpExtensionIntegrationTest {

    private static final String GATEWAY_URL = "http://localhost:9090/sigfox/%s/";

    public static void main(String[] args) throws IOException {
        doPost("YOUR_DEVICE_TYPE_ID", "TOKEN");
    }

    private static void doPost(String deviceTypeId, String token) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String postJson = new String(Files.readAllBytes(Paths.get(String.format("src/test/resources/%s.json", deviceTypeId))));
        new RestTemplate().exchange(String.format(GATEWAY_URL, deviceTypeId), HttpMethod.POST, new HttpEntity<>(postJson, headers), String.class);
    }
}
