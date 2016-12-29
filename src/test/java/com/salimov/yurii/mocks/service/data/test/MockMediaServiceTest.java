package com.salimov.yurii.mocks.service.data.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Media;
import com.salimov.yurii.service.data.interfaces.MediaService;

import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.MockConstants.ID;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;

public abstract class MockMediaServiceTest<T extends Media<Long>> extends MockDataServiceTest<T> {

    @Test
    public void whenInitAndAddMediaThenReturnMedia() {
        T media = (T) getService().initAndAdd(TITLE, URL);
        assertNotNull(media);
    }

    @Test
    public void whenInitAndUpdateMediaThenReturnMedia() {
        T media = (T) getService().initAndUpdate(ID, TITLE, URL);
        assertNotNull(media);
    }

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        T media = (T) getService().getByTitle(TITLE);
        assertNotNull(media);
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        T media = (T) getService().getByUrl(URL);
        assertNotNull(media);
    }

    @Ignore
    @Override
    protected abstract MediaService getService();
}
