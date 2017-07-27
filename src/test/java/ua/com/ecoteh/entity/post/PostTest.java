package ua.com.ecoteh.entity.post;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentTest;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileBuilder;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class PostTest extends ContentTest {

    private static Post post;

    @BeforeClass
    public static void setUp() throws Exception {
        post = new Post(
                ID, VALIDATION, TITLE, URL, TEXT,
                DESCRIPTION, KEYWORDS, getFile(), DATE
        );
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Post{" +
                "Content{" +
                "Model{id=" + post.getId() +
                ", validated=" + post.isValidated() + '}' +
                ", title='" + post.getTitle() + '\'' +
                ", url='" + post.getUrl() + '\'' +
                ", text='" + post.getText() + '\'' +
                ", description='" + post.getDescription() + '\'' +
                ", keywords='" + post.getKeywords() + '\'' +
                ", logo=" + post.getLogo() + '}' +
                ", date=" + post.getDate() +
                '}';
        assertEquals(post.toString(), testString);
    }

    @Test
    public void getText() throws Exception {
        assertFalse(post.getText().isEmpty());
    }

    @Test
    public void getDate() throws Exception {
        assertNotNull(post.getDate());
    }

    @Test
    public void getDateToString() throws Exception {
        assertFalse(post.getDateToString().isEmpty());
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final PostEntity entity = post.convert();
        assertTrue(entity.getId() >= 0);
        assertEquals(entity.isValidated(), post.isValidated());
        assertEquals(entity.getTitle(), post.getTitle());
        assertEquals(entity.getUrl(), post.getUrl());
        assertEquals(entity.getDescription(), post.getDescription());
        assertEquals(entity.getKeywords(), post.getKeywords());
        assertEquals(entity.getText(), post.getText());
        assertEquals(entity.getDate(), post.getDate());
        assertNotNull(entity.getLogoEntity());
    }

    @Test
    public void getBuilder() throws Exception {
        final FileBuilder builder = File.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Post getInstance() {
        return post;
    }
}
