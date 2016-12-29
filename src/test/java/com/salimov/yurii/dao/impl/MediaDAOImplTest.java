package com.salimov.yurii.dao.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import com.salimov.yurii.dao.interfaces.MediaDao;
import com.salimov.yurii.entity.Media;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class MediaDAOImplTest<T extends Media<Long>> extends DataDAOImplTest<T> {

    @Test
    @Transactional
    public void whenGetByTitleThenReturnSomeMedia() {
        final T media1 = (T) getDao()
                .add(
                        getObject()
                );
        final T media2 = (T) getDao()
                .getByTitle(
                        media1.getTitle()
                );
        assertNotNull(media2);
        assertEquals(media1, media2);
    }

    @Test
    @Transactional
    public void whenGetByUrlThenReturnSomeMedia() {
        final T media1 = (T) getDao()
                .add(
                        getObject()
                );
        final T media2 = (T) getDao()
                .getByUrl(
                        media1.getUrl()
                );
        assertNotNull(media2);
        assertEquals(media1, media2);
    }

    @Transactional
    public void whenRemoveByTitle() {
        final T media = (T) getDao().add(
                getObject()
        );
        assertTrue(
                getDao()
                        .exists(
                                media.getId()
                        )
        );
        getDao().removeByTitle(
                media.getTitle()
        );
        assertTrue(
                getDao()
                        .exists(
                                media.getId()
                        )
        );
    }

    @Transactional
    public void whenRemoveByUrl() {
        final T media = (T) getDao()
                .add(
                        getObject()
                );
        assertTrue(
                getDao()
                        .exists(
                                media.getId()
                        )
        );

        getDao().removeByUrl(
                media.getUrl()
        );
        assertTrue(
                getDao()
                        .exists(
                                media.getId()
                        )
        );
    }

    @Ignore
    protected abstract MediaDao getDao();
}
