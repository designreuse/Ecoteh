package ua.com.ecoteh.service.data.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.category.Category;
import ua.com.ecoteh.entity.category.CategoryBuilder;
import ua.com.ecoteh.entity.content.Content;

import java.util.Comparator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContentComparatorTest {

    @Test
    public void whenGetByTitleComparatorThenReturnNotNull() {
        final Comparator<Content> byTitleComparator = new ContentComparator.ByTitle<>();
        assertNotNull(byTitleComparator);
    }

    @Test
    public void compareByTitleTest() {
        final Comparator<Content> byTitleComparator = new ContentComparator.ByTitle<>();
        comparatorTest(byTitleComparator);
    }

    @Test
    public void whenGetByUrlComparatorThenReturnNotNull() {
        final Comparator<Content> byUrlComparator = new ContentComparator.ByUrl<>();
        assertNotNull(byUrlComparator);
    }

    @Test
    public void compareByUrlTest() {
        final Comparator<Content> byUrlComparator = new ContentComparator.ByUrl<>();
        comparatorTest(byUrlComparator);
    }

    private void comparatorTest(final Comparator<Content> comparator) {
        final CategoryBuilder builder = Category.getBuilder();
        final Category first = builder.build();
        final Category second = builder.build();
        int compareValue;
        int temp = comparator.compare(first, second);
        for (int i = 0; i < 10; i++) {
            compareValue = comparator.compare(first, second);
            assertTrue(compareValue == temp);
            temp = compareValue;
        }
    }
}
