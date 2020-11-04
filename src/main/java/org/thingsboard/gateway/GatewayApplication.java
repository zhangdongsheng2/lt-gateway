package org.thingsboard.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class GatewayApplication {

    private static final String SPRING_CONFIG_NAME_KEY = "--spring.config.name";
    public static final String DEFAULT_SPRING_CONFIG_PARAM = SPRING_CONFIG_NAME_KEY + "=" + "lt-gateway";

    public static void main(String[] args) {
        try {
            SpringApplication.run(GatewayApplication.class, updateArguments(args));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            System.exit(1);
        }
    }

    private static String[] updateArguments(String[] args) {
        if (Arrays.stream(args).noneMatch(arg -> arg.startsWith(SPRING_CONFIG_NAME_KEY))) {
            String[] modifiedArgs = new String[args.length + 1];
            System.arraycopy(args, 0, modifiedArgs, 0, args.length);
            modifiedArgs[args.length] = DEFAULT_SPRING_CONFIG_PARAM;
            return modifiedArgs;
        }
        return args;
    }

}
