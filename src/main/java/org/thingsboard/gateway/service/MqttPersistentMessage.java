package org.thingsboard.gateway.service;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Valerii Sosliuk on 1/2/2018.
 */
@Data
@Builder
public class MqttPersistentMessage implements Serializable {

    private static final long serialVersionUID = -3133461476074777891L;

    private UUID id;
    private long timestamp;
    private String deviceId;
    private int messageId;
    private String topic;
    private byte[] payload;

    @Override
    public String toString() {
        return "{deviceId='" + deviceId + '\'' +
                ", payload=" + new String(payload) +
                ", timestamp=" + timestamp +
                ", topic='" + topic + '\'' +
                "id=" + id +
                ", messageId=" + messageId +
                '}';
    }
}
