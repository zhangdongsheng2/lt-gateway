package org.thingsboard.gateway.extensions.http.conf;

import lombok.Data;
import org.thingsboard.gateway.extensions.http.conf.mapping.HttpDeviceDataConverter;

import java.util.List;

@Data
public class HttpConverterConfiguration {

    private String converterId;
    private String deviceTypeId;
    private String token;
    private List<HttpDeviceDataConverter> converters;
}
