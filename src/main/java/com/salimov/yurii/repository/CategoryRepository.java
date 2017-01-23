package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Category;

/**
 * The interface provides a set of JPA methods
 * for working {@link Category} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Category
 */
public interface CategoryRepository
        extends DataRepository<Category, Long> {

    /**
     * Returns category from a database,
     * which matches the parameter title.
     *
     * @param title a title of the category to return.
     * @return The object of class {@link Category}.
     * @see Category
     */
    Category findByTitle(final String title);

    /**
     * Returns category from a database,
     * which matches the parameter url.
     *
     * @param url a url of the category to return.
     * @return The object of class {@link Category}.
     * @see Category
     */
    Category findByUrl(final String url);


    /**
     * Removes category from a database,
     * which matches the parameter title.
     *
     * @param title a title of the category to remove.
     * @see Category
     */
    void deleteByTitle(final String title);

    /**
     * Removes category from a database,
     * which matches the parameter url.
     *
     * @param url a url of the category to remove.
     * @see Category
     */
    void deleteByUrl(final String url);
}
