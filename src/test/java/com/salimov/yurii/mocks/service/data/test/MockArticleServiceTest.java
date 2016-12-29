package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.service.data.interfaces.ArticleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.service.data.MockServices.getArticleService;

public class MockArticleServiceTest extends MockContentServiceTest<Article> {

    private ArticleService service;

    @Before
    public void initArticleService() {
        this.service = getArticleService();
    }

    @Test
    public void whenInitAndAddArticleThenReturnThisArticle() {
        //Article article = this.service.initAndAdd(TITLE, DESCRIPTION, TEXT, KEYWORDS, NUMBER, null, null, null, null);
        //assertNotNull(article);
    }

    @Test
    public void whenGetByValidNumberThenReturnSomeArticle() {
        Article article = this.service.getByNumber(NUMBER, true);
        assertNotNull(article);
    }

    @Test
    public void whenSortedArticlesByNumberThenReturnThisSortCollection() {
        Collection<Article> articles1 = getArticles();
        Collection<Article> articles2 = this.service.sortByNumber(articles1, false);
        assertNotNull(articles2);

        articles2 = this.service.sortByNumber(articles1, true);
        assertNotNull(articles2);
    }

    @Test
    public void whenSortedArticlesByDateThenReturnThisSortCollection() {
        Collection<Article> articles1 = getArticles();
        Collection<Article> articles2 = this.service.sortByDate(articles1, false);
        assertNotNull(articles2);

        articles2 = this.service.sortByDate(articles1, true);
        assertNotNull(articles2);
    }

    @Test
    public void whenGetAndSortedArticlesByNumberThenReturnThisSortCollection() {
        Collection<Article> articles = this.service.getAndSortByNumber(false);
        assertNotNull(articles);

        articles = this.service.getAndSortByNumber(true);
        assertNotNull(articles);
    }

    @Test
    public void whenGetAndSortedArticlesByDateThenReturnThisSortCollection() {
        Collection<Article> articles = this.service.getAndSortByDate(false);
        assertNotNull(articles);

        articles = this.service.getAndSortByDate(true);
        assertNotNull(articles);
    }

    @Test
    public void whenFilteredArticlesByDateThenReturnThisFilterCollection() {
        Collection<Article> articles1 = getArticles();
        Collection<Article> articles2 = this.service.filterByDate(articles1, DATE, DATE);
        assertNotNull(articles2);
    }

    @Test
    public void whenFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        Category category = getCategory();
        Collection<Article> articles1 = getArticles();
        Collection<Article> articles2 = this.service.filterByCategory(articles1, category);
        assertNotNull(articles2);
    }

    @Test
    public void whenFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        Collection<Category> categories = getCategories();
        Collection<Article> articles1 = getArticles();
        Collection<Article> articles2 = this.service.filterByCategories(articles1, categories);
        assertNotNull(articles2);
    }

    @Test
    public void whenGetAndFilteredArticlesByDateThenReturnThisFilterCollection() {
        Collection<Article> articles = this.service.getAndFilterByDate(DATE, DATE);
        assertNotNull(articles);
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        Category category = getCategory();
        Collection<Article> articles2 = this.service.getAndFilterByCategory(category);
        assertNotNull(articles2);
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        Collection<Category> categories = getCategories();
        Collection<Article> articles = this.service.getAndFilterByCategories(categories);
        assertNotNull(articles);
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
