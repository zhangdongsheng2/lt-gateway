package org.thingsboard.gateway.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.thingsboard.server.common.data.kv.KvEntry;
import org.thingsboard.server.common.data.kv.TsKvEntry;

import java.util.List;

/**
 * Created by ashvayka on 23.01.17.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DeviceData {

    private final String name;
    private final String type;
    private final List<KvEntry> attributes;
    private final List<TsKvEntry> telemetry;
    private int timeout;
}
