package com.salimov.yurii.util.captcha;

import javax.json.Json;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Captcha
 */
public final class CaptchaImpl implements Captcha {
    /**
     * The reCAPTCHA user agent.
     */
    private final String userAgent;

    /**
     * The reCAPTCHA accept language.
     */
    private final String acceptLanguage;

    /**
     * The reCAPTCHA url.
     */
    private final String url;

    /**
     * The reCAPTCHA server key.
     */
    private final String serverKey;

    /**
     * The reCAPTCHA request param.
     */
    private final String parameter;

    /**
     * The reCAPTCHA header param.
     */
    private final String header;

    /**
     * The work status.
     */
    private String status;

    /**
     * Constructor.
     *
     * @param userAgent      a captcha user agent.
     * @param acceptLanguage a captcha accept language.
     * @param url            a captcha url.
     * @param serverKey      a captcha server key.
     * @param parameter      a captcha request param.
     * @param header         a captcha header param.
     */
    public CaptchaImpl(
            final String userAgent,
            final String acceptLanguage,
            final String url,
            final String serverKey,
            final String parameter,
            final String header
    ) {
        this.userAgent = userAgent;
        this.acceptLanguage = acceptLanguage;
        this.url = url;
        this.serverKey = serverKey;
        this.parameter = parameter;
        this.header = header;
    }

    /**
     * Verifies captcha from request.
     *
     * @param request a request from client.
     * @return {@code true} if captcha is verify, {@code false} otherwise.
     */
    @Override
    public boolean isVerify(final HttpServletRequest request) {
        this.status = null;
        boolean result = false;
        if (request != null) {
            String ipAddress = request.getHeader(this.header);
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            final String captcha = request.getParameter(this.parameter);
            result = isVerify(captcha, ipAddress);
        }
        return result;
    }

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return {@code true} if captcha is verify, {@code false} otherwise.
     */
    @Override
    public boolean isVerify(final String captcha, final String ipAddress) {
        this.status = null;
        boolean result = false;
        if (isNotBlank(captcha) && isNotBlank(ipAddress)) {
            try {
                result = parseJSON(
                        getResponse(
                                new URL(this.url),
                                captcha,
                                ipAddress
                        )
                );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Return work status.
     *
     * @return Work status.
     */
    @Override
    public String getStatus() {
        return this.status;
    }

    /**
     * Send request and return response.
     *
     * @param url       a request url.
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return A response to string.
     * @throws IOException If an I/O error occurs.
     */
    private String getResponse(
            final URL url,
            final String captcha,
            final String ipAddress
    ) throws IOException {
        final HttpsURLConnection connection = getConnection(url);
        final String postParams = "secret=" + this.serverKey
                + "&response=" + captcha
                + "&remoteip=" + ipAddress;
        writePostParams(
                postParams,
                connection.getOutputStream()
        );
        setStatus(
                url,
                postParams,
                connection.getResponseCode()
        );
        return readResponse(
                connection.getInputStream()
        );
    }

    /**
     * Creates and returns https url connection.
     *
     * @param url a request url.
     * @return The https url connection.
     * @throws IOException If an I/O error occurs.
     */
    private HttpsURLConnection getConnection(final URL url) throws IOException {
        final HttpsURLConnection connection
                = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty(
                "User-Agent",
                this.userAgent
        );
        connection.setRequestProperty(
                "Accept-Language",
                this.acceptLanguage
        );
        connection.setDoOutput(true);
        return connection;
    }

    /**
     * Writes post parameters in the stream.
     *
     * @param postParams   a post parameters.
     * @param outputStream a output stream.
     * @throws IOException If an I/O error occurs.
     */
    private void writePostParams(
            final String postParams,
            final OutputStream outputStream
    ) throws IOException {
        try (
                final DataOutputStream stream = new DataOutputStream(
                        outputStream
                )
        ) {
            stream.writeBytes(postParams);
            stream.flush();
        }
    }

    /**
     * Reads response with the stream and returns response to string.
     *
     * @param inputStream a input stream.
     * @return The response to string.
     * @throws IOException If an I/O error occurs.
     */
    private String readResponse(final InputStream inputStream)
            throws IOException {
        final StringBuilder response = new StringBuilder();
        try (
                final BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream)
                )
        ) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    /**
     * Parses JSON response and return 'success' value.
     *
     * @param response a JSON response.
     * @return 'Success' value.
     */
    private boolean parseJSON(final String response) {
        boolean result = false;
        if (isNotBlank(response)) {
            try (
                    final JsonReader jsonReader = Json.createReader(
                            new StringReader(response)
                    )
            ) {
                result = jsonReader.readObject()
                        .getBoolean("success");
                if (isNotBlank(this.status)) {
                    this.status += "\nSuccess = " + result;
                }
            }
        }
        return result;
    }

    /**
     * Create work status.
     *
     * @param url          a request url.
     * @param postParams   a post parameters.
     * @param responseCode a response code.
     */
    private void setStatus(
            final URL url,
            final String postParams,
            final int responseCode
    ) {
        this.status = "Sending 'POST' request to URL : " + url
                + "\nPost parameters : " + postParams
                + "\nResponse Code : " + responseCode;
    }
}
