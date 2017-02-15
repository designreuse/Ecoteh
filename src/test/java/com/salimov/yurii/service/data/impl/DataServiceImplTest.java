package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Model;
import com.salimov.yurii.service.data.interfaces.DataService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;
import static org.junit.Assert.*;

public abstract class DataServiceImplTest<T extends Model<E>, E extends Number> {

    @Test
    public void whenAddNullThenReturnsIt() {
        assertNull(getService().add(null));
    }

    @Test
    public void whenAddInvalidModelThenReturnsIt() {
        assertNull(getService().add(null));
        assertNotNull(getService().add(getInvalidObject()));
    }

    @Test
    public void whenAddValidModelThenReturnsIt() {
        assertNotNull(getService().add(getObject()));
    }

    @Test
    public void whenAddAllNullThenReturnsEmptyCollection() {
        assertTrue(getService().addAll(null).isEmpty());
    }

    @Test
    public void whenAddAllEmptyListThenReturnsEmptyCollection() {
        assertTrue(getService().addAll(new ArrayList<>()).isEmpty());
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
        assertNull(getService().update((T) null));
    }

    @Test
    public void whenUpdateInvalidModelThenReturnsIt() {
        final T invalidModel = getInvalidObject();
        assertEquals(
                invalidModel,
                getService().update(invalidModel)
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
        assertTrue(getService().update((Collection<T>) null).isEmpty());
    }

    @Test
    public void whenUpdateAllInvalidModelThenReturnEmptyCollection() {
        assertTrue(
                getService().update(
                        new ArrayList<>()
                ).isEmpty()
        );
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
        assertFalse(getService().getAll().isEmpty());
    }

    @Test
    public void whenGetAllWithFalseValidThenReturnSomeModels() {
        assertFalse(getService().getAll(false).isEmpty());
    }

    @Test
    public void whenGetAllWithTrueValidThenReturnSomeModels() {
        assertFalse(getService().getAll(true).isEmpty());
    }

    @Test
    public void whenExistsByNullIdThenReturnFalse() {
        assertFalse(getService().exists((E) null));
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        assertFalse(getService().exists((E) UNKNOWN_ID));
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        assertNotNull(getService().exists((E) ID));
    }

    @Test
    public void whenExistsByNullModelThenReturnsFalse() {
        assertFalse(getService().exists((T) null));
    }

    @Test
    public void whenExistsByModelWithIdThenReturnsFalse() {
        final T model = getObject();
        model.setId(null);
        assertFalse(getService().exists(model));
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
        assertTrue(
                getService()
                        .subList(null, 1, 1)
                        .isEmpty()
        );
    }

    @Test
    public void whenSubListForEmptyCollectionsThenReturnsEmptyList() {
        assertTrue(
                getService().subList(
                        new ArrayList<>(), 1, 1
                ).isEmpty()
        );
    }

    @Test
    public void whenSubListWithFromIndexGreatToIndexThenReturnEmptyList() {
        assertFalse(
                getService().subList(
                        getObjects(), 2, 1
                ).isEmpty()
        );
    }

    @Test
    public void whenSubListWithMoreBigIndexThenReturnEmptyList() {
        final Collection<T> objects = getObjects();
        assertFalse(
                getService().subList(
                        objects,
                        objects.size() + 1,
                        objects.size() + 2
                ).isEmpty()
        );
    }

    @Test
    public void whenSubListWithMoreBigToIndexThenReturnEmptyList() {
        final Collection<T> objects = getObjects();
        assertFalse(
                getService().subList(
                        objects,
                        1, objects.size() + 1
                ).isEmpty()
        );
    }

    @Test
    public void whenSubListWithAllValidIndexThenReturnSubList() {
        assertEquals(
                getService().subList(
                        getObjects(), 1, 2
                ).size(),
                1
        );
    }

    @Test
    public void whenGetAndSubListWithFromIndexGreatToIndexThenReturnEmptyList() {
        assertFalse(
                getService()
                        .getAndSubList(2, 1)
                        .isEmpty()
        );
    }

    @Test
    public void whenGetAndSubListWithMoreBigIndexThenReturnEmptyList() {
        assertFalse(
                getService().getAndSubList(
                        Integer.MAX_VALUE - 1,
                        Integer.MAX_VALUE
                ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSubListWithMoreBigToIndexThenReturnEmptyList() {
        assertFalse(
                getService().getAndSubList(
                        1, Integer.MAX_VALUE
                ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidForNullThenReturnsEmptyList() {
        assertTrue(
                getService()
                        .filteredByValid(null)
                        .isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidForEmptyListThenReturnsEmptyList() {
        assertTrue(
                getService()
                        .filteredByValid(
                                new ArrayList<>()
                        ).isEmpty()
        );
    }

    @Test
    public void whenFilteredByValidForValidModelsThenReturnsEmptyList() {
        assertFalse(
                getService()
                        .filteredByValid(
                                getObjects()
                        ).isEmpty()
        );
    }

    @Test
    public void whenGetAndSubListWithAllValidParametersThenReturnSubList() {
        assertFalse(
                getService()
                        .getAndSubList(1, 2)
                        .isEmpty()
        );
    }

    @Test
    public void whenRemoveByNullIdThenDoNothing() {
        getService().remove((E) null);
    }

    @Test
    public void whenRemoveByIdThenDoIt() {
        getService().remove((E) ID);
    }

    @Test
    public void whenRemoveByNullModelThenDoNothing() {
        getService().remove((T) null);
    }

    @Test
    public void whenRemoveByModelThenDoIt() {
        getService().remove(getObject());
    }

    @Test
    public void whenRemoveByNullModelsThenDoNothing() {
        getService().remove((Collection<T>) null);
    }

    @Test
    public void whenRemoveByEmptyModelsThenDoNothing() {
        getService().remove(new ArrayList<>());
    }

    @Test
    public void whenRemoveByModelsThenDoIt() {
        getService().remove(getObjects());
    }

    @Test
    public void whenRemoveAllThenDoIt() {
        getService().removeAll();
    }

    @Test
    public void whenGetServiceThenReturnNotNull() {
        assertNotNull(getService());
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        assertNotNull(getObject());
    }

    @Test
    public void whenGetObjectsThenReturnNotEmptyCollecton() {
        assertFalse(getObjects().isEmpty());
    }

    @Test
    public void whenGetInvalidObjectThenReturnNotNull() {
        assertNotNull(getInvalidObject());
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
