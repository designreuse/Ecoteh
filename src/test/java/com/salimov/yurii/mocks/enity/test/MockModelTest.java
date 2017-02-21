package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.Model;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.MockConstants.DEFAULT_SIZE;

public abstract class MockModelTest<T extends Model> {

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
