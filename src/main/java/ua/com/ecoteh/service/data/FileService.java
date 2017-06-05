package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface FileService extends DataService<File> {

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         the title of a new file.
     * @param type          the type of a new file.
     * @param multipartFile the multipart file of a new file.
     * @return The new saving file.
     */
    File add(String title, FileType type, MultipartFile multipartFile);

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         the title of the a file.
     * @param multipartFile the multipart file of a new file.
     * @return The new saving file.
     */
    File add(String title, MultipartFile multipartFile);

    /**
     * Initializes, updates and returns file with incoming id.
     *
     * @param id            the id of a file to update.
     * @param title         the new title to the file.
     * @param type          the type of the new file.
     * @param multipartFile the new multipart file to the file.
     * @return The updating file with incoming id.
     */
    File update(
            long id,
            String title,
            FileType type,
            MultipartFile multipartFile
    );

    /**
     * Initializes, updates and returns file with incoming id.
     *
     * @param id            the id of a file to update.
     * @param title         the new title to a file.
     * @param multipartFile the new multipart file to a file.
     * @return The updating photo with incoming id.
     */
    File update(
            long id,
            String title,
            MultipartFile multipartFile
    );

    /**
     * Returns file object with the incoming title.
     *
     * @param title the title of a file to return.
     * @return The media with incoming title.
     */
    File getByTitle(String title);

    /**
     * Returns file object with the incoming url.
     *
     * @param url the URL of a file to return.
     * @return The media with incoming URL.
     */
    File getByUrl(String url);

    /**
     * Removes file object with the incoming title.
     *
     * @param title the title of a file to remove.
     */
    void removeByTitle(String title);

    /**
     * Removes file object with the incoming url.
     *
     * @param url a URL of the file to remove.
     */
    void removeByUrl(String url);

    /**
     * Sorts and returns file objects by title.
     *
     * @param fileEntities  the fileEntities to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of fileEntities.
     */
    List<File> sortByTitle(Collection<File> fileEntities, boolean revers);

    /**
     * Save a multipart file in the file system in the directory rootPath.
     *
     * @param file     the multipart file to save.
     * @param rootPath the directory path.
     */
    void saveFile(MultipartFile file, String rootPath);

    /**
     * Save a multipart file in the file system.
     *
     * @param file the multipart file to save.
     */
    void saveFile(MultipartFile file);

    /**
     * Deletes file in the file system.
     *
     * @param path the file path.
     * @return true if able to delete the file, false otherwise.
     */
    boolean deleteFile(String path);

    /**
     * Returns fileEntities with the incoming type.
     *
     * @param type the type of fileEntities to return.
     * @return The fileEntities with the incoming type.
     */
    List<File> getByType(FileType type);

    /**
     * Returns last file with the incoming type.
     *
     * @param type the type of file to return.
     * @return The last file with the incoming type.
     */
    File getLastByType(FileType type);
}
