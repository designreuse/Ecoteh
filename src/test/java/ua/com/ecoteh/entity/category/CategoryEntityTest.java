package ua.com.ecoteh.entity.category;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.content.ContentEntityTest;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryEntityTest extends ContentEntityTest {

    private CategoryEntity category;

    @Before
    public void beforeTest() {
        this.category = new CategoryEntity();
        this.category.setId(ID);
        this.category.setValidated(VALIDATION);
        this.category.setTitle(TITLE);
        this.category.setUrl(URL);
        this.category.setDescription(DESCRIPTION);
        this.category.setKeywords(KEYWORDS);
        this.category.setLogoEntity(getFileEntity());
        this.category.setArticleEntities(getArticleEntities());
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "CategoryEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + this.category.getId() +
                ", validated=" + this.category.isValidated() +
                '}' +
                ", title='" + this.category.getTitle() + '\'' +
                ", url='" + this.category.getUrl() + '\'' +
                ", description='" + this.category.getDescription() + '\'' +
                ", keywords='" + this.category.getKeywords() + '\'' +
                ", logo=" + this.category.getLogoEntity() +
                '}' +
                '}';
        assertEquals(this.category.toString(), toStringTest);
    }

    @Test
    public void whenSetArticlesThenGetIt() {
        final Collection<ArticleEntity> articles = getArticleEntities();
        this.category.setArticleEntities(articles);
        assertNotNull(this.category.getArticleEntities());
        assertEquals(this.category.getArticleEntities(), articles);
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Category category = this.category.convert();
        assertEquals(category.getId(), this.category.getId());
        assertEquals(category.isValidated(), this.category.isValidated());
        assertEquals(category.getTitle(), this.category.getTitle());
        assertEquals(category.getUrl(), this.category.getUrl());
        assertEquals(category.getDescription(), this.category.getDescription());
        assertEquals(category.getKeywords(), this.category.getKeywords());
        assertNotNull(category.getLogo());
        assertNotNull(category.getArticles());
    }

    @Override
    protected CategoryEntity getInstance() {
        return this.category;
    }
}