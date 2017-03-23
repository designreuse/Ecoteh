package com.salimov.ecoteh.mocks.dao.test;

import com.salimov.ecoteh.dao.interfaces.ArticleDao;
import com.salimov.ecoteh.entity.Article;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.ANY_STRING;
import static com.salimov.ecoteh.mocks.MockConstants.NUMBER;
import static com.salimov.ecoteh.mocks.dao.MockDao.getArticleDao;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getArticle;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getArticles;
import static org.junit.Assert.*;

public class MockArticleDaoTest extends MockContentDAOTest<Article> {

    private ArticleDao dao;

    @Before
    public void initArticleDao() {
        this.dao = getArticleDao();
    }

    @Test
    public void whenGetByInvalidNumberThenReturnNull() {
        assertNull(this.dao.getByNumber(null));
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByValidNumberThenReturnSomeContent() {
        final Article article = this.dao.getByNumber(NUMBER);
        assertNotNull(article);
        assertEquals(article.getNumber(), NUMBER);
    }

    @Ignore
    @Override
    protected ArticleDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Article getObject() {
        return getArticle();
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return getArticles();
    }
}
