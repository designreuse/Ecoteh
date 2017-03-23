package com.salimov.ecoteh.controller.superadmin;

import com.salimov.ecoteh.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import com.salimov.ecoteh.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with
 * object of the {@link Cache} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 * For the work used implementation of the interface {@link MainMVFabric}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/superadmin/cache")
@ComponentScan(basePackages = "com.salimov.ecoteh.service.fabric")
@SuppressWarnings("SpringMVCViewInspection")
public class CacheController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric a implementation of the {@link MainMVFabric} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CacheController(final MainMVFabric fabric) {
        this.fabric = new CacheMVFabricImpl(fabric);
    }

    /**
     * Returns page with information about objects in the cache.
     * Request mapping: /admin/cache, /admin/cache/
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/"},
            method = RequestMethod.GET
    )
    public ModelAndView getCachePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("objects", Cache.getEntriesToString());
        modelAndView.setViewName("superadmin/cache/cache");
        return modelAndView;
    }

    /**
     * Cleans cache and redirect by URL /admin/cache.
     * Request mapping: /admin/cache/clear
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
        modelAndView.setViewName("redirect:/superadmin/cache");
        return modelAndView;
    }

    /**
     * Removes object from cache with key and redirect by URL /admin/cache.
     * Request mapping: /admin/cache/remove/{key}
     * Method: GET
     *
     * @param key          a object key in the cache.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/remove/{key}",
            method = RequestMethod.GET
    )
    public ModelAndView removeObjectFromCache(
            @PathVariable("key") final String key,
            final ModelAndView modelAndView
    ) {
        Cache.remove(key);
        modelAndView.setViewName("redirect:/superadmin/cache");
        return modelAndView;
    }
}
