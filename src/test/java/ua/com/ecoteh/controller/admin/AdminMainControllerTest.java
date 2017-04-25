package ua.com.ecoteh.controller.admin;

import ua.com.ecoteh.controller.client.MainController;
import ua.com.ecoteh.controller.client.MainControllerTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import ua.com.ecoteh.mocks.MockConstants;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getAdminMainController;

public class AdminMainControllerTest extends MainControllerTest {

    private static AdminMainController controller;

    @BeforeClass
    public static void setUp() {
        controller = getAdminMainController();
    }

    @Test
    public void whenGetAdminMenuThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getAdminMenu(),
                "admin/menu/menu",
                new String[] { "authorized_user", "main_company", "categories", "favicon" }
        );
    }

    @Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        checkModelAndView(
                controller.sendMessage(
                        MockConstants.URL, MockConstants.NAME, MockConstants.PHONE, MockConstants.EMAIL, MockConstants.ANY_STRING
                ),
                "redirect:" + MockConstants.URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        checkModelAndView(
                controller.sendResponse(MockConstants.NAME, MockConstants.ANY_STRING),
                "client/response/all", null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Override
    protected MainController getController() {
        return controller;
    }
}
