package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.List;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;

public final class CategoryEntityTest extends ContentTest<CategoryEntity> {

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final CategoryEntity categoryEntity = new CategoryEntity(null, null, null);
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final CategoryEntity categoryEntity = new CategoryEntity("", "", "");
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final CategoryEntity categoryEntity = new CategoryEntity(" ", " ", " ");
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final CategoryEntity categoryEntity = new CategoryEntity("   ", "   ", "   ");
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final CategoryEntity categoryEntity = new CategoryEntity(TITLE, DESCRIPTION, KEYWORDS);
        assertNotNull(categoryEntity.getTitle());
        assertNotNull(categoryEntity.getDescription());
        assertNotNull(categoryEntity.getKeywords());
        assertEquals(categoryEntity.getTitle(), TITLE);
        assertEquals(categoryEntity.getDescription(), DESCRIPTION);
        assertEquals(categoryEntity.getKeywords(), KEYWORDS);
    }

    @Test
    @Override
    public void toStringTest() {
        final CategoryEntity categoryEntity = MockEntity.getCategoryEntity();
        assertNotNull(categoryEntity.toString());
        final String categoryToString = "CategoryEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + categoryEntity.getId() +
                ", validated=" + categoryEntity.isValidated() +
                '}' +
                ", title='" + categoryEntity.getTitle() + '\'' +
                ", url='" + categoryEntity.getUrl() + '\'' +
                ", description='" + categoryEntity.getDescription() + '\'' +
                ", keywords='" + categoryEntity.getKeywords() + '\'' +
                ", logo=" + categoryEntity.getLogoEntity() +
                '}' +
                '}';
        assertEquals(categoryEntity.toString(), categoryToString);
    }

    @Test
    public void equalsObjects() {
        final CategoryEntity categoryEntity1 = new CategoryEntity();
        final CategoryEntity categoryEntity2 = new CategoryEntity();
        assertTrue(categoryEntity1.equals(categoryEntity2));
        categoryEntity1.setTitle(TITLE);
        categoryEntity2.setTitle(TITLE);
        assertTrue(categoryEntity1.equals(categoryEntity2));
        categoryEntity1.setUrl(URL);
        categoryEntity2.setUrl(URL);
        assertTrue(categoryEntity1.equals(categoryEntity2));
    }

    @Test
    public void hashCodeValidObject() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        int value = 0;
        assertEquals(categoryEntity.hashCode(), value);
        categoryEntity.setTitle(TITLE);
        value += categoryEntity.getTitle().hashCode() + categoryEntity.getUrl().hashCode();
        assertEquals(categoryEntity.hashCode(), value);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNotNull() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setLogoEntity(null);
        assertNotNull(categoryEntity.getLogoEntity());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setLogoEntity(MockEntity.getFileEntity());
        assertNotNull(categoryEntity.getLogoEntity());
        assertEquals(categoryEntity.getLogoEntity(), MockEntity.getFileEntity());
    }

    @Test
    public void whenArticlesAreInvalidThenNotAddThey() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setArticleEntities(null);
        assertNotNull(categoryEntity.getArticleEntities());
        assertTrue(categoryEntity.getArticleEntities().isEmpty());
    }

    @Test
    public void whenArticlesAreValidThenAddThey() {
        final CategoryEntity categoryEntity = new CategoryEntity();
        final ArticleEntity articleEntity = MockEntity.getArticleEntity();
        final List<ArticleEntity> articleEntities = MockEntity.getArticleEntities();
        assertNotNull(categoryEntity.getArticleEntities());
        assertTrue(categoryEntity.getArticleEntities().isEmpty());
        checkArticlesSize(articleEntities.size(), categoryEntity, articleEntities);
        categoryEntity.removeArticle(articleEntity);
        assertNotNull(categoryEntity.getArticleEntities());
        assertFalse(categoryEntity.getArticleEntities().isEmpty());
        checkArticlesSize(articleEntities.size(), categoryEntity, articleEntities);
        categoryEntity.removeArticles(articleEntities);
        assertNotNull(categoryEntity.getArticleEntities());
        assertTrue(categoryEntity.getArticleEntities().isEmpty());
        categoryEntity.addArticle(articleEntity);
        categoryEntity.addArticles(articleEntities);
        assertTrue(categoryEntity.containsArticle(articleEntity));
        assertTrue(categoryEntity.containsArticles(articleEntities));
        categoryEntity.addArticle(articleEntity);
        categoryEntity.addArticles(articleEntities);
        categoryEntity.clearArticles();
        assertNotNull(categoryEntity.getArticleEntities());
        assertTrue(categoryEntity.getArticleEntities().isEmpty());
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final CategoryEntity model1 = getInstance();
        final CategoryEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final CategoryEntity model1 = getInstance();
        final CategoryEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected CategoryEntity getObject() {
        return MockEntity.getCategoryEntity();
    }

    @Override
    protected CategoryEntity getInstance() {
        return new CategoryEntity();
    }

    @Ignore
    private void checkArticlesSize(
            final int size,
            final CategoryEntity categoryEntity,
            final List<ArticleEntity> articleEntities
    ) {
        for (int i = 0; i < 10; i++) {
            categoryEntity.addArticles(articleEntities);
            assertFalse(categoryEntity.getArticleEntities().isEmpty());
            assertEquals(categoryEntity.getArticleEntities().size(), size);
        }
    }
}
