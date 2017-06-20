package ua.com.ecoteh.controller.superadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.util.cache.Cache;

/**
 * The class implements a set of methods for working with
 * object of the {@link Cache} class for admins.
 * Class methods create and return modelsAndView, depending on the request.
 * For the work used implementation of the interface {@link MainMVFabric}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(value = "/superadmin/cache")
@ComponentScan(basePackages = "ua.com.ecoteh.service.fabric")
@SuppressWarnings("SpringMVCViewInspection")
public final class CacheController {

    /**
     * The implementation of the interface provides a set of standard
     * methods for creates and returns the main modelAndViews for admins.
     */
    private final MainMVFabric fabric;

    /**
     * Constructor.
     * Initializes a implementation of the interface.
     *
     * @param fabric the implementation of the {@link MainMVFabric} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CacheController(@Qualifier("cacheMVFabricImpl") final MainMVFabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Returns a page with information about objects in a cache.
     * Request mapping: /superadmin/cache
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/" },
            method = RequestMethod.GET
    )
    public ModelAndView getCachePage() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.addObject("objects", Cache.getEntriesToString());
        modelAndView.setViewName("cache/cache");
        return modelAndView;
    }

    /**
     * Cleans cache and redirect by the "/superadmin/cache" URL.
     * Request mapping: /superadmin/cache/clear
     * Method: GET
     *
     * @return The redirect string to the "/superadmin/cache" URL.
     */
    @RequestMapping(
            value = "/clear",
            method = RequestMethod.GET
    )
    public String clearCache() {
        Cache.clear();
        return "redirect:/superadmin/cache";
    }

    /**
     * Removes a object with the incoming key from a cache
     * and redirect by the "/superadmin/cache" URL.
     * Request mapping: /superadmin/cache/remove/{key}
     * Method: GET
     *
     * @param key a object key in the cache.
     * @return The redirect string to the "/superadmin/cache" URL.
     */
    @RequestMapping(
            value = "/remove/{key}",
            method = RequestMethod.GET
    )
    public String removeObjectFromCache(@PathVariable("key") final String key) {
        Cache.remove(key);
        return "redirect:/superadmin/cache";
    }
}
