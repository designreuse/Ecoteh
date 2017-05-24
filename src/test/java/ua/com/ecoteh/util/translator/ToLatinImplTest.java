package ua.com.ecoteh.util.translator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

public class ToLatinImplTest {

    @Test
    public void fromCyrillic() throws Exception {
        final String before = "абвгдеёжзіийклмнопрстуфхцчшщъыьэюя .,!?/\\(){}[]$#^*";
        final String after = new ToLatinImpl(before).fromCyrillic();
        assertNotNull(after);
        assertNotEquals(before, after);
    }

    @Test
    public void getValue() throws Exception {
        final ToLatin toLatin = new ToLatinImpl(ANY_STRING);
        assertNotNull(toLatin.getValue());
        assertEquals(toLatin.getValue(), ANY_STRING);
    }
}
