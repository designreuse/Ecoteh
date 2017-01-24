package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Test;

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
        assertEquals(
                content.toString(),
                value
        );
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
        assertEquals(
                content1.equals(content2),
                value
        );
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
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        final T content = getObject();
        content.initialize(null, null, null);
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
        content.initialize("", "", "");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
        content.initialize(" ", " ", " ");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
        content.initialize("   ", "   ", "   ");
        assertNull(content.getTitle());
        assertNull(content.getDescription());
        assertNull(content.getKeywords());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final T content = getObject();
        content.initialize(
                MockConstants.TITLE,
                MockConstants.DESCRIPTION,
                MockConstants.KEYWORDS
        );
        assertNotNull(content.getTitle());
        assertNotNull(content.getDescription());
        assertNotNull(content.getKeywords());
        Assert.assertEquals(
                content.getTitle(),
                MockConstants.TITLE
        );
        Assert.assertEquals(
                content.getDescription(),
                MockConstants.DESCRIPTION
        );
        Assert.assertEquals(
                content.getKeywords(),
                MockConstants.KEYWORDS
        );
    }

    @Test
    public void whenSetInvalidTitleThenGetNull() {
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
    public void whenSetValidTitleThenGetThisTitle() {
        final T content = getObject();
        content.setTitle(MockConstants.TITLE);
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        Assert.assertEquals(
                content.getTitle(),
                MockConstants.TITLE
        );
        assertEquals(
                content.getUrl(),
                Translator.fromCyrillicToLatin(MockConstants.TITLE)
        );
    }

    @Test
    public void whenSetInvalidUrlThenGetNull() {
        final T content = getObject();
        content.setUrl(null);
        assertNull(content.getUrl());
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
    public void whenSetInvalidDescriptionThenReturnNull() {
        final T content = getObject();
        content.setDescription(null);
        assertNull(content.getDescription());
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
        content.setDescription(MockConstants.DESCRIPTION);
        assertNotNull(content.getDescription());
        Assert.assertEquals(
                content.getDescription(),
                MockConstants.DESCRIPTION
        );
    }

    @Test
    public void whenSetInvalidKeywordsThenReturnNull() {
        final T content = getObject();
        content.setKeywords(null);
        assertNull(content.getKeywords());
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
        content.setKeywords(MockConstants.KEYWORDS);
        assertNotNull(content.getKeywords());
        Assert.assertEquals(
                content.getKeywords(),
                MockConstants.KEYWORDS
        );
    }

    @Test
    @Override
    public void validObject() {
        final T content = getObject();
        final boolean value = content != null &&
                isNotBlank(content.getTitle()) &&
                isNotBlank(content.getUrl());
        assertEquals(Content.isValidated(content), value);
    }
}
