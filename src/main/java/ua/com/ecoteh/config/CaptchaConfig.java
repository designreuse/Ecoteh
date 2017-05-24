package ua.com.ecoteh.config;

import ua.com.ecoteh.util.captcha.Captcha;
import ua.com.ecoteh.util.captcha.ReCaptcha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class describes a google captcha configurations.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Configuration
@PropertySource("classpath:captcha.properties")
public class CaptchaConfig {

    /**
     * The implementation of the interface describes a set of methods
     * for working with google reCAPTCHA.
     */
    private static Captcha captcha;

    /**
     * The reCAPTCHA user agent.
     */
    @Value("${captcha.user-agent}")
    private String userAgent;

    /**
     * The reCAPTCHA accept language.
     */
    @Value("${captcha.accept-language}")
    private String acceptLanguage;

    /**
     * The reCAPTCHA do output.
     */
    @Value("${captcha.do-output}")
    private boolean doOutput;

    /**
     * The reCAPTCHA url.
     */
    @Value("${captcha.url}")
    private String url;

    /**
     * The reCAPTCHA server key.
     */
    @Value("${captcha.server-key}")
    private String serverKey;

    /**
     * The reCAPTCHA request param.
     */
    @Value("${captcha.parameter}")
    private String parameter;

    /**
     * The reCAPTCHA header param.
     */
    @Value("${captcha.header}")
    private String header;

    /**
     * Get object for working with google reCAPTCHA.
     *
     * @return The captcha with configuration.
     */
    @Bean
    public Captcha captcha() {
        if (isNull(captcha)) {
            initCaptcha();
        }
        return captcha;
    }

    /**
     * Initializes the captcha object.
     */
    private void initCaptcha() {
        captcha = new ReCaptcha(
                this.userAgent,
                this.acceptLanguage,
                this.doOutput,
                this.url,
                this.serverKey,
                this.parameter,
                this.header
        );
    }
}
