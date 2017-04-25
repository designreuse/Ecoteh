package ua.com.ecoteh.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.mocks.controller.MockAuthorizationController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AuthorizationControllerTest {

    private static AuthorizationController controller;

    @BeforeClass
    public static void setUp() {
        controller = MockAuthorizationController.getAuthorizationController();
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
        String viewName = controller.logoutPage(
                mock(HttpServletRequest.class),
                mock(HttpServletResponse.class)
        );
        assertEquals(viewName, "redirect:/login?logout");
    }
}
