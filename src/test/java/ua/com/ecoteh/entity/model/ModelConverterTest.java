package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelConverterTest<T extends Model, E extends ModelEntity> {

    @Test
    public void whenConvertThenReturnValidEntity() {
        final ModelConverter<T, E> converter = getConverter();
        final E entity = converter.convert();
        checkEntity(entity);
    }

    @Test
    public void whenSyncConvertThenReturnValidEntity() {
        final ModelConverter<T, E> converter = getConverter();
        final E entity = converter.syncConvert();
        checkEntity(entity);
    }

    @Test
    public void whenGetConverterThenReturnNotNull() {
        final ModelConverter<T, E> converter = getConverter();
        assertNotNull(converter);
    }

    @Test
    public void whenGetModelThenReturnNotNull() {
        final T model = getModel();
        assertNotNull(model);
    }

    protected void checkEntity(final E entity) {
        assertNotNull(entity);
        final T model = getModel();
        assertEquals(entity.getId(), model.getId());
        assertEquals(entity.isValidated(), model.isValidated());
    }

    protected abstract ModelConverter<T, E> getConverter();

    protected abstract T getModel();
}