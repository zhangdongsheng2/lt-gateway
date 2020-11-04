package org.thingsboard.gateway.extensions;

import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;

public abstract class ExtensionUpdate implements ExtensionService {

    public void update (TbExtensionConfiguration configurationNode) throws Exception {
        destroy();
        init(configurationNode, true);
    }
}
