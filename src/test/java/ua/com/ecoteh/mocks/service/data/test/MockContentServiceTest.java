package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Content;
import ua.com.ecoteh.service.data.ContentService;
import org.junit.Ignore;
import org.junit.Test;

import static ua.com.ecoteh.mocks.MockConstants.TITLE;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static org.junit.Assert.assertNotNull;

public abstract class MockContentServiceTest<T extends Content> extends MockDataServiceTest<T> {

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        assertNotNull(getService().getByTitle(TITLE, true));
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        assertNotNull(getService().getByUrl(URL, true));
    }

    @Test
    public void whenSortByTitleThenReturnSomeContents() {
        assertNotNull(getService().sortByTitle(getObjects(), false));
        assertNotNull(getService().sortByTitle(getObjects(), true));
    }

    @Test
    public void whenSortByUrlThenReturnSomeContents() {
        assertNotNull(getService().sortByUrl(getObjects(), false));
        assertNotNull(getService().sortByUrl(getObjects(), true));
    }

    @Test
    public void whenGetAndSortByTitleThenReturnSomeContents() {
        assertNotNull(getService().getAndSortByTitle(false));
        assertNotNull(getService().getAndSortByTitle(true));
    }

    @Test
    public void whenGetAndSortByUrlThenReturnSomeContents() {
        assertNotNull(getService().getAndSortByTitle(false));
        assertNotNull(getService().getAndSortByTitle(true));
    }

    @Ignore
    @Override
    protected abstract ContentService getService();
}
