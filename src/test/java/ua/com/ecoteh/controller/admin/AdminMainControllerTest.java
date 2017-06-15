package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.controller.client.MainController;
import ua.com.ecoteh.controller.client.MainControllerTest;
import ua.com.ecoteh.service.data.CompanyService;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.sender.SenderService;

import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.*;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;
import static ua.com.ecoteh.mocks.service.message.MockSenderService.getSenderService;

public class AdminMainControllerTest extends MainControllerTest {

    private static AdminMainController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final CompanyService companyService = getCompanyService();
        final UserService userService = getUserService();
        final ResponseService responseService = getResponseService();
        final MessageService messageService = getMessageService();
        final SenderService senderService = getSenderService();
        controller = new AdminMainController(
                fabric, companyService, userService, responseService,
                messageService, senderService
        );
    }

    @Test
    public void whenGetAdminMenuThenReturnSomeModelAndView() {
        final String viewName = "admin/menu";
        final String[] keys = { "main_company", "categories", "favicon", "authorized_user" };
        final ModelAndView modelAndView = controller.getAdminMenu();
        checkModelAndView(modelAndView, viewName, keys);
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
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        final String viewName = "response/all";
        final String[] keys = { "main_company", "categories", "favicon", "responses", "revers", "captcha" };
        final ModelAndView modelAndView = controller.sendResponse(NAME, TEXT);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Override
    protected MainController getController() {
        return controller;
    }

    private void whenSendMessageWithIncomingUrlThenRedirectToHomePage(final String url) {
        final String viewName = "home/index";
        final String[] keys = { "main_company", "categories", "company",
                "print_companies", "print_responses", "favicon" };
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT);
        checkModelAndView(modelAndView, viewName, keys);
    }

    private void whenSendMessageWithIncomingUrlThenRedirectToContactsPage(final String url) {
        final String viewName = "company/main_contacts";
        final String[] keys = { "main_company", "categories", "favicon", "company", "captcha" };
        final ModelAndView modelAndView = controller.sendMessage(url, NAME, PHONE, EMAIL, TEXT);
        checkModelAndView(modelAndView, viewName, keys);
    }
}
