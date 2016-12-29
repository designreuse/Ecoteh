package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.VideoDao;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.salimov.yurii.entity.Video;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getVideos;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class VideoDAOImplTest extends MediaDAOImplTest<Video> {

    @Autowired
    private VideoDao dao;

    @Ignore
    @Override
    protected VideoDao getDao() {
        return dao;
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
