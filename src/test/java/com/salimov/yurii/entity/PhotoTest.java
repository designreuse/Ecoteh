package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class PhotoTest extends MediaTest<Photo> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Photo photo = new Photo(null, null);
        assertNull(photo.getTitle());
        assertNull(photo.getUrl());

        photo = new Photo("", "");
        assertNull(photo.getTitle());
        assertNull(photo.getUrl());

        photo = new Photo(" ", " ");
        assertNull(photo.getTitle());
        assertNull(photo.getUrl());

        photo = new Photo("   ", "   ");
        assertNull(photo.getTitle());
        assertNull(photo.getUrl());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Photo photo = new Photo(
                MockConstants.TITLE,
                MockConstants.URL
        );
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getUrl());
        Assert.assertEquals(
                photo.getTitle(),
                MockConstants.TITLE
        );
        Assert.assertEquals(
                photo.getUrl(),
                MockConstants.URL
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final Photo photo1 = new Photo();
        final Photo photo2 = new Photo();
        assertTrue(
                photo1.equals(photo2)
        );

        photo1.setTitle(MockConstants.TITLE);
        photo2.setTitle(MockConstants.TITLE);
        assertTrue(
                photo1.equals(photo2)
        );

        photo1.setUrl(MockConstants.URL);
        photo2.setUrl(MockConstants.URL);
        assertTrue(
                photo1.equals(photo2)
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Photo photo = new Photo();
        int value = 0;
        assertEquals(
                photo.hashCode(),
                value
        );

        photo.setTitle(MockConstants.TITLE);
        value += (
                isNotBlank(photo.getTitle()) ?
                        photo.getTitle().hashCode() : 0
        ) + (
                isNotBlank(photo.getUrl()) ?
                        photo.getUrl().hashCode() : 0
        );
        assertEquals(
                photo.hashCode(),
                value
        );
    }

    @Test
    @Override
    public void whenSetValidTitleThenGetThisTitle() {
        final Photo photo = MockEntity.getPhoto();
        photo.setTitle(MockConstants.TITLE);
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getUrl());
        Assert.assertEquals(
                photo.getTitle(),
                MockConstants.TITLE
        );

        final String url = Translator.fromCyrillicToLatin(
                MockConstants.TITLE
                        .replace(".", "!")
        );
        assertEquals(
                photo.getUrl(),
                isNotBlank(url) ?  url.replace("!", ".") : url
        );
    }

    @Test
    public void whenTranslateAndSetInvalidUrlThenGetNull() {
        final Photo photo = MockEntity.getPhoto();
        photo.setUrl(null);
        assertNull(photo.getUrl());

        photo.setUrl("");
        assertNull(photo.getUrl());

        photo.setUrl(" ");
        assertNull(photo.getUrl());

        photo.setUrl("   ");
        assertNull(photo.getUrl());
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final Photo photo = MockEntity.getPhoto();
        photo.setUrl(MockConstants.URL);
        assertNotNull(photo.getUrl());

        final String url = Translator.fromCyrillicToLatin(
                MockConstants.URL.replace(".", "!")
        );
        assertEquals(
                photo.getUrl(),
                isNotBlank(url) ?  url.replace("!", ".") : url
        );
    }

    @Ignore
    @Override
    protected Photo getObject() {
        return MockEntity.getPhoto();
    }
}
