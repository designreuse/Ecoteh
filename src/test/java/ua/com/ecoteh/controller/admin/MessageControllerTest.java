package ua.com.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.controller.MockAdminController.getMessageController;
import static org.junit.Assert.assertEquals;

public class MessageControllerTest {

    private static MessageController controller;

    @BeforeClass
    public static void setUp() {
        controller = getMessageController();
    }

    @Test
    public void whenGetMessagesPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getMessagesPage(),
                "message/all",
                new String[]{"main_company", "categories", "messages"}
        );
    }

    @Test
    public void whenDeleteMessageByIdThenReturnSomeModelAndView() {
        String viewName = controller.deleteMessageById(ID);
        assertEquals(viewName, "redirect:/admin/messages");
    }

    @Test
    public void whenDeleteAllMessagesThenReturnSomeModelAndView() {
        String viewName = controller.deleteAllMessages();
        assertEquals(viewName, "redirect:/admin/messages");
    }
}
