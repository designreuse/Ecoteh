package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Date;

import static org.junit.Assert.*;

public final class ArticleEntityTest extends ContentTest<ArticleEntity> {

    @Test
    public void whenInitializeByConstructorThenSetNotNulDateAndNumber() {
        final ArticleEntity articleEntity = new ArticleEntity();
        assertNotNull(articleEntity.getDate());
        assertNotNull(articleEntity.getNumber());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final ArticleEntity articleEntity = new ArticleEntity(null, null, null, null, null);
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final ArticleEntity articleEntity = new ArticleEntity("", "", "", "", "");
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final ArticleEntity articleEntity = new ArticleEntity(" ", " ", " ", " ", " ");
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getDate());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final ArticleEntity articleEntity = new ArticleEntity("   ", "   ", "   ", "   ", "   ");
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getDate());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final ArticleEntity articleEntity = new ArticleEntity(MockConstants.TITLE, MockConstants.DESCRIPTION, MockConstants.TEXT, MockConstants.KEYWORDS, MockConstants.NUMBER);
        assertNotNull(articleEntity.getTitle());
        assertNotNull(articleEntity.getDescription());
        assertNotNull(articleEntity.getText());
        assertNotNull(articleEntity.getNumber());
        assertNotNull(articleEntity.getDate());
        assertEquals(articleEntity.getTitle(), MockConstants.TITLE);
        assertEquals(articleEntity.getDescription(), MockConstants.DESCRIPTION);
        assertEquals(articleEntity.getText(), MockConstants.TEXT);
        assertEquals(articleEntity.getKeywords(), MockConstants.KEYWORDS);
        assertEquals(articleEntity.getNumber(), MockConstants.NUMBER);
    }

    @Test
    @Override
    public void toStringTest() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        assertNotNull(articleEntity.toString());
        final String articleToString = "ArticleEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + articleEntity.getId() +
                ", validated=" + articleEntity.isValidated() +
                '}' +
                ", title='" + articleEntity.getTitle() + '\'' +
                ", url='" + articleEntity.getUrl() + '\'' +
                ", description='" + articleEntity.getDescription() + '\'' +
                ", keywords='" + articleEntity.getKeywords() + '\'' +
                ", logo=" + articleEntity.getLogoEntity() +
                '}' +
                ", number='" + articleEntity.getNumber() + '\'' +
                ", text='" + articleEntity.getText() + '\'' +
                ", date=" + articleEntity.getDate() +
                ", price=" + articleEntity.getPrice() +
                ", category=" + articleEntity.getCategoryEntity() +
                '}';
        assertEquals(articleEntity.toString(), articleToString);
    }

    @Test
    public void equalsInvalidObjects() {
        final ArticleEntity articleEntity1 = new ArticleEntity();
        final ArticleEntity articleEntity2 = new ArticleEntity();
        assertEquals(
                articleEntity1.equals(articleEntity2),
                articleEntity1.getNumber().equals(articleEntity2.getNumber())
        );
        articleEntity1.setTitle(MockConstants.TITLE);
        articleEntity2.setTitle(MockConstants.TITLE);
        assertEquals(
                articleEntity1.equals(articleEntity2),
                articleEntity1.getNumber().equals(articleEntity2.getNumber())
        );
        articleEntity1.setText(MockConstants.TEXT);
        articleEntity2.setText(MockConstants.TEXT);
        assertEquals(
                articleEntity1.equals(articleEntity2),
                articleEntity1.getNumber().equals(articleEntity2.getNumber())
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final ArticleEntity articleEntity1 = MockEntity.getArticleEntity();
        final ArticleEntity articleEntity2 = articleEntity1.clone();
        assertEquals(articleEntity1, articleEntity2);
        final boolean value = articleEntity1.getTitle().equalsIgnoreCase(articleEntity2.getTitle()) &&
                articleEntity1.getUrl().equalsIgnoreCase(articleEntity2.getUrl());
        assertEquals(articleEntity1.equals(articleEntity2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final ArticleEntity articleEntity = new ArticleEntity();
        int value = articleEntity.getNumber().hashCode();
        assertEquals(articleEntity.hashCode(), value);
        articleEntity.setTitle(MockConstants.TITLE);
        value += articleEntity.getTitle().hashCode() + articleEntity.getUrl().hashCode();
        assertEquals(articleEntity.hashCode(), value);
        articleEntity.setText(MockConstants.TEXT);
        value += articleEntity.getText().hashCode();
        assertEquals(articleEntity.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        final int value = articleEntity.getTitle().hashCode() +
                articleEntity.getUrl().hashCode() +
                articleEntity.getDescription().hashCode() +
                articleEntity.getNumber().hashCode() +
                articleEntity.getText().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(articleEntity.hashCode(), value);
        }
    }

    @Test
    public void whenSetNullNumberThenGetNotNull() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setNumber(null);
        assertNotNull(articleEntity.getNumber());
    }

    @Test
    public void whenSetBlankNumberThenGetNotNull() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setNumber("");
        assertNotNull(articleEntity.getNumber());
        articleEntity.setNumber(" ");
        assertNotNull(articleEntity.getNumber());
        articleEntity.setNumber("   ");
        assertNotNull(articleEntity.getNumber());
    }

    @Test
    public void whenSetValidNumberThenGetThisNumber() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setNumber(MockConstants.NUMBER);
        assertNotNull(articleEntity.getNumber());
        assertEquals(articleEntity.getNumber(), MockConstants.NUMBER);
        articleEntity.newNumber();
        assertNotNull(articleEntity.getNumber());
        assertNotEquals(articleEntity.getNumber(), MockConstants.NUMBER);
    }

    @Test
    public void whenSetNullTextThenGetEmptyString() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setText(null);
        assertNotNull(articleEntity.getText());
        assertTrue(articleEntity.getText().isEmpty());
    }

    @Test
    public void whenSetBlankTextThenGetEmptyString() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setText("");
        assertNotNull(articleEntity.getText());
        articleEntity.setText(" ");
        assertNotNull(articleEntity.getText());
        articleEntity.setText("        ");
        assertNotNull(articleEntity.getText());
        assertTrue(articleEntity.getText().isEmpty());
    }

    @Test
    public void whenSetValidTextThenGetThisText() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setText(MockConstants.TEXT);
        assertNotNull(articleEntity.getText());
        assertEquals(articleEntity.getText(), MockConstants.TEXT);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDate() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setDate(null);
        assertNotNull(articleEntity.getDate());
    }

    @Test
    public void whenSetValidDateThenGetThisDate() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        final Date date = new Date();
        articleEntity.setDate(date);
        assertNotNull(articleEntity.getDate());
        assertEquals(articleEntity.getDate(), date);
    }

    @Test
    public void whenSetInvalidDateThenGetNewDateToString() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setDate(null);
        assertNotNull(articleEntity.getDateToString());
    }

    @Test
    public void whenSetValidDateThenGetThisDateToString() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setDate(new Date());
        assertNotNull(articleEntity.getDateToString());
    }

    @Test
    public void whenSetNullCategoryThenGetNull() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        articleEntity.setCategoryEntity(null);
        assertNull(articleEntity.getCategoryEntity());
    }

    @Test
    public void whenSetNotNullCategoryThenGetThisCategory() {
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        final CategoryEntity categoryEntity = MockEntity.getCategoryEntity();
        categoryEntity.setArticleEntities(null);
        articleEntity.setCategoryEntity(null);
        articleEntity.setCategoryEntity(categoryEntity);
        assertNotNull(articleEntity.getCategoryEntity());
        assertEquals(articleEntity.getCategoryEntity(), categoryEntity);
        assertFalse(categoryEntity.containsArticle(articleEntity));
        articleEntity.setCategoryEntity(null);
        assertNull(articleEntity.getCategoryEntity());
        assertFalse(categoryEntity.containsArticle(articleEntity));
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final ArticleEntity model1 = getInstance();
        final ArticleEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final ArticleEntity model1 = getInstance();
        final ArticleEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected ArticleEntity getObject() {
        return MockEntity.getArticleEntity();
    }

    @Override
    protected ArticleEntity getInstance() {
        return new ArticleEntity();
    }
}
