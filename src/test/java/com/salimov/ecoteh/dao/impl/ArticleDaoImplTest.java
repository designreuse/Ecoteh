package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.ArticleDao;
import com.salimov.ecoteh.entity.Article;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getArticle;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getArticles;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getArticleRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class ArticleDaoImplTest extends DataDAOImplTest<Article> {

    private ArticleDao dao;

    @Before
    public void beforeTest() {
        this.dao = new ArticleDaoImpl(getArticleRepository());
    }

    @Test
    public void whenGetByNullTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(null));
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeArticle() {
        assertNotNull(this.dao.getByTitle(TITLE));
    }

    @Test
    public void whenGetByNullUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(null));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeArticle() {
        assertNotNull(this.dao.getByUrl(URL));
    }

    @Test
    public void whenGetByNullNumberThenReturnsNull() {
        assertNull(this.dao.getByNumber(null));
    }

    @Test
    public void whenGetByInvalidNumberThenReturnsNull() {
        assertNull(this.dao.getByNumber(ANY_STRING));
    }

    @Test
    public void whenGetByValidNumberThenReturnsSomeArticle() {
        assertNotNull(this.dao.getByNumber(NUMBER));
    }

    @Test
    public void whenGetByNullCategoryIdThenReturnsEmptyCollection() {
        assertTrue(this.dao.getByCategoryId(null).isEmpty());
    }

    @Test
    public void whenGetByUnknownCategoryIdThenReturnsEmptyCollection() {
        assertTrue(this.dao.getByCategoryId(UNKNOWN_ID).isEmpty());
    }

    @Test
    public void whenGetByValidByCategoryIdThenReturnsSomeArticle() {
        assertNotNull(this.dao.getByCategoryId(ID));
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        this.dao.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        this.dao.removeByUrl(URL);
    }

    @Test
    public void whenRemoveByNumberThenDoIt() {
        getDao().removeByNumber(NUMBER);
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
