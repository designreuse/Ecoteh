package ua.com.ecoteh.entity.post;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentEntityTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostEntityTest extends ContentEntityTest {

    private PostEntity post;

    @Before
    public void beforeTest() {
        this.post = new PostEntity();
        this.post.setId(ID);
        this.post.setValidated(VALIDATION);
        this.post.setTitle(TITLE);
        this.post.setUrl(URL);
        this.post.setDescription(DESCRIPTION);
        this.post.setKeywords(KEYWORDS);
        this.post.setText(TEXT);
        this.post.setDate(DATE);
        this.post.setLogoEntity(getFileEntity());
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "PostEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + this.post.getId() +
                ", validated=" + this.post.isValidated() +
                '}'+
                ", title='" + this.post.getTitle() + '\'' +
                ", url='" + this.post.getUrl() + '\'' +
                ", text='" + this.post.getText() + '\'' +
                ", description='" + this.post.getDescription() + '\'' +
                ", keywords='" + this.post.getKeywords() + '\'' +
                ", logo=" + this.post.getLogoEntity() +
                '}'+
                ", date=" + this.post.getDate() +
                '}';
        assertEquals(this.post.toString(), toStringTest);
    }

    @Test
    public void whenSetDateThenGetIt() {
        Date date;
        for (int i = 0; i < 5; i++) {
            date = new Date();
            this.post.setDate(date);
            assertEquals(this.post.getDate(), date);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Post post = this.post.convert();
        assertEquals(post.getId(), this.post.getId());
        assertEquals(post.isValidated(), this.post.isValidated());
        assertEquals(post.getTitle(), this.post.getTitle());
        assertEquals(post.getUrl(), this.post.getUrl());
        assertEquals(post.getDescription(), this.post.getDescription());
        assertEquals(post.getKeywords(), this.post.getKeywords());
        assertEquals(post.getText(), this.post.getText());
        assertEquals(post.getDate(), this.post.getDate());
        assertNotNull(post.getLogo());
    }

    @Override
    protected PostEntity getInstance() {
        return this.post;
    }
}
