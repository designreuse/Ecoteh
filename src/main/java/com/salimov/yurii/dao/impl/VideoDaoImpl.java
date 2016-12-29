package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.VideoDao;
import com.salimov.yurii.entity.Video;
import com.salimov.yurii.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods
 * for working {@link Video} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see VideoDao
 * @see Video
 * @see VideoRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class VideoDaoImpl
        extends DataDaoImpl<Video, Long>
        implements VideoDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Video} objects with a database.
     *
     * @see VideoRepository
     */
    private final VideoRepository repository;

    /**
     * Constructor.
     *
     * @param repository The interface provides a set of JPA methods
     *                   for working {@link Video} objects with a database.
     * @see VideoRepository
     */
    @Autowired
    public VideoDaoImpl(final VideoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns video with the parameter title from a database.
     *
     * @param title a title of video to return.
     * @return The video with the parameter title.
     * @see Video
     */
    @Override
    public Video getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns video with the parameter url from a database.
     *
     * @param url a url of video to return.
     * @return The video with the parameter url.
     * @see Video
     */
    @Override
    public Video getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes video with the parameter title from a database.
     *
     * @param title a title of the video to remove.
     * @see Video
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes video with the parameter url from a database.
     *
     * @param url a url of the video to remove.
     * @see Video
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
