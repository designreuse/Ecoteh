package com.salimov.ecoteh.mocks.controller;

import com.salimov.ecoteh.controller.admin.ResponseController;
import com.salimov.ecoteh.controller.admin.UserController;
import com.salimov.ecoteh.controller.client.ClientMainController;
import org.junit.Ignore;

import static com.salimov.ecoteh.mocks.service.data.MockServices.*;
import static com.salimov.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;
import static com.salimov.ecoteh.mocks.service.message.MockSenderService.getSenderService;

@Ignore
public final class MockClientController {

    private static ClientMainController clientMainController;

    private static ResponseController responseController;
    private static UserController userController;

    public static ClientMainController getClientMainController() {
        if (clientMainController == null) {
            initClientMainController();
        }
        return clientMainController;
    }

    public static ResponseController getResponseController() {
        if (responseController == null) {
            initAdminResponseController();
        }
        return responseController;
    }

    public static UserController getUserController() {
        if (userController == null) {
            initAdminUserController();
        }
        return userController;
    }

    private static void initClientMainController() {
        clientMainController =  new ClientMainController(
                getMainMVFabric(),
                getCompanyService(),
                getUserService(),
                getResponseService(),
                null,
                getSenderService(),
                null
        );
    }

    private static void initAdminResponseController() {
        responseController =  new ResponseController(getResponseService());
    }

    private static void initAdminUserController() {
        userController =  new UserController(
                getMainMVFabric(),
                getUserService(),
                getCompanyService(),
                getFileService(),
                getSenderService()
        );
    }
}
