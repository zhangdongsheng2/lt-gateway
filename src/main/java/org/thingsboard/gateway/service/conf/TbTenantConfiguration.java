package org.thingsboard.gateway.service.conf;

import lombok.Data;

import java.util.List;

/**
 * Created by ashvayka on 29.09.17.
 */
@Data
public class TbTenantConfiguration {

    private String label;
    private TbReportingConfiguration reporting;
    private TbPersistenceConfiguration persistence;
    private TbConnectionConfiguration connection;
    private Boolean remoteConfiguration;
    private List<TbExtensionConfiguration> extensions;
}
