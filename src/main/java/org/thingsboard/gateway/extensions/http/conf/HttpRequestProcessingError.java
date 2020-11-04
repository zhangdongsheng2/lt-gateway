package org.thingsboard.gateway.extensions.http.conf;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by ashvayka on 23.03.17.
 */
@Data
@AllArgsConstructor
public class HttpRequestProcessingError {

    private String message;

}
