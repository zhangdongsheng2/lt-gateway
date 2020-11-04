package org.thingsboard.gateway.util;

import lombok.Data;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

/**
 * Created by ashvayka on 16.01.17.
 */
@Data
public class CertificateInfo {

    private final X509Certificate certificate;
    private final KeyPair keyPair;

}
