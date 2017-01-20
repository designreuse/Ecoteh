package com.salimov.yurii.controller.admin;

import com.salimov.yurii.controller.other.MainController;
import com.salimov.yurii.controller.other.MainControllerTest;
import com.salimov.yurii.util.cache.Cache;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockController.getAdminMainController;
import static org.junit.Assert.assertEquals;

public class AdminMainControllerTest extends MainControllerTest {

    private static AdminMainController controller;

    @BeforeClass
    public static void setUp() {
        controller = getAdminMainController();
    }

    @Test
    public void whenGetAdminMenuThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.getAdminMenu(),
                "admin/menu/menu_page",
                new String[]{
                        "user",
                        "is_enabled",
                        "main_company",
                        "categories"
                }
        );
    }

    @Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        checkModelAndView(
                controller.sendMessage(
                        URL,
                        NAME, PHONE, EMAIL,
                        ANY_STRING
                ),
                "redirect:" + URL,
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }

    @Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        checkModelAndView(
                controller.sendResponse(NAME, ANY_STRING),
                "client/response/all_page",
                null
        );
    }

    @Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }

    @Test
    public void whenSiteOffThenThenRedirectToAdminMenu() {
        checkModelAndView(
                controller.siteOff(
                        new ModelAndView()
                ),
                "redirect:/admin/menu",
                null
        );
    }

    @Test
    public void whenSiteOnThenThenClearChacheRedirectToAdminMenu() {
        checkModelAndView(
                controller.siteOff(
                        new ModelAndView()
                ),
                "redirect:/admin/menu",
                null
        );
        assertEquals(Cache.getSize(), 0);
    }

    @Override
    protected MainController getController() {
        return controller;
    }
}
