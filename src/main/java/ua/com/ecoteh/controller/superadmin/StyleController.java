package ua.com.ecoteh.controller.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mapping.model.IllegalMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.service.data.StyleService;
import ua.com.ecoteh.service.fabrica.MainMVFabric;

/**
 * The class implements a set of methods for working with CSS styles.
 * Class methods create and return modelsAndView, depending on the request.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.util.properties"
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
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final StyleService styleService
    ) {
        this.fabric = fabric;
        this.styleService = styleService;
    }

    /**
     * Returns a page with CSS styles to edit.
     * Request mapping: /admin/style/
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/edit" },
            method = RequestMethod.GET
    )
    public ModelAndView getStylesToEdit() {
        ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("styles", this.styleService.get());
        modelAndView.setViewName("style/edit");
        return modelAndView;
    }

    /**
     * Updates and save CSS styles and redirects by URL /admin/style.
     * Request mapping: /admin/style/update
     * Method: POST
     *
     * @param styles a new CSS styles.
     * @return The ready object of the ModelAndView class.
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
        final String message = String.format(
                ExceptionMessage.GET_METHOD_NOT_SUPPORTED_MESSAGE,
                "/superadmin/style/update"
        );
        throw new IllegalMappingException(message);
    }

    /**
     * Rollbacks a CSS styles.
     * Request mapping: /admin/style/rollback
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
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
