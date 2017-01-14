package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ArticleTest extends ContentTest<Article> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Article article = new Article();
        assertNotNull(article.getDate());
        assertNotNull(article.getNumber());

        article = new Article(null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article("", "", "", "", "");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article(" ", " ", " ", " ", " ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());

        article = new Article("   ", "   ", "   ", "   ", "   ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Article article = new Article(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.TEXT,
                MockConstants.KEYWORDS,
                MockConstants.NUMBER
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
        assertEquals(
                article.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                article.getDescription(),
                MockConstants.DESCRIPTION
        );
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
    }

    @Test
    @Override
    public void toStringTest() {
        final Article article = MockEntity.getArticle();
        final String value = article.getClass().getSimpleName() +
                " " + article.getTitle()
                + " \nKeywords: " + article.getKeywords()
                + " \nURL: " + article.getUrl()
                + " \nDescription: " + article.getDescription()
                + " \nText: " + article.getText()
                + " \nDate: " + article.getDate()
                + " \nNumber: " + article.getNumber();
        assertEquals(
                article.toString(),
                value
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Article article1 = new Article();
        final Article article2 = new Article();
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
        article1.setTitle(MockConstants.TITLE);
        article2.setTitle(MockConstants.TITLE);
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
        article1.setText(MockConstants.TEXT);
        article2.setText(MockConstants.TEXT);
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Article article1 = MockEntity.getArticle();
        final Article article2 = (Article) article1.clone();
        assertEquals(article1, article2);
        final boolean value = (
                isNotBlank(article1.getTitle())
                        ? article1.getTitle().equalsIgnoreCase(article2.getTitle()) :
                        isBlank(article2.getTitle())
        ) && (
                isNotBlank(article1.getUrl()) ?
                        article1.getUrl().equalsIgnoreCase(article2.getUrl()) :
                        isBlank(article2.getUrl())
        );
        assertEquals(
                article1.equals(article2),
                value
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Article article = new Article();
        int value = isNotBlank(article.getNumber()) ? article.getNumber().hashCode() : 0;
        assertEquals(
                article.hashCode(),
                value
        );
        article.setTitle(MockConstants.TITLE);
        value += (isNotBlank(article.getTitle()) ? article.getTitle().hashCode() : 0)
                + (isNotBlank(article.getUrl()) ? article.getUrl().hashCode() : 0);
        assertEquals(
                article.hashCode(),
                value
        );
        article.setText(MockConstants.TEXT);
        value += isNotBlank(article.getText()) ? article.getText().hashCode() : 0;
        assertEquals(
                article.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Article article = MockEntity.getArticle();
        final int value = (
                isNotBlank(article.getTitle()) ? article.getTitle().hashCode() : 0
        ) + (
                isNotBlank(article.getUrl()) ? article.getUrl().hashCode() : 0
        ) + (
                isNotBlank(article.getNumber()) ? article.getNumber().hashCode() : 0
        ) + (
                isNotBlank(article.getText()) ? article.getText().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(article.hashCode(), value);
        }
    }

    @Test
    @Override
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        super.whenInitializeObjectWithInvalidParametersThenGetNull();
        final Article article = new Article();
        article.initialize(null, null, null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getPhoto());
        assertNotNull(article.getDate());

        final File file = new File();
        article.initialize("", "", "", "", "", null, file);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getPhoto());
        assertNotNull(article.getDate());

        article.initialize(" ", " ", " ", " ", " ", null, file);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getPhoto());
        assertNotNull(article.getDate());

        article.initialize("   ", "   ", "   ", "   ", "   ", null, file);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNull(article.getPhoto());
        assertNotNull(article.getDate());
    }

    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Article article = new Article();
        final Category category = MockEntity.getCategory();
        final File mainFile = MockEntity.getPhoto();
        article.initialize(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.TEXT,
                MockConstants.KEYWORDS,
                MockConstants.NUMBER,
                category,
                mainFile
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNotNull(article.getCategory());
        assertNotNull(article.getPhoto());
        assertNotNull(article.getDate());
        assertEquals(
                article.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                article.getDescription(),
                MockConstants.DESCRIPTION
        );
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getKeywords(),
                MockConstants.KEYWORDS
        );
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
        assertEquals(
                article.getCategory(),
                category
        );
        assertEquals(
                article.getPhoto(),
                mainFile
        );
    }

    @Test
    public void whenSetInvalidNumberThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setNumber(null);
        assertNotNull(article.getNumber());
        article.setNumber("");
        assertNotNull(article.getNumber());
        article.setNumber(" ");
        assertNotNull(article.getNumber());
        article.setNumber("   ");
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenSetValidNumberThenGetThisNumber() {
        final Article article = MockEntity.getArticle();
        article.setNumber(MockConstants.NUMBER);
        assertNotNull(article.getNumber());
        assertEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
        article.newNumber();
        assertNotNull(article.getNumber());
        assertNotEquals(
                article.getNumber(),
                MockConstants.NUMBER
        );
    }

    @Test
    public void whenSetInvalidTextThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setText(null);
        assertNull(article.getText());
        article.setText("");
        assertNull(article.getText());
        article.setText(" ");
        assertNull(article.getText());
        article.setText("        ");
        assertNull(article.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Article article = MockEntity.getArticle();
        article.setText(MockConstants.TEXT);
        assertNotNull(article.getText());
        assertEquals(
                article.getText(),
                MockConstants.TEXT
        );
    }

    @Test
    public void whenSetInvalidDateThenGetNewDate() {
        final Article article = MockEntity.getArticle();
        article.setDate(null);
        assertNotNull(article.getDate());
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final Article article = MockEntity.getArticle();
        final Date date = new Date();
        article.setDate(date);
        assertNotNull(article.getDate());
        assertEquals(
                article.getDate(),
                date
        );
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetNullCategoryThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setCategory(null);
        assertNull(article.getCategory());
    }

    @Test
    public void whenSetNotNullCategoryThenGetThisCategory() {
        final Article article = MockEntity.getArticle();
        final Category category = MockEntity.getCategory();
        category.setArticles(null);
        article.setCategory(null);
        article.setCategory(category);
        assertNotNull(article.getCategory());
        assertEquals(
                article.getCategory(),
                category
        );
        assertTrue(category.containsArticle(article));
        article.setCategory(null);
        assertNull(article.getCategory());
        assertFalse(category.containsArticle(article));
    }

    @Test
    public void whenSetInvalidMainPhotoThenGetNull() {
        final Article article = MockEntity.getArticle();
        article.setPhoto(null);
        assertNull(article.getPhoto());

        final File invalidFile = new File();
        article.setPhoto(invalidFile);
        assertNull(article.getPhoto());

        final File file = MockEntity.getPhoto();
        file.setUrl(null);
        article.setPhoto(file);
        assertNull(article.getPhoto());
    }

    @Test
    public void whenSetValidMainPhotoThenGetThisPhoto() {
        final Article article = MockEntity.getArticle();
        final File file = MockEntity.getPhoto();
        article.setPhoto(file);
        assertNotNull(article.getPhoto());
        assertEquals(
                article.getPhoto(),
                file
        );
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Article article = MockEntity.getArticle();
        assertFalse(Article.isValidated(null));
        assertTrue(
                Article.isValidated(article)
        );
        article.setTitle(null);
        assertFalse(
                Article.isValidated(article)
        );
        article = MockEntity.getArticle();
        article.setUrl(null);
        assertFalse(
                Article.isValidated(article)
        );
        article = MockEntity.getArticle();
        article.setText(null);
        assertFalse(
                Article.isValidated(article)
        );
    }

    @Ignore
    @Override
    protected Article getObject() {
        return MockEntity.getArticle();
    }
}
