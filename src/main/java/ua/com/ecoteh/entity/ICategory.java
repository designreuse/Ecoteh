package ua.com.ecoteh.entity;

import java.util.Collection;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Category} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ICategory extends IContent {

    /**
     * Adds new article to the list of articles.
     *
     * @param article the article to add.
     */
    void addArticle(Article article);

    /**
     * Adds new articles to the list of articles.
     *
     * @param articles the articles to add.
     */
    void addArticles(Collection<Article> articles);

    /**
     * Removes article from the list of articles.
     *
     * @param article the article to remove.
     */
    void removeArticle(Article article);

    /**
     * Removes articles from the list of articles.
     *
     * @param articles the articles to remove.
     */
    void removeArticles(Collection<Article> articles);

    /**
     * Returns an list of articles.
     *
     * @return The list of articles.
     */
    Collection<Article> getArticles();

    /**
     * Sets a new articles to list of articles.
     *
     * @param articles the articles to add.
     */
    void setArticles(Collection<Article> articles);

    /**
     * Contains article in the list of articles.
     *
     * @param article the article to contain.
     * @return true if article is contains, false otherwise.
     */
    boolean containsArticle(Article article);

    /**
     * Contains articles in the list of articles.
     *
     * @param articles the articles to contain.
     * @return true if articles are contains, false otherwise.
     */
    boolean containsArticles(Collection<Article> articles);

    /**
     * Clears the list of articles.
     */
    void clearArticles();

    /**
     * Initializes the category.
     * Returns this category with a new copied fields.
     *
     * @param category the category to copy.
     * @return This category with new fields.
     */
    Category initialize(Category category);
}
