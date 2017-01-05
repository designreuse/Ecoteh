package com.salimov.yurii.controller.admin;

import com.salimov.yurii.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.yurii.service.fabrica.interfaces.AdminMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.CacheMVFabric;
import com.salimov.yurii.util.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for working with
 * main ModelAndView objects and object of the {@link Cache} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 * For the work used implementation of the interface {@link AdminMVFabric}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Cache
 * @see AdminMVFabric
 */
@Controller
@RequestMapping(value = "/admin/cache")
@ComponentScan(basePackages = "com.salimov.yurii.service.fabric")
@SuppressWarnings("SpringMVCViewInspection")
public class AdminCacheController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     *
     * @see CacheMVFabric
     */
    private final CacheMVFabric fabric;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric a implementation of the {@link AdminMVFabric} interface.
     * @see AdminMVFabric
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AdminCacheController(final AdminMVFabric fabric) {
        this.fabric = new CacheMVFabricImpl(fabric);
    }

    /**
     * Returns page with information about objects in the cache.
     * Request mapping: /admin/cache, /admin/cache/
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     * @see Cache
     */
    @RequestMapping(
            value = {"", "/"},
            method = RequestMethod.GET
    )
    public ModelAndView getCache() {
        ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("objects", Cache.getEntriesToString());
        modelAndView.setViewName("admin/cache/cache_page");
        return modelAndView;
    }

    /**
     * Cleans cache and redirect by url /admin/cache.
     * Request mapping: /admin/cache/clear
     * Method: GET
     *
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Cache
     */
    @RequestMapping(
            value = "/clear",
            method = RequestMethod.GET
    )
    public ModelAndView clearCache(final ModelAndView modelAndView) {
        Cache.clear();
        modelAndView.setViewName("redirect:/admin/cache");
        return modelAndView;
    }

    /**
     * Removes object from cache with key and redirect by url /admin/cache.
     * Request mapping: /admin/cache/remove/{key}
     * Method: GET
     *
     * @param key          a object key in the cache.
     * @param modelAndView a object of class ModelAndView for to update.
     * @return The ready object of class ModelAndView.
     * @see Cache
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
        modelAndView.setViewName("redirect:/admin/cache");
        return modelAndView;
    }
}
