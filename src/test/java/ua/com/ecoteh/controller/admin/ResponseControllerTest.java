package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.enity.MockModels.getResponse;
import static ua.com.ecoteh.mocks.service.data.MockServices.getResponseService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

public class ResponseControllerTest {

    private static ResponseController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getMainMVFabric();
        final ResponseService responseService = getResponseService();
        controller = new ResponseController(fabric, responseService);
    }

    @Test
    public void whenEditResponsePageThenReturnSomeModelAndView() {
        final String viewName = "response/edit";
        final String[] keys = { "main_company", "categories", "favicon", "response" };
        final ModelAndView modelAndView = controller.editResponse(ID);
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenUpdateResponseThenReturnSomeModelAndView() {
        final Response response = getResponse();
        final String actualRedirect = controller.updateResponse(
                response.getId(), response.getUsername(),
                response.getText(), response.isValidated()
        );
        final String expectedRedirect = "redirect:/responses";
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test(expected = IllegalMappingException.class)
    public void whenUpdateResponseByGetMethodThenThrowIllegalMappingException() {
        controller.updateResponse();
    }

    @Test
    public void whenReverseValidResponseThenReturnSomeModelAndView() {
       final String expectedRedirect = "redirect:/responses";
        final String actualRedirect = controller.reverseValidResponse(ID);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteResponseByIdThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/responses";
        final String actualRedirect = controller.deleteResponseById(ID);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllResponsesThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/responses";
        final String actualRedirect = controller.deleteAllResponses();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
