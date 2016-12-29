package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import org.junit.Before;
import org.junit.Ignore;
import com.salimov.yurii.entity.Video;
import com.salimov.yurii.service.data.interfaces.VideoService;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getVideos;

public class MockVideoServiceTest extends MockMediaServiceTest<Video> {

    private VideoService service;

    @Before
    public void initVideoService() {
        this.service = MockServices.getVideoService();
    }

    @Ignore
    @Override
    protected VideoService getService() {
        return this.service;
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
