package org.thingsboard.gateway.extensions.opc.conf;

import lombok.Data;

import java.util.List;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class OpcUaConfiguration {

    List<OpcUaServerConfiguration> servers;
}
