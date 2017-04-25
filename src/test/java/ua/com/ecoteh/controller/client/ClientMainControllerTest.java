package ua.com.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.controller.MockClientController;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

public class ClientMainControllerTest extends MainControllerTest {

    private static ClientMainController controller;

    @BeforeClass
    public static void setUp() {
        controller = MockClientController.getClientMainController();
    }

    @Test(expected = NullPointerException.class)
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        checkModelAndView(
                controller.sendMessage(
                        MockConstants.URL, MockConstants.NAME, MockConstants.PHONE, MockConstants.EMAIL, MockConstants.ANY_STRING, null
                ),
                "redirect:/",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test(expected = NullPointerException.class)
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        checkModelAndView(
                controller.sendResponse(
                        MockConstants.NAME, MockConstants.ANY_STRING, null
                ),
                "redirect:/responses",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Ignore
    @Override
    protected MainController getController() {
        return controller;
    }
}
