package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.post.Post;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Post} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Post
 */
public interface PostService extends ContentService<Post> {

    /**
     * Sorts and returns posts by date.
     *
     * @param posts  the posts to sort.
     * @param revers is sort in descending or ascending.
     * @return The sorted list of posts.
     */
    List<Post> sortByDate(Collection<Post> posts, boolean revers);

    /**
     * Sorts and returns posts by date.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of posts.
     */
    List<Post> getAndSortByDate(boolean revers);

    /**
     * Filters and returns posts by incoming dates.
     *
     * @param posts  the posts to filter.
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of posts.
     */
    List<Post> filterByDate(Collection<Post> posts, Date start, Date finish);

    /**
     * Filters and returns posts by the dates.
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of posts.
     */
    List<Post> getAndFilterByDate(Date start, Date finish);
}
