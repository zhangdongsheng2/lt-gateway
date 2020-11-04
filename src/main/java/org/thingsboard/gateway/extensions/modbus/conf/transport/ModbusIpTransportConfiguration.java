 package org.thingsboard.gateway.extensions.modbus.conf.transport;

import lombok.Data;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusExtensionConstants;

@Data
public class ModbusIpTransportConfiguration implements ModbusTransportConfiguration {
    private String host;
    private int port = ModbusExtensionConstants.DEFAULT_MODBUS_TCP_PORT;
    private int timeout = ModbusExtensionConstants.DEFAULT_SOCKET_TIMEOUT;
    @Override
    public long getRetryInterval() {
        return timeout;
    }
}