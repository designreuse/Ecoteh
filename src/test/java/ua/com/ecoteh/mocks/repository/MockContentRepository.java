package ua.com.ecoteh.mocks.repository;

import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.repository.ContentRepository;

import java.util.Collection;

import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
abstract class MockContentRepository<E extends ContentEntity> extends MockDataRepository<E> {

    @Override
    ContentRepository<E> create() {
        super.create();
        initFindByTitle();
        initFindByUrl();
        return getRepository();
    }

    @Override
    abstract ContentRepository<E> getRepository();

    @Override
    abstract E getEntity();

    @Override
    abstract Collection<E> getEntities();

    private void initFindByTitle() {
        final ContentRepository<E> repository = getRepository();
        final E entity = getEntity();
        when(repository.findByTitle(TITLE)).thenReturn(entity);
        when(repository.findByTitle(ANY_STRING)).thenReturn(null);
        when(repository.findByTitle(null)).thenReturn(null);
    }

    private void initFindByUrl() {
        final ContentRepository<E> repository = getRepository();
        final E entity = getEntity();
        when(repository.findByUrl(URL)).thenReturn(entity);
        when(repository.findByUrl(ANY_STRING)).thenReturn(null);
        when(repository.findByUrl(null)).thenReturn(null);
    }
}
