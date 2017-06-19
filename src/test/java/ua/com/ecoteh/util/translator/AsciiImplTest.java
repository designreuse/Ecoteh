package ua.com.ecoteh.util.translator;

import org.junit.Test;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AsciiImplTest {

    @Test
    public void whenCallConstructorWithString() {
        final Ascii ascii = new AsciiImpl(ANY_STRING);
        assertNotNull(ascii.to());
    }

    @Test
    public void whenCallConstructorWithInteger() {
        final Ascii ascii = new AsciiImpl(101);
        assertNotNull(ascii.to());
    }

    @Test
    public void to() throws Exception {
        final Ascii ascii = new AsciiImpl(ANY_STRING);
        assertNotNull(ascii.to());
    }

    @Test
    public void from() throws Exception {
        final String before = new AsciiImpl(ANY_STRING).to();
        assertNotNull(before);
        final String after = new AsciiImpl(before).from();
        assertNotNull(after);
        assertEquals(after, ANY_STRING);
    }

    @Test
    public void whenSetInvalidValueToTranslateFromAscii() {
        final String before = "Hello, World!";
        final String after = new AsciiImpl(before).from();
        assertNotNull(after);
        assertTrue(after.isEmpty());
    }
}