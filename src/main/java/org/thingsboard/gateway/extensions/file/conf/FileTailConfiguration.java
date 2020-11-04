package org.thingsboard.gateway.extensions.file.conf;

import lombok.Data;

import java.util.List;

/**
 * Created by ashvayka on 15.05.17.
 */
@Data
public class FileTailConfiguration {

    List<FileMonitorConfiguration> fileMonitorConfigurations;
}
