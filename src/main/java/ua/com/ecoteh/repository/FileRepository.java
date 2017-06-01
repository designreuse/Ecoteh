package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.file.FileType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link FileEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface FileRepository extends DataRepository<FileEntity> {

    /**
     * Returns file from a database,
     * which matches the parameter title.
     *
     * @param title the title of the file to return.
     * @return The object of class {@link FileEntity}.
     */
    FileEntity findByTitle(String title);

    /**
     * Returns file from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a file to return.
     * @return The object of class {@link FileEntity}.
     */
    FileEntity findByUrl(String url);

    /**
     * Removes file from a database,
     * which matches the parameter title.
     *
     * @param title the title of a file to remove.
     */
    void deleteByTitle(String title);

    /**
     * Removes file from a database,
     * which matches the parameter url.
     *
     * @param url the URL of a file to remove.
     */
    void deleteByUrl(String url);

    /**
     * Returns all files with the type.
     *
     * @param type the type of files to return.
     * @return The files with the type.
     */
    List<FileEntity> findAllByType(FileType type);
}
