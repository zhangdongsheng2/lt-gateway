package org.thingsboard.gateway.extensions.opc.scan;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.thingsboard.server.common.data.kv.KvEntry;

import java.util.Optional;

/**
 * Created by ashvayka on 17.01.17.
 */
public class DataValueConverter {

    public static Optional<KvEntry> toKvEntry(DataValue dataValue){
        return Optional.empty();
    }
}
