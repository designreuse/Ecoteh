package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockArticleEntityTest extends MockContentEntityTest<ArticleEntity> {

    private static ArticleEntity article;
    private static Collection<ArticleEntity> articles;

    @BeforeClass
    public static void beforeClass() {
        article = getArticleEntity();
        articles = getArticleEntities();
    }

    @Test
    public void whenGetNumberThenReturnNotEmpty() {
        assertFalse(article.getNumber().isEmpty());
    }

    @Test
    public void whenGetCategoryThenReturnNotNull() {
        assertNotNull(article.getCategoryEntity());
    }

    @Test
    public void whenGetDateThenReturnNotNull() {
        assertNotNull(article.getDate());
    }

    @Test
    public void whenGetCurrencyThenReturnNotEmpty() {
        assertFalse(article.getCurrency().isEmpty());
    }

    @Test
    public void whenGetPriceThenReturnNotNegative() {
        assertTrue(article.getPrice() > 0);
    }

    @Ignore
    @Override
    protected ArticleEntity getObject() {
        return article;
    }

    @Ignore
    @Override
    protected Collection<ArticleEntity> getObjects() {
        return articles;
    }
}
