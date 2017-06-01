package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.model.ModelEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.DEFAULT_SIZE;

public abstract class MockModelTest<T extends ModelEntity> {

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        final T category = getObject();
        assertNotNull(category);
    }

    @Test
    public void whenGetObjectsThenReturnSomeModels() {
        Collection<T> articles = getObjects();
        assertNotNull(articles);
        assertEquals(articles.size(), DEFAULT_SIZE);
    }

    @Ignore
    public abstract void whenGetModelThenReturnValidModel();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
