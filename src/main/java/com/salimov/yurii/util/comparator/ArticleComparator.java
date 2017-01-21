package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.Article;

import java.util.Comparator;

/**
 * The class implements a set of methods for working
 * with comparators for {@link Article}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Article
 */
public final class ArticleComparator {

    /**
     * The class implements a method for working with comparator
     * for {@link Article} by number.
     *
     * @see Article
     */
    public final static class ByNumber implements Comparator<Article> {
        /**
         * Compares its two arguments for order.
         *
         * @param article1 The first object to be compared.
         * @param article2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see Article
         */
        @Override
        public int compare(final Article article1, final Article article2) {
            int result;
            if (article1 == null && article2 == null) {
                result = 0;
            } else if (article1 == null) {
                result = -1;
            } else if (article2 == null) {
                result = 1;
            } else {
                result = article1.getNumber()
                        .compareTo(
                                article2.getNumber()
                        );
            }
            return result;
        }
    }

    /**
     * The class implements a method for working with comparator
     * for {@link Article} by date.
     *
     * @see Article
     */
    public final static class ByDate implements Comparator<Article> {

        /**
         * Compares its two arguments for order.
         *
         * @param article1 the first object to be compared.
         * @param article2 the second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see Article
         */
        @Override
        public int compare(final Article article1, final Article article2) {
            int result;
            if (article1 == null && article2 == null) {
                result = 0;
            } else if (article1 == null) {
                result = -1;
            } else if (article2 == null) {
                result = 1;
            } else {
                result = article1.getDate()
                        .compareTo(
                                article2.getDate()
                        );
            }
            return result;
        }
    }
}
