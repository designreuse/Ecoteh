package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.CategoryDao;
import com.salimov.ecoteh.entity.Category;
import com.salimov.ecoteh.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods for working {@link Category}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class CategoryDaoImpl extends DataDaoImpl<Category> implements CategoryDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Category} objects with a database.
     */
    private final CategoryRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working {@link Category} objects with a database.
     */
    @Autowired
    public CategoryDaoImpl(final CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns category with the parameter title from a database.
     *
     * @param title a title of the category to return.
     * @return The category with the parameter title.
     */
    @Override
    public Category getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns category with the parameter url from a database.
     *
     * @param url a URL of the category to return.
     * @return The category with the parameter url.
     */
    @Override
    public Category getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes category with the parameter title from a database.
     *
     * @param title a title of the category to remove.
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes category with the parameter url from a database.
     *
     * @param url a URL of the category to remove.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
