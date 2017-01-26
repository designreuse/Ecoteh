package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Model;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;
import static org.junit.Assert.*;

public abstract class DataDAOImplTest<T extends Model<E>, E extends Number> {

    @Test
    public void whenAddNullThenReturnNull() {
        assertNull(
                getDao().add(null)
        );

    }

    @Test
    @Transactional
    public void whenAddValidModelThenReturnThisModel() {
        assertNotNull(
                getDao().add(getObject())
        );
    }

    @Test
    @Transactional
    public void whenAddNullsThenReturnEmptyCollection() {
        final Collection<T> models = getDao().addAll(null);
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    @Transactional
    public void whenAddModelsThenReturnThisModels() {
        final Collection<T> models = getDao().addAll(getObjects());
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenUpdateNullThenRetunrsNull() {
        assertNull(
                getDao().update(null)
        );
    }

    @Test
    @Transactional
    public void whenUpdateModelThenReturnThisModel() {
        assertNotNull(
                getDao()
                        .update(
                                getObject()
                        )
        );
    }

    @Test
    public void whenGetByInvalidIdThenReturnNull() {
        assertNull(
                getDao().get(null)
        );
    }

    @Test
    @Transactional
    public void whenGetByIdThenReturnSomeModel() {
        assertNotNull(
                getDao().get((E) ID)
        );
    }

    @Test
    @Transactional
    public void whenGetAllThenReturnSomeModels() {
        assertNotNull(
                getDao().getAll()
        );
    }

    @Test
    public void whenExistsByInvalidIdThenReturnsFalse() {
        assertFalse(
                getDao().exists(null)
        );
    }

    @Test
    @Transactional
    public void whenExistsByIdThenReturnBoolean() {
        assertTrue(
                getDao()
                        .exists((E) ID)
        );
        assertFalse(
                getDao()
                        .exists((E) UNKNOWN_ID)
        );
        assertFalse(
                getDao()
                        .exists(null)
        );
    }

    @Test
    public void whenGetDaoThenReturnNotNull() {
        assertNotNull(
                getDao()
        );
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        assertNotNull(
                getObject()
        );
    }

    @Test
    public void whenGetObjectsThenReturnNotEmptyCollecton() {
        assertFalse(
                getObjects().isEmpty()
        );
    }

    @Ignore
    protected abstract DataDao<T, E> getDao();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
