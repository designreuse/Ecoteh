package ua.com.ecoteh.service.data.comparator;

import ua.com.ecoteh.entity.article.Article;
import ua.com.ecoteh.entity.post.Post;

import java.util.Comparator;
import java.util.Date;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class PostComparator extends ContentComparator {

    /**
     * Constructor.
     */
    private PostComparator() {
    }

    /**
     * The class implements a method for working with comparator
     * for {@link Post} by date.
     */
    public final static class ByDate implements Comparator<Post> {

        /**
         * Compares two posts by date.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Post()) = -1
         *     compare(new Post(), null) = 1
         *     compare(new Post(), new Post()) = compares by date
         * </pre>
         *
         * @param first  the post object to be compared.
         * @param second the post object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first post date is less than, equal to, or greater than the
         * second post date.
         */
        @Override
        public int compare(final Post first, final Post second) {
            int result = PostComparator.compare(first, second);
            if (result == 2) {
                result = compareByDate(first, second);
            }
            return result;
        }

        /**
         * Compares two posts by date.
         *
         * @param first  the first post to be compared (newer null).
         * @param second the second post to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first post date is less than, equal to, or greater than the
         * second post date.
         */
        private int compareByDate(final Post first, final Post second) {
            final Date firstDate = first.getDate();
            final Date secondDate = second.getDate();
            return firstDate.compareTo(secondDate);
        }
    }
}
