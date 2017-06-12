package ua.com.ecoteh.mocks.service.data.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.service.search.SearchService;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.KEYWORDS;
import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static ua.com.ecoteh.mocks.service.data.MockServices.getSearchService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockSearchServiceTest {

    private static SearchService service;

    @BeforeClass
    public static void beforeClass() {
        service = getSearchService();
    }

    @Test
    public void whenSearchThenReturnNotNull() {
        final String content = "all";
        ModelAndView modelAndView = service.search(KEYWORDS, content, true);
        assertNotNull(modelAndView);
        modelAndView = service.search(KEYWORDS, content, false);
        assertNotNull(modelAndView);
    }

    @Test
    public void whenSearchThenReturnValidModelAndView() {
        final String content = "all";
        final String[] keys = { "all" };
        final String viewName = "search/search";
        ModelAndView modelAndView = service.search(KEYWORDS, content, true);
        checkModelAndView(modelAndView, viewName, keys);
        modelAndView = service.search(KEYWORDS, content, false);
        checkModelAndView(modelAndView, viewName, keys);
    }
}
