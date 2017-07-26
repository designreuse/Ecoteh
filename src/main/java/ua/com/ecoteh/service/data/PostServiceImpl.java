package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.post.Post;
import ua.com.ecoteh.entity.post.PostEntity;
import ua.com.ecoteh.repository.PostRepository;
import ua.com.ecoteh.service.data.comparator.PostComparator;
import ua.com.ecoteh.util.time.Time;

import java.util.*;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * The class of the service layer, implements a set of methods
 * for working with objects of the {@link Post} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class PostServiceImpl extends ContentServiceImpl<Post, PostEntity> implements PostService {

    /**
     * Constructor.
     *
     * @param repository  the implementation of the {@link PostRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PostServiceImpl(
            final PostRepository repository,
            final FileService fileService
    ) {
        super(repository, fileService);
    }

    /**
     * Sorts and returns incoming posts by date.
     * For sorting used {@link PostComparator.ByDate} comparator.
     *
     * @param posts the posts to sort.
     * @param revers   is sort in descending or ascending.
     * @return The sorted list of posts or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Post> sortByDate(final Collection<Post> posts, final boolean revers) {
        final Comparator<Post> comparator = new PostComparator.ByDate();
        return sort(posts, comparator, revers);
    }

    /**
     * Sorts and returns all posts by date.
     * For sorting used {@link PostComparator.ByDate} comparator.
     *
     * @param revers is sort in descending or ascending.
     * @return The sorted list of posts or empty list (newer null).
     * @see PostComparator.ByDate
     */
    @Override
    @Transactional(readOnly = true)
    public List<Post> getAndSortByDate(final boolean revers) {
        final Collection<Post> posts = getAll();
        return sortByDate(posts, revers);
    }

    /**
     * Filters and returns incoming posts by the date.
     * Return empty list if responses is empty.
     * Return back responses list if dates are null
     * or start date is equals to end finish date.
     * <pre>
     *     filterByDate(null, ..., ...) = empty ArrayList()
     *     filterByDate(new ArrayList(), ..., ...) = empty ArrayList()
     *
     *     Collection collection = new ArrayList();
     *     collection.add(new Post());
     *     filterByDate(collection, null, null) = collection
     *     filterByDate(collection, new Date(), null) = collection
     *     filterByDate(collection, null, new Date()) = collection
     *
     *     Date start = new Date();
     *     Date finish = new Date();
     *     if start date greater than finish date:
     *     filterByDate(collection, start, finish) = collection
     *     if finish date greater than start date:
     *     filterByDate(collection, start, finish) = filtered list of posts
     * </pre>
     *
     * @param posts the posts to filter.
     * @param start    the initial date.
     * @param finish   the final date.
     * @return The filtered / no filtered list of posts or empty list (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Post> filterByDate(
            final Collection<Post> posts,
            final Date start, final Date finish
    ) {
        final List<Post> result = new ArrayList<>();
        if (isNotEmpty(posts)) {
            if (checkDate(start, finish)) {
                result.addAll(
                        posts.stream()
                                .filter(post -> timeFilter(post, start, finish))
                                .collect(Collectors.toList())
                );
            } else {
                result.addAll(posts);
            }
        }
        return result;
    }

    /**
     * Filters and returns posts by the date.
     * <pre>
     *     getAndFilterByDate(null, null) = all posts
     *     getAndFilterByDate(new Date(), null) = all posts
     *     getAndFilterByDate(null, new Date()) = all posts
     *
     *     Date startDate = new Date();
     *     Date finishDate = new Date();
     *     if start date greater than finish date:
     *     filterByDate(start, finish) = all posts
     *     if finish date greater than start date:
     *     filterByDate(start, finish) = filtered list of posts
     * </pre>
     *
     * @param start  the initial date.
     * @param finish the final date.
     * @return The filtered list of posts or empty list (newer null).
     */
    @Override
    @Transactional
    public List<Post> getAndFilterByDate(final Date start, final Date finish) {
        final Collection<Post> posts = getAll();
        return filterByDate(posts, start, finish);
    }

    /**
     * Return Class object of {@link Post} class.
     *
     * @return The Class object of {@link Post} class.
     */
    @Override
    Class<Post> getModelClass() {
        return Post.class;
    }

    /**
     * Checks a dates to correction.
     * Dates must be not null and not equals between themselves.
     * Used the checkDate() method of the {@link Time} class.
     *
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if dates are correct, false otherwise.
     */
    private boolean checkDate(final Date startDate, final Date finishDate) {
        return Time.checkDate(startDate, finishDate);
    }

    /**
     * Filters post object date with input dates.
     * Used the checkTime() method of the {@link Time} class.
     *
     * @param post    the post to filter.
     * @param startDate  the initial date.
     * @param finishDate the final date.
     * @return true if time is correct, false otherwise.
     */
    private boolean timeFilter(final Post post, final Date startDate, final Date finishDate) {
        final Date postDate = post.getDate();
        return Time.checkTime(postDate, startDate, finishDate);
    }
}
