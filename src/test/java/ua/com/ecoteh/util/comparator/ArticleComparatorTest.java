package ua.com.ecoteh.util.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleBuilder;

import java.util.Comparator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleComparatorTest {

    @Test
    public void whenGetByNumberComparatorThenReturnNotNull() {
        final Comparator<Article> byNumberComparator = new ArticleComparator.ByNumber();
        assertNotNull(byNumberComparator);
    }

    @Test
    public void compareByNumberTest() {
        final Comparator<Article> byNumberComparator = new ArticleComparator.ByNumber();
        comparatorTest(byNumberComparator);
    }

    @Test
    public void whenGetByDateComparatorThenReturnNotNull() {
        final Comparator<Article> byDateComparator = new ArticleComparator.ByDate();
        assertNotNull(byDateComparator);
    }

    @Test
    public void compareByDateTest() {
        final Comparator<Article> byDateComparator = new ArticleComparator.ByDate();
        comparatorTest(byDateComparator);
    }

    @Test
    public void whenGetByPriceComparatorThenReturnNotNull() {
        final Comparator<Article> byPriceComparator = new ArticleComparator.ByPrice();
        assertNotNull(byPriceComparator);
    }

    @Test
    public void compareByPriceTest() {
        final Comparator<Article> byPriceComparator = new ArticleComparator.ByPrice();
        comparatorTest(byPriceComparator);
    }

    private void comparatorTest(final Comparator<Article> comparator) {
        final ArticleBuilder builder = Article.getBuilder();
        final Article first = builder.build();
        final Article second = builder.build();
        int compareValue;
        int temp = comparator.compare(first, second);
        for (int i = 0; i < 10; i++) {
            compareValue = comparator.compare(first, second);
            assertTrue(compareValue == temp);
            temp = compareValue;
        }
    }
}
