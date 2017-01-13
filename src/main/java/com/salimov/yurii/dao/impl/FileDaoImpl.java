package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.FileDao;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see FileDao
 * @see File
 * @see FileRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class FileDaoImpl
        extends DataDaoImpl<File, Long>
        implements FileDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link File} objects with a database.
     *
     * @see FileRepository
     */
    private final FileRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link File}
     *                   objects with a database.
     * @see FileRepository
     */
    @Autowired
    public FileDaoImpl(final FileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns photo with the parameter title from a database.
     *
     * @param title a title of photo to return.
     * @return The photo with the parameter title.
     * @see File
     */
    @Override
    public File getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns photo with the parameter url from a database.
     *
     * @param url a url of photo to return.
     * @return The photo with the parameter url.
     * @see File
     */
    @Override
    public File getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes photo with the parameter title from a database.
     *
     * @param title a title of the photo to remove.
     * @see File
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes photo with the parameter url from a database.
     *
     * @param url a url of the photo to remove.
     * @see File
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
