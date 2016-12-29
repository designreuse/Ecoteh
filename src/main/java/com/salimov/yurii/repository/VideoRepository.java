package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Video;

/**
 * The interface provides a set of JPA methods
 * for working {@link Video} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Video
 */
public interface VideoRepository extends DataRepository<Video, Long> {

    /**
     * Returns video from a database,
     * which matches the parameter title.
     *
     * @param title a title of the video to return.
     * @return The object of class {@link Video}.
     * @see Video
     */
    Video findByTitle(final String title);

    /**
     * Returns video from a database,
     * which matches the parameter url.
     *
     * @param url a url of the video to return.
     * @return The object of class {@link Video}.
     * @see Video
     */
    Video findByUrl(final String url);

    /**
     * Removes video from a database,
     * which matches the parameter title.
     *
     * @param title a title of the video to remove.
     * @see Video
     */
    void deleteByTitle(final String title);

    /**
     * Removes video from a database,
     * which matches the parameter url.
     *
     * @param url a url of the video to remove.
     * @see Video
     */
    void deleteByUrl(final String url);
}
