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
public final class MockDao {

    private static ArticleDao articleDao;
    private static CategoryDao categoryDao;
    private static CompanyDao companyDao;
    private static FileDao fileDao;
    private static MessageDao messageDao;
    private static ResponseDao responseDao;
    private static UserDao userDao;

    public static ArticleDao getArticleDao() {
        if (articleDao == null) {
            articleDao = initArticleDao();
        }
        return articleDao;
    }

    public static CategoryDao getCategoryDao() {
        if (categoryDao == null) {
            categoryDao = initCategoryDao();
        }
        return categoryDao;
    }

    public static CompanyDao getCompanyDao() {
        if (companyDao == null) {
            companyDao = initCompanyDao();
        }
        return companyDao;
    }

    public static FileDao getFileDao() {
        if (fileDao == null) {
            fileDao = initFileDao();
        }
        return fileDao;
    }

    public static MessageDao getMessageDao() {
        if (messageDao == null) {
            messageDao = initMessageDao();
        }
        return messageDao;
    }

    public static ResponseDao getResponseDao() {
        if (responseDao == null) {
            responseDao = initResponseDao();
        }
        return responseDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = initUserDao();
        }
        return userDao;
    }

    private static ArticleDao initArticleDao() {
        final Article article = getArticle();
        final List<Article> articles = getArticles();
        final ArticleDao dao = mock(ArticleDao.class);
        contentDao(
                dao,
                article,
                new Article(),
                articles
        );
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

    private static CategoryDao initCategoryDao() {
        final CategoryDao dao = mock(CategoryDao.class);
        contentDao(
                dao,
                getCategory(),
                new Category(),
                getCategories()
        );
        return dao;
    }

    private static CompanyDao initCompanyDao() {
        final List<Company> companies = getCompanies();
        final CompanyDao dao = mock(CompanyDao.class);
        contentDao(
                dao,
                getCompany(),
                new Company(),
                companies
        );
        when(
                dao.getByType(COMPANY_TYPE)
        ).thenReturn(companies);
        when(
                dao.getByType(null)
        ).thenReturn(null);
        return dao;
    }

    private static FileDao initFileDao() {
        final File file = getFile();
        final FileDao dao = mock(FileDao.class);
        dataDao(
                dao,
                file,
                new File(),
                getFiles()
        );
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(file);
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

    private static MessageDao initMessageDao() {
        final MessageDao Dao = mock(MessageDao.class);
        dataDao(
                Dao,
                getMessage(),
                new Message(),
                getMessages()
        );
        return Dao;
    }

    private static ResponseDao initResponseDao() {
        final ResponseDao dao = mock(ResponseDao.class);
        dataDao(
                dao,
                getResponse(),
                new Response(),
                getResponses()
        );
        return dao;
    }

    private static UserDao initUserDao() {
        final User user = getUser();
        final UserDao dao = mock(UserDao.class);
        dataDao(
                dao, user,
                new User(),
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

    private static <T extends Content<Long>, E extends ContentDao<T, Long>> void contentDao(
            final E dao,
            final T content,
            final T emptyContent,
            final Collection<T> contents
    ) {
        dataDao(
                dao,
                content,
                emptyContent,
                contents
        );
        when(
                dao.getByTitle(TITLE)
        ).thenReturn(content);
        when(
                dao.getByTitle(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByTitle(null)
        ).thenReturn(null);
        when(
                dao.getByUrl(URL)
        ).thenReturn(content);
        when(
                dao.getByUrl(ANY_STRING)
        ).thenReturn(null);
        when(
                dao.getByUrl(null)
        ).thenReturn(null);
    }

    private static <T extends Model<Long>, E extends DataDao<T, Long>> void dataDao(
            final E dao,
            final T model,
            final T emptyModel,
            final Collection<T> models
    ) {
        when(
                dao.add(model)
        ).thenReturn(model);
        when(
                dao.add(emptyModel)
        ).thenReturn(emptyModel);
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
                dao.update(emptyModel)
        ).thenReturn(emptyModel);
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
