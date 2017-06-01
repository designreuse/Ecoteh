package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.service.data.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntity;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntities;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class MockFileEntityServiceTest extends MockDataServiceTest<FileEntity> {

    private FileService service;

    @Before
    public void initFileService() {
        this.service = getFileService();
    }

    @Test
    public void whenAddFileThenReturnThisFile() {
        assertNotNull(this.service.add(TITLE, null));
    }

    @Test
    public void whenUpdateFileThenReturnThisFile() {
        assertNotNull(this.service.update(ID, TITLE, null));
    }

    @Test
    public void whenDeleteFileThenReturnBoolean() {
        assertNotNull(this.service.deleteFile(PATH));
    }

    @Test
    public void whenGetByTitleThenReturnSomeContent() {
        assertNotNull(getService().getByTitle(TITLE));
    }

    @Test
    public void whenGetByUrlThenReturnSomeContent() {
        assertNotNull(getService().getByUrl(URL));
    }

    @Ignore
    @Override
    protected FileService getService() {
        return this.service;
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
