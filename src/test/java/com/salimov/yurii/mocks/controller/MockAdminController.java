package com.salimov.yurii.mocks.controller;

import com.salimov.yurii.controller.admin.*;

import static com.salimov.yurii.mocks.service.data.MockServices.*;
import static com.salimov.yurii.mocks.service.fabrica.MockMVFabric.getMainMVFabric;
import static com.salimov.yurii.mocks.service.message.MockSenderService.getSenderService;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class MockAdminController {

    private static AdminMainController adminMainController;
    private static ArticleController articleController;
    private static CategoryController categoryController;
    private static CompanyController companyController;
    private static FileController fileController;
    private static MessageController messageController;
    private static ResponseController responseController;
    private static UserController userController;

    public static AdminMainController getAdminMainController() {
        if (adminMainController == null) {
            initAdminMainController();
        }
        return adminMainController;
    }

    public static ArticleController getArticleController() {
        if (articleController == null) {
            initArticleController();
        }
        return articleController;
    }

    public static CategoryController getCategoryController() {
        if (categoryController == null) {
            initCategoryController();
        }
        return categoryController;
    }

    public static CompanyController getCompanyController() {
        if (companyController == null) {
            initCompanyController();
        }
        return companyController;
    }

    public static FileController getFileController() {
        if (fileController == null) {
            initFileController();
        }
        return fileController;
    }

    public static MessageController getMessageController() {
        if (messageController == null) {
            initMessageController();
        }
        return messageController;
    }

    public static ResponseController getResponseController() {
        if (responseController == null) {
            initResponseController();
        }
        return responseController;
    }

    public static UserController getUserController() {
        if (userController == null) {
            initUserController();
        }
        return userController;
    }

    private static void initAdminMainController() {
        adminMainController = new AdminMainController(
                getMainMVFabric(),
                getCompanyService(),
                getUserService(),
                null,
                getSenderService(),
                getResponseService());
    }

    private static void initArticleController() {
        articleController = new ArticleController(
                getMainMVFabric(),
                getArticleService(),
                getCategoryService()
        );
    }

    private static void initCategoryController() {
        categoryController = new CategoryController(
                getMainMVFabric(),
                getCategoryService()
        );
    }

    private static void initCompanyController() {
        companyController = new CompanyController(
                getMainMVFabric(),
                getCompanyService()
        );
    }

    private static void initFileController() {
        fileController = new FileController(
                getMainMVFabric(),
                getFileService()
        );
    }

    private static void initMessageController() {
        messageController = new MessageController(
                getMainMVFabric(),
                getMessageService()
        );
    }

    private static void initResponseController() {
        responseController = new ResponseController(
                getResponseService()
        );
    }

    private static void initUserController() {
        userController = new UserController(
                getMainMVFabric(),
                getUserService(),
                getCompanyService(),
                getFileService(),
                getSenderService()
        );
    }
}
