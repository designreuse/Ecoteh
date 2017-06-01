package ua.com.ecoteh.mocks.enity.test;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.file.FileEntity;

import java.util.Collection;

import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntity;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntities;

public final class MockFileEntityTest extends MockModelTest<FileEntity> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final FileEntity fileEntity = getFileEntity();
        assertNotNull(fileEntity.getTitle());
        assertNotNull(fileEntity.getUrl());
        assertNotNull(fileEntity.getId());
    }

    @Ignore
    @Override
    protected FileEntity getObject() {
        return getFileEntity();
    }

    @Ignore
    @Override
    protected Collection<FileEntity> getObjects() {
        return getFileEntities();
    }
}
