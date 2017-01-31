package com.salimov.yurii.controller.other;

import com.salimov.yurii.config.DefaultConfig;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The default configuration controller.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DefaultConfig
 */
@Controller
public class WorkController {

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
                request,
                response,
                SecurityContextHolder.getContext()
                        .getAuthentication()
        );
        Cache.removeAll("Admin");
        modelAndView.setViewName("redirect:/login?logout");
        return modelAndView;
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception sender:
     * "You do not have sufficient permissions to access this page."
     * Request mapping: /illegal_access_exception
     * Method: POST
     *
     * @throws IllegalAccessException throws when the not authorized user
     *                                is tries to use private methods.
     */
    @RequestMapping(
            value = "/illegal_access_exception",
            method = RequestMethod.GET
    )
    public void getIllegalAccessException() throws IllegalAccessException {
        throw new IllegalAccessException(
                "You do not have sufficient permissions to access this page."
        );
    }

    /**
     * Enables the site.
     * Request mapping: /superman/on
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/superman/on",
            method = RequestMethod.GET
    )
    public ModelAndView on(final ModelAndView modelAndView) {
        DefaultConfig.superOn();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    /**
     * Disables the site.
     * Request mapping: /superman/off
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/superman/off",
            method = RequestMethod.GET
    )
    public ModelAndView off(final ModelAndView modelAndView) {
        DefaultConfig.superOff();
        Cache.clear();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    /**
     * Clears the cache.
     * Request mapping: /superman/clear
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/superman/clear",
            method = RequestMethod.GET
    )
    public ModelAndView clearCache(final ModelAndView modelAndView) {
        Cache.clear();
        System.gc();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
