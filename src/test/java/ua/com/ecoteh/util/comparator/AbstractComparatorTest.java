package ua.com.ecoteh.util.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.article.ArticleBuilder;
import ua.com.ecoteh.entity.model.Model;

import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class AbstractComparatorTest {

    @Test
    public void whenCompareWithNullModelsThenReturnZero() {
        final Model first = null;
        final Model second = null;
        final int result = AbstractComparator.compare(first, second);
        assertTrue(result == 0);
    }

    @Test
    public void whenCompareWithNullFirstModelThenReturnNegativeOne() {
        final Model first = null;
        final ArticleBuilder builder = Article.getBuilder();
        final Article second = builder.build();
        final int result = AbstractComparator.compare(first, second);
        assertTrue(result == -1);
    }

    @Test
    public void whenCompareWithNullSecondModelThenReturnOne() {
        final ArticleBuilder builder = Article.getBuilder();
        final Article first = builder.build();
        final Model second = null;
        final int result = AbstractComparator.compare(first, second);
        assertTrue(result == 1);
    }

    @Test
    public void whenCompareThenReturnTwo() {
        final ArticleBuilder builder = Article.getBuilder();
        final Article first = builder.build();
        final Article second = builder.build();
        final int result = AbstractComparator.compare(first, second);
        assertTrue(result == 2);
    }
}