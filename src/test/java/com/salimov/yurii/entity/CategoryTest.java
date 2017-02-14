package com.salimov.yurii.entity;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class CategoryTest extends ContentTest<Category> {

    @Test
    public void whenPassNullParametersInConstructorThenSaveNull() {
        final Category category = new Category(null, null, null);
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_1() {
        final Category category = new Category("", "", "");
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_2() {
        final Category category = new Category(" ", " ", " ");
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_3() {
        final Category category = new Category("   ", "   ", "   ");
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Category category = new Category(TITLE, DESCRIPTION, KEYWORDS);
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
        assertEquals(category.getTitle(), TITLE);
        assertEquals(category.getDescription(), DESCRIPTION);
        assertEquals(category.getKeywords(), KEYWORDS);
    }

    @Test
    @Override
    public void toStringTest() {
        final Category category = getCategory();
        assertNotNull(category.toString());
        final String categoryToString = "Category{" +
                "Content{" +
                "Model{" +
                "id=" + category.getId() +
                ", validated=" + category.isValidated() +
                '}' +
                ", title='" + category.getTitle() + '\'' +
                ", url='" + category.getUrl() + '\'' +
                ", description='" + category.getDescription() + '\'' +
                ", keywords='" + category.getKeywords() + '\'' +
                '}' +
                ", photoUrl='" + category.getPhotoUrl() + '\'' +
                '}';
        assertEquals(category.toString(), categoryToString);
    }

    @Test
    public void equalsObjects() {
        final Category category1 = new Category();
        final Category category2 = new Category();
        assertTrue(category1.equals(category2));
        category1.setTitle(TITLE);
        category2.setTitle(TITLE);
        assertTrue(category1.equals(category2));
        category1.setUrl(URL);
        category2.setUrl(URL);
        assertTrue(category1.equals(category2));
    }

    @Test
    public void hashCodeObject() {
        final Category category = new Category();
        int value = 0;
        assertEquals(category.hashCode(), value);
        category.setTitle(TITLE);
        value += (
                isNotBlank(category.getTitle()) ? category.getTitle().hashCode() : 0
        ) + (
                isNotBlank(category.getUrl()) ? category.getUrl().hashCode() : 0
        );
        assertEquals(category.hashCode(), value);
    }

    @Test
    @Override
    public void whenInitializeObjectWithNullParametersThenGetNull() {
        super.whenInitializeObjectWithNullParametersThenGetNull();
        final Category category = new Category();
        category.initialize(null, null, null, null);
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
        assertNull(category.getPhotoUrl());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_1() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_1();
        final Category category = new Category();
        category.initialize("", "", "", null);
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
        assertNull(category.getPhotoUrl());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_2() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_2();
        final Category category = new Category();
        category.initialize("", "", "", null);
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
        assertNull(category.getPhotoUrl());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_3() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_3();
        final Category category = new Category();
        category.initialize("", "", "", null);
        assertNull(category.getTitle());
        assertNull(category.getDescription());
        assertNull(category.getKeywords());
        assertNull(category.getPhotoUrl());
    }

    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Category category = new Category();
        category.initialize(
                TITLE,
                DESCRIPTION,
                KEYWORDS,
                ANY_STRING
        );
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
        assertNotNull(category.getPhotoUrl());
        assertEquals(category.getTitle(), TITLE);
        assertEquals(category.getDescription(), DESCRIPTION);
        assertEquals(category.getKeywords(), KEYWORDS);
        assertEquals(category.getPhotoUrl(), ANY_STRING);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNull() {
        final Category category = new Category();
        category.setPhotoUrl(null);
        assertNull(category.getPhotoUrl());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final Category category = new Category();
        category.setPhotoUrl(ANY_STRING);
        assertNotNull(category.getPhotoUrl());
        assertEquals(category.getPhotoUrl(), ANY_STRING);
    }

    @Test
    public void whenArticlesAreInvalidThenNotAddThey() {
        final Category category = new Category();
        category.setArticles(null);
        assertNotNull(category.getArticles());
        assertTrue(category.getArticles().isEmpty());
    }

    @Test
    public void whenArticlesAreValidThenAddThey() {
        final Category category = new Category();
        final Article article = getArticle();
        final List<Article> articles = getArticles();

        assertNotNull(category.getArticles());
        assertTrue(category.getArticles().isEmpty());
        checkArticlesSize(
                articles.size(),
                category,
                articles
        );
        category.removeArticle(article);
        assertNotNull(category.getArticles());
        assertFalse(category.getArticles().isEmpty());
        checkArticlesSize(
                articles.size(),
                category,
                articles
        );
        category.removeArticles(articles);
        assertNotNull(category.getArticles());
        assertTrue(category.getArticles().isEmpty());
        category.addArticle(article);
        category.addArticles(articles);
        assertTrue(category.containsArticle(article));
        assertTrue(category.containsArticles(articles));
        category.addArticle(article);
        category.addArticles(articles);
        category.clearArticles();
        assertNotNull(category.getArticles());
        assertTrue(category.getArticles().isEmpty());
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Category category = getCategory();
        assertFalse(Category.isValidated(null));
        assertTrue(Category.isValidated(category));
        category.setTitle(null);
        assertFalse(Category.isValidated(category));
        category = getCategory();
        category.setUrl(null);
        assertFalse(Category.isValidated(category));
        category = getCategory();
        category.setArticles(null);
        assertFalse(Category.isValidated(category));
        category = getCategory();
        category.setArticles(getArticles());
        assertTrue(Category.isValidated(category));
    }

    @Ignore
    @Override
    protected Category getObject() {
        return getCategory();
    }

    @Ignore
    private void checkArticlesSize(
            final int size,
            final Category category,
            final List<Article> articles
    ) {
        for (int i = 0; i < 10; i++) {
            category.addArticles(articles);
            assertFalse(category.getArticles().isEmpty());
            assertEquals(category.getArticles().size(), size);
        }
    }
}
