package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.authorization.AuthorizationController;
import ua.com.ecoteh.mocks.service.data.MockServices;
import ua.com.ecoteh.mocks.service.fabrica.MockMVFabric;

public class MockAuthorizationController {

    private static AuthorizationController authorizationController;

    public static AuthorizationController getAuthorizationController() {
        if (authorizationController == null) {
            initWorkController();
        }
        return authorizationController;
    }

    private static void initWorkController() {
        authorizationController = new AuthorizationController(
                MockMVFabric.getMainMVFabric(),
                MockServices.getUserService()
        );
    }
}
