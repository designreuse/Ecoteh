package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.controller.client.MainController;
import com.salimov.ecoteh.controller.client.MainControllerTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getAdminMainController;

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
                new String[]{"user", "main_company", "categories"}
        );
    }

    @Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        checkModelAndView(
                controller.sendMessage(
                        URL, NAME, PHONE, EMAIL, ANY_STRING
                ),
                "redirect:" + URL,
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
                controller.sendResponse(NAME, ANY_STRING),
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