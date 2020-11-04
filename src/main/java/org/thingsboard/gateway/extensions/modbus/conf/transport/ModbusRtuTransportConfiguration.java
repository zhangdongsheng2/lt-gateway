 package org.thingsboard.gateway.extensions.modbus.conf.transport;

import lombok.Data;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusExtensionConstants;

@Data
public class ModbusRtuTransportConfiguration implements ModbusTransportConfiguration {
    private String portName;
    private int timeout = ModbusExtensionConstants.DEFAULT_SOCKET_TIMEOUT;
    private String encoding;
    private int baudRate;
    private int dataBits;
    private float stopBits;
    private String parity;
    @Override
    public long getRetryInterval() {
        return timeout;
    }
}