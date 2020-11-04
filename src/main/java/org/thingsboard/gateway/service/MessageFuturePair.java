package org.thingsboard.gateway.service;

import io.netty.util.concurrent.Future;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Valerii Sosliuk on 3/10/2018.
 */
@Data
@AllArgsConstructor
public class MessageFuturePair {

    Future<? super Void> future;
    MqttPersistentMessage message;
}
