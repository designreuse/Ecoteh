package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.enums.FileType;

import java.util.List;

/**
 * The interface provides a set of standard methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface FileDao extends DataDao<File> {

    /**
     * Returns media object of the {@link File} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media object to return.
     * @return The media object with the parameter title.
     */
    File getByTitle(final String title);

    /**
     * Returns media object of the {@link File} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a URL of the media object to return.
     * @return The media object with the parameter url.
     */
    File getByUrl(final String url);

    /**
     * Removes media object of the {@link File} class
     * or subclasses with the parameter title from a database.
     *
     * @param title a title of the media to remove.
     */
    void removeByTitle(final String title);

    /**
     * Removes media object of the {@link File} class
     * or subclasses with the parameter url from a database.
     *
     * @param url a URL of the media to remove.
     */
    void removeByUrl(final String url);

    /**
     * Returns files with the type.
     *
     * @param type a type of files to return.
     * @return The files with the type.
     */
    List<File> getByFileType(final FileType type);
}
