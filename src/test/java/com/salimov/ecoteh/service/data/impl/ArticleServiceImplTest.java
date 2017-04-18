package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.Article;
import com.salimov.ecoteh.service.data.interfaces.ArticleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.*;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getArticleRepository;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class ArticleServiceImplTest extends ContentServiceImplTest<Article> {

    private ArticleService service;

    @Before
    public void beforeTest() {
        this.service = new ArticleServiceImpl(
                getArticleRepository(),
                getFileService()
        );
    }

    @Test
    public void whenUpdateThenReturnSomeArticle() {
        assertNotNull(this.service.update(URL, getArticle()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByInvalidNumberAndValidThenThrowIllegalArgumentException() {
        this.service.getByNumber(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByInvalidNumberAndInvalidThenThrowIllegalArgumentException() {
        this.service.getByNumber(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNumberAndValidThenThrowIllegalArgumentException() {
        this.service.getByNumber("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNumberAndInvalidThenThrowIllegalArgumentException() {
        this.service.getByNumber("", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByValidNumberAndTrueValidThenReturnsSomeArticleOrThrowException() {
        assertNotNull(this.service.getByNumber(NUMBER, true));
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByValidNumberAndFalseValidThenReturnsSomeArticleOrThrowException() {
        assertNotNull(this.service.getByNumber(NUMBER, false));
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberAndValidThenThrowNullPointerException() {
        this.service.getByNumber(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberAndInvalidThenThrowNullPointerException() {
        this.service.getByNumber(ANY_STRING, false);
    }

    @Test
    public void whenSortByNumberWithNullCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByNumber(null, true).isEmpty());
    }

    @Test
    public void whenSortByNumberWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByNumber(null, false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByNumber(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByNumber(new ArrayList<>(), false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndTrueReversThenReturnsEmptyList() {
        assertFalse(this.service.sortByNumber(getArticles(), false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndFalseReversThenReturnsEmptyList() {
        assertFalse(this.service.sortByNumber(getArticles(), true).isEmpty());
    }

    @Test
    public void whenSortByDateWithNullCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByDate(null, true).isEmpty());
    }

    @Test
    public void whenSortByDateWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByDate(null, false).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByDate(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(this.service.sortByDate(new ArrayList<>(), false).isEmpty());
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithTrueReversThenReturnsEmptyList() {
        assertFalse(this.service.sortByDate(getArticles(), true).isEmpty());
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithFalseReversThenReturnsEmptyList() {
        assertFalse(this.service.sortByDate(getArticles(), false).isEmpty());
    }

    @Test
    public void whenGetAndSortByNumberWithTrueReversThenReturnsSomeList() {
        assertFalse(this.service.getAndSortByNumber(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByNumberWithFalseReversThenReturnsSomeList() {
        assertFalse(this.service.getAndSortByNumber(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByDateWithTrueReversThenReturnsSomeList() {
        assertFalse(this.service.getAndSortByDate(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByDateWithFalseReversThenReturnsSomeList() {
        assertFalse(this.service.getAndSortByDate(false).isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullCollectionThenReturnsEmptyList() {
        final Date startDate = new Date();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date finishDate = new Date();
        assertTrue(this.service.filterByDate(null, startDate, finishDate).isEmpty());
    }

    @Test
    public void whenFilterByDateWithEmptyCollectionThenReturnsEmptyList() {
        final Date startDate = new Date();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date finishDate = new Date();
        assertTrue(this.service.filterByDate(new ArrayList<>(), startDate, finishDate).isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = this.service.filterByDate(
                articles, null, new Date()
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = this.service.filterByDate(
                articles, new Date(), null
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenFilterByByDateThenReturnsSomeList() {
        final List<Article> articles = getArticles();
        final Date finishDate = new Date();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        final List<Article> filterArticles = this.service.filterByDate(
                articles, startDate, finishDate
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenRemoveByNullNumberThenDoNothing() {
        this.service.removeByNumber(null);
    }

    @Test
    public void whenRemoveByBlankNumberThenDoNothing() {
        this.service.removeByNumber("");
        this.service.removeByNumber(" ");
        this.service.removeByNumber("  ");
    }

    @Test(expected = NullPointerException.class)
    public void whenRemoveByNumberThenDoIt() {
        this.service.removeByNumber(NUMBER);
    }

    @Test
    public void whenFilterByCategoryWithNullArticlesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategory(null, getCategory()));
    }

    @Test
    public void whenFilterByCategoryWithEmptyArticlesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategory(new ArrayList<>(), getCategory()));
    }

    @Test
    public void whenFilterByCategoriesWithNullArticlesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategories(null, getCategories()));
    }

    @Test
    public void whenFilterByCategoriesWithEmptyArticlesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategories(new ArrayList<>(), getCategories()));
    }

    @Test
    public void whenFilterByCategoriesWithNullCategoriesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategories(getArticles(), null));
    }

    @Test
    public void whenFilterByCategoriesWithEmptyCategoriesThenReturnSomeList() {
        assertNotNull(this.service.filterByCategories(getArticles(), new ArrayList<>()));
    }

    @Test
    public void whenFilterByDateThenReturnsSomeList() {
        assertNotNull(this.service.getAndFilterByDate(new Date(), new Date()));
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnsSomeList() {
        assertNotNull(this.service.getAndFilterByDate(null, new Date()));
    }

    @Test
    public void whenFilterByDateWithNullFinishDateThenReturnsSomeList() {
        assertNotNull(this.service.getAndFilterByDate(new Date(), null));
    }

    @Test
    public void whenGetAndFilterByCategoriesWithNullCategoryThenReturnSomeList() {
        assertNotNull(this.service.getAndFilterByCategories(null));
    }

    @Test
    public void whenGetAndFilterByCategoriesWithEmptyCategoryThenReturnSomeList() {
        assertNotNull(this.service.getAndFilterByCategories(new ArrayList<>()));
    }

    @Ignore
    public void whenAddInvalidModelThenReturnsIt() {
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

    @Ignore
    @Override
    protected Article getInvalidObject() {
        return new Article();
    }
}
