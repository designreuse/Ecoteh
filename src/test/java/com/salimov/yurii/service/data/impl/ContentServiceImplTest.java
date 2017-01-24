package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Content;
import com.salimov.yurii.service.data.interfaces.ContentService;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static org.junit.Assert.assertNotNull;

public abstract class ContentServiceImplTest<T extends Content<E>, E extends Number>
        extends DataServiceImplTest<T, E> {

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleWithValidThenThrowsIllegalArgumentException() {
        getService().getByTitle(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullTitleWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByTitle(null, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankTitleWithValidThenThrowsIllegalArgumentException() {
        getService().getByTitle("", true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByBlankTitleWithInvalidThenThrowsIllegalArgumentException() {
        getService().getByTitle(" ", false);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleWithValidThenThrowsNullPointerException() {
        getService().getByTitle(ANY_STRING, true);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownTitleWithInvalidThenThrowsNullPointerException() {
        getService().getByTitle(ANY_STRING, false);
    }

    @Test
    public void whenGetByTitleThenReturnsSomeContent() {
        assertNotNull(
                getService().getByTitle(TITLE, true)
        );
        assertNotNull(
                getService().getByTitle(TITLE, false)
        );
    }

    // TODO: add another test methods.

    @Ignore
    @Override
    protected abstract ContentService<T, E> getService();
}
