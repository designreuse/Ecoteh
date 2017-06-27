package ua.com.ecoteh.entity.content;

import org.junit.Test;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntityTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEntityTest extends ModelEntityTest {

    @Test
    public void toStringTest() {
        final ContentEntity entity = getInstance();
        final String toStringTest = "ContentEntity{" +
                "ModelEntity{" +
                "id=" + entity.getId() +
                ", validated=" + entity.isValidated() +
                '}' +
                ", title='" + entity.getTitle() + '\'' +
                ", url='" + entity.getUrl() + '\'' +
                ", text='" + entity.getText() + '\'' +
                ", description='" + entity.getDescription() + '\'' +
                ", keywords='" + entity.getKeywords() + '\'' +
                ", logo=" + entity.getLogoEntity() +
                '}';
        assertEquals(entity.toString(), toStringTest);
    }

    @Test
    public void whenSetTitleThenGetIt() {
        final ContentEntity entity = getInstance();
        for (int i = 0; i < 5; i++) {
            final String title = TITLE + i;
            entity.setTitle(title);
            assertEquals(entity.getTitle(), title);
        }
    }

    @Test
    public void whenSetUrlThenGetIt() {
        final ContentEntity entity = getInstance();
        for (int i = 0; i < 5; i++) {
            final String url = URL + i;
            entity.setUrl(url);
            assertEquals(entity.getUrl(), url);
        }
    }

    @Test
    public void whenSetTextThenGetIt() {
        final ContentEntity entity = getInstance();
        for (int i = 0; i < 5; i++) {
            final String text = TEXT + i;
            entity.setText(text);
            assertEquals(entity.getText(), text);
        }
    }

    @Test
    public void whenSetDescriptionThenGetIt() {
        final ContentEntity entity = getInstance();
        for (int i = 0; i < 5; i++) {
            final String description = DESCRIPTION + i;
            entity.setDescription(description);
            assertEquals(entity.getDescription(), description);
        }
    }

    @Test
    public void whenSetKeywordsThenGetIt() {
        final ContentEntity entity = getInstance();
        for (int i = 0; i < 5; i++) {
            final String keywords = KEYWORDS + i;
            entity.setKeywords(keywords);
            assertEquals(entity.getKeywords(), keywords);
        }
    }

    @Test
    public void whenSetLogoThenGetIt() {
        final ContentEntity entity = getInstance();
        final FileEntity logo = getFileEntity();
        entity.setLogoEntity(logo);
        assertEquals(entity.getLogoEntity(), logo);
    }

    @Override
    protected abstract ContentEntity getInstance();
}