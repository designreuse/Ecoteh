package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.VideoDao;
import com.salimov.yurii.mocks.dao.MockDAO;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Before;
import org.junit.Ignore;
import com.salimov.yurii.entity.Video;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getVideos;

public class MockVideoDAOTest extends MockMediaDAOTest<Video> {

    private VideoDao dao;

    @Before
    public void initVideoDao() {
        this.dao = MockDAO.getVideoDAO();
    }

    @Ignore
    @Override
    protected VideoDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Video getObject() {
        return MockEntity.getVideo();
    }

    @Ignore
    @Override
    protected Collection<Video> getObjects() {
        return MockEntity.getVideos();
    }
}
