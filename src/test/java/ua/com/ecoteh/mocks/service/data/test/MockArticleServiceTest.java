package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.service.data.ArticleService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.*;
import static ua.com.ecoteh.mocks.service.data.MockServices.getArticleService;

public final class MockArticleServiceTest extends MockContentServiceTest<Article> {

    private static ArticleService service;
    private static Article article;
    private static Collection<Article> articles;
    private static Category category;
    private static Collection<Category> categories;

    @BeforeClass
    public static void beforeClass() {
        service = getArticleService();
        article = getArticle();
        articles = getArticles();
        category = getCategory();
        categories = getCategories();
    }

    @Test
    public void whenGetByNumberThenReturnNotNull() {
        Article article = service.getByNumber(NUMBER, true);
        assertNotNull(article);
        article = service.getByNumber(NUMBER, false);
        assertNotNull(article);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNumberValidThenThrowIllegalArgumentException() {
        service.getByNumber(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNumberInvalidThenThrowIllegalArgumentException() {
        service.getByNumber(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyNumberValidThenThrowIllegalArgumentException() {
        service.getByNumber("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyNumberInvalidThenThrowIllegalArgumentException() {
        service.getByNumber("", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberValidThenThrowNullPointerException() {
        service.getByNumber(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberInvalidThenThrowNullPointerException() {
        service.getByNumber(ANY_STRING, false);
    }

    @Test
    public void whenGetByCategoryIdThenReturnNotEmptyCollection() {
        final Collection<Article> articles = service.getByCategoryId(ID);
        assertFalse(articles.isEmpty());
    }

    @Test
    public void whenGetByCategoryIdThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> articles = service.getByCategoryId(ID);
        articles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetByUnknownCategoryIdThenReturnEmptyCollection() {
        final Collection<Article> articles = service.getByCategoryId(UNKNOWN_ID);
        assertTrue(articles.isEmpty());
    }

    @Test
    public void whenGetByCategoryTitleThenReturnNotEmptyCollection() {
        final Collection<Article> articles = service.getByCategoryTitle(TITLE);
        assertFalse(articles.isEmpty());
    }

    @Test
    public void whenGetByCategoryTitleThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> articles = service.getByCategoryTitle(TITLE);
        articles.forEach(Assert::assertNotNull);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankCategoryTitleThenThrownIllegalArgumentException() {
        service.getByCategoryTitle("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullCategoryTitleThenThrownIllegalArgumentException() {
        service.getByCategoryTitle(null);
    }

    @Test
    public void whenSortByNumberThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByNumber(articles, true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.sortByNumber(articles, false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortByNumberThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.sortByNumber(articles, true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.sortByNumber(articles, false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByNumberThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByNumber(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByNumber(new ArrayList<>(), false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByNumberThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByNumber(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByNumber(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenGetAndSortByNumberThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.getAndSortByNumber(true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.getAndSortByNumber(false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortByDateThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByDate(articles, true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.sortByDate(articles, false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortByDateThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.sortByDate(articles, true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.sortByDate(articles, false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByDateThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByDate(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByDate(new ArrayList<>(), false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByDateThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByDate(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByDate(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenGetAndSortByDateThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.getAndSortByDate(true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.getAndSortByDate(false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortByPriceThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByPrice(articles, true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.sortByPrice(articles, false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortByPriceThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.sortByPrice(articles, true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.sortByPrice(articles, false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortEmptyCollectionByPriceThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByPrice(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByPrice(new ArrayList<>(), false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortNullCollectionByPriceThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByPrice(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByPrice(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenGetAndSortByPriceThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.getAndSortByPrice(true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.getAndSortByPrice(false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(articles, DATE, DATE);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(articles, null, DATE);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullEndDateThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(articles, DATE, null);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(articles, null, null);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(new ArrayList<>(), DATE, DATE);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(new ArrayList<>(), null, DATE);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(new ArrayList<>(), DATE, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(new ArrayList<>(), null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(null, DATE, DATE);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(null, null, DATE);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(null, DATE, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByDate(null, null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByDate(DATE, DATE);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.getAndFilterByDate(DATE, DATE);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByDate(null, DATE);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullEndDateThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByDate(DATE, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByDate(null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoryThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(articles, category);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoryThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByCategory(articles, category);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByNullCategoryThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(articles, null);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByNullCategoryThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByCategory(articles, null);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(new ArrayList<>(), category);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByNullCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(new ArrayList<>(), null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(null, category);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByNullCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByCategoryThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByCategoryThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByNullCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoriesThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoriesThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByNullCategoriesThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, null);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByNullCategoriesThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, null);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByEmptyCategoriesThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, new ArrayList<>());
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByEmptyCategoriesThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByCategories(articles, new ArrayList<>());
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(new ArrayList<>(), categories);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(null, categories);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByNullCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByEmptyCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(null, new ArrayList<>());
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByEmptyCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategories(new ArrayList<>(), new ArrayList<>());
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByCategoriesThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByEmptyCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(new ArrayList<>());
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullCategoriesThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilteredByValidThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filteredByValid(articles);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilteredEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filteredByValid(new ArrayList<>());
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilteredNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filteredByValid(null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Ignore
    @Override
    protected ArticleService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Article getObject() {
        return article;
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return articles;
    }
}
