package com.salimov.yurii.mocks.dao.test;


import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.mocks.MockConstants;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public abstract class MockDataDAOTest<T extends Model<Long>> {

    @Test
    public void whenGetMockDaoThenReturnNotNull() {
        assertNotNull(getDao());
    }

    @Test
    public void whenGetObjectThenReturnSomeObject() {
        assertNotNull(getObject());
    }

    @Test
    public void whenGetObjectsThenReturnSomeObjects() {
        assertNotNull(getObjects());
    }

    @Test
    public void whenAddInvalidModelThenReturnNull() {
        T model = (T) getDao().add(null);
        assertNull(model);
    }

    @Test
    public void whenAddValidModelThenReturnThisModel() {
        T model1 = getObject();
        T model2 = (T) getDao().add(model1);
        assertNotNull(model2);
        assertEquals(model1, model2);
    }

    @Test
    public void whenAddAllInvalidModelThenReturnNull() {
        Collection<T> models = getDao().addAll(null);
        assertNull(models);
    }

    @Test
    public void whenAddAllValidModelThenReturnThisModels() {
        Collection<T> models1 = getObjects();
        Collection<T> models2 = getDao().addAll(models1);
        assertNotNull(models2);
    }

    @Test
    public void whenUpdateInvalidModelThenReturnNull() {
        T model = (T) getDao().update(null);
        assertNull(model);
    }

    @Test
    public void whenUpdateValidModelThenReturnThisModel() {
        T model1 = getObject();
        T model2 = (T) getDao().update(model1);
        assertNotNull(model2);
        assertEquals(model1, model2);
    }

    @Test
    public void whenGetByInvalidIdThenReturnNull() {
        T model = (T) getDao().get(null);
        assertNull(model);

        model = (T) getDao().get(MockConstants.UNKNOWN_ID);
        assertNull(model);
    }

    @Test
    public void whenGetByValidIdThenReturnSomeModel() {
        T model = (T) getDao().get(MockConstants.ID);
        assertNotNull(model);
    }

    @Test
    public void whenGetAllModelsThenReturnThem() {
        Collection<T> models = getDao().getAll();
        assertNotNull(models);
    }

    @Test
    public void whenExistsByInvalidIdThenReturnFalse() {
        boolean value = getDao().exists(null);
        assertFalse(value);

        value = getDao().exists(MockConstants.UNKNOWN_ID);
        assertFalse(value);
    }

    @Test
    public void whenExistsByValidIdThenReturnTrue() {
        boolean value = getDao().exists(MockConstants.ID);
        assertTrue(value);
    }

    @Ignore
    protected abstract DataDao getDao();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
