package com.salimov.ecoteh.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.ID;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAdminController.getMessageController;
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
                "admin/message/all",
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
