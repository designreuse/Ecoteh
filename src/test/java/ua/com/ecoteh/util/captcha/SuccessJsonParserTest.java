package ua.com.ecoteh.util.captcha;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class SuccessJsonParserTest {

    @Test
    public void whenNullResponseThenReturnFalse() {
        final String response = null;
        final SuccessJsonParser parser = new SuccessJsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }

    @Test
    public void whenEmptyResponseThenReturnFalse() {
        final String response = "";
        final SuccessJsonParser parser = new SuccessJsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }

    @Test
    public void whenTrueResponseThenReturnTrue() {
        final String response = "{\"success\":true}";
        final SuccessJsonParser parser = new SuccessJsonParser(response);
        final boolean result = parser.parse();
        assertTrue(result);
    }

    @Test
    public void whenFalseResponseThenReturnFalse() {
        final String response = "{\"success\":false}";
        final SuccessJsonParser parser = new SuccessJsonParser(response);
        final boolean result = parser.parse();
        assertFalse(result);
    }
}
