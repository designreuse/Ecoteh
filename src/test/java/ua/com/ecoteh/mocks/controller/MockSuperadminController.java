package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.superadmin.CacheController;
import ua.com.ecoteh.controller.superadmin.ConfigurationController;
import ua.com.ecoteh.controller.superadmin.StyleController;
import ua.com.ecoteh.mocks.service.fabrica.MockMVFabric;

import static ua.com.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static ua.com.ecoteh.mocks.service.data.MockServices.getStyleService;

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
        cacheController = new CacheController(MockMVFabric.getMainMVFabric());
    }

    private static void initConfigurationController() {
        configurationController = new ConfigurationController(MockMVFabric.getMainMVFabric(), getContentProperties());
    }

    private static void initStyleController() {
        styleController = new StyleController(MockMVFabric.getMainMVFabric(), getStyleService());
    }
}
