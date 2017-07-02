package ua.com.ecoteh.service.data;

import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.content.ContentEditor;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.ContentRepository;
import ua.com.ecoteh.service.data.comparator.ContentComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Content} class or subclass.
 *
 * @param <T> model type, extends {@link Content}.
 * @param <E> entity type, extends {@link ContentEntity}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Content
 * @see ContentEntity
 */
public abstract class ContentServiceImpl<T extends Content, E extends ContentEntity>
        extends DataServiceImpl<T, E> implements ContentService<T> {

    /**
     * The object provides a set of standard JPA methods
     * for working {@link ContentEntity} objects with the database.
     */
    private final ContentRepository<E> repository;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link FileEntity}.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository  the implementation of the {@link ContentRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
     */
    ContentServiceImpl(
            final ContentRepository<E> repository,
            final FileService fileService
    ) {
        super(repository);
        this.repository = repository;
        this.fileService = fileService;
    }

    /**
     * Saves and returns object of {@link Content} class or subclasses.
     * If can`t find model then throws NullPointerException.
     *
     * @param content the content to add.
     * @return The saving model (newer null).
     * @throws IllegalArgumentException Throw exception when incoming model is null.
     * @throws NullPointerException     Throw exception when saving modelEntity is null.
     */
    @Override
    @Transactional
    public T add(final T content) throws IllegalArgumentException, NullPointerException {
        if (isNull(content)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final File file = content.getLogo();
        final String path = this.fileService.saveFile(file.getMultipartFile());
        final E contentEntity = convertToEntity(content);
        final FileEntity fileEntity = contentEntity.getLogoEntity();
        fileEntity.setUrl(path);
        final E savingEntity = this.repository.save(contentEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Initializes, updates and returns content.
     * If a incoming content is null then throws IllegalArgumentException.
     *
     * @param content the content object to update.
     * @return The updating content with parameter id (newer null).
     * @throws IllegalArgumentException Throw exception when input content is null.
     */
    @Override
    @Transactional
    public T update(final T content) throws IllegalArgumentException {
        if (isNull(content)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final T old = getByUrl(content.getUrl(), false);
        final E contentEntity = updateContent(content, old);
        final E savingEntity = this.repository.save(contentEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Returns object of {@link Content} or subclasses with the incoming title.
     * If a incoming title is null or empty then throws IllegalArgumentException.
     * If can`t find content by incoming title then throws NullPointerException.
     *
     * @param title   the title of a content to return.
     * @param isValid is get valid content or not.
     * @return The content with incoming title (newer null).
     * @throws IllegalArgumentException Throw exception when object parameter title is blank.
     * @throws NullPointerException     Throw exception when object with parameter title is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public T getByTitle(final String title, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_TITLE_MESSAGE,
                    getClassSimpleName()
            );
        }
        final E entity = this.repository.findByTitle(title);
        if (isNotValidated(entity, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), title
            );
        }
        return convertToModel(entity);
    }

    /**
     * Returns object of {@link Content} or subclasses with the incoming URL.
     * If a incoming URL is null or empty then throws IllegalArgumentException.
     * If can`t find content by incoming URL then throws NullPointerException.
     *
     * @param url     the URL of a content to return.
     * @param isValid is get valid content or not.
     * @return The content with incoming URL (newer null).
     * @throws IllegalArgumentException Throw exception when object parameter url is blank.
     * @throws NullPointerException     Throw exception when object with parameter url is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public T getByUrl(final String url, final boolean isValid)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_URL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final E entity = this.repository.findByUrl(url);
        if (isNotValidated(entity, isValid)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return convertToModel(entity);
    }

    /**
     * Removes object of {@link Content} or subclasses with the incoming title.
     * Removes content if title is not blank.
     *
     * @param title the title of a content to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            final T content = getByTitle(title, false);
            remove(content);
        }
    }

    /**
     * Removes object of {@link Content} or subclasses with the incoming URL.
     *
     * @param url the URL of a content to remove.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            final T content = getByUrl(url, false);
            remove(content);
        }
    }

    /**
     * Removes object of {@link Content} or subclasses.
     * Removes content if it is not null.
     *
     * @param content the content to remove.
     */
    @Override
    @Transactional
    public void remove(final T content) {
        if (isNotNull(content)) {
            super.remove(content);
            final File logo = content.getLogo();
            this.fileService.deleteFile(logo.getUrl());
        }
    }

    /**
     * Removes objects of {@link Content} class or subclasses.
     * Removes contents if are not null.
     *
     * @param contents the users to remove.
     */
    @Override
    @Transactional
    public void remove(final Collection<T> contents) {
        if (isNotEmpty(contents)) {
            contents.forEach(this::remove);
        }
    }

    /**
     * Removes all objects of {@link Content} class or subclasses.
     */
    @Override
    @Transactional
    public void removeAll() {
        final Collection<T> contents = getAll(false);
        contents.forEach(this::remove);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by title.
     * For sorting used {@link ContentComparator.ByTitle} comparator.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
     * @see ContentComparator.ByTitle
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByTitle(final Collection<T> contents, final boolean revers) {
        final Comparator<T> comparator = new ContentComparator.ByTitle<>();
        return sort(contents, comparator, revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     * For sorting used {@link ContentComparator.ByUrl} comparator.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
     * @see ContentComparator.ByUrl
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByUrl(final Collection<T> contents, final boolean revers) {
        final Comparator<T> comparator = new ContentComparator.ByUrl<>();
        return sort(contents, comparator, revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by title.
     * For sorting used {@link ContentComparator.ByTitle} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
     * @see ContentComparator.ByTitle
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByTitle(final boolean revers) {
        final Collection<T> contents = getAll();
        return sortByTitle(contents, revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     * For sorting used {@link ContentComparator.ByUrl} comparator.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
     * @see ContentComparator.ByUrl
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByUrl(final boolean revers) {
        final Collection<T> contents = getAll();
        return sortByUrl(contents, revers);
    }

    /**
     * Updates a contents.
     *
     * @param from the content to copy.
     * @param to   the content to update.
     * @return the updated content entity.
     */
    E updateContent(final T from, final T to) {
        final ContentEditor<T, ?> editor = to.getEditor();
        editor.copy(from);
        final File newLogo = from.getLogo();
        if (isNotEmpty(newLogo.getMultipartFile())) {
            final File oldLogo = to.getLogo();
            final File updatedLogo = updateLogo(oldLogo, newLogo);
            editor.addLogo(updatedLogo);
        } else {
            editor.addLogo(to.getLogo());
        }
        final T updatedContent = editor.update();
        return convertToEntity(updatedContent);
    }

    /**
     * Updated a logos.
     *
     * @param from the logo to copy.
     * @param to   the logo to update.
     * @return the updated logo.
     */
    private File updateLogo(final File from, final File to) {
        this.fileService.deleteFile(from.getUrl());
        final String url = this.fileService.saveFile(to.getMultipartFile());
        final FileEditor fileEditor = from.getEditor();
        fileEditor.addUrl(url);
        return fileEditor.update();
    }
}
