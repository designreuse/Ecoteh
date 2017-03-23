package com.salimov.ecoteh.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockAuthorizationController.getAuthorizationController;
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
                "login/login",
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
