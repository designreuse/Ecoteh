package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.CategoryDao;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

/**
 * The class implements a set of standard methods for working {@link Category}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see CategoryDao
 * @see Category
 * @see CategoryRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class CategoryDaoImpl
        extends DataDaoImpl<Category, Long> implements CategoryDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Category} objects with a database.
     *
     * @see CategoryRepository
     */
    private final CategoryRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link Category}
     *                   objects with a database.
     * @see CategoryRepository
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
     * @see Category
     */
    @Override
    public Category getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns category with the parameter url from a database.
     *
     * @param url a url of the category to return.
     * @return The category with the parameter url.
     * @see Category
     */
    @Override
    public Category getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes category with the parameter title from a database.
     *
     * @param title a title of the category to remove.
     * @see Category
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes category with the parameter url from a database.
     *
     * @param url a url of the category to remove.
     * @see Category
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }
}
