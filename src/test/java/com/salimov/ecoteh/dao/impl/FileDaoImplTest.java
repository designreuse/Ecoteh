package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.FileDao;
import com.salimov.ecoteh.entity.File;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getFile;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getFiles;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getFileRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class FileDaoImplTest extends DataDAOImplTest<File> {

    private FileDao dao;

    @Before
    public void beforeTest() {
        this.dao = new FileDaoImpl(getFileRepository());
    }

    @Test
    public void whenGetByNullTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(null));
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeFile() {
        assertNotNull(this.dao.getByTitle(TITLE));
    }

    @Test
    public void whenGetByNullUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(null));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeFile() {
        assertNotNull(this.dao.getByUrl(URL));
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        this.dao.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        this.dao.removeByUrl(URL);
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
