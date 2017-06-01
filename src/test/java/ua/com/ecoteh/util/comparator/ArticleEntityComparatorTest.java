package ua.com.ecoteh.util.comparator;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.article.ArticleEntity;

import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.NUMBER;
import static ua.com.ecoteh.mocks.enity.MockEntity.getArticleEntity;

public final class ArticleEntityComparatorTest {

    @Test
    public void articleComparator() {
        ArticleComparator comparator = new ArticleComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getArticleComparatorByNumber() {
        Comparator<ArticleEntity> comparator = new ArticleComparator.ByNumber();
        assertNotNull(comparator);

        final ArticleEntity articleEntity1 = getArticleEntity();
        final ArticleEntity articleEntity2 = getArticleEntity();
        int value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String num1 = NUMBER + "1";
        final String num2 = NUMBER + "2";

        articleEntity1.setNumber(num1);
        articleEntity2.setNumber(num2);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        articleEntity1.setNumber(num2);
        articleEntity2.setNumber(num1);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getArticleComparatorByNumberWithInvalidArticles() {
        Comparator<ArticleEntity> comparator = new ArticleComparator.ByNumber();
        getArticleComparatorWithInvalidArticles(comparator);
    }

    @Test
    public void getArticleComparatorByDate() throws InterruptedException {
        Comparator<ArticleEntity> comparator = new ArticleComparator.ByDate();
        assertNotNull(comparator);

        final Date date1 = new Date();
        Thread.sleep(100);
        final Date date2 = new Date();

        final ArticleEntity articleEntity1 = getArticleEntity();
        articleEntity1.setDate(date1);
        final ArticleEntity articleEntity2 = getArticleEntity();
        articleEntity2.setDate(date1);
        int value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        articleEntity1.setDate(date1);
        articleEntity2.setDate(date2);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        articleEntity1.setDate(date2);
        articleEntity2.setDate(date1);
        value = comparator.compare(articleEntity1, articleEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getArticleComparatorByDateWithInvalidArticles() {
        Comparator<ArticleEntity> comparator = new ArticleComparator.ByDate();
        getArticleComparatorWithInvalidArticles(comparator);
    }

    @Ignore
    private static void getArticleComparatorWithInvalidArticles(Comparator<ArticleEntity> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final ArticleEntity articleEntity = getArticleEntity();
        value = comparator.compare(articleEntity, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, articleEntity);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
