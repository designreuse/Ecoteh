package ua.com.ecoteh.entity.article;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentConverterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.enity.MockModels.getArticle;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleConverterTest extends ContentConverterTest<Article, ArticleEntity> {

    private static ArticleConverter converter;
    private static Article article;

    @BeforeClass
    public static void beforeClass() {
        article = getArticle();
        converter = new ArticleConverter(article);
    }

    @Override
    protected void checkEntity(final ArticleEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getNumber(), article.getNumber());
        assertEquals(entity.getText(), article.getText());
        assertEquals(entity.getDate(), article.getDate());
        assertTrue(entity.getPrice() == article.getPrice());
        assertEquals(entity.getCurrency(), article.getCurrency());
        assertNotNull(entity.getCategoryEntity());
    }

    @Override
    protected ArticleConverter getConverter() {
        return converter;
    }

    @Override
    protected Article getModel() {
        return article;
    }
}
