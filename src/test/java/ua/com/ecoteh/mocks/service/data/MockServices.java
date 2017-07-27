package ua.com.ecoteh.mocks.service.data;

import org.junit.Ignore;
import ua.com.ecoteh.service.data.*;
import ua.com.ecoteh.service.search.SearchService;
import ua.com.ecoteh.service.seo.SeoService;

@Ignore
public final class MockServices {

    private static ArticleService articleService;
    private static CategoryService categoryService;
    private static CompanyService companyService;
    private static FileService fileService;
    private static MessageService messageService;
    private static ResponseService responseService;
    private static UserService userService;
    private static SearchService searchService;
    private static SeoService seoService;
    private static StyleService styleService;
    private static PostService postService;

    public static ArticleService getArticleService() {
        if (articleService == null) {
            final MockArticleService mockArticleService = new MockArticleService();
            articleService = mockArticleService.create();
        }
        return articleService;
    }

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            final MockCategoryService mockCategoryService = new MockCategoryService();
            categoryService = mockCategoryService.create();
        }
        return categoryService;
    }

    public static CompanyService getCompanyService() {
        if (companyService == null) {
            final MockCompanyService mockCompanyService = new MockCompanyService();
            companyService = mockCompanyService.create();
        }
        return companyService;
    }

    public static FileService getFileService() {
        if (fileService == null) {
            final MockFileService mockFileService = new MockFileService();
            fileService = mockFileService.create();
        }
        return fileService;
    }

    public static MessageService getMessageService() {
        if (messageService == null) {
            final MockMessageService mockMessageService = new MockMessageService();
            messageService = mockMessageService.create();
        }
        return messageService;
    }

    public static ResponseService getResponseService() {
        if (responseService == null) {
            final MockResponseService mockResponseService = new MockResponseService();
            responseService = mockResponseService.create();
        }
        return responseService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            final MockUserService mockUserService = new MockUserService();
            userService = mockUserService.create();
        }
        return userService;
    }

    public static SearchService getSearchService() {
        if (searchService == null) {
            final MockSearchService mockSearchService = new MockSearchService();
            searchService = mockSearchService.create();
        }
        return searchService;
    }

    public static SeoService getSeoService() {
        if (seoService == null) {
            final MockSeoService mockSeoService = new MockSeoService();
            seoService = mockSeoService.create();
        }
        return seoService;
    }

    public static StyleService getStyleService() {
        if (styleService == null) {
            final MockStyleService mockStyleService = new MockStyleService();
            styleService = mockStyleService.create();
        }
        return styleService;
    }

    public static PostService getPostService() {
        if (postService == null) {
            final MockPostService mockPostService = new MockPostService();
            postService = mockPostService.create();
        }
        return postService;
    }
}
