package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;
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
        File file = this.service.initAndAdd(TITLE, URL, null);
        assertNotNull(file);
    }

    @Test
    public void whenInitAndUpdatePhotoThenReturnThisPhoto() {
        File file = this.service.initAndUpdate(ID, TITLE, URL, null);
        assertNotNull(file);
    }

    @Test
    public void whenDeleteFileThenReturnBoolean() {
        boolean value = this.service.deleteFile(PATH);
        assertNotNull(value);
    }

    @Test
    public void whenUpdatePhotoThenReturnThisPhoto() {
        final File file1 = getPhoto();
        File file2 = this.service.updatePhoto(file1, null, TITLE, PATH);
        assertNotNull(file2);
    }

    @Ignore
    @Override
    protected FileService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected File getObject() {
        return getPhoto();
    }

    @Ignore
    @Override
    protected Collection<File> getObjects() {
        return getPhotos();
    }
}
