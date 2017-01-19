package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.entity.Media;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;

import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.enity.MockEntity.getFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MediaComparatorTest {

    @Test
    public void mediaComparator() {
        MediaComparator comparator = new MediaComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getMediaComparatorByTitle() {
        Comparator<Media> comparator = new MediaComparator.ByTitle<>();
        assertNotNull(comparator);

        final File file1 = getFile();
        final File file2 = getFile();
        int value = comparator.compare(file1, file2);
        assertEquals(value, 0);

        final String title1 = TITLE + " 1";
        final String title2 = TITLE + " 2";
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
        Comparator<Media> comparator = new MediaComparator.ByTitle<>();
        getMediaComparatorWithInvalidMedias(comparator);
    }

    @Test
    public void getMediaComparatorByUrl() {
        Comparator<Media> comparator = new MediaComparator.ByUrl<>();
        assertNotNull(comparator);

        final File file1 = getFile();
        final File file2 = getFile();
        int value = comparator.compare(file1, file2);
        assertEquals(value, 0);

        final String url1 = URL + " 1";
        final String url2 = URL + " 2";
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
        Comparator<Media> comparator = new MediaComparator.ByUrl<>();
        getMediaComparatorWithInvalidMedias(comparator);
    }

    @Ignore
    private static void getMediaComparatorWithInvalidMedias(Comparator<Media> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final File file = getFile();
        value = comparator.compare(file, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, file);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
