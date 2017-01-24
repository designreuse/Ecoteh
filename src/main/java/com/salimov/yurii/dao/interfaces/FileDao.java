package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.File;

/**
 * The interface provides a set of standard methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.FileDaoImpl
 * @see DataDao
 */
public interface FileDao
        extends DataDao<File, Long> {

    /**
     * Returns media object of the {@link File} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media object to return.
     * @return The media object with the parameter title.
     * @see File
     */
    File getByTitle(final String title);

    /**
     * Returns media object of the {@link File} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a url of the media object to return.
     * @return The media object with the parameter url.
     * @see File
     */
    File getByUrl(final String url);

    /**
     * Removes media object of the {@link File} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media to remove.
     * @see File
     */
    void removeByTitle(final String title);

    /**
     * Removes media object of the {@link File} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a url of the media to remove.
     * @see File
     */
    void removeByUrl(final String url);
}
