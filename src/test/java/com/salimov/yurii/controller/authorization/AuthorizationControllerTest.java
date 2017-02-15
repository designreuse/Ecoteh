package com.salimov.yurii.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.salimov.yurii.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.yurii.mocks.controller.MockAuthorizationController.getAuthorizationController;
import static org.mockito.Mockito.mock;

public class AuthorizationControllerTest {

    private static AuthorizationController controller;

    @BeforeClass
    public static void setUp() {
        controller = getAuthorizationController();
    }

    @Test
    public void whenLoginPageThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.loginPage(),
                "login/login_page",
                null
        );
    }

    @Test
    public void whenLogoutPageeThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.logoutPage(
                        new ModelAndView(),
                        mock(HttpServletRequest.class),
                        mock(HttpServletResponse.class)
                ),
                "redirect:/login?logout",
                null
        );
    }
}
