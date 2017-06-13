package ua.com.ecoteh.entity.file;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntityTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileEntityTest extends ModelEntityTest {

    private FileEntity file;

    @Before
    public void beforeTests() {
        this.file = new FileEntity();
        this.file.setId(ID);
        this.file.setValidated(VALIDATION);
        this.file.setTitle(TITLE);
        this.file.setUrl(URL);
        this.file.setType(FILE_TYPE);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "FileEntity{" +
                "ModelEntity{" +
                "id=" + this.file.getId() +
                ", validated=" + this.file.isValidated() +
                '}' +
                ", title='" + this.file.getTitle() + '\'' +
                ", type='" + this.file.getType() + '\'' +
                ", url='" + this.file.getUrl() + '\'' +
                '}';
        assertEquals(this.file.toString(), toStringTest);
    }

    @Test
    public void whenSetTitleThenGetIt() {
        String title;
        for (int i = 0; i < 5; i++) {
            title = TITLE + i;
            this.file.setTitle(title);
            assertEquals(this.file.getTitle(), title);
        }
    }

    @Test
    public void whenSetUrlThenGetIt() {
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.file.setUrl(url);
            assertEquals(this.file.getUrl(), url);
        }
    }

    @Test
    public void whenSetTypeThenGetIt() {
        for (FileType type : FileType.values()) {
            this.file.setType(type);
            assertEquals(this.file.getType(), type);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final File file = this.file.convert();
        assertEquals(file.getId(), this.file.getId());
        assertEquals(file.isValidated(), this.file.isValidated());
        assertEquals(file.getTitle(), this.file.getTitle());
        assertEquals(file.getUrl(), this.file.getUrl());
        assertEquals(file.getType(), this.file.getType());
    }

    @Override
    protected FileEntity getInstance() {
        return this.file;
    }
}