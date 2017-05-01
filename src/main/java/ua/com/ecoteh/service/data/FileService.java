package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.enums.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface FileService extends DataService<File> {

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         a title of the new file.
     * @param type          a type of the new file.
     * @param multipartFile a multipart file of the new file.
     * @return The new saving file.
     */
    File add(
            final String title,
            final FileType type,
            final MultipartFile multipartFile
    );

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         a title of the new file.
     * @param multipartFile a multipart file of the new file.
     * @return The new saving file.
     */
    File add(
            final String title,
            final MultipartFile multipartFile
    );

    /**
     * Initializes, updates and returns photo with parameter id.
     *
     * @param id            a id of the photo to update.
     * @param title         a new title to the file.
     * @param type          a type of the new file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating photo with parameter id.
     */
    File update(
            final long id,
            final String title,
            final FileType type,
            final MultipartFile multipartFile
    );

    /**
     * Initializes, updates and returns photo with parameter id.
     *
     * @param id            a id of the photo to update.
     * @param title         a new title to the file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating photo with parameter id.
     */
    File update(
            final long id,
            final String title,
            final MultipartFile multipartFile
    );

    /**
     * Returns file object with the parameter title.
     *
     * @param title a title of the media to return.
     * @return The media with parameter title.
     */
    File getByTitle(final String title);

    /**
     * Returns file object with the parameter url.
     *
     * @param url a URL of the file to return.
     * @return The media with parameter url.
     */
    File getByUrl(final String url);

    /**
     * Removes file object with the parameter title.
     *
     * @param title a title of the file to remove.
     */
    void removeByTitle(final String title);

    /**
     * Removes file object with the parameter url.
     *
     * @param url a URL of the file to remove.
     */
    void removeByUrl(final String url);

    /**
     * Sorts and returns file objects by title.
     *
     * @param files  the files to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of files.
     */
    List<File> sortByTitle(
            final Collection<File> files,
            final boolean revers
    );

    /**
     * Save a multipart file in the file system in the directory rootPath.
     *
     * @param file thr file to save.
     * @param rootPath      a directory path.
     */
    void saveFile(
            final MultipartFile file,
            final String rootPath
    );

    /**
     * Save a multipart file in the file system.
     *
     * @param file the file to save.
     */
    void saveFile(final MultipartFile file);

    /**
     * Deletes file in the file system.
     *
     * @param path the file path.
     * @return true if able to delete the file, false otherwise.
     */
    boolean deleteFile(final String path);

    /**
     * Returns files with the type.
     *
     * @param type a type of files to return.
     * @return The files with the type.
     */
    List<File> getByType(final FileType type);

    /**
     * Returns last file with the type.
     *
     * @param type a type of file to return.
     * @return The last file with the type.
     */
    File getLastByType(final FileType type);
}
