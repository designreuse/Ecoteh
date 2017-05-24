package ua.com.ecoteh.mocks.service.data;

import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.service.search.SearchService;
import ua.com.ecoteh.service.seo.SeoService;
import ua.com.ecoteh.util.comparator.ContentComparator;
import org.junit.Ignore;
import org.springframework.web.servlet.ModelAndView;
import ua.com.ecoteh.entity.Content;
import ua.com.ecoteh.entity.Model;
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
        contentService(articleService, MockEntity.getArticle(), MockEntity.getArticles());
        when(articleService.update(MockConstants.URL, MockEntity.getArticle())).thenReturn(MockEntity.getArticle());
        when(articleService.getByNumber(MockConstants.NUMBER, true)).thenReturn(MockEntity.getArticle());
        when(articleService.getByNumber(MockConstants.NUMBER, false)).thenReturn(MockEntity.getArticle());
        when(articleService.sortByNumber(MockEntity.getArticles(), true)).thenReturn(MockEntity.getArticles());
        when(articleService.sortByNumber(MockEntity.getArticles(), false)).thenReturn(MockEntity.getArticles());
        when(articleService.sortByDate(MockEntity.getArticles(), true)).thenReturn(MockEntity.getArticles());
        when(articleService.sortByDate(MockEntity.getArticles(), false)).thenReturn(MockEntity.getArticles());
        when(articleService.getAndSortByNumber(true)).thenReturn(MockEntity.getArticles());
        when(articleService.getAndSortByNumber(false)).thenReturn(MockEntity.getArticles());
        when(articleService.getAndSortByDate(true)).thenReturn(MockEntity.getArticles());
        when(articleService.getAndSortByDate(false)).thenReturn(MockEntity.getArticles());
        when(articleService.filterByDate(MockEntity.getArticles(), MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getArticles());
        when(articleService.filterByCategory(MockEntity.getArticles(), MockEntity.getCategory())).thenReturn(
                MockEntity.getArticles());
        when(articleService.filterByCategories(MockEntity.getArticles(), MockEntity.getCategories())).thenReturn(
                MockEntity.getArticles());
        when(articleService.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getArticles());
        when(articleService.getAndFilterByCategory(MockEntity.getCategory())).thenReturn(MockEntity.getArticles());
        when(articleService.getAndFilterByCategories(MockEntity.getCategories())).thenReturn(MockEntity.getArticles());
    }

    private static void initCategoryService() {
        categoryService = mock(CategoryService.class);
        contentService(categoryService, MockEntity.getCategory(), MockEntity.getCategories());
        when(categoryService.update(MockConstants.URL, MockEntity.getCategory())).thenReturn(MockEntity.getCategory());
    }

    private static void initCompanyService() {
        companyService = mock(CompanyService.class);
        contentService(companyService, MockEntity.getCompany(), MockEntity.getCompanies());
        when(companyService.add(MockEntity.getCompany())).thenReturn(MockEntity.getCompany());
        when(companyService.update(MockConstants.URL, MockEntity.getCompany())).thenReturn(MockEntity.getCompany());
        when(companyService.getMainCompany()).thenReturn(MockEntity.getCompany());
        when(companyService.getPartners(true)).thenReturn(MockEntity.getCompanies());
    }

    private static void initFileService() {
        fileService = mock(FileService.class);
        dateService(fileService, MockEntity.getFile(), MockEntity.getFiles());
        when(fileService.getByTitle(MockConstants.TITLE)).thenReturn(MockEntity.getFile());
        when(fileService.getByUrl(MockConstants.URL)).thenReturn(MockEntity.getFile());
        when(fileService.add(MockConstants.TITLE, null)).thenReturn(MockEntity.getFile());
        when(fileService.update(MockConstants.ID, MockConstants.TITLE, null)).thenReturn(MockEntity.getFile());
        when(fileService.deleteFile(MockConstants.PATH)).thenReturn(true);
    }

    private static void initMessageService() {
        messageService = mock(MessageService.class);
        dateService(messageService, MockEntity.getMessage(), MockEntity.getMessages());
    }

    private static void initResponseService() {
        responseService = mock(ResponseService.class);
        dateService(responseService, MockEntity.getResponse(), MockEntity.getResponses());
        when(responseService.add(MockEntity.getResponse())).thenReturn(MockEntity.getResponse());
        when(responseService.update(MockConstants.ID, MockEntity.getResponse())).thenReturn(MockEntity.getResponse());
        when(responseService.sortByDate(MockEntity.getResponses(), true)).thenReturn(MockEntity.getResponses());
        when(responseService.sortByDate(MockEntity.getResponses(), false)).thenReturn(MockEntity.getResponses());
        when(responseService.getAndSortByDate(true)).thenReturn(MockEntity.getResponses());
        when(responseService.getAndSortByDate(false)).thenReturn(MockEntity.getResponses());
        when(responseService.filterByDate(MockEntity.getResponses(), MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getResponses());
        when(responseService.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE)).thenReturn(
                MockEntity.getResponses());
    }

    private static void initUserService() {
        userService = mock(UserService.class);
        dateService(userService, MockEntity.getUser(), MockEntity.getUsers());
        when(userService.add(MockEntity.getUser())).thenReturn(MockEntity.getUser());
        when(userService.update(MockConstants.URL, MockEntity.getUser())).thenReturn(MockEntity.getUser());
        when(userService.getByName(MockConstants.NAME)).thenReturn(MockEntity.getUser());
        when(userService.getByUrl(MockConstants.URL)).thenReturn(MockEntity.getUser());
        when(userService.getByLogin(MockConstants.LOGIN)).thenReturn(MockEntity.getUser());
        when(userService.getByEmail(MockConstants.EMAIL)).thenReturn(MockEntity.getUser());
        when(userService.getAuthenticatedUser()).thenReturn(MockEntity.getUser());
        when(userService.getMainAdmin()).thenReturn(MockEntity.getUser());
        when(userService.getAdmins()).thenReturn(MockEntity.getUsers());
        when(userService.getPersonnel()).thenReturn(MockEntity.getUsers());
        when(userService.sortByName(MockEntity.getUsers(), true)).thenReturn(MockEntity.getUsers());
        when(userService.sortByName(MockEntity.getUsers(), false)).thenReturn(MockEntity.getUsers());
        when(userService.sortByUrl(MockEntity.getUsers(), true)).thenReturn(MockEntity.getUsers());
        when(userService.sortByUrl(MockEntity.getUsers(), false)).thenReturn(MockEntity.getUsers());
        when(userService.sortByRole(MockEntity.getUsers(), MockConstants.USER_ROLE, true)).thenReturn(
                MockEntity.getUsers());
        when(userService.sortByRole(MockEntity.getUsers(), MockConstants.USER_ROLE, false)).thenReturn(
                MockEntity.getUsers());
        when(userService.getAndSortByName(true)).thenReturn(MockEntity.getUsers());
        when(userService.getAndSortByName(false)).thenReturn(MockEntity.getUsers());
        when(userService.getAndSortByUrl(true)).thenReturn(MockEntity.getUsers());
        when(userService.getAndSortByUrl(false)).thenReturn(MockEntity.getUsers());
        when(userService.getAndSortByRole(MockConstants.USER_ROLE, true)).thenReturn(MockEntity.getUsers());
        when(userService.getAndSortByRole(MockConstants.USER_ROLE, false)).thenReturn(MockEntity.getUsers());
        when(userService.filterByRole(MockEntity.getUsers(), MockConstants.USER_ROLE)).thenReturn(MockEntity.getUsers());
        final List<UserRole> roles = new ArrayList<>();
        roles.add(MockConstants.USER_ROLE);
        when(userService.filterByRoles(MockEntity.getUsers(), roles)).thenReturn(MockEntity.getUsers());
        when(userService.getAndFilterByRole(MockConstants.USER_ROLE)).thenReturn(MockEntity.getUsers());
        when(userService.getAndFilterByRoles(roles)).thenReturn(MockEntity.getUsers());
    }

    private static <T extends Content, E extends ContentService<T>> void contentService(
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

    private static <T extends Model, E extends DataService<T>> void dateService(
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
                new NullPointerException("Can`t find Model by id " + MockConstants.UNKNOWN_ID + "!")
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
        siteMapModelAndView.addObject("articles", MockEntity.getArticles());
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
