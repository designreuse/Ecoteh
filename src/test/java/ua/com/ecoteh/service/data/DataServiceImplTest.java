package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.entity.model.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class DataServiceImplTest<T extends Model> {

    @Test
    public void whenAddModelThenReturnNotNull() {
        final DataService<T> service = getService();
        final T model = getModel();
        final T savedModel = service.add(model);
        assertNotNull(savedModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddNullModelThenThrowIllegalArgumentException() {
        final DataService<T> service = getService();
        final T model = null;
        service.add(model);
    }

    @Test(expected = NullPointerException.class)
    public void whenAddUnknownModelThenThrowNullPointerException() {
        final DataService<T> service = getService();
        final T model = getUnknownModel();
        service.add(model);
    }

    @Test
    public void whenAddCollectionThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> savedModels = service.add(models);
        assertFalse(savedModels.isEmpty());
    }

    @Test
    public void whenAddCollectionThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> savedModels = service.add(models);
        savedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenAddNullCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> savedModels = service.add(models);
        assertTrue(savedModels.isEmpty());
    }

    @Test
    public void whenAddEmptyCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> savedModels = service.add(models);
        assertTrue(savedModels.isEmpty());
    }

    @Test
    public void whenUpdateModelThenReturnNotNull() {
        final DataService<T> service = getService();
        final T model = getModel();
        final T updatedModel = service.update(model);
        assertNotNull(updatedModel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateNullModelThenThrowIllegalArgumentException() {
        final DataService<T> service = getService();
        final T model = null;
        service.update(model);
    }

    @Test(expected = NullPointerException.class)
    public void whenUpdateUnknownModelThenThrowNullPointerException() {
        final DataService<T> service = getService();
        final T model = getUnknownModel();
        service.update(model);
    }

    @Test
    public void whenUpdateCollectionThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> savedModels = service.update(models);
        assertFalse(savedModels.isEmpty());
    }

    @Test
    public void whenUpdateCollectionThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> savedModels = service.update(models);
        savedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenUpdateNullCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> savedModels = service.update(models);
        assertTrue(savedModels.isEmpty());
    }

    @Test
    public void whenUpdateEmptyCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> savedModels = service.update(models);
        assertTrue(savedModels.isEmpty());
    }

    @Test
    public void whenGetByIdThenReturnNotNull() {
        final DataService<T> service = getService();
        final T model = service.get(ID);
        assertNotNull(model);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownIdThenThrownNullPointerException() {
        final DataService<T> service = getService();
        service.get(UNKNOWN_ID);
    }

    @Test
    public void whenGetAllThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = service.getAll();
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAllThenReturnCollectionNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = service.getAll();
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAllWithValidThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        Collection<T> models = service.getAll(true);
        assertFalse(models.isEmpty());
        models = service.getAll(false);
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetAllWithValidThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        Collection<T> models = service.getAll(true);
        models.forEach(Assert::assertNotNull);
        models = service.getAll(false);
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenRemoveByIdThenDoIt() {
        final DataService<T> service = getService();
        service.remove(ID);
    }

    @Test
    public void whenRemoveByModelThenDoIt() {
        final DataService<T> service = getService();
        final T model = getModel();
        service.remove(model);
    }

    @Test
    public void whenRemoveByNullModelThenDoNothing() {
        final DataService<T> service = getService();
        final T model = null;
        service.remove(model);
    }

    @Test
    public void whenRemoveByModelsThenDoIt() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        service.remove(models);
    }

    @Test
    public void whenRemoveByEmptyModelsThenDoNothing() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        service.remove(models);
    }

    @Test
    public void whenRemoveByNullModelsThenDoNothing() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        service.remove(models);
    }

    @Test
    public void whenRemoveAllThenDoIt() {
        final DataService<T> service = getService();
        service.removeAll();
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        final DataService<T> service = getService();
        assertTrue(service.exists(ID));
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        final DataService<T> service = getService();
        assertFalse(service.exists(UNKNOWN_ID));
    }

    @Test
    public void whenExistsByModelThenReturnTrue() {
        final DataService<T> service = getService();
        final T model = getModel();
        assertTrue(service.exists(model));
    }

    @Test
    public void whenExistsByNullModelThenReturnFalse() {
        final DataService<T> service = getService();
        final T model = null;
        assertFalse(service.exists(model));
    }

    @Test
    public void whenSortWithReversThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = getComparator();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertFalse(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenSortWithReversThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = getComparator();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        sortedModels.forEach(Assert::assertNotNull);
        sortedModels = service.sort(models, comparator, false);
        sortedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionWithReversThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Comparator<T> comparator = getComparator();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionWithReversThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Comparator<T> comparator = getComparator();
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortWithNullComparatorWithReversThenReturnIncomingCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = null;
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertEquals(sortedModels, models);
        sortedModels = service.sort(models, comparator, false);
        assertEquals(sortedModels, models);
    }

    @Test
    public void whenSortNullCollectionWithNullComparatorWithReversThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Comparator<T> comparator = null;
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionWithNullComparatorWithReversThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Comparator<T> comparator = null;
        Collection<T> sortedModels = service.sort(models, comparator, true);
        assertTrue(sortedModels.isEmpty());
        sortedModels = service.sort(models, comparator, false);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = getComparator();
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenSortThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = getComparator();
        final Collection<T> sortedModels = service.sort(models, comparator);
        sortedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSortNullCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Comparator<T> comparator = getComparator();
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Comparator<T> comparator = getComparator();
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortWithNullComparatorThenReturnIncomingCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Comparator<T> comparator = null;
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertEquals(sortedModels, models);
    }

    @Test
    public void whenSortNullCollectionWithNullComparatorThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Comparator<T> comparator = null;
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSortEmptyCollectionWithNullComparatorThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Comparator<T> comparator = null;
        final Collection<T> sortedModels = service.sort(models, comparator);
        assertTrue(sortedModels.isEmpty());
    }

    @Test
    public void whenSubListThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final int from = 0;
        final int to = models.size() / 2;
        final Collection<T> subModels = service.subList(models, from, to);
        assertFalse(subModels.isEmpty());
        assertTrue(subModels.size() == (to - from));
    }

    @Test
    public void whenSubListThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final int from = 0;
        final int to = models.size() / 2;
        final Collection<T> subModels = service.subList(models, from, to);
        subModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenSubNullListThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final int from = 0;
        final int to = 10;
        final Collection<T> subModels = service.subList(models, from, to);
        assertTrue(subModels.isEmpty());
    }

    @Test
    public void whenSubEmptyListThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final int from = 0;
        final int to = models.size() / 2;
        final Collection<T> subModels = service.subList(models, from, to);
        assertTrue(subModels.isEmpty());
    }

    @Test
    public void whenSubListWithInvalidIndexesThenReturnIncomingCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        int from = models.size() / 2;
        int to = 0;
        Collection<T> subModels = service.subList(models, from, to);
        assertEquals(subModels, models);
        from = -5;
        to = models.size() + 1;
        subModels = service.subList(models, from, to);
        assertEquals(subModels, models);
    }

    @Test
    public void whenGetAndSubListThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final int from = 0;
        final int to = 1;
        final Collection<T> sortedModels = service.getAndSubList(from, to);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenGetAndSubListThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final int from = 0;
        final int to = 1;
        final Collection<T> sortedModels = service.getAndSubList(from, to);
        sortedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetAndSubListWithInvalidIndexesThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        int from = -100;
        int to = 1550000;
        Collection<T> sortedModels = service.getAndSubList(from, to);
        assertFalse(sortedModels.isEmpty());
        from = -1;
        to = -2;
        sortedModels = service.getAndSubList(from, to);
        assertFalse(sortedModels.isEmpty());
    }

    @Test
    public void whenGetAndSubListWithInvalidIndexesThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        int from = -100;
        int to = 1550000;
        Collection<T> sortedModels = service.getAndSubList(from, to);
        sortedModels.forEach(Assert::assertNotNull);
        from = -1;
        to = -2;
        sortedModels = service.getAndSubList(from, to);
        sortedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> filteredModels = service.filterByValid(models);
        assertFalse(filteredModels.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> filteredModels = service.filterByValid(models);
        filteredModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> filteredModels = service.filterByValid(models);
        assertTrue(filteredModels.isEmpty());
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> filteredModels = service.filterByValid(models);
        assertTrue(filteredModels.isEmpty());
    }

    @Test
    public void whenShuffleThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getModels();
        final Collection<T> shuffledModels = service.shuffle(models);
        assertFalse(shuffledModels.isEmpty());
    }

    @Test
    public void whenShuffleNullCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> shuffledModels = service.shuffle(models);
        assertTrue(shuffledModels.isEmpty());
    }

    @Test
    public void whenShuffleEmptyCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> shuffledModels = service.shuffle(models);
        assertTrue(shuffledModels.isEmpty());
    }

    @Test
    public void whenGetServiceThenReturnNotNull() {
        final DataService<T> service = getService();
        assertNotNull(service);
    }

    @Test
    public void whenGetModelThenReturnNotNull() {
        final T model = getModel();
        assertNotNull(model);
    }

    @Test
    public void whenGetModelsThenReturnNotEmptyCollection() {
        final Collection<T> models = getModels();
        assertFalse(models.isEmpty());
    }

    @Test
    public void whenGetModelsThenReturnCollectionWithNotNullObjects() {
        final Collection<T> models = getModels();
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetUnknownModelThenReturnNotNull() {
        final T unknownModel = getUnknownModel();
        assertNotNull(unknownModel);
    }

    @Test
    public void whenGetComparatorThenReturnNotNull() {
        final Comparator<T> comparator = getComparator();
        assertNotNull(comparator);
    }

    protected abstract DataService<T> getService();

    protected abstract T getModel();

    protected abstract Collection<T> getModels();

    protected abstract T getUnknownModel();

    protected abstract Comparator<T> getComparator();
}
