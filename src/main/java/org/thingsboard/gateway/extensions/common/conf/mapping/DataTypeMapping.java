package org.thingsboard.gateway.extensions.common.conf.mapping;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.server.common.data.kv.DataType;

/**
 * Created by ashvayka on 17.01.17.
 */
@Data
@AllArgsConstructor
public class DataTypeMapping {

    private DataType dataType;

    @JsonCreator
    public static DataTypeMapping forValue(String value) {
        return new DataTypeMapping(DataType.valueOf(value.toUpperCase()));
    }

}
