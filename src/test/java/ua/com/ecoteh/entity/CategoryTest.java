package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;

public final class CategoryTest extends ContentTest<Category> {

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final Category category = new Category(null, null, null);
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final Category category = new Category("", "", "");
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final Category category = new Category(" ", " ", " ");
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final Category category = new Category("   ", "   ", "   ");
        assertNotNull(category.getTitle());
        assertNotNull(category.getDescription());
        assertNotNull(category.getKeywords());
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
        final Category category = MockEntity.getCategory();
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
                ", logo=" + category.getLogo() +
                '}' +
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
    public void hashCodeValidObject() {
        final Category category = new Category();
        int value = 0;
        assertEquals(category.hashCode(), value);
        category.setTitle(TITLE);
        value += category.getTitle().hashCode() + category.getUrl().hashCode();
        assertEquals(category.hashCode(), value);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNotNull() {
        final Category category = new Category();
        category.setLogo(null);
        assertNotNull(category.getLogo());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final Category category = new Category();
        category.setLogo(MockEntity.getFile());
        assertNotNull(category.getLogo());
        assertEquals(category.getLogo(), MockEntity.getFile());
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
        final Article article = MockEntity.getArticle();
        final List<Article> articles = MockEntity.getArticles();
        assertNotNull(category.getArticles());
        assertTrue(category.getArticles().isEmpty());
        checkArticlesSize(articles.size(), category, articles);
        category.removeArticle(article);
        assertNotNull(category.getArticles());
        assertFalse(category.getArticles().isEmpty());
        checkArticlesSize(articles.size(), category, articles);
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
    public void whenInitializeByNullThenDoNothing() {
        final Category model1 = getInstance();
        final Category model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final Category model1 = getInstance();
        final Category model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected Category getObject() {
        return MockEntity.getCategory();
    }

    @Override
    protected Category getInstance() {
        return new Category();
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
