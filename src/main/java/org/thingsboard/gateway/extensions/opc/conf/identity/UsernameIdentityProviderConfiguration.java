package org.thingsboard.gateway.extensions.opc.conf.identity;

import lombok.Data;
import org.eclipse.milo.opcua.sdk.client.api.identity.IdentityProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class UsernameIdentityProviderConfiguration implements IdentityProviderConfiguration {

    private final String username;
    private final String password;

    @Override
    public IdentityProvider toProvider() {
        return new UsernameProvider(username, password);
    }
}
