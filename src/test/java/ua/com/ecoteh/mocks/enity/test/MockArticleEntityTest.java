package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.article.ArticleEntity;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class MockArticleEntityTest extends MockModelTest<ArticleEntity> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getKeywords());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getId());
        assertNotNull(articleEntity.getUrl());
        assertNull(articleEntity.getCategoryEntity());
    }

    @Ignore
    @Override
    protected ArticleEntity getObject() {
        return MockEntity.getArticleEntity();
    }

    @Ignore
    @Override
    protected Collection<ArticleEntity> getObjects() {
        return MockEntity.getArticleEntities();
    }
}
