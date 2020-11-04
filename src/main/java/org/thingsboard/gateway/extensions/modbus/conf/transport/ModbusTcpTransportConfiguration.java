package org.thingsboard.gateway.extensions.modbus.conf.transport;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true, includeFieldNames=true)
public class ModbusTcpTransportConfiguration extends ModbusIpTransportConfiguration {
    boolean rtuOverTcp;
    boolean reconnect;
}