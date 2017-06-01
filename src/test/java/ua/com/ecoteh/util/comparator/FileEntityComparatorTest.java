package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.file.FileEntity;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class FileEntityComparatorTest {

    @Test
    public void mediaComparator() {
        FileComparator comparator = new FileComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getMediaComparatorByTitle() {
        final Comparator<FileEntity> comparator = new FileComparator.ByTitle();
        assertNotNull(comparator);

        final FileEntity fileEntity1 = MockEntity.getFileEntity();
        final FileEntity fileEntity2 = MockEntity.getFileEntity();
        int value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, 0);

        final String title1 = MockConstants.TITLE + " 1";
        final String title2 = MockConstants.TITLE + " 2";
        fileEntity1.setTitle(title1);
        fileEntity2.setTitle(title2);
        value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, -1);

        fileEntity1.setTitle(title2);
        fileEntity2.setTitle(title1);
        value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, 1);
    }

    @Test
    public void getMediaComparatorByTitleWithInvalidMedias() {
        getMediaComparatorWithInvalidMedias(new FileComparator.ByTitle());
    }

    @Test
    public void getMediaComparatorByUrl() {
        final Comparator<FileEntity> comparator = new FileComparator.ByUrl();
        assertNotNull(comparator);

        final FileEntity fileEntity1 = MockEntity.getFileEntity();
        final FileEntity fileEntity2 = MockEntity.getFileEntity();
        int value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, 0);

        final String url1 = MockConstants.URL + " 1";
        final String url2 = MockConstants.URL + " 2";
        fileEntity1.setUrl(url1);
        fileEntity2.setUrl(url2);
        value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, -1);

        fileEntity1.setUrl(url2);
        fileEntity2.setUrl(url1);
        value = comparator.compare(fileEntity1, fileEntity2);
        assertEquals(value, 1);
    }

    @Test
    public void getMediaComparatorByUrlWithInvalidMedias() {
        getMediaComparatorWithInvalidMedias(new FileComparator.ByUrl());
    }

    @Ignore
    private static void getMediaComparatorWithInvalidMedias(final Comparator<FileEntity> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final FileEntity fileEntity = MockEntity.getFileEntity();
        value = comparator.compare(fileEntity, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, fileEntity);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
