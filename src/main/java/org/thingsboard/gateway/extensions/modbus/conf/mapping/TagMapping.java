package org.thingsboard.gateway.extensions.modbus.conf.mapping;

import lombok.Data;
import org.thingsboard.gateway.extensions.common.conf.mapping.DataTypeMapping;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusExtensionConstants;

@Data
public class TagMapping {
    private String tag;
    private int functionCode;
    private int address;
    private int registerCount = ModbusExtensionConstants.DEFAULT_REGISTER_COUNT;
    private String byteOrder = ModbusExtensionConstants.BIG_ENDIAN_BYTE_ORDER;
    private int bit = ModbusExtensionConstants.NO_BIT_INDEX_DEFINED;
}
