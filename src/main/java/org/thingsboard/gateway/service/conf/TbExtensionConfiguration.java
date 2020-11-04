package org.thingsboard.gateway.service.conf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;

/**
 * Created by ashvayka on 29.09.17.
 */
@Data
public class TbExtensionConfiguration {

    private String id;
    private String type;
    private JsonNode configuration;
    private String extensionConfiguration;
}
