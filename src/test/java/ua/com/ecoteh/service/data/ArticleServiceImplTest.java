package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.*;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class ArticleServiceImplTest extends ContentServiceImplTest<Article> {

    private static ArticleService service;

    @BeforeClass
    public static void beforeTest() {
        service = new ArticleServiceImpl(
                MockRepository.getArticleRepository(),
                getFileService()
        );
    }

    @Test
    public void whenUpdateThenReturnSomeArticle() {
        assertNotNull(service.update(URL, getArticle()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByInvalidNumberAndValidThenThrowIllegalArgumentException() {
        service.getByNumber(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByInvalidNumberAndInvalidThenThrowIllegalArgumentException() {
        service.getByNumber(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNumberAndValidThenThrowIllegalArgumentException() {
        service.getByNumber("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankNumberAndInvalidThenThrowIllegalArgumentException() {
        service.getByNumber("", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByValidNumberAndTrueValidThenReturnsSomeArticleOrThrowException() {
        assertNotNull(service.getByNumber(NUMBER, true));
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByValidNumberAndFalseValidThenReturnsSomeArticleOrThrowException() {
        assertNotNull(service.getByNumber(NUMBER, false));
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberAndValidThenThrowNullPointerException() {
        service.getByNumber(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberAndInvalidThenThrowNullPointerException() {
        service.getByNumber(ANY_STRING, false);
    }

    @Test
    public void whenSortByNumberWithNullCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(service.sortByNumber(null, true).isEmpty());
    }

    @Test
    public void whenSortByNumberWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(service.sortByNumber(null, false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(service.sortByNumber(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByNumberWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(service.sortByNumber(new ArrayList<>(), false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndTrueReversThenReturnsEmptyList() {
        assertFalse(service.sortByNumber(getArticles(), false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndFalseReversThenReturnsEmptyList() {
        assertFalse(service.sortByNumber(getArticles(), true).isEmpty());
    }

    @Test
    public void whenSortByDateWithNullCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(service.sortByDate(null, true).isEmpty());
    }

    @Test
    public void whenSortByDateWithNullCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(service.sortByDate(null, false).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndTrueReversThenReturnsEmptyList() {
        assertTrue(service.sortByDate(new ArrayList<>(), true).isEmpty());
    }

    @Test
    public void whenSortByDateWithEmptyCollectionAndFalseReversThenReturnsEmptyList() {
        assertTrue(service.sortByDate(new ArrayList<>(), false).isEmpty());
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithTrueReversThenReturnsEmptyList() {
        assertFalse(service.sortByDate(getArticles(), true).isEmpty());
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithFalseReversThenReturnsEmptyList() {
        assertFalse(service.sortByDate(getArticles(), false).isEmpty());
    }

    @Test
    public void whenGetAndSortByNumberWithTrueReversThenReturnsSomeList() {
        assertFalse(service.getAndSortByNumber(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByNumberWithFalseReversThenReturnsSomeList() {
        assertFalse(service.getAndSortByNumber(false).isEmpty());
    }

    @Test
    public void whenGetAndSortByDateWithTrueReversThenReturnsSomeList() {
        assertFalse(service.getAndSortByDate(true).isEmpty());
    }

    @Test
    public void whenGetAndSortByDateWithFalseReversThenReturnsSomeList() {
        assertFalse(service.getAndSortByDate(false).isEmpty());
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
        assertTrue(service.filterByDate(null, startDate, finishDate).isEmpty());
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
        assertTrue(service.filterByDate(new ArrayList<>(), startDate, finishDate).isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = service.filterByDate(
                articles, null, new Date()
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnsEmptyList() {
        final List<Article> articles = getArticles();
        final List<Article> filterArticles = service.filterByDate(
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
        final List<Article> filterArticles = service.filterByDate(
                articles, startDate, finishDate
        );
        assertFalse(filterArticles.isEmpty());
        assertEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenFilterByByDateThenReturnsList() {
        final List<Article> articles = getArticles();
        try {
            for (Article article : articles) {
                article.setDate(new Date());
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = articles.get(articles.size() - 1).getDate();
        final Date finishDate = new Date();
        final List<Article> filterArticles = service.filterByDate(
                articles, startDate, finishDate
        );
        assertFalse(filterArticles.isEmpty());
        assertNotEquals(articles.size(), filterArticles.size());
    }

    @Test
    public void whenRemoveByNullNumberThenDoNothing() {
        service.removeByNumber(null);
    }

    @Test
    public void whenRemoveByBlankNumberThenDoNothing() {
        service.removeByNumber("");
        service.removeByNumber(" ");
        service.removeByNumber("  ");
    }

    @Test(expected = NullPointerException.class)
    public void whenRemoveByUnknownNumberThenThrowNullPointerException() {
        service.removeByNumber(ANY_STRING);
    }

    @Test
    public void whenRemoveByNumberThenDoIt() {
        service.removeByNumber(NUMBER);
    }

    @Test
    public void whenFilterByCategoryWithNullArticlesThenReturnSomeList() {
        assertNotNull(service.filterByCategory(null, getCategory()));
    }

    @Test
    public void whenFilterByCategoryWithEmptyArticlesThenReturnSomeList() {
        assertNotNull(service.filterByCategory(new ArrayList<>(), getCategory()));
    }

    @Test
    public void whenFilterByCategoriesWithNullArticlesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(null, getCategories()));
    }

    @Test
    public void whenFilterByCategoriesWithEmptyArticlesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(new ArrayList<>(), getCategories()));
    }

    @Test
    public void whenFilterByCategoriesWithNullCategoriesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(getArticles(), null));
    }

    @Test
    public void whenFilterByCategoriesWithEmptyCategoriesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(getArticles(), new ArrayList<>()));
    }

    @Test
    public void whenFilterByCategoriesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(getArticles(), getCategories()));
    }

    @Test
    public void whenFilterByDateThenReturnsSomeList() {
        assertNotNull(service.getAndFilterByDate(new Date(), new Date()));
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnsSomeList() {
        assertNotNull(service.getAndFilterByDate(null, new Date()));
    }

    @Test
    public void whenFilterByDateWithNullFinishDateThenReturnsSomeList() {
        assertNotNull(service.getAndFilterByDate(new Date(), null));
    }

    @Test
    public void whenGetAndFilterByCategoriesWithNullCategoryThenReturnSomeList() {
        assertNotNull(service.getAndFilterByCategories(null));
    }

    @Test
    public void whenGetAndFilterByCategoriesWithEmptyCategoryThenReturnSomeList() {
        assertNotNull(service.getAndFilterByCategories(new ArrayList<>()));
    }

    @Test
    public void whenGetByCategoryIdThenReturnSomeList() {
        assertNotNull(service.getByCategoryId(ID));
    }

    @Test
    public void whenGetByUnknownCategoryIdThenReturnSomeList() {
        assertNotNull(service.getByCategoryId(UNKNOWN_ID));
    }

    @Test
    public void whenGetByCategoryTitleThenReturnSomeList() {
        assertNotNull(service.getByCategoryTitle(TITLE));
    }

    @Test
    public void whenGetByUnknownCategoryTitleThenReturnSomeList() {
        assertNotNull(service.getByCategoryTitle(ANY_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullCategoryTitleThenThrowIllegalArgumentException() {
        service.getByCategoryTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyCategoryTitleThenThrowIllegalArgumentException() {
        service.getByCategoryTitle("");
    }

    @Test
    public void whenGetAndFilterByNullCategory() {
        assertNotNull(service.getAndFilterByCategory(null));
    }

    @Ignore
    @Override
    protected ArticleService getService() {
        return service;
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
