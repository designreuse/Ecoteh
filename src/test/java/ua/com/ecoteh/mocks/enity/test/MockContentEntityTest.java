package ua.com.ecoteh.mocks.enity.test;

import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentEntity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class MockContentEntityTest<E extends ContentEntity> extends MockModelEntityTest<E> {

    @Test
    public void whenGetTitleThenReturnNotEmpty() {
        final E content = getObject();
        assertFalse(content.getTitle().isEmpty());
    }

    @Test
    public void whenGetUrlThenReturnNotEmpty() {
        final E content = getObject();
        assertFalse(content.getUrl().isEmpty());
    }

    @Test
    public void whenGetDescriptionThenReturnNotEmpty() {
        final E content = getObject();
        assertFalse(content.getDescription().isEmpty());
    }

    @Test
    public void whenGetKeywordsThenReturnNotEmpty() {
        final E content = getObject();
        assertFalse(content.getKeywords().isEmpty());
    }

    @Test
    public void whenGetLogoThenReturnNotNull() {
        final E content = getObject();
        assertNotNull(content.getLogoEntity());
    }
}
