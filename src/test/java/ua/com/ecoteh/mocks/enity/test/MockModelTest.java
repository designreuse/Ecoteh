package ua.com.ecoteh.mocks.enity.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.model.Model;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public abstract class MockModelTest<T extends Model> {

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        final T model = getObject();
        assertNotNull(model);
    }

    @Test
    public void whenGetObjectsThenReturnNotEmpty() {
        final Collection<T> models = getObjects();
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetObjectsThenReturnCollectionsWithNotNullObjects() {
        final Collection<T> models = getObjects();
        models.forEach(Assert::assertNotNull);
    }

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
