package org.thingsboard.gateway.extensions;

import org.thingsboard.gateway.service.conf.TbExtensionConfiguration;

/**
 * Created by ashvayka on 29.09.17.
 */
public interface ExtensionService {

    TbExtensionConfiguration getCurrentConfiguration();

    void init(TbExtensionConfiguration configuration, Boolean isRemote) throws Exception;

    void update(TbExtensionConfiguration configuration) throws Exception;

    void destroy() throws Exception;
}
