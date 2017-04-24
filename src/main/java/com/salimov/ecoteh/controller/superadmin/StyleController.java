package com.salimov.ecoteh.controller.superadmin;

import com.salimov.ecoteh.service.data.StyleService;
import com.salimov.ecoteh.service.fabrica.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.MainMVFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with CSS styles.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(
        value = {
                "/superadmin/style",
                "/superadmin/styles"
        }
)
@ComponentScan(
        basePackages = {
                "com.salimov.ecoteh.service.fabrica",
                "com.salimov.ecoteh.util.properties"
        }
)
@SuppressWarnings("SpringMVCViewInspection")
public class StyleController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;

    /**
     * The interface of the service layer, describes a set of methods
     * for working with CSS styles.
     */
    private final StyleService styleService;

    /**
     * Constructor.
     *
     * @param fabric       a implementation of the {@link MainMVFabric} interface.
     * @param styleService a implementation of the {@link StyleService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public StyleController(
            final MainMVFabric fabric,
            final StyleService styleService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.styleService = styleService;
    }

    /**
     * Returns a page with CSS styles to edit.
     * Request mapping: /admin/style/
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/edit"},
            method = RequestMethod.GET
    )
    public ModelAndView getStylesToEdit() {
        ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("styles", this.styleService.get());
        modelAndView.setViewName("superadmin/style/edit");
        return modelAndView;
    }

    /**
     * Updates and save CSS styles and redirects by URL /admin/style.
     * Request mapping: /admin/style/update
     * Method: POST
     *
     * @param styles a new CSS styles.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST
    )
    public String updateStyles(@RequestParam(value = "styles") final String styles) {
        this.styleService.save(styles);
        return "redirect:/superadmin/style";
    }

    /**
     * The method throws an exception in the case of reference to it.
     * The exception message: "GET method in "/admin/style/update" is not supported!"
     * Request mapping: /admin/style/update
     * Method: GET
     *
     * @throws IllegalMappingException thrown when an error occurs reading
     *                                 the mapping between object and datastore.
     */
    @RequestMapping(
            value = "/update",
            method = RequestMethod.GET
    )
    public void updateStyles() throws IllegalMappingException {
        throw new IllegalMappingException("GET method in \"/superadmin/style/update\" is not supported!");
    }

    /**
     * Rollbacks a CSS styles.
     * Request mapping: /admin/style/rollback
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/rollback",
            method = RequestMethod.GET
    )
    public String rollbackStyles() {
        this.styleService.rollback();
        return "redirect:/superadmin/style";
    }
}
