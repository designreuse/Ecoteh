package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.Article;

import java.util.List;

/**
 * The interface provides a set of standard methods for working
 * {@link Article} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ArticleDao extends ContentDao<Article> {

    /**
     * Returns article with the parameter number from a database.
     *
     * @param number a title of the article to return.
     * @return The article with parameter title.
     */
    Article getByNumber(final String number);

    /**
     * Returns articles with the parameter category id from a database.
     *
     * @param id a category id of the article to return.
     * @return The article with parameter category id.
     */
    List<Article> getByCategoryId(final Long id);

    /**
     * Removes article with the parameter number from a database.
     *
     * @param number a number of the article to remove.
     */
    void removeByNumber(final String number);
}
