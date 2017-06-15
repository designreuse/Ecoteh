package ua.com.ecoteh.controller.authorization;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.service.data.UserService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The authorization controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@ComponentScan(basePackages = "ua.com.ecoteh.service.fabrica")
public class AuthorizationController {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(AuthorizationController.class);

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link UserEntity} class.
     */
    private final UserService userService;

    /**
     * Constructor.
     *
     * @param fabric      the implementation of the {@link MainMVFabric} interface.
     * @param userService the implementation of the {@link UserService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AuthorizationController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final UserService userService
    ) {
        this.fabric = fabric;
        this.userService = userService;
    }

    /**
     * Login user.
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public ModelAndView loginPage() {
        ModelAndView modelAndView;
        if (this.userService.isAuthenticatedUser()) {
            modelAndView = new ModelAndView("redirect:/admin/menu");
        } else {
            try {
                modelAndView = this.fabric.getDefaultModelAndView();
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
                ex.printStackTrace();
                modelAndView = new ModelAndView();
            }
            modelAndView.setViewName("login/login");
        }
        return modelAndView;
    }

    /**
     * Logout user and redirects to /login?logout.
     *
     * @param request  the implementation of the interface to provide
     *                 request information for HTTP servlets.
     * @param response the implementation of the interface to provide
     *                 response information for HTTP servlets.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/logout",
            method = RequestMethod.GET
    )
    public String logoutPage(final HttpServletRequest request, final HttpServletResponse response) {
        final String viewName;
        if (this.userService.isAuthenticatedUser()) {
            logout(request, response);
            Cache.removeAll("Admin");
            viewName = "redirect:/login?logout";
        } else {
            viewName = "redirect:/login";
        }
        return viewName;
    }

    /**
     * Logout user.
     *
     * @param request  the implementation of the interface to provide
     *                 request information for HTTP servlets.
     * @param response the implementation of the interface to provide
     *                 response information for HTTP servlets.
     */
    private void logout(final HttpServletRequest request, final HttpServletResponse response) {
        final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutHandler.logout(request, response, authentication);
    }
}
