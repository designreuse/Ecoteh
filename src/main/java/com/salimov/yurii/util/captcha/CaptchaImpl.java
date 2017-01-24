package com.salimov.yurii.util.captcha;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods
 * for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * @param userAgent      a captcha user agent.
     * @param acceptLanguage a captcha accept language.
     * @param doOutput       a do output.
     * @param url            a captcha url.
     * @param serverKey      a captcha server key.
     * @param parameter      a captcha request param.
     * @param header         a captcha header param.
     */
    public CaptchaImpl(
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
     * @param request a request from client.
     * @return {@code true} if captcha is verify, {@code false} otherwise.
     */
    @Override
    public boolean isVerify(final HttpServletRequest request) {
        this.status = null;
        boolean result = false;
        if (request != null) {
            result = isVerify(
                    request.getParameter(this.parameter),
                    getIpAddress(request)
            );
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
    public boolean isVerify(
            final String captcha,
            final String ipAddress
    ) {
        this.status = null;
        boolean result = false;
        if (isNotBlank(captcha) && isNotBlank(ipAddress)) {
            try {
                result = new JsonParser(
                        getResponse(
                                new URL(this.url),
                                captcha,
                                ipAddress
                        )
                ).parse();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (isNotBlank(this.status)) {
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

    /**
     * Returns the request ip address.
     *
     * @param request a request from client.
     * @return The ip address.
     */
    private String getIpAddress(final HttpServletRequest request) {
        String ipAddress = request.getHeader(this.header);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * Send request and return response.
     *
     * @param url       a request url.
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return The response to string.
     * @throws IOException If an I/O error occurs.
     */
    private String getResponse(
            final URL url,
            final String captcha,
            final String ipAddress
    ) throws IOException {
        final HttpsURLConnection connection = new Connection(
                url,
                this.userAgent,
                this.acceptLanguage,
                this.doOutput
        ).getHttpsURLConnection();
        final String postParams = "secret=" + this.serverKey
                + "&response=" + captcha
                + "&remoteip=" + ipAddress;
        Stream.write(
                postParams,
                connection.getOutputStream()
        );
        setStatus(
                url,
                postParams,
                connection.getResponseCode()
        );
        return Stream.read(
                connection.getInputStream()
        );
    }
}
