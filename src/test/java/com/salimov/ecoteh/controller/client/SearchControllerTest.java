package com.salimov.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.KEYWORDS;
import static com.salimov.ecoteh.mocks.ModelAndViews.checkModelAndView;
import static com.salimov.ecoteh.mocks.controller.MockSearchController.getSearchController;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class SearchControllerTest {

    private static SearchController controller;

    @BeforeClass
    public static void setUp() {
        controller = getSearchController();
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
                controller.searchResult(KEYWORDS, "all", false),
                "client/search/result_page",
                null
        );
    }
}
