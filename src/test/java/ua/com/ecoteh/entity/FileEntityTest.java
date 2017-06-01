package ua.com.ecoteh.entity;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.util.translator.Translator;

import static org.junit.Assert.*;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

public final class FileEntityTest extends ModelTest<FileEntity> {

    @Test
    public void toStringTest() {
        final FileEntity fileEntity = getObject();
        assertNotNull(fileEntity.toString());
        final String fileToString = "FileEntity{" +
                "ModelEntity{" +
                "id=" + fileEntity.getId() +
                ", validated=" + fileEntity.isValidated() +
                '}' +
                ", title='" + fileEntity.getTitle() + '\'' +
                ", type='" + fileEntity.getType() + '\'' +
                ", url='" + fileEntity.getUrl() + '\'' +
                '}';
        assertEquals(fileEntity.toString(), fileToString);
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final FileEntity fileEntity1 = getObject();
        final FileEntity fileEntity2 = fileEntity1.clone();
        assertEquals(fileEntity1, fileEntity2);
        final boolean value = fileEntity1.getTitle().equalsIgnoreCase(fileEntity2.getTitle()) &&
                fileEntity1.getUrl().equalsIgnoreCase(fileEntity2.getUrl());
        assertEquals(fileEntity1.equals(fileEntity2), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final FileEntity fileEntity = getObject();
        final int value = fileEntity.getTitle().hashCode() + fileEntity.getUrl().hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(fileEntity.hashCode(), value);
        }
    }

    @Test
    public void whenSetNullTitleThenGetEmptyString() {
        final FileEntity fileEntity = getObject();
        fileEntity.setTitle(null);
        assertNotNull(fileEntity.getTitle());
        assertEquals(fileEntity.getTitle(), "");
    }

    @Test
    public void whenSetBlankTitleThenGetEmptyString() {
        final FileEntity fileEntity = getObject();
        fileEntity.setTitle("");
        assertNotNull(fileEntity.getTitle());
        fileEntity.setTitle(" ");
        assertNotNull(fileEntity.getTitle());
        fileEntity.setTitle("    ");
        assertNotNull(fileEntity.getTitle());
        assertEquals(fileEntity.getTitle(), "");
    }

    @Test
    public void whenSetNullUrlThenGetEmptyStringl() {
        final FileEntity fileEntity = getObject();
        fileEntity.setUrl(null);
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final FileEntity fileEntity = getObject();
        fileEntity.setUrl("");
        assertNotNull(fileEntity.getUrl());
        fileEntity.setUrl(" ");
        assertNotNull(fileEntity.getUrl());
        fileEntity.setUrl("   ");
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final FileEntity fileEntity = getObject();
        fileEntity.setUrl(MockConstants.URL);
        assertNotNull(fileEntity.getUrl());
        final String url = Translator.fromCyrillicToLatin(MockConstants.URL);
        assertEquals(fileEntity.getUrl(), url);
        fileEntity.setUrl(null);
        fileEntity.translateAndSetUrl(MockConstants.URL);
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), url);
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final FileEntity fileEntity = new FileEntity(null, null);
        assertNotNull(fileEntity.getTitle());
        assertNotNull(fileEntity.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final FileEntity fileEntity = new FileEntity("", "");
        assertNotNull(fileEntity.getTitle());
        assertEquals(fileEntity.getTitle(), "");
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final FileEntity fileEntity = new FileEntity(" ", " ");
        assertNotNull(fileEntity.getTitle());
        assertEquals(fileEntity.getTitle(), "");
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final FileEntity fileEntity = new FileEntity("  ", "  ");
        assertNotNull(fileEntity.getTitle());
        assertEquals(fileEntity.getTitle(), "");
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final FileEntity fileEntity = new FileEntity(MockConstants.TITLE, MockConstants.URL);
        assertNotNull(fileEntity.getTitle());
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getTitle(), MockConstants.TITLE);
        assertEquals(fileEntity.getUrl(), MockConstants.URL);
    }

    @Test
    public void equalsInvalidObjects() {
        final FileEntity fileEntity1 = new FileEntity();
        final FileEntity fileEntity2 = new FileEntity();
        assertTrue(fileEntity1.equals(fileEntity2));
        fileEntity1.setTitle(MockConstants.TITLE);
        fileEntity2.setTitle(MockConstants.TITLE);
        assertTrue(fileEntity1.equals(fileEntity2));
        fileEntity1.setUrl(MockConstants.URL);
        fileEntity2.setUrl(MockConstants.URL);
        assertTrue(fileEntity1.equals(fileEntity2));
    }

    @Test
    public void hashCodeInvalidObject() {
        final FileEntity fileEntity = new FileEntity();
        int value = 0;
        assertEquals(fileEntity.hashCode(), value);
        fileEntity.setTitle(MockConstants.TITLE);
        value += (isNotEmpty(fileEntity.getTitle()) ? fileEntity.getTitle().hashCode() : 0) +
                (isNotEmpty(fileEntity.getUrl()) ? fileEntity.getUrl().hashCode() : 0);
        assertEquals(fileEntity.hashCode(), value);
    }

    @Test
    public void whenSetValidTitleThenGetThisTitle() {
        final FileEntity fileEntity = MockEntity.getFileEntity();
        fileEntity.setTitle(MockConstants.TITLE);
        assertNotNull(fileEntity.getTitle());
        assertNotNull(fileEntity.getUrl());
        Assert.assertEquals(fileEntity.getTitle(), MockConstants.TITLE);
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetEmptyString() {
        final FileEntity fileEntity = MockEntity.getFileEntity();
        fileEntity.setUrl(null);
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }


    @Test
    public void whenTranslateAndSetBlankUrlThenGetNull() {
        final FileEntity fileEntity = MockEntity.getFileEntity();
        fileEntity.setUrl("");
        assertNotNull(fileEntity.getUrl());
        fileEntity.setUrl(" ");
        assertNotNull(fileEntity.getUrl());
        fileEntity.setUrl("   ");
        assertNotNull(fileEntity.getUrl());
        assertEquals(fileEntity.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final FileEntity fileEntity = MockEntity.getFileEntity();
        fileEntity.setUrl(MockConstants.URL);
        assertNotNull(fileEntity.getUrl());
        final String url = Translator.fromCyrillicToLatin(MockConstants.URL.replace(".", "!"));
        assertEquals(fileEntity.getUrl(), isNotEmpty(url) ? url.replace("!", ".") : url);
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final FileEntity model1 = getInstance();
        final FileEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final FileEntity model1 = getInstance();
        final FileEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected FileEntity getObject() {
        return MockEntity.getFileEntity();
    }

    @Override
    protected FileEntity getInstance() {
        return new FileEntity();
    }
}
