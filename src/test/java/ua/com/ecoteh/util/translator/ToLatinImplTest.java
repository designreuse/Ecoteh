package ua.com.ecoteh.util.translator;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ToLatinImplTest {

    @Test
    public void fromCyrillic() throws Exception {
        final String before = "абвгдеёжзіийклмнопрстуфхцчшщъыьэюя .,!?/\\(){}[]$#^*";
        final String after = new ToLatinImpl(before).fromCyrillic();
        assertNotNull(after);
        assertNotEquals(before, after);
    }
}
