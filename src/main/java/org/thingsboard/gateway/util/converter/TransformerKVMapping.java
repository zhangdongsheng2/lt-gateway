package org.thingsboard.gateway.util.converter;

import lombok.Data;
import org.thingsboard.gateway.extensions.common.conf.mapping.KVMapping;
import org.thingsboard.gateway.util.converter.transformer.DataValueTransformer;

@Data
public class TransformerKVMapping extends KVMapping {
    private DataValueTransformer transformer;
}
