package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getResponseController;
import static org.junit.Assert.assertEquals;

public class ResponseControllerTest {

    private static ResponseController controller;

    @BeforeClass
    public static void setUp() {
        controller = getResponseController();
    }

    @Test
    public void whenReverseValidResponseThenReturnSomeModelAndView() {
        String viewName = controller.reverseValidResponse(ID);
        assertEquals(viewName, "redirect:/responses");
    }

    @Test
    public void whenDeleteResponseByIdThenReturnSomeModelAndView() {
        String viewName = controller.deleteResponseById(ID);
        assertEquals(viewName, "redirect:/responses");
    }

    @Test
    public void whenDeleteAllResponsesThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllResponses();
        assertEquals(viewName, "redirect:/responses");
    }
}
