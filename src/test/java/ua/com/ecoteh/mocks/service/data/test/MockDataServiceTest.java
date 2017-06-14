package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.service.data.DataService;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;

public abstract class MockDataServiceTest<T extends Model> {

    @Test
    public void whenAddModelThenReturnNotNull() {
        final DataService<T> service = getService();
        final T model = getObject();
        T savingModel = service.add(model);
        assertNotNull(savingModel);
        final Collection<T> models = getObjects();
        for (T m : models) {
            savingModel = service.add(m);
            assertNotNull(savingModel);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenAddNullThenThrowIllegalArgumentException() {
        final DataService<T> service = getService();
        final T model = null;
        service.add(model);
    }

    @Test
    public void whenAddModelsThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> savedModels = service.add(models);
        assertFalse(savedModels.isEmpty());
    }

    @Test
    public void whenAddModelsThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> savedModels = service.add(models);
        savedModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenUpdateModelThenReturnNotNull() {
        final DataService<T> service = getService();
        final T model = getObject();
        T updatedModel = service.update(model);
        assertNotNull(updatedModel);
        final Collection<T> models = getObjects();
        for (T m : models) {
            updatedModel = service.update(m);
            assertNotNull(updatedModel);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateNullModelThenThrowIllegalArgumentException() {
        final DataService<T> service = getService();
        final T model = null;
        service.update(model);
    }

    @Test
    public void whenUpdateModelsThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> updatedModel = service.update(models);
        assertFalse(updatedModel.isEmpty());
    }

    @Test
    public void whenUpdateModelsThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> updatedModels = service.update(models);
        updatedModels.forEach(Assert::assertNotNull);
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
    public void whenGetAllThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        Collection<T> models = service.getAll();
        models.forEach(Assert::assertNotNull);
        models = service.getAll(true);
        models.forEach(Assert::assertNotNull);
        models = service.getAll(false);
        models.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenExistsByModelThenReturnTrue() {
        final DataService<T> service = getService();
        final T model = getObject();
        assertTrue(service.exists(model));
        final Collection<T> models = getObjects();
        for(T m : models) {
            assertTrue(service.exists(m));
        }
    }

    @Test
    public void whenExistsByNullModelThenReturnFalse() {
        final DataService<T> service = getService();
        final T model = null;
        assertFalse(service.exists(model));
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
    public void whenSubListModelsThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> result = service.subList(models, INDEX, INDEX);
        assertFalse(result.isEmpty());
    }

    @Test
    public void whenSubListModelsThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> result = service.subList(models, INDEX, INDEX);
        for (T model : result) {
            assertNotNull(model);
        }
    }

    @Test
    public void whenSubListWithEmptyModelsThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> result = service.subList(models, INDEX, INDEX);
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenSubListWithNullModelsThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> result = service.subList(models, INDEX, INDEX);
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenGetAndSubListModelsThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> result = service.getAndSubList(INDEX, INDEX);
        assertFalse(result.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> filteredModels = service.filterByValid(models);
        assertFalse(filteredModels.isEmpty());
    }

    @Test
    public void whenFilterByValidThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> filteredModels = service.filterByValid(models);
        filteredModels.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenFilterEmptyCollectionByValidThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> filteredModels = service.filterByValid(models);
        assertTrue(filteredModels.isEmpty());
    }

    @Test
    public void whenFilterNullCollectionByValidThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> filteredModels = service.filterByValid(models);
        assertTrue(filteredModels.isEmpty());
    }

    @Test
    public void whenShuffleCollectionThenReturnNotEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> result = service.shuffle(models);
        assertFalse(result.isEmpty());
    }

    @Test
    public void whenShuffleCollectionThenReturnCollectionWithNotNullObjects() {
        final DataService<T> service = getService();
        final Collection<T> models = getObjects();
        final Collection<T> result = service.shuffle(models);
        result.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenShuffleEmptyCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = new ArrayList<>();
        final Collection<T> result = service.shuffle(models);
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenShuffleNullCollectionThenReturnEmptyCollection() {
        final DataService<T> service = getService();
        final Collection<T> models = null;
        final Collection<T> result = service.shuffle(models);
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenGetServiceThenReturnNotNull() {
        final DataService<T> service = getService();
        assertNotNull(service);
    }

    @Test
    public void whenGetObjectThenReturnNotNull() {
        final T model = getObject();
        assertNotNull(model);
    }

    @Test
    public void whenGetObjectsThenReturnCollectionWithNotNullObjects() {
        final Collection<T> models = getObjects();
        models.forEach(Assert::assertNotNull);
    }

    @Ignore
    protected abstract DataService<T> getService();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
