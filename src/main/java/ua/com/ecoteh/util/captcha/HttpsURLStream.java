package ua.com.ecoteh.util.captcha;

import ua.com.ecoteh.exception.ExceptionMessage;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods
 * for working with streams.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class HttpsURLStream {

    /**
     * The https URL connection.
     */
    private final HttpsURLConnection connection;

    /**
     * Constructor.
     *
     * @param connection the https URL connection.
     * @throws IllegalArgumentException Throw exception when incoming connection is null.
     */
    HttpsURLStream(final HttpsURLConnection connection) throws IllegalArgumentException {
        if (isNull(connection)) {
            final String message = String.format(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    HttpsURLConnection.class.getSimpleName()
            );
            throw new IllegalArgumentException(message);
        }
        this.connection = connection;
    }

    /**
     * Reads response with the stream and returns response to string.
     *
     * @return The response to string.
     * @throws IOException If an I/O error occurs.
     */
    String read() throws IOException {
        final InputStream inputStream = getInputStream();
        final StringBuilder sb = new StringBuilder();
        try (final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String inputLine;
            while (isNotNull(inputLine = bufferedReader.readLine())) {
                sb.append(inputLine);
            }
        }
        return sb.toString();
    }

    /**
     * Writes value in the stream.
     *
     * @param value the value to write.
     * @throws IOException If an I/O error occurs.
     */
    void write(final String value) throws IOException {
        final OutputStream outputStream = getOutputStream();
        try (final DataOutputStream stream = new DataOutputStream(outputStream)) {
            stream.writeBytes(value);
            stream.flush();
        }
    }

    /**
     * Return an input stream of this https URL connection.
     *
     * @return the input stream.
     * @throws IOException If an I/O error occurs.
     */
    private InputStream getInputStream() throws IOException {
        return this.connection.getInputStream();
    }

    /**
     * Return an output stream of this https URL connection.
     *
     * @return the output stream.
     * @throws IOException If an I/O error occurs.
     */
    private OutputStream getOutputStream() throws IOException {
        return this.connection.getOutputStream();
    }
}
