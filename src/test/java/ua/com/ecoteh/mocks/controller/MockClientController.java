package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.admin.ResponseController;
import ua.com.ecoteh.controller.admin.UserController;
import ua.com.ecoteh.controller.client.ClientMainController;
import org.junit.Ignore;
import ua.com.ecoteh.mocks.service.message.MockSenderService;

import static ua.com.ecoteh.mocks.service.data.MockServices.*;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

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
                MockSenderService.getSenderService(),
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
                MockSenderService.getSenderService()
        );
    }
}
