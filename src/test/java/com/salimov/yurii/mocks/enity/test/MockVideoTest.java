package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Ignore;
import org.junit.Test;
import com.salimov.yurii.entity.Video;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static com.salimov.yurii.mocks.enity.MockEntity.getVideos;

public class MockVideoTest extends MockModelTest<Video> {

    @Test
    @Override
    public void whenGetModelThenReturnValidModel() {
        final Video video = MockEntity.getVideo();
        assertNotNull(video.getTitle());
        assertNotNull(video.getUrl());
        assertNotNull(video.getId());
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

    @Ignore
    @Override
    protected Collection<Video> getObjects(int size) {
        return MockEntity.getVideos(size);
    }
}
