package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.service.data.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFile;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFiles;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class MockFileServiceTest extends MockDataServiceTest<File> {

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
    protected File getObject() {
        return getFile();
    }

    @Ignore
    @Override
    protected Collection<File> getObjects() {
        return getFiles();
    }
}
