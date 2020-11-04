package org.thingsboard.gateway.extensions.modbus.conf;

import lombok.Data;
import org.thingsboard.gateway.extensions.modbus.conf.mapping.DeviceMapping;
import org.thingsboard.gateway.extensions.modbus.conf.transport.ModbusTransportConfiguration;

import java.util.List;

@Data
public class ModbusServerConfiguration {
    private ModbusTransportConfiguration transport;
    private List<DeviceMapping> devices;
}