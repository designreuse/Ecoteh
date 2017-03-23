package com.salimov.ecoteh.entity;

import com.salimov.ecoteh.mocks.MockConstants;
import com.salimov.ecoteh.util.translator.Translator;
import org.junit.Assert;
import org.junit.Test;

import static com.salimov.ecoteh.mocks.MockConstants.DESCRIPTION;
import static com.salimov.ecoteh.mocks.MockConstants.KEYWORDS;
import static com.salimov.ecoteh.mocks.MockConstants.TITLE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public abstract class ContentTest<T extends Content> extends ModelTest<T> {

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
        final boolean value =
                content1.getTitle().equalsIgnoreCase(content2.getTitle())
                        && content1.getUrl().equalsIgnoreCase(content2.getUrl());
        assertEquals(content1.equals(content2), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final T content = getObject();
        final int value = content.getTitle().hashCode() + content.getUrl().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(content.hashCode(), value);
        }
    }

    @Test
    public void whenSetNullTitleThenGetEmptyString() {
        final T content = getObject();
        content.setTitle(null);
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        content.setTitle("");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        content.setTitle(" ");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        content.setTitle("    ");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        assertTrue(content.getTitle().isEmpty());
    }

    @Test
    public void whenSetBlankTitleThenGetEmptyString() {
        final T content = getObject();
        content.setTitle("");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        content.setTitle(" ");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        content.setTitle("    ");
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        assertTrue(content.getTitle().isEmpty());
    }

    @Test
    public void whenSetValidTitleThenGetThisTitle() {
        final T content = getObject();
        content.setTitle(TITLE);
        assertNotNull(content.getTitle());
        assertNotNull(content.getUrl());
        assertEquals(content.getTitle(), TITLE);
    }

    @Test
    public void whenSetNullUrlThenGetEmptyString() {
        final T content = getObject();
        content.setUrl(null);
        assertNotNull(content.getUrl());
        assertTrue(content.getUrl().isEmpty());
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final T content = getObject();
        content.setUrl("");
        assertNotNull(content.getUrl());
        content.setUrl(" ");
        assertNotNull(content.getUrl());
        content.setUrl("   ");
        assertNotNull(content.getUrl());
        assertTrue(content.getUrl().isEmpty());
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
    public void whenSetNullDescriptionThenReturnEmptyString() {
        final T content = getObject();
        content.setDescription(null);
        assertNotNull(content.getDescription());
        assertTrue(content.getDescription().isEmpty());
    }

    @Test
    public void whenSetBlankDescriptionThenReturnEmptyString() {
        final T content = getObject();
        content.setDescription("");
        assertNotNull(content.getDescription());
        content.setDescription(" ");
        assertNotNull(content.getDescription());
        content.setDescription("   ");
        assertNotNull(content.getDescription());
        assertTrue(content.getDescription().isEmpty());
    }

    @Test
    public void whenSetValidDescriptionThenReturnThisDescription() {
        final T content = getObject();
        content.setDescription(DESCRIPTION);
        assertNotNull(content.getDescription());
        Assert.assertEquals(content.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenSetNullKeywordsThenReturnEmptyString() {
        final T content = getObject();
        content.setKeywords(null);
        assertNotNull(content.getKeywords());
        assertTrue(content.getKeywords().isEmpty());
    }

    @Test
    public void whenSetBlankKeywordsThenReturnEmptyString() {
        final T content = getObject();
        content.setKeywords("");
        assertNotNull(content.getKeywords());
        content.setKeywords(" ");
        assertNotNull(content.getKeywords());
        content.setKeywords("   ");
        assertNotNull(content.getKeywords());
        assertTrue(content.getKeywords().isEmpty());
    }

    @Test
    public void whenSetValidKeywordsThenReturnThisKeywords() {
        final T content = getObject();
        content.setKeywords(KEYWORDS);
        assertNotNull(content.getKeywords());
        Assert.assertEquals(content.getKeywords(), KEYWORDS);
    }
}
