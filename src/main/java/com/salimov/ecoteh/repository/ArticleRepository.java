package com.salimov.ecoteh.repository;

import com.salimov.ecoteh.entity.Article;

import java.util.List;

/**
 * The interface provides a set of JPA methods for working
 * {@link Article} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ArticleRepository extends DataRepository<Article> {

    /**
     * Returns article from a database,
     * which matches the parameter title.
     *
     * @param title a title of the article to return.
     * @return The object of class {@link Article}.
     */
    Article findByTitle(final String title);

    /**
     * Returns article from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the article to return.
     * @return The object of class {@link Article}.
     */
    Article findByUrl(final String url);

    /**
     * Returns article from a database,
     * which matches the parameter number.
     *
     * @param number a title of the article to return.
     * @return The object of class {@link Article}.
     */
    Article findByNumber(final String number);

    /**
     * Returns articles from a database,
     * which matches the parameter category id.
     *
     * @param id a category id of the article to return.
     * @return The object of class {@link Article}.
     */
    List<Article> findByCategoryId(final Long id);

    /**
     * Removes article from a database,
     * which matches the parameter title.
     *
     * @param title a title of the article to remove.
     */
    void deleteByTitle(final String title);

    /**
     * Removes article from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the article to remove.
     */
    void deleteByUrl(final String url);

    /**
     * Removes article from a database,
     * which matches the parameter number.
     *
     * @param number a number of the article to remove.
     */
    void deleteByNumber(final String number);
}
