package ua.com.ecoteh.mocks.repository.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.repository.ArticleRepository;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getArticleRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockArticleRepositoryTest extends MockContentRepositoryTest<ArticleEntity> {

    private static ArticleEntity article;
    private static Collection<ArticleEntity> articles;
    private static ArticleRepository repository;

    @BeforeClass
    public static void beforeClass() {
        article = getArticleEntity();
        articles = getArticleEntities();
        repository = getArticleRepository();
    }

    @Test
    public void whenFindByNumberThenReturnNotNull() {
        final ArticleEntity entity = repository.findByNumber(NUMBER);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownNumberThenReturnNull() {
        final ArticleEntity entity = repository.findByNumber(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullNumberThenReturnNull() {
        final ArticleEntity entity = repository.findByNumber(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByCategoryIdThenReturnNotEmptyCollection() {
        final Collection<ArticleEntity> articles = repository.findByCategoryId(ID);
        assertFalse(articles.isEmpty());
    }

    @Test
    public void whenFindByCategoryIdThenReturnCollectionWithNotNullObjects() {
        final Collection<ArticleEntity> articles = repository.findByCategoryId(ID);
        articles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFindByUnknownCategoryIdThenReturnEmptyCollection() {
        final Collection<ArticleEntity> articles = repository.findByCategoryId(UNKNOWN_ID);
        assertTrue(articles.isEmpty());
    }

    @Test
    public void whenFindByCategoryTitleThenReturnNotEmptyCollection() {
        final Collection<ArticleEntity> articles = repository.findByCategoryTitle(TITLE);
        assertFalse(articles.isEmpty());
    }

    @Test
    public void whenFindByCategoryTitleThenReturnCollectionWithNotNullObjects() {
        final Collection<ArticleEntity> articles = repository.findByCategoryTitle(TITLE);
        articles.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFindByUnknownCategoryTitleThenReturnEmptyCollection() {
        final Collection<ArticleEntity> articles = repository.findByCategoryTitle(ANY_STRING);
        assertTrue(articles.isEmpty());
    }

    @Override
    protected ArticleRepository getRepository() {
        return repository;
    }

    @Override
    protected ArticleEntity getObject() {
        return article;
    }

    @Override
    protected Collection<ArticleEntity> getObjects() {
        return articles;
    }
}
