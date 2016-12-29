package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.PhotoDao;
import org.junit.Before;
import org.junit.Ignore;
import com.salimov.yurii.entity.Photo;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getPhoto;
import static com.salimov.yurii.mocks.enity.MockEntity.getPhotos;
import static com.salimov.yurii.mocks.dao.MockDAO.getPhotoDAO;

public class MockPhotoDAOTest extends MockMediaDAOTest<Photo> {

    private PhotoDao dao;

    @Before
    public void initPhotoDao() {
        this.dao = getPhotoDAO();
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
