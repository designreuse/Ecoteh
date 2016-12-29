package com.salimov.yurii.service.captcha;

import javax.servlet.http.HttpServletRequest;

/**
 * The class of the service layer, describes a set of methods
 * for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see CaptchaServiceImpl
 * @see com.salimov.yurii.util.captcha.Captcha
 */
public interface CaptchaService {

    /**
     * Verifies captcha from request.
     *
     * @param request a request from client.
     * @return Returns true if captcha is verify, otherwise returns false.
     */
    boolean isVerify(final HttpServletRequest request);

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return Returns true if captcha is verify, otherwise returns false.
     */
    boolean isVerify(final String captcha, final String ipAddress);

    /**
     * Return work status.
     *
     * @return Work status.
     */
    String getStatus();
}
