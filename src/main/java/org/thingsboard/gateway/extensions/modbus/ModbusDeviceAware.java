package org.thingsboard.gateway.extensions.modbus;

public interface ModbusDeviceAware {
    ModbusDevice getDevice(String deviceName);
}
