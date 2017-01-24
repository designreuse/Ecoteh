package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Content;
import com.salimov.yurii.service.data.interfaces.ContentService;
import org.junit.Ignore;
import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static org.junit.Assert.assertNotNull;

public abstract class MockContentServiceTest<T extends Content<Long>> extends MockDataServiceTest<T> {

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        assertNotNull(
                getService()
                        .getByTitle(
                                TITLE, true
                        )
        );
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        assertNotNull(
                getService()
                        .getByUrl(
                                URL, true
                        )
        );
    }

    @Test
    public void whenSortByTitleThenReturnSomeContents() {
        assertNotNull(
                getService()
                        .sortByTitle(
                                getObjects(), false
                        )
        );
        assertNotNull(
                getService()
                        .sortByTitle(
                                getObjects(), true
                        )
        );
    }

    @Test
    public void whenSortByUrlThenReturnSomeContents() {
        assertNotNull(
                getService()
                        .sortByUrl(
                                getObjects(), false
                        )
        );
        assertNotNull(
                getService()
                        .sortByUrl(
                                getObjects(), true
                        )
        );
    }

    @Test
    public void whenGetAndSortByTitleThenReturnSomeContents() {
        assertNotNull(
                getService()
                        .getAndSortByTitle(false)
        );

        assertNotNull(
                getService()
                        .getAndSortByTitle(true)
        );
    }

    @Test
    public void whenGetAndSortByUrlThenReturnSomeContents() {
        assertNotNull(
                getService()
                        .getAndSortByTitle(false)
        );
        assertNotNull(
                getService()
                        .getAndSortByTitle(true)
        );
    }

    @Ignore
    @Override
    protected abstract ContentService getService();
}
