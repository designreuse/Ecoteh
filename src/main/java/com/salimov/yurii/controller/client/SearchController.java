package com.salimov.yurii.controller.client;

import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.service.fabrica.interfaces.ClientMVFabric;
import com.salimov.yurii.service.fabrica.interfaces.MainMVFabric;
import com.salimov.yurii.service.search.SearchService;
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
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see SectionService
 * @see CategoryService
 * @see ArticleService
 * @see CompanyService
 * @see UserService
 * @see MainMVFabric
 * @see ClientMVFabric
 */
@Controller
@RequestMapping(value = "/search")
@ComponentScan(basePackages = "com.salimov.yurii.service")
@SuppressWarnings("SpringMVCViewInspection")
public class SearchController {

    /**
     * The implementation of the interface provides a set of standard methods
     * for creates and returns the main modelAndViews.
     *
     * @see MainMVFabric
     */
    private final MainMVFabric fabric;

    /**
     * The implementation of the interface describes a set of methods
     * for search for content on the site.
     *
     * @see SearchService
     */
    private final SearchService searchService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param fabric        a implementation of the {@link ClientMVFabric}
     *                      interface.
     * @param searchService a implementation of the {@link SearchService}
     *                      interface.
     * @see ClientMVFabric
     * @see SearchService
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SearchController(
            final ClientMVFabric fabric,
            final SearchService searchService
    ) {
        this.fabric = fabric;
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
            value = {"", "/"},
            method = RequestMethod.GET
    )
    public ModelAndView search() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("client/search/result_page");
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
        return this.searchService.search(
                keywords,
                content,
                howSearch
        );
    }

    /**
     * Redirects to "/search/".
     * Request mapping: /result
     * Method: GET
     *
     * @return The ready object of class ModelAndView.
     */
    @RequestMapping(
            value = "/result",
            method = RequestMethod.GET
    )
    public ModelAndView searchResult() {
        return search();
    }
}
