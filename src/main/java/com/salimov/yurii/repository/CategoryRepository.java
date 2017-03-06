package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Category;

/**
 * The interface provides a set of JPA methods
 * for working {@link Category} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface CategoryRepository extends DataRepository<Category> {

    /**
     * Returns category from a database,
     * which matches the parameter title.
     *
     * @param title a title of the category to return.
     * @return The object of class {@link Category}.
     */
    Category findByTitle(final String title);

    /**
     * Returns category from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the category to return.
     * @return The object of class {@link Category}.
     */
    Category findByUrl(final String url);


    /**
     * Removes category from a database,
     * which matches the parameter title.
     *
     * @param title a title of the category to remove.
     */
    void deleteByTitle(final String title);

    /**
     * Removes category from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the category to remove.
     */
    void deleteByUrl(final String url);
}
