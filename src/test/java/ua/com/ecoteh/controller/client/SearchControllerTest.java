package ua.com.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.controller.MockSearchController;

import static ua.com.ecoteh.mocks.ModelAndViews.checkModelAndView;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SearchControllerTest {

    private static SearchController controller;

    @BeforeClass
    public static void setUp() {
        controller = MockSearchController.getSearchController();
    }

    @Test
    public void whenSearchThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.search(),
                "client/search/result",
                null
        );
    }

    @Test
    public void whenSearchResultByPostMethodThenReturnSomeModelAndView() {
        checkModelAndView(
                controller.searchResult(MockConstants.KEYWORDS, "all", false),
                "client/search/result_page",
                null
        );
    }
}
