package org.thingsboard.gateway.extensions.modbus;

import java.util.List;
import java.util.stream.Collectors;

import org.thingsboard.gateway.extensions.ExtensionUpdate;
import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;
import org.thingsboard.gateway.service.gateway.GatewayService;
import org.thingsboard.gateway.util.ConfigurationTools;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.gateway.extensions.modbus.conf.ModbusConfiguration;

@Slf4j
public class DefaultModbusService extends ExtensionUpdate implements ModbusService {

    private final GatewayService gateway;
    private TbExtensionConfiguration currentConfiguration;
    private List<ModbusClient> clients;

    public DefaultModbusService(GatewayService gateway) {
        this.gateway = gateway;
    }

    public TbExtensionConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    public void init(TbExtensionConfiguration configurationNode, Boolean isRemote) throws Exception {
        currentConfiguration = configurationNode;
        log.info("[{}] Initializing Modbus service", gateway.getTenantLabel());
        ModbusConfiguration configuration;
        try {
            if(isRemote) {
                configuration = ConfigurationTools.readConfiguration(configurationNode.getConfiguration(), ModbusConfiguration.class);
            } else {
                configuration = ConfigurationTools.readFileConfiguration(configurationNode.getExtensionConfiguration(), ModbusConfiguration.class);
            }
        } catch (Exception e) {
            log.error("[{}] Modbus service configuration failed", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }

        log.debug("[{}] Modbus service configuration [{}]", gateway.getTenantLabel(), configuration);

        try {
            clients = configuration.getServers().stream().map(c -> new ModbusClient(gateway, c)).collect(Collectors.toList());
            clients.forEach(ModbusClient::connect);
        } catch (Exception e) {
            log.error("[{}] Modbus service initialization failed", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }
    }

    public void destroy() {
        if (clients != null) {
            clients.forEach(ModbusClient::disconnect);
        }
    }
}
