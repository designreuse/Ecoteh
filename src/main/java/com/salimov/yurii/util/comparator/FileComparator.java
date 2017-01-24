package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.File;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link File}.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see File
 */
public final class FileComparator {

    /**
     * The class implements a method for working 
     * with comparator for {@link File} by title.
     *
     * @see File
     */
    public final static class ByTitle<T extends File> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param file1 The first object to be compared.
         * @param file2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see File
         */
        @Override
        public int compare(final T file1, final T file2) {
            int result;
            if (file1 == null && file2 == null) {
                result = 0;
            } else if (file1 == null) {
                result = -1;
            } else if (file2 == null) {
                result = 1;
            } else {
                result = file1.getTitle()
                        .compareToIgnoreCase(
                                file2.getTitle()
                        );
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link File} by url.
     *
     * @see File
     */
    public final static class ByUrl<T extends File> implements Comparator<T> {

        /**
         * Compares its two arguments for order.
         *
         * @param file1 The first object to be compared.
         * @param file2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see File
         */
        @Override
        public int compare(final T file1, final T file2) {
            int result;
            if (file1 == null && file2 == null) {
                result = 0;
            } else if (file1 == null) {
                result = -1;
            } else if (file2 == null) {
                result = 1;
            } else {
                result = file1.getUrl()
                        .compareTo(
                                file2.getUrl()
                        );
            }
            return result;
        }
    }
}
