package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Model;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public abstract class DataServiceImplTest<T extends Model> {

    @Test(expected = NullPointerException.class)
    public void whenAddNullThenThrowException() {
        assertNull(getService().add((T) null));
    }

    @Test(expected = NullPointerException.class)
    public void whenAddInvalidModelThenThrowException() {
        assertNull(getService().add((T) null));
    }

    @Test
    public void whenAddValidModelThenReturnsIt() {
        assertNotNull(getService().add(getObject()));
    }

    @Test
    public void whenAddAllNullThenReturnsEmptyCollection() {
        assertTrue(getService().add((Collection<T>) null).isEmpty());
    }

    @Test
    public void whenAddAllEmptyListThenReturnsEmptyCollection() {
        assertTrue(getService().add(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenAddAllValidModelsThenReturnsIt() {
        assertFalse(getService().add(getObjects()).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void whenUpdateNullThenThrowException() {
        assertNull(getService().update((T) null));
    }

    @Test
    public void whenUpdateInvalidModelThenReturnsIt() {
        final T invalidModel = getInvalidObject();
        assertEquals(invalidModel, getService().update(invalidModel));
    }

    @Test
    public void whenUpdateValidModelThenReturnsUpdatingModel() {
        assertNotNull(getService().update(getObject()));
    }

    @Test
    public void whenUpdateAllNullThenReturnEmptyCollection() {
        assertTrue(getService().update((Collection<T>) null).isEmpty());
    }

    @Test
    public void whenUpdateAllInvalidModelThenReturnEmptyCollection() {
        assertTrue(getService().update(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenUpdateModelsThenReturnSomeCollection() {
        assertFalse(getService().update(getObjects()).isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void whenGetModelByUnknownIdThenThrowNullPointerException()
            throws NullPointerException {
        getService().get(MockConstants.UNKNOWN_ID);
    }

    @Test
    public void whenGetAllThenReturnSomeList() {
        assertFalse(getService().getAll().isEmpty());
    }

    @Test
    public void whenGetAllWithFalseValidThenReturnSomeModels() {
        assertFalse(getService().getAll(false).isEmpty());
    }

    @Test
    public void whenGetAllWithTrueValidThenReturnSomeList() {
        assertFalse(getService().getAll(true).isEmpty());
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        assertFalse(getService().exists(MockConstants.UNKNOWN_ID));
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        assertNotNull(getService().exists(MockConstants.ID));
    }

    @Test
    public void whenExistsByNullModelThenReturnsFalse() {
        assertFalse(getService().exists(null));
    }

    @Test
    public void whenExistsByModelThenReturnsTrue() {
        assertTrue(getService().exists(getObject()));
    }

    @Test
    public void whenSubListForNullThenReturnsEmptyList() {
        assertTrue(getService().subList(null, 1, 1).isEmpty());
    }

    @Test
    public void whenSubListForEmptyCollectionsThenReturnsEmptyList() {
        assertTrue(getService().subList(new ArrayList<>(), 1, 1).isEmpty());
    }

    @Test
    public void whenSubListWithFromIndexGreatToIndexThenReturnEmptyList() {
        assertFalse(getService().subList(getObjects(), 2, 1).isEmpty());
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
        assertFalse(getService().subList(objects, 1, objects.size() + 1).isEmpty());
    }

    @Test
    public void whenSubListWithAllValidIndexThenReturnSubList() {
        assertEquals(getService().subList(getObjects(), 1, 2).size(), 1);
    }

    @Test
    public void whenGetAndSubListWithFromIndexGreatToIndexThenReturnAllList() {
        assertFalse(getService().getAndSubList(2, 1).isEmpty());
    }

    @Test
    public void whenGetAndSubListWithMoreBigIndexThenReturnSomeList() {
        assertFalse(getService().getAndSubList(Integer.MAX_VALUE - 1, Integer.MAX_VALUE).isEmpty());
    }

    @Test
    public void whenGetAndSubListWithMoreBigToIndexThenReturnAllList() {
        assertFalse(getService().getAndSubList(1, Integer.MAX_VALUE).isEmpty());
    }

    @Test
    public void whenFilteredByValidForNullThenReturnsEmptyList() {
        assertTrue(getService().filteredByValid(null).isEmpty());
    }

    @Test
    public void whenFilteredByValidForEmptyListThenReturnsEmptyList() {
        assertTrue(getService().filteredByValid(new ArrayList<>()).isEmpty());
    }

    @Test
    public void whenFilteredByValidForValidModelsThenReturnsSomeList() {
        assertFalse(getService().filteredByValid(getObjects()).isEmpty());
    }

    @Test
    public void whenGetAndSubListWithAllValidParametersThenReturnSubList() {
        assertFalse(getService().getAndSubList(1, 2).isEmpty());
    }

    @Test
    public void whenRemoveByIdThenDoIt() {
        getService().remove(MockConstants.ID);
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
    public void whenGetObjectsThenReturnNotEmptyCollection() {
        assertFalse(getObjects().isEmpty());
    }

    @Test
    public void whenGetInvalidObjectThenReturnNotNull() {
        assertNotNull(getInvalidObject());
    }

    @Test
    public void whenSortWithNullComparatorThenReturnSomeList() {
        assertFalse(getService().sort(getObjects(), null).isEmpty());
    }

    @Test
    public void whenShuffleThenReturnSomeList() {
        assertFalse(getService().shuffle(getObjects()).isEmpty());
    }

    @Ignore
    protected abstract DataService<T> getService();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();

    @Ignore
    protected abstract T getInvalidObject();
}
