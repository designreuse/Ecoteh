package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.File;

import java.util.Collection;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.Category} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IContent
 * @see com.salimov.yurii.entity.Category
 */
public interface ICategory extends IContent {

    /**
     * Returns a file of the category.
     *
     * @return The category file.
     * @see File
     */
    String getPhotoUrl();

    /**
     * Sets a new file to the category.
     *
     * @param photoUrl a new main file to the category.
     * @see File
     */
    void setPhotoUrl(final String photoUrl);

    /**
     * Adds new article to the list of articles.
     *
     * @param article an article to add.
     * @see Article
     */
    void addArticle(final Article article);

    /**
     * Adds new articles to the list of articles.
     *
     * @param articles an articles to add.
     * @see Article
     */
    void addArticles(final Collection<Article> articles);

    /**
     * Removes article from the list of articles.
     *
     * @param article an article to remove.
     * @see Article
     */
    void removeArticle(final Article article);

    /**
     * Removes articles from the list of articles.
     *
     * @param articles an articles to remove.
     * @see Article
     */
    void removeArticles(final Collection<Article> articles);

    /**
     * Returns an list of articles.
     *
     * @return The list of articles.
     * @see Article
     */
    Collection<Article> getArticles();

    /**
     * Sets a new articles to list of articles.
     *
     * @param articles an articles to add.
     * @see Article
     */
    void setArticles(final Collection<Article> articles);

    /**
     * Contains article in the list of articles.
     *
     * @param article an article to contain.
     * @return {@code true} if article is contains,
     * {@code false} otherwise.
     * @see Article
     */
    boolean containsArticle(final Article article);

    /**
     * Contains articles in the list of articles.
     *
     * @param articles an articles to contain.
     * @return {@code true} if articles are contains,
     * {@code false} otherwise.
     * @see Article
     */
    boolean containsArticles(final Collection<Article> articles);

    /**
     * Clears the list of articles.
     *
     * @see Article
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
