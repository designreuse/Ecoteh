package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.article.ArticleEntity;

import java.util.Collection;

/**
 * The interface provides a set of JPA methods for working
 * {@link ArticleEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see ArticleEntity
 */
public interface ArticleRepository extends ContentRepository<ArticleEntity> {

    /**
     * Returns all article from a database,
     * which matches the parameter number.
     *
     * @param number the title of a article to return.
     * @return The object of class {@link ArticleEntity}.
     */
    ArticleEntity findByNumber(String number);

    /**
     * Returns all articles from a database,
     * which matches the parameter category id.
     *
     * @param id the category id of a articles to return.
     * @return The object of class {@link ArticleEntity}.
     */
    Collection<ArticleEntity> findByCategoryId(long id);

    /**
     * Returns all articles from a database,
     * which matches the parameter category title.
     *
     * @param title the category title of a articles to return.
     * @return The object of class {@link ArticleEntity}.
     * @see ua.com.ecoteh.entity.category.CategoryEntity
     */
    Collection<ArticleEntity> findByCategoryTitle(String title);

    /**
     * Removes article from a database,
     * which matches the parameter number.
     *
     * @param number the number of the article to remove.
     */
    void deleteByNumber(String number);
}
