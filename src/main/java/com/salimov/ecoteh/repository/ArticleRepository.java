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
public interface ArticleRepository extends ContentRepository<Article> {

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
     * which matches the parameter number.
     *
     * @param number a number of the article to remove.
     */
    void deleteByNumber(final String number);
}
