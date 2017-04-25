package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.service.data.ArticleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;
import static org.junit.Assert.assertNotNull;

public final class MockArticleServiceTest extends MockContentServiceTest<Article> {

    private ArticleService service;

    @Before
    public void initArticleService() {
        this.service = getArticleService();
    }

    @Test
    public void whenGetByValidNumberThenReturnSomeArticle() {
        assertNotNull(this.service.getByNumber(MockConstants.NUMBER, true));
    }

    @Test
    public void whenSortedArticlesByNumberThenReturnThisSortCollection() {
        assertNotNull(this.service.sortByNumber(MockEntity.getArticles(), false));
        assertNotNull(this.service.sortByNumber(MockEntity.getArticles(), true));
    }

    @Test
    public void whenSortedArticlesByDateThenReturnThisSortCollection() {
        assertNotNull(this.service.sortByDate(MockEntity.getArticles(), false));
        assertNotNull(this.service.sortByDate(MockEntity.getArticles(), true));
    }

    @Test
    public void whenGetAndSortedArticlesByNumberThenReturnThisSortCollection() {
        assertNotNull(this.service.getAndSortByNumber(false));
        assertNotNull(this.service.getAndSortByNumber(true));
    }

    @Test
    public void whenGetAndSortedArticlesByDateThenReturnThisSortCollection() {
        assertNotNull(this.service.getAndSortByDate(false));
        assertNotNull(this.service.getAndSortByDate(true));
    }

    @Test
    public void whenFilteredArticlesByDateThenReturnThisFilterCollection() {
        assertNotNull(this.service.filterByDate(MockEntity.getArticles(), MockConstants.DATE, MockConstants.DATE));
    }

    @Test
    public void whenFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        assertNotNull(this.service.filterByCategory(MockEntity.getArticles(), MockEntity.getCategory()));
    }

    @Test
    public void whenFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        assertNotNull(this.service.filterByCategories(MockEntity.getArticles(), MockEntity.getCategories()));
    }

    @Test
    public void whenGetAndFilteredArticlesByDateThenReturnThisFilterCollection() {
        assertNotNull(this.service.getAndFilterByDate(MockConstants.DATE, MockConstants.DATE));
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoryThenReturnThisFilterCollection() {
        assertNotNull(this.service.getAndFilterByCategory(MockEntity.getCategory()));
    }

    @Test
    public void whenGetAndFilteredArticlesByCategoriesThenReturnThisFilterCollection() {
        assertNotNull(this.service.getAndFilterByCategories(MockEntity.getCategories()));
    }

    @Ignore
    @Override
    protected ArticleService getService() {
        return this.service;
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
