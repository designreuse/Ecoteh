package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see File
 * @see DataService
 */
public interface FileService extends DataService<File, Long> {

    /**
     * Initializes, saves and returns a new photo.
     *
     * @param title     a title of the new photo.
     * @param photoFile a photo file (image) of the new photo.
     * @return The new saving photo.
     * @see File
     */
    File initAndAdd(
            final String title,
            final MultipartFile photoFile
    );

    /**
     * Initializes, updates and returns photo with parameter id.
     *
     * @param id        a id of the photo to update.
     * @param title     a new title of the photo.
     * @param photoFile a new photo file (image) of the photo.
     * @return The updating photo with parameter id.
     * @see File
     */
    File initAndUpdate(
            final Long id,
            final String title,
            final MultipartFile photoFile
    );

    /**
     * Returns file object with the parameter title.
     *
     * @param title a title of the media to return.
     * @return The media with parameter title.
     * @see File
     */
    File getByTitle(final String title);

    /**
     * Returns file object with the parameter url.
     *
     * @param url a url of the file to return.
     * @return The media with parameter url.
     * @see File
     */
    File getByUrl(final String url);

    /**
     * Removes file object with the parameter title.
     *
     * @param title a title of the file to remove.
     * @see File
     */
    void removeByTitle(final String title);

    /**
     * Removes file object with the parameter url.
     *
     * @param url a url of the file to remove.
     * @see File
     */
    void removeByUrl(final String url);

    /**
     * Sorts and returns file objects by title.
     *
     * @param files  the files to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of files.
     * @see File
     */
    List<File> sortByTitle(
            final Collection<File> files,
            final boolean revers
    );

    /**
     * Save the multipart file in the file system in the directory rootPath.
     *
     * @param multipartFile    a file to save.
     * @param rootPath a directory path.
     * @see File
     */
    void saveFile(
            final MultipartFile multipartFile,
            final String rootPath
    );

    /**
     * Save the multipart file in the file system.
     *
     * @param multipartFile a file to save.
     * @see File
     */
    void saveFile(final MultipartFile multipartFile);

    /**
     * Deletes file in the file system.
     *
     * @param rootPath a file path.
     * @return {@code true} if able to delete the file, {@code false} otherwise.
     * @see File
     */
    boolean deleteFile(final String rootPath);
}
