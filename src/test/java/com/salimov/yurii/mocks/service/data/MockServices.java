package com.salimov.yurii.mocks.service.data;

import com.salimov.yurii.entity.*;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.util.comparator.ContentComparator;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
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

    public static ArticleService getArticleService() {
        if (articleService == null) {
            articleService = initArticleService();
        }
        return MockServices.articleService;
    }

    public static CategoryService getCategoryService() {
        if (categoryService == null) {
            categoryService = initCategoryService();
        }
        return categoryService;
    }

    public static CompanyService getCompanyService() {
        if (companyService == null) {
            companyService = initCompanyService();
        }
        return companyService;
    }

    public static FileService getFileService() {
        if (fileService == null) {
            fileService = initPhotoService();
        }
        return fileService;
    }

    public static MessageService getMessageService() {
        if (messageService == null) {
            messageService = initMessageService();
        }
        return messageService;
    }

    public static ResponseService getResponseService() {
        if (responseService == null) {
            responseService = initResponseService();
        }
        return responseService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = initUserService();
        }
        return userService;
    }

    private static ArticleService initArticleService() {
        final Article article = getArticle();
        final List<Article> articles = getArticles();
        final Category category = getCategory();
        final List<Category> categories = getCategories();
        final ArticleService articleService = mock(ArticleService.class);
        contentService(articleService, article, articles);
        when(
                articleService.initAndAdd(
                        TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER,
                        null, true
                )
        ).thenReturn(article);
        when(
                articleService.initAndUpdate(
                        URL, TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER,
                        null, true
                )
        ).thenReturn(article);
        when(
                articleService.getByNumber(NUMBER, true)
        ).thenReturn(article);
        when(
                articleService.getByNumber(NUMBER, false)
        ).thenReturn(article);
        when(
                articleService.sortByNumber(articles, true)
        ).thenReturn(articles);
        when(
                articleService.sortByNumber(articles, false)
        ).thenReturn(articles);
        when(
                articleService.sortByDate(articles, true)
        ).thenReturn(articles);
        when(
                articleService.sortByDate(articles, false)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByNumber(true)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByNumber(false)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByDate(true)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByDate(false)
        ).thenReturn(articles);
        when(
                articleService.filterByDate(
                        articles,
                        DATE,
                        DATE
                )
        ).thenReturn(articles);
        when(
                articleService.filterByCategory(articles, category)
        ).thenReturn(articles);
        when(
                articleService.filterByCategories(articles, categories)
        ).thenReturn(articles);
        when(
                articleService.getAndFilterByDate(
                        DATE,
                        DATE
                )
        ).thenReturn(articles);
        when(
                articleService.getAndFilterByCategory(category)
        ).thenReturn(articles);
        when(
                articleService.getAndFilterByCategories(categories)
        ).thenReturn(articles);
        return articleService;
    }

    private static CategoryService initCategoryService() {
        final Category category = getCategory();
        final CategoryService categoryService = mock(CategoryService.class);
        contentService(categoryService, category, getCategories());
        when(
                categoryService.initAndAdd(
                        TITLE,
                        DESCRIPTION, KEYWORDS,
                        null, true)
        ).thenReturn(category);
        when(
                categoryService.initAndUpdate(
                        URL, TITLE,
                        DESCRIPTION, KEYWORDS,
                        null,
                        true)
        ).thenReturn(category);
        return categoryService;
    }

    private static CompanyService initCompanyService() {
        final Company company = getCompany();
        final List<Company> companies = new ArrayList<>();
        companies.add(company);
        final CompanyService companyService = mock(CompanyService.class);
        contentService(companyService, company, companies);
        when(
                companyService.initAndAdd(
                        TITLE, DOMAIN,
                        TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS,
                        null,
                        true
                )
        ).thenReturn(company);
        when(
                companyService.initAndUpdate(
                        URL, TITLE, DOMAIN,
                        TAGLINE, DESCRIPTION, INFORMATION, KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS,
                        PHOTO_URL,
                        true
                )
        ).thenReturn(company);
        when(
                companyService.getMainCompany()
        ).thenReturn(company);
        when(
                companyService.getMainCompanyToHome()
        ).thenReturn(company);
        when(
                companyService.getPartners(true)
        ).thenReturn(companies);
        return companyService;
    }

    private static FileService initPhotoService() {
        final File file = getPhoto();
        final FileService fileService = mock(FileService.class);
        mediaService(fileService, file, getPhotos());
        when(
                fileService.initAndAdd(TITLE, URL, null)
        ).thenReturn(file);
        when(
                fileService.initAndUpdate(ID, TITLE, URL, null)
        ).thenReturn(file);
        when(
                fileService.deleteFile(PATH)
        ).thenReturn(true);
        when(
                fileService.updatePhoto(file, null, TITLE, PATH)
        ).thenReturn(file);
        return fileService;
    }

    private static MessageService initMessageService() {
        final MessageService messageService = mock(MessageService.class);
        dateService(
                messageService,
                getMessage(),
                getMessages()
        );
        return messageService;
    }

    private static ResponseService initResponseService() {
        final Response response = getResponse();
        final List<Response> responses = getResponses();
        final ResponseService responseService = mock(ResponseService.class);
        dateService(responseService, response, responses);
        when(
                responseService.initAndAdd(NAME, TEXT)
        ).thenReturn(response);
        when(
                responseService.initAndUpdate(ID, NAME, TEXT)
        ).thenReturn(response);
        when(
                responseService.sortByDate(responses, true)
        ).thenReturn(responses);
        when(
                responseService.sortByDate(responses, false)
        ).thenReturn(responses);
        when(
                responseService.getAndSortByDate(true)
        ).thenReturn(responses);
        when(
                responseService.getAndSortByDate(false)
        ).thenReturn(responses);
        when(
                responseService.filterByDate(responses, DATE, DATE)
        ).thenReturn(responses);
        when(
                responseService.getAndFilterByDate(DATE, DATE)
        ).thenReturn(responses);
        return responseService;
    }

    private static UserService initUserService() {
        final User user = getUser();
        final List<User> users = getUsers();
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        final UserService userService = mock(UserService.class);
        dateService(userService, user, users);
        when(
                userService.initAndAdd(
                        NAME, LOGIN, PASSWORD,
                        DESCRIPTION,
                        PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL,
                        true, true, true)
        ).thenReturn(user);
        when(
                userService.initAndUpdate(
                        URL, NAME,
                        LOGIN, PASSWORD,
                        DESCRIPTION,
                        PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        PHOTO_URL,
                        true, true, true
                )
        ).thenReturn(user);
        when(
                userService.getByName(NAME)
        ).thenReturn(user);
        when(
                userService.getByUrl(URL)
        ).thenReturn(user);
        when(
                userService.getByLogin(LOGIN)
        ).thenReturn(user);
        when(
                userService.getByEmail(EMAIL)
        ).thenReturn(user);
        when(
                userService.getByPhone(PHONE)
        ).thenReturn(user);
        when(
                userService.getAuthenticatedUser()
        ).thenReturn(user);
        when(
                userService.getMainAdmin()
        ).thenReturn(user);
        when(
                userService.getAdmins()
        ).thenReturn(users);
        when(
                userService.getPersonnel()
        ).thenReturn(users);
        when(
                userService.sortByName(users, true)
        ).thenReturn(users);
        when(
                userService.sortByName(users, false)
        ).thenReturn(users);
        when(
                userService.sortByUrl(users, true)
        ).thenReturn(users);
        when(
                userService.sortByUrl(users, false)
        ).thenReturn(users);
        when(
                userService.sortByRole(
                        users, USER_ROLE, true
                )
        ).thenReturn(users);
        when(
                userService.sortByRole(
                        users, USER_ROLE, false
                )
        ).thenReturn(users);
        when(
                userService.getAndSortByName(true)
        ).thenReturn(users);
        when(
                userService.getAndSortByName(false)
        ).thenReturn(users);
        when(
                userService.getAndSortByUrl(true)
        ).thenReturn(users);
        when(
                userService.getAndSortByUrl(false)
        ).thenReturn(users);
        when(
                userService.getAndSortByRole(USER_ROLE, true)
        ).thenReturn(users);
        when(
                userService.getAndSortByRole(USER_ROLE, false)
        ).thenReturn(users);
        when(
                userService.filterByRole(users, USER_ROLE)
        ).thenReturn(users);
        when(
                userService.filterByRoles(users, roles)
        ).thenReturn(users);
        when(
                userService.getAndFilterByRole(USER_ROLE)
        ).thenReturn(users);
        when(
                userService.getAndFilterByRoles(roles)
        ).thenReturn(users);
        return userService;
    }

    private static <T extends Content, E extends ContentService> void contentService(
            final E service,
            final T content,
            final Collection<T> contents
    ) {
        dateService(service, content, contents);
        when(
                service.sort(
                        contents,
                        new ContentComparator.ByTitle<>(),
                        true
                )
        ).thenReturn(
                new ArrayList(contents)
        );
        when(
                service.sort(
                        contents,
                        new ContentComparator.ByTitle<>(), false
                )
        ).thenReturn(
                new ArrayList(contents)
        );
        when(
                service.sort(
                        contents,
                        new ContentComparator.ByTitle<>()
                )
        ).thenReturn(
                new ArrayList(contents)
        );
    }

    private static <T extends Media, E extends MediaService> void mediaService(
            final E service,
            final T media,
            final Collection<T> medias
    ) {
        dateService(service, media, medias);
        when(
                service.getByTitle(TITLE)
        ).thenReturn(media);
        when(
                service.getByUrl(URL)
        ).thenReturn(media);
    }

    private static <T extends Model, E extends DataService> void dateService(
            final E service,
            final T model,
            final Collection<T> models
    ) {
        when(
                service.add(model)
        ).thenReturn(model);
        when(
                service.add(models)
        ).thenReturn(models);
        when(
                service.update(model)
        ).thenReturn(model);
        when(
                service.update(models)
        ).thenReturn(models);
        when(
                service.get(ID)
        ).thenReturn(model);
        when(
                service.getAll()
        ).thenReturn(models);
        when(
                service.getAll(true)
        ).thenReturn(models);
        when(
                service.getAll(false)
        ).thenReturn(models);
        when(
                service.exists(model)
        ).thenReturn(true);
        when(
                service.exists((Article) null)
        ).thenReturn(false);
        when(
                service.exists(ID)
        ).thenReturn(true);
        when(
                service.exists((Long) null)
        ).thenReturn(false);
        when(
                service.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                service.subList(
                        models,
                        INDEX,
                        INDEX
                )
        ).thenReturn(
                new ArrayList(models)
        );
        when(
                service.getAndSubList(
                        INDEX,
                        INDEX
                )
        ).thenReturn(
                new ArrayList(models)
        );
        when(
                service.filteredByValid(models)
        ).thenReturn(
                new ArrayList(models)
        );
    }
}
