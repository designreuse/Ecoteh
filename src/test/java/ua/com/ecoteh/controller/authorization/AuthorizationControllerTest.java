package ua.com.ecoteh.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getUserService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

public class AuthorizationControllerTest {

    private static AuthorizationController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final UserService userService = getUserService();
        controller = new AuthorizationController(fabric, userService);
    }

    @Test
    public void whenLoginPageThenReturnSomeModelAndView() {
        final String viewName = "login/login";
        final String[] keys = null;
        final ModelAndView modelAndView = controller.loginPage();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenLogoutPageeThenReturnSomeModelAndView() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final String expectedViewName = controller.logoutPage(request, response);
        final String actualViewName = "redirect:/login";
        assertEquals(expectedViewName, actualViewName);
    }
}
