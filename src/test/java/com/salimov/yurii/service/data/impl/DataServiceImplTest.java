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
    public void whenAddNullThenReturnsIt() {
        assertNull(
                getService().add(null)
        );
    }

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
    public void whenAddAllNullThenReturnsEmptyCollection() {
        assertTrue(
                getService()
                        .addAll(null)
                        .isEmpty()
        );
    }

    @Test
    public void whenAddAllEmptyListThenReturnsEmptyCollection() {
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
    public void whenUpdateNullThenReturnsIt() {
        assertNull(
                getService()
                        .update((T) null)
        );
    }

    @Test
    public void whenUpdateInvalidModelThenReturnsIt() {
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
    public void whenUpdateAllNullThenReturnEmptyCollection() {
        final Collection<T> models = getService().update(
                (Collection<T>) null
        );
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void whenUpdateAllInvalidModelThenReturnEmptyCollection() {
        final Collection<T> models = getService().update(
                new ArrayList<>()
        );
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetModelByNullIdThenThrowIllegalArgumentException()
            throws IllegalAccessException {
        getService().get(null);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetModelByUnknownIdThenThrowNullPointerException()
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
    public void whenGetAllWithFalseValidThenReturnSomeModels() {
        final Collection<T> models = getService().getAll(false);
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAllWithTrueValidThenReturnSomeModels() {
        final Collection<T> models = getService().getAll(true);
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenExistsByNullIdThenReturnFalse() {
        assertFalse(
                getService().exists((E) null)
        );
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        assertFalse(
                getService().exists((E) UNKNOWN_ID)
        );
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        assertNotNull(
                getService().exists((E) ID)
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
                getService().exists(model)
        );
    }

    @Test
    public void whenExistsByModelThenReturnsTrue() {
        assertTrue(
                getService().exists(
                        getObject()
                )
        );
    }

    @Test
    public void whenSubListForNullThenReturnsEmptyList() {
        final List<T> models = getService().subList(null, 1, 1);
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void whenSubListForEmptyCollectionsThenReturnsEmptyList() {
        final List<T> models = getService().subList(
                new ArrayList<>(), 1, 1
        );
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void whenSubListWithFromIndexGreatToIndexThenReturnEmptyList() {
        final List<T> models = getService().subList(
                getObjects(), 2, 1
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenSubListWithMoreBigIndexThenReturnEmptyList() {
        final Collection<T> objects = getObjects();
        final List<T> models = getService().subList(
                objects,
                objects.size() + 1,
                objects.size() + 2
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenSubListWithMoreBigToIndexThenReturnEmptyList() {
        final Collection<T> objects = getObjects();
        final List<T> models = getService().subList(
                objects,
                1, objects.size() + 1
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenSubListWithAllValidIndexThenReturnSubList() {
        List<T> models = getService().subList(
                getObjects(), 1, 2
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
        assertEquals(models.size(), 1);
    }

    @Test
    public void whenGetAndSubListWithFromIndexGreatToIndexThenReturnEmptyList() {
        final List<T> models = getService().getAndSubList(2, 1);
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAndSubListWithMoreBigIndexThenReturnEmptyList() {
        final List<T> models = getService().getAndSubList(
                Integer.MAX_VALUE - 1,
                Integer.MAX_VALUE
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAndSubListWithMoreBigToIndexThenReturnEmptyList() {
        final List<T> models = getService().getAndSubList(
                1, Integer.MAX_VALUE
        );
        assertNotNull(models);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenFilteredByValidForNullThenReturnsEmptyList() {
        final List<T> models = getService().filteredByValid(null);
        assertNotNull(models);
        assertTrue(models.isEmpty());
    }

    @Test
    public void whenFilteredByValidForEmptyListThenReturnsEmptyList() {
        final List<T> models = getService().filteredByValid(
                new ArrayList<>()
        );
        assertNotNull(models);
        assertTrue(models.isEmpty());
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

    @Test
    public void whenGetServiceThenReturnNotNull() {
        assertNotNull(
                getService()
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

    @Test
    public void whenGetInvalidObjectThenReturnNotNull() {
        assertNotNull(
                getInvalidObject()
        );
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
