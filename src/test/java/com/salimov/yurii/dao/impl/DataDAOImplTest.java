package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Model;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public abstract class DataDAOImplTest<T extends Model<Long>> {

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void whenAddNullThenThrowException() {
        getDao().add(null);
    }

    @Test
    @Transactional
    public void whenAddValidModelThenReturnThisModel() {
        final T model1 = getObject();
        final T model2 = (T) getDao().add(model1);
        assertNotNull(model2);
        assertEquals(model1, model2);
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
        final Collection<T> models1 = getObjects();
        final Collection<T> models2 = getDao().addAll(models1);
        assertNotNull(models2);
        assertEquals(models1, models2);
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void whenUpdateNullThenThrowException() {
        getDao().update(null);
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

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void whenGetByInvalidIdThenThrowException() {
        getDao().get(null);
    }

    @Test
    @Transactional
    public void whenGetByIdThenReturnSomeModel() {
        T model1 = (T) getDao()
                .add(
                        getObject()
                );
        final T model2 = (T) getDao()
                .get(
                        model1.getId()
                );
        assertNotNull(model2);
    }

    @Test
    @Transactional
    public void whenGetAllThenReturnSomeModels() {
        assertNotNull(
                getDao().getAll()
        );
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void whenExistsByInvalidIdThenThrowException() {
        getDao().exists(null);
    }

    @Test
    @Transactional
    public void whenExistsByIdThenReturnBoolean() {
        final T model1 = getObject();
        model1.setId(Long.MAX_VALUE);
        assertFalse(
                getDao()
                        .exists(
                                model1.getId()
                        )
        );

        final T model2 = (T) getDao().add(model1);
        assertTrue(
                getDao()
                        .exists(
                                model2.getId()
                        )
        );
    }

    @Test
    @Transactional
    public void whenRemoveById() {
        final T model1 = getObject();
        assertFalse(
                getDao()
                        .exists(
                                model1.getId()
                        )
        );
        final T model2 = (T) getDao().add(model1);
        assertTrue(
                getDao()
                        .exists(
                                model2.getId()
                        )
        );
        getDao().remove(model2.getId());
        assertFalse(
                getDao()
                        .exists(
                                model2.getId()
                        )
        );
    }

    @Test
    @Transactional
    public void whenRemoveByModel() {
        final T model1 = getObject();
        model1.setId(Long.MAX_VALUE);
        assertFalse(
                getDao()
                        .exists(
                                model1.getId()
                        )
        );
        final T model2 = (T) getDao().add(model1);
        assertTrue(
                getDao()
                        .exists(
                                model2.getId()
                        )
        );
        getDao().remove(model2);
        assertFalse(
                getDao()
                        .exists(
                                model2.getId()
                        )
        );
    }

    @Test
    @Transactional
    public void whenRemoveByModels() {
        final T model = getObject();
        model.setId(Long.MAX_VALUE);
        final Collection<T> models1 = new ArrayList<>();
        models1.add(model);
        assertFalse(
                getDao()
                        .getAll()
                        .containsAll(models1)
        );
        final Collection<T> models2 = getDao().addAll(models1);
        assertTrue(
                getDao()
                        .getAll()
                        .containsAll(models2)
        );
        getDao().remove(models2);
        assertFalse(
                getDao()
                        .getAll()
                        .containsAll(models2)
        );
    }

    @Test
    @Transactional
    public void whenRemoveAll() {
        final T model = getObject();
        model.setId(Long.MAX_VALUE);
        final Collection<T> models = new ArrayList<>();
        models.add(model);
        getDao().addAll(models);
        final Collection<T> all = getDao().getAll();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        getDao().removeAll();
    }

    @Test
    public void whenGetDaoThenReturnNotNull() {
        assertNotNull(getDao());
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        assertNotNull(getObject());
    }

    @Test
    public void whenGetObjectThenReturnNotNullAndNotEmptyCollection() {
        final Collection<T> models = getObjects();
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Ignore
    protected abstract DataDao getDao();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
