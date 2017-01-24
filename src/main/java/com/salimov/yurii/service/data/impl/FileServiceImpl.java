package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.FileDao;
import com.salimov.yurii.entity.Content;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.util.comparator.FileComparator;
import com.salimov.yurii.util.properties.ContentProperties;
import com.salimov.yurii.util.saver.SaverImpl;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for working  with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see File
 * @see FileService
 * @see DataServiceImpl
 * @see FileDao
 */
@Service
@ComponentScan(basePackages = "com.salimov.yurii.dao")
public final class FileServiceImpl
        extends DataServiceImpl<File, Long>
        implements FileService {

    /**
     * The interface provides a set of standard methods
     * for working {@link File} objects a the database.
     *
     * @see FileDao
     * @see File
     */
    private final FileDao dao;

    /**
     * The implementation of the interface describes
     * the methods for getting application properties..
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao        a implementation of the
     *                   {@link FileDao} interface.
     * @param properties a implementation of the
     *                   {@link ContentProperties} interface.
     * @see FileDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public FileServiceImpl(
            final FileDao dao,
            final ContentProperties properties
    ) {
        super(dao);
        this.dao = dao;
        this.properties = properties;
    }

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         a title of the new file.
     * @param url           a title of the new file.
     * @param multipartFile a multipart file of the new file.
     * @return The new saving file.
     * @see File
     */
    @Override
    @Transactional
    public File initAndAdd(
            final String title,
            final String url,
            final MultipartFile multipartFile
    ) {
        final File file = new File(
                title,
                createRelativePath(
                        title, url,
                        multipartFile
                )
        );
        saveFile(
                multipartFile,
                file.getUrl()
        );
        return add(file);
    }

    /**
     * Initializes, updates and returns file with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param id            a id of the file to update.
     * @param title         a new title to the file.
     * @param url           a new url to the file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating file with parameter id or {@code null}.
     * @see File
     */
    @Override
    @Transactional
    public File initAndUpdate(
            final Long id,
            final String title,
            final String url,
            final MultipartFile multipartFile
    ) {
        final File file = get(id);
        if (multipartFile != null) {
            deleteFile(
                    file.getUrl()
            );
        }
        file.initialize(
                title,
                file.getUrl().contains(url)
                        ? file.getUrl() :
                        createRelativePath(
                                title, url,
                                multipartFile
                        )
        );
        if (multipartFile != null) {
            saveFile(
                    multipartFile,
                    file.getUrl()
            );
        }
        return update(file);
    }

    /**
     * Returns file object with the parameter title.
     * If title is blank then return {@code null}.
     *
     * @param title a title of the file to return.
     * @return The file with parameter title or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter title
     *                                  is blank.
     * @throws NullPointerException     Throw exception when object
     *                                  with parameter title is not exist.
     * @see File
     */
    @Override
    @Transactional(readOnly = true)
    public File getByTitle(final String title)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(title)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " title is blank!"
            );
        }
        final File media = this.dao.getByTitle(title);
        if (media == null) {
            throw new NullPointerException(
                    "Can`t find file object by title \"" + title + "\"!"
            );
        }
        return media;
    }

    /**
     * Returns file object with the parameter url.
     * If url is blank then return {@code null}.
     *
     * @param url a url of the file to return.
     * @return The file with parameter url or {@code null}.
     * @throws NullPointerException Throw exception when object
     *                              with parameter url is not exist.
     * @see File
     */
    @Override
    @Transactional(readOnly = true)
    public File getByUrl(final String url)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(url)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " url is blank!"
            );
        }
        final String path = createAbsolutePath(url);
        final File file = this.dao.getByUrl(path);
        if (file == null) {
            throw new NullPointerException(
                    "Can`t find file object by URL \"" + path + "\"!"
            );
        }
        return file;
    }

    /**
     * Removes file object with the parameter title.
     * Removes file if title is not blank.
     *
     * @param title a title of the file to remove.
     * @see File
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotBlank(title)) {
            this.dao.removeByTitle(title);
        }
    }

    /**
     * Removes file object with the parameter url.
     *
     * @param url a url of the file to remove.
     * @see File
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        remove(
                getByUrl(
                        createAbsolutePath(url)
                )
        );
    }

    /**
     * Removes file.
     * Removes file if it is not {@code null}.
     *
     * @param file the file to remove.
     * @see File
     */
    @Override
    @Transactional
    public void remove(final File file) {
        if (file != null) {
            deleteFile(
                    file.getUrl()
            );
            super.remove(file);
        }
    }

    /**
     * Removes files.
     * Removes files if are not empty.
     *
     * @param files The files to remove.
     * @see File
     */
    @Override
    @Transactional
    public void remove(final Collection<File> files) {
        if (files != null && !files.isEmpty()) {
            files.forEach(
                    this::remove
            );
        }
    }

    /**
     * Removes all files.
     *
     * @see File
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(
                getAll()
        );
    }

    /**
     * Saves the multipart file in the file system in the directory rootPath.
     * Saves file if it is not {@code null} and path is not blank.
     *
     * @param multipartFile the multipart file to save.
     * @param rootPath      a directory path.
     * @see File
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile multipartFile,
            final String rootPath
    ) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            new SaverImpl(
                    multipartFile,
                    createAbsolutePath(
                            isNotBlank(rootPath)
                                    ? rootPath
                                    : multipartFile.getOriginalFilename()
                    )
            ).write();
        }
    }

    /**
     * Saves the multipart file in the file system.
     * Saves file if it is not {@code null}.
     *
     * @param multipartFile the multipart file to save.
     * @see File
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile multipartFile
    ) {
        saveFile(
                multipartFile,
                null
        );
    }

    /**
     * Deletes file in the file system.
     * Deletes file if path is not blank.
     *
     * @param rootPath the file path.
     * @return Returns {@code true} if able to delete the file,
     * otherwise return {@code false}.
     * @see File
     */
    @Override
    @Transactional
    public boolean deleteFile(final String rootPath) {
        return new SaverImpl(
                createAbsolutePath(rootPath)
        ).delete();
    }

    /**
     * Updates and returns object of class {@link File}.
     * Updates file if file is not {@code null}.
     *
     * @param photo         the file to update.
     * @param multipartFile a multipart file to save.
     * @param title         a new title to the file
     * @param rootPath      a directory path.
     * @return The updating file.
     * @see File
     */
    @Override
    @Transactional
    public File updatePhoto(
            final File photo,
            final MultipartFile multipartFile,
            final String title,
            final String rootPath
    ) {
        if ((
                photo != null
        ) && (
                multipartFile != null
        ) && (
                !multipartFile.isEmpty()
        )) {
            deleteFile(
                    photo.getUrl()
            );
            photo.setTitle(
                    title + " " + multipartFile.getOriginalFilename()
            );
            photo.setUrl(
                    rootPath + "/" +
                            title.replace(" ", "_") +
                            "_" + multipartFile.getOriginalFilename()
            );
            update(photo);
            saveFile(
                    multipartFile,
                    photo.getUrl()
            );
        }
        return photo;
    }

    /**
     * Validates input file object.
     *
     * @param file              the file to valid.
     * @param requiredParameters is validate by required parameters.
     * @param exist              is validate by exists.
     * @param duplicate          is validate by duplicate.
     * @return {@code true} if file is valid, {@code false} otherwise.
     * @see File
     */
    @Override
    protected boolean validated(
            final File file,
            final boolean requiredParameters,
            final boolean exist,
            final boolean duplicate
    ) {
        if (file == null) {
            return false;
        }
        if (requiredParameters && !File.isValidated(file)) {
            return false;
        }
        if (exist && !exists(file)) {
            return false;
        }
        if (duplicate) {
            if ((
                    this.dao.getByTitle(
                            file.getTitle()
                    ) != null
            ) || (
                    this.dao.getByUrl(
                            file.getUrl()
                    ) != null
            )) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts and returns file objects by title.
     *
     * @param files the files to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of files.
     * @see Content
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> sortByTitle(
            final Collection<File> files,
            final boolean revers
    ) {
        return sort(
                files,
                new FileComparator.ByTitle<>(),
                revers
        );
    }

    /**
     * Return Class object of {@link File} class.
     *
     * @return The Class object of {@link File} class.
     */
    @Override
    protected Class<File> getModelClass() {
        return File.class;
    }

    /**
     * Creates and returns a absolute path to file.
     *
     * @param subPath a path to the file relative
     *                to resources directory.
     * @return The absolute path to file.
     */
    private String createAbsolutePath(final String subPath) {
        return this.properties.getResourcesAbsolutePath()
                + subPath;
    }

    /**
     * Creates ad returns a relative path to file.
     *
     * @param title         a file title.
     * @param url           a file URL.
     * @param multipartFile a multipart file.
     * @return The relative path to file.
     */
    private static String createRelativePath(
            final String title,
            final String url,
            final MultipartFile multipartFile
    ) {
        return (isNotBlank(url) ? url + "/" : "")
                + Translator.fromCyrillicToLatin(title)
                + getMultipartFileType(multipartFile);
    }

    /**
     * Returns a multipart file type.
     *
     * @param multipartFile a multipart file.
     * @return The multipart file type.
     */
    private static String getMultipartFileType(
            final MultipartFile multipartFile
    ) {
        final String name = multipartFile.getOriginalFilename();
        final String type = name.substring(
                name.lastIndexOf("."),
                name.length()
        );
        return isNotBlank(type) ? type : multipartFile.getContentType();
    }
}

