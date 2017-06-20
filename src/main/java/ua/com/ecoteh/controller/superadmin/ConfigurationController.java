package ua.com.ecoteh.controller.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.properties.ContentProperties;
import ua.com.ecoteh.util.time.Time;

import java.util.Date;

/**
 * Super admin controller.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(
        value = {
                "/superadmin/config",
                "/superadmin/configuration"
        }
)
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.service.fabrica",
                "ua.com.ecoteh.util.properties"
        }
)
public final class ConfigurationController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     */
    private final ContentProperties contentProperties;

    /**
     * Constructor.
     *
     * @param fabric            a implementation of the {@link MainMVFabric} interface.
     * @param contentProperties a implementation of the {@link ContentProperties} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ConfigurationController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final ContentProperties contentProperties
    ) {
        this.fabric = fabric;
        this.contentProperties = contentProperties;
    }

    /**
     * Returns page with project configuration.
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/" },
            method = RequestMethod.GET
    )
    public ModelAndView getPropertiesPage() {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
            modelAndView.addObject("properties", this.contentProperties);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("server_date", new Date());
        modelAndView.addObject("conf_date", Time.getDate());
        modelAndView.setViewName("configuration/configuration");
        return modelAndView;
    }
}
