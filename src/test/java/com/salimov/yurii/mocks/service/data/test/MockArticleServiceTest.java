package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static com.salimov.yurii.mocks.service.data.MockServices.getArticleService;
import static org.junit.Assert.assertNotNull;

public final class MockArticleServiceTest
        extends MockContentServiceTest<Article> {

    private ArticleService service;

    @Before
    public void initArticleService() {
        this.service = getArticleService();
    }

    @Test
    public void whenInitAndAddArticleThenReturnThisArticle() {
        assertNotNull(
                this.service.initAndAdd(
                        TITLE,
                        DESCRIPTION, TEXT, KEYWORDS,
                        NUMBER,
                        getCategory(),
                        true
                )
        );
    }

    @Test
    public void whenGetByValidNumberThenReturnSomeArticle() {
        assertNotNull(
                this.service.getByNumber(NUMBER, true)
        );
    }

    @Test
    public void whenSortedArticlesByNumberThenReturnThisSortCollection() {
        assertNotNull(
                this.service.sortByNumber(
                        getArticles(), false
                )
        );
        assertNotNull(
                this.service.sortByNumber(
                        getArticles(), true
                )
        );
    }

    @Test
    public void whenSortedArticlesByDateThenReturnThisSortCollection() {
        assertNotNull(
                this.service.sortByDate(
                        getArticles(), false
                )
        );
        assertNotNull(
                this.service.sortByDate(
                        getArticles(), true
                )
        );
    }

    @Test
    public void whenGetAndSortedArticlesByNumberThenReturnThisSortCollection() {
        assertNotNull(
                this.service.getAndSortByNumber(false)
        );
        assertNotNull(
                this.service.getAndSortByNumber(true)
        );
    }

    @Test
    public void whenGetAndSortedArticlesByDateThenReturnThisSortCollection() {
        assertNotNull(
                this.service.getAndSortByDate(false)
        );
        assertNotNull(
                this.service.getAndSortByDate(true)
        );
    }

    @Test
    public void whenFilteredArticlesByDateThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.filterByDate(
                        getArticles(),
                        DATE, DATE
                )
        );
    }

    @Test
    public void whenFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.filterByCategory(
                        getArticles(),
                        getCategory()
                )
        );
    }

    @Test
    public void whenFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.filterByCategories(
                        getArticles(),
                        getCategories()
                )
        );
    }

    @Test
    public void whenGetAndFilteredArticlesByDateThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.getAndFilterByDate(DATE, DATE)
        );
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.getAndFilterByCategory(
                        getCategory()
                )
        );
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        assertNotNull(
                this.service.getAndFilterByCategories(
                        getCategories()
                )
        );
    }

    @Ignore
    @Override
    protected ArticleService getService() {
        return this.service;
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
