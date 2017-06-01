package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.article.ArticleEntity;
import ua.com.ecoteh.entity.category.CategoryEntity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link ArticleEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ArticleService extends ContentService<ArticleEntity> {

    /**
     * Returns article with the parameter number.
     *
     * @param number  the number of an article to return.
     * @param isValid is get valid article or not.
     * @return The object of class {@link ArticleEntity}.
     */
    ArticleEntity getByNumber(String number, boolean isValid);

    /**
     * Returns all article with the categoryEntity id.
     *
     * @param id the categoryEntity id of articleEntities to return.
     * @return The list of articleEntities.
     */
    List<ArticleEntity> getByCategoryId(long id);

    /**
     * Returns all article with the categoryEntity title.
     *
     * @param title the categoryEntity title of articleEntities to return.
     * @return The list of articleEntities.
     */
    List<ArticleEntity> getByCategoryTitle(String title);

    /**
     * Removes all article with the parameter number.
     *
     * @param number the number of an article to remove.
     */
    void removeByNumber(String number);

    /**
     * Sorts and returns articleEntities by number.
     *
     * @param articleEntities the articleEntities to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<ArticleEntity> sortByNumber(Collection<ArticleEntity> articleEntities, boolean revers);

    /**
     * Sorts and returns articleEntities by date.
     *
     * @param articleEntities the articleEntities to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<ArticleEntity> sortByDate(Collection<ArticleEntity> articleEntities, boolean revers);

    /**
     * Sorts and returns articleEntities by number.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<ArticleEntity> getAndSortByNumber(boolean revers);

    /**
     * Sorts and returns articleEntities by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<ArticleEntity> getAndSortByDate(boolean revers);

    /**
     * Filters and returns articleEntities by incming dates.
     *
     * @param articleEntities  the articleEntities to filter.
     * @param startDate the initial date.
     * @param endDate   the final date.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> filterByDate(Collection<ArticleEntity> articleEntities, Date startDate, Date endDate);

    /**
     * Filters and returns articleEntities by the categoryEntity.
     *
     * @param articleEntities the articleEntities to filter.
     * @param categoryEntity the categoryEntity filtering.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> filterByCategory(Collection<ArticleEntity> articleEntities, CategoryEntity categoryEntity);

    /**
     * Filters and returns articleEntities by the categories.
     *
     * @param articleEntities   the articleEntities to filter.
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> filterByCategories(Collection<ArticleEntity> articleEntities, Collection<CategoryEntity> categories);

    /**
     * Filters and returns articleEntities by the dates.
     *
     * @param startDate the initial date.
     * @param endDate   the final date.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> getAndFilterByDate(Date startDate, Date endDate);

    /**
     * Filters and returns articleEntities by the categoryEntity.
     *
     * @param categoryEntity the categoryEntity filtering.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> getAndFilterByCategory(CategoryEntity categoryEntity);

    /**
     * Filters and returns articleEntities by the categories.
     *
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities.
     */
    List<ArticleEntity> getAndFilterByCategories(Collection<CategoryEntity> categories);
}
