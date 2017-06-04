package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.content.Content;

import java.util.Comparator;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link Content} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContentComparator extends AbstractComparator {

    /**
     * The class implements a method for working
     * with comparator for {@link Content} by title.
     *
     * @param <T> entity type, extends {@link Content}.
     */
    public final static class ByTitle<T extends Content> implements Comparator<T> {

        /**
         * Compares two contents by title.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Content()) = -1
         *     compare(new Content(), null) = 1
         *     compare(new Content(), new Content()) = compares by title
         * </pre>
         *
         * @param first  the first content to be compared.
         * @param second the second content to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first content title is less than, equal to, or greater than the
         * second content title.
         */
        @Override
        public int compare(final T first, final T second) {
            int result = ContentComparator.compare(first, second);
            if (result == 2) {
                result = compareByTitle(first, second);
            }
            return result;
        }

        /**
         * Compares two contents by title.
         *
         * @param <T>    entity type, extends {@link Content}.
         * @param first  the first content to be compared (newer null).
         * @param second the second content to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first content title is less than, equal to, or greater than the
         * second content title.
         */
        private <T extends Content> int compareByTitle(final T first, final T second) {
            return first.getTitle().compareToIgnoreCase(second.getTitle());
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link Content} by URL.
     */

    /**
     * The class implements a method for working
     * with comparator for {@link Content} by URL.
     *
     * @param <T> entity type, extends {@link Content}.
     */
    public final static class ByUrl<T extends Content> implements Comparator<T> {

        /**
         * Compares two contents by URL.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Content()) = -1
         *     compare(new Content(), null) = 1
         *     compare(new Content(), new Content()) = compares by URL
         * </pre>
         *
         * @param first  the first content to be compared.
         * @param second the second content to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first content URL is less than, equal to, or greater than the
         * second content URL.
         */
        @Override
        public int compare(final T first, final T second) {
            int result = ContentComparator.compare(first, second);
            if (result == 2) {
                result = compareByUrl(first, second);
            }
            return result;
        }

        /**
         * Compares two contents by URL.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Content()) = -1
         *     compare(new Content(), null) = 1
         *     compare(new Content(), new Content()) = compares by URL
         * </pre>
         *
         * @param <T>    entity type, extends {@link Content}.
         * @param first  the first content to be compared (newer null).
         * @param second the second content to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first content URL is less than, equal to, or greater than the
         * second content URL.
         */
        private <T extends Content> int compareByUrl(final T first, final T second) {
            return first.getUrl().compareToIgnoreCase(second.getUrl());
        }
    }
}
