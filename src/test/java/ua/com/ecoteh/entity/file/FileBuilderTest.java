package ua.com.ecoteh.entity.file;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileBuilderTest extends ModelBuilderTest<File> {

    private FileBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new FileBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final File file = this.builder.build();
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertNotNull(file.getType());
        assertNull(file.getMultipartFile());
    }

    @Test
    public void whenAddTitleThenBuildWithIt() {
        File file;
        String title;
        for (int i = 0; i < 5; i++) {
            title = TITLE + i;
            this.builder.addTitle(title);
            file = this.builder.build();
            assertEquals(file.getTitle(), title);
        }
    }

    @Test
    public void whenAddNullTitleThenBuildWithEmptyIt() {
        this.builder.addTitle(null);
        final File file = this.builder.build();
        assertTrue(file.getTitle().isEmpty());
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        File file;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.builder.addUrl(url);
            file = this.builder.build();
            assertEquals(file.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        this.builder.addUrl(null);
        final File file = this.builder.build();
        assertTrue(file.getUrl().isEmpty());
    }

    @Test
    public void whenAddTypeThenBuildWithIt() {
        File file;
        for (FileType type : FileType.values()) {
            this.builder.addType(type);
            file = this.builder.build();
            assertEquals(file.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeThenBuildWithEmptyIt() {
        this.builder.addType((FileType) null);
        final File file = this.builder.build();
        assertNotNull(file.getType());
    }

    @Test
    public void whenAddTypeNameThenBuildWithIt() {
        File file;
        for (FileType type : FileType.values()) {
            this.builder.addType(type.name());
            file = this.builder.build();
            assertEquals(file.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeNameThenBuildWithEmptyIt() {
        this.builder.addType((String) null);
        final File file = this.builder.build();
        assertNotNull(file.getType());
    }

    @Test
    public void whenAddEmptyTypeNameThenBuildWithEmptyIt() {
        this.builder.addType("");
        final File file = this.builder.build();
        assertNotNull(file.getType());
    }

    @Test
    public void whenAddMultipartFileThenBuildWithIt() {
        final MultipartFile multipartFile = mock(MultipartFile.class);
        this.builder.addMultipartFile(multipartFile);
        final File file = this.builder.build();
        assertNotNull(file.getMultipartFile());
    }

    @Test
    public void whenAddNullMultipartFileThenBuildWithIt() {
        this.builder.addMultipartFile(null);
        final File file = this.builder.build();
        assertNull(file.getMultipartFile());
    }

    @Override
    protected FileBuilder getBuilder() {
        return this.builder;
    }
}