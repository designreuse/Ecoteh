package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.FileDao;
import org.junit.Before;
import org.junit.Ignore;
import com.salimov.yurii.entity.File;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static com.salimov.yurii.mocks.dao.MockDao.getFileDao;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MockFileDAOTest extends MockDataDAOTest<File> {

    private FileDao dao;

    @Before
    public void initPhotoDao() {
        this.dao = getFileDao();
    }

    @Test
    public void whenGetByInvalidTitleThenReturnNull() {
        assertNull(this.dao.getByTitle(null));
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByValidTitleThenReturnSomeContent() {
        assertNotNull(this.dao.getByTitle(TITLE));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnNull() {
        assertNull(this.dao.getByUrl(null));
        assertNull(this.dao.getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByValidUrlThenReturnSomeContent() {
        assertNotNull(this.dao.getByUrl(URL));
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
