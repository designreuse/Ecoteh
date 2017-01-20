package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.FileDao;
import com.salimov.yurii.entity.File;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getFile;
import static com.salimov.yurii.mocks.enity.MockEntity.getFiles;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class FileDAOImplTest extends DataDAOImplTest<File> {

    @Autowired
    private FileDao dao;

    @Test
    @Transactional
    public void whenGetByTitleThenReturnSomeFile() {
        assertNotNull(
                this.dao.getByTitle(
                        this.dao.add(
                                getObject()
                        ).getTitle()
                )
        );
    }

    @Test
    @Transactional
    public void whenGetByUrlThenReturnSomeMedia() {
        assertNotNull(
                this.dao.getByUrl(
                        this.dao.add(
                                getObject()
                        ).getUrl()
                )
        );
    }

    @Transactional
    public void whenRemoveByTitle() {
        final File media = this.dao.add(
                getObject()
        );
        assertTrue(
                this.dao.exists(
                        media.getId()
                )
        );
        this.dao.removeByTitle(
                media.getTitle()
        );
        assertTrue(
                this.dao.exists(
                        media.getId()
                )
        );
    }

    @Transactional
    public void whenRemoveByUrl() {
        final File media = this.dao.add(
                getObject()
        );
        assertTrue(
                this.dao.exists(
                        media.getId()
                )
        );
        this.dao.removeByUrl(
                media.getUrl()
        );
        assertTrue(
                this.dao.exists(
                        media.getId()
                )
        );
    }

    @Ignore
    @Override
    public void whenRemoveByModels() {
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
