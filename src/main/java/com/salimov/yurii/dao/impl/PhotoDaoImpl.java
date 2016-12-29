package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.PhotoDao;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods
 * for working {@link Photo} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see PhotoDao
 * @see Photo
 * @see PhotoRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class PhotoDaoImpl
        extends DataDaoImpl<Photo, Long>
        implements PhotoDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Photo} objects with a database.
     *
     * @see PhotoRepository
     */
    private final PhotoRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link Photo}
     *                   objects with a database.
     * @see PhotoRepository
     */
    @Autowired
    public PhotoDaoImpl(final PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns photo with the parameter title from a database.
     *
     * @param title a title of photo to return.
     * @return The photo with the parameter title.
     * @see Photo
     */
    @Override
    public Photo getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns photo with the parameter url from a database.
     *
     * @param url a url of photo to return.
     * @return The photo with the parameter url.
     * @see Photo
     */
    @Override
    public Photo getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes photo with the parameter title from a database.
     *
     * @param title a title of the photo to remove.
     * @see Photo
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes photo with the parameter url from a database.
     *
     * @param url a url of the photo to remove.
     * @see Photo
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
