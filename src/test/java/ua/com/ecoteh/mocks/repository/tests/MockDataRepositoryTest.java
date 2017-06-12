package ua.com.ecoteh.mocks.repository.tests;

import org.junit.Assert;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.repository.DataRepository;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class MockDataRepositoryTest <E extends ModelEntity> {

    @Test
    public void whenSaveModelThenReturnNotNull() {
        final E entity = getObject();
        final DataRepository<E> repository = getRepository();
        final E savingEntity = repository.save(entity);
        assertNotNull(savingEntity);
    }

    @Test
    public void whenSaveNullThenReturnNull() {
        final E entity = null;
        final DataRepository<E> repository = getRepository();
        final E savingEntity = repository.save(entity);
        assertNull(savingEntity);
    }

    @Test
    public void whenFindOneThenReturnNotNull() {
        final DataRepository<E> repository = getRepository();
        final E entity = repository.findOne(ID);
        assertNotNull(entity);
    }

    @Test
    public void whenFindOneWithUnknownIdThenReturnNull() {
        final DataRepository<E> repository = getRepository();
        final E entity = repository.findOne(UNKNOWN_ID);
        assertNull(entity);
    }

    @Test
    public void whenFindOneWithNullIdThenReturnNull() {
        final DataRepository<E> repository = getRepository();
        final E entity = repository.findOne((Long) null);
        assertNull(entity);
    }

    @Test
    public void whenFindAllThenReturnNotEmptyCollection() {
        final DataRepository<E> repository = getRepository();
        final Collection<E> entities = repository.findAll();
        assertFalse(entities.isEmpty());
    }

    @Test
    public void whenFindAllThenReturnCollectionWithNotNullObjects() {
        final DataRepository<E> repository = getRepository();
        final Collection<E> entities = repository.findAll();
        entities.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenExistsByIdThenReturnTrue() {
        final DataRepository<E> repository = getRepository();
        assertTrue(repository.exists(ID));
    }

    @Test
    public void whenExistsByUnknownIdThenReturnFalse() {
        final DataRepository<E> repository = getRepository();
        assertFalse(repository.exists(UNKNOWN_ID));
    }

    @Test
    public void whenExistsByNullIdThenReturnFalse() {
        final DataRepository<E> repository = getRepository();
        assertFalse(repository.exists((Long) null));
    }

    protected abstract DataRepository<E> getRepository();

    protected abstract E getObject();

    protected abstract Collection<E> getObjects();
}
