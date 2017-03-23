package com.salimov.ecoteh.mocks.service.data.test;

import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.service.data.interfaces.FileService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static org.junit.Assert.assertNotNull;
import static com.salimov.ecoteh.mocks.enity.MockEntity.*;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getFileService;

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
