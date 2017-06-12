package ua.com.ecoteh.mocks.repository.tests;

import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.repository.ContentRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public abstract class MockContentRepositoryTest <E extends ContentEntity> extends MockDataRepositoryTest<E> {

    @Test
    public void whenFindByTitleThenReturnNotNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByTitle(TITLE);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownTitleThenReturnNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByTitle(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullTitleThenReturnNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByTitle(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByUrlThenReturnNotNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByUrl(URL);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownUrlThenReturnNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByUrl(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullUrlThenReturnNull() {
        final ContentRepository<E> repository = getRepository();
        final E entity = repository.findByUrl(null);
        assertNull(entity);
    }

    @Override
    protected abstract ContentRepository<E> getRepository();
}
