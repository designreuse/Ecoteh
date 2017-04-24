package com.salimov.ecoteh.mocks.service.data;

import com.salimov.ecoteh.entity.*;
import com.salimov.ecoteh.enums.UserRole;
import com.salimov.ecoteh.service.data.*;
import com.salimov.ecoteh.service.search.SearchService;
import com.salimov.ecoteh.service.seo.SeoService;
import com.salimov.ecoteh.util.comparator.ContentComparator;
import org.junit.Ignore;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.*;
import static com.salimov.ecoteh.mocks.properties.MockContentProperties.getContentProperties;
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
        contentService(articleService, getArticle(), getArticles());
        when(articleService.update(URL, getArticle())).thenReturn(getArticle());
        when(articleService.getByNumber(NUMBER, true)).thenReturn(getArticle());
        when(articleService.getByNumber(NUMBER, false)).thenReturn(getArticle());
        when(articleService.sortByNumber(getArticles(), true)).thenReturn(getArticles());
        when(articleService.sortByNumber(getArticles(), false)).thenReturn(getArticles());
        when(articleService.sortByDate(getArticles(), true)).thenReturn(getArticles());
        when(articleService.sortByDate(getArticles(), false)).thenReturn(getArticles());
        when(articleService.getAndSortByNumber(true)).thenReturn(getArticles());
        when(articleService.getAndSortByNumber(false)).thenReturn(getArticles());
        when(articleService.getAndSortByDate(true)).thenReturn(getArticles());
        when(articleService.getAndSortByDate(false)).thenReturn(getArticles());
        when(articleService.filterByDate(getArticles(), DATE, DATE)).thenReturn(getArticles());
        when(articleService.filterByCategory(getArticles(), getCategory())).thenReturn(getArticles());
        when(articleService.filterByCategories(getArticles(), getCategories())).thenReturn(getArticles());
        when(articleService.getAndFilterByDate(DATE, DATE)).thenReturn(getArticles());
        when(articleService.getAndFilterByCategory(getCategory())).thenReturn(getArticles());
        when(articleService.getAndFilterByCategories(getCategories())).thenReturn(getArticles());
    }

    private static void initCategoryService() {
        categoryService = mock(CategoryService.class);
        contentService(categoryService, getCategory(), getCategories());
        when(categoryService.update(URL, getCategory())).thenReturn(getCategory());
    }

    private static void initCompanyService() {
        companyService = mock(CompanyService.class);
        contentService(companyService, getCompany(), getCompanies());
        when(companyService.add(getCompany())).thenReturn(getCompany());
        when(companyService.update(URL, getCompany())).thenReturn(getCompany());
        when(companyService.getMainCompany()).thenReturn(getCompany());
        when(companyService.getPartners(true)).thenReturn(getCompanies());
    }

    private static void initFileService() {
        fileService = mock(FileService.class);
        dateService(fileService, getFile(), getFiles());
        when(fileService.getByTitle(TITLE)).thenReturn(getFile());
        when(fileService.getByUrl(URL)).thenReturn(getFile());
        when(fileService.add(TITLE, null)).thenReturn(getFile());
        when(fileService.update(ID, TITLE, null)).thenReturn(getFile());
        when(fileService.deleteFile(PATH)).thenReturn(true);
    }

    private static void initMessageService() {
        messageService = mock(MessageService.class);
        dateService(messageService, getMessage(), getMessages());
    }

    private static void initResponseService() {
        responseService = mock(ResponseService.class);
        dateService(responseService, getResponse(), getResponses());
        when(responseService.add(getResponse())).thenReturn(getResponse());
        when(responseService.update(ID, getResponse())).thenReturn(getResponse());
        when(responseService.sortByDate(getResponses(), true)).thenReturn(getResponses());
        when(responseService.sortByDate(getResponses(), false)).thenReturn(getResponses());
        when(responseService.getAndSortByDate(true)).thenReturn(getResponses());
        when(responseService.getAndSortByDate(false)).thenReturn(getResponses());
        when(responseService.filterByDate(getResponses(), DATE, DATE)).thenReturn(getResponses());
        when(responseService.getAndFilterByDate(DATE, DATE)).thenReturn(getResponses());
    }

    private static void initUserService() {
        userService = mock(UserService.class);
        dateService(userService, getUser(), getUsers());
        when(userService.add(getUser())).thenReturn(getUser());
        when(userService.update(URL, getUser())).thenReturn(getUser());
        when(userService.getByName(NAME)).thenReturn(getUser());
        when(userService.getByUrl(URL)).thenReturn(getUser());
        when(userService.getByLogin(LOGIN)).thenReturn(getUser());
        when(userService.getByEmail(EMAIL)).thenReturn(getUser());
        when(userService.getAuthenticatedUser()).thenReturn(getUser());
        when(userService.getMainAdmin()).thenReturn(getUser());
        when(userService.getAdmins()).thenReturn(getUsers());
        when(userService.getPersonnel()).thenReturn(getUsers());
        when(userService.sortByName(getUsers(), true)).thenReturn(getUsers());
        when(userService.sortByName(getUsers(), false)).thenReturn(getUsers());
        when(userService.sortByUrl(getUsers(), true)).thenReturn(getUsers());
        when(userService.sortByUrl(getUsers(), false)).thenReturn(getUsers());
        when(userService.sortByRole(getUsers(), USER_ROLE, true)).thenReturn(getUsers());
        when(userService.sortByRole(getUsers(), USER_ROLE, false)).thenReturn(getUsers());
        when(userService.getAndSortByName(true)).thenReturn(getUsers());
        when(userService.getAndSortByName(false)).thenReturn(getUsers());
        when(userService.getAndSortByUrl(true)).thenReturn(getUsers());
        when(userService.getAndSortByUrl(false)).thenReturn(getUsers());
        when(userService.getAndSortByRole(USER_ROLE, true)).thenReturn(getUsers());
        when(userService.getAndSortByRole(USER_ROLE, false)).thenReturn(getUsers());
        when(userService.filterByRole(getUsers(), USER_ROLE)).thenReturn(getUsers());
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        when(userService.filterByRoles(getUsers(), roles)).thenReturn(getUsers());
        when(userService.getAndFilterByRole(USER_ROLE)).thenReturn(getUsers());
        when(userService.getAndFilterByRoles(roles)).thenReturn(getUsers());
    }

    private static <T extends Content, E extends ContentService<T>> void contentService(
            final E service,
            final T content,
            final Collection<T> contents
    ) {
        dateService(service, content, contents);
        when(service.getByTitle(TITLE, true)).thenReturn(content);
        when(service.getByTitle(TITLE, false)).thenReturn(content);
        when(service.getByTitle(ANY_STRING, true)).thenThrow(NullPointerException.class);
        when(service.getByTitle(ANY_STRING, false)).thenThrow(NullPointerException.class);
        when(service.getByTitle(null, true)).thenThrow(IllegalArgumentException.class);
        when(service.getByTitle(null, false)).thenThrow(IllegalArgumentException.class);
        when(service.getByUrl(URL, true)).thenReturn(content);
        when(service.getByUrl(URL, false)).thenReturn(content);
        when(service.getByUrl(ANY_STRING, true)).thenThrow(NullPointerException.class);
        when(service.getByUrl(ANY_STRING, false)).thenThrow(NullPointerException.class);
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
        when(service.addAll(models)).thenReturn(models);
        when(service.update(model)).thenReturn(model);
        when(service.update(models)).thenReturn(models);
        when(service.get(ID)).thenReturn(model);
        when(service.get(UNKNOWN_ID)).thenThrow(
                new NullPointerException("Can`t find Model by id " + UNKNOWN_ID + "!")
        );
        when(service.getAll()).thenReturn(models);
        when(service.getAll(true)).thenReturn(models);
        when(service.getAll(false)).thenReturn(models);
        when(service.exists(model)).thenReturn(true);
        when(service.exists((T) null)).thenReturn(false);
        when(service.exists(ID)).thenReturn(true);
        when(service.exists(UNKNOWN_ID)).thenReturn(false);
        when(service.exists((T) null)).thenReturn(false);
        final T temp = (T) model.clone();
        when(service.exists(temp)).thenReturn(false);
        when(service.exists(model)).thenReturn(true);
        when(service.subList(models, INDEX, INDEX)).thenReturn(new ArrayList<T>(models));
        when(service.getAndSubList(INDEX, INDEX)).thenReturn(new ArrayList<T>(models));
        when(service.filteredByValid(models)).thenReturn(new ArrayList<T>(models));
    }

    private static void initSearchService() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("all", true);
        modelAndView.setViewName("client/search/result_page");
        searchService = mock(SearchService.class);
        when(searchService.search(KEYWORDS, "all", false)).thenReturn(modelAndView);
    }

    private static void initSeoService() {
        final ModelAndView robotsModelAndView = new ModelAndView();
        robotsModelAndView.addObject("domain", DOMAIN);
        robotsModelAndView.setViewName("seo/robots");
        final ModelAndView siteMapModelAndView = new ModelAndView();
        siteMapModelAndView.addObject("domain", DOMAIN);
        siteMapModelAndView.addObject("categories", getCategories());
        siteMapModelAndView.addObject("articles", getArticles());
        siteMapModelAndView.addObject("companies", getCompanies());
        siteMapModelAndView.setViewName("seo/sitemap");
        seoService = mock(SeoService.class);
        when(seoService.getRobotsTxt()).thenReturn(robotsModelAndView);
        when(seoService.getSiteMapXml()).thenReturn(siteMapModelAndView);
    }

    private static void initStyleService() {
        styleService = new StyleServiceImpl(getContentProperties());
    }
}
