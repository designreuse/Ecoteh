package ua.com.ecoteh.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.response.Response;
import ua.com.ecoteh.entity.response.ResponseEditor;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.ResponseService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;

/**
 * The class implements a set of methods for working with
 * objects of the {@link Response} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/admin/response",
                "/admin/responses"
        }
)
@ComponentScan(basePackages = "ua.com.ecoteh.service.data")
@SuppressWarnings("SpringMVCViewInspection")
public final class ResponseController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for working with objects of the {@link Response} class.
     */
    private final ResponseService responseService;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric          the implementation of the {@link MainMVFabric} interface.
     * @param responseService the implementation of the {@link ResponseService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ResponseController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final ResponseService responseService
    ) {
        this.fabric = fabric;
        this.responseService = responseService;
    }

    /**
     * Returns the page to edit a response with the incoming id.
     * Request mapping: /admin/response/edit/{id},
     * where {id} is a id of a response to edit.
     * Method: GET
     *
     * @param id the id of a response to edit.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/edit/{id}",
            method = RequestMethod.GET
    )
    public ModelAndView editResponse(@PathVariable("id") final long id) {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("response", this.responseService.get(id));
        modelAndView.setViewName("response/edit");
        return modelAndView;
    }

    /**
     * Updates and save a response with the incoming id
     * and redirects by the "/responses/" URL.
     * Request mapping: /admin/response/update
     * Method: POST
     *
     * @param id        the id of a response to update.
     * @param username  the user name of a new response.
     * @param text      the text name of a new response.
     * @param validated is valid new response.
     * @return The redirect string to the "/responses/" URL.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateResponse(
            @RequestParam(value = "id", defaultValue = "0") final long id,
            @RequestParam(value = "username", defaultValue = "") final String username,
            @RequestParam(value = "text", defaultValue = "") final String text,
            @RequestParam(value = "is_valid", defaultValue = "false") final boolean validated
    ) {
        final Response response = this.responseService.get(id);
        final ResponseEditor editor = response.getEditor();
        editor.addUsername(username).addText(text).addValidated(validated);
        final Response updatedResponse = editor.update();
        this.responseService.update(updatedResponse);
        Cache.clear();
        return "redirect:/responses";
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message:
     * "GET method in "/admin/response/update" is not supported!"
     * Request mapping: /admin/response/update
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateResponse() throws IllegalMappingException {
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/admin/response/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Changes a response validation and redirects
     * by the "/responses" URL.
     * Request mapping: /admin/response/valid/{id},
     * where {id} is a id of a saving response.
     * Method: GET
     *
     * @param id a id of the response to update.
     * @return The redirect string to the "/responses/" URL.
     */
    @RequestMapping(
            value = "/valid/{id}",
            method = RequestMethod.GET
    )
    public String reverseValidResponse(@PathVariable("id") final long id) {
        final Response response = this.responseService.get(id);
        final ResponseEditor editor = response.getEditor();
        editor.reverseValidated();
        final Response updatedResponse = editor.update();
        this.responseService.update(updatedResponse);
        Cache.clear();
        return "redirect:/responses";
    }

    /**
     * Removes a response with the incoming id
     * and redirects by the "/responses" URL.
     * Request mapping: /admin/response/delete/{id},
     * where {id} is a id of a response to remove.
     * Method: GET
     *
     * @param id the id of a response to remove.
     * @return The ready object of the ModelAndView class.
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
     * Removes an all responses and redirects
     * by the "/responses" URL.
     * Request mapping: /admin/response/delete/all
     * Method: GET
     *
     * @return The redirect string to the "/responses" URL.
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
