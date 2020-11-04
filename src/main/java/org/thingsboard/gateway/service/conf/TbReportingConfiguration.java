package org.thingsboard.gateway.service.conf;

import lombok.Data;

/**
 * Created by ashvayka on 24.01.17.
 */
@Data
public class TbReportingConfiguration {

    private long interval;
    private int maxErrorsPerInterval;
}
