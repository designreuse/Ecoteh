package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.admin.AdminCacheController;
import com.salimov.yurii.controller.admin.AdminMainController;
import com.salimov.yurii.controller.admin.AdminResponseController;
import com.salimov.yurii.controller.admin.AdminUserController;
import com.salimov.yurii.controller.advice.AdviceController;
import com.salimov.yurii.controller.client.ClientMainController;
import com.salimov.yurii.controller.other.WorkController;
import com.salimov.yurii.controller.seo.SeoController;
import org.junit.Ignore;

import static com.salimov.yurii.mocks.service.data.MockServices.*;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getAdminMVFabric;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getClientMVFabric;
import static com.salimov.yurii.mocks.service.message.MockSenderService.getSenderService;

@Ignore
public final class MockController {

    private static ClientMainController clientMainController;

    private static AdminMainController adminMainController;
    private static AdminResponseController adminResponseController;
    private static AdminUserController adminUserController;
    private static AdminCacheController adminCacheController;

    private static AdviceController adviceController;
    private static SeoController seoController;
    private static WorkController workController;

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

    public static AdminResponseController getAdminResponseController() {
        if (adminResponseController == null) {
            adminResponseController = initAdminResponseController();
        }
        return adminResponseController;
    }

    public static AdminUserController getAdminUserController() {
        if (adminUserController == null) {
            adminUserController = initAdminUserController();
        }
        return adminUserController;
    }

    public static AdminCacheController getAdminCacheController() {
        if (adminCacheController == null) {
            adminCacheController = initAdminCacheController();
        }
        return adminCacheController;
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

    public static WorkController getWorkController() {
        if (workController == null) {
            workController = initWorkController();
        }
        return workController;
    }

    private static ClientMainController initClientMainController() {
        return new ClientMainController(
                getClientMVFabric(),
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
                getAdminMVFabric(),
                getCompanyService(),
                getUserService(),
                null,
                getSenderService(),
                getResponseService());
    }

    private static AdminResponseController initAdminResponseController() {
        return new AdminResponseController(
                getResponseService()
        );
    }

    private static AdminUserController initAdminUserController() {
        return new AdminUserController(
                getAdminMVFabric(),
                getUserService(),
                getCompanyService(),
                getSenderService()
        );
    }

    private static AdminCacheController initAdminCacheController() {
        return new AdminCacheController(
                getAdminMVFabric()
        );
    }

    private static AdviceController initAdviceController() {
        return new AdviceController(
                getClientMVFabric()
        );
    }

    private static SeoController initSeoController() {
        return new SeoController(
                null
        );
    }

    private static WorkController initWorkController() {
        return new WorkController();
    }
}
