package org.thingsboard.gateway.util;

import lombok.Data;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class KeystoreConfiguration {

    private String type;
    private String location;
    private String fileContent;
    private String password;
    private String alias;
    private String keyPassword;

}
