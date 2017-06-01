package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.service.search.SearchService;
import ua.com.ecoteh.service.seo.SeoService;
import ua.com.ecoteh.util.comparator.ContentComparator;
import org.junit.Ignore;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.properties.MockContentProperties;
import ua.com.ecoteh.service.data.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    public static ArticleService getArticleService() {
        if (articleService == null) {
            initArticleService();
        }
        return articleService;
    }

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            initCategoryService();
        }
        return categoryService;
    }

    public static CompanyService getCompanyService() {
        if (companyService == null) {
            initCompanyService();
        }
        return companyService;
    }

    public static FileService getFileService() {
        if (fileService == null) {
            initFileService();
        }
        return fileService;
    }

    public static MessageService getMessageService() {
        if (messageService == null) {
            initMessageService();
        }
        return messageService;
    }

    public static ResponseService getResponseService() {
        if (responseService == null) {
            initResponseService();
        }
        return responseService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            initUserService();
        }
        return userService;
    }

    public static SearchService getSearchService() {
        if (searchService == null) {
            initSearchService();
        }
        return searchService;
    }

    public static SeoService getSeoService() {
        if (seoService == null) {
            initSeoService();
        }
        return seoService;
    }

    public static StyleService getStyleService() {
        if (styleService == null) {
            initStyleService();
        }
        return styleService;
    }

    private static void initArticleService() {
        articleService = mock(ArticleService.class);
        contentService(articleService, MockEntity.getArticleEntity(), MockEntity.getArticleEntities());
        when(articleService.update(MockConstants.URL, MockEntity.getArticleEntity())).thenReturn(MockEntity.getArticleEntity());
        when(articleService.getByNumber(MockConstants.NUMBER, true)).thenReturn(MockEntity.getArticleEntity());
        when(articleService.getByNumber(MockConstants.NUMBER, false)).thenReturn(MockEntity.getArticleEntity());
        when(articleService.sortByNumber(MockEntity.getArticleEntities(), true)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.sortByNumber(MockEntity.getArticleEntities(), false)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.sortByDate(MockEntity.getArticleEntities(), true)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.sortByDate(MockEntity.getArticleEntities(), false)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.getAndSortByNumber(true)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.getAndSortByNumber(false)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.getAndSortByDate(true)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.getAndSortByDate(false)).thenReturn(MockEntity.getArticleEntities());
        when(articleService.filterByDate(MockEntity.getArticleEntities(), MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getArticleEntities());
        when(articleService.filterByCategory(MockEntity.getArticleEntities(), MockEntity.getCategoryEntity())).thenReturn(
                MockEntity.getArticleEntities());
        when(articleService.filterByCategories(MockEntity.getArticleEntities(), MockEntity.getCategories())).thenReturn(
                MockEntity.getArticleEntities());
        when(articleService.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getArticleEntities());
        when(articleService.getAndFilterByCategory(MockEntity.getCategoryEntity())).thenReturn(MockEntity.getArticleEntities());
        when(articleService.getAndFilterByCategories(MockEntity.getCategories())).thenReturn(MockEntity.getArticleEntities());
    }

    private static void initCategoryService() {
        categoryService = mock(CategoryService.class);
        contentService(categoryService, MockEntity.getCategoryEntity(), MockEntity.getCategories());
        when(categoryService.update(MockConstants.URL, MockEntity.getCategoryEntity())).thenReturn(MockEntity.getCategoryEntity());
    }

    private static void initCompanyService() {
        companyService = mock(CompanyService.class);
        contentService(companyService, MockEntity.getCompanyEntity(), MockEntity.getCompanies());
        when(companyService.add(MockEntity.getCompanyEntity())).thenReturn(MockEntity.getCompanyEntity());
        when(companyService.update(MockConstants.URL, MockEntity.getCompanyEntity())).thenReturn(MockEntity.getCompanyEntity());
        when(companyService.getMainCompany()).thenReturn(MockEntity.getCompanyEntity());
        when(companyService.getPartners(true)).thenReturn(MockEntity.getCompanies());
    }

    private static void initFileService() {
        fileService = mock(FileService.class);
        dateService(fileService, MockEntity.getFileEntity(), MockEntity.getFileEntities());
        when(fileService.getByTitle(MockConstants.TITLE)).thenReturn(MockEntity.getFileEntity());
        when(fileService.getByUrl(MockConstants.URL)).thenReturn(MockEntity.getFileEntity());
        when(fileService.add(MockConstants.TITLE, null)).thenReturn(MockEntity.getFileEntity());
        when(fileService.update(MockConstants.ID, MockConstants.TITLE, null)).thenReturn(MockEntity.getFileEntity());
        when(fileService.deleteFile(MockConstants.PATH)).thenReturn(true);
    }

    private static void initMessageService() {
        messageService = mock(MessageService.class);
        dateService(messageService, MockEntity.getMessageEntity(), MockEntity.getMessageEntities());
    }

    private static void initResponseService() {
        responseService = mock(ResponseService.class);
        dateService(responseService, MockEntity.getResponseEntity(), MockEntity.getResponseEntities());
        when(responseService.add(MockEntity.getResponseEntity())).thenReturn(MockEntity.getResponseEntity());
        when(responseService.update(MockConstants.ID, MockEntity.getResponseEntity())).thenReturn(MockEntity.getResponseEntity());
        when(responseService.sortByDate(MockEntity.getResponseEntities(), true)).thenReturn(MockEntity.getResponseEntities());
        when(responseService.sortByDate(MockEntity.getResponseEntities(), false)).thenReturn(MockEntity.getResponseEntities());
        when(responseService.getAndSortByDate(true)).thenReturn(MockEntity.getResponseEntities());
        when(responseService.getAndSortByDate(false)).thenReturn(MockEntity.getResponseEntities());
        when(responseService.filterByDate(MockEntity.getResponseEntities(), MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getResponseEntities());
        when(responseService.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getResponseEntities());
    }

    private static void initUserService() {
        userService = mock(UserService.class);
        dateService(userService, MockEntity.getUserEntity(), MockEntity.getUserEntities());
        when(userService.add(MockEntity.getUserEntity())).thenReturn(MockEntity.getUserEntity());
        when(userService.update(MockConstants.URL, MockEntity.getUserEntity())).thenReturn(MockEntity.getUserEntity());
        when(userService.getByName(MockConstants.NAME)).thenReturn(MockEntity.getUserEntity());
        when(userService.getByUrl(MockConstants.URL)).thenReturn(MockEntity.getUserEntity());
        when(userService.getByLogin(MockConstants.LOGIN)).thenReturn(MockEntity.getUserEntity());
        when(userService.getByEmail(MockConstants.EMAIL)).thenReturn(MockEntity.getUserEntity());
        when(userService.getAuthenticatedUser()).thenReturn(MockEntity.getUserEntity());
        when(userService.getMainAdmin()).thenReturn(MockEntity.getUserEntity());
        when(userService.getAdmins()).thenReturn(MockEntity.getUserEntities());
        when(userService.getPersonnel()).thenReturn(MockEntity.getUserEntities());
        when(userService.sortByName(MockEntity.getUserEntities(), true)).thenReturn(MockEntity.getUserEntities());
        when(userService.sortByName(MockEntity.getUserEntities(), false)).thenReturn(MockEntity.getUserEntities());
        when(userService.sortByUrl(MockEntity.getUserEntities(), true)).thenReturn(MockEntity.getUserEntities());
        when(userService.sortByUrl(MockEntity.getUserEntities(), false)).thenReturn(MockEntity.getUserEntities());
        when(userService.sortByRole(MockEntity.getUserEntities(), MockConstants.USER_ROLE, true)).thenReturn(
                MockEntity.getUserEntities());
        when(userService.sortByRole(MockEntity.getUserEntities(), MockConstants.USER_ROLE, false)).thenReturn(
                MockEntity.getUserEntities());
        when(userService.getAndSortByName(true)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndSortByName(false)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndSortByUrl(true)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndSortByUrl(false)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndSortByRole(MockConstants.USER_ROLE, true)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndSortByRole(MockConstants.USER_ROLE, false)).thenReturn(MockEntity.getUserEntities());
        when(userService.filterByRole(MockEntity.getUserEntities(), MockConstants.USER_ROLE)).thenReturn(MockEntity.getUserEntities());
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        when(userService.filterByRoles(MockEntity.getUserEntities(), roles)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndFilterByRole(MockConstants.USER_ROLE)).thenReturn(MockEntity.getUserEntities());
        when(userService.getAndFilterByRoles(roles)).thenReturn(MockEntity.getUserEntities());
    }

    private static <T extends ContentEntity, E extends ContentService<T>> void contentService(
            final E service,
            final T content,
            final Collection<T> contents
    ) {
        dateService(service, content, contents);
        when(service.getByTitle(MockConstants.TITLE, true)).thenReturn(content);
        when(service.getByTitle(MockConstants.TITLE, false)).thenReturn(content);
        when(service.getByTitle(MockConstants.ANY_STRING, true)).thenThrow(NullPointerException.class);
        when(service.getByTitle(MockConstants.ANY_STRING, false)).thenThrow(NullPointerException.class);
        when(service.getByTitle(null, true)).thenThrow(IllegalArgumentException.class);
        when(service.getByTitle(null, false)).thenThrow(IllegalArgumentException.class);
        when(service.getByUrl(MockConstants.URL, true)).thenReturn(content);
        when(service.getByUrl(MockConstants.URL, false)).thenReturn(content);
        when(service.getByUrl(MockConstants.ANY_STRING, true)).thenThrow(NullPointerException.class);
        when(service.getByUrl(MockConstants.ANY_STRING, false)).thenThrow(NullPointerException.class);
        when(service.getByUrl(null, true)).thenThrow(IllegalArgumentException.class);
        when(service.getByUrl(null, false)).thenThrow(IllegalArgumentException.class);
        when(service.sortByTitle(contents, true)).thenReturn(new ArrayList<>(contents));
        when(service.sortByTitle(contents, false)).thenReturn(new ArrayList<>(contents));
        when(service.sortByTitle(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(service.sortByTitle(new ArrayList<>(), false)
        ).thenReturn(new ArrayList<>());
        when(service.sortByTitle(null, true)).thenReturn(new ArrayList<>());
        when(service.sortByTitle(null, false)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(contents, true)).thenReturn(new ArrayList<>(contents));
        when(service.sortByUrl(contents, false)).thenReturn(new ArrayList<>(contents));
        when(service.sortByUrl(new ArrayList<>(), true)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(new ArrayList<>(), false)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(null, true)).thenReturn(new ArrayList<>());
        when(service.sortByUrl(null, false)).thenReturn(new ArrayList<>());
        when(service.getAndSortByTitle(true)).thenReturn(new ArrayList<>(contents));
        when(service.getAndSortByTitle(false)).thenReturn(new ArrayList<>(contents));
        when(service.getAndSortByUrl(true)).thenReturn(new ArrayList<>(contents));
        when(service.getAndSortByUrl(false)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, new ContentComparator.ByTitle<>(), true)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, new ContentComparator.ByTitle<>(), false)).thenReturn(new ArrayList<>(contents));
        when(service.sort(contents, new ContentComparator.ByTitle<>())).thenReturn(new ArrayList<>(contents));
    }

    private static <T extends ModelEntity, E extends DataService<T>> void dateService(
            final E service,
            final T model,
            final Collection<T> models
    ) {
        when(service.add(model)).thenReturn(model);
        when(service.add(models)).thenReturn(models);
        when(service.update(model)).thenReturn(model);
        when(service.update(models)).thenReturn(models);
        when(service.get(MockConstants.ID)).thenReturn(model);
        when(service.get(MockConstants.UNKNOWN_ID)).thenThrow(
                new NullPointerException("Can`t find ModelEntity by id " + MockConstants.UNKNOWN_ID + "!")
        );
        when(service.getAll()).thenReturn(models);
        when(service.getAll(true)).thenReturn(models);
        when(service.getAll(false)).thenReturn(models);
        when(service.exists(model)).thenReturn(true);
        when(service.exists((T) null)).thenReturn(false);
        when(service.exists(MockConstants.ID)).thenReturn(true);
        when(service.exists(MockConstants.UNKNOWN_ID)).thenReturn(false);
        when(service.exists((T) null)).thenReturn(false);
        final T temp = (T) model.clone();
        when(service.exists(temp)).thenReturn(false);
        when(service.exists(model)).thenReturn(true);
        when(service.subList(models, MockConstants.INDEX, MockConstants.INDEX)).thenReturn(new ArrayList<T>(models));
        when(service.getAndSubList(MockConstants.INDEX, MockConstants.INDEX)).thenReturn(new ArrayList<T>(models));
        when(service.filteredByValid(models)).thenReturn(new ArrayList<T>(models));
    }

    private static void initSearchService() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("all", true);
        modelAndView.setViewName("search/search");
        searchService = mock(SearchService.class);
        when(searchService.search(MockConstants.KEYWORDS, "all", false)).thenReturn(modelAndView);
    }

    private static void initSeoService() {
        final ModelAndView robotsModelAndView = new ModelAndView();
        robotsModelAndView.addObject("domain", MockConstants.DOMAIN);
        robotsModelAndView.setViewName("seo/robots");
        final ModelAndView siteMapModelAndView = new ModelAndView();
        siteMapModelAndView.addObject("domain", MockConstants.DOMAIN);
        siteMapModelAndView.addObject("categories", MockEntity.getCategories());
        siteMapModelAndView.addObject("articles", MockEntity.getArticleEntities());
        siteMapModelAndView.addObject("companies", MockEntity.getCompanies());
        siteMapModelAndView.setViewName("seo/sitemap");
        seoService = mock(SeoService.class);
        when(seoService.getRobotsTxt()).thenReturn(robotsModelAndView);
        when(seoService.getSiteMapXml()).thenReturn(siteMapModelAndView);
    }

    private static void initStyleService() {
        styleService = new StyleServiceImpl(MockContentProperties.getContentProperties());
    }
}
