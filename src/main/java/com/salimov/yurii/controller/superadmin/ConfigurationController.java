package com.salimov.yurii.controller.superadmin;

import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.util.cache.Cache;
import com.salimov.yurii.util.properties.ContentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Super admin controller.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/superadmin")
public class ConfigurationController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     *
     * @see ContentProperties
     */
    private final ContentProperties contentProperties;

    /**
     * Constructor.
     *
     * @param fabric            a implementation
     *                          of the {@link MainMVFabric} interface.
     * @param contentProperties a implementation of the
     *                          {@link ContentProperties} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ConfigurationController(
            final MainMVFabric fabric,
            final ContentProperties contentProperties
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.contentProperties = contentProperties;
    }

    /**
     * Returns page with project configuration.
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/", "/config", "/configuration"},
            method = RequestMethod.GET
    )
    public ModelAndView getProperties() {
        ModelAndView modelAndView;
        try {
            modelAndView = this.fabric.getDefaultModelAndView();
            modelAndView.addObject("properties", this.contentProperties);
        } catch (Exception ex) {
            ex.printStackTrace();
            modelAndView = new ModelAndView();
        }
        modelAndView.setViewName("superadmin/configuration/configuration_page");
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
            value = "/clear",
            method = RequestMethod.GET
    )
    public ModelAndView clearCache(final ModelAndView modelAndView) {
        Cache.clear();
        System.gc();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
