package ua.com.ecoteh.entity.file;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileEditorTest extends ModelEditorTest<File> {

    private FileEditor editor;
    private File file;

    @Before
    public void beforeTests() {
        this.file = getFile();
        this.editor = new FileEditor(this.file);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final File updatedFile = this.editor.update();
        assertEquals(updatedFile.getTitle(), this.file.getTitle());
        assertEquals(updatedFile.getUrl(), this.file.getUrl());
        assertEquals(updatedFile.getType(), this.file.getType());
        assertEquals(updatedFile.getMultipartFile(), this.file.getMultipartFile());
    }

    @Test
    public void whenAddTitleThenBuildWithIt() {
        File file;
        String title;
        for (int i = 0; i < 5; i++) {
            title = TITLE + i;
            this.editor.addTitle(title);
            file = this.editor.update();
            assertEquals(file.getTitle(), title);
        }
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        this.editor.addTitle(null);
        final File file = this.editor.update();
        assertEquals(file.getTitle(), this.file.getTitle());
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        File file;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.editor.addUrl(url);
            file = this.editor.update();
            assertEquals(file.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        this.editor.addUrl(null);
        final File file = this.editor.update();
        assertEquals(file.getUrl(), this.file.getUrl());
    }

    @Test
    public void whenAddTypeThenBuildWithIt() {
        File file;
        for (FileType type : FileType.values()) {
            this.editor.addType(type);
            file = this.editor.update();
            assertEquals(file.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeThenBuildWithEmptyIt() {
        this.editor.addType((FileType) null);
        final File file = this.editor.update();
        assertEquals(file.getType(), this.file.getType());
    }

    @Test
    public void whenAddTypeNameThenBuildWithIt() {
        File file;
        for (FileType type : FileType.values()) {
            this.editor.addType(type.name());
            file = this.editor.update();
            assertEquals(file.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeNameThenBuildWithEmptyIt() {
        this.editor.addType((String) null);
        final File file = this.editor.update();
        assertNotNull(file.getType());
    }

    @Test
    public void whenAddEmptyTypeNameThenBuildWithEmptyIt() {
        this.editor.addType("");
        final File file = this.editor.update();
        assertNotNull(file.getType());
    }

    @Test
    public void whenAddMultipartFileThenBuildWithIt() {
        final MultipartFile multipartFile = mock(MultipartFile.class);
        this.editor.addMultipartFile(multipartFile);
        final File file = this.editor.update();
        assertEquals(file.getMultipartFile(), multipartFile);
    }

    @Test
    public void whenAddNullMultipartFileThenBuildWithIt() {
        this.editor.addMultipartFile(null);
        final File file = this.editor.update();
        assertEquals(file.getMultipartFile(), this.file.getMultipartFile());
    }

    @Override
    protected FileEditor getEditor() {
        return this.editor;
    }

    @Override
    protected File getModel() {
        return this.file;
    }
}