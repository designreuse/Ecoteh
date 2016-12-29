package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.SectionDao;
import com.salimov.yurii.entity.Section;
import com.salimov.yurii.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods
 * for working {@link Section} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see SectionDao
 * @see Section
 * @see SectionRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class SectionDaoImpl
        extends DataDaoImpl<Section, Long>
        implements SectionDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Section} objects with a database.
     *
     * @see SectionRepository
     */
    private final SectionRepository repository;

    /**
     * Constructor.
     *
     * @param repository The interface provides a set of JPA methods
     *                   for working {@link Section} objects with a database.
     * @see SectionRepository
     */
    @Autowired
    public SectionDaoImpl(final SectionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns section with the parameter title from a database.
     *
     * @param title a title of the section to return.
     * @return The section with parameter title.
     * @see Section
     */
    @Override
    public Section getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns section with the parameter url from a database.
     *
     * @param url a url of the section to return.
     * @return The section with parameter url.
     * @see Section
     */
    @Override
    public Section getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes section with the parameter title from a database.
     *
     * @param title a title of the section to remove.
     * @see Section
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes section with the parameter url from a database.
     *
     * @param url a url of the section to remove.
     * @see Section
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
