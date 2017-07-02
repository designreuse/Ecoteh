package ua.com.ecoteh.util.captcha;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class implements a set of methods for working with JSON parser.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class SuccessJsonParser {

    private final static String ASSOCIATE = "success";

    /**
     * The JSON parser response.
     */
    private final String response;

    /**
     * Constructor.
     *
     * @param response a SuccessJsonParser response.
     */
    SuccessJsonParser(final String response) {
        this.response = response;
    }

    /**
     * Parses SuccessJsonParser response and return 'success' value.
     *
     * @return 'Success' value.
     */
    boolean parse() {
        final boolean result;
        if (isNotEmpty(this.response)) {
            final JsonObject json = readJson();
            result = json.getBoolean(ASSOCIATE);
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
        try (StringReader stringReader = new StringReader(this.response);
                JsonReader jsonReader = Json.createReader(stringReader)) {
            return jsonReader.readObject();
        }
    }
}
