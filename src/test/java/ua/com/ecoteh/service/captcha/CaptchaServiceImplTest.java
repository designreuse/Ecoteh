package ua.com.ecoteh.service.captcha;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.util.captcha.Captcha;
import ua.com.ecoteh.util.captcha.ReCaptcha;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class CaptchaServiceImplTest {

    private static CaptchaService service;

    @BeforeClass
    public static void setUp() {
        final Captcha captcha = new ReCaptcha(
                "userAgent", "acceptLanguage", true,
                "url", "serverKey", "parameter", "header"
        );
        service = new CaptchaServiceImpl(captcha);
    }

    @Test
    public void isVerifyWithMockHttpServletRequestThenReturnFalse() {
        final HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        assertFalse(service.isVerify(servletRequest));
    }

    @Test
    public void isVerifyWithUnknownParametersThenReturnFalse() {
        assertFalse(service.isVerify(ANY_STRING, ANY_STRING));
    }

    @Test
    public void whenGetStatusThenReturnSomeString() {
        assertNotNull(service.getStatus());
    }
}
