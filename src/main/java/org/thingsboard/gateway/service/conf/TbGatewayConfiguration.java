package org.thingsboard.gateway.service.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Created by ashvayka on 29.09.17.
 */
@Configuration
@ConfigurationProperties(prefix = "gateways")
@Data
public class TbGatewayConfiguration {

    List<TbTenantConfiguration> tenants;
}
