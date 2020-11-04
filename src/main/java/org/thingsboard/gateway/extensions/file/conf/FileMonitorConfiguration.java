package org.thingsboard.gateway.extensions.file.conf;

import lombok.Data;

/**
 * Created by ashvayka on 15.05.17.
 */
@Data
public class FileMonitorConfiguration {

    private String file;
    private int skipLines;
    private int updateInterval;
    private String[] csvColumns;
    private CsvDeviceDataConverter converter;
}
