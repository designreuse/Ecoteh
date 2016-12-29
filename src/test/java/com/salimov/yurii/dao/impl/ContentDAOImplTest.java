package com.salimov.yurii.dao.impl;

import com.salimov.yurii.entity.Content;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import com.salimov.yurii.dao.interfaces.ContentDao;

import static org.junit.Assert.*;

public abstract class ContentDAOImplTest<T extends Content<Long>> extends DataDAOImplTest<T> {

    @Test
    @Transactional
    public void whenGetByTitleThenReturnSomeContent() {
        final T content1 = (T) getDao()
                .add(
                        getObject()
                );
        final T content2 = (T) getDao()
                .getByTitle(
                        content1.getTitle()
                );
        assertNotNull(content2);
        assertEquals(content1, content2);
    }

    @Test
    @Transactional
    public void whenGetByUrlThenReturnSomeContent() {
        final T content1 = (T) getDao()
                .add(
                        getObject()
                );
        final T content2 = (T) getDao()
                .getByUrl(
                        content1.getUrl()
                );
        assertNotNull(content2);
        assertEquals(content1, content2);
    }

    @Test
    @Transactional
    public void whenRemoveByTitle() {
        final T content = (T) getDao()
                .add(
                        getObject()
                );
        assertTrue(
                getDao()
                        .exists(
                                content.getId()
                        )
        );
        getDao().removeByTitle(
                content.getTitle()
        );
        assertFalse(
                getDao()
                        .exists(
                                content.getId()
                        )
        );
    }

    @Test
    @Transactional
    public void whenRemoveByUrl() {
        final T content = (T) getDao()
                .add(
                        getObject()
                );
        assertTrue(
                getDao()
                        .exists(
                                content.getId()
                        )
        );
        getDao().removeByUrl(
                content.getUrl()
        );
        assertFalse(
                getDao()
                        .exists(
                                content.getId()
                        )
        );
    }

    @Ignore
    protected abstract ContentDao getDao();
}
