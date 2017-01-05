package com.salimov.yurii.controller.client;

import com.salimov.yurii.controller.other.MainController;
import com.salimov.yurii.controller.other.MainControllerTest;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;

import static com.salimov.yurii.mocks.controller.MockController.getClientMainController;

public class ClientMainControllerTest extends MainControllerTest {

    private static ClientMainController controller;

    @BeforeClass
    public static void setUp() {
        controller = getClientMainController();
    }

    /*@Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        ModelAndView modelAndView = controller.sendMessage(null, NAME, PHONE, EMAIL, ANY_STRING, new ModelAndView(), null);
        String viewName = "redirect:/";
        checkModelAndView(modelAndView, viewName, null);
    }*/

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    /*@Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        ModelAndView modelAndView = controller.sendResponse(NAME, ANY_STRING, new ModelAndView(), null);
        String viewName = "redirect:/responses";
        checkModelAndView(modelAndView, viewName, null);
    }*/

    /*@Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }*/

    @Ignore
    @Override
    protected MainController getController() {
        return controller;
    }
}
