package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.service.data.interfaces.FileService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.dao.MockDao.getFileDao;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getFile;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getFiles;
import static com.salimov.ecoteh.mocks.properties.MockContentProperties.getContentProperties;

public final class FileServiceImplTest extends DataServiceImplTest<File> {

    private FileService service;

    @Before
    public void beforeTest() {
        this.service = new FileServiceImpl(getFileDao(), getContentProperties());
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

    @Ignore
    @Override
    protected File getInvalidObject() {
        return new File();
    }
}
