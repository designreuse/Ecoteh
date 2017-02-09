package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.superadmin.CacheController;
import com.salimov.yurii.controller.admin.AdminMainController;
import com.salimov.yurii.controller.admin.ResponseController;
import com.salimov.yurii.controller.admin.UserController;
import com.salimov.yurii.controller.advice.AdviceController;
import com.salimov.yurii.controller.client.ClientMainController;
import com.salimov.yurii.controller.authorization.AuthorizationController;
import com.salimov.yurii.controller.seo.SeoController;
import org.junit.Ignore;

import static com.salimov.yurii.mocks.service.data.MockServices.*;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;
import static com.salimov.yurii.mocks.service.message.MockSenderService.getSenderService;

@Ignore
public final class MockController {

    private static ClientMainController clientMainController;

    private static AdminMainController adminMainController;
    private static ResponseController responseController;
    private static UserController userController;
    private static CacheController cacheController;

    private static AdviceController adviceController;
    private static SeoController seoController;
    private static AuthorizationController authorizationController;

    public static ClientMainController getClientMainController() {
        if (clientMainController == null) {
            clientMainController = initClientMainController();
        }
        return clientMainController;
    }

    public static AdminMainController getAdminMainController() {
        if (adminMainController == null) {
            adminMainController = initAdminMainController();
        }
        return adminMainController;
    }

    public static ResponseController getResponseController() {
        if (responseController == null) {
            responseController = initAdminResponseController();
        }
        return responseController;
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = initAdminUserController();
        }
        return userController;
    }

    public static CacheController getCacheController() {
        if (cacheController == null) {
            cacheController = initAdminCacheController();
        }
        return cacheController;
    }

    public static AdviceController getAdviceController() {
        if (adviceController == null) {
            adviceController = initAdviceController();
        }
        return adviceController;
    }

    public static SeoController getSeoController() {
        if (seoController == null) {
            seoController = initSeoController();
        }
        return seoController;
    }

    public static AuthorizationController getAuthorizationController() {
        if (authorizationController == null) {
            authorizationController = initWorkController();
        }
        return authorizationController;
    }

    private static ClientMainController initClientMainController() {
        return new ClientMainController(
                getMainMVFabric(),
                getCompanyService(),
                getUserService(),
                getResponseService(),
                null,
                getSenderService(),
                null
        );
    }

    private static AdminMainController initAdminMainController() {
        return new AdminMainController(
                getMainMVFabric(),
                getCompanyService(),
                getUserService(),
                null,
                getSenderService(),
                getResponseService());
    }

    private static ResponseController initAdminResponseController() {
        return new ResponseController(
                getResponseService()
        );
    }

    private static UserController initAdminUserController() {
        return new UserController(
                getMainMVFabric(),
                getUserService(),
                getCompanyService(),
                getSenderService()
        );
    }

    private static CacheController initAdminCacheController() {
        return new CacheController(
                getMainMVFabric()
        );
    }

    private static AdviceController initAdviceController() {
        return new AdviceController(
                getMainMVFabric()
        );
    }

    private static SeoController initSeoController() {
        return new SeoController(null);
    }

    private static AuthorizationController initWorkController() {
        return new AuthorizationController(null);
    }
}
