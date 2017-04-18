package com.salimov.ecoteh.controller.admin;

import com.salimov.ecoteh.entity.Response;
import com.salimov.ecoteh.service.data.interfaces.ResponseService;
import com.salimov.ecoteh.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Response} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/admin/response",
                "/admin/responses"
        }
)
@ComponentScan(basePackages = "com.salimov.ecoteh.service.data")
@SuppressWarnings("SpringMVCViewInspection")
public class ResponseController {

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Response} class.
     */
    private final ResponseService responseService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param responseService a implementation of the {@link ResponseService} interface.
     */
    @Autowired
    public ResponseController(final ResponseService responseService) {
        this.responseService = responseService;
    }

    /**
     * Changes response validation and redirects by URL /admin/responses.
     * Request mapping: /admin/response/valid/{id}
     * Method: GET
     *
     * @param id           a id of the response to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/valid/{id}",
            method = RequestMethod.GET
    )
    public String reverseValidResponse(@PathVariable("id") final long id) {
        final Response response = this.responseService.get(id);
        response.reverseValidated();
        this.responseService.update(response);
        Cache.clear();
        return "redirect:/responses";
    }

    /**
     * Removes response with id and redirects by URL /admin/responses.
     * Request mapping: /admin/response/delete/{id}
     * Method: GET
     *
     * @param id           a id of the response to remove.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/{id}",
            method = RequestMethod.GET
    )
    public String deleteResponseById(@PathVariable("id") final long id) {
        this.responseService.remove(id);
        Cache.clear();
        return "redirect:/responses";
    }

    /**
     * Removes all responses and redirects by URL /admin/responses.
     * Request mapping: /admin/response/delete/all
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/delete/all",
            method = RequestMethod.GET
    )
    public String deleteAllResponses() {
        this.responseService.removeAll();
        Cache.clear();
        return "redirect:/responses";
    }
}
