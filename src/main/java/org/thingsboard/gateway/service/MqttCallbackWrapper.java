package org.thingsboard.gateway.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

/**
 * Created by Valerii Sosliuk on 12/30/2017.
 */
@Data
@AllArgsConstructor
public class MqttCallbackWrapper {

    private Consumer<Void> successCallback;
    private Consumer<Throwable> failureCallback;
}
