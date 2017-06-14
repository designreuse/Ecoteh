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
 * @see Article
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
     * Returns all article with the category id.
     *
     * @param id the category id of articles to return.
     * @return The list of articles.
     */
    Collection<Article> getByCategoryId(long id);

    /**
     * Returns all article with the category title.
     *
     * @param title the category title of articles to return.
     * @return The list of articles.
     */
    Collection<Article> getByCategoryTitle(String title);

    /**
     * Removes all article with the parameter number.
     *
     * @param number the number of an article to remove.
     */
    void removeByNumber(String number);

    /**
     * Sorts and returns articles by number.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> sortByNumber(Collection<Article> articles, boolean revers);

    /**
     * Sorts and returns articles by date.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> sortByDate(Collection<Article> articles, boolean revers);

    /**
     * Sorts and returns articles by price.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> sortByPrice(Collection<Article> articles, boolean revers);

    /**
     * Sorts and returns articles by number.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> getAndSortByNumber(boolean revers);

    /**
     * Sorts and returns articles by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> getAndSortByDate(boolean revers);

    /**
     * Sorts and returns articles by price.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     */
    List<Article> getAndSortByPrice(boolean revers);

    /**
     * Filters and returns articles by incoming dates.
     *
     * @param articles the articles to filter.
     * @param start    the initial date.
     * @param finish   the final date.
     * @return The filtered list of articles.
     */
    List<Article> filterByDate(Collection<Article> articles, Date start, Date finish);

    /**
     * Filters and returns articles by the category.
     *
     * @param articles the articles to filter.
     * @param category the category filtering.
     * @return The filtered list of articles.
     */
    List<Article> filterByCategory(Collection<Article> articles, Category category);

    /**
     * Filters and returns articles by the categories.
     *
     * @param articles   the articles to filter.
     * @param categories the categories filtering.
     * @return The filtered list of articles.
     */
    List<Article> filterByCategories(Collection<Article> articles, Collection<Category> categories);

    /**
     * Filters and returns articles by the dates.
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByDate(Date start, Date finish);

    /**
     * Filters and returns articles by the category.
     *
     * @param category the category filtering.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByCategory(Category category);

    /**
     * Filters and returns articles by the categories.
     *
     * @param categories the categories filtering.
     * @return The filtered list of articles.
     */
    List<Article> getAndFilterByCategories(Collection<Category> categories);
}
