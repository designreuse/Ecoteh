package com.salimov.yurii.mocks.dao;

import com.salimov.yurii.dao.interfaces.*;
import com.salimov.yurii.entity.*;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;

import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public final class MockDAO {

    private static ArticleDao articleDao;
    private static CategoryDao categoryDao;
    private static CompanyDao companyDao;
    private static FileDao fileDAO;
    private static ResponseDao responseDAO;
    private static UserDao userDAO;

    public static ArticleDao getArticleDao() {
        if (articleDao == null) {
            articleDao = initArticleDAO();
        }
        return articleDao;
    }

    public static CategoryDao getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = initCategoryDAO();
        }
        return categoryDao;
    }

    public static CompanyDao getCompanyDao() {
        if (companyDao == null) {
            companyDao = initCompanyDAO();
        }
        return companyDao;
    }

    public static FileDao getFileDAO() {
        if (fileDAO == null) {
            fileDAO = initPhotoDAO();
        }
        return fileDAO;
    }

    public static ResponseDao getResponseDAO() {
        if (responseDAO == null) {
            responseDAO = initResponseDAO();
        }
        return responseDAO;
    }

    public static UserDao getUserDAO() {
        if (userDAO == null) {
            userDAO = initUserDAO();
        }
        return userDAO;
    }

    private static ArticleDao initArticleDAO() {
        final Article article = MockEntity.getArticle();
        final List<Article> articles = MockEntity.getArticles();
        final ArticleDao dao = mock(ArticleDao.class);
        when(
                dao.add(article)
        ).thenReturn(article);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(articles)
        ).thenReturn(articles);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(article)
        ).thenReturn(article);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(article);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(articles);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(article);
        when(
                dao.getByTitle(null)
        ).thenReturn(null);
        when(
                dao.getByTitle(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(article);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByNumber(NUMBER)
        ).thenReturn(article);
        when(
                dao.getByNumber(null)
        ).thenReturn(null);
        when(
                dao.getByNumber(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByCategoryId(ID)
        ).thenReturn(articles);
        when(
                dao.getByCategoryId(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.getByCategoryId(null)
        ).thenReturn(null);
        return dao;
    }

    private static CategoryDao initCategoryDAO() {
        final Category category = MockEntity.getCategory();
        final List<Category> categories = MockEntity.getCategories();
        final CategoryDao dao = mock(CategoryDao.class);
        when(
                dao.add(category)
        ).thenReturn(category);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(categories)
        ).thenReturn(categories);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(category)
        ).thenReturn(category);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(category);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(categories);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(category);
        when(
                dao.getByTitle(null)
        ).thenReturn(null);
        when(
                dao.getByTitle(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(category);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        return dao;
    }

    private static CompanyDao initCompanyDAO() {
        final Company company = MockEntity.getCompany();
        final List<Company> companies = MockEntity.getCompanies();
        final CompanyDao dao = mock(CompanyDao.class);
        when(
                dao.add(company)
        ).thenReturn(company);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(companies)
        ).thenReturn(companies);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(company)
        ).thenReturn(company);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(company);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(companies);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(company);
        when(
                dao.getByTitle(null)
        ).thenReturn(null);
        when(
                dao.getByTitle(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(company);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByType(COMPANY_TYPE)
        ).thenReturn(companies);
        when(
                dao.getByType(null)
        ).thenReturn(null);
        return dao;
    }

    private static FileDao initPhotoDAO() {
        final File file = MockEntity.getPhoto();
        final List<File> files = MockEntity.getPhotos();
        final FileDao dao = mock(FileDao.class);
        when(
                dao.add(file)
        ).thenReturn(file);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(files)
        ).thenReturn(files);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(file)
        ).thenReturn(file);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(file);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(files);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(file);
        when(
                dao.getByTitle(null)
        ).thenReturn(null);
        when(
                dao.getByTitle(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(file);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        return dao;
    }

    private static ResponseDao initResponseDAO() {
        final Response response = MockEntity.getResponse();
        final List<Response> responses = MockEntity.getResponses();
        final ResponseDao dao = mock(ResponseDao.class);
        when(
                dao.add(response)
        ).thenReturn(response);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(responses)
        ).thenReturn(responses);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(response)
        ).thenReturn(response);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(response);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(responses);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        return dao;
    }

    private static UserDao initUserDAO() {
        final User user = MockEntity.getUser();
        final List<User> users = MockEntity.getUsers();
        final UserDao dao = mock(UserDao.class);
        when(
                dao.add(user)
        ).thenReturn(user);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(users)
        ).thenReturn(users);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(user)
        ).thenReturn(user);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(user);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(users);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
        when(
                dao.getByName(NAME)
        ).thenReturn(user);
        when(
                dao.getByName(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByName(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(user);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
        when(
                dao.getByLogin(LOGIN)
        ).thenReturn(user);
        when(
                dao.getByLogin(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByLogin(null)
        ).thenReturn(null);
        when(
                dao.getByPhone(PHONE)
        ).thenReturn(user);
        when(
                dao.getByPhone(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByPhone(null)
        ).thenReturn(null);
        when(
                dao.getByEmail(EMAIL)
        ).thenReturn(user);
        when(
                dao.getByEmail(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByEmail(null)
        ).thenReturn(null);
        return dao;
    }
}
