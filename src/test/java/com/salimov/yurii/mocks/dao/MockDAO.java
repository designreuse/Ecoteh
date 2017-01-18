package com.salimov.yurii.mocks.dao;

import com.salimov.yurii.dao.interfaces.*;
import com.salimov.yurii.entity.*;
import org.junit.Ignore;

import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public final class MockDAO {

    private static ArticleDao articleDao;
    private static CategoryDao categoryDao;
    private static CompanyDao companyDao;
    private static FileDao fileDAO;
    private static MessageDao messageDao;
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

    public static MessageDao getMessageDAO() {
        if (messageDao == null) {
            messageDao = initMessageDAO();
        }
        return messageDao;
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
        final Article article = getArticle();
        final List<Article> articles = getArticles();
        final ArticleDao dao = mock(ArticleDao.class);
        dataDao(
                dao,
                article,
                articles
        );
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
        final Category category = getCategory();
        final CategoryDao dao = mock(CategoryDao.class);
        dataDao(
                dao,
                category,
                getCategories()
        );
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
        final Company company = getCompany();
        final List<Company> companies = getCompanies();
        final CompanyDao dao = mock(CompanyDao.class);
        dataDao(
                dao,
                company,
                companies
        );
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
        final File file = getPhoto();
        final FileDao dao = mock(FileDao.class);
        dataDao(
                dao,
                file,
                getPhotos()
        );
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

    private static MessageDao initMessageDAO() {
        final MessageDao dao = mock(MessageDao.class);
        dataDao(
                dao,
                getMessage(),
                getMessages()
        );
        return dao;
    }

    private static ResponseDao initResponseDAO() {
        final ResponseDao dao = mock(ResponseDao.class);
        dataDao(
                dao,
                getResponse(),
                getResponses()
        );
        return dao;
    }

    private static UserDao initUserDAO() {
        final User user = getUser();
        final UserDao dao = mock(UserDao.class);
        dataDao(
                dao,
                user,
                getUsers()
        );
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

    private static <T extends Model, E extends DataDao> void dataDao(
            final E dao,
            final T model,
            final Collection<T> models
    ) {
        when(
                dao.add(model)
        ).thenReturn(model);
        when(
                dao.add(null)
        ).thenReturn(null);
        when(
                dao.addAll(models)
        ).thenReturn(models);
        when(
                dao.addAll(null)
        ).thenReturn(null);
        when(
                dao.update(model)
        ).thenReturn(model);
        when(
                dao.update(null)
        ).thenReturn(null);
        when(
                dao.get(ID)
        ).thenReturn(model);
        when(
                dao.get(UNKNOWN_ID)
        ).thenReturn(null);
        when(
                dao.get(null)
        ).thenReturn(null);
        when(
                dao.getAll()
        ).thenReturn(models);
        when(
                dao.exists(ID)
        ).thenReturn(true);
        when(
                dao.exists(UNKNOWN_ID)
        ).thenReturn(false);
        when(
                dao.exists(null)
        ).thenReturn(false);
    }
}
