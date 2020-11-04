package org.thingsboard.gateway.extensions.modbus.conf.mapping;

import lombok.Data;
import lombok.ToString;
import org.thingsboard.gateway.extensions.common.conf.mapping.DataTypeMapping;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusExtensionConstants;

@Data
@ToString(callSuper=true)
public class PollingTagMapping extends TagMapping {
    private int pollPeriod = ModbusExtensionConstants.NO_POLL_PERIOD_DEFINED;
    private DataTypeMapping type;
}
