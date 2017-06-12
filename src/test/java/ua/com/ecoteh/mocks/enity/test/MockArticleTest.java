package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticle;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticles;

public final class MockArticleTest extends MockContentTest<Article> {

    private static Article article;
    private static Collection<Article> articles;

    @BeforeClass
    public static void beforeClass() {
        article = getArticle();
        articles = getArticles();
    }

    @Test
    public void whenGetTextThenReturnNotEmpty() {
        assertFalse(article.getText().isEmpty());
    }

    @Test
    public void whenGetNumberThenReturnNotEmpty() {
        assertFalse(article.getNumber().isEmpty());
    }

    @Test
    public void whenGetCategoryThenReturnNotNull() {
        assertNotNull(article.getCategory());
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
    protected Article getObject() {
        return article;
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return articles;
    }
}
