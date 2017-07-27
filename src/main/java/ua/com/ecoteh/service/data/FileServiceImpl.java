package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.file.*;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.FileRepository;
import ua.com.ecoteh.service.data.comparator.FileComparator;
import ua.com.ecoteh.util.generator.Generator;
import ua.com.ecoteh.util.generator.StringGenerator;
import ua.com.ecoteh.util.loader.Loader;
import ua.com.ecoteh.util.loader.MultipartFileLoader;
import ua.com.ecoteh.config.properties.ContentProperties;
import ua.com.ecoteh.util.translator.Translator;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link File} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see File
 * @see FileEntity
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.config.properties"
        }
)
public final class FileServiceImpl extends DataServiceImpl<File, FileEntity> implements FileService {

    /**
     * The interface provides a set of standard methods
     * for working {@link FileEntity} objects a the database.
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
     * @param repository the implementation of the {@link FileRepository} interface.
     * @param properties the implementation of the {@link ContentProperties} interface.
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
     * Saves and returns a new file.
     *
     * @param file the title of a new file.
     * @return The new saving file (newer null).
     */
    @Override
    @Transactional
    public File add(final File file) {
        if (isNull(file)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final MultipartFile multipartFile = file.getMultipartFile();
        final String path = saveFile(multipartFile);
        final FileEntity entity = file.convert();
        entity.setUrl(path);
        final FileEntity savingEntity = this.repository.save(entity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Saves and returns files.
     * Returns empty collection if files is empty.
     * <pre>
     *     add(null) = empty ArrayList()
     *     add(new ArrayList()) = empty ArrayList()
     *
     *     Collection models = new ArrayList();
     *     models.add(new Model());
     *     add(models) = saving models
     * </pre>
     *
     * @param files the files to add.
     * @return The saving files or empty collection.
     */
    @Override
    @Transactional
    public Collection<File> add(final Collection<File> files) {
        final Collection<File> result;
        if (isNotEmpty(files)) {
            final Collection<FileEntity> entities = convertToEntities(files);
            final Collection<FileEntity> savingEntities = this.repository.save(entities);
            result = convertToModels(savingEntities);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    /**
     * Initializes, updates and returns photo with incoming id.
     *
     * @param file the id of a file to update.
     * @return The updating photo with incoming id (newer null).
     * @throws IllegalArgumentException Throw exception when file is static.
     */
    @Override
    @Transactional
    public File update(final File file) {
        if (isNull(file)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final File old = get(file.getId());
        final FileEntity fileEntity = updateFile(file, old);
        final FileEntity savingEntity = this.repository.save(fileEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Returns file object with the incoming title.
     * If a incoming title is null or empty then throws IllegalArgumentException.
     *
     * @param title the title of a file to return.
     * @return The file with incoming title (newer null).
     * @throws IllegalArgumentException Throw exception when parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByTitle(final String title) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_TITLE_MESSAGE,
                    getClassSimpleName()
            );
        }
        final FileEntity entity = this.repository.findByTitle(title);
        if (isNull(entity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), title
            );
        }
        return convertToModel(entity);
    }

    /**
     * Returns file object with the incoming URL.
     * If a incoming URL is null or empty then throws IllegalArgumentException.
     *
     * @param url the URL of a file to return.
     * @return The file with incoming URL (newer null).
     * @throws IllegalArgumentException Throw exception when parameter URL is blank.
     * @throws NullPointerException     Throw exception when object with parameter URL is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_URL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final FileEntity entity = this.repository.findByUrl(url);
        if (isNull(entity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return convertToModel(entity);
    }

    /**
     * Removes file object with the incoming id.
     *
     * @param id the id of a file to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean remove(final long id) {
        boolean result = id > 0;
        if (id > 0) {
            final File file = get(id);
            result = remove(file);
        }
        return result;
    }

    /**
     * Removes file object with the incoming title.
     * Removes file if title is not null and not empty.
     *
     * @param title the title of a file to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean removeByTitle(final String title) {
        boolean result = isNotEmpty(title);
        if (isNotEmpty(title)) {
            final File file = getByTitle(title);
            result = remove(file);
        }
        return result;
    }

    /**
     * Removes file object with the incoming URL.
     *
     * @param url the URL of a file to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean removeByUrl(final String url) {
        boolean result = isNotEmpty(url);
        if (isNotEmpty(url)) {
            final File file = getByUrl(url);
            result = remove(file);
        }
        return result;
    }

    /**
     * Removes file if it is not null.
     * If incoming file is static then throws IllegalArgumentException.
     *
     * @param file the file to remove.
     * @return true if model is deleted, false otherwise.
     * @throws IllegalArgumentException if the incoming file is static.
     */
    @Override
    @Transactional
    public boolean remove(final File file) throws IllegalArgumentException {
        boolean result = isNotNull(file);
        if (isNotNull(file)) {
            if (isStaticFile(file)) {
                throw getIllegalArgumentException(ExceptionMessage.FORBIDDEN_STATIC_FILE_MESSAGE);
            }
            result = super.remove(file) && deleteFile(file.getUrl());
        }
        return result;
    }

    /**
     * Removes files if are not empty.
     *
     * @param files the files to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean remove(final Collection<File> files) {
        boolean result = isNotEmpty(files);
        if (result) {
            files.forEach(this::remove);
        }
        return result;
    }

    /**
     * Removes all files.
     */
    @Override
    @Transactional
    public void removeAll() {
        final Collection<File> files = getAll();
        remove(files);
    }

    /**
     * Saves a multipart file in the file system in a directory rootPath.
     * Saves file if it is not null and not empty.
     *
     * @param file     the multipart file to save.
     * @param rootPath the root directory path.
     * @return the relative path to the saving file.
     */
    @Override
    @Transactional
    public String saveFile(final MultipartFile file, final String rootPath) {
        checkMultipartFile(file);
        final String relativePath = createRelativePath(rootPath, file);
        final String absolutePath = createAbsolutePath(relativePath);
        final Loader loader = new MultipartFileLoader(file, absolutePath);
        loader.write();
        return relativePath;
    }

    /**
     * Saves a multipart file in the file system.
     * Saves file if it is not null and not empty.
     *
     * @param file the multipart file to save.
     * @return the path to the saving file.
     */
    @Override
    @Transactional
    public String saveFile(final MultipartFile file) {
        checkMultipartFile(file);
        final String rootPath = file.getOriginalFilename();
        return saveFile(file, rootPath);
    }

    /**
     * Deletes file in the file system.
     *
     * @param path the file path.
     * @return Returns true if able to delete the file,
     * otherwise return false.
     */
    @Override
    @Transactional
    public boolean deleteFile(final String path) {
        final String absolutePath = createAbsolutePath(path);
        final Loader loader = new MultipartFileLoader(absolutePath);
        return loader.delete();
    }

    /**
     * Sorts and returns file objects by title.
     * For sorting used {@link FileComparator.ByTitle} comparator.
     *
     * @param files  the files to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of files (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> sortByTitle(final Collection<File> files, final boolean revers) {
        final Comparator<File> comparator = new FileComparator.ByTitle();
        return sort(files, comparator, revers);
    }

    /**
     * Returns files with the incoming type.
     * If a incoming type is null then throws IllegalArgumentException.
     *
     * @param type the type of files to return.
     * @return The files with the type (newer null).
     * @throws IllegalArgumentException Throw exception when parameter type is null.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<File> getByType(final FileType type) throws IllegalArgumentException {
        if (isNull(type)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_TYPE_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final Collection<FileEntity> entities = this.repository.findAllByType(type);
        return convertToModels(entities);
    }

    /**
     * Returns last file with the type.
     *
     * @param type the type of file to return.
     * @return The last file with the type (newer null).
     * @throws IllegalArgumentException Throw exception when parameter type is null.
     */
    @Override
    @Transactional(readOnly = true)
    public File getLastByType(final FileType type) throws IllegalArgumentException {
        if (isNull(type)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_TYPE_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final FileEntity entity = this.repository.findLastByType(type);
        final File file;
        if (isNotNull(entity)) {
            file = entity.convert();
        } else {
            final FileBuilder builder = File.getBuilder();
            file = builder.build();
        }
        return file;
    }

    /**
     * Return Class object of {@link FileEntity} class.
     *
     * @return The Class object of {@link FileEntity} class.
     */
    @Override
    Class<File> getModelClass() {
        return File.class;
    }

    /**
     * Validated a multipart file. FileEntity is valid if it is not null, not empty and
     * it has normal size.
     * If an incoming multipart file is null or empty then throws NullPointerException.
     * If an incoming multipart file size is greater than max file size then
     * throws IllegalArgumentException.
     *
     * @param file the multipart file to check.
     * @throws NullPointerException     if the incoming file is null or empty.
     * @throws IllegalArgumentException if the incoming file size is greater
     *                                  than max file size.
     */
    private void checkMultipartFile(final MultipartFile file)
            throws NullPointerException, IllegalArgumentException {
        if (isEmpty(file)) {
            throw getNullPointerException(ExceptionMessage.MULTIPART_FILE_IS_EMPTY_MESSAGE);
        }
        if (isGreatMaxSize(file.getSize())) {
            throw getIllegalArgumentException(
                    ExceptionMessage.MAX_FILE_SIZE_MESSAGE,
                    getMaxFileSize(), file.getSize()
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
        return (size > getMaxFileSize());
    }

    /**
     * Returns a maximum file size.
     *
     * @return The maximum file size.
     */
    private long getMaxFileSize() {
        return this.properties.getMaxFileSize();
    }

    /**
     * Creates and returns a absolute path to a file.
     *
     * @param file     the multipart file.
     * @param rootPath the root directory path.
     * @return The absolute path to file (newer null).
     */
    private String createAbsolutePath(final MultipartFile file, final String rootPath) {
        return createAbsolutePath(rootPath) + (isEmpty(rootPath) ? file.getOriginalFilename() : "");
    }

    /**
     * Creates and returns a absolute path.
     *
     * @param rootPath the root directory path.
     * @return The absolute path (newer null).
     */
    private String createAbsolutePath(final String rootPath) {
        return this.properties.getProjectAbsolutePath() + rootPath;
    }

    /**
     * Creates and returns a relative path to a file.
     *
     * @param title the file title.
     * @param file  the multipart file.
     * @return The relative path to file (newer null).
     */
    private String createRelativePath(final String title, final MultipartFile file) {
        final Generator<String> generator = new StringGenerator();
        return this.properties.getResourcesLocation() +
                Translator.fromCyrillicToLatin(title) +
                generator.generate() +
                getMultipartFileType(file);
    }

    /**
     * Returns a multipart file type.
     * If file title "FileEntity.txt" them returns ".txt".
     * If file title "FileEntity" them returns "" (empty string).
     *
     * @param file the multipart file (newer null).
     * @return The multipart file type or empty string (newer null).
     */
    private String getMultipartFileType(final MultipartFile file) {
        String type;
        final String name = file.getOriginalFilename();
        if (name.contains(".")) {
            type = name.substring(name.lastIndexOf("."), name.length());
        } else {
            type = "";
        }
        return type;
    }

    /**
     * Checks an incoming file is static or not.
     *
     * @param file the file to check (newer null).
     * @return true if file is static, false otherwise.
     */
    private boolean isStaticFile(final File file) {
        return file.getType().equals(FileType.STATIC);
    }

    /**
     * Updates a users.
     *
     * @param from the user to copy.
     * @param to   the user to update.
     * @return the updated user entity.
     */
    private FileEntity updateFile(final File from, final File to) {
        final FileEditor editor = to.getEditor();
        if (isNotEmpty(from.getMultipartFile())) {
            final File updatedPhoto = updateLogo(from, to);
            editor.copy(updatedPhoto);
        }
        final File updatedFile = editor.update();
        return convertToEntity(updatedFile);
    }

    /**
     * Updated a photos.
     *
     * @param from the photo to copy.
     * @param to   the photo to update.
     * @return the updated photo.
     */
    private File updateLogo(final File from, final File to) {
        deleteFile(to.getUrl());
        final String url = saveFile(from.getMultipartFile());
        final FileEditor fileEditor = to.getEditor();
        fileEditor.addUrl(url);
        return fileEditor.update();
    }
}
