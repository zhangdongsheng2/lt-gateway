package org.thingsboard.gateway.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.io.IOException;

/**
 * Created by Valerii Sosliuk on 6/8/2018.
 */
public class JsonUtils {

    public static JsonNode fromString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(json);
    }

    public static void assertWithoutTimestamp(String deviceName, String expected, String actual) throws JSONException, IOException {
        JSONAssert.assertEquals(expected, actual,
                new CustomComparator(JSONCompareMode.STRICT, new Customization(deviceName + "[0].ts",
                        new RegularExpressionValueMatcher<>("\\d+"))));
    }

    public static void assertEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
    }
}
