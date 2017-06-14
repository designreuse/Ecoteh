package ua.com.ecoteh.mocks.service.captcha;

import ua.com.ecoteh.service.captcha.CaptchaService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockCaptchaServiceImpl {
    
    private static CaptchaService service;

    MockCaptchaServiceImpl() {
        service = mock(CaptchaService.class);
    }

    public static CaptchaService getCaptchaService() {
        if (service == null) {
            final MockCaptchaServiceImpl mockCaptchaService = new MockCaptchaServiceImpl();
            mockCaptchaService.create();
        }
        return service;
    }
    
    CaptchaService create() {
        initIsVerify();
        initGetStatus();
        return service;
    }

    private void initIsVerify() {
        final HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        assertFalse(service.isVerify(servletRequest));
        when(service.isVerify(null)).thenReturn(false);
        when(service.isVerify(servletRequest)).thenReturn(true);
        when(service.isVerify(ANY_STRING, ANY_STRING)).thenReturn(false);
    }

    private void initGetStatus() {
        when(service.getStatus()).thenReturn(ANY_STRING);
    }
}
