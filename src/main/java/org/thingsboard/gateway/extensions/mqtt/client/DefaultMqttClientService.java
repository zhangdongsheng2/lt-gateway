package org.thingsboard.gateway.extensions.mqtt.client;

import lombok.extern.slf4j.Slf4j;
import org.thingsboard.gateway.extensions.ExtensionUpdate;
import org.thingsboard.gateway.extensions.mqtt.client.conf.MqttClientConfiguration;
import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;
import org.thingsboard.gateway.service.gateway.GatewayService;
import org.thingsboard.gateway.util.ConfigurationTools;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ashvayka on 23.01.17.
 */
@Slf4j
public class DefaultMqttClientService extends ExtensionUpdate implements MqttClientService {

    private final GatewayService gateway;
    private TbExtensionConfiguration currentConfiguration;
    private List<MqttBrokerMonitor> brokers;

    public DefaultMqttClientService(GatewayService gateway) {
        this.gateway = gateway;
    }

    @Override
    public TbExtensionConfiguration getCurrentConfiguration() {
        return currentConfiguration;
    }

    @Override
    public void init(TbExtensionConfiguration configurationNode, Boolean isRemote) throws Exception {
        currentConfiguration = configurationNode;
        log.info("[{}] Initializing MQTT client service!", gateway.getTenantLabel());
        MqttClientConfiguration configuration;
        try {
            if(isRemote) {
                configuration = ConfigurationTools.readConfiguration(configurationNode.getConfiguration(), MqttClientConfiguration.class);
            } else {
                configuration = ConfigurationTools.readFileConfiguration(configurationNode.getExtensionConfiguration(), MqttClientConfiguration.class);
            }
        } catch (Exception e) {
            log.error("[{}] MQTT client service configuration failed!", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }

        try {
            brokers = configuration.getBrokers().stream().map(c -> new MqttBrokerMonitor(gateway, c)).collect(Collectors.toList());
            brokers.forEach(MqttBrokerMonitor::connect);
        } catch (Exception e) {
            log.error("[{}] MQTT client service initialization failed!", gateway.getTenantLabel(), e);
            gateway.onConfigurationError(e, currentConfiguration);
            throw e;
        }
    }

    @Override
    public void destroy() {
        if (brokers != null) {
            brokers.forEach(MqttBrokerMonitor::disconnect);
        }
    }


}
