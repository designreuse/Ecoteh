package com.salimov.yurii.controller.admin;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAdminController.getMessageController;

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
        checkModelAndView(
                controller.deleteMessageById(ID, new ModelAndView()),
                "redirect:/admin/messages",
                null
        );
    }

    @Test
    public void whenDeleteAllMessagesThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.deleteAllMessages(new ModelAndView()),
                "redirect:/admin/messages",
                null
        );
    }
}
