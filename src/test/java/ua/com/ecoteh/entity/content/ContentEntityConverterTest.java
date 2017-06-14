package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentEntityConverterTest<E extends ContentEntity, T extends Content>
        extends ModelEntityConverterTest<E, T> {

    @Override
    protected void checkEntity(final T content) {
        super.checkEntity(content);
        final E entity = getEntity();
        assertEquals(content.getTitle(), entity.getTitle());
        assertEquals(content.getUrl(), entity.getUrl());
        assertEquals(content.getDescription(), entity.getDescription());
        assertEquals(content.getKeywords(), entity.getKeywords());
        assertNotNull(content.getLogo());
    }

    @Override
    protected abstract ContentEntityConverter<E, T> getConverter();
}