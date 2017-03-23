package com.salimov.ecoteh.mocks.controller;

import com.salimov.ecoteh.controller.superadmin.ConfigurationController;

import static com.salimov.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

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
