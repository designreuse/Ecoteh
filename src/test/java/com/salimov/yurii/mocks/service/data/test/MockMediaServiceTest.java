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
        assertNotNull(
                getService()
                        .initAndAdd(
                                TITLE, URL
                        )
        );
    }

    @Test
    public void whenInitAndUpdateMediaThenReturnMedia() {
        assertNotNull(
                getService()
                        .initAndUpdate(
                                ID, TITLE, URL
                        )
        );
    }

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        assertNotNull(
                getService()
                        .getByTitle(TITLE)
        );
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        assertNotNull(
                getService()
                        .getByUrl(URL)
        );
    }

    @Ignore
    @Override
    protected abstract MediaService getService();
}
