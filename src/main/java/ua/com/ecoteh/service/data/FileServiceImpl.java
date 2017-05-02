package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.enums.FileType;
import ua.com.ecoteh.repository.FileRepository;
import ua.com.ecoteh.util.comparator.FileComparator;
import ua.com.ecoteh.util.loader.MultipartFileLoader;
import ua.com.ecoteh.util.properties.ContentProperties;
import ua.com.ecoteh.util.translator.Translator;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

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
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.util.properties"
        }
)
public final class FileServiceImpl extends DataServiceImpl<File> implements FileService {

    /**
     * The message that a incoming title is null or empty.
     */
    private final static String BLANK_TITLE_MESSAGE =
            "Incoming %s title is null or empty!";

    /**
     * The message that a incoming URL is null or empty.
     */
    private final static String BLANK_URL_MESSAGE =
            "Incoming %s URL is null or empty!";

    /**
     * The message that a incoming type is null.
     */
    private final static String NULL_TYPE_MESSAGE =
            "Incoming %s type is null!";

    /**
     * The message that a static files are forbidden to remove.
     */
    private final static String FORBIDDEN_STATIC_FILE_MESSAGE =
            "Static files are forbidden to remove!";

    /**
     * The message that a incoming multipart file is null or empty.
     */
    private final static String MULTIPART_FILE_IS_EMPTY_MESSAGE =
            "Incoming multipart file is null or empty!";

    /**
     * The message that a incoming multipart file has size greater
     * than max sixe.
     */
    private final static String MAX_FILE_SIZE_MESSAGE =
            "Maximum file size must be no larger than %d bytes. " +
                    "Size of the incoming file is %d bytes.";

    /**
     * The interface provides a set of standard methods
     * for working {@link File} objects a the database.
     */
    private final FileRepository repository;

    /**
     * The implementation of the interface describes
     * the methods for getting application properties.
     */
    private final ContentProperties properties;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository a implementation of the {@link FileRepository} interface.
     * @param properties a implementation of the {@link ContentProperties} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public FileServiceImpl(
            final FileRepository repository,
            final ContentProperties properties
    ) {
        super(repository);
        this.repository = repository;
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
        if (isNotEmpty(multipartFile)) {
            isValidMultipartFile(multipartFile);
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
        if (isNotEmpty(multipartFile)) {
            isValidMultipartFile(multipartFile);
            deleteFile(file.getUrl());
            saveFile(multipartFile, file.getUrl());
        }
        return update(file);
    }

    /**
     * Initializes, updates and returns file with parameter id.
     *
     * @param id            a id of the file to update.
     * @param title         a new title to the file.
     * @param multipartFile a new multipart file to the file.
     * @return The updating file with parameter id or null.
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
     *
     * @param title a title of the file to return.
     * @return The file with parameter title.
     * @throws IllegalArgumentException Throw exception when parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByTitle(final String title) throws IllegalArgumentException {
        if (isEmpty(title)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_TITLE_MESSAGE, getClassSimpleName())
            );
        }
        return this.repository.findByTitle(title);
    }

    /**
     * Returns file object with the parameter url.
     *
     * @param url a url of the file to return.
     * @return The file with parameter url.
     * @throws IllegalArgumentException Throw exception when parameter url is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByUrl(final String url) throws IllegalArgumentException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException(
                    String.format(BLANK_URL_MESSAGE, getClassSimpleName())
            );
        }
        return this.repository.findByUrl(url);
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
        if (isNotEmpty(title)) {
            this.repository.deleteByTitle(title);
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
     * Removes file if it is not null.
     *
     * @param file the file to remove.
     */
    @Override
    @Transactional
    public void remove(final File file) throws IllegalArgumentException {
        if (isNotNull(file)) {
            if (!file.isValidated()) {
                throw new IllegalArgumentException(FORBIDDEN_STATIC_FILE_MESSAGE);
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
        if (isNotEmpty(files)) {
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
     * Saves a multipart file in the file system in a directory rootPath.
     * Saves file if it is not null and not empty.
     *
     * @param file     the multipart file to save.
     * @param rootPath a directory path.
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile file,
            final String rootPath
    ) {
        if (isNotEmpty(file)) {
            new MultipartFileLoader(
                    file, createAbsolutePath(file, rootPath)
            ).write();
        }
    }

    /**
     * Saves a multipart file in the file system.
     * Saves file if it is not null and not empty.
     *
     * @param file the multipart file to save.
     */
    @Override
    @Transactional
    public void saveFile(final MultipartFile file) {
        saveFile(file, file.getOriginalFilename());
    }

    /**
     * Deletes file in the file system.
     * Deletes file if path is not blank.
     *
     * @param path the file path.
     * @return Returns true if able to delete the file,
     * otherwise return false.
     */
    @Override
    @Transactional
    public boolean deleteFile(final String path) {
        return new MultipartFileLoader(
                this.properties.getProjectAbsolutePath() + path
        ).delete();
    }

    /**
     * Validates input file object.
     *
     * @param file      the file to valid.
     * @param exist     is validate by exists.
     * @param duplicate is validate by duplicate.
     * @return true if file is valid, false otherwise.
     */
    @Override
    protected boolean validated(
            final File file,
            final boolean exist,
            final boolean duplicate
    ) {
        if (isNull(file)) {
            return false;
        }
        if (exist && !exists(file)) {
            return false;
        }
        if (duplicate) {
            if (isNotNull(this.repository.findByTitle(file.getTitle())) ||
                    isNotNull(this.repository.findByUrl(file.getUrl()))) {
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
     * @throws IllegalArgumentException Throw exception when parameter type is null.
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> getByType(final FileType type) throws IllegalArgumentException {
        if (isNull(type)) {
            throw new IllegalArgumentException(
                    String.format(NULL_TYPE_MESSAGE, getClassSimpleName())
            );
        }
        return this.repository.findAllByType(type);
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
        if (isNotEmpty(files)) {
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

    /**
     * @param file
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */

    /**
     * Validated a multipart file.
     * File is valid if it is not null,
     * not empty and it has normal size.
     *
     * @param file the multipart file to check.
     * @throws NullPointerException     if the incoming file is null or empty.
     * @throws IllegalArgumentException if the incoming file size is greater
     *                                  than max file size
     */
    private void isValidMultipartFile(final MultipartFile file)
            throws NullPointerException, IllegalArgumentException {
        if (isEmpty(file)) {
            throw new NullPointerException(MULTIPART_FILE_IS_EMPTY_MESSAGE);
        }
        if (isGreatMaxSize(file.getSize())) {
            throw new IllegalArgumentException(
                    String.format(
                            MAX_FILE_SIZE_MESSAGE,
                            this.properties.getMaxFileSize(), file.getSize()
                    )
            );
        }
    }

    /**
     * Checks an incoming file size.
     * <pre>
     *     int maxSize = 1024; - for example
     *     int size = 2048;
     *     isGreatMaxSize(size) = true
     *
     *     size = 512;
     *     isGreatMaxSize(size) = false
     *     size = maxSize;
     *     isGreatMaxSize(size) = false
     * </pre>
     *
     * @param size the incoming file size.
     * @return true if size is greater than max file size,
     * false otherwise.
     */
    private boolean isGreatMaxSize(final long size) {
        return (size > this.properties.getMaxFileSize());
    }

    /**
     * Creates and returns a absolute path to a file.
     *
     * @param file     a multipart file.
     * @param rootPath a root directory path.
     * @return The relative path to file.
     */
    private String createAbsolutePath(
            final MultipartFile file,
            final String rootPath
    ) {
        return this.properties.getProjectAbsolutePath() +
                (isNotEmpty(rootPath) ? rootPath : file.getOriginalFilename());
    }

    /**
     * Creates and returns a relative path to a file.
     *
     * @param title a file title.
     * @param file  a multipart file.
     * @return The relative path to file.
     */
    private String createRelativePath(
            final String title,
            final MultipartFile file
    ) {
        return this.properties.getResourcesLocation() +
                Translator.fromCyrillicToLatin(title) +
                new Random().nextInt() +
                getMultipartFileType(file);
    }

    /**
     * Returns a multipart file type.
     *
     * @param file a multipart file.
     * @return The multipart file type.
     */
    private static String getMultipartFileType(final MultipartFile file) {
        String type;
        final String name = file.getOriginalFilename();
        if (name.contains(".")) {
            type = name.substring(name.lastIndexOf("."), name.length());
        } else {
            type = "";
        }
        return type;
    }
}