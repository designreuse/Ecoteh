package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.dao.MockDAO;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Article;

import java.util.Collection;

import static org.junit.Assert.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticles;

public class MockArticleDaoTest extends MockContentDAOTest<Article> {

    private ArticleDao dao;

    @Before
    public void initArticleDao() {
        this.dao = MockDAO.getArticleDao();
    }

    @Test
    public void whenGetByInvalidNumberThenReturnNull() {
        Article article = this.dao.getByNumber(null);
        assertNull(article);

        article = this.dao.getByTitle(MockConstants.ANY_STRING);
        assertNull(article);
    }

    @Test
    public void whenGetByValidNumberThenReturnSomeContent() {
        Article article = this.dao.getByNumber(MockConstants.NUMBER);
        assertNotNull(article);
        assertEquals(article.getNumber(), MockConstants.NUMBER);
    }

    @Ignore
    @Override
    protected ArticleDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Article getObject() {
        return MockEntity.getArticle();
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return MockEntity.getArticles();
    }
}
