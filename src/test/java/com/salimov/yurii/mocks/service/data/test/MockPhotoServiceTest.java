package com.salimov.yurii.mocks.service.data.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.service.data.interfaces.PhotoService;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;
import static com.salimov.yurii.mocks.service.data.MockServices.getPhotoService;

public class MockPhotoServiceTest extends MockMediaServiceTest<Photo> {

    private PhotoService service;

    @Before
    public void initPhotoService() {
        this.service = getPhotoService();
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
        Photo photo = this.service.initAndAdd(TITLE, URL, null);
        assertNotNull(photo);
    }

    @Test
    public void whenInitAndUpdatePhotoThenReturnThisPhoto() {
        Photo photo = this.service.initAndUpdate(ID, TITLE, URL, null);
        assertNotNull(photo);
    }

    @Test
    public void whenDeleteFileThenReturnBoolean() {
        boolean value = this.service.deleteFile(PATH);
        assertNotNull(value);
    }

    @Test
    public void whenUpdatePhotoThenReturnThisPhoto() {
        final Photo photo1 = getPhoto();
        Photo photo2 = this.service.updatePhoto(photo1, null, TITLE, PATH);
        assertNotNull(photo2);
    }

    @Ignore
    @Override
    protected PhotoService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Photo getObject() {
        return getPhoto();
    }

    @Ignore
    @Override
    protected Collection<Photo> getObjects() {
        return getPhotos();
    }
}
