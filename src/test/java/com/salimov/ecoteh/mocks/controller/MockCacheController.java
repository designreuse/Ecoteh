package com.salimov.ecoteh.mocks.controller;

import com.salimov.ecoteh.controller.superadmin.CacheController;

import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockCacheController {

    private static CacheController cacheController;

    public static CacheController getCacheController() {
        if (cacheController == null) {
            initCacheController();
        }
        return cacheController;
    }

    private static void initCacheController() {
        cacheController = new CacheController(getMainMVFabric());
    }
}
