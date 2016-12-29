package com.salimov.yurii.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Photo;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;

public class MockPhotoTest extends MockModelTest<Photo> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Photo photo = getPhoto();
        assertNotNull(photo.getTitle());
        assertNotNull(photo.getUrl());
        assertNotNull(photo.getId());
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

    @Ignore
    @Override
    protected Collection<Photo> getObjects(int size) {
        return getPhotos(size);
    }
}
