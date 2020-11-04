package org.thingsboard.gateway.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Valerii Sosliuk on 5/26/2018.
 */
public class IoUtils {

    public static String getResourceAsString(String path) throws IOException {
        URL url = Resources.getResource(path);
        return Resources.toString(url, Charsets.UTF_8);
    }
}
