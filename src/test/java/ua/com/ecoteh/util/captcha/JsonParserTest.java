package ua.com.ecoteh.util.captcha;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class JsonParserTest {

    @Test
    public void whenNullResponseThenReturnFalse() {
        final String response = null;
        final JsonParser parser = new JsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }

    @Test
    public void whenEmptyResponseThenReturnFalse() {
        final String response = "";
        final JsonParser parser = new JsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }

    @Test
    public void whenTrueResponseThenReturnTrue() {
        final String response = "{\"success\":true}";
        final JsonParser parser = new JsonParser(response);
        final boolean result = parser.parse();
        assertTrue(result);
    }

    @Test
    public void whenFalseResponseThenReturnFalse() {
        final String response = "{\"success\":false}";
        final JsonParser parser = new JsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }
}
