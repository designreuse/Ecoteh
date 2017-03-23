package com.salimov.ecoteh.mocks.controller;

import com.salimov.ecoteh.controller.authorization.AuthorizationController;

import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

public class MockAuthorizationController {

    private static AuthorizationController authorizationController;

    public static AuthorizationController getAuthorizationController() {
        if (authorizationController == null) {
            initWorkController();
        }
        return authorizationController;
    }

    private static void initWorkController() {
        authorizationController = new AuthorizationController(getMainMVFabric());
    }
}
