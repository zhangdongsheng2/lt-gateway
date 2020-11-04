package org.thingsboard.gateway.extensions.file;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.gateway.extensions.ExtensionUpdate;
import org.thingsboard.gateway.extensions.file.conf.FileTailConfiguration;
import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;
import org.thingsboard.gateway.service.gateway.GatewayService;
import org.thingsboard.gateway.util.ConfigurationTools;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ashvayka on 15.05.17.
 */
@Slf4j
public class DefaultFileTailService extends ExtensionUpdate {

    private final GatewayService gateway;
    private TbExtensionConfiguration currentConfiguration;
    private List<FileMonitor> brokers;

    public DefaultFileTailService(GatewayService gateway) {
        this.gateway = gateway;
    }

    @Override
    public TbExtensionConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    @Override
    public void init(TbExtensionConfiguration configurationNode, Boolean isRemote) throws Exception {
        currentConfiguration = configurationNode;
        log.info("[{}] Initializing File Tail service!", gateway.getTenantLabel());
        FileTailConfiguration configuration;
        try {
            if(isRemote) {
                configuration = ConfigurationTools.readConfiguration(configurationNode.getConfiguration(), FileTailConfiguration.class);
            } else {
                configuration = ConfigurationTools.readFileConfiguration(configurationNode.getExtensionConfiguration(), FileTailConfiguration.class);
            }
        } catch (Exception e) {
            log.error("[{}] File Tail service configuration failed!", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }

        try {
            brokers = configuration.getFileMonitorConfigurations().stream().map(c -> new FileMonitor(gateway, c)).collect(Collectors.toList());
            brokers.forEach(FileMonitor::init);
        } catch (Exception e) {
            log.error("[{}] File Tail service initialization failed!", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }
    }

    @Override
    public void destroy() {
        if (brokers != null) {
            brokers.forEach(FileMonitor::stop);
        }
    }

}
