package org.thingsboard.gateway.service.conf;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * Created by ashvayka on 24.01.17.
 */
@Data
@Slf4j
public class TbPersistenceConfiguration {

    private String type;
    private String path;
    private int bufferSize;
    private long pollingInterval;

}
