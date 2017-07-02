package ua.com.ecoteh.service.data.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;

import java.util.Comparator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileComparatorTest {

    @Test
    public void whenGetByTitleComparatorThenReturnNotNull() {
        final Comparator<File> byTitleComparator = new FileComparator.ByTitle();
        assertNotNull(byTitleComparator);
    }

    @Test
    public void compareByTitleTest() {
        final Comparator<File> byTitleComparator = new FileComparator.ByTitle();
        comparatorTest(byTitleComparator);
    }

    @Test
    public void whenGetByUrlComparatorThenReturnNotNull() {
        final Comparator<File> byUrlComparator = new FileComparator.ByUrl();
        assertNotNull(byUrlComparator);
    }

    @Test
    public void compareByUrlTest() {
        final Comparator<File> byUrlComparator = new FileComparator.ByUrl();
        comparatorTest(byUrlComparator);
    }

    private void comparatorTest(final Comparator<File> comparator) {
        final FileBuilder builder = File.getBuilder();
        final File first = builder.build();
        final File second = builder.build();
        int compareValue;
        int temp = comparator.compare(first, second);
        for (int i = 0; i < 10; i++) {
            compareValue = comparator.compare(first, second);
            assertTrue(compareValue == temp);
            temp = compareValue;
        }
    }
}