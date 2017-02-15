package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.advice.AdviceController;

import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

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
