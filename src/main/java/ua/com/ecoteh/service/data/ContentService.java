package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.content.Content;

import java.util.Collection;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of {@link Content} class or subclass.
 *
 * @param <T> entity type, extends {@link Content}.
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface ContentService<T extends Content> extends DataService<T> {

    /**
     * Initializes, updates and returns content with the incoming URL
     *
     * @param url     the URL of a content to update.
     * @param content the content to update.
     * @return The updating content with incoming id.
     */
    T update(String url, T content);

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the incoming title.
     *
     * @param title   the title of a content to return.
     * @param isValid is get valid content or not.
     * @return The content with the incoming title.
     */
    T getByTitle(String title, boolean isValid);

    /**
     * Returns content object of the {@link Content} class or subclasses
     * with the incoming URL.
     *
     * @param url     the URL of a content to return.
     * @param isValid is get valid content or not.
     * @return The content with the incoming URL.
     */
    T getByUrl(String url, boolean isValid);

    /**
     * Removes content object of the {@link Content} class or subclasses
     * with the incoming title.
     *
     * @param title the title of a content to remove.
     */
    void removeByTitle(String title);

    /**
     * Removes content object of the {@link Content} class or subclasses
     * with the incoming url.
     *
     * @param url the URL of a content to remove.
     */
    void removeByUrl(String url);

    /**
     * Sorts and returns objects of {@link Content} class
     * or subclasses by title.
     *
     * @param contents the contents to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
     */
    List<T> sortByTitle(Collection<T> contents, boolean revers);

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     *
     * @param contents the objects to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of contents.
     */
    List<T> sortByUrl(Collection<T> contents, boolean revers);

    /**
     * Sorts and returns objects of {@link Content} class
     * or subclasses by title.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents.
     */
    List<T> getAndSortByTitle(boolean revers);

    /**
     * Sorts and returns objects of {@link Content} class or subclasses by URL.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of contents.
     */
    List<T> getAndSortByUrl(boolean revers);
}
