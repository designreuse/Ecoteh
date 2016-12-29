package com.salimov.yurii.service.data.interfaces;

import org.springframework.web.multipart.MultipartFile;
import com.salimov.yurii.entity.Photo;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Photo} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Photo
 * @see MediaService
 * @see DataService
 */
public interface PhotoService extends MediaService<Photo, Long> {

    /**
     * Initializes, saves and returns a new photo.
     *
     * @param title     a title of the new photo.
     * @param url       a url of the new photo.
     * @param photoFile a photo file (image) of the new photo.
     * @return The new saving photo.
     * @see Photo
     */
    Photo initAndAdd(
            final String title,
            final String url,
            final MultipartFile photoFile
    );

    /**
     * Initializes, updates and returns photo with parameter id.
     *
     * @param id        a id of the photo to update.
     * @param title     a new title of the photo.
     * @param url       a new title of the photo.
     * @param photoFile a new photo file (image) of the photo.
     * @return The updating photo with parameter id.
     * @see Photo
     */
    Photo initAndUpdate(
            final Long id,
            final String title,
            final String url,
            final MultipartFile photoFile
    );

    /**
     * Save the multipart file in the file system in the directory rootPath.
     *
     * @param photo    a file to save.
     * @param rootPath a directory path.
     * @see Photo
     */
    void saveFile(final MultipartFile photo, final String rootPath);

    /**
     * Save the multipart file in the file system.
     *
     * @param photo a file to save.
     * @see Photo
     */
    void saveFile(final MultipartFile photo);

    /**
     * Deletes file in the file system.
     *
     * @param rootPath a file path.
     * @return {@code true} if able to delete the file, {@code false} otherwise.
     * @see Photo
     */
    boolean deleteFile(final String rootPath);

    /**
     * Updates and returns the photo.
     *
     * @param photo    the photo to update.
     * @param file     a file to save.
     * @param title    a new title of the photo.
     * @param rootPath a directory path.
     * @return The updating photo.
     * @see Photo
     */
    Photo updatePhoto(
            final Photo photo,
            final MultipartFile file,
            final String title,
            final String rootPath
    );
}
