package com.salimov.yurii.entity;

import com.salimov.yurii.util.translator.Translator;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public abstract class MediaTest<T extends Media<Long>> extends ModelTest<T> {

    @Test
    @Override
    public void validObject() {
        final T media = getObject();
        assertEquals(
                media.isValidated(media),
                media != null && isNotBlank(media.getUrl())
        );
    }

    @Test
    public void toStringTest() {
        final T media = getObject();
        assertTrue(
                isNotBlank(
                        media.toString()
                )
        );
        String value = media.getClass().getSimpleName() + " " + media.getTitle()
                + " \nURL: " + media.getUrl();
        assertEquals(
                media.toString(),
                value
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final T media1 = getObject();
        final T media2 = (T) media1.clone();
        assertEquals(media1, media2);
        final boolean value = (isNotBlank(media1.getTitle()) ?
                media1.getTitle()
                        .equalsIgnoreCase(
                                media2.getTitle()
                        ) :
                isBlank(media2.getTitle())
        ) && (
                isNotBlank(media1.getUrl()) ?
                        media1.getUrl()
                                .equalsIgnoreCase(
                                        media2.getUrl()
                                ) :
                        isBlank(media2.getUrl())
        );
        assertEquals(
                media1.equals(media2),
                value
        );
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final T media = getObject();
        final int value = (
                isNotBlank(media.getTitle()) ? media.getTitle().hashCode() : 0
        ) + (
                isNotBlank(media.getUrl()) ? media.getUrl().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(media.hashCode(), value);
        }
    }

    @Test
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        final T media = getObject();
        media.initialize(null, null);
        assertNull(media.getTitle());
        assertNull(media.getUrl());
        media.initialize("", "");
        assertNull(media.getTitle());
        assertNull(media.getUrl());
        media.initialize(" ", " ");
        assertNull(media.getTitle());
        assertNull(media.getUrl());
        media.initialize("   ", "   ");
        assertNull(media.getTitle());
        assertNull(media.getUrl());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final T media = getObject();
        media.initialize(TITLE, URL);
        assertNotNull(media.getTitle());
        assertNotNull(media.getUrl());
        assertEquals(media.getTitle(), TITLE);
        assertEquals(media.getUrl(), URL);
    }

    @Test
    public void whenSetInvalidTitleThenGetNull() {
        final T media = getObject();
        media.setTitle(null);
        assertNull(media.getTitle());
        media.setTitle("");
        assertNull(media.getTitle());
        media.setTitle(" ");
        assertNull(media.getTitle());
        media.setTitle("    ");
        assertNull(media.getTitle());
    }

    @Test
    public void whenSetValidTitleThenGetThisTitle() {
        final T media = getObject();
        media.setTitle(TITLE);
        assertNotNull(media.getTitle());
        assertNotNull(media.getUrl());
        assertEquals(media.getTitle(), TITLE);
        assertEquals(
                media.getUrl(),
                Translator.fromCyrillicToLatin(TITLE)
        );
    }

    @Test
    public void whenSetInvalidUrlThenGetNull() {
        final T media = getObject();
        media.setUrl(null);
        assertNull(media.getUrl());
        media.setUrl("");
        assertNull(media.getUrl());
        media.setUrl(" ");
        assertNull(media.getUrl());
        media.setUrl("   ");
        assertNull(media.getUrl());
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final T media = getObject();
        media.setUrl(URL);
        assertNotNull(media.getUrl());
        final String url = Translator.fromCyrillicToLatin(URL);
        assertEquals(
                media.getUrl(), url
        );
        media.setUrl(null);
        media.translateAndSetUrl(URL);
        assertNotNull(media.getUrl());
        assertEquals(
                media.getUrl(),
                url
        );
    }
}
