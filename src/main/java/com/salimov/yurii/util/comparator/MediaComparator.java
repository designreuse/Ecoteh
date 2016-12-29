package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.Media;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link Media}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Media
 */
public final class MediaComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link Media} by title.
     *
     * @see Media
     */
    public static class ByTitle<T extends Media> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param media1 The first object to be compared.
         * @param media2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see Media
         */
        @Override
        public int compare(final T media1, final T media2) {
            int result;
            if (media1 == null && media2 == null) {
                result = 0;
            } else if (media1 == null) {
                result = -1;
            } else if (media2 == null) {
                result = 1;
            } else {
                result = media1.getTitle()
                        .compareToIgnoreCase(media2.getTitle());
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link Media} by url.
     *
     * @see Media
     */
    public static class ByUrl<T extends Media> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param media1 The first object to be compared.
         * @param media2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see Media
         */
        @Override
        public int compare(final T media1, final T media2) {
            int result;
            if (media1 == null && media2 == null) {
                result = 0;
            } else if (media1 == null) {
                result = -1;
            } else if (media2 == null) {
                result = 1;
            } else {
                result = media1.getUrl().compareTo(media2.getUrl());
            }
            return result;
        }
    }
}
