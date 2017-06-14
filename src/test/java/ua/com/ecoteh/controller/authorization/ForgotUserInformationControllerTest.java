package ua.com.ecoteh.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.captcha.CaptchaService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.NAME;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.captcha.MockCaptchaServiceImpl.getCaptchaService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;
import static ua.com.ecoteh.mocks.service.data.MockServices.getUserService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;
import static ua.com.ecoteh.mocks.service.message.MockSenderService.getSenderService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ForgotUserInformationControllerTest {

    private static ForgotUserInformationController controller;

    @BeforeClass
    public static void beforeClass() {
        final MainMVFabric fabric = getCacheMVFabric();
        final UserService userService = getUserService();
        final CompanyService companyService = getCompanyService();
        final SenderService senderService = getSenderService();
        final CaptchaService captchaService = getCaptchaService();
        controller = new ForgotUserInformationController(
                fabric, userService, companyService, senderService, captchaService
        );
    }

    @Test
    public void whenGetForgotPageThenReturnNotNull() {
        final String viewName = "login/forgot_form";
        final String[] keys = { "main_company", "categories", "favicon", "username", "is_captcha", "is_forgot" };
        final ModelAndView modelAndView = controller.getForgotPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenForgotUserByUsernameThenReturnNotNull() {
        final String viewName = "login/forgot_form";
        final String[] keys = { "main_company", "categories", "favicon", "username", "is_captcha", "is_forgot" };
        final HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        final ModelAndView modelAndView = controller.forgotUserByUsername(NAME, servletRequest);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenForgotUserByUsernameByGetMethodThenReturnRedirect() {
        final String expectedViewName = controller.forgotUserByUsername();
        final String actualViewName = "redirect:/login";
        assertEquals(expectedViewName, actualViewName);
    }
}