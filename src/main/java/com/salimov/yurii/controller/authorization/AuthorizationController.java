package com.salimov.yurii.controller.authorization;

import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The authorization controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
public class AuthorizationController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     *
     * @param fabric a implementation of the {@link MainMVFabric} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AuthorizationController(final MainMVFabric fabric) {
        this.fabric = new CacheMVFabricImpl(fabric);
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public ModelAndView loginPage() {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    /**
     * Logout user and redirects to /login?logout.
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @param request      a implementation of the interface to provide
     *                     request information for HTTP servlets.
     * @param response     a implementation of the interface to provide
     *                     response information for HTTP servlets.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/logout",
            method = RequestMethod.GET
    )
    public ModelAndView logoutPage(
            final ModelAndView modelAndView,
            final HttpServletRequest request,
            final HttpServletResponse response
    ) {
        new SecurityContextLogoutHandler().logout(
                request, response,
                SecurityContextHolder.getContext().getAuthentication()
        );
        Cache.removeAll("Admin");
        modelAndView.setViewName("redirect:/login?logout");
        return modelAndView;
    }
}
