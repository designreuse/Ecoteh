package com.salimov.ecoteh.controller.client;

import com.salimov.ecoteh.service.fabrica.impl.CacheMVFabricImpl;
import com.salimov.ecoteh.service.fabrica.interfaces.MainMVFabric;
import com.salimov.ecoteh.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * The class implements a set of methods for search for content on the site.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/search")
@ComponentScan(basePackages = "com.salimov.ecoteh.service")
@SuppressWarnings("SpringMVCViewInspection")
public class SearchController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for search for content on the site.
     */
    private final SearchService searchService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric        a implementation of the {@link MainMVFabric} interface.
     * @param searchService a implementation of the {@link SearchService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SearchController(
            final MainMVFabric fabric,
            final SearchService searchService
    ) {
        this.fabric = new CacheMVFabricImpl(fabric);
        this.searchService = searchService;
    }

    /**
     * Returns the page to search for content on the site.
     * Request mapping: /search, /search/
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = {"", "/", "/result"},
            method = RequestMethod.GET
    )
    public ModelAndView search() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("client/search/result");
        return modelAndView;
    }

    /**
     * Returns the page with the found content.
     * Request mapping: /search/result
     * Method: POST
     *
     * @param keywords  a keywords for content search.
     * @param content   a objects list for content search.
     * @param howSearch a search mode.
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/result",
            method = RequestMethod.POST
    )
    public ModelAndView searchResult(
            @RequestParam(value = "keywords") final String keywords,
            @RequestParam(value = "content") final String content,
            @RequestParam(value = "how_search") final boolean howSearch
    ) {
        return this.searchService.search(keywords, content, howSearch);
    }
}
