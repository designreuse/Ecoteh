package ua.com.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.captcha.CaptchaService;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.captcha.MockCaptchaServiceImpl.getCaptchaService;
import static ua.com.ecoteh.mocks.service.data.MockServices.*;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;
import static ua.com.ecoteh.mocks.service.message.MockSenderService.getSenderService;

public class ClientMainControllerTest extends MainControllerTest {

    private static ClientMainController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final CompanyService companyService = getCompanyService();
        final UserService userService = getUserService();
        final ResponseService responseService = getResponseService();
        final MessageService messageService = getMessageService();
        final SenderService senderService = getSenderService();
        final CaptchaService captchaService = getCaptchaService();
        controller = new ClientMainController(
                fabric, companyService, userService, responseService,
                messageService, senderService, captchaService
        );
    }

    @Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        final String url = null;
        whenSendMessageWithIncomingUrlThenRedirectToHomePage(url);
    }

    @Test
    public void whenSendMessageWithEmptyUrlThenRedirectToHomePage() {
        final String url = "";
        whenSendMessageWithIncomingUrlThenRedirectToHomePage(url);
    }

    @Test
    public void whenSendMessageWithIndexUrlThenRedirectToHomePage() {
        final String url = "/index";
        whenSendMessageWithIncomingUrlThenRedirectToHomePage(url);
    }

    @Test
    public void whenSendMessageWithHomeUrlThenRedirectToHomePage() {
        final String url = "/home";
        whenSendMessageWithIncomingUrlThenRedirectToHomePage(url);
    }

    @Test
    public void whenSendMessageWithContactsUrlThenRedirectToContactsPage() {
        final String url = "/contacts";
        whenSendMessageWithIncomingUrlThenRedirectToContactsPage(url);
    }

    @Test
    public void whenSendMessageWithAddressUrlThenRedirectToContactsPage() {
        final String url = "/address";
        whenSendMessageWithIncomingUrlThenRedirectToContactsPage(url);
    }

    @Test
    public void whenSendMessageWithAnotherUrlThenRedirectToThisUrlPage() {
        final String url = ANY_STRING;
        final String viewName = "redirect:" + url;
        final String[] keys = {};
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT, request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers", "is_captcha" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ModelAndView modelAndView = controller.sendResponse(NAME, TEXT, request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Ignore
    @Override
    protected ClientMainController getController() {
        return controller;
    }

    private void whenSendMessageWithIncomingUrlThenRedirectToHomePage(final String url) {
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "company",
                "print_companies", "print_responses", "favicon", "is_captcha" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT, request);
        checkModelAndView(modelAndView, viewName, keys);
    }

    private void whenSendMessageWithIncomingUrlThenRedirectToContactsPage(final String url) {
        final String viewName = "company/main_contacts";
        final String[] keys = { "main_company", "categories", "favicon", "company", "is_captcha" };
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT, request);
        checkModelAndView(modelAndView, viewName, keys);
    }
}
