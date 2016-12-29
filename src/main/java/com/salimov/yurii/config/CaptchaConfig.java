package com.salimov.yurii.config;

import com.salimov.yurii.util.captcha.Captcha;
import com.salimov.yurii.util.captcha.CaptchaImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
@Configuration
@PropertySource("classpath:captcha.properties")
public class CaptchaConfig {

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
        return new CaptchaImpl(
                this.userAgent,
                this.acceptLanguage,
                this.url,
                this.serverKey,
                this.parameter,
                this.header
        );
    }
}
