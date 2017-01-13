package com.salimov.yurii.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.File;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;

public class MockFileTest extends MockModelTest<File> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final File file = getPhoto();
        assertNotNull(file.getTitle());
        assertNotNull(file.getUrl());
        assertNotNull(file.getId());
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

    @Ignore
    @Override
    protected Collection<File> getObjects(int size) {
        return getPhotos(size);
    }
}
