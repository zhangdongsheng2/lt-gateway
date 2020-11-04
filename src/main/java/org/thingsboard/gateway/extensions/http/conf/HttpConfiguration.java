package org.thingsboard.gateway.extensions.http.conf;

import lombok.Data;

import java.util.List;

@Data
public class HttpConfiguration {

    List<HttpConverterConfiguration> converterConfigurations;
    List<HttpConverterConfiguration> deviceTypeConfigurations;
}
