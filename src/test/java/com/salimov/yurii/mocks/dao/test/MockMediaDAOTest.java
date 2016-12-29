package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.MediaDao;
import com.salimov.yurii.entity.Media;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.*;

public abstract class MockMediaDAOTest<T extends Media<Long>> extends MockDataDAOTest<T> {

    @Test
    public void whenGetByInvalidTitleThenReturnNull() {
        T content = (T) getDao().getByTitle(null);
        assertNull(content);

        content = (T) getDao().getByTitle(ANY_STRING);
        assertNull(content);
    }

    @Test
    public void whenGetByValidTitleThenReturnSomeContent() {
        T content = (T) getDao().getByTitle(TITLE);
        assertNotNull(content);
    }

    @Test
    public void whenGetByInvalidUrlThenReturnNull() {
        T content = (T) getDao().getByUrl(null);
        assertNull(content);

        content = (T) getDao().getByUrl(ANY_STRING);
        assertNull(content);
    }

    @Test
    public void whenGetByValidUrlThenReturnSomeContent() {
        T content = (T) getDao().getByUrl(URL);
        assertNotNull(content);
    }

    @Ignore
    @Override
    protected abstract MediaDao getDao();
}
