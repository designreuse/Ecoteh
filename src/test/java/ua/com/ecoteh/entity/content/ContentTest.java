package ua.com.ecoteh.entity.content;

import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentTest extends ModelTest {

    @Test
    @Override
    public void toStringTest() throws Exception {
        final Content content = getInstance();
        final String testString = "Content{" +
                "Model{id=" + content.getId() +
                ", validated=" + content.isValidated() + '}' +
                ", title='" + content.getTitle() + '\'' +
                ", url='" + content.getUrl() + '\'' +
                ", description='" + content.getDescription() + '\'' +
                ", keywords='" + content.getKeywords() + '\'' +
                ", logo=" + content.getLogo() +
                '}';
        assertEquals(content.toString(), testString);
    }

    @Test
    public void getTitle() throws Exception {
        final Content content = getInstance();
        assertFalse(content.getTitle().isEmpty());
    }

    @Test
    public void getUrl() throws Exception {
        final Content content = getInstance();
        assertFalse(content.getUrl().isEmpty());
    }

    @Test
    public void getDescription() throws Exception {
        final Content content = getInstance();
        assertFalse(content.getDescription().isEmpty());
    }

    @Test
    public void getKeywords() throws Exception {
        final Content content = getInstance();
        assertFalse(content.getKeywords().isEmpty());
    }

    @Test
    public void getLogo() throws Exception {
        final Content content = getInstance();
        assertNotNull(content.getLogo());
    }

    @Override
    protected abstract Content getInstance();
}