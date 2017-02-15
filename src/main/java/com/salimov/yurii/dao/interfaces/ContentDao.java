package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Content;

/**
 * The interface provides a set of standard methods for working objects
 * of the {@link Content} class or subclasses with a database.
 *
 * @param <T>  Entity type, extends {@link Content}.
 * @param <E> Entity id type, extends Number.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataDao
 * @see Content
 */
public interface ContentDao<T extends Content<E>, E extends Number> extends DataDao<T, E> {

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title a title of the content object to return.
     * @return The content object with parameter title.
     * @see Content
     */
    T getByTitle(final String title);

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter url from a database.
     *
     * @param url a url of the content object to return.
     * @return The content object with parameter url.
     * @see Content
     */
    T getByUrl(final String url);

    /**
     * Removes content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title a title of the content object to remove.
     * @see Content
     */
    void removeByTitle(final String title);

    /**
     * Removes content object with the parameter url from a database.
     *
     * @param url a url of the content object to remove.
     * @see Content
     */
    void removeByUrl(final String url);
}
