package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.FileDao;
import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.enums.FileType;
import com.salimov.ecoteh.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class implements a set of standard methods
 * for working {@link File} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class FileDaoImpl extends DataDaoImpl<File> implements FileDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link File} objects with a database.
     */
    private final FileRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working {@link File} objects with a database.
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
     */
    @Override
    public File getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns photo with the parameter url from a database.
     *
     * @param url a URL of photo to return.
     * @return The photo with the parameter url.
     */
    @Override
    public File getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes photo with the parameter title from a database.
     *
     * @param title a title of the photo to remove.
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes photo with the parameter url from a database.
     *
     * @param url a URL of the photo to remove.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Returns files with the type.
     *
     * @param type a type of files to return.
     * @return The files with the type.
     */
    @Override
    public List<File> getByFileType(final FileType type) {
        return this.repository.findAllByType(type);
    }
}
