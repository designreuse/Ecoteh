package com.salimov.yurii.controller.client;

import com.salimov.yurii.controller.other.MainController;
import com.salimov.yurii.controller.other.MainControllerTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockController.getClientMainController;

public class ClientMainControllerTest extends MainControllerTest {

    private static ClientMainController controller;

    @BeforeClass
    public static void setUp() {
        controller = getClientMainController();
    }

    @Test(expected = NullPointerException.class)
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        checkModelAndView(
                controller.sendMessage(
                        URL, NAME, PHONE, EMAIL, ANY_STRING, null
                ),
                "redirect:/",
                null
        );
    }

    @Test
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test(expected = NullPointerException.class)
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        checkModelAndView(
                controller.sendResponse(
                        NAME, ANY_STRING, null
                ),
                "redirect:/responses",
                null
        );
    }

    @Test
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Ignore
    @Override
    protected MainController getController() {
        return controller;
    }
}
