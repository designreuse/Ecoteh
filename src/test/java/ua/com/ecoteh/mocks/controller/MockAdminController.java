package ua.com.ecoteh.mocks.controller;

import ua.com.ecoteh.controller.admin.*;
import ua.com.ecoteh.mocks.service.fabrica.MockMVFabric;
import ua.com.ecoteh.mocks.service.message.MockSenderService;

import static ua.com.ecoteh.mocks.service.data.MockServices.*;
import static ua.com.ecoteh.mocks.service.fabrica.MockMVFabric.getMainMVFabric;

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
                MockMVFabric.getMainMVFabric(),
                getCompanyService(),
                getUserService(),
                getMessageService(),
                MockSenderService.getSenderService(),
                getResponseService());
    }

    private static void initArticleController() {
        articleController = new ArticleController(
                MockMVFabric.getMainMVFabric(),
                getArticleService(),
                getCategoryService(),
                getFileService()
        );
    }

    private static void initCategoryController() {
        categoryController = new CategoryController(
                MockMVFabric.getMainMVFabric(),
                getCategoryService(),
                getFileService()
        );
    }

    private static void initCompanyController() {
        companyController = new CompanyController(
                MockMVFabric.getMainMVFabric(),
                getCompanyService(),
                getFileService()
        );
    }

    private static void initFileController() {
        fileController = new FileController(
                MockMVFabric.getMainMVFabric(),
                getFileService()
        );
    }

    private static void initMessageController() {
        messageController = new MessageController(
                MockMVFabric.getMainMVFabric(),
                getMessageService()
        );
    }

    private static void initResponseController() {
        responseController = new ResponseController(
                getMainMVFabric(),
                getResponseService()
        );
    }

    private static void initUserController() {
        userController = new UserController(
                MockMVFabric.getMainMVFabric(),
                getUserService(),
                getCompanyService(),
                getFileService(),
                MockSenderService.getSenderService()
        );
    }
}
