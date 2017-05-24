package ua.com.ecoteh.service.captcha;

import ua.com.ecoteh.util.captcha.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * The class of the service layer, implements a set of methods
 * for working with google reCAPTCHA.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(basePackages = "ua.com.ecoteh.util.captcha")
public class CaptchaServiceImpl implements CaptchaService {

    /**
     * The object of google reCAPTCHA.
     */
    private final Captcha captcha;

    /**
     * Constructor.
     *
     * @param captcha a object of google reCAPTCHA.
     */
    @Autowired
    public CaptchaServiceImpl(final Captcha captcha) {
        this.captcha = captcha;
    }

    /**
     * Verifies captcha from request.
     *
     * @param request a request from client.
     * @return Returns true if captcha is verify, otherwise returns false.
     */
    @Override
    public boolean isVerify(final HttpServletRequest request) {
        return this.captcha.isVerify(request);
    }

    /**
     * Verifies captcha from ip address.
     *
     * @param captcha   a StaticCaptcha for check.
     * @param ipAddress a request ip address.
     * @return Returns true if captcha is verify, otherwise returns false.
     */
    @Override
    public boolean isVerify(
            final String captcha,
            final String ipAddress
    ) {
        return this.captcha.isVerify(captcha, ipAddress);
    }

    /**
     * Return work status.
     *
     * @return Work status.
     */
    @Override
    public String getStatus() {
        return this.captcha.getStatus();
    }
}
