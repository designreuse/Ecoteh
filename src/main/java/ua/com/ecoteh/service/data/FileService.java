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
 * @see File
 */
public interface FileService extends DataService<File> {

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
     * @return true if model is deleted, false otherwise.
     */
    boolean removeByTitle(String title);

    /**
     * Removes file object with the incoming url.
     *
     * @param url a URL of the file to remove.
     * @return true if model is deleted, false otherwise.
     */
    boolean removeByUrl(String url);

    /**
     * Sorts and returns file objects by title.
     *
     * @param files  the fileEntities to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of fileEntities.
     */
    List<File> sortByTitle(Collection<File> files, boolean revers);

    /**
     * Save a multipart file in the file system in the directory rootPath.
     *
     * @param file     the multipart file to save.
     * @param rootPath the directory path.
     * @return the relative path to the saving file.
     */
    String saveFile(MultipartFile file, String rootPath);

    /**
     * Save a multipart file in the file system.
     *
     * @param file the multipart file to save.
     * @return the path to the saving file.
     */
    String saveFile(MultipartFile file);

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
    Collection<File> getByType(FileType type);

    /**
     * Returns last file with the incoming type.
     *
     * @param type the type of file to return.
     * @return The last file with the incoming type.
     */
    File getLastByType(FileType type);
}
