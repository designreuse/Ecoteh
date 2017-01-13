package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class FileTest extends MediaTest<File> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        File file = new File(null, null);
        assertNull(file.getTitle());
        assertNull(file.getUrl());

        file = new File("", "");
        assertNull(file.getTitle());
        assertNull(file.getUrl());

        file = new File(" ", " ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());

        file = new File("   ", "   ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final File file = new File(
                MockConstants.TITLE,
                MockConstants.URL
        );
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        Assert.assertEquals(
                file.getTitle(),
                MockConstants.TITLE
        );
        Assert.assertEquals(
                file.getUrl(),
                MockConstants.URL
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final File file1 = new File();
        final File file2 = new File();
        assertTrue(
                file1.equals(file2)
        );

        file1.setTitle(MockConstants.TITLE);
        file2.setTitle(MockConstants.TITLE);
        assertTrue(
                file1.equals(file2)
        );

        file1.setUrl(MockConstants.URL);
        file2.setUrl(MockConstants.URL);
        assertTrue(
                file1.equals(file2)
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final File file = new File();
        int value = 0;
        assertEquals(
                file.hashCode(),
                value
        );

        file.setTitle(MockConstants.TITLE);
        value += (
                isNotBlank(file.getTitle()) ?
                        file.getTitle().hashCode() : 0
        ) + (
                isNotBlank(file.getUrl()) ?
                        file.getUrl().hashCode() : 0
        );
        assertEquals(
                file.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void whenSetValidTitleThenGetThisTitle() {
        final File file = MockEntity.getPhoto();
        file.setTitle(MockConstants.TITLE);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        Assert.assertEquals(
                file.getTitle(),
                MockConstants.TITLE
        );

        final String url = Translator.fromCyrillicToLatin(
                MockConstants.TITLE
                        .replace(".", "!")
        );
        assertEquals(
                file.getUrl(),
                isNotBlank(url) ?  url.replace("!", ".") : url
        );
    }

    @Test
    public void whenTranslateAndSetInvalidUrlThenGetNull() {
        final File file = MockEntity.getPhoto();
        file.setUrl(null);
        assertNull(file.getUrl());

        file.setUrl("");
        assertNull(file.getUrl());

        file.setUrl(" ");
        assertNull(file.getUrl());

        file.setUrl("   ");
        assertNull(file.getUrl());
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final File file = MockEntity.getPhoto();
        file.setUrl(MockConstants.URL);
        assertNotNull(file.getUrl());

        final String url = Translator.fromCyrillicToLatin(
                MockConstants.URL.replace(".", "!")
        );
        assertEquals(
                file.getUrl(),
                isNotBlank(url) ?  url.replace("!", ".") : url
        );
    }

    @Ignore
    @Override
    protected File getObject() {
        return MockEntity.getPhoto();
    }
}
