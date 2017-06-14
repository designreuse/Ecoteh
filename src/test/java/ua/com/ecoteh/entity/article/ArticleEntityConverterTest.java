package ua.com.ecoteh.entity.article;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentEntityConverterTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getArticleEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleEntityConverterTest extends ContentEntityConverterTest<ArticleEntity, Article> {

    private static ArticleEntityConverter converter;
    private static ArticleEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getArticleEntity();
        converter = new ArticleEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Article article) {
        super.checkEntity(article);
        assertEquals(article.getNumber(), entity.getNumber());
        assertEquals(article.getText(), entity.getText());
        assertEquals(article.getDate(), entity.getDate());
        assertTrue(article.getPrice() == entity.getPrice());
        assertEquals(article.getCurrency(), entity.getCurrency());
        assertNotNull(article.getCategory());
    }

    @Override
    protected ArticleEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected ArticleEntity getEntity() {
        return entity;
    }
}