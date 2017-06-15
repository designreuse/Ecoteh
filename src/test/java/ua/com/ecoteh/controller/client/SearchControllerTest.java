package ua.com.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.fabrica.MainMVFabric;
import ua.com.ecoteh.service.search.SearchService;

import static ua.com.ecoteh.mocks.MockConstants.KEYWORDS;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getSearchService;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getCacheMVFabric;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class SearchControllerTest {

    private static SearchController controller;

    @BeforeClass
    public static void setUp() {
        final MainMVFabric fabric = getCacheMVFabric();
        final SearchService searchService = getSearchService();
        controller = new SearchController(fabric, searchService);
    }

    @Test
    public void whenSearchThenReturnSomeModelAndView() {
        final String viewName = "search/search";
        final String[] keys = { "main_company", "categories", "favicon" };
        final ModelAndView modelAndView = controller.search();
        checkModelAndView(modelAndView, viewName, keys);
    }

    @Test
    public void whenSearchResultByPostMethodThenReturnSomeModelAndView() {
        final String content = "all";
        final boolean howSearch = false;
        final String viewName = "search/search";
        final String[] keys = { "main_company", "categories", "favicon", "keywords",
                "in_categories", "in_articles", "in_companies", "all", "is_search", "how_search" };
        final ModelAndView modelAndView = controller.searchResult(KEYWORDS, content, howSearch);
        checkModelAndView(modelAndView, viewName, keys);
    }
}
