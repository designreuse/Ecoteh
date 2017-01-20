package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.FileService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.dao.MockDAO.getFileDAO;
import static com.salimov.yurii.mocks.enity.MockEntity.*;

public class FileServiceImplTest extends DataServiceImplTest<File, Long> {

    private FileService fileService;

    @Before
    public void beforeTest() {
        fileService = new FileServiceImpl(
                getFileDAO(), null
        );
    }

    @Ignore
    @Override
    protected FileService getService() {
        return fileService;
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
