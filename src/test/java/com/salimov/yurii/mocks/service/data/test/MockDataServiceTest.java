package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Model;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.service.data.interfaces.DataService;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.UNKNOWN_ID;

public abstract class MockDataServiceTest<T extends Model<Long>> {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        DataService dataService = getService();
        assertNotNull(dataService);
    }

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        T model = getObject();
        assertNotNull(model);
    }

    @Test
    public void whenGetObjectsThenReturnSomeModels() {
        Collection<T> models = getObjects();
        assertNotNull(models);
    }

    @Test
    public void whenAddModelThenReturnThisModel() {
        T model1 = getObject();
        T model2 = (T) getService().add(model1);
        assertNotNull(model2);
    }

    @Test
    public void whenAddModelsThenReturnThisModels() {
        Collection<T> models1 = getObjects();
        Collection<T> models2 = getService().add(models1);
        assertNotNull(models2);
    }

    @Test
    public void whenUpdateModelThenReturnThisModel() {
        T model1 = getObject();
        getService().add(model1);
        T model2 = (T) getService().update(model1);
        assertNotNull(model2);
    }

    @Test
    public void whenUpdateModelsThenReturnThisModels() {
        Collection<T> models1 = getObjects();
        Collection<T> models2 = getService().update(models1);
        assertNotNull(models2);
    }

    @Test
    public void whenGetByValidIdThenReturnSomeModel() {
        T model = (T) getService().get(ID);
        assertNotNull(model);
    }

    @Test
    public void whenGetAllThenReturnSomeModels() {
        Collection<T> models = getService().getAll();
        assertNotNull(models);

        models = getService().getAll(false);
        assertNotNull(models);

        models = getService().getAll(true);
        assertNotNull(models);
    }

    @Test
    public void whenExistsByInvalidModelThenReturnFalse() {
        boolean value = getService().exists((Model) null);
        assertFalse(value);
    }

    @Test
    public void whenExistsByValidModelThenReturnTrue() {
        T model = getObject();
        boolean value = getService().exists(model);
        assertTrue(value);
    }

    @Test
    public void whenExistsByInvalidIDThenReturnFalse() {
        boolean value = getService().exists((Long) null);
        assertFalse(value);

        value = getService().exists(UNKNOWN_ID);
        assertFalse(value);
    }

    @Test
    public void whenExistsByValidIDThenReturnTrue() {
        boolean value = getService().exists(ID);
        assertTrue(value);
    }

    @Test
    public void whenSortingCollectionThenReturnThisSortCollection() {

    }

    @Ignore
    protected abstract DataService getService();

    @Ignore
    protected abstract T getObject();

    @Ignore
    protected abstract Collection<T> getObjects();
}
