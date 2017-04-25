package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.File;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class FileComparatorTest {

    @Test
    public void mediaComparator() {
        FileComparator comparator = new FileComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getMediaComparatorByTitle() {
        final Comparator<File> comparator = new FileComparator.ByTitle<>();
        assertNotNull(comparator);

        final File file1 = MockEntity.getFile();
        final File file2 = MockEntity.getFile();
        int value = comparator.compare(file1, file2);
        assertEquals(value, 0);

        final String title1 = MockConstants.TITLE + " 1";
        final String title2 = MockConstants.TITLE + " 2";
        file1.setTitle(title1);
        file2.setTitle(title2);
        value = comparator.compare(file1, file2);
        assertEquals(value, -1);

        file1.setTitle(title2);
        file2.setTitle(title1);
        value = comparator.compare(file1, file2);
        assertEquals(value, 1);
    }

    @Test
    public void getMediaComparatorByTitleWithInvalidMedias() {
        getMediaComparatorWithInvalidMedias(
                new FileComparator.ByTitle<>()
        );
    }

    @Test
    public void getMediaComparatorByUrl() {
        final Comparator<File> comparator = new FileComparator.ByUrl<>();
        assertNotNull(comparator);

        final File file1 = MockEntity.getFile();
        final File file2 = MockEntity.getFile();
        int value = comparator.compare(file1, file2);
        assertEquals(value, 0);

        final String url1 = MockConstants.URL + " 1";
        final String url2 = MockConstants.URL + " 2";
        file1.setUrl(url1);
        file2.setUrl(url2);
        value = comparator.compare(file1, file2);
        assertEquals(value, -1);

        file1.setUrl(url2);
        file2.setUrl(url1);
        value = comparator.compare(file1, file2);
        assertEquals(value, 1);
    }

    @Test
    public void getMediaComparatorByUrlWithInvalidMedias() {
        getMediaComparatorWithInvalidMedias(
                new FileComparator.ByUrl<>()
        );
    }

    @Ignore
    private static void getMediaComparatorWithInvalidMedias(
            final Comparator<File> comparator
    ) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final File file = MockEntity.getFile();
        value = comparator.compare(file, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, file);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
