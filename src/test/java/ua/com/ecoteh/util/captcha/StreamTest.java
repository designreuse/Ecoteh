package ua.com.ecoteh.util.captcha;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class StreamTest {

    @Test
    public void whenWrite() {
        try {
            Stream.write(ANY_STRING, mock(OutputStream.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenRead() {
        try {
            Stream.read(mock(InputStream.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}