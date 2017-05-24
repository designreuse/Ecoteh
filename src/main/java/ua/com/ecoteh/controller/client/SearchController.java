package ua.com.ecoteh.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.search.SearchService;

/**
 * The class implements a set of methods for search for content on the site.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Controller
@RequestMapping(value = "/search")
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.fabrica",
                "ua.com.ecoteh.service.data"
        }
)
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
     * @param fabric        the implementation of the {@link MainMVFabric} interface.
     * @param searchService the implementation of the {@link SearchService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SearchController(
            @Qualifier("cacheMVFabricImpl") final MainMVFabric fabric,
            final SearchService searchService
    ) {
        this.fabric = fabric;
        this.searchService = searchService;
    }

    /**
     * Returns a page to search for content on the site.
     * Request mapping: /search, /search/result
     * Method: GET
     *
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = { "", "/", "/result" },
            method = RequestMethod.GET
    )
    public ModelAndView search() {
        final ModelAndView modelAndView = this.fabric.getDefaultModelAndView();
        modelAndView.setViewName("search/search");
        return modelAndView;
    }

    /**
     * Returns a page with a found content.
     * Request mapping: /search/result
     * Method: POST
     *
     * @param keywords  the keywords for a content search.
     * @param content   the objects list for a content search.
     * @param howSearch the search mode.
     * @return The ready object of the ModelAndView class.
     */
    @RequestMapping(
            value = "/result",
            method = RequestMethod.POST
    )
    public ModelAndView searchResult(
            @RequestParam(value = "keywords", defaultValue = "") final String keywords,
            @RequestParam(value = "content", defaultValue = "") final String content,
            @RequestParam(value = "how_search", defaultValue = "false") final boolean howSearch
    ) {
        return this.searchService.search(keywords, content, howSearch);
    }
}
