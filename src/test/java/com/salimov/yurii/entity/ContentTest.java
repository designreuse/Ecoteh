package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.DESCRIPTION;
import static com.salimov.yurii.mocks.MockConstants.KEYWORDS;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public abstract class ContentTest<T extends Content<Long>> extends ModelTest<T> {

    @Test
    public void toStringTest() {
        final T content = getObject();
        assertTrue(isNotBlank(content.toString()));
        String value = content.getClass().getSimpleName() + " \"" + content.getTitle()
                + "\"\nKeywords: " + content.getKeywords()
                + "\nURL: " + content.getUrl()
                + "\nDescription: " + content.getDescription();
        assertEquals(content.toString(), value);
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final T content1 = getObject();
        final T content2 = (T) content1.clone();
        assertEquals(content1, content2);
        final boolean value = (
                isNotBlank(content1.getTitle()) ?
                        content1.getTitle()
                                .equalsIgnoreCase(
                                        content2.getTitle()
                                ) : isBlank(content2.getTitle())
        ) && (
                isNotBlank(content1.getUrl()) ?
                        content1.getUrl()
                                .equalsIgnoreCase(
                                        content2.getUrl()
                                ) : isBlank(content2.getUrl())
        );
        assertEquals(content1.equals(content2), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final T content = getObject();
        final int value = (
                isNotBlank(content.getTitle()) ? content.getTitle().hashCode() : 0
        ) + (
                isNotBlank(content.getUrl()) ? content.getUrl().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(content.hashCode(), value);
        }
    }

    @Test
    public void whenInitializeObjectWithNullParametersThenGetNull() {
        final T content = getObject();
        content.initialize(null, null, null);
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_1() {
        final T content = getObject();
        content.initialize("", "", "");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_2() {
        final T content = getObject();
        content.initialize(" ", " ", " ");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_3() {
        final T content = getObject();
        content.initialize("  ", "  ", "  ");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final T content = getObject();
        content.initialize(
                TITLE, DESCRIPTION, KEYWORDS
        );
        assertNotNull(content.getTitle());
        assertNotNull(content.getDescription());
        assertNotNull(content.getKeywords());
        assertEquals(content.getTitle(), TITLE);
        assertEquals(content.getDescription(), DESCRIPTION);
        assertEquals(content.getKeywords(), KEYWORDS);
    }

    @Test
    public void whenSetNullTitleThenGetNull() {
        final T content = getObject();
        content.setTitle(null);
        assertNull(content.getTitle());
        assertNull(content.getUrl());
        content.setTitle("");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
        content.setTitle(" ");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
        content.setTitle("    ");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
    }

    @Test
    public void whenSetBlankTitleThenGetNull() {
        final T content = getObject();
        content.setTitle("");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
        content.setTitle(" ");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
        content.setTitle("    ");
        assertNull(content.getTitle());
        assertNull(content.getUrl());
    }

    @Test
    public void whenSetValidTitleThenGetThisTitle() {
        final T content = getObject();
        content.setTitle(TITLE);
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        assertEquals(content.getTitle(), TITLE);
        assertEquals(
                content.getUrl(),
                Translator.fromCyrillicToLatin(TITLE)
        );
    }

    @Test
    public void whenSetNullUrlThenGetNull() {
        final T content = getObject();
        content.setUrl(null);
        assertNull(content.getUrl());
    }

    @Test
    public void whenSetBlankUrlThenGetNull() {
        final T content = getObject();
        content.setUrl("");
        assertNull(content.getUrl());
        content.setUrl(" ");
        assertNull(content.getUrl());
        content.setUrl("   ");
        assertNull(content.getUrl());
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final T content = getObject();
        content.setUrl(MockConstants.URL);
        assertNotNull(content.getUrl());
        assertEquals(
                content.getUrl(),
                Translator.fromCyrillicToLatin(MockConstants.URL)
        );
        content.setUrl(null);
        content.translateAndSetUrl(MockConstants.URL);
        assertNotNull(content.getUrl());
        assertEquals(
                content.getUrl(),
                Translator.fromCyrillicToLatin(MockConstants.URL)
        );
    }

    @Test
    public void whenSetNullDescriptionThenReturnNull() {
        final T content = getObject();
        content.setDescription(null);
        assertNull(content.getDescription());
    }

    @Test
    public void whenSetBlankDescriptionThenReturnNull() {
        final T content = getObject();
        content.setDescription("");
        assertNull(content.getDescription());
        content.setDescription(" ");
        assertNull(content.getDescription());
        content.setDescription("   ");
        assertNull(content.getDescription());
    }

    @Test
    public void whenSetValidDescriptionThenReturnThisDescription() {
        final T content = getObject();
        content.setDescription(DESCRIPTION);
        assertNotNull(content.getDescription());
        Assert.assertEquals(content.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenSetNullKeywordsThenReturnNull() {
        final T content = getObject();
        content.setKeywords(null);
        assertNull(content.getKeywords());
    }

    @Test
    public void whenSetBlankKeywordsThenReturnNull() {
        final T content = getObject();
        content.setKeywords("");
        assertNull(content.getKeywords());
        content.setKeywords(" ");
        assertNull(content.getKeywords());
        content.setKeywords("   ");
        assertNull(content.getKeywords());
    }

    @Test
    public void whenSetValidKeywordsThenReturnThisKeywords() {
        final T content = getObject();
        content.setKeywords(KEYWORDS);
        assertNotNull(content.getKeywords());
        Assert.assertEquals(content.getKeywords(), KEYWORDS);
    }
}
