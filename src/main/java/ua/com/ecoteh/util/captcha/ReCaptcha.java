package ua.com.ecoteh.util.captcha;

import org.apache.log4j.Logger;
import ua.com.ecoteh.aspect.ExceptionAspectController;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class implements a set of methods
 * for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ReCaptcha implements Captcha {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(ExceptionAspectController.class);

    /**
     * The reCAPTCHA user agent.
     */
    private final String userAgent;

    /**
     * The reCAPTCHA accept language.
     */
    private final String acceptLanguage;

    /**
     * The reCAPTCHA doOutput.
     */
    private final boolean doOutput;

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
     * @param userAgent      the captcha user agent.
     * @param acceptLanguage the captcha accept language.
     * @param doOutput       the do output.
     * @param url            the captcha URL.
     * @param serverKey      the captcha server key.
     * @param parameter      the captcha request param.
     * @param header         the captcha header param.
     */
    public ReCaptcha(
            final String userAgent,
            final String acceptLanguage,
            final boolean doOutput,
            final String url,
            final String serverKey,
            final String parameter,
            final String header
    ) {
        this.userAgent = userAgent;
        this.acceptLanguage = acceptLanguage;
        this.doOutput = doOutput;
        this.url = url;
        this.serverKey = serverKey;
        this.parameter = parameter;
        this.header = header;
        this.status = "";
    }

    /**
     * Verifies captcha from request.
     *
     * @param request the request from client.
     * @return true if captcha is verify, false otherwise.
     */
    @Override
    public boolean isVerify(final HttpServletRequest request) {
        this.status = "";
        boolean result = false;
        if (isNotNull(request)) {
            final String captcha = request.getParameter(this.parameter);
            final String ipAddress = getIpAddress(request);
            result = isVerify(captcha, ipAddress);
        }
        return result;
    }

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   the StaticCaptcha for check.
     * @param ipAddress the request ip address.
     * @return true if captcha is verify, false otherwise.
     */
    @Override
    public boolean isVerify(final String captcha, final String ipAddress) {
        this.status = "";
        boolean result = false;
        if (isNotEmpty(captcha) && isNotEmpty(ipAddress)) {
            try {
                final URL url = new URL(this.url);
                final String response = getResponse(url, captcha, ipAddress);
                final JsonParser parser = new JsonParser(response);
                result = parser.parse();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        if (isNotEmpty(this.status)) {
            this.status += "\nSuccess = " + result;
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
     * Create work status.
     *
     * @param url          the request URL.
     * @param postParams   the post parameters.
     * @param responseCode the response code.
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

    /**
     * Returns the request ip address.
     *
     * @param request the request from client.
     * @return The ip address.
     */
    private String getIpAddress(final HttpServletRequest request) {
        final String ipAddress;
        if (isNotNull(request)) {
            ipAddress = getHeaderFromRequest(request);
        } else {
            ipAddress = null;
        }
        return ipAddress;
    }

    /**
     * Returns the request header.
     *
     * @param request the request from client (newer null).
     * @return The request header.
     */
    private String getHeaderFromRequest(final HttpServletRequest request) {
        String header = request.getHeader(this.header);
        if (isEmpty(header)) {
            header = request.getRemoteAddr();
        }
        return header;
    }

    /**
     * Send request and return response.
     *
     * @param url       the request URL.
     * @param captcha   the StaticCaptcha for check.
     * @param ipAddress the request ip address.
     * @return The response to string.
     * @throws IOException If an I/O error occurs.
     */
    private String getResponse(
            final URL url,
            final String captcha,
            final String ipAddress
    ) throws IOException {
        final HttpsURLConnection connection = getConnection(url);
        final String postParams = getPostParams(captcha, ipAddress);
        writeToConnection(postParams, connection);
        setStatus(url, postParams, connection.getResponseCode());
        return readFromConnection(connection);
    }

    /**
     * Writes the incoming value to https URL connection.
     *
     * @param value      the value to write.
     * @param connection the https URL connection.
     * @throws IOException If an I/O error occurs.
     */
    private void writeToConnection(final String value, final HttpsURLConnection connection) throws IOException {
        final HttpsURLStream httpsURLStream = new HttpsURLStream(connection);
        httpsURLStream.write(value);
    }

    /**
     * Reads response from the https URL connection.
     *
     * @param connection the https URL connection.
     * @return the response.
     * @throws IOException If an I/O error occurs.
     */
    private String readFromConnection(final HttpsURLConnection connection) throws IOException {
        final HttpsURLStream httpsURLStream = new HttpsURLStream(connection);
        return httpsURLStream.read();
    }

    /**
     * Returns a https URL connection.
     *
     * @param url the request URL.
     * @return The https URL connection.
     * @throws IOException If an I/O error occurs.
     */
    private HttpsURLConnection getConnection(final URL url) throws IOException {
        final Connection connection = new Connection(
                url, this.userAgent,
                this.acceptLanguage, this.doOutput
        );
        return connection.getHttpsURLConnection();
    }

    /**
     * Returns a post params.
     *
     * @param captcha   the StaticCaptcha for check.
     * @param ipAddress the request ip address.
     * @return The post params.
     */
    private String getPostParams(final String captcha, final String ipAddress) {
        return "secret=" + this.serverKey +
                "&response=" + captcha +
                "&remoteip=" + ipAddress;
    }
}
