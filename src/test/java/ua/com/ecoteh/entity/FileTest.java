package ua.com.ecoteh.entity;

import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.util.translator.Translator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;

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
                ", type='" + file.getType() + '\'' +
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
        final boolean value = file1.getTitle().equalsIgnoreCase(file2.getTitle()) &&
                file1.getUrl().equalsIgnoreCase(file2.getUrl());
        assertEquals(file1.equals(file2), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final File file = getObject();
        final int value = file.getTitle().hashCode() + file.getUrl().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(file.hashCode(), value);
        }
    }

    @Test
    public void whenSetNullTitleThenGetEmptyString() {
        final File file = getObject();
        file.setTitle(null);
        assertNotNull(file.getTitle());
        assertEquals(file.getTitle(), "");
    }

    @Test
    public void whenSetBlankTitleThenGetEmptyString() {
        final File file = getObject();
        file.setTitle("");
        assertNotNull(file.getTitle());
        file.setTitle(" ");
        assertNotNull(file.getTitle());
        file.setTitle("    ");
        assertNotNull(file.getTitle());
        assertEquals(file.getTitle(), "");
    }

    @Test
    public void whenSetNullUrlThenGetEmptyStringl() {
        final File file = getObject();
        file.setUrl(null);
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final File file = getObject();
        file.setUrl("");
        assertNotNull(file.getUrl());
        file.setUrl(" ");
        assertNotNull(file.getUrl());
        file.setUrl("   ");
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final File file = getObject();
        file.setUrl(MockConstants.URL);
        assertNotNull(file.getUrl());
        final String url = Translator.fromCyrillicToLatin(MockConstants.URL);
        assertEquals(file.getUrl(), url);
        file.setUrl(null);
        file.translateAndSetUrl(MockConstants.URL);
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), url);
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final File file = new File(null, null);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final File file = new File("", "");
        assertNotNull(file.getTitle());
        assertEquals(file.getTitle(), "");
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final File file = new File(" ", " ");
        assertNotNull(file.getTitle());
        assertEquals(file.getTitle(), "");
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final File file = new File("  ", "  ");
        assertNotNull(file.getTitle());
        assertEquals(file.getTitle(), "");
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final File file = new File(MockConstants.TITLE, MockConstants.URL);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertEquals(file.getTitle(), MockConstants.TITLE);
        assertEquals(file.getUrl(), MockConstants.URL);
    }

    @Test
    public void equalsInvalidObjects() {
        final File file1 = new File();
        final File file2 = new File();
        assertTrue(file1.equals(file2));
        file1.setTitle(MockConstants.TITLE);
        file2.setTitle(MockConstants.TITLE);
        assertTrue(file1.equals(file2));
        file1.setUrl(MockConstants.URL);
        file2.setUrl(MockConstants.URL);
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
        final File file = MockEntity.getFile();
        file.setTitle(MockConstants.TITLE);
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        Assert.assertEquals(file.getTitle(), MockConstants.TITLE);
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetEmptyString() {
        final File file = MockEntity.getFile();
        file.setUrl(null);
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }


    @Test
    public void whenTranslateAndSetBlankUrlThenGetNull() {
        final File file = MockEntity.getFile();
        file.setUrl("");
        assertNotNull(file.getUrl());
        file.setUrl(" ");
        assertNotNull(file.getUrl());
        file.setUrl("   ");
        assertNotNull(file.getUrl());
        assertEquals(file.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final File file = MockEntity.getFile();
        file.setUrl(MockConstants.URL);
        assertNotNull(file.getUrl());
        final String url = Translator.fromCyrillicToLatin(MockConstants.URL.replace(".", "!"));
        assertEquals(
                file.getUrl(),
                isNotBlank(url) ? url.replace("!", ".") : url
        );
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final File model1 = getInstance();
        final File model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final File model1 = getInstance();
        final File model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected File getObject() {
        return MockEntity.getFile();
    }

    @Override
    protected File getInstance() {
        return new File();
    }
}
