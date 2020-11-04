package org.thingsboard.gateway.service.data;

import lombok.Data;

/**
 * Created by ashvayka on 22.02.17.
 */
@Data
public class RpcCommandResponse {

    private int requestId;
    private String deviceName;
    private String data;

}
