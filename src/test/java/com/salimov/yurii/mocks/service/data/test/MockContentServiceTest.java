package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Content;
import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.service.data.interfaces.ContentService;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public abstract class MockContentServiceTest<T extends Content<Long>> extends MockDataServiceTest<T> {

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        T content = (T) getService().getByTitle(MockConstants.TITLE, true);
        assertNotNull(content);
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        T content = (T) getService().getByUrl(MockConstants.URL, true);
        assertNotNull(content);
    }

    @Test
    public void whenSortByTitleThenReturnSomeContents() {
        Collection<T> contents = getService().sortByTitle(getObjects(), false);
        assertNotNull(contents);

        contents = getService().sortByTitle(getObjects(), true);
        assertNotNull(contents);
    }

    @Test
    public void whenSortByUrlThenReturnSomeContents() {
        Collection<T> contents = getService().sortByUrl(getObjects(), false);
        assertNotNull(contents);

        contents = getService().sortByUrl(getObjects(), true);
        assertNotNull(contents);
    }

    @Test
    public void whenGetAndSortByTitleThenReturnSomeContents() {
        Collection<T> contents = getService().getAndSortByTitle(false);
        assertNotNull(contents);

        contents = getService().getAndSortByTitle(true);
        assertNotNull(contents);
    }

    @Test
    public void whenGetAndSortByUrlThenReturnSomeContents() {
        Collection<T> contents = getService().getAndSortByTitle(false);
        assertNotNull(contents);

        contents = getService().getAndSortByTitle(true);
        assertNotNull(contents);
    }

    @Ignore
    @Override
    protected abstract ContentService getService();
}
