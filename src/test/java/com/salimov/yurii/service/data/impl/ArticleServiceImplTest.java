package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.service.data.interfaces.ArticleService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.dao.MockDao.getArticleDao;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.junit.Assert.*;

public final class ArticleServiceImplTest extends ContentServiceImplTest<Article, Long> {

    private ArticleService service;

    @Before
    public void beforeTest() {
        this.service = new ArticleServiceImpl(
                getArticleDao()
        );
    }

    @Test
    public void whenInitAndAddThenReturnSomeArticle() {
        assertNotNull(
                this.service.initAndAdd(
                        TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER,
                        getCategory(),
                        true
                )
        );
    }

    @Test
    public void whenInitAndUpdateThenReturnSomeArticle() {
        assertNotNull(
                this.service.initAndUpdate(
                        URL, TITLE,
                        DESCRIPTION, TEXT,
                        KEYWORDS, NUMBER,
                        getCategory(),
                        true
                )
        );
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

    @Test
    public void whenGetByValidNumberAndTrueValidThenReturnsSomeArticle() {
        assertNotNull(
                this.service.getByNumber(NUMBER, true)
        );
    }

    @Test
    public void whenGetByValidNumberAndFalseValidThenReturnsSomeArticle() {
        assertNotNull(
                this.service.getByNumber(NUMBER, false)
        );
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
        assertTrue(
                this.service.sortByNumber(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNumberWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByNumber(
                        null, false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByNumber(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByNumber(
                        new ArrayList<>(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndTrueReversThenReturnsEmptyList() {
        assertFalse(
                this.service.sortByNumber(
                        getArticles(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndFalseReversThenReturnsEmptyList() {
        assertFalse(
                this.service.sortByNumber(
                        getArticles(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithNullCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        null, true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        null, false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        new ArrayList<>(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(
                this.service.sortByDate(
                        new ArrayList<>(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithTrueReversThenReturnsEmptyList() {
        assertFalse(
                this.service.sortByDate(
                        getArticles(), true
                ).isEmpty()
        );
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithFalseReversThenReturnsEmptyList() {
        assertFalse(
                this.service.sortByDate(
                        getArticles(), false
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByNumberWithTrueReversThenReturnsEmptyList() {
        assertFalse(
                this.service.getAndSortByNumber(true).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByNumberWithFalseReversThenReturnsEmptyList() {
        assertFalse(
                this.service.getAndSortByNumber(false).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByDateWithTrueReversThenReturnsEmptyList() {
        assertFalse(
                this.service.getAndSortByDate(true).isEmpty()
        );
    }

    @Test
    public void whenGetAndSortByDateWithFalseReversThenReturnsEmptyList() {
        assertFalse(
                this.service.getAndSortByDate(false).isEmpty()
        );
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
        assertTrue(
                this.service.filterByDate(
                        null,
                        startDate,
                        finishDate
                ).isEmpty()
        );
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
        assertTrue(
                this.service.filterByDate(
                        new ArrayList<>(),
                        startDate,
                        finishDate
                ).isEmpty()
        );
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = this.service.filterByDate(
                articles,
                null,
                new Date()
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(
                articles.size(),
                filterArticles.size()
        );
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = this.service.filterByDate(
                articles,
                new Date(),
                null
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(
                articles.size(),
                filterArticles.size()
        );
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
                articles,
                startDate,
                finishDate
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(
                articles.size(),
                filterArticles.size()
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

    @Ignore
    @Override
    protected Article getInvalidObject() {
        return new Article();
    }
}
