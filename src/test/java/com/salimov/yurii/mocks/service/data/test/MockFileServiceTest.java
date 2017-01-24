package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static com.salimov.yurii.mocks.service.data.MockServices.getFileService;

public final class MockFileServiceTest
        extends MockDataServiceTest<File> {

    private FileService service;

    @Before
    public void initFileService() {
        this.service = getFileService();
    }

    @Test
    public void whenInitAndAddFileThenReturnThisFile() {
        assertNotNull(
                this.service.initAndAdd(
                        TITLE, URL,
                        null
                )
        );
    }

    @Test
    public void whenInitAndUpdateFileThenReturnThisFile() {
        assertNotNull(
                this.service.initAndUpdate(
                        ID, TITLE, URL,
                        null
                )
        );
    }

    @Test
    public void whenDeleteFileThenReturnBoolean() {
        assertNotNull(
                this.service.deleteFile(PATH)
        );
    }

    @Test
    public void whenUpdateFileThenReturnThisFile() {
        assertNotNull(
                this.service.updatePhoto(
                        getFile(),
                        null,
                        TITLE, PATH
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
    protected FileService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected File getObject() {
        return getFile();
    }

    @Ignore
    @Override
    protected Collection<File> getObjects() {
        return getFiles();
    }
}
