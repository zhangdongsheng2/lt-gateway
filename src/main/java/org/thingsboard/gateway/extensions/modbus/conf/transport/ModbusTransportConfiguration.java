 package org.thingsboard.gateway.extensions.modbus.conf.transport;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by ashvayka on 16.01.17.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ModbusTcpTransportConfiguration.class, name = "tcp"),
        @JsonSubTypes.Type(value = ModbusUdpTransportConfiguration.class, name = "udp"),
        @JsonSubTypes.Type(value = ModbusRtuTransportConfiguration.class, name = "rtu")})
public interface ModbusTransportConfiguration {
 /**
  * Get the trial cycle time
  *
  * @return
  */
 long getRetryInterval();

}