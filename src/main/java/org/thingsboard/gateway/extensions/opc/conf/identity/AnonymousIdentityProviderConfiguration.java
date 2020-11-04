package org.thingsboard.gateway.extensions.opc.conf.identity;

import lombok.Data;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class AnonymousIdentityProviderConfiguration implements IdentityProviderConfiguration {

    @Override
    public IdentityProvider toProvider() {
        return new AnonymousProvider();
    }
}
