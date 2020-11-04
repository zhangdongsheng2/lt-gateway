package org.thingsboard.gateway.extensions.modbus.conf;

import lombok.Data;

import java.util.List;

import org.thingsboard.gateway.extensions.modbus.conf.ModbusServerConfiguration;

@Data
public class ModbusConfiguration {
    private List<ModbusServerConfiguration> servers;
}
