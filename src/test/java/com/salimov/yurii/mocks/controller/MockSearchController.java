package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.client.SearchController;

import static com.salimov.yurii.mocks.service.data.MockServices.getSearchService;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockSearchController {

    private static SearchController searchController;

    public static SearchController getSearchController() {
        if (searchController == null) {
            initSearchController();
        }
        return searchController;
    }

    private static void initSearchController() {
        searchController = new SearchController(getMainMVFabric(), getSearchService());
    }
}
