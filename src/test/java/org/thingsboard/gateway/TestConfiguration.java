package org.thingsboard.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thingsboard.gateway.mqtt.TestMqttHandler;

import java.util.TimeZone;

/**
 * Created by Valerii Sosliuk on 6/12/2018.
 */
@Configuration
@ComponentScan("org.thingsboard.gateway")
public class TestConfiguration {

    @Bean
    public TimeZone timeZone(){
        TimeZone defaultTimeZone = TimeZone.getTimeZone("UTC");
        TimeZone.setDefault(defaultTimeZone);
        return defaultTimeZone;
    }

    @Bean
    public TestMqttHandler getMqttHandler() {
        return new TestMqttHandler();
    }

}
