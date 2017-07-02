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
            final String captcha = getCaptchaValue(request);
            final String ip = getIpAddress(request);
            result = isVerify(captcha, ip);
        }
        return result;
    }

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha the captcha value for check.
     * @param ip      the request ip address.
     * @return true if captcha is verify, false otherwise.
     */
    @Override
    public boolean isVerify(final String captcha, final String ip) {
        this.status = "";
        boolean result = false;
        if (isNotEmpty(captcha) && isNotEmpty(ip)) {
            result = verify(captcha, ip);
        }
        addStatusSuccess(result);
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
     * Verifies captcha from ip address.
     *
     * @param captcha the captcha value for check.
     * @param ip      the request ip address.
     * @return true if captcha is verify, false otherwise.
     */
    private boolean verify(final String captcha, final String ip) {
        boolean result;
        try {
            final String response = getResponse(captcha, ip);
            result = parseSuccess(response);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        return result;
    }

    /**
     * Parse success request from the JSON object.
     *
     * @param response the response to parse.
     * @return true or false.
     */
    private boolean parseSuccess(final String response) {
        final SuccessJsonParser parser = new SuccessJsonParser(response);
        return parser.parse();
    }

    /**
     * Returns a captcha value from the incoming request.
     *
     * @param request the request from client (newer null).
     * @return the captcha value.
     */
    private String getCaptchaValue(final HttpServletRequest request) {
        return request.getParameter(this.parameter);
    }

    /**
     * Returns the request ip address.
     *
     * @param request the request from client.
     * @return The ip address.
     */
    private String getIpAddress(final HttpServletRequest request) {
        final String ip;
        if (isNotNull(request)) {
            ip = getHeaderFromRequest(request);
        } else {
            ip = "";
        }
        return ip;
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
     * @param captcha the captcha value for check.
     * @param ip      the request ip address.
     * @return The response to string.
     * @throws IOException If an I/O error occurs.
     */
    private String getResponse(final String captcha, final String ip) throws IOException {
        final HttpsURLConnection connection = getConnection();
        final String postParams = getPostParams(captcha, ip);
        writeToConnection(postParams, connection);
        setStatus(postParams, connection.getResponseCode());
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
        final HttpsURLStream stream = new HttpsURLStream(connection);
        return stream.read();
    }

    /**
     * Returns a https URL connection.
     *
     * @return The https URL connection.
     * @throws IOException If an I/O error occurs.
     */
    private HttpsURLConnection getConnection() throws IOException {
        final URL url = getUrl();
        final Connection connection = new Connection(url, this.userAgent, this.acceptLanguage, this.doOutput);
        return connection.getHttpsURLConnection();
    }

    /**
     * Creates a URL object from the String (this url) representation.
     *
     * @return The URL object.
     * @throws IOException If an I/O error occurs.
     */
    private URL getUrl() throws IOException {
        return new URL(this.url);
    }

    /**
     * Returns a post params.
     *
     * @param captcha the captcha value for check.
     * @param ip      the request ip address.
     * @return The post params.
     */
    private String getPostParams(final String captcha, final String ip) {
        return "secret=" + this.serverKey +
                "&response=" + captcha +
                "&remoteip=" + ip;
    }

    /**
     * Create work status.
     *
     * @param postParams   the post parameters.
     * @param responseCode the response code.
     */
    private void setStatus(final String postParams, final int responseCode) {
        this.status = "Sending 'POST' request to URL : " + url
                + "\nPost parameters : " + postParams
                + "\nResponse Code : " + responseCode;
    }

    /**
     * Adds the incoming success value to the status.
     *
     * @param success the success value.
     */
    private void addStatusSuccess(final boolean success) {
        if (isNotEmpty(this.status)) {
            this.status += "\nSuccess = " + success;
        }
    }
}
