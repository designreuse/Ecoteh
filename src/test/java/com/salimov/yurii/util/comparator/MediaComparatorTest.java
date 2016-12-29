package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Media;
import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;

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

        final Photo photo1 = MockEntity.getPhoto();
        final Photo photo2 = MockEntity.getPhoto();
        int value = comparator.compare(photo1, photo2);
        assertEquals(value, 0);

        final String title1 = MockConstants.TITLE + " 1";
        final String title2 = MockConstants.TITLE + " 2";
        photo1.setTitle(title1);
        photo2.setTitle(title2);
        value = comparator.compare(photo1, photo2);
        assertEquals(value, -1);

        photo1.setTitle(title2);
        photo2.setTitle(title1);
        value = comparator.compare(photo1, photo2);
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

        final Photo photo1 = MockEntity.getPhoto();
        final Photo photo2 = MockEntity.getPhoto();
        int value = comparator.compare(photo1, photo2);
        assertEquals(value, 0);

        final String url1 = MockConstants.URL + " 1";
        final String url2 = MockConstants.URL + " 2";
        photo1.setUrl(url1);
        photo2.setUrl(url2);
        value = comparator.compare(photo1, photo2);
        assertEquals(value, -1);

        photo1.setUrl(url2);
        photo2.setUrl(url1);
        value = comparator.compare(photo1, photo2);
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

        final Photo photo = MockEntity.getPhoto();
        value = comparator.compare(photo, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, photo);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
