package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Date;

import static org.junit.Assert.*;

public final class ArticleTest extends ContentTest<Article> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDateAndNumber() {
        final Article article = new Article();
        assertNotNull(article.getDate());
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final Article article = new Article(null, null, null, null, null);
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final Article article = new Article("", "", "", "", "");
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final Article article = new Article(" ", " ", " ", " ", " ");
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final Article article = new Article("   ", "   ", "   ", "   ", "   ");
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Article article = new Article(MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.TEXT, MockConstants.KEYWORDS, MockConstants.NUMBER);
        assertNotNull(article.getTitle());
        assertNotNull(article.getDescription());
        assertNotNull(article.getText());
        assertNotNull(article.getNumber());
        assertNotNull(article.getDate());
        assertEquals(article.getTitle(), MockConstants.TITLE);
        assertEquals(article.getDescription(), MockConstants.DESCRIPTION);
        assertEquals(article.getText(), MockConstants.TEXT);
        assertEquals(article.getKeywords(), MockConstants.KEYWORDS);
        assertEquals(article.getNumber(), MockConstants.NUMBER);
    }

    @Test
    @Override
    public void toStringTest() {
        final Article article = MockEntity.getArticle();
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
                ", logo=" + article.getLogo() +
                '}' +
                ", number='" + article.getNumber() + '\'' +
                ", text='" + article.getText() + '\'' +
                ", date=" + article.getDate() +
                ", price=" + article.getPrice() +
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
                article1.getNumber().equals(article2.getNumber())
        );
        article1.setTitle(MockConstants.TITLE);
        article2.setTitle(MockConstants.TITLE);
        assertEquals(
                article1.equals(article2),
                article1.getNumber().equals(article2.getNumber())
        );
        article1.setText(MockConstants.TEXT);
        article2.setText(MockConstants.TEXT);
        assertEquals(
                article1.equals(article2),
                article1.getNumber().equals(article2.getNumber())
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final Article article1 = MockEntity.getArticle();
        final Article article2 = article1.clone();
        assertEquals(article1, article2);
        final boolean value = article1.getTitle().equalsIgnoreCase(article2.getTitle()) &&
                article1.getUrl().equalsIgnoreCase(article2.getUrl());
        assertEquals(article1.equals(article2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final Article article = new Article();
        int value = article.getNumber().hashCode();
        assertEquals(article.hashCode(), value);
        article.setTitle(MockConstants.TITLE);
        value += article.getTitle().hashCode() + article.getUrl().hashCode();
        assertEquals(article.hashCode(), value);
        article.setText(MockConstants.TEXT);
        value += article.getText().hashCode();
        assertEquals(article.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final Article article = MockEntity.getArticle();
        final int value = article.getTitle().hashCode() +
                article.getUrl().hashCode() +
                article.getDescription().hashCode() +
                article.getNumber().hashCode() +
                article.getText().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(article.hashCode(), value);
        }
    }

    @Test
    public void whenSetNullNumberThenGetNotNull() {
        final Article article = MockEntity.getArticle();
        article.setNumber(null);
        assertNotNull(article.getNumber());
    }

    @Test
    public void whenSetBlankNumberThenGetNotNull() {
        final Article article = MockEntity.getArticle();
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
        assertEquals(article.getNumber(), MockConstants.NUMBER);
        article.newNumber();
        assertNotNull(article.getNumber());
        assertNotEquals(article.getNumber(), MockConstants.NUMBER);
    }

    @Test
    public void whenSetNullTextThenGetEmptyString() {
        final Article article = MockEntity.getArticle();
        article.setText(null);
        assertNotNull(article.getText());
        assertTrue(article.getText().isEmpty());
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final Article article = MockEntity.getArticle();
        article.setText("");
        assertNotNull(article.getText());
        article.setText(" ");
        assertNotNull(article.getText());
        article.setText("        ");
        assertNotNull(article.getText());
        assertTrue(article.getText().isEmpty());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final Article article = MockEntity.getArticle();
        article.setText(MockConstants.TEXT);
        assertNotNull(article.getText());
        assertEquals(article.getText(), MockConstants.TEXT);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDate() {
        final Article article = MockEntity.getArticle();
        article.setDate(null);
        assertNotNull(article.getDate());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final Article article = MockEntity.getArticle();
        final Date date = new Date();
        article.setDate(date);
        assertNotNull(article.getDate());
        assertEquals(article.getDate(), date);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDateToString() {
        final Article article = MockEntity.getArticle();
        article.setDate(null);
        assertNotNull(article.getDateToString());
    }

    @Test
    public void whenSetValidDateThenGetThisDateToString() {
        final Article article = MockEntity.getArticle();
        article.setDate(new Date());
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
        assertEquals(article.getCategory(), category);
        assertFalse(category.containsArticle(article));
        article.setCategory(null);
        assertNull(article.getCategory());
        assertFalse(category.containsArticle(article));
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final Article model1 = getInstance();
        final Article model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Article model1 = getInstance();
        final Article model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected Article getObject() {
        return MockEntity.getArticle();
    }

    @Override
    protected Article getInstance() {
        return new Article();
    }
}
