package ua.com.ecoteh.util.captcha;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class HttpsURLStreamTest {

    private static HttpsURLStream stream;

    @BeforeClass
    public static void beforeClass() {
        final HttpsURLConnection connection = createMockHttpsURLConnection();
        stream = new HttpsURLStream(connection);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddNullConnectionInConstructorThenThrowIllegalArgumentException() {
        final HttpsURLConnection connection = null;
        new HttpsURLStream(connection);
    }

    @Test
    public void readTest() {
        try {
            stream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeTest() {
        try {
            stream.write(ANY_STRING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenCreateMockHttpsURLConnectionThenReturnNotNull() {
        final HttpsURLConnection connection = createMockHttpsURLConnection();
        assertNotNull(connection);
    }

    @Test
    public void whenCreateMockInputStreamThenReturnNotNull() {
        final InputStream inputStream = createMockInputStream();
        assertNotNull(inputStream);
    }

    @Test
    public void whenCreateMockOutputStreamThenReturnNotNull() {
        final OutputStream outputStream = createMockOutputStream();
        assertNotNull(outputStream);
    }

    private static HttpsURLConnection createMockHttpsURLConnection() {
        final HttpsURLConnection connection = mock(HttpsURLConnection.class);
        try {
            final InputStream inputStream = createMockInputStream();
            when(connection.getInputStream()).thenReturn(inputStream);
            final OutputStream outputStream = createMockOutputStream();
            when(connection.getOutputStream()).thenReturn(outputStream);
        } catch (IOException e) {
        }
        return connection;
    }

    private static InputStream createMockInputStream() {
        final InputStream inputStream = mock(InputStream.class);
        try {
            when(inputStream.read()).thenReturn(10);
            when(inputStream.read(new byte[] {})).thenReturn(10);
        } catch (IOException e) {
        }
        return inputStream;
    }

    private static OutputStream createMockOutputStream() {
        final OutputStream outputStream = mock(OutputStream.class);
        return outputStream;
    }
}