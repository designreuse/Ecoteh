package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.*;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class ArticleEntityServiceImplTest extends ContentServiceImplTest<ArticleEntity> {

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
        assertNotNull(service.update(URL, getArticleEntity()));
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
        assertFalse(service.sortByNumber(getArticleEntities(), false).isEmpty());
    }

    @Test
    public void whenSortByNumberWithValidInputCollectionAndFalseReversThenReturnsEmptyList() {
        assertFalse(service.sortByNumber(getArticleEntities(), true).isEmpty());
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
        assertFalse(service.sortByDate(getArticleEntities(), true).isEmpty());
    }

    @Test
    public void whenSortByDateAndValidInputCollectionWithFalseReversThenReturnsEmptyList() {
        assertFalse(service.sortByDate(getArticleEntities(), false).isEmpty());
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
        final List<ArticleEntity> articleEntities = getArticleEntities();
        final List<ArticleEntity> filterArticleEntities = service.filterByDate(
                articleEntities, null, new Date()
        );
        assertFalse(filterArticleEntities.isEmpty());
        assertEquals(articleEntities.size(), filterArticleEntities.size());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnsEmptyList() {
        final List<ArticleEntity> articleEntities = getArticleEntities();
        final List<ArticleEntity> filterArticleEntities = service.filterByDate(
                articleEntities, new Date(), null
        );
        assertFalse(filterArticleEntities.isEmpty());
        assertEquals(articleEntities.size(), filterArticleEntities.size());
    }

    @Test
    public void whenFilterByByDateThenReturnsSomeList() {
        final List<ArticleEntity> articleEntities = getArticleEntities();
        final Date finishDate = new Date();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = new Date();
        final List<ArticleEntity> filterArticleEntities = service.filterByDate(
                articleEntities, startDate, finishDate
        );
        assertFalse(filterArticleEntities.isEmpty());
        assertEquals(articleEntities.size(), filterArticleEntities.size());
    }

    @Test
    public void whenFilterByByDateThenReturnsList() {
        final List<ArticleEntity> articleEntities = getArticleEntities();
        try {
            for (ArticleEntity articleEntity : articleEntities) {
                articleEntity.setDate(new Date());
                Thread.sleep(100);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        final Date startDate = articleEntities.get(articleEntities.size() - 1).getDate();
        final Date finishDate = new Date();
        final List<ArticleEntity> filterArticleEntities = service.filterByDate(
                articleEntities, startDate, finishDate
        );
        assertFalse(filterArticleEntities.isEmpty());
        assertNotEquals(articleEntities.size(), filterArticleEntities.size());
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
        assertNotNull(service.filterByCategory(null, getCategoryEntity()));
    }

    @Test
    public void whenFilterByCategoryWithEmptyArticlesThenReturnSomeList() {
        assertNotNull(service.filterByCategory(new ArrayList<>(), getCategoryEntity()));
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
        assertNotNull(service.filterByCategories(getArticleEntities(), null));
    }

    @Test
    public void whenFilterByCategoriesWithEmptyCategoriesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(getArticleEntities(), new ArrayList<>()));
    }

    @Test
    public void whenFilterByCategoriesThenReturnSomeList() {
        assertNotNull(service.filterByCategories(getArticleEntities(), getCategories()));
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
    protected ArticleEntity getObject() {
        return getArticleEntity();
    }

    @Ignore
    @Override
    protected Collection<ArticleEntity> getObjects() {
        return getArticleEntities();
    }

    @Ignore
    @Override
    protected ArticleEntity getInvalidObject() {
        return new ArticleEntity();
    }
}
