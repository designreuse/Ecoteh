package com.salimov.yurii.entity;

import com.salimov.yurii.mocks.MockConstants;
import com.salimov.yurii.mocks.enity.MockEntity;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class VideoTest extends MediaTest<Video> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        Video video = new Video(null, null);
        assertNull(video.getTitle());
        assertNull(video.getUrl());

        video = new Video("", "");
        assertNull(video.getTitle());
        assertNull(video.getUrl());

        video = new Video(" ", " ");
        assertNull(video.getTitle());
        assertNull(video.getUrl());

        video = new Video("   ", "   ");
        assertNull(video.getTitle());
        assertNull(video.getUrl());
    }

    @Test
    public void equalsInvalidObjects() {
        final Video video1 = new Video();
        final Video video2 = new Video();
        assertTrue(
                video1.equals(video2)
        );

        video1.setTitle(MockConstants.TITLE);
        video2.setTitle(MockConstants.TITLE);
        assertTrue(
                video1.equals(video2)
        );

        video1.setUrl(MockConstants.URL);
        video2.setUrl(MockConstants.URL);
        assertTrue(
                video1.equals(video2)
        );
    }

    @Test
    public void hashCodeInvalidObject() {
        final Video video = new Video();
        int value = 0;
        assertEquals(
                video.hashCode(),
                value
        );

        video.setTitle(MockConstants.TITLE);
        value += (
                isNotBlank(video.getTitle()) ?
                        video.getTitle().hashCode() : 0
        ) + (
                isNotBlank(video.getUrl()) ?
                        video.getUrl().hashCode() : 0
        );
        assertEquals(
                video.hashCode(),
                value
        );
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final Video video = new Video(
                MockConstants.TITLE,
                MockConstants.URL
        );
        assertNotNull(video.getTitle());
        assertNotNull(video.getUrl());
        Assert.assertEquals(
                video.getTitle(),
                MockConstants.TITLE
        );
        Assert.assertEquals(
                video.getUrl(),
                MockConstants.URL
        );
    }

    @Ignore
    @Override
    protected Video getObject() {
        return MockEntity.getVideo();
    }
}
