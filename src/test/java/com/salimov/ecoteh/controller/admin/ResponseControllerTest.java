package com.salimov.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.ecoteh.mocks.MockConstants.ID;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getResponseController;

public class ResponseControllerTest {

    private static ResponseController controller;

    @BeforeClass
    public static void setUp() {
        controller = getResponseController();
    }

    @Test
    public void whenReverseValidResponseThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.reverseValidResponse(ID, new ModelAndView()),
                "redirect:/responses",
                null
        );
    }

    @Test
    public void whenDeleteResponseByIdThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteResponseById(ID, new ModelAndView()),
                "redirect:/responses",
                null
        );
    }

    @Test
    public void whenDeleteAllResponsesThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllResponses(new ModelAndView()),
                "redirect:/responses",
                null
        );
    }
}
