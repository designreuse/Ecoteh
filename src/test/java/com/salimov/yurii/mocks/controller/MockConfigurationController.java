package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.superadmin.ConfigurationController;

import static com.salimov.yurii.mocks.properties.MockContentProperties.getContentProperties;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockConfigurationController {

    private static ConfigurationController configurationController;

    public static ConfigurationController getConfigurationController() {
        if (configurationController == null) {
            initConfigurationController();
        }
        return configurationController;
    }

    private static void initConfigurationController() {
        configurationController = new ConfigurationController(getMainMVFabric(), getContentProperties());
    }
}
