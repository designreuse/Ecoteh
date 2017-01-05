package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Media;

/**
 * The interface provides a set of standard methods for working
 * medias objects of the {@link Media} class or subclasses with a database.
 *
 * @param <T>  Entity type, extends {@link Media}.
 * @param <E> Entity id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataDao
 * @see Media
 */
public interface MediaDao<T extends Media<E>, E extends Number>
        extends DataDao<T, E> {

    /**
     * Returns media object of the {@link Media} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media object to return.
     * @return The media object with the parameter title.
     * @see Media
     */
    T getByTitle(final String title);

    /**
     * Returns media object of the {@link Media} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a url of the media object to return.
     * @return The media object with the parameter url.
     * @see Media
     */
    T getByUrl(final String url);

    /**
     * Removes media object of the {@link Media} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media to remove.
     * @see Media
     */
    void removeByTitle(final String title);

    /**
     * Removes media object of the {@link Media} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a url of the media to remove.
     * @see Media
     */
    void removeByUrl(final String url);
}
