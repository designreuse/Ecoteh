package ua.com.ecoteh.entity.file;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileTest extends ModelTest {

    private static File file;

    @BeforeClass
    public static void beforeClass() {
        file = new File(
                ID, VALIDATION, TITLE, URL, FILE_TYPE,
                mock(MultipartFile.class)
        );
    }

    @Test
    public void toStringTest() throws Exception {
        final String testString = "File{" +
                "Model{id=" + file.getId() +
                ", validated=" + file.isValidated() + '}' +
                ", title='" + file.getTitle() + '\'' +
                ", type='" + file.getType() + '\'' +
                ", url='" + file.getUrl() + '\'' +
                '}';
        assertEquals(file.toString(), testString);
    }

    @Test
    public void getTitle() throws Exception {
        assertFalse(file.getTitle().isEmpty());
    }

    @Test
    public void getUrl() throws Exception {
        assertFalse(file.getUrl().isEmpty());
    }

    @Test
    public void getType() throws Exception {
        assertNotNull(file.getType());
    }

    @Test
    public void getMultipartFile() throws Exception {
        assertNotNull(file.getMultipartFile());
    }

    @Test
    public void convert() throws Exception {
        super.convert();
        final FileEntity entity = file.convert();
        assertEquals(entity.getId(), file.getId());
        assertEquals(entity.isValidated(), file.isValidated());
        assertEquals(entity.getTitle(), file.getTitle());
        assertEquals(entity.getUrl(), file.getUrl());
        assertEquals(entity.getType(), file.getType());
    }

    @Test
    public void getBuilder() throws Exception {
        final FileBuilder builder = File.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected File getInstance() {
        return file;
    }
}