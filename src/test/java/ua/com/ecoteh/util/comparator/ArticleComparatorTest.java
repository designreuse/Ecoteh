package ua.com.ecoteh.util.comparator;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.Article;

import java.util.Comparator;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.NUMBER;
import static ua.com.ecoteh.mocks.enity.MockEntity.getArticle;

public final class ArticleComparatorTest {

    @Test
    public void articleComparator() {
        ArticleComparator comparator = new ArticleComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getArticleComparatorByNumber() {
        Comparator<Article> comparator = new ArticleComparator.ByNumber();
        assertNotNull(comparator);

        final Article article1 = getArticle();
        final Article article2 = getArticle();
        int value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String num1 = NUMBER + "1";
        final String num2 = NUMBER + "2";

        article1.setNumber(num1);
        article2.setNumber(num2);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, -1);

        article1.setNumber(num2);
        article2.setNumber(num1);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getArticleComparatorByNumberWithInvalidArticles() {
        Comparator<Article> comparator = new ArticleComparator.ByNumber();
        getArticleComparatorWithInvalidArticles(comparator);
    }

    @Test
    public void getArticleComparatorByDate() throws InterruptedException {
        Comparator<Article> comparator = new ArticleComparator.ByDate();
        assertNotNull(comparator);

        final Date date1 = new Date();
        Thread.sleep(100);
        final Date date2 = new Date();

        final Article article1 = getArticle();
        article1.setDate(date1);
        final Article article2 = getArticle();
        article2.setDate(date1);
        int value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 0);

        article1.setDate(date1);
        article2.setDate(date2);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, -1);

        article1.setDate(date2);
        article2.setDate(date1);
        value = comparator.compare(article1, article2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getArticleComparatorByDateWithInvalidArticles() {
        Comparator<Article> comparator = new ArticleComparator.ByDate();
        getArticleComparatorWithInvalidArticles(comparator);
    }

    @Ignore
    private static void getArticleComparatorWithInvalidArticles(Comparator<Article> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final Article article = getArticle();
        value = comparator.compare(article, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, article);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
