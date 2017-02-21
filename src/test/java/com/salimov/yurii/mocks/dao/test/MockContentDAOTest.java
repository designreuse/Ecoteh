package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.ContentDao;
import com.salimov.yurii.entity.Content;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public abstract class MockContentDAOTest<T extends Content> extends MockDataDAOTest<T> {

    @Test
    public void whenGetByInvalidTitleThenReturnNull() {
        assertNull(getDao().getByTitle(null));
        assertNull(getDao().getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByValidTitleThenReturnSomeContent() {
        assertNotNull(getDao().getByTitle(TITLE));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnNull() {
        assertNull(getDao().getByUrl(null));
        assertNull(getDao().getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByValidUrlThenReturnSomeContent() {
        assertNotNull(getDao().getByUrl(URL));
    }

    @Ignore
    @Override
    protected abstract ContentDao getDao();
}
