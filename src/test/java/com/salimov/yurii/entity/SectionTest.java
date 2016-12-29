package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class SectionTest extends ContentTest<Section> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Section section = new Section(null, null, null);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());

        section = new Section("", "", "");
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());

        section = new Section(" ", " ", " ");
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());

        section = new Section("   ", "   ", "   ");
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Section section = new Section(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.KEYWORDS
        );
        assertNotNull(section.getTitle());
        assertNotNull(section.getDescription());
        assertNotNull(section.getKeywords());
        assertEquals(
                section.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                section.getDescription(),
                MockConstants.DESCRIPTION
        );
        assertEquals(
                section.getKeywords(),
                MockConstants.KEYWORDS
        );
    }

    @Test
    @Override
    public void toStringTest() {
        final Section section = MockEntity.getSection();
        final List<Category> categories = MockEntity.getCategories();
        section.setCategories(categories);
        assertNotNull(section.toString());
        final String value = section.getClass().getSimpleName() +
                " " + section.getTitle()
                + " \nKeywords: " + section.getKeywords()
                + " \nURL: " + section.getUrl()
                + " \nDescription: " + section.getDescription();
        assertEquals(
                section.toString(),
                value
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Section section1 = new Section();
        final Section section2 = new Section();
        assertTrue(section1.equals(section2));
        section1.setTitle(MockConstants.TITLE);
        section2.setTitle(MockConstants.TITLE);
        assertTrue(
                section1.equals(section2)
        );
        section1.setUrl(MockConstants.URL);
        section2.setUrl(MockConstants.URL);
        assertTrue(
                section1.equals(section2)
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Section section = new Section();
        int value = 0;
        assertEquals(section.hashCode(), value);

        value += (
                isNotBlank(section.getTitle()) ? section.getTitle().hashCode() : 0
        ) + (
                isNotBlank(section.getUrl()) ? section.getUrl().hashCode() : 0
        );
        assertEquals(
                section.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        super.whenInitializeObjectWithInvalidParametersThenGetNull();

        final Section section = MockEntity.getSection();
        section.initialize(null, null, null, null);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
        assertNull(section.getPhoto());

        final Photo photo = MockEntity.getPhoto();
        photo.setTitle(null);
        section.initialize("", "", "", photo);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
        assertNull(section.getPhoto());

        photo.setUrl(MockConstants.URL);
        section.initialize(" ", " ", " ", photo);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
        assertNotNull(section.getPhoto());

        photo.setUrl(null);
        section.initialize(" ", " ", " ", photo);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
        assertNull(section.getPhoto());

        photo.setUrl("");
        section.initialize("   ", "   ", "   ", photo);
        assertNull(section.getTitle());
        assertNull(section.getDescription());
        assertNull(section.getKeywords());
        assertNull(section.getPhoto());
    }

    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Section section = MockEntity.getSection();
        section.initialize(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.KEYWORDS,
                MockEntity.getPhoto()
        );
        assertNotNull(section.getTitle());
        assertNotNull(section.getDescription());
        assertNotNull(section.getKeywords());
        assertNotNull(section.getPhoto());
        assertEquals(
                section.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                section.getDescription(),
                MockConstants.DESCRIPTION);
        assertEquals(
                section.getKeywords(),
                MockConstants.KEYWORDS
        );
    }

    @Test
    public void whenCategoriesAreInvalidThenNotAddThey() {
        final Section section = new Section();
        section.setCategories(null);
        assertNotNull(section.getCategories());
        assertNotNull(section.getCategories());
        assertTrue(
                section.getCategories().isEmpty()
        );
        assertTrue(
                section.getCategories().isEmpty()
        );
    }

    @Test
    public void whenCategoriesAreValidThenAddThey() {
        final Section section = MockEntity.getSection();
        final Category category = MockEntity.getCategory();
        final List<Article> articles = MockEntity.getArticles();
        category.addArticles(articles);
        final List<Category> categories = MockEntity.getCategories();
        section.setCategories(null);
        assertNotNull(section.getCategories());
        assertNotNull(section.getCategories());
        assertTrue(
                section.getCategories().isEmpty()
        );
        assertTrue(
                section.getCategories().isEmpty()
        );
        for (int i = 0; i < 10; i++) {
            section.addCategory(category);
            assertFalse(
                    section.getCategories().isEmpty()
            );
            assertFalse(
                    section.getCategories().isEmpty()
            );
            assertEquals(
                    section.getCategories().size(), 1
            );
            assertEquals(
                    section.getCategories().size(), 1
            );
        }
        section.removeCategory(category);
        assertNotNull(section.getCategories());
        assertNotNull(section.getCategories());
        assertTrue(
                section.getCategories().isEmpty()
        );
        assertTrue(
                section.getCategories().isEmpty()
        );
        for (int i = 0; i < 10; i++) {
            section.setCategories(categories);
            assertFalse(
                    section.getCategories().isEmpty()
            );
            assertEquals(
                    section.getCategories().size(),
                    categories.size()
            );
        }
        section.removeCategories(categories);
        assertNotNull(section.getCategories());
        assertTrue(
                section.getCategories().isEmpty()
        );

        section.addCategory(category);
        section.addCategories(categories);
        assertTrue(
                section.containsCategory(category)
        );
        assertTrue(
                section.containsCategories(categories)
        );
        section.addCategory(category);
        section.addCategories(categories);
        section.clearCategories();
        assertNotNull(
                section.getCategories()
        );
        assertTrue(
                section.getCategories().isEmpty()
        );
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Section section = MockEntity.getSection();
        final Category category = MockEntity.getCategory();
        final Article article = MockEntity.getArticle();
        category.addArticle(article);
        final List<Category> categories = new ArrayList<>();
        categories.add(category);
        section.setCategories(categories);

        assertFalse(Section.isValidated(null));
        assertTrue(
                Section.isValidated(section)
        );
        section.setTitle(null);
        assertFalse(
                Section.isValidated(section)
        );
        section = MockEntity.getSection();
        section.setUrl(null);
        assertFalse(
                Section.isValidated(section)
        );
        section = MockEntity.getSection();
        section.setCategories(null);
        assertFalse(
                Section.isValidated(section)
        );
    }

    @Ignore
    @Override
    protected Section getObject() {
        return MockEntity.getSection();
    }
}
