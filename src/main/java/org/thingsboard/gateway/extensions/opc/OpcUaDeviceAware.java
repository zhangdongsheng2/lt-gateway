package org.thingsboard.gateway.extensions.opc;

public interface OpcUaDeviceAware {
    OpcUaDevice getDevice(String deviceName);
}
