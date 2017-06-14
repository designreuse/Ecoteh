package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleBuilder;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.*;
import static ua.com.ecoteh.mocks.repository.MockRepository.getArticleRepository;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleServiceImplTest extends ContentServiceImplTest<Article> {

    private static ArticleService service;
    private static Article article;
    private static Collection<Article> articles;

    @BeforeClass
    public static void beforeClass() {
        final ArticleRepository repository = getArticleRepository();
        final FileService fileService = getFileService();
        service = new ArticleServiceImpl(repository, fileService);
        article = getArticle();
        articles = getArticles();
    }

    @Test
    public void whenGetByNumberThenReturnNotNull() {
        Article article = service.getByNumber(NUMBER, true);
        assertNotNull(article);
        article = service.getByNumber(NUMBER, false);
        assertNotNull(article);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullNumberThenThrowIllegalArgumentException() {
        service.getByNumber(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyNumberThenThrowIllegalArgumentException() {
        service.getByNumber("", true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownNumberThenThrowNullPointerException() {
        service.getByNumber(ANY_STRING, true);
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
    public void whenGetByNullCategoryTitleThenThrowIllegalArgumentException() {
        service.getByCategoryTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyCategoryTitleThenThrowIllegalArgumentException() {
        service.getByCategoryTitle("");
    }

    @Test
    public void whenGetByUnknownCategoryTitleThenReturnEmptyCollection() {
        final Collection<Article> articles = service.getByCategoryTitle(ANY_STRING);
        assertTrue(articles.isEmpty());
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
    public void whenSortNullCollectionByNumberThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByNumber(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByNumber(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByNumberThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByNumber(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByNumber(new ArrayList<>(), false);
        assertTrue(sortedArticles.isEmpty());
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
    public void whenSortNullCollectionByDateThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByDate(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByDate(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByDateThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByDate(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByDate(new ArrayList<>(), false);
        assertTrue(sortedArticles.isEmpty());
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
    public void whenSortNullCollectionByPriceThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByPrice(null, true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByPrice(null, false);
        assertTrue(sortedArticles.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionByPriceThenReturnEmptyCollection() {
        Collection<Article> sortedArticles = service.sortByPrice(new ArrayList<>(), true);
        assertTrue(sortedArticles.isEmpty());
        sortedArticles = service.sortByPrice(new ArrayList<>(), false);
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
    public void whenGetAndSortByNumberThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.getAndSortByNumber(true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.getAndSortByNumber(false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSortByDateThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.getAndSortByDate(true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.getAndSortByDate(false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenGetAndSortByDateThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.getAndSortByDate(true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.getAndSortByDate(false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSortByPriceThenReturnNotEmptyCollection() {
        Collection<Article> sortedArticles = service.getAndSortByPrice(true);
        assertFalse(sortedArticles.isEmpty());
        sortedArticles = service.getAndSortByPrice(false);
        assertFalse(sortedArticles.isEmpty());
    }

    @Test
    public void whenGetAndSortByPriceThenReturnCollectionWithNotNullObjects() {
        Collection<Article> sortedArticles = service.getAndSortByPrice(true);
        sortedArticles.forEach(Assert::assertNotNull);
        sortedArticles = service.getAndSortByPrice(false);
        sortedArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.filterByDate(articles, start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.filterByDate(articles, start, finish);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByDateThenReturnEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.filterByDate(null, start, finish);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByDateThenReturnEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.filterByDate(new ArrayList<>(), start, finish);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.filterByDate(articles, start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullFinishDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Article> filteredArticles = service.filterByDate(articles, start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = null;
        final Collection<Article> filteredArticles = service.filterByDate(articles, start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoryThenReturnNotEmptyCollection() {
        final Collection<Article> articles = new ArrayList<>();
        final Category category = getCategory();
        final ArticleBuilder builder = Article.getBuilder();
        builder.addCategory(category);
        final Article article = builder.build();
        articles.add(article);
        final Collection<Article> filteredArticles = service.filterByCategory(articles, category);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoryThenReturnCollectionWithNotNullObjects() {
        final Category category = getCategory();
        final Collection<Article> filteredArticles = service.filterByCategory(articles, category);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByCategoryThenReturnEmptyCollection() {
        final Category category = getCategory();
        final Collection<Article> filteredArticles = service.filterByCategory(null, category);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByCategoryThenReturnEmptyCollection() {
        final Category category = getCategory();
        final Collection<Article> filteredArticles = service.filterByCategory(new ArrayList<>(), category);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByNullCategoryThenReturnNotEmptyCollection() {
        final Category category = null;
        final Collection<Article> filteredArticles = service.filterByCategory(articles, category);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByNullCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(null, null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByNullCategoryThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByCategory(new ArrayList<>(), null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoriesThenReturnNotEmptyCollection() {
        final Collection<Article> articles = new ArrayList<>();
        final Collection<Category> categories = new ArrayList<>();
        final Category category = getCategory();
        categories.add(category);
        final ArticleBuilder builder = Article.getBuilder();
        builder.addCategory(category);
        final Article article = builder.build();
        articles.add(article);
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByCategoriesThenReturnCollectionWithNotNull() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByCategoriesThenReturnEmptyCollection() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.filterByCategories(null, categories);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByCategoriesThenReturnEmptyCollection() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.filterByCategories(new ArrayList<>(), categories);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByNullCategoriesThenReturnIncomingCollection() {
        final Collection<Category> categories = null;
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        assertFalse(filteredArticles.isEmpty());
        assertEquals(filteredArticles, articles);
    }

    @Test
    public void whenFilterByEmptyCategoriesThenReturnIncomingCollection() {
        final Collection<Category> categories = new ArrayList<>();
        final Collection<Article> filteredArticles = service.filterByCategories(articles, categories);
        assertFalse(filteredArticles.isEmpty());
        assertEquals(filteredArticles, articles);
    }

    @Test
    public void whenGetAndFilterByDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullStartDateThenReturnCollectionWithNotNullObjects() {
        final Date start = null;
        final Date finish = new Date();
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnNotEmptyCollection() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullFinishDateThenReturnCollectionWithNotNullObjects() {
        final Date start = new Date();
        final Date finish = null;
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnNotEmptyCollection() {
        final Date start = null;
        final Date finish = null;
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByDateWithNullDatesThenReturnCollectionWithNotNullObjects() {
        final Date start = null;
        final Date finish = null;
        final Collection<Article> filteredArticles = service.getAndFilterByDate(start, finish);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByCategoryThenReturnNotEmptyCollection() {
        final Category category = getCategory();
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByCategoryThenReturnCollectionWithNotNullObjects() {
        final Category category = getCategory();
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByNullCategoryThenReturnFullCollection() {
        final Category category = null;
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullCategoryThenReturnFullCollectionWithNotNullObjects() {
        final Category category = null;
        final Collection<Article> filteredArticles = service.getAndFilterByCategory(category);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByCategoriesThenReturnNotEmptyCollection() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByCategoriesThenReturnCollectionWithNotNullObjects() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByNullCategoriesThenReturnFullCollection() {
        final Collection<Category> categories = null;
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByNullCategoriesThenReturnFullCollectionWithNotNullObjects() {
        final Collection<Category> categories = getCategories();
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndFilterByEmptyCategoriesThenReturnFullCollection() {
        final Collection<Category> categories = new ArrayList<>();
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenGetAndFilterByEmptyCategoriesThenReturnFullCollectionWithNotNullObjects() {
        final Collection<Category> categories = new ArrayList<>();
        final Collection<Article> filteredArticles = service.getAndFilterByCategories(categories);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByValid(articles);
        assertFalse(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnCollectionWithNotNullObjects() {
        final Collection<Article> filteredArticles = service.filterByValid(articles);
        filteredArticles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByValid(null);
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final Collection<Article> filteredArticles = service.filterByValid(new ArrayList<>());
        assertTrue(filteredArticles.isEmpty());
    }

    @Test
    public void whenRemoveByNumberThenDoIt() {
        service.removeByNumber(NUMBER);
    }

    @Test
    public void whenRemoveByNullNumberThenDoNothing() {
        service.removeByNumber(null);
    }

    @Test
    public void whenRemoveByEmptyNumberThenDoNothing() {
        service.removeByNumber("");
    }

    @Override
    protected ArticleService getService() {
        return service;
    }

    @Override
    protected Article getModel() {
        return article;
    }

    @Override
    protected Collection<Article> getModels() {
        return articles;
    }

    @Override
    protected Article getUnknownModel() {
        final ArticleBuilder builder = Article.getBuilder();
        builder.addId(UNKNOWN_ID).addTitle(ANY_STRING).addUrl(ANY_STRING);
        return builder.build();
    }
}
