package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.enity.MockEntity.getFile;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class FileTest extends ModelTest<File> {

    @Test
    public void toStringTest() {
        final File file = getObject();
        assertNotNull(file.toString());
        final String fileToString = "File{" +
                "Model{" +
                "id=" + file.getId() +
                ", validated=" + file.isValidated() +
                '}' +
                ", title='" + file.getTitle() + '\'' +
                ", url='" + file.getUrl() + '\'' +
                '}';
        assertEquals(file.toString(), fileToString);
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final File file1 = getObject();
        final File file2 = file1.clone();
        assertEquals(file1, file2);
        final boolean value = (isNotBlank(file1.getTitle()) ?
                file1.getTitle()
                        .equalsIgnoreCase(
                                file2.getTitle()
                        ) :
                isBlank(file2.getTitle())
        ) && (
                isNotBlank(file1.getUrl()) ?
                        file1.getUrl()
                                .equalsIgnoreCase(
                                        file2.getUrl()
                                ) :
                        isBlank(file2.getUrl())
        );
        assertEquals(file1.equals(file2), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final File file = getObject();
        final int value = (
                isNotBlank(file.getTitle()) ? file.getTitle().hashCode() : 0
        ) + (
                isNotBlank(file.getUrl()) ? file.getUrl().hashCode() : 0
        );
        for (int i = 0; i < 10; i++) {
            assertEquals(file.hashCode(), value);
        }
    }

    @Test
    public void whenInitializeObjectWithNullParametersThenGetNull() {
        final File file = getObject();
        file.initialize(null, null);
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull() {
        final File file = getObject();
        file.initialize("", "");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
        file.initialize(" ", " ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
        file.initialize("   ", "   ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final File file = getObject();
        file.initialize(TITLE, URL);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertEquals(file.getTitle(), TITLE);
        assertEquals(file.getUrl(), URL);
    }

    @Test
    public void whenSetNullTitleThenGetNull() {
        final File file = getObject();
        file.setTitle(null);
        assertNull(file.getTitle());
    }

    @Test
    public void whenSetBlankTitleThenGetNull() {
        final File file = getObject();
        file.setTitle("");
        assertNull(file.getTitle());
        file.setTitle(" ");
        assertNull(file.getTitle());
        file.setTitle("    ");
        assertNull(file.getTitle());
    }

    @Test
    public void whenSetNullUrlThenGetNull() {
        final File file = getObject();
        file.setUrl(null);
        assertNull(file.getUrl());
    }

    @Test
    public void whenSetBlankUrlThenGetNull() {
        final File file = getObject();
        file.setUrl("");
        assertNull(file.getUrl());
        file.setUrl(" ");
        assertNull(file.getUrl());
        file.setUrl("   ");
        assertNull(file.getUrl());
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final File file = getObject();
        file.setUrl(URL);
        assertNotNull(file.getUrl());
        final String url = Translator.fromCyrillicToLatin(URL);
        assertEquals(file.getUrl(), url);
        file.setUrl(null);
        file.translateAndSetUrl(URL);
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), url);
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveNull() {
        final File file = new File(null, null);
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_1() {
        final File file = new File("", "");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_2() {
        final File file = new File(" ", " ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_3() {
        final File file = new File("  ", "  ");
        assertNull(file.getTitle());
        assertNull(file.getUrl());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final File file = new File(TITLE, URL);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertEquals(file.getTitle(), TITLE);
        assertEquals(file.getUrl(), URL);
    }

    @Test
    public void equalsInvalidObjects() {
        final File file1 = new File();
        final File file2 = new File();
        assertTrue(file1.equals(file2));
        file1.setTitle(MockConstants.TITLE);
        file2.setTitle(MockConstants.TITLE);
        assertTrue(file1.equals(file2));
        file1.setUrl(URL);
        file2.setUrl(URL);
        assertTrue(file1.equals(file2));
    }

    @Test
    public void hashCodeInvalidObject() {
        final File file = new File();
        int value = 0;
        assertEquals(file.hashCode(), value);
        file.setTitle(MockConstants.TITLE);
        value += (
                isNotBlank(file.getTitle()) ?
                        file.getTitle().hashCode() : 0
        ) + (
                isNotBlank(file.getUrl()) ?
                        file.getUrl().hashCode() : 0
        );
        assertEquals(file.hashCode(), value);
    }

    @Test
    public void whenSetValidTitleThenGetThisTitle() {
        final File file = getFile();
        file.setTitle(TITLE);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        Assert.assertEquals(file.getTitle(), TITLE);
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetNull() {
        final File file = getFile();
        file.setUrl(null);
        assertNull(file.getUrl());
    }


    @Test
    public void whenTranslateAndSetBlankUrlThenGetNull() {
        final File file = getFile();
        file.setUrl("");
        assertNull(file.getUrl());
        file.setUrl(" ");
        assertNull(file.getUrl());
        file.setUrl("   ");
        assertNull(file.getUrl());
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final File file = getFile();
        file.setUrl(URL);
        assertNotNull(file.getUrl());

        final String url = Translator.fromCyrillicToLatin(
                URL.replace(".", "!")
        );
        assertEquals(
                file.getUrl(),
                isNotBlank(url) ? url.replace("!", ".") : url
        );
    }

    @Ignore
    @Override
    protected File getObject() {
        return getFile();
    }
}
