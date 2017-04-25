package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.Content;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link Content}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class ContentComparator {

    /**
     * The class implements a method for working
     * with comparator for {@link Content} by title.
     */
    public final static class ByTitle<T extends Content> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param content1 The first object to be compared.
         * @param content2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(final T content1, final T content2) {
            int result;
            if ((content1 == null) && (content2 == null)) {
                result = 0;
            } else if (content1 == null) {
                result = -1;
            } else if (content2 == null) {
                result = 1;
            } else {
                result = content1.getTitle().compareToIgnoreCase(content2.getTitle());
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link Content} by URL.
     */
    public final static class ByUrl<T extends Content> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param content1 The first object to be compared.
         * @param content2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         */
        @Override
        public int compare(final T content1, final T content2) {
            int result;
            if (content1 == null && content2 == null) {
                result = 0;
            } else if (content1 == null) {
                result = -1;
            } else if (content2 == null) {
                result = 1;
            } else {
                result = content1.getUrl().compareToIgnoreCase(content2.getUrl());
            }
            return result;
        }
    }
}
