package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Photo;

/**
 * The interface provides a set of JPA methods
 * for working {@link Photo} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Photo
 */
public interface PhotoRepository extends DataRepository<Photo, Long> {

    /**
     * Returns photo from a database,
     * which matches the parameter title.
     *
     * @param title a title of the photo to return.
     * @return The object of class {@link Photo}.
     * @see Photo
     */
    Photo findByTitle(final String title);

    /**
     * Returns photo from a database,
     * which matches the parameter url.
     *
     * @param url a url of the photo to return.
     * @return The object of class {@link Photo}.
     * @see Photo
     */
    Photo findByUrl(final String url);

    /**
     * Removes photo from a database,
     * which matches the parameter title.
     *
     * @param title a title of the photo to remove.
     * @see Photo
     */
    void deleteByTitle(final String title);

    /**
     * Removes photo from a database,
     * which matches the parameter url.
     *
     * @param url a url of the photo to remove.
     * @see Photo
     */
    void deleteByUrl(final String url);
}
