package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.Content;

/**
 * The interface provides a set of standard methods for working objects
 * of the {@link Content} class or subclasses with a database.
 *
 * @param <T> Entity type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ContentDao<T extends Content> extends DataDao<T> {

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title a title of the content object to return.
     * @return The content object with parameter title.
     */
    T getByTitle(final String title);

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the parameter url from a database.
     *
     * @param url a URL of the content object to return.
     * @return The content object with parameter url.
     */
    T getByUrl(final String url);

    /**
     * Removes content object of the {@link Content} class or subclasses
     * with the parameter title from a database.
     *
     * @param title a title of the content object to remove.
     */
    void removeByTitle(final String title);

    /**
     * Removes content object with the parameter url from a database.
     *
     * @param url a URL of the content object to remove.
     */
    void removeByUrl(final String url);
}
