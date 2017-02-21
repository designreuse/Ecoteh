package com.salimov.yurii.mocks.dao.test;


import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Model;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;
import static org.junit.Assert.*;

public abstract class MockDataDAOTest<T extends Model> {

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
        assertNull(getDao().add(null));
    }

    @Test
    public void whenAddValidModelThenReturnThisModel() {
        assertNotNull(getDao().add(getObject()));
    }

    @Test
    public void whenAddAllInvalidModelThenReturnNull() {
        assertNull(getDao().addAll(null));
    }

    @Test
    public void whenAddAllValidModelThenReturnThisModels() {
        assertNotNull(getDao().addAll(getObjects()));
    }

    @Test
    public void whenUpdateInvalidModelThenReturnNull() {
        assertNull(getDao().update(null));
    }

    @Test
    public void whenUpdateValidModelThenReturnThisModel() {
        assertNotNull(getDao().update(getObject()));
    }

    @Test
    public void whenGetByValidIdThenReturnSomeModel() {
        assertNotNull(getDao().get(ID));
    }

    @Test
    public void whenGetAllModelsThenReturnThem() {
        assertFalse(getDao().getAll().isEmpty());
    }

    @Test
    public void whenExistsByInvalidIdThenReturnFalse() {
        assertFalse(getDao().exists(UNKNOWN_ID));
    }

    @Test
    public void whenExistsByValidIdThenReturnTrue() {
        assertTrue(getDao().exists(ID));
    }

    @Ignore
    protected abstract DataDao getDao();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
