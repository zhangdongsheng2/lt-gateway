package org.thingsboard.gateway.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.thingsboard.gateway.service.RpcCommandListener;

/**
 * Created by ashvayka on 23.02.17.
 */
@Data
@AllArgsConstructor
public class RpcCommandSubscription {

    private String deviceNameFilter;
    private RpcCommandListener listener;

    public boolean matches(String deviceName) {
        return deviceName.matches(deviceNameFilter);
    }

}
