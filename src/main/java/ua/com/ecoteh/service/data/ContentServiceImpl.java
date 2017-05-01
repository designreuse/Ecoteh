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
     *
     */
    private final static String INCOMING_OBJECT_IS_NULL_MESSAGE =
            "Incoming object of the %s class is null!";

    /**
     *
     */
    private final static String BLANK_TITLE_MESSAGE = "Incoming %s title is blank!";

    /**
     *
     */
    private final static String FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE =
            "Can`t find object of the %s class by incoming title %s!";

    /**
     *
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
     * @param repository  a implementation of the {@link ContentRepository} interface.
     * @param fileService a implementation of the {@link FileService} interface.
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
     * Initializes, updates and returns content with parameter url.
     *
     * @param url     a URL of the content to update.
     * @param content a content object to update.
     * @return The updating content with parameter id.
     * @throws IllegalArgumentException Throw exception when input content is null.
     */
    @Override
    @Transactional
    public T update(
            final String url,
            final T content
    ) throws IllegalArgumentException {
        if (isNull(content)) {
            throw new IllegalArgumentException(
                    String.format(
                            INCOMING_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName()
                    )
            );
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
     * Returns object of {@link Content} or subclasses with the parameter title.
     *
     * @param title   a title of the content to return.
     * @param isValid is get valid content or not.
     * @return The content with parameter title.
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
            throw new IllegalArgumentException(
                    String.format(BLANK_TITLE_MESSAGE, getClassSimpleName())
            );
        }
        final T content = this.repository.findByTitle(title);
        if (isNotValidContent(content, isValid)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_TITLE_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), title
                    )
            );
        }
        return content;
    }

    /**
     * Returns object of {@link Content} or subclasses with the parameter url.
     *
     * @param url     a URL of the content to return.
     * @param isValid is get valid content or not.
     * @return the content with parameter url.
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
            throw new IllegalArgumentException(getClassSimpleName() + " url is blank!");
        }
        final T content = this.repository.findByUrl(url);
        if (isNotValidContent(content, isValid)) {
            throw new NullPointerException(
                    String.format(
                            FINDING_BY_URL_OBJECT_IS_NULL_MESSAGE,
                            getClassSimpleName(), url
                    )
            );
        }
        return content;
    }

    /**
     * Removes object of {@link Content} or subclasses with the parameter title.
     * Removes content if title is not blank.
     *
     * @param title a title of the content to remove.
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotEmpty(title)) {
            this.repository.deleteByTitle(title);
        }
    }

    /**
     * Removes object of {@link Content} or subclasses with the parameter url.
     *
     * @param url a URL of the content to remove.
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
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
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
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
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
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents.
     */
    @Override
    @Transactional(readOnly = true)
    public List<T> getAndSortByTitle(final boolean revers) {
        return sortByTitle(getAll(), revers);
    }

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     *
     * @param revers Sort in descending or ascending.
     * @return The sorted list of contents.
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
     * @param newLogo
     * @param oldLogo
     * @return
     */
    protected static boolean isNewLogo(final File newLogo, final File oldLogo) {
        return !newLogo.equals(oldLogo) && isNotEmpty(newLogo.getUrl());
    }

    /**
     * Copies the object "from" to object "to".
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    protected abstract void copy(T from, T to);

    /**
     * @param content
     * @param isValid
     * @param <T>
     * @return
     */
    private static <T extends Content> boolean isNotValidContent(
            final T content,
            final boolean isValid
    ) {
        return isNull(content) || (isValid && !content.isValidated());
    }
}
