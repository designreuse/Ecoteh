package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.FileDao;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.enums.FileType;
import com.salimov.yurii.service.data.interfaces.FileService;
import com.salimov.yurii.util.comparator.FileComparator;
import com.salimov.yurii.util.loader.MultipartFileLoader;
import com.salimov.yurii.util.properties.ContentProperties;
import com.salimov.yurii.util.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.util.properties"
        }
)
public final class FileServiceImpl extends DataServiceImpl<File> implements FileService {

    /**
     * The interface provides a set of standard methods
     * for working {@link File} objects a the database.
     */
    private final FileDao dao;

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao        a implementation of the {@link FileDao} interface.
     * @param properties a implementation of the {@link ContentProperties} interface.
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
     * @param type          a type of the new file.
     * @param multipartFile a multipart file of the new file.
     * @return The new saving file.
     */
    @Override
    @Transactional
    public File add(
            final String title,
            final FileType type,
            final MultipartFile multipartFile
    ) {
        File file;
        if ((multipartFile != null) && !multipartFile.isEmpty()) {
            checkMultipartFile(multipartFile);
            file = new File(title, createRelativePath(title, multipartFile));
            file.setType(type);
            saveFile(multipartFile, file.getUrl());
            file = add(file);
        } else {
            file = new File();
        }
        return file;
    }

    /**
     * Initializes, saves and returns a new file.
     *
     * @param title         a title of the new file.
     * @param multipartFile a multipart file of the new file.
     * @return The new saving file.
     */
    @Override
    @Transactional
    public File add(
            final String title,
            final MultipartFile multipartFile
    ) {
        return add(title, FileType.OTHER, multipartFile);
    }

    /**
     * Initializes, updates and returns photo with parameter id.
     *
     * @param id            a id of the photo to update.
     * @param title         a new title to the file.
     * @param type          a type of the new file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating photo with parameter id.
     * @throws IllegalArgumentException Throw exception when file is static.
     */
    @Override
    @Transactional
    public File update(
            final long id,
            final String title,
            final FileType type,
            final MultipartFile multipartFile
    ) {
        final File file = get(id);
        file.setTitle(title);
        if (!file.getType().equals(FileType.STATIC)) {
            file.setType(type);
        }
        if (multipartFile != null && !multipartFile.isEmpty()) {
            checkMultipartFile(multipartFile);
            deleteFile(file.getUrl());
            saveFile(multipartFile, file.getUrl());
        }
        return update(file);
    }

    /**
     * Initializes, updates and returns file with parameter id.
     * Returns {@code null} if id is {@code null}.
     *
     * @param id            a id of the file to update.
     * @param title         a new title to the file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating file with parameter id or {@code null}.
     */
    @Override
    @Transactional
    public File update(
            final long id,
            final String title,
            final MultipartFile multipartFile
    ) {
        return update(id, title, FileType.OTHER, multipartFile);
    }

    /**
     * Returns file object with the parameter title.
     * If title is blank then return {@code null}.
     *
     * @param title a title of the file to return.
     * @return The file with parameter title or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByTitle(final String title) throws IllegalArgumentException {
        if (isBlank(title)) {
            throw new IllegalArgumentException(getClassSimpleName() + " title is blank!");
        }
        return this.dao.getByTitle(title);
    }

    /**
     * Returns file object with the parameter url.
     * If url is blank then return {@code null}.
     *
     * @param url a url of the file to return.
     * @return The file with parameter url or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter url is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByUrl(final String url) throws IllegalArgumentException {
        if (isBlank(url)) {
            throw new IllegalArgumentException(getClassSimpleName() + " URL is blank!");
        }
        return this.dao.getByUrl(url);
    }

    /**
     * Removes file object with the parameter title.
     * Removes file if title is not blank.
     *
     * @param title a title of the file to remove.
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
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        remove(getByUrl(url));
    }

    /**
     * Removes file if it is not {@code null}.
     *
     * @param file the file to remove.
     */
    @Override
    @Transactional
    public void remove(final File file) throws IllegalArgumentException {
        if (file != null) {
            if (!file.isValidated()) {
                throw new IllegalArgumentException("Static files forbidden to remove!");
            }
            deleteFile(file.getUrl());
            super.remove(file);
        }
    }

    /**
     * Removes files if are not empty.
     *
     * @param files The files to remove.
     */
    @Override
    @Transactional
    public void remove(final Collection<File> files) {
        if (files != null && !files.isEmpty()) {
            files.forEach(this::remove);
        }
    }

    /**
     * Removes all files.
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(getAll());
    }

    /**
     * Saves the multipart file in the file system in the directory rootPath.
     * Saves file if it is not {@code null} and not empty.
     *
     * @param multipartFile the multipart file to save.
     * @param rootPath      a directory path.
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile multipartFile,
            final String rootPath
    ) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            new MultipartFileLoader(
                    multipartFile,
                    this.properties.getProjectAbsolutePath()
                            + (isNotBlank(rootPath) ? rootPath : multipartFile.getOriginalFilename())
            ).write();
        }
    }

    /**
     * Saves the multipart file in the file system.
     * Saves file if it is not {@code null}.
     *
     * @param multipartFile the multipart file to save.
     */
    @Override
    @Transactional
    public void saveFile(final MultipartFile multipartFile) {
        saveFile(multipartFile, multipartFile.getOriginalFilename());
    }

    /**
     * Deletes file in the file system.
     * Deletes file if path is not blank.
     *
     * @param rootPath the file path.
     * @return Returns {@code true} if able to delete the file,
     * otherwise return {@code false}.
     */
    @Override
    @Transactional
    public boolean deleteFile(final String rootPath) {
        return new MultipartFileLoader(
                this.properties.getProjectAbsolutePath() + rootPath
        ).delete();
    }

    /**
     * Validates input file object.
     *
     * @param file      the file to valid.
     * @param exist     is validate by exists.
     * @param duplicate is validate by duplicate.
     * @return {@code true} if file is valid, {@code false} otherwise.
     */
    @Override
    protected boolean validated(
            final File file,
            final boolean exist,
            final boolean duplicate
    ) {
        if (file == null) {
            return false;
        }
        if (exist && !exists(file)) {
            return false;
        }
        if (duplicate) {
            if ((this.dao.getByTitle(file.getTitle()) != null)
                    || (this.dao.getByUrl(file.getUrl()) != null)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Sorts and returns file objects by title.
     *
     * @param files  the files to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of files.
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> sortByTitle(
            final Collection<File> files,
            final boolean revers
    ) {
        return sort(files, new FileComparator.ByTitle<>(), revers);
    }

    /**
     * Returns files with the type.
     *
     * @param type a type of files to return.
     * @return The files with the type.
     * @throws IllegalArgumentException Throw exception when parameter type is {@code null}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> getByType(final FileType type) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("File type is null");
        }
        return this.dao.getByFileType(type);
    }

    /**
     * Returns last file with the type.
     *
     * @param type a type of file to return.
     * @return The last file with the type.
     */
    @Override
    @Transactional(readOnly = true)
    public File getLastByType(final FileType type) {
        final List<File> files = getByType(type);
        File file;
        if (!files.isEmpty()) {
            file = files.get(files.size() - 1);
        } else {
            file = new File();
        }
        return file;
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

    private void checkMultipartFile(final MultipartFile multipartFile)
            throws NullPointerException, IllegalArgumentException {
        if ((multipartFile == null) || multipartFile.isEmpty()) {
            throw new NullPointerException("Can`t find multipart file!");
        }
        if (multipartFile.getSize() > this.properties.getMaxFileSize()) {
            throw new IllegalArgumentException(
                    "Maximum file size must be no larger than " + this.properties.getMaxFileSize() + " bytes. "
                            + "Size of the input file is " + multipartFile.getSize() + " bytes."
            );
        }
    }

    /**
     * Creates ad returns a relative path to file.
     *
     * @param title         a file title.
     * @param multipartFile a multipart file.
     * @return The relative path to file.
     */
    private String createRelativePath(
            final String title,
            final MultipartFile multipartFile
    ) {
        return this.properties.getResourcesLocation() +
                Translator.fromCyrillicToLatin(title) +
                new Random().nextInt() +
                getMultipartFileType(multipartFile);
    }

    /**
     * Returns a multipart file type.
     *
     * @param multipartFile a multipart file.
     * @return The multipart file type.
     */
    private static String getMultipartFileType(final MultipartFile multipartFile) {
        String type;
        final String name = multipartFile.getOriginalFilename();
        if (name.contains(".")) {
            type = name.substring(name.lastIndexOf("."), name.length());
        } else {
            type = "";
        }
        return type;
    }
}
