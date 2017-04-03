package com.salimov.ecoteh.util.captcha;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class ReCaptchaTest {

    private Captcha captcha;

    @Before
    public void beforeTests() {
        this.captcha = new ReCaptcha(
                "userAgent",
                "acceptLanguage",
                true,
                "url",
                "serverKey",
                "parameter",
                "header"
        );
    }

    @Test
    public void whenIsVerifyNullHttpServletRequestThenReturnFalse() {
        assertFalse(this.captcha.isVerify(null));
    }

    @Test
    public void whenIsVerifyMockHttpServletRequestThenReturnFalse() {
        assertFalse(
                this.captcha.isVerify(
                        mock(HttpServletRequest.class)
                )
        );
    }

    @Test
    public void whenGetStatusThenReturnIt() {
        assertNull(this.captcha.getStatus());
    }
}
