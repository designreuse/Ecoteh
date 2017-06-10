package ua.com.ecoteh.service.data;

import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.ContentRepository;
import ua.com.ecoteh.util.comparator.ContentComparator;

import java.util.Collection;
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
        final T contentToUpdate = getByUrl(content.getUrl(), false);
        final File newLogo = content.getLogo();
        final File oldLogo = contentToUpdate.getLogo();
        if (isNewLogo(newLogo, oldLogo)) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        return super.update(content);
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
            throw getIllegalArgumentException(ExceptionMessage.BLANK_URL_MESSAGE);
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
            this.repository.deleteByTitle(title);
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
            this.repository.deleteByUrl(url);
        }
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
        return sort(contents, new ContentComparator.ByTitle<>(), revers);
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
        return sort(contents, new ContentComparator.ByUrl<>(), revers);
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
        return sortByTitle(getAll(), revers);
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
        return sortByUrl(getAll(), revers);
    }

    /**
     * Checks incoming photos.
     * The new photo must be not equals to the old photo.
     * <pre>
     *     isNewLogo(photo, photo) = false
     *
     *     if the photo1 is not equals to the photo2
     *     isNewLogo(photo1, photo2) = false
     *
     *     if the photo1 is equals to the photo2
     *     isNewLogo(photo1, photo2) = true
     * </pre>
     *
     * @param newLogo the new photo (newer null).
     * @param oldLogo the old photo (newer null).
     * @return true if the incoming photos is not equals and
     * new photo URL is not empty.
     */
    static boolean isNewLogo(final File newLogo, final File oldLogo) {
        return !newLogo.equals(oldLogo) && isNotEmpty(newLogo.getUrl());
    }
}
