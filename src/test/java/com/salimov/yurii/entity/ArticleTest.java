package com.salimov.yurii.entity;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getArticle;
import static com.salimov.yurii.mocks.enity.MockEntity.getCategory;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class ArticleTest extends ContentTest<Article> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDateAndNumber() {
        final Article article = new Article();
        assertNotNull(article.getDate());
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveNull() {
        final Article article = new Article(null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_1() {
        final Article article = new Article("", "", "", "", "");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_2() {
        final Article article = new Article(" ", " ", " ", " ", " ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_3() {
        final Article article = new Article("   ", "   ", "   ", "   ", "   ");
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Article article = new Article(
                TITLE,
                DESCRIPTION, TEXT,
                KEYWORDS, NUMBER
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
        assertEquals(article.getTitle(), TITLE);
        assertEquals(article.getDescription(), DESCRIPTION);
        assertEquals(article.getText(), TEXT);
        assertEquals(article.getKeywords(), KEYWORDS);
        assertEquals(article.getNumber(), NUMBER);
    }

    @Test
    @Override
    public void toStringTest() {
        final Article article = getArticle();
        assertNotNull(article.toString());
        final String articleToString = "Article{" +
                "Content{" +
                "Model{" +
                "id=" + article.getId() +
                ", validated=" + article.isValidated() +
                '}' +
                ", title='" + article.getTitle() + '\'' +
                ", url='" + article.getUrl() + '\'' +
                ", description='" + article.getDescription() + '\'' +
                ", keywords='" + article.getKeywords() + '\'' +
                '}' +
                ", number='" + article.getNumber() + '\'' +
                ", text='" + article.getText() + '\'' +
                ", date=" + article.getDate() +
                ", category=" + article.getCategory() +
                '}';
        assertEquals(article.toString(), articleToString);
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
        article1.setTitle(TITLE);
        article2.setTitle(TITLE);
        assertEquals(
                article1.equals(article2),
                article1.getNumber()
                        .equals(
                                article2.getNumber()
                        )
        );
        article1.setText(TEXT);
        article2.setText(TEXT);
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
        final Article article1 = getArticle();
        final Article article2 = article1.clone();
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
        article.setTitle(TITLE);
        value += (isNotBlank(article.getTitle()) ? article.getTitle().hashCode() : 0)
                + (isNotBlank(article.getUrl()) ? article.getUrl().hashCode() : 0);
        assertEquals(
                article.hashCode(),
                value
        );
        article.setText(TEXT);
        value += isNotBlank(article.getText()) ? article.getText().hashCode() : 0;
        assertEquals(
                article.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Article article = getArticle();
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
    public void whenInitializeObjectWithNullParametersThenGetNull() {
        super.whenInitializeObjectWithNullParametersThenGetNull();
        final Article article = new Article();
        article.initialize(null, null, null, null, null, null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNotNull(article.getDate());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_1() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_1();
        final Article article = new Article();
        article.initialize("", "", "", "", "", null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNotNull(article.getDate());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_2() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_2();
        final Article article = new Article();
        article.initialize(" ", " ", " ", " ", " ", null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNotNull(article.getDate());
    }

    @Test
    @Override
    public void whenInitializeObjectWithBlankParametersThenGetNull_3() {
        super.whenInitializeObjectWithBlankParametersThenGetNull_3();
        final Article article = new Article();
        article.initialize("  ", "  ", "  ", "  ", "  ", null);
        assertNull(article.getTitle());
        assertNull(article.getDescription());
        assertNull(article.getText());
        assertNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNull(article.getCategory());
        assertNotNull(article.getDate());
    }


    @Test
    @Override
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        super.whenInitializeObjectWithValidParametersThenGetThisValue();
        final Article article = new Article();
        final Category category = getCategory();
        article.initialize(
                TITLE,
                DESCRIPTION, TEXT,
                KEYWORDS, NUMBER,
                category
        );
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getKeywords());
        assertNotNull(article.getNumber());
        assertNotNull(article.getCategory());
        assertNotNull(article.getDate());
        assertEquals(article.getTitle(), TITLE);
        assertEquals(article.getDescription(), DESCRIPTION);
        assertEquals(article.getText(), TEXT);
        assertEquals(article.getKeywords(), KEYWORDS);
        assertEquals(article.getKeywords(), KEYWORDS);
        assertEquals(article.getNumber(), NUMBER);
        assertEquals(article.getCategory(), category);
    }

    @Test
    public void whenSetNullNumberThenGetNull() {
        final Article article = getArticle();
        article.setNumber(null);
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenSetBlankNumberThenGetNull() {
        final Article article = getArticle();
        article.setNumber("");
        assertNotNull(article.getNumber());
        article.setNumber(" ");
        assertNotNull(article.getNumber());
        article.setNumber("   ");
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenSetValidNumberThenGetThisNumber() {
        final Article article = getArticle();
        article.setNumber(NUMBER);
        assertNotNull(article.getNumber());
        assertEquals(article.getNumber(), NUMBER);
        article.newNumber();
        assertNotNull(article.getNumber());
        assertNotEquals(article.getNumber(), NUMBER);
    }

    @Test
    public void whenSetNullTextThenGetNull() {
        final Article article = getArticle();
        article.setText(null);
        assertNull(article.getText());
    }

    @Test
    public void whenSetBlankTextThenGetNull() {
        final Article article = getArticle();
        article.setText("");
        assertNull(article.getText());
        article.setText(" ");
        assertNull(article.getText());
        article.setText("        ");
        assertNull(article.getText());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Article article = getArticle();
        article.setText(TEXT);
        assertNotNull(article.getText());
        assertEquals(article.getText(), TEXT);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDate() {
        final Article article = getArticle();
        article.setDate(null);
        assertNotNull(article.getDate());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final Article article = getArticle();
        final Date date = new Date();
        article.setDate(date);
        assertNotNull(article.getDate());
        assertEquals(article.getDate(), date);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDateToString() {
        final Article article = getArticle();
        article.setDate(null);
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetValidDateThenGetThisDateToString() {
        final Article article = getArticle();
        article.setDate(new Date());
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetNullCategoryThenGetNull() {
        final Article article = getArticle();
        article.setCategory(null);
        assertNull(article.getCategory());
    }

    @Test
    public void whenSetNotNullCategoryThenGetThisCategory() {
        final Article article = getArticle();
        final Category category = getCategory();
        category.setArticles(null);
        article.setCategory(null);
        article.setCategory(category);
        assertNotNull(article.getCategory());
        assertEquals(article.getCategory(), category);
        assertTrue(category.containsArticle(article));
        article.setCategory(null);
        assertNull(article.getCategory());
        assertFalse(category.containsArticle(article));
    }

    @Test
    @Override
    public void validObject() {
        super.validObject();
        Article article = getArticle();
        assertFalse(Article.isValidated(null));
        assertTrue(Article.isValidated(article));
        article.setTitle(null);
        assertFalse(Article.isValidated(article));
        article = getArticle();
        article.setUrl(null);
        assertFalse(Article.isValidated(article));
        article = getArticle();
        article.setText(null);
        assertFalse(Article.isValidated(article));
    }

    @Ignore
    @Override
    protected Article getObject() {
        return getArticle();
    }
}
