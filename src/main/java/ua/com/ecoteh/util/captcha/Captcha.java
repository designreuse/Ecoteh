package ua.com.ecoteh.util.captcha;

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
     * @return true if captcha is verify, false otherwise.
     */
    boolean isVerify(HttpServletRequest request);

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   the StaticCaptcha for check.
     * @param ipAddress the request ip address.
     * @return true if captcha is verify, false otherwise.
     */
    boolean isVerify(String captcha, String ipAddress);

    /**
     * Return a work status.
     *
     * @return The work status.
     */
    String getStatus();
}
