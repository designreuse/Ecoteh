package ua.com.ecoteh.mocks.enity.test;

import org.junit.Test;
import ua.com.ecoteh.entity.content.Content;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class MockContentTest<T extends Content> extends MockModelTest<T> {

    @Test
    public void whenGetTitleThenReturnNotEmpty() {
        final T content = getObject();
        assertFalse(content.getTitle().isEmpty());
    }

    @Test
    public void whenGetUrlThenReturnNotEmpty() {
        final T content = getObject();
        assertFalse(content.getUrl().isEmpty());
    }

    @Test
    public void whenGetDescriptionThenReturnNotEmpty() {
        final T content = getObject();
        assertFalse(content.getDescription().isEmpty());
    }

    @Test
    public void whenGetKeywordsThenReturnNotEmpty() {
        final T content = getObject();
        assertFalse(content.getKeywords().isEmpty());
    }

    @Test
    public void whenGetLogoThenReturnNotNull() {
        final T content = getObject();
        assertNotNull(content.getLogo());
    }
}
