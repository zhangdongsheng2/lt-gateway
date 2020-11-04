package org.thingsboard.gateway.extensions.modbus.conf.mapping;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class TagValueMapping extends TagMapping {
    private Object value;
}
