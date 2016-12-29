package com.salimov.yurii.service.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.salimov.yurii.dao.interfaces.VideoDao;
import com.salimov.yurii.entity.Video;
import com.salimov.yurii.service.data.interfaces.VideoService;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Video} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Video
 * @see VideoService
 * @see MediaServiceImpl
 * @see DataServiceImpl
 * @see VideoDao
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.dao")
public final class VideoServiceImpl
        extends MediaServiceImpl<Video, Long>
        implements VideoService {

    /**
     * Constructor.
     *
     * @param dao a implementation of the {@link VideoDao} interface.
     * @see VideoDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public VideoServiceImpl(final VideoDao dao) {
        super(dao);
    }

    /**
     * Return Class object of {@link Video} class.
     *
     * @return The Class object of {@link Video} class.
     */
    @Override
    protected Class getModelClass() {
        return Video.class;
    }
}
