package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.ArticleDao;
import com.salimov.yurii.entity.Article;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticle;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticles;
import static com.salimov.yurii.mocks.repository.MockRepository.getArticleRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class ArticleDaoImplTest
        extends DataDAOImplTest<Article, Long> {

    private ArticleDao dao;

    @Before
    public void beforeTest() {
        this.dao = new ArticleDaoImpl(
                getArticleRepository()
        );
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(
                this.dao.getByTitle(null)
        );
        assertNull(
                this.dao.getByTitle(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeArticle() {
        assertNotNull(
                this.dao.getByTitle(TITLE)
        );
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(
                this.dao.getByUrl(null)
        );
        assertNull(
                this.dao.getByUrl(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeArticle() {
        assertNotNull(
                this.dao.getByUrl(URL)
        );
    }

    @Test
    public void whenGetByInvalidNumberThenReturnsNull() {
        assertNull(
                this.dao.getByNumber(null)
        );
        assertNull(
                this.dao.getByNumber(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidNumberThenReturnsSomeArticle() {
        assertNotNull(
                this.dao.getByNumber(NUMBER)
        );
    }

    @Test
    public void whenGetByInvalidCategoryIdThenReturnsEmptyColection() {
        final Collection<Article> articles1 = this.dao.getByCategoryId(null);
        assertNotNull(articles1);
        assertTrue(articles1.isEmpty());

        final Collection<Article> articles2 = this.dao.getByCategoryId(UNKNOWN_ID);
        assertNotNull(articles2);
        assertTrue(articles2.isEmpty());
    }

    @Test
    public void whenGetByValidByCategoryIdThenReturnsSomeArticle() {
        assertNotNull(
                this.dao.getByCategoryId(ID)
        );
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
