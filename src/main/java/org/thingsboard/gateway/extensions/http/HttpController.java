package org.thingsboard.gateway.extensions.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.thingsboard.gateway.extensions.http.conf.HttpRequestProcessingError;
import org.thingsboard.gateway.service.TenantManagerService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class HttpController {
    private static final String TOKEN_HEADER = "Authorization";

    @Autowired
    private TenantManagerService service;

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/sigfox/{deviceTypeId}", method = RequestMethod.POST)
    public void handleSigfoxRequest(@PathVariable String deviceTypeId,
                              @RequestHeader(TOKEN_HEADER) String token,
                              @RequestBody String body) throws Exception {
        service.processRequest(deviceTypeId, token, body);
    }

    @RequestMapping(value = "/uplink/{converterId}", method = RequestMethod.POST)
    public void handleRequest(@PathVariable String converterId,
                              @RequestBody String body) throws Exception {
        service.processRequest(converterId, null, body);
    }

    @ExceptionHandler(Exception.class)
    public void handleThingsboardException(Exception exception, HttpServletResponse response) {
        log.debug("Processing exception {}", exception.getMessage(), exception);
        if (!response.isCommitted()) {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                if (exception instanceof SecurityException) {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    mapper.writeValue(response.getWriter(),
                            new HttpRequestProcessingError("You don't have permission to perform this operation!"));
                } else {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    mapper.writeValue(response.getWriter(), new HttpRequestProcessingError(exception.getMessage()));
                }
            } catch (IOException e) {
                log.error("Can't handle exception", e);
            }
        }
    }
}