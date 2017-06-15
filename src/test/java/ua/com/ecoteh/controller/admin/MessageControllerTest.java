package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.MessageService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getMessageService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

public class MessageControllerTest {

    private static MessageController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final MessageService messageService = getMessageService();
        controller = new MessageController(fabric, messageService);
    }

    @Test
    public void whenGetMessagesPageThenReturnSomeModelAndView() {
        final String viewName = "message/all";
        final String[] keys = { "main_company", "categories", "favicon", "messages" };
        final ModelAndView modelAndView = controller.getMessagesPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenDeleteMessageByIdThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/admin/messages";
        final String actualRedirect = controller.deleteMessageById(ID);
        assertEquals(actualRedirect, expectedRedirect);
    }

    @Test
    public void whenDeleteAllMessagesThenReturnSomeModelAndView() {
        final String expectedRedirect = "redirect:/admin/messages";
        final String actualRedirect = controller.deleteAllMessages();
        assertEquals(actualRedirect, expectedRedirect);
    }
}
