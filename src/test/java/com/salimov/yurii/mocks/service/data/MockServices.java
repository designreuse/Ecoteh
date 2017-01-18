package com.salimov.yurii.mocks.service.data;

import com.salimov.yurii.entity.*;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.service.data.interfaces.*;
import com.salimov.yurii.util.comparator.ContentComparator;
import com.salimov.yurii.util.comparator.ResponseComparator;
import com.salimov.yurii.util.comparator.UserComparator;
import org.junit.Ignore;

import java.util.ArrayList;
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
        final List<Article> articles = new ArrayList<>();
        articles.add(article);
        final Category category = getCategory();
        final List<Category> categories = new ArrayList<>();
        categories.add(category);
        final ArticleService articleService = mock(ArticleService.class);
        when(
                articleService.add(article)
        ).thenReturn(article);
        when(
                articleService.add(articles)
        ).thenReturn(articles);
        when(
                articleService.update(article)
        ).thenReturn(article);
        when(
                articleService.update(articles)
        ).thenReturn(articles);
        when(
                articleService.get(ID)
        ).thenReturn(article);
        when(
                articleService.getAll()
        ).thenReturn(articles);
        when(
                articleService.getAll(true)
        ).thenReturn(articles);
        when(
                articleService.getAll(false)
        ).thenReturn(articles);
        when(
                articleService.exists(article)
        ).thenReturn(true);
        when(
                articleService.exists((Article) null)
        ).thenReturn(false);
        when(
                articleService.exists(ID)
        ).thenReturn(true);
        when(
                articleService.exists((Long) null)
        ).thenReturn(false);
        when(
                articleService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                articleService.sort(
                        articles,
                        new ContentComparator.ByTitle<>(),
                        true
                )
        ).thenReturn(articles);
        when(
                articleService.sort(
                        articles,
                        new ContentComparator.ByTitle<>(), false
                )
        ).thenReturn(articles);
        when(
                articleService.sort(
                        articles,
                        new ContentComparator.ByTitle<>()
                )
        ).thenReturn(articles);
        when(
                articleService.subList(
                        articles,
                        INDEX,
                        INDEX
                )
        ).thenReturn(articles);
        when(
                articleService.getAndSubList(
                        INDEX,
                        INDEX
                )
        ).thenReturn(articles);
        when(
                articleService.filteredByValid(articles)
        ).thenReturn(articles);

        when(
                articleService.getByTitle(TITLE, true)
        ).thenReturn(article);
        when(
                articleService.getByUrl(URL, true)
        ).thenReturn(article);
        when(
                articleService.getByTitle(TITLE, false)
        ).thenReturn(article);
        when(
                articleService.getByUrl(URL, false)
        ).thenReturn(article);
        when(
                articleService.sortByTitle(articles, true)
        ).thenReturn(articles);
        when(
                articleService.sortByTitle(articles, false)
        ).thenReturn(articles);
        when(
                articleService.sortByUrl(articles, true)
        ).thenReturn(articles);
        when(
                articleService.sortByUrl(articles, false)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByTitle(true)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByTitle(false)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByUrl(true)
        ).thenReturn(articles);
        when(
                articleService.getAndSortByUrl(false)
        ).thenReturn(articles);
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
        final List<Category> categories = new ArrayList<>();
        categories.add(category);
        final File file = getPhoto();
        final CategoryService categoryService = mock(CategoryService.class);
        when(
                categoryService.add(category)
        ).thenReturn(category);
        when(
                categoryService.add(categories)
        ).thenReturn(categories);
        when(
                categoryService.update(category)
        ).thenReturn(category);
        when(
                categoryService.update(categories)
        ).thenReturn(categories);
        when(
                categoryService.get(ID)
        ).thenReturn(category);
        when(
                categoryService.getAll()
        ).thenReturn(categories);
        when(
                categoryService.getAll(true)
        ).thenReturn(categories);
        when(
                categoryService.getAll(false)
        ).thenReturn(categories);
        when(
                categoryService.exists(category)
        ).thenReturn(true);
        when(
                categoryService.exists((Category) null)
        ).thenReturn(false);
        when(
                categoryService.exists(ID)
        ).thenReturn(true);
        when(
                categoryService.exists((Long) null)
        ).thenReturn(false);
        when(
                categoryService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                categoryService.sort(
                        categories,
                        new ContentComparator.ByTitle<>(),
                        true
                )
        ).thenReturn(categories);
        when(
                categoryService.sort(
                        categories,
                        new ContentComparator.ByTitle<>(),
                        false
                )
        ).thenReturn(categories);
        when(
                categoryService.sort(
                        categories,
                        new ContentComparator.ByTitle<>()
                )
        ).thenReturn(categories);
        when(
                categoryService.subList(
                        categories,
                        INDEX,
                        INDEX
                )
        ).thenReturn(categories);
        when(
                categoryService.getAndSubList(
                        INDEX,
                        INDEX
                )
        ).thenReturn(categories);
        when(
                categoryService.filteredByValid(categories)
        ).thenReturn(categories);

        when(
                categoryService.getByTitle(TITLE, true)
        ).thenReturn(category);
        when(
                categoryService.getByUrl(URL, true)
        ).thenReturn(category);
        when(
                categoryService.getByTitle(TITLE, false)
        ).thenReturn(category);
        when(
                categoryService.getByUrl(URL, false)
        ).thenReturn(category);
        when(
                categoryService.sortByTitle(categories, true)
        ).thenReturn(categories);
        when(
                categoryService.sortByTitle(categories, false)
        ).thenReturn(categories);
        when(
                categoryService.sortByUrl(categories, true)
        ).thenReturn(categories);
        when(
                categoryService.sortByUrl(categories, false)
        ).thenReturn(categories);
        when(
                categoryService.getAndSortByTitle(true)
        ).thenReturn(categories);
        when(
                categoryService.getAndSortByTitle(false)
        ).thenReturn(categories);
        when(
                categoryService.getAndSortByUrl(true)
        ).thenReturn(categories);
        when(
                categoryService.getAndSortByUrl(false)
        ).thenReturn(categories);
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
        when(
                companyService.add(company)
        ).thenReturn(company);
        when(
                companyService.add(companies)
        ).thenReturn(companies);
        when(
                companyService.update(company)
        ).thenReturn(company);
        when(
                companyService.update(companies)
        ).thenReturn(companies);
        when(
                companyService.get(ID)
        ).thenReturn(company);
        when(
                companyService.getAll()
        ).thenReturn(companies);
        when(
                companyService.getAll(true)
        ).thenReturn(companies);
        when(
                companyService.getAll(false)
        ).thenReturn(companies);
        when(
                companyService.exists(company)
        ).thenReturn(true);
        when(
                companyService.exists((Company) null)
        ).thenReturn(false);
        when(
                companyService.exists(ID)
        ).thenReturn(true);
        when(
                companyService.exists((Long) null)
        ).thenReturn(false);
        when(
                companyService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                companyService.sort(
                        companies,
                        new ContentComparator.ByTitle<>(),
                        true
                )
        ).thenReturn(companies);
        when(
                companyService.sort(
                        companies,
                        new ContentComparator.ByTitle<>(),
                        false
                )
        ).thenReturn(companies);
        when(
                companyService.sort(
                        companies,
                        new ContentComparator.ByTitle<>()
                )
        ).thenReturn(companies);
        when(
                companyService.subList(companies, INDEX, INDEX)
        ).thenReturn(companies);
        when(
                companyService.getAndSubList(INDEX, INDEX)
        ).thenReturn(companies);
        when(
                companyService.filteredByValid(companies)
        ).thenReturn(companies);
        when(
                companyService.getByTitle(TITLE, true)
        ).thenReturn(company);
        when(
                companyService.getByUrl(URL, true)
        ).thenReturn(company);
        when(
                companyService.getByTitle(TITLE, false)
        ).thenReturn(company);
        when(
                companyService.getByUrl(URL, false)
        ).thenReturn(company);
        when(
                companyService.sortByTitle(companies, true)
        ).thenReturn(companies);
        when(
                companyService.sortByTitle(companies, false)
        ).thenReturn(companies);
        when(
                companyService.sortByUrl(companies, true)
        ).thenReturn(companies);
        when(
                companyService.sortByUrl(companies, false)
        ).thenReturn(companies);
        when(
                companyService.getAndSortByTitle(true)
        ).thenReturn(companies);
        when(
                companyService.getAndSortByTitle(false)
        ).thenReturn(companies);
        when(
                companyService.getAndSortByUrl(true)
        ).thenReturn(companies);
        when(
                companyService.getAndSortByUrl(false)
        ).thenReturn(companies);
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
        final List<File> files = new ArrayList<>();
        files.add(file);
        final FileService fileService = mock(FileService.class);
        when(
                fileService.add(file)
        ).thenReturn(file);
        when(
                fileService.add(files)
        ).thenReturn(files);
        when(
                fileService.update(file)
        ).thenReturn(file);
        when(
                fileService.update(files)
        ).thenReturn(files);
        when(
                fileService.get(ID)
        ).thenReturn(file);
        when(
                fileService.getAll()
        ).thenReturn(files);
        when(
                fileService.getAll(true)
        ).thenReturn(files);
        when(
                fileService.getAll(false)
        ).thenReturn(files);
        when(
                fileService.exists(file)
        ).thenReturn(true);
        when(
                fileService.exists((File) null)
        ).thenReturn(false);
        when(
                fileService.exists(ID)).thenReturn(true);
        when(
                fileService.exists((Long) null)
        ).thenReturn(false);
        when(
                fileService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                fileService.subList(files, INDEX, INDEX)
        ).thenReturn(files);
        when(
                fileService.getAndSubList(INDEX, INDEX)
        ).thenReturn(files);
        when(
                fileService.filteredByValid(files)
        ).thenReturn(files);
        when(
                fileService.getByTitle(TITLE)
        ).thenReturn(file);
        when(
                fileService.getByUrl(URL)
        ).thenReturn(file);
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

    private static ResponseService initResponseService() {
        final Response response = getResponse();
        final List<Response> responses = new ArrayList<>();
        responses.add(response);
        final ResponseService responseService = mock(ResponseService.class);
        when(
                responseService.add(response)
        ).thenReturn(response);
        when(
                responseService.add(responses)
        ).thenReturn(responses);
        when(
                responseService.update(response)
        ).thenReturn(response);
        when(
                responseService.update(responses)
        ).thenReturn(responses);
        when(
                responseService.get(ID)
        ).thenReturn(response);
        when(
                responseService.getAll()
        ).thenReturn(responses);
        when(
                responseService.getAll(true)
        ).thenReturn(responses);
        when(
                responseService.getAll(false)
        ).thenReturn(responses);
        when(
                responseService.exists(response)
        ).thenReturn(true);
        when(
                responseService.exists((Response) null)
        ).thenReturn(false);
        when(
                responseService.exists(ID)
        ).thenReturn(true);
        when(
                responseService.exists((Long) null)
        ).thenReturn(false);
        when(
                responseService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                responseService.sort(responses,
                        new ResponseComparator.ByDate(),
                        true
                )
        ).thenReturn(responses);
        when(
                responseService.sort(
                        responses,
                        new ResponseComparator.ByDate(),
                        false
                )
        ).thenReturn(responses);
        when(
                responseService.sort(
                        responses,
                        new ResponseComparator.ByDate()
                )
        ).thenReturn(responses);
        when(
                responseService.subList(responses, INDEX, INDEX)
        ).thenReturn(responses);
        when(
                responseService.getAndSubList(INDEX, INDEX)
        ).thenReturn(responses);
        when(
                responseService.filteredByValid(responses)
        ).thenReturn(responses);
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
        final List<User> users = new ArrayList<>();
        users.add(user);
        final List<UserRole> roles = new ArrayList<>();
        roles.add(USER_ROLE);
        final UserService userService = mock(UserService.class);
        when(
                userService.add(user)
        ).thenReturn(user);
        when(
                userService.add(users)
        ).thenReturn(users);
        when(
                userService.update(user)
        ).thenReturn(user);
        when(
                userService.update(users)
        ).thenReturn(users);
        when(
                userService.get(ID)
        ).thenReturn(user);
        when(
                userService.getAll()
        ).thenReturn(users);
        when(
                userService.getAll(true)
        ).thenReturn(users);
        when(
                userService.getAll(false)
        ).thenReturn(users);
        when(
                userService.exists(user)
        ).thenReturn(true);
        when(
                userService.exists((User) null)
        ).thenReturn(false);
        when(
                userService.exists(ID)
        ).thenReturn(true);
        when(
                userService.exists((Long) null)
        ).thenReturn(false);
        when(
                userService.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                userService.sort(
                        users,
                        new UserComparator.ByName(),
                        true
                )
        ).thenReturn(users);
        when(
                userService.sort(
                        users,
                        new UserComparator.ByName(),
                        false
                )
        ).thenReturn(users);
        when(
                userService.sort(
                        users,
                        new UserComparator.ByName()
                )
        ).thenReturn(users);
        when(
                userService.subList(users, INDEX, INDEX)
        ).thenReturn(users);
        when(
                userService.getAndSubList(INDEX, INDEX)
        ).thenReturn(users);
        when(
                userService.filteredByValid(users)
        ).thenReturn(users);
        when(
                userService.initAndAdd(
                        NAME,
                        LOGIN,
                        PASSWORD,
                        DESCRIPTION,
                        PHONE,
                        EMAIL,
                        VKONTAKTE,
                        FACEBOOK,
                        TWITTER,
                        SKYPE,
                        null, true, true, true)
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
                userService.sortByRole(users, USER_ROLE, true)
        ).thenReturn(users);
        when(
                userService.sortByRole(users, USER_ROLE, false)
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
}
