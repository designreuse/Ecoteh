package com.salimov.ecoteh.util.captcha;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface describes a set of methods
 * for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Captcha {

    /**
     * Verifies captcha from request.
     *
     * @param request a request from client.
     * @return {@code true} if captcha is verify,
     * {@code false} otherwise.
     */
    boolean isVerify(final HttpServletRequest request);

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return {@code true} if captcha is verify,
     * {@code false} otherwise.
     */
    boolean isVerify(
            final String captcha,
            final String ipAddress
    );

    /**
     * Return a work status.
     *
     * @return The work status.
     */
    String getStatus();
}
