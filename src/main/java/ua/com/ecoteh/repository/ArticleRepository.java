package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.Article;

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
     * Returns all article from a database,
     * which matches the parameter number.
     *
     * @param number the title of a article to return.
     * @return The object of class {@link Article}.
     */
    Article findByNumber(String number);

    /**
     * Returns all articles from a database,
     * which matches the parameter category id.
     *
     * @param id the category id of a article to return.
     * @return The object of class {@link Article}.
     */
    List<Article> findByCategoryId(long id);

    /**
     * Returns all articles from a database,
     * which matches the parameter category title.
     *
     * @param title the category title of a articles to return.
     * @return The object of class {@link Article}.
     */
    List<Article> findByCategoryTitle(String title);

    /**
     * Removes article from a database,
     * which matches the parameter number.
     *
     * @param number the number of the article to remove.
     */
    void deleteByNumber(String number);
}
