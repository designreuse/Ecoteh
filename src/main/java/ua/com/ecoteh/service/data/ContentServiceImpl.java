package ua.com.ecoteh.service.data;

import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.Content;
import ua.com.ecoteh.entity.File;
import ua.com.ecoteh.repository.ContentRepository;
import ua.com.ecoteh.util.comparator.ContentComparator;

import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Content} class or subclass.
 *
 * @param <T> entity type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public abstract class ContentServiceImpl<T extends Content>
        extends DataServiceImpl<T> implements ContentService<T> {

    /**
     * The message that a incoming object is null.
     */
    private final static String INCOMING_OBJECT_IS_NULL_MESSAGE =
            "Incoming object of the %s class is null!";

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
     * The message that a service cannot find content by incoming title.
     */
    private final static String FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming title %s!";

    /**
     * The message that a service cannot find content by incoming URL.
     */
    private final static String FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming URL %s!";

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Content} objects with the database.
     */
    private final ContentRepository<T> repository;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link File}.
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
            final ContentRepository<T> repository,
            final FileService fileService
    ) {
        super(repository);
        this.repository = repository;
        this.fileService = fileService;
    }

    /**
     * Initializes, updates and returns content with incoming URL.
     * If a incoming content is null then throws IllegalArgumentException.
     *
     * @param url     the URL of a content to update.
     * @param content the content object to update.
     * @return The updating content with parameter id (newer null).
     * @throws IllegalArgumentException Throw exception when input content is null.
     */
    @Override
    @Transactional
    public T update(
            final String url,
            final T content
    ) throws IllegalArgumentException {
        if (isNull(content)) {
            throw getIllegalArgumentException(INCOMING_OBJECT_IS_NULL_MESSAGE, getClassSimpleName());
        }
        final T contentToUpdate = getByUrl(url, false);
        final File newLogo = content.getLogo();
        final File oldLogo = contentToUpdate.getLogo();
        if (isNewLogo(newLogo, oldLogo)) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        copy(content, contentToUpdate);
        return update(contentToUpdate);
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
    public T getByTitle(
            final String title,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(title)) {
            throw getIllegalArgumentException(BLANK_TITLE_MESSAGE, getClassSimpleName());
        }
        final T content = this.repository.findByTitle(title);
        if (isNotValidated(content, isValid)) {
            throw getNullPointerException(
                    FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), title
            );
        }
        return content;
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
    public T getByUrl(
            final String url,
            final boolean isValid
    ) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw getIllegalArgumentException(BLANK_URL_MESSAGE);
        }
        final T content = this.repository.findByUrl(url);
        if (isNotValidated(content, isValid)) {
            throw getNullPointerException(
                    FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), url
            );
        }
        return content;
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
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByTitle(
            final Collection<T> contents,
            final boolean revers
    ) {
        return sort(contents, new ContentComparator.ByTitle<>(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     * For sorting used {@link ContentComparator.ByUrl} comparator.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> sortByUrl(
            final Collection<T> contents,
            final boolean revers
    ) {
        return sort(contents, new ContentComparator.ByUrl<>(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by title.
     * For sorting used {@link ContentComparator.ByTitle} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents or empty list (newer null).
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
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByUrl(final boolean revers) {
        return sortByUrl(getAll(), revers);
    }

    /**
     * Validates input object of {@link Content} class or subclasses.
     *
     * @param content   the contents to valid.
     * @param exist     is validate input object by exists.
     * @param duplicate is validate input object by duplicate.
     * @return Returns true if object is valid, otherwise returns false.
     */
    @Override
    protected boolean validated(
            final T content,
            final boolean exist,
            final boolean duplicate
    ) {
        if (isNull(content)) {
            return false;
        }
        if (exist && !exists(content)) {
            return false;
        }
        if (duplicate) {
            if (isNotNull(this.repository.findByTitle(content.getTitle())) ||
                    isNotNull(this.repository.findByUrl(content.getUrl()))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copies the object "from" to object "to".
     * Incoming objects must be not null.
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    protected abstract void copy(T from, T to);

    /**
     * Checks incoming photos.
     * The new photo must be not equals to the old photo.
     * <pre>
     *     File photo1 = new File();
     *     isNewLogo(photo1, photo1) = false
     *
     *     File photo2 = new File();
     *     isNewLogo(photo1, photo2) = false
     *
     *     photo2.setUrl("photo_2);
     *     isNewLogo(photo1, photo2) = true
     * </pre>
     *
     * @param newLogo the new photo (newer null).
     * @param oldLogo the old photo (newer null).
     * @return true if the incoming photos is not equals and
     * new photo URL is not empty.
     */
    protected static boolean isNewLogo(final File newLogo, final File oldLogo) {
        return !newLogo.equals(oldLogo) && isNotEmpty(newLogo.getUrl());
    }

    /**
     * Check the incoming content to not valid.
     * Content is not valid if it is null or not valid.
     * <pre>
     *     isNotValidated(null, false) = true
     *     isNotValidated(null, true) = true
     *
     *     Content content = new Content();
     *     content.setValidated(false);
     *     isNotValidated(content, false) = true
     *     isNotValidated(content, true) = false
     *
     *     content.setValidated(true);
     *     isNotValidated(content, false) = true
     *     isNotValidated(content, true) = true
     * </pre>
     *
     * @param content the content to check.
     * @param isValid checks the incoming content to valid or not.
     * @param <T>     entity type, extends {@link Content}.
     * @return true if the content is null or it is not valid.
     */
    protected static <T extends Content> boolean isNotValidated(
            final T content,
            final boolean isValid
    ) {
        return isNull(content) || (isValid && !content.isValidated());
    }
}
