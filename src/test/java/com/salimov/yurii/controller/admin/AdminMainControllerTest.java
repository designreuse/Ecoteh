package com.salimov.yurii.controller.admin;

import com.salimov.yurii.controller.other.MainController;
import com.salimov.yurii.controller.other.MainControllerTest;
import com.salimov.yurii.util.cache.Cache;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.web.servlet.ModelAndView;

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
                        "categories",
                        "partners"
                }
        );
    }
/*
    @com.salimov.yurii.controller.Test
    public void whenSendMessageWithNullUrlThenRedirectToHomePage() {
        ModelAndView modelAndView = controller.sendMessage(null, NAME, PHONE, EMAIL, ANY_STRING, new ModelAndView());
        String viewName = "redirect:/admin/";
        checkModelAndView(modelAndView, viewName, null);
    }
*/
    @Test(expected = IllegalMappingException.class)
    public void whenSendMessageByGetMethodThenThrowsException() {
        controller.sendMessage();
    }
/*
    @com.salimov.yurii.controller.com.salimov.yurii.controller.Test
    public void whenSendResponseThenRedirectPageWithAllResponses() {
        ModelAndView modelAndView = controller.sendResponse(NAME, ANY_STRING, new ModelAndView());
        String viewName = "redirect:/responses";
        checkModelAndView(modelAndView, viewName, null);
    }

    @com.salimov.yurii.controller.com.salimov.yurii.controller.Test(expected = IllegalMappingException.class)
    public void whenSendResponseByGetMethodThenThrowsException() {
        controller.sendResponse();
    }
*/
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
