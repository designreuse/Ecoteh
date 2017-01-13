package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.MediaDao;
import com.salimov.yurii.entity.Media;
import com.salimov.yurii.service.data.interfaces.MediaService;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, describes a set of methods
 * for working with objects of {@link Media} class or subclasses.
 *
 * @param <T>  entity type, extends {@link Media}.
 * @param <E> entity id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 * @see MediaService
 * @see DataServiceImpl
 * @see com.salimov.yurii.service.data.impl.FileServiceImpl
 * @see com.salimov.yurii.service.data.impl.VideoServiceImpl
 * @see MediaDao
 */
public abstract class MediaServiceImpl<T extends Media<E>, E extends Number>
        extends DataServiceImpl<T, E>
        implements MediaService<T, E> {

    /**
     * The object provides a set of standard JPA methods
     * for working {@link Media} objects with the database.
     *
     * @see MediaDao
     */
    private final MediaDao<T, E> dao;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation of the {@link MediaDao} interface.
     * @see MediaDao
     */
    MediaServiceImpl(final MediaDao<T, E> dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Initializes, saves and returns object of class {@link Media}
     * or subclasses.
     *
     * @param title a title of the new media.
     * @param url   a url of the new media.
     * @return The new saving media.
     * @see Media
     */
    @Override
    @Transactional
    public T initAndAdd(final String title, final String url) {
        final T media = getInstance(
                getModelClass()
        );
        if (media != null) {
            media.initialize(title, url);
        }
        return add(media);
    }

    /**
     * Updates, saves and returns object of class {@link Media}
     * or subclasses. Returns {@code null} if id is {@code null}.
     *
     * @param id    a id of the media to update.
     * @param title a new title to the media.
     * @param url   a new url to the media.
     * @return The updating media with parameter id.
     * @see Media
     */
    @Override
    @Transactional
    public T initAndUpdate(
            final E id,
            final String title,
            final String url
    ) {
        final T media = get(id);
        media.initialize(title, url);
        return update(media);
    }

    /**
     * Returns object of class {@link Media} or subclasses
     * with the parameter title. If title is blank then return {@code null}.
     *
     * @param title a title of the media to return.
     * @return The media with parameter title or {@code null}.
     * @throws IllegalArgumentException Throw exception when parameter title
     *                                  is blank.
     * @throws NullPointerException     Throw exception when object
     *                                  with parameter title is not exist.
     * @see Media
     */
    @Override
    @Transactional(readOnly = true)
    public T getByTitle(final String title)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(title)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " title is blank!"
            );
        }
        final T media = this.dao.getByTitle(title);
        if (media == null) {
            throw new NullPointerException(
                    "Can`t find " + getClassSimpleName()
                            + " by title \"" + title + "\"!"
            );
        }
        return media;
    }

    /**
     * Returns object of class {@link Media} or subclasses
     * with the parameter url. If url is blank then return {@code null}.
     *
     * @param url a url of the media to return.
     * @return The media with parameter url or {@code null}.
     * @throws NullPointerException Throw exception when object
     *                              with parameter url is not exist.
     * @see Media
     */
    @Override
    @Transactional(readOnly = true)
    public T getByUrl(final String url)
            throws IllegalArgumentException, NullPointerException {
        if (isBlank(url)) {
            throw new IllegalArgumentException(
                    getClassSimpleName() + " url is blank!"
            );
        }
        final T media = this.dao.getByUrl(url);
        if (media == null) {
            throw new NullPointerException(
                    "Can`t find object of " + getClassSimpleName() +
                            " by URL \"" + url + "\"!"
            );
        }
        return media;
    }

    /**
     * Removes object of class {@link Media} or subclasses the parameter title.
     * Removes media if title is not blank.
     *
     * @param title a title of the media to remove.
     * @see Media
     */
    @Override
    @Transactional
    public void removeByTitle(final String title) {
        if (isNotBlank(title)) {
            this.dao.removeByTitle(title);
        }
    }

    /**
     * Removes object of class {@link Media} or subclasses
     * with the parameter url.
     *
     * @param url a url of the media to remove.
     * @see Media
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        remove(
                getByUrl(url)
        );
    }

    /**
     * Validates input object of class {@link Media} or subclasses.
     *
     * @param media              the media to valid.
     * @param requiredParameters is validate by required parameters.
     * @param exist              is validate by exists.
     * @param duplicate          is validate by duplicate.
     * @return {@code true} if media is valid, {@code false} otherwise.
     * @see Media
     */
    @Override
    protected boolean validated(
            final T media,
            final boolean requiredParameters,
            final boolean exist,
            final boolean duplicate
    ) {
        if (media == null) {
            return false;
        }
        if (requiredParameters && !Media.isValidated(media)) {
            return false;
        }
        if (exist && !exists(media)) {
            return false;
        }
        if (duplicate) {
            final String title = media.getTitle();
            final String url = media.getUrl();
            if (this.dao.getByTitle(title) != null
                    || this.dao.getByUrl(url) != null
                    ) {
                return false;
            }
        }
        return true;
    }
}
