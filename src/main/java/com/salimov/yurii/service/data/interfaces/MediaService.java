package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Media;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of {@link Media} class or subclasses.
 *
 * @param <T>  entity type, extends {@link Media}.
 * @param <E> entity id type, extends Number.
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 * @see DataService
 * @see FileService
 */
public interface MediaService<T extends Media, E extends Number>
        extends DataService<T, E> {

    /**
     * Initializes, saves and returns media object of class {@link Media}
     * or subclasses.
     *
     * @param title a title of the new media.
     * @param url   a url of the new media.
     * @return The new saving media.
     * @see Media
     */
    T initAndAdd(
            final String title,
            final String url
    );

    /**
     * Updates, saves and returns media object of class {@link Media}
     * or subclasses.
     *
     * @param id    a id of the media to update.
     * @param title a new title of the media.
     * @param url   a new url of the media.
     * @return The updating article with parameter id.
     * @see Media
     */
    T initAndUpdate(
            final E id,
            final String title,
            final String url
    );

    /**
     * Returns media object of class {@link Media} or subclasses
     * with the parameter title.
     *
     * @param title a title of the media to return.
     * @return The media with parameter title.
     * @see Media
     */
    T getByTitle(final String title);

    /**
     * Returns media object of class {@link Media} or subclasses
     * with the parameter url.
     *
     * @param url a url of the media to return.
     * @return The media with parameter url.
     * @see Media
     */
    T getByUrl(final String url);

    /**
     * Removes media object of class {@link Media} or subclasses
     * with the parameter title.
     *
     * @param title a title of the media to remove.
     * @see Media
     */
    void removeByTitle(final String title);

    /**
     * Removes media object of class {@link Media} or subclasses
     * with the parameter url.
     *
     * @param url a url of the media to remove.
     * @see Media
     */
    void removeByUrl(final String url);
}
