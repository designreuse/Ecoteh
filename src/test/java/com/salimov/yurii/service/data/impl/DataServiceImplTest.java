package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Model;
import com.salimov.yurii.service.data.interfaces.DataService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;
import static org.junit.Assert.*;

public abstract class DataServiceImplTest<T extends Model<E>, E extends Number> {

    @Test
    public void whenAddInvalidModelThenReturnsIt() {
        assertNull(
                getService().add(null)
        );
        assertNotNull(
                getService().add(
                        getInvalidObject()
                )
        );
    }

    @Test
    public void whenAddValidModelThenReturnsIt() {
        assertNotNull(
                getService().add(
                        getObject()
                )
        );
    }

    @Test
    public void whenAddAllInvalidModelsThenReturnsEmptyCollection() {
        assertTrue(
                getService()
                        .addAll(null)
                        .isEmpty()
        );
        assertTrue(
                getService()
                        .addAll(
                                new ArrayList<>()
                        ).isEmpty()
        );
    }

    @Test
    public void whenAddAllValidModelsThenReturnsIt() {
        assertFalse(
                getService()
                        .addAll(
                                getObjects()
                        ).isEmpty()
        );
    }

    @Test
    public void whenUpdateInvalidModelThenReturnsIt() {
        assertNull(
                getService()
                        .update((T) null)
        );
        final T invalidModel = getInvalidObject();
        assertEquals(
                invalidModel,
                getService()
                        .update(invalidModel)
        );
    }

    @Test
    public void whenUpdateValidModelThenReturnsUpdatingModel() {
        assertNotNull(
                getService().update(
                        getObject()
                )
        );
    }

    @Test
    public void whenUpdateAllInvalidModelThenReturnEmptyCollection() {
        final Collection<T> models1 = getService()
                .update((Collection<T>) null);
        assertNotNull(models1);
        assertTrue(models1.isEmpty());

        final Collection<T> models2 = getService()
                .update(new ArrayList<>());
        assertNotNull(models2);
        assertTrue(models2.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetModelByNullIdThenThrowIllegalArgumentException()
            throws IllegalAccessException {
        getService().get(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetModelByUnkownIdThenThrowNullPointerException()
            throws NullPointerException {
        getService().get((E) UNKNOWN_ID);
    }

    @Test
    public void whenGetAllThenReturnSomeModels() {
        final Collection<T> models = getService().getAll();
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAllWithValidValueThenReturnSomeModels() {
        final Collection<T> models1 = getService().getAll(true);
        assertNotNull(models1);
        assertFalse(models1.isEmpty());

        final Collection<T> models3 = getService().getAll(false);
        assertNotNull(models3);
        assertFalse(models3.isEmpty());
    }

    @Test
    public void whenExistsByNullIdThenReturnFalse() {
        assertFalse(
                getService()
                        .exists((E) null)
        );
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        assertFalse(
                getService()
                        .exists((E) UNKNOWN_ID)
        );
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        assertNotNull(
                getService()
                        .exists((E) ID)
        );
    }

    @Test
    public void whenExistsByNullModelThenReturnsFalse() {
        assertFalse(
                getService()
                        .exists((T) null)
        );
    }

    @Test
    public void whenExistsByModelWithIdThenReturnsFalse() {
        final T model = getObject();
        model.setId(null);
        assertFalse(
                getService()
                        .exists(model)
        );
    }

    @Test
    public void whenExistsByModelThenReturnsTrue() {
        assertTrue(
                getService()
                        .exists(
                                getObject()
                        )
        );
    }

    @Test
    public void whenSubListForEmptyCollectionsThenReturnsEmptyList() {
        final List<T> models1 = getService()
                .subList(null, 1, 1);
        assertNotNull(models1);
        assertTrue(models1.isEmpty());

        final List<T> models2 = getService()
                .subList(new ArrayList<>(), 1, 1);
        assertNotNull(models2);
        assertTrue(models2.isEmpty());
    }

    @Test
    public void whenSubListForInvalidIndexThenReturnEmptyList() {
        final Collection<T> models = getObjects();
        final List<T> models1 = getService().subList(models, 2, 1);
        assertNotNull(models1);
        assertFalse(models1.isEmpty());

        final List<T> models2 = getService().subList(
                models,
                models.size() + 1,
                models.size() + 2
        );
        assertNotNull(models2);
        assertFalse(models2.isEmpty());

        final List<T> models3 = getService().subList(
                models,
                1,
                models.size() + 1
        );
        assertNotNull(models3);
        assertFalse(models3.isEmpty());
    }

    @Test
    public void whenSubListWithAllValidParemetersThenReturnSubList() {
        List<T> models = getService().subList(
                getObjects(), 1, 2
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
        assertEquals(models.size(), 1);
    }

    @Test
    public void whenGetAndSubListForInvalidIndexThenReturnEmptyList() {
        final List<T> models1 = getService().getAndSubList(2, 1);
        assertNotNull(models1);
        assertFalse(models1.isEmpty());

        final List<T> models2 = getService().getAndSubList(
                Integer.MAX_VALUE - 1,
                Integer.MAX_VALUE
        );
        assertNotNull(models2);
        assertFalse(models2.isEmpty());

        final List<T> models3 = getService().getAndSubList(
                1, Integer.MAX_VALUE
        );
        assertNotNull(models3);
        assertFalse(models3.isEmpty());
    }

    @Test
    public void whenFilteredByValidForInvalidModelsThenReturnsEmptyList() {
        final List<T> models1 = getService().filteredByValid(null);
        assertNotNull(models1);
        assertTrue(models1.isEmpty());

        final List<T> models2 = getService().filteredByValid(
                new ArrayList<>()
        );
        assertNotNull(models2);
        assertTrue(models2.isEmpty());
    }

    @Test
    public void whenFilteredByValidForValidModelsThenReturnsEmptyList() {
        final List<T> temp = new ArrayList<>();
        for (T model : getObjects()) {
            model.setValidated(true);
            temp.add(model);
        }
        final List<T> models1 = getService().filteredByValid(temp);
        assertNotNull(models1);
        assertFalse(models1.isEmpty());
    }

    @Test
    public void whenGetAndSubListWithAllValidParemetersThenReturnSubList() {
        List<T> models = getService().getAndSubList(1, 2);
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Ignore
    protected abstract DataService<T, E> getService();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();

    @Ignore
    protected abstract T getInvalidObject();
}
