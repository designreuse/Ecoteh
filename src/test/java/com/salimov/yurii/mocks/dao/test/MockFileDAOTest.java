package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.FileDao;
import org.junit.Before;
import org.junit.Ignore;
import com.salimov.yurii.entity.File;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static com.salimov.yurii.mocks.dao.MockDAO.getFileDAO;

public class MockFileDAOTest extends MockMediaDAOTest<File> {

    private FileDao dao;

    @Before
    public void initPhotoDao() {
        this.dao = getFileDAO();
    }

    @Ignore
    @Override
    protected FileDao getDao() {
        return this.dao;
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
