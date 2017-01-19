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

public class MockFileServiceTest extends MockMediaServiceTest<File> {

    private FileService service;

    @Before
    public void initPhotoService() {
        this.service = getFileService();
    }

    @Ignore
    @Override
    public void whenInitAndAddMediaThenReturnMedia() {
    }

    @Ignore
    @Override
    public void whenInitAndUpdateMediaThenReturnMedia() {
    }

    @Test
    public void whenInitAndAddPhotoThenReturnThisPhoto() {
        assertNotNull(
                this.service.initAndAdd(
                        TITLE, URL,
                        null
                )
        );
    }

    @Test
    public void whenInitAndUpdatePhotoThenReturnThisPhoto() {
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
    public void whenUpdatePhotoThenReturnThisPhoto() {
        assertNotNull(
                this.service.updatePhoto(
                        getFile(),
                        null,
                        TITLE, PATH
                )
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
