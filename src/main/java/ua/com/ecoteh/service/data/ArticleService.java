package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.category.Category;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ArticleService extends ContentService<Article> {

    /**
     * Returns article with the parameter number.
     *
     * @param number  the number of an article to return.
     * @param isValid is get valid article or not.
     * @return The object of class {@link Article}.
     */
    Article getByNumber(String number, boolean isValid);

    /**
     * Returns all article with the categoryEntity id.
     *
     * @param id the categoryEntity id of articleEntities to return.
     * @return The list of articleEntities.
     */
    Collection<Article> getByCategoryId(long id);

    /**
     * Returns all article with the categoryEntity title.
     *
     * @param title the categoryEntity title of articleEntities to return.
     * @return The list of articleEntities.
     */
    Collection<Article> getByCategoryTitle(String title);

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
    List<Article> sortByNumber(Collection<Article> articleEntities, boolean revers);

    /**
     * Sorts and returns articleEntities by date.
     *
     * @param articleEntities the articleEntities to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<Article> sortByDate(Collection<Article> articleEntities, boolean revers);

    /**
     * Sorts and returns articleEntities by number.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<Article> getAndSortByNumber(boolean revers);

    /**
     * Sorts and returns articleEntities by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articleEntities.
     */
    List<Article> getAndSortByDate(boolean revers);

    /**
     * Filters and returns articleEntities by incming dates.
     *
     * @param articleEntities  the articleEntities to filter.
     * @param startDate the initial date.
     * @param endDate   the final date.
     * @return The filtered list of articleEntities.
     */
    List<Article> filterByDate(Collection<Article> articleEntities, Date startDate, Date endDate);

    /**
     * Filters and returns articleEntities by the categoryEntity.
     *
     * @param articleEntities the articleEntities to filter.
     * @param categoryEntity the categoryEntity filtering.
     * @return The filtered list of articleEntities.
     */
    List<Article> filterByCategory(Collection<Article> articleEntities, Category categoryEntity);

    /**
     * Filters and returns articleEntities by the categories.
     *
     * @param articleEntities   the articleEntities to filter.
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities.
     */
    List<Article> filterByCategories(Collection<Article> articleEntities, Collection<Category> categories);

    /**
     * Filters and returns articleEntities by the dates.
     *
     * @param startDate the initial date.
     * @param endDate   the final date.
     * @return The filtered list of articleEntities.
     */
    List<Article> getAndFilterByDate(Date startDate, Date endDate);

    /**
     * Filters and returns articleEntities by the categoryEntity.
     *
     * @param categoryEntity the categoryEntity filtering.
     * @return The filtered list of articleEntities.
     */
    List<Article> getAndFilterByCategory(Category categoryEntity);

    /**
     * Filters and returns articleEntities by the categories.
     *
     * @param categories the categories filtering.
     * @return The filtered list of articleEntities.
     */
    List<Article> getAndFilterByCategories(Collection<Category> categories);
}
