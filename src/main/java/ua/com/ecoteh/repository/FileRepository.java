package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.enums.FileType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface FileRepository extends DataRepository<File> {

    /**
     * Returns file from a database,
     * which matches the parameter title.
     *
     * @param title the title of the file to return.
     * @return The object of class {@link File}.
     */
    File findByTitle(final String title);

    /**
     * Returns file from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a file to return.
     * @return The object of class {@link File}.
     */
    File findByUrl(final String url);

    /**
     * Removes file from a database,
     * which matches the parameter title.
     *
     * @param title the title of a file to remove.
     */
    void deleteByTitle(final String title);

    /**
     * Removes file from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a file to remove.
     */
    void deleteByUrl(final String url);

    /**
     * Returns all files with the type.
     *
     * @param type the type of files to return.
     * @return The files with the type.
     */
    List<File> findAllByType(final FileType type);
}
