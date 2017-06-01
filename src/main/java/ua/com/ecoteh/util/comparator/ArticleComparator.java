package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.article.ArticleEntity;

import java.util.Comparator;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link ArticleEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ArticleComparator extends ContentComparator {

    /**
     * The class implements a method for working with comparator
     * for {@link ArticleEntity} by number.
     */
    public final static class ByNumber implements Comparator<ArticleEntity> {

        /**
         * Compares two articles by number.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new ArticleEntity()) = -1
         *     compare(new ArticleEntity(), null) = 1
         *     compare(new ArticleEntity(), new ArticleEntity()) = compares by number
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first article number is less than, equal to, or greater than the
         * second article number.
         */
        @Override
        public int compare(final ArticleEntity first, final ArticleEntity second) {
            int result = ArticleComparator.compare(first, second);
            if (result == 2) {
                result = compareByNumber(first, second);
            }
            return result;
        }

        /**
         * Compares two articles by number.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first article number is less than, equal to, or greater than the
         * second article number.
         */
        private static int compareByNumber(final ArticleEntity first, final ArticleEntity second) {
            return first.getNumber().compareTo(second.getNumber());
        }
    }

    /**
     * The class implements a method for working with comparator
     * for {@link ArticleEntity} by date.
     */
    public final static class ByDate implements Comparator<ArticleEntity> {

        /**
         * Compares two articles by date.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new ArticleEntity()) = -1
         *     compare(new ArticleEntity(), null) = 1
         *     compare(new ArticleEntity(), new ArticleEntity()) = compares by date
         * </pre>
         *
         * @param first  the article object to be compared.
         * @param second the article object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first article date is less than, equal to, or greater than the
         * second article date.
         */
        @Override
        public int compare(final ArticleEntity first, final ArticleEntity second) {
            int result = ArticleComparator.compare(first, second);
            if (result == 2) {
                result = compareByDate(first, second);
            }
            return result;
        }

        /**
         * Compares two articles by date.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first article date is less than, equal to, or greater than the
         * second article date.
         */
        private static int compareByDate(final ArticleEntity first, final ArticleEntity second) {
            return first.getDate().compareTo(second.getDate());
        }
    }
}
