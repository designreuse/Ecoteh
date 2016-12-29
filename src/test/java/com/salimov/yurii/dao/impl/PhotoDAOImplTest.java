package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.PhotoDao;
import com.salimov.yurii.entity.Photo;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PhotoDAOImplTest extends MediaDAOImplTest<Photo> {

    @Autowired
    private PhotoDao dao;

    @Ignore
    @Override
    public void whenRemoveByModels() {
    }

    @Ignore
    @Override
    protected PhotoDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Photo getObject() {
        return getPhoto();
    }

    @Ignore
    @Override
    protected Collection<Photo> getObjects() {
        return getPhotos();
    }
}
