package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.Article;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class MockArticleTest extends MockModelTest<Article> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Article article = MockEntity.getArticle();
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNotNull(article.getId());
        assertNotNull(article.getUrl());
        assertNull(article.getCategory());
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
