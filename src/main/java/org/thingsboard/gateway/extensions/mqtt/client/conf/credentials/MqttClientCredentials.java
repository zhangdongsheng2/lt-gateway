package org.thingsboard.gateway.extensions.mqtt.client.conf.credentials;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * Created by ashvayka on 23.01.17.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnonymousCredentials.class, name = "anonymous"),
        @JsonSubTypes.Type(value = BasicCredentials.class, name = "basic"),
        @JsonSubTypes.Type(value = CertPemClientCredentials.class, name = "cert.PEM"),
        @JsonSubTypes.Type(value = CertJKSClientCredentials.class, name = "cert.JKS"),
        @JsonSubTypes.Type(value = CertPKCS12ClientCredentials.class, name = "cert.PKCS12")})
public interface MqttClientCredentials {

    void configure(MqttConnectOptions clientOptions);

}
