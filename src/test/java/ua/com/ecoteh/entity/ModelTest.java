package ua.com.ecoteh.entity;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class ModelTest<T extends Model> {

    @Test
    public void equalsValidObjects() {
        final T model1 = getObject();
        final T model2 = (T) model1.clone();
        final T model3 = (T) model2.clone();
        for (int i = 0; i < 10; i++) {
            assertTrue(model1.equals(model1));
            assertTrue(model1.equals(model2));
            assertTrue(model2.equals(model1));
            assertTrue(model1.equals(model2));
            assertTrue(model2.equals(model3));
            assertTrue(model1.equals(model3));
            assertFalse(model1.equals(null));
            assertFalse(model2.equals(null));
            assertFalse(model3.equals(null));
        }
        final boolean value = (model1 == model2) ||
                (model1.getClass() == model2.getClass());
        assertEquals(model1.equals(model2), value);
    }

    @Test
    public void hashCodeValidObject() {
        final T model = getObject();
        int hash1 = model.hashCode();
        for (int i = 0; i < 10; i++) {
            int hash2 = model.hashCode();
            assertEquals(hash1, hash2);
            hash1 = hash2;
        }
    }

    @Test
    public void cloneTest() {
        final T model = getObject();
        final T clone;
        clone = (T) model.clone();
        assertFalse(model == clone);
        assertEquals(model, clone);
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        assertNotNull(getObject());
    }

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract T getInstance();
}
