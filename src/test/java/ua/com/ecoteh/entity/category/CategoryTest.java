package ua.com.ecoteh.entity.category;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.content.ContentTest;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticles;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CategoryTest extends ContentTest {

    private static Category category;

    @BeforeClass
    public static void beforeClass() {
        category = new Category(
                ID, VALIDATION, TITLE, URL, TEXT, DESCRIPTION, KEYWORDS,
                getFile(), getArticles()
        );
    }

    @Test
    public void toStringTest() throws Exception {
        final String testString = "Category{Content{Model{" +
                "id=" + category.getId() +
                ", validated=" + category.isValidated() + '}' +
                ", title='" + category.getTitle() + '\'' +
                ", url='" + category.getUrl() + '\'' +
                ", text='" + category.getText() + '\'' +
                ", description='" + category.getDescription() + '\'' +
                ", keywords='" + category.getKeywords() + '\'' +
                ", logo=" + category.getLogo() +
                "}}";
        assertEquals(category.toString(), testString);
    }

    @Test
    public void getArticlesThenReturnNotEmptyCollection() throws Exception {
        final Collection<Article> articles = category.getArticles();
        assertFalse(articles.isEmpty());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final CategoryEntity entity = category.convert();
        assertTrue(entity.getId() >= 0);
        assertEquals(entity.isValidated(), category.isValidated());
        assertEquals(entity.getTitle(), category.getTitle());
        assertEquals(entity.getUrl(), category.getUrl());
        assertEquals(entity.getText(), category.getText());
        assertEquals(entity.getDescription(), category.getDescription());
        assertEquals(entity.getKeywords(), category.getKeywords());
        assertNotNull(entity.getLogoEntity());
        assertNotNull(entity.getArticleEntities());
    }

    @Test
    public void getBuilder() throws Exception {
        final CategoryBuilder builder = Category.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Category getInstance() {
        return category;
    }
}