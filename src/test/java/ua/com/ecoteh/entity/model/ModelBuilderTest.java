package ua.com.ecoteh.entity.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class ModelBuilderTest<T extends Model> {

    @Test
    public void whenBuildEmptyThenReturnValidModel() {
        final ModelBuilder<T, ?> builder = getBuilder();
        final T model = builder.build();
        assertTrue(model.getId() >= 0);
    }

    @Test
    public void whenAddIdThenBuildWithIt() {
        final ModelBuilder<T, ?> builder = getBuilder();
        T model;
        for (int i = 0; i < 5; i++) {
            builder.addId(i);
            model = builder.build();
            assertTrue(model.getId() == i);
        }
    }

    @Test
    public void whenAddNegativeIdThenBuildWithZeroId() {
        final ModelBuilder<T, ?> builder = getBuilder();
        T model;
        for (int i = 0; i < 5; i++) {
            builder.addId(-i);
            model = builder.build();
            assertTrue(model.getId() == 0);
        }
    }

    @Test
    public void whenAddValidatedThenBuildWithIt() {
        final ModelBuilder<T, ?> builder = getBuilder();
        builder.addValidated(true);
        T model = builder.build();
        assertTrue(model.isValidated());
        builder.addValidated(false);
        model = builder.build();
        assertFalse(model.isValidated());
    }

    @Test
    public void whenSetValidThenReturnBuildValid() {
        final ModelBuilder<T, ?> builder = getBuilder();
        builder.isValid();
        final T model = builder.build();
        assertTrue(model.isValidated());
    }

    @Test
    public void whenSetNotValidThenReturnBuildInvalid() {
        final ModelBuilder<T, ?> builder = getBuilder();
        builder.isNotValid();
        final T model = builder.build();
        assertFalse(model.isValidated());
    }

    protected abstract ModelBuilder<T, ?> getBuilder();
}