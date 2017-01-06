package com.salimov.yurii.util.captcha;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working
 * with JsonParser parser.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see CaptchaImpl
 */
final class JsonParser {

    /**
     * The JsonParser response.
     */
    private final String response;

    JsonParser(final String response) {
        this.response = response;
    }

    /**
     * Parses JsonParser response and return 'success' value.
     *
     * @return 'Success' value.
     */
    boolean parse() {
        boolean result = false;
        if (isNotBlank(response)) {
            try (
                    final JsonReader jsonReader = Json.createReader(
                            new StringReader(response)
                    )
            ) {
                result = jsonReader.readObject()
                        .getBoolean("success");
            }
        }
        return result;
    }
}
