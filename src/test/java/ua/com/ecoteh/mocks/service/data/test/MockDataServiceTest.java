package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Model;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.service.data.DataService;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;

public abstract class MockDataServiceTest<T extends Model> {

    @Test
    public void whenGetMockServiceThenReturnNotNull() {
        assertNotNull(getService());
    }

    @Test
    public void whenGetObjectThenReturnSomeModel() {
        assertNotNull(getObject());
    }

    @Test
    public void whenGetObjectsThenReturnSomeModels() {
        assertNotNull(getObjects());
    }

    @Test
    public void whenAddModelThenReturnThisModel() {
        assertNotNull(getService().add(getObject()));
    }

    @Test
    public void whenAddModelsThenReturnThisModels() {
        assertNotNull(
                getService().addAll(getObjects())
        );
    }

    @Test
    public void whenUpdateModelThenReturnThisModel() {
        assertNotNull(getService().update(getService().add(getObject())));
    }

    @Test
    public void whenUpdateModelsThenReturnThisModels() {
        assertNotNull(getService().update(getObjects()));
    }

    @Test
    public void whenGetByValidIdThenReturnSomeModel() {
        assertNotNull(getService().get(ID));
    }

    @Test
    public void whenGetAllThenReturnSomeModels() {
        assertNotNull(getService().getAll());
        assertNotNull(getService().getAll(false));
        assertNotNull(getService().getAll(true));
    }

    @Test
    public void whenExistsByInvalidModelThenReturnFalse() {
        assertFalse(getService().exists(null));
    }

    @Test
    public void whenExistsByValidModelThenReturnTrue() {
        assertTrue(getService().exists(getObject()));
    }

    @Test
    public void whenExistsByInvalidIDThenReturnFalse() {
        assertFalse(getService().exists(UNKNOWN_ID));
    }

    @Test
    public void whenExistsByValidIDThenReturnTrue() {
        assertTrue(getService().exists(ID));
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
