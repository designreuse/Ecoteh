package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;

import java.util.List;

/**
 * The interface provides a set of JPA methods for working
 * {@link Article} objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Article
 */
public interface ArticleRepository
        extends DataRepository<Article, Long> {

    /**
     * Returns article from a database,
     * which matches the parameter title.
     *
     * @param title a title of the article to return.
     * @return The object of class {@link Article}.
     * @see Article
     */
    Article findByTitle(final String title);

    /**
     * Returns article from a database,
     * which matches the parameter url.
     *
     * @param url a title of the article to return.
     * @return The object of class {@link Article}.
     * @see Article
     */
    Article findByUrl(final String url);

    /**
     * Returns article from a database,
     * which matches the parameter number.
     *
     * @param number a title of the article to return.
     * @return The object of class {@link Article}.
     * @see Article
     */
    Article findByNumber(final String number);

    /**
     * Returns articles from a database,
     * which matches the parameter category id.
     *
     * @param id a category id of the article to return.
     * @return The object of class {@link Article}.
     * @see Article
     * @see Category
     */
    List<Article> findByCategoryId(final Long id);

    /**
     * Removes article from a database,
     * which matches the parameter title.
     *
     * @param title a title of the article to remove.
     * @see Article
     */
    void deleteByTitle(final String title);

    /**
     * Removes article from a database,
     * which matches the parameter url.
     *
     * @param url a url of the article to remove.
     * @see Article
     */
    void deleteByUrl(final String url);

    /**
     * Removes article from a database,
     * which matches the parameter number.
     *
     * @param number a number of the article to remove.
     * @see Article
     */
    void deleteByNumber(final String number);
}
