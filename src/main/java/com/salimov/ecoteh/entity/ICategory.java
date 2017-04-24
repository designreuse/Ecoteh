package com.salimov.ecoteh.entity;

import java.util.Collection;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.ecoteh.entity.Category} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ICategory extends IContent {

    /**
     * Adds new article to the list of articles.
     *
     * @param article an article to add.
     */
    void addArticle(final Article article);

    /**
     * Adds new articles to the list of articles.
     *
     * @param articles an articles to add.
     */
    void addArticles(final Collection<Article> articles);

    /**
     * Removes article from the list of articles.
     *
     * @param article an article to remove.
     */
    void removeArticle(final Article article);

    /**
     * Removes articles from the list of articles.
     *
     * @param articles an articles to remove.
     */
    void removeArticles(final Collection<Article> articles);

    /**
     * Returns an list of articles.
     *
     * @return The list of articles.
     */
    Collection<Article> getArticles();

    /**
     * Sets a new articles to list of articles.
     *
     * @param articles an articles to add.
     */
    void setArticles(final Collection<Article> articles);

    /**
     * Contains article in the list of articles.
     *
     * @param article an article to contain.
     * @return true if article is contains, false otherwise.
     */
    boolean containsArticle(final Article article);

    /**
     * Contains articles in the list of articles.
     *
     * @param articles an articles to contain.
     * @return true if articles are contains, false otherwise.
     */
    boolean containsArticles(final Collection<Article> articles);

    /**
     * Clears the list of articles.
     */
    void clearArticles();

    /**
     * Initializes the category.
     *
     * @param category a category to copy.
     * @return The this category with new fields.
     */
    Category initialize(final Category category);
}
