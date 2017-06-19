package ua.com.ecoteh.util.captcha;

import javax.json.Json;
import javax.json.JsonObject;
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
        final boolean result;
        if (isNotEmpty(this.response)) {
            final JsonObject json = readJson();
            result = json.getBoolean("success");
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Creates and return a JSON reader using this response.
     *
     * @return the JSON object.
     */
    private JsonObject readJson() {
        try (final StringReader stringReader = new StringReader(this.response);
                final JsonReader jsonReader = Json.createReader(stringReader)) {
            return jsonReader.readObject();
        }
    }
}
