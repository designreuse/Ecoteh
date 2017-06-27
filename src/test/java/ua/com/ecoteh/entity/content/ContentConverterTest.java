package ua.com.ecoteh.entity.content;

import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ContentConverterTest<T extends Content, E extends ContentEntity>
        extends ModelConverterTest<T, E> {

    @Override
    protected void checkEntity(final E entity) {
        super.checkEntity(entity);
        final T content = getModel();
        assertEquals(entity.getTitle(), content.getTitle());
        assertEquals(entity.getUrl(), content.getUrl());
        assertEquals(entity.getText(), content.getText());
        assertEquals(entity.getDescription(), content.getDescription());
        assertEquals(entity.getKeywords(), content.getKeywords());
        assertNotNull(entity.getLogoEntity());
    }

    @Override
    protected abstract ContentConverter<T, E> getConverter();
}