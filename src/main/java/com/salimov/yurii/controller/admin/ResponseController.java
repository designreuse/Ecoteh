package com.salimov.yurii.controller.admin;

import com.salimov.yurii.entity.Response;
import com.salimov.yurii.service.data.interfaces.ResponseService;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working
 * with main ModelAndView objects and object of the {@link Response}
 * class for admins. Class methods create and return modelsAndView,
 * depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Response
 * @see ResponseService
 */
@Controller
@RequestMapping(value = "/admin/response")
@ComponentScan(basePackages = "com.salimov.yurii.service.data")
@SuppressWarnings("SpringMVCViewInspection")
public class ResponseController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Response} class.
     *
     * @see ResponseService
     */
    private final ResponseService responseService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param responseService a implementation of the {@link ResponseService}
     *                        interface.
     * @see ResponseService
     */
    @Autowired
    public ResponseController(final ResponseService responseService) {
        this.responseService = responseService;
    }

    /**
     * Changes response validation and redirects by url /admin/responses.
     * Request mapping: /admin/response/valid/{id}
     * Method: GET
     *
     * @param id           a id of the response to update.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/valid/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView reverseValidResponse(
            @PathVariable("id") final long id,
            final ModelAndView modelAndView
    ) {
        final Response response = this.responseService.get(id);
        response.reverseValidated();
        this.responseService.update(response);
        Cache.clear();
        modelAndView.setViewName("redirect:/responses");
        return modelAndView;
    }

    /**
     * Removes response with url and redirects by url /admin/responses.
     * Request mapping: /admin/response/delete/{id}
     * Method: GET
     *
     * @param id           a id of the response to remove.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView deleteResponse(
            @PathVariable("id") final long id,
            final ModelAndView modelAndView
    ) {
        this.responseService.remove(id);
        Cache.clear();
        modelAndView.setViewName("redirect:/responses");
        return modelAndView;
    }

    /**
     * Removes all responses and redirects by url /admin/responses.
     * Request mapping: /admin/response/delete/all
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Response
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public ModelAndView deleteAllResponses(final ModelAndView modelAndView) {
        this.responseService.removeAll();
        Cache.clear();
        modelAndView.setViewName("redirect:/responses");
        return modelAndView;
    }
}
