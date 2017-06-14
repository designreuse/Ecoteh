package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelEntityConverterTest<E extends ModelEntity, T extends Model> {

    @Test
    public void whenConvertThenReturnValidEntity() {
        final ModelEntityConverter<E, T> converter = getConverter();
        final T model = converter.convert();
        checkEntity(model);
    }

    @Test
    public void whenSyncConvertThenReturnValidEntity() {
        final ModelEntityConverter<E, T> converter = getConverter();
        final T model = converter.syncConvert();
        checkEntity(model);
    }

    @Test
    public void when() {
        final ModelEntityConverter<E, T> converter = getConverter();
        final ModelBuilder<T, ?> builder = converter.prepareBuilder();
        assertNotNull(builder);
    }

    @Test
    public void whenGetConverterThenReturnNotNull() {
        final ModelEntityConverter<E, T> converter = getConverter();
        assertNotNull(converter);
    }

    @Test
    public void whenGetModelThenReturnNotNull() {
        final E entity = getEntity();
        assertNotNull(entity);
    }

    protected void checkEntity(final T model) {
        assertNotNull(model);
        final E entity = getEntity();
        assertEquals(model.getId(), entity.getId());
        assertEquals(model.isValidated(), entity.isValidated());
    }

    protected abstract ModelEntityConverter<E, T> getConverter();

    protected abstract E getEntity();
}