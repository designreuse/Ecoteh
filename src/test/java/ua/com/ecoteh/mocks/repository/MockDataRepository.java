package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.repository.DataRepository;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ID;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class MockDataRepository<E extends ModelEntity> {

    DataRepository<E> create() {
        initSave();
        initFindOne();
        initFindAll();
        initTest();
        return getRepository();
    }

    abstract DataRepository<E> getRepository();

    abstract E getEntity();

    abstract Collection<E> getEntities();

    private void initSave() {
        final DataRepository<E> repository = getRepository();
        final E entity = getEntity();
        final Collection<E> entities = getEntities();
        when(repository.save(entity)).thenReturn(entity);
        when(repository.save((E) null)).thenReturn(null);
        when(repository.save(entities)).thenReturn(new ArrayList<>(entities));
        for (E e : entities) {
            when(repository.save(e)).thenReturn(e);
        }
    }

    private void initFindOne() {
        final DataRepository<E> repository = getRepository();
        final E entity = getEntity();
        when(repository.findOne(ID)).thenReturn(entity);
        when(repository.findOne(UNKNOWN_ID)).thenReturn(null);
        when(repository.findOne((Long) null)).thenReturn(null);
    }

    private void initFindAll() {
        final DataRepository<E> repository = getRepository();
        final Collection<E> entities = getEntities();
        when(repository.findAll()).thenReturn(new ArrayList<>(entities));
    }

    private void initTest() {
        final DataRepository<E> repository = getRepository();
        when(repository.exists(ID)).thenReturn(true);
        when(repository.exists(UNKNOWN_ID)).thenReturn(false);
        when(repository.exists((Long) null)).thenReturn(false);
    }
}
