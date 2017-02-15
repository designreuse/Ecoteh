package com.salimov.yurii.util.captcha;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

/**
 * The class implements a set of methods for working
 * with https URL connection.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see CaptchaImpl
 */
final class Connection {

    /**
     * The request url.
     */
    private final URL url;

    /**
     * The user agent.
     */
    private final String userAgent;

    /**
     * The accept language.
     */
    private final String acceptLanguage;

    /**
     * The do output.
     */
    private final boolean doOutput;

    /**
     * The https url connection.
     */
    private HttpsURLConnection connection;

    /**
     * Constructor.
     *
     * @param url            a request url.
     * @param userAgent      a user agent.
     * @param acceptLanguage a accept language.
     * @param doOutput       a do output.
     */
    Connection(
            final URL url,
            final String userAgent,
            final String acceptLanguage,
            final boolean doOutput
    ) {
        this.url = url;
        this.userAgent = userAgent;
        this.acceptLanguage = acceptLanguage;
        this.doOutput = doOutput;
    }

    /**
     * Creates and returns https url connection.
     *
     * @return The https url connection.
     * @throws IOException If an I/O error occurs.
     */
    HttpsURLConnection getHttpsURLConnection() throws IOException {
        if (this.connection == null) {
            createConnection();
        }
        return this.connection;
    }

    /**
     * Creates https URL connection.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void createConnection() throws IOException {
        this.connection = (HttpsURLConnection) this.url.openConnection();
        this.connection.setRequestMethod("POST");
        this.connection.setRequestProperty("User-Agent", this.userAgent);
        this.connection.setRequestProperty("Accept-Language", this.acceptLanguage);
        this.connection.setDoOutput(this.doOutput);
    }
}
