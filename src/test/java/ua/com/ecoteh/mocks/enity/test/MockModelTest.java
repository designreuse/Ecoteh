package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.model.Model;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.DEFAULT_SIZE;

public abstract class MockModelTest<T extends Model> {

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        final T model = getObject();
        assertNotNull(model);
    }

    @Test
    public void whenGetObjectsThenReturnSomeModels() {
        Collection<T> models = getObjects();
        assertNotNull(models);
        assertEquals(models.size(), DEFAULT_SIZE);
    }

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
