package com.salimov.ecoteh.mocks.controller;

import com.salimov.ecoteh.controller.superadmin.CacheController;
import com.salimov.ecoteh.controller.superadmin.ConfigurationController;
import com.salimov.ecoteh.controller.superadmin.StyleController;

import static com.salimov.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getStyleService;
import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockSuperadminController {

    private static CacheController cacheController;

    private static ConfigurationController configurationController;

    private static StyleController styleController;

    public static CacheController getCacheController() {
        if (cacheController == null) {
            initCacheController();
        }
        return cacheController;
    }

    public static ConfigurationController getConfigurationController() {
        if (configurationController == null) {
            initConfigurationController();
        }
        return configurationController;
    }

    public static StyleController getStyleController() {
        if (styleController == null) {
            initStyleController();
        }
        return styleController;
    }

    private static void initCacheController() {
        cacheController = new CacheController(getMainMVFabric());
    }

    private static void initConfigurationController() {
        configurationController = new ConfigurationController(getMainMVFabric(), getContentProperties());
    }

    private static void initStyleController() {
        styleController = new StyleController(getMainMVFabric(), getStyleService());
    }
}
