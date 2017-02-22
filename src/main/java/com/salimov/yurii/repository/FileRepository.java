package com.salimov.yurii.repository;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.enums.FileType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see File
 */
public interface FileRepository extends DataRepository<File> {

    /**
     * Returns photo from a database,
     * which matches the parameter title.
     *
     * @param title a title of the photo to return.
     * @return The object of class {@link File}.
     * @see File
     */
    File findByTitle(final String title);

    /**
     * Returns photo from a database,
     * which matches the parameter url.
     *
     * @param url a url of the photo to return.
     * @return The object of class {@link File}.
     * @see File
     */
    File findByUrl(final String url);

    /**
     * Removes photo from a database,
     * which matches the parameter title.
     *
     * @param title a title of the photo to remove.
     * @see File
     */
    void deleteByTitle(final String title);

    /**
     * Removes photo from a database,
     * which matches the parameter url.
     *
     * @param url a url of the photo to remove.
     * @see File
     */
    void deleteByUrl(final String url);

    /**
     * Returns files with the type.
     *
     * @param type a type of files to return.
     * @return The files with the type.
     * @see FileType
     */
    List<File> findAllByType(final FileType type);
}
