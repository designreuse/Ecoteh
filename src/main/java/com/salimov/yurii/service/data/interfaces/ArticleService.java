package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Article;
import com.salimov.yurii.entity.Category;
import com.salimov.yurii.entity.Photo;
import com.salimov.yurii.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Article
 * @see ContentService
 * @see DataService
 */
public interface ArticleService extends ContentService<Article, Long> {

    /**
     * Initializes, saves and returns a new article.
     *
     * @param title       a title of the new article.
     * @param description a description of the new article.
     * @param text        a text of the new article.
     * @param keywords    a keywords of the new article.
     * @param number      a number of the new article.
     * @param category    a category of the new article.
     * @param mainPhoto   a photo of the new article.
     * @param slides      a slides of the new article.
     * @param videos      a videos of the new article.
     * @param isValid a value of validations of the model.
     * @return The new saving article.
     * @see Article
     * @see Category
     * @see Photo
     * @see Video
     */
    Article initAndAdd(
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final MultipartFile mainPhoto,
            final MultipartFile[] slides,
            final String videos,
            final boolean isValid
    );

    /**
     * Initializes, updates and returns article with parameter id.
     *
     * @param url          a url of the article to update.
     * @param title        a new title to the article.
     * @param description  a new description to the article.
     * @param text         a new text to the article.
     * @param keywords     a new keywords to the article.
     * @param number       a new number to the article.
     * @param category     a new category to the article.
     * @param mainFile     a new photo to the article.
     * @param photoAction  a action on the main photo.
     * @param slideFiles   a new slides to the article.
     * @param slidesAction a action on the slides.
     * @param videoUrls    a new videos to the article.
     * @param isValid      a validated of the article.
     * @return The updating article with parameter id or {@code null}.
     * @see Article
     * @see Category
     * @see Photo
     * @see Video
     */
    Article initAndUpdate(
            final String url,
            final String title,
            final String description,
            final String text,
            final String keywords,
            final String number,
            final Category category,
            final MultipartFile mainFile,
            final String photoAction,
            final MultipartFile[] slideFiles,
            final String slidesAction,
            final String videoUrls,
            final boolean isValid
    );

    /**
     * Returns article with the parameter number.
     *
     * @param number  a title of the article to return.
     * @param isValid is get valid article or not.
     * @return The object of class {@link Article}.
     * @see Article
     */
    Article getByNumber(final String number, final boolean isValid);

    /**
     * Removes article the parameter number.
     *
     * @param number The number of the article to remove.
     * @see Article
     */
    void removeByNumber(final String number);

    /**
     * Sorts and returns articles by number.
     *
     * @param articles the articles to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
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
     * @see Article
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
     * @see Article
     */
    List<Article> getAndSortByNumber(final boolean revers);

    /**
     * Sorts and returns articles by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of articles.
     * @see Article
     */
    List<Article> getAndSortByDate(final boolean revers);

    /**
     * Filters and returns articles by the date.
     *
     * @param articles  the articles to filter.
     * @param startDate a initial date.
     * @param endDate   a final date.
     * @return The filtered list of articles.
     * @see Article
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
     * @see Article
     * @see Category
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
     * @see Article
     * @see Category
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
     * @see Article
     */
    List<Article> getAndFilterByDate(final Date startDate, final Date endDate);

    /**
     * Filters and returns articles by the category.
     *
     * @param category a category filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    List<Article> getAndFilterByCategory(final Category category);

    /**
     * Filters and returns articles by the categories.
     *
     * @param categories a categories filtering.
     * @return The filtered list of articles.
     * @see Article
     * @see Category
     */
    List<Article> getAndFilterByCategories(
            final Collection<Category> categories
    );
}
