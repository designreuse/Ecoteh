package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.file.FileType;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.FileRepository;
import ua.com.ecoteh.util.comparator.FileComparator;
import ua.com.ecoteh.util.loader.MultipartFileLoader;
import ua.com.ecoteh.util.properties.ContentProperties;
import ua.com.ecoteh.util.translator.Translator;

import java.util.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link FileEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.util.properties"
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
     * Saves and returns a new fileEntity.
     *
     * @param file the title of a new fileEntity.
     * @return The new saving fileEntity (newer null).
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
        final File savingFile;
        final MultipartFile multipartFile = file.getMultipartFile();
        if (isNotEmpty(file.getMultipartFile())) {
            isValidMultipartFile(multipartFile);
            saveFile(multipartFile, file.getUrl());
            savingFile = super.add(file);
        } else {
            savingFile = File.getBuilder().build();
        }
        return savingFile;
    }

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
     * @param file the id of a fileEntity to update.
     * @return The updating photo with incoming id (newer null).
     * @throws IllegalArgumentException Throw exception when fileEntity is static.
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
        final File savingFile;
        final MultipartFile multipartFile = file.getMultipartFile();
        if (isNotEmpty(file.getMultipartFile())) {
            isValidMultipartFile(multipartFile);
            final File toUpdateFile = get(file.getId());
            deleteFile(toUpdateFile.getUrl());
            saveFile(multipartFile, file.getUrl());
            savingFile = super.update(file);
        } else {
            savingFile = File.getBuilder().build();
        }
        return savingFile;
    }

    /**
     * Returns fileEntity object with the incoming title.
     * If a incoming title is null or empty then throws IllegalArgumentException.
     *
     * @param title the title of a fileEntity to return.
     * @return The fileEntity with incoming title (newer null).
     * @throws IllegalArgumentException Throw exception when parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByTitle(final String title) throws IllegalArgumentException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_TITLE_MESSAGE,
                    getClassSimpleName()
            );
        }
        final FileEntity fileEntity = this.repository.findByTitle(title);
        return convertToModel(fileEntity);
    }

    /**
     * Returns fileEntity object with the incoming URL.
     * If a incoming URL is null or empty then throws IllegalArgumentException.
     *
     * @param url the URL of a fileEntity to return.
     * @return The fileEntity with incoming URL (newer null).
     * @throws IllegalArgumentException Throw exception when parameter URL is blank.
     */
    @Override
    @Transactional(readOnly = true)
    public File getByUrl(final String url) throws IllegalArgumentException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_URL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final FileEntity fileEntity = this.repository.findByUrl(url);
        return convertToModel(fileEntity);
    }

    /**
     * Removes fileEntity object with the incoming title.
     * Removes fileEntity if title is not null and not empty.
     *
     * @param title the title of a fileEntity to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            this.repository.deleteByTitle(title);
        }
    }

    /**
     * Removes fileEntity object with the incoming URL.
     *
     * @param url the URL of a fileEntity to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        remove(getByUrl(url));
    }

    /**
     * Removes fileEntity if it is not null.
     * If incoming fileEntity is static then throws IllegalArgumentException.
     *
     * @param file the fileEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final File file) throws IllegalArgumentException {
        if (isNotNull(file)) {
            if (isStaticFile(file)) {
                throw getIllegalArgumentException(ExceptionMessage.FORBIDDEN_STATIC_FILE_MESSAGE);
            }
            deleteFile(file.getUrl());
            super.remove(file);
        }
    }

    /**
     * Removes fileEntities if are not empty.
     *
     * @param files the fileEntities to remove.
     */
    @Override
    @Transactional
    public void remove(final Collection<File> files) {
        if (isNotEmpty(files)) {
            files.forEach(this::remove);
        }
    }

    /**
     * Removes all fileEntities.
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(getAll());
    }

    /**
     * Saves a multipart fileEntity in the fileEntity system in a directory rootPath.
     * Saves fileEntity if it is not null and not empty.
     *
     * @param file     the multipart fileEntity to save.
     * @param rootPath the root directory path.
     */
    @Override
    @Transactional
    public void saveFile(
            final MultipartFile file,
            final String rootPath
    ) {
        if (isNotEmpty(file)) {
            final String absolutePath = createAbsolutePath(file, rootPath);
            new MultipartFileLoader(file, absolutePath).write();
        }
    }

    /**
     * Saves a multipart fileEntity in the fileEntity system.
     * Saves fileEntity if it is not null and not empty.
     *
     * @param file the multipart fileEntity to save.
     */
    @Override
    @Transactional
    public void saveFile(final MultipartFile file) {
        saveFile(file, file.getOriginalFilename());
    }

    /**
     * Deletes fileEntity in the fileEntity system.
     *
     * @param path the fileEntity path.
     * @return Returns true if able to delete the fileEntity,
     * otherwise return false.
     */
    @Override
    @Transactional
    public boolean deleteFile(final String path) {
        final String absolutePath = createAbsolutePath(path);
        return new MultipartFileLoader(absolutePath).delete();
    }

    /**
     * Sorts and returns fileEntity objects by title.
     * For sorting used {@link FileComparator.ByTitle} comparator.
     *
     * @param fileEntities the fileEntities to sort.
     * @param revers       is sort in descending or ascending.
     * @return The sorted list of fileEntities (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<File> sortByTitle(
            final Collection<File> fileEntities,
            final boolean revers
    ) {
        final Comparator<File> byTitleComparator = new FileComparator.ByTitle();
        return sort(fileEntities, byTitleComparator, revers);
    }

    /**
     * Returns fileEntities with the incoming type.
     * If a incoming type is null then throws IllegalArgumentException.
     *
     * @param type the type of fileEntities to return.
     * @return The fileEntities with the type (newer null).
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
        final Collection<FileEntity> fileEntities = this.repository.findAllByType(type);
        return convertToModels(fileEntities);
    }

    /**
     * Returns last fileEntity with the type.
     *
     * @param type the type of fileEntity to return.
     * @return The last fileEntity with the type (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public File getLastByType(final FileType type) {
        final List<File> files = (List<File>) getByType(type);
        final File file;
        if (isNotEmpty(files)) {
            file = files.get(files.size() - 1);
        } else {
            file = File.getBuilder().build();
        }
        return file;
    }

    /**
     * Return Class object of {@link FileEntity} class.
     *
     * @return The Class object of {@link FileEntity} class.
     */
    @Override
    protected Class<File> getModelClass() {
        return File.class;
    }

    /**
     * Validated a multipart fileEntity. FileEntity is valid if it is not null, not empty and
     * it has normal size.
     * If an incoming multipart fileEntity is null or empty then throws NullPointerException.
     * If an incoming multipart fileEntity size is greater than max fileEntity size then
     * throws IllegalArgumentException.
     *
     * @param file the multipart fileEntity to check.
     * @throws NullPointerException     if the incoming fileEntity is null or empty.
     * @throws IllegalArgumentException if the incoming fileEntity size is greater
     *                                  than max fileEntity size.
     */
    private void isValidMultipartFile(final MultipartFile file)
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
     * Checks an incoming fileEntity size.
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
     * @param size the incoming fileEntity size.
     * @return true if size is greater than max fileEntity size,
     * false otherwise.
     */
    private boolean isGreatMaxSize(final long size) {
        return (size > getMaxFileSize());
    }

    /**
     * Returns a maximum fileEntity size.
     *
     * @return The maximum fileEntity size.
     */
    private long getMaxFileSize() {
        return this.properties.getMaxFileSize();
    }

    /**
     * Creates and returns a absolute path to a fileEntity.
     *
     * @param file     the multipart fileEntity.
     * @param rootPath the root directory path.
     * @return The absolute path to fileEntity (newer null).
     */
    private String createAbsolutePath(final MultipartFile file, final String rootPath) {
        return createAbsolutePath(rootPath) +
                (isEmpty(rootPath) ? file.getOriginalFilename() : "");
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
     * Creates and returns a relative path to a fileEntity.
     *
     * @param title the fileEntity title.
     * @param file  the multipart fileEntity.
     * @return The relative path to fileEntity (newer null).
     */
    private String createRelativePath(final String title, final MultipartFile file) {
        return this.properties.getResourcesLocation() +
                Translator.fromCyrillicToLatin(title) +
                new Random().nextInt() +
                getMultipartFileType(file);
    }

    /**
     * Returns a multipart fileEntity type.
     * If fileEntity title "FileEntity.txt" them returns ".txt".
     * If fileEntity title "FileEntity" them returns "" (empty string).
     *
     * @param file the multipart fileEntity (newer null).
     * @return The multipart fileEntity type or empty string (newer null).
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
     * Checks an incoming fileEntity is static or not.
     *
     * @param file the fileEntity to check (newer null).
     * @return true if fileEntity is static, false otherwise.
     */
    private boolean isStaticFile(final File file) {
        return file.getType().equals(FileType.STATIC);
    }
}
