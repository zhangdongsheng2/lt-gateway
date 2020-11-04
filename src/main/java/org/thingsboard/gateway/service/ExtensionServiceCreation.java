package org.thingsboard.gateway.service;

import org.thingsboard.gateway.extensions.ExtensionService;
import org.thingsboard.gateway.service.gateway.GatewayService;

public interface ExtensionServiceCreation {

    ExtensionService createExtensionServiceByType(GatewayService gateway, String type);
}
