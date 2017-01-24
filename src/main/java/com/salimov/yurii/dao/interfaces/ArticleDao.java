package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;

import java.util.List;

/**
 * The interface provides a set of standard methods for working
 * {@link Article} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.ArticleDaoImpl
 * @see ContentDao
 * @see Article
 */
public interface ArticleDao
        extends ContentDao<Article, Long> {

    /**
     * Returns article with the parameter number from a database.
     *
     * @param number a title of the article to return.
     * @return The article with parameter title.
     * @see Article
     */
    Article getByNumber(final String number);

    /**
     * Returns articles with the parameter category id from a database.
     *
     * @param id a category id of the article to return.
     * @return The article with parameter category id.
     * @see Article
     * @see Category
     */
    List<Article> getByCategoryId(final Long id);

    /**
     * Removes article with the parameter number from a database.
     *
     * @param number a number of the article to remove.
     * @see Article
     */
    void removeByNumber(final String number);
}
