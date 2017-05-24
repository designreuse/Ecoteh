package ua.com.ecoteh.util.captcha;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.StringReader;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for working
 * with JsonParser parser.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class JsonParser {

    /**
     * The JsonParser response.
     */
    private final String response;

    /**
     * Constructor.
     *
     * @param response a JsonParser response.
     */
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
        if (isNotEmpty(this.response)) {
            try (final JsonReader jsonReader = Json.createReader(new StringReader(this.response))) {
                result = jsonReader.readObject().getBoolean("success");
            }
        }
        return result;
    }
}
