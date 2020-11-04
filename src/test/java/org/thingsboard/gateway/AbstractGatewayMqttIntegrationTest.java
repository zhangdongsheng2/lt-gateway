package org.thingsboard.gateway;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Valerii Sosliuk on 5/9/2018.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
@ContextConfiguration(classes = {TestConfiguration.class, GatewayConfiguration.class})
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public abstract class AbstractGatewayMqttIntegrationTest {

}
