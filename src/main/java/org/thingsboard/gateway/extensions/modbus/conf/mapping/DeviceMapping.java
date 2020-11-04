package org.thingsboard.gateway.extensions.modbus.conf.mapping;

import lombok.Data;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusExtensionConstants;

import java.util.Collections;
import java.util.List;

@Data
public class DeviceMapping {
    private int unitId;
    private String deviceName;
    private int attributesPollPeriod = ModbusExtensionConstants.DEFAULT_POLL_PERIOD;
    private int timeseriesPollPeriod = ModbusExtensionConstants.DEFAULT_POLL_PERIOD;
    private List<PollingTagMapping> attributes = Collections.emptyList(); // FIXME: Is it a real case, what device is without attributes?
    private List<PollingTagMapping> timeseries = Collections.emptyList(); // FIXME: Is it a real case, what device is without timeseries?
}
