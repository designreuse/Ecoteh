package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.Article;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getArticle;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticles;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class MockArticleTest extends MockModelTest<Article> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Article article = getArticle();
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
        return getArticle();
    }

    @Ignore
    @Override
    protected Collection<Article> getObjects() {
        return getArticles();
    }
}
