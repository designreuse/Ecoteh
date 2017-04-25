package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.advice.AdviceController;

import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockAdviceController {

    private static AdviceController adviceController;

    public static AdviceController getAdviceController() {
        if (adviceController == null) {
            initAdviceController();
        }
        return adviceController;
    }

    private static void initAdviceController() {
        adviceController = new AdviceController(getMainMVFabric());
    }
}
