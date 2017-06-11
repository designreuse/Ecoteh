package ua.com.ecoteh.mocks.enity.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class MockModelEntityTest<E extends ModelEntity> {

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        final E model = getObject();
        assertNotNull(model);
    }

    @Test
    public void whenGetObjectsThenReturnNotEmpty() {
        final Collection<E> models = getObjects();
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetObjectsThenReturnCollectionsWithNotNullObjects() {
        final Collection<E> models = getObjects();
        models.forEach(Assert::assertNotNull);
    }

    @Ignore
    protected abstract E getObject();

    @Ignore
    protected abstract Collection<E> getObjects();
}
