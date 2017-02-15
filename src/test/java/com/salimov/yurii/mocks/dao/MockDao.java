package com.salimov.yurii.mocks.dao;

import com.salimov.yurii.dao.interfaces.*;
import com.salimov.yurii.entity.*;
import com.salimov.yurii.enums.CompanyType;
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
            initArticleDao();
        }
        return articleDao;
    }

    public static CategoryDao getCategoryDao() {
        if (categoryDao == null) {
            initCategoryDao();
        }
        return categoryDao;
    }

    public static CompanyDao getCompanyDao() {
        if (companyDao == null) {
            initCompanyDao();
        }
        return companyDao;
    }

    public static FileDao getFileDao() {
        if (fileDao == null) {
            initFileDao();
        }
        return fileDao;
    }

    public static MessageDao getMessageDao() {
        if (messageDao == null) {
            initMessageDao();
        }
        return messageDao;
    }

    public static ResponseDao getResponseDao() {
        if (responseDao == null) {
            initResponseDao();
        }
        return responseDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            initUserDao();
        }
        return userDao;
    }

    private static void initArticleDao() {
        final Article article = getArticle();
        final List<Article> articles = getArticles();
        articleDao = mock(ArticleDao.class);
        contentDao(articleDao, article, new Article(), articles);
        when(articleDao.getByNumber(NUMBER)).thenReturn(article);
        when(articleDao.getByNumber(null)).thenReturn(null);
        when(articleDao.getByNumber(ANY_STRING)).thenReturn(null);
        when(articleDao.getByCategoryId(ID)).thenReturn(articles);
        when(articleDao.getByCategoryId(UNKNOWN_ID)).thenReturn(null);
        when(articleDao.getByCategoryId(null)).thenReturn(null);
    }

    private static void initCategoryDao() {
        categoryDao = mock(CategoryDao.class);
        contentDao(categoryDao, getCategory(), new Category(), getCategories());
    }

    private static void initCompanyDao() {
        final List<Company> companies = getCompanies();
        companyDao = mock(CompanyDao.class);
        contentDao(companyDao, getCompany(), new Company(), companies);
        when(companyDao.getByType(CompanyType.PARTNER)).thenReturn(companies);
        when(companyDao.getByType(CompanyType.MAIN)).thenReturn(companies);
        when(companyDao.getByType(null)).thenReturn(null);
    }

    private static void initFileDao() {
        final File file = getFile();
        fileDao = mock(FileDao.class);
        dataDao(fileDao, file, new File(), getFiles());
        when(fileDao.getByTitle(TITLE)).thenReturn(file);
        when(fileDao.getByTitle(TITLE)).thenReturn(file);
        when(fileDao.getByTitle(null)).thenReturn(null);
        when(fileDao.getByTitle(ANY_STRING)).thenReturn(null);
        when(fileDao.getByUrl(URL)).thenReturn(file);
        when(fileDao.getByUrl(null)).thenReturn(null);
        when(fileDao.getByUrl(ANY_STRING)).thenReturn(null);
    }

    private static void initMessageDao() {
        messageDao = mock(MessageDao.class);
        dataDao(messageDao, getMessage(), new Message(), getMessages());
    }

    private static void initResponseDao() {
        responseDao = mock(ResponseDao.class);
        dataDao(responseDao, getResponse(), new Response(), getResponses());
    }

    private static void initUserDao() {
        final User user = getUser();
        userDao = mock(UserDao.class);
        dataDao(userDao, user, new User(), getUsers());
        when(userDao.getByName(NAME)).thenReturn(user);
        when(userDao.getByName(ANY_STRING)).thenReturn(null);
        when(userDao.getByName(null)).thenReturn(null);
        when(userDao.getByUrl(URL)).thenReturn(user);
        when(userDao.getByUrl(ANY_STRING)).thenReturn(null);
        when(userDao.getByUrl(null)).thenReturn(null);
        when(userDao.getByLogin(LOGIN)).thenReturn(user);
        when(userDao.getByLogin(ANY_STRING)).thenReturn(null);
        when(userDao.getByLogin(null)).thenReturn(null);
        when(userDao.getByPhone(PHONE)).thenReturn(user);
        when(userDao.getByPhone(ANY_STRING)).thenReturn(null);
        when(userDao.getByPhone(null)).thenReturn(null);
        when(userDao.getByEmail(EMAIL)).thenReturn(user);
        when(userDao.getByEmail(ANY_STRING)).thenReturn(null);
        when(userDao.getByEmail(null)).thenReturn(null);
    }

    private static <T extends Content<Long>, E extends ContentDao<T, Long>> void contentDao(
            final E dao,
            final T content,
            final T emptyContent,
            final Collection<T> contents
    ) {
        dataDao(dao, content, emptyContent, contents);
        when(dao.getByTitle(TITLE)).thenReturn(content);
        when(dao.getByTitle(ANY_STRING)).thenReturn(null);
        when(dao.getByTitle(null)).thenReturn(null);
        when(dao.getByUrl(URL)).thenReturn(content);
        when(dao.getByUrl(ANY_STRING)).thenReturn(null);
        when(dao.getByUrl(null)).thenReturn(null);
    }

    private static <T extends Model<Long>, E extends DataDao<T, Long>> void dataDao(
            final E dao,
            final T model,
            final T emptyModel,
            final Collection<T> models
    ) {
        when(dao.add(model)).thenReturn(model);
        when(dao.add(emptyModel)).thenReturn(emptyModel);
        when(dao.add(null)).thenReturn(null);
        when(dao.addAll(models)).thenReturn(models);
        when(dao.addAll(null)).thenReturn(null);
        when(dao.update(model)).thenReturn(model);
        when(dao.update(emptyModel)).thenReturn(emptyModel);
        when(dao.update(null)).thenReturn(null);
        when(dao.get(ID)).thenReturn(model);
        when(dao.get(UNKNOWN_ID)).thenReturn(null);
        when(dao.get(null)).thenReturn(null);
        when(dao.getAll()).thenReturn(models);
        when(dao.exists(ID)).thenReturn(true);
        when(dao.exists(UNKNOWN_ID)).thenReturn(false);
        when(dao.exists(null)).thenReturn(false);
    }
}
