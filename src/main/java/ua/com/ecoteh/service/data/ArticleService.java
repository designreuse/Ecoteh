package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Article;
import ua.com.ecoteh.entity.Category;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ArticleService extends ContentService<Article> {

    /**
     * Returns article with the parameter number.
     *
     * @param number  a title of the article to return.
     * @param isValid is get valid article or not.
     * @return The object of class {@link Article}.
     */
    Article getByNumber(
            final String number,
            final boolean isValid
    );

    /**
     * Returns article with the category id.
     *
     * @param id a category id of the article to return.
     * @return The object of class {@link Article}.
     */
    List<Article> getByCategoryId(final long id);

    /**
     * Returns article with the category title.
     *
     * @param categoryTitle a category title of the articles to return.
     * @return The object of class {@link Article}.
     */
    List<Article> getByCategoryTitle(final String categoryTitle);

    /**
     * Removes article the parameter number.
     *
     * @param number The number of the article to remove.
     */
    void removeByNumber(final String number);

    /**
     * Sorts and returns articles by number.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> sortByNumber(
            final Collection<Article> articles,
            final boolean revers
    );

    /**
     * Sorts and returns articles by date.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> sortByDate(
            final Collection<Article> articles,
            final boolean revers
    );

    /**
     * Sorts and returns articles by number.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> getAndSortByNumber(final boolean revers);

    /**
     * Sorts and returns articles by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> getAndSortByDate(final boolean revers);

    /**
     * Filters and returns articles by the date.
     *
     * @param articles  the articles to filter.
     * @param startDate a initial date.
     * @param endDate   a final date.
     * @return The filtered list of articles.
     */
    List<Article> filterByDate(
            final Collection<Article> articles,
            final Date startDate,
            final Date endDate
    );

    /**
     * Filters and returns articles by the category.
     *
     * @param articles the articles to filter.
     * @param category a category filtering.
     * @return The filtered list of articles.
     */
    List<Article> filterByCategory(
            final Collection<Article> articles,
            final Category category
    );

    /**
     * Filters and returns articles by the categories.
     *
     * @param articles   the articles to filter.
     * @param categories a categories filtering.
     * @return The filtered list of articles.
     */
    List<Article> filterByCategories(
            final Collection<Article> articles,
            final Collection<Category> categories
    );

    /**
     * Filters and returns articles by the date.
     *
     * @param startDate a initial date.
     * @param endDate   a final date.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByDate(
            final Date startDate,
            final Date endDate
    );

    /**
     * Filters and returns articles by the category.
     *
     * @param category a category filtering.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByCategory(final Category category);

    /**
     * Filters and returns articles by the categories.
     *
     * @param categories a categories filtering.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByCategories(final Collection<Category> categories);
}
