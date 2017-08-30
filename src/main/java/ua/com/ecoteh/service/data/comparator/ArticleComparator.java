package ua.com.ecoteh.service.data.comparator;

import ua.com.ecoteh.entity.article.Article;

import java.util.Comparator;
import java.util.Date;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link Article} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class ArticleComparator extends ContentComparator {

    /**
     * Constructor.
     */
    private ArticleComparator() {
    }

    /**
     * The class implements a method for working with comparator
     * for {@link Article} by number.
     */
    public final static class ByNumber implements Comparator<Article> {

        /**
         * Compares two articles by number.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Article()) = -1
         *     compare(new Article(), null) = 1
         *     compare(new Article(), new Article()) = compares by number
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first article number is less than, equal to, or greater than the
         * second article number.
         */
        @Override
        public int compare(final Article first, final Article second) {
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
        private int compareByNumber(final Article first, final Article second) {
            final String firstNumber = first.getNumber();
            final String secondNumber = second.getNumber();
            return firstNumber.compareTo(secondNumber);
        }
    }

    /**
     * The class implements a method for working with comparator
     * for {@link Article} by date.
     */
    public final static class ByDate implements Comparator<Article> {

        /**
         * Compares two articles by date.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Article()) = -1
         *     compare(new Article(), null) = 1
         *     compare(new Article(), new Article()) = compares by date
         * </pre>
         *
         * @param first  the article object to be compared.
         * @param second the article object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first article date is less than, equal to, or greater than the
         * second article date.
         */
        @Override
        public int compare(final Article first, final Article second) {
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
        private int compareByDate(final Article first, final Article second) {
            final Date firstDate = first.getDate();
            final Date secondDate = second.getDate();
            return firstDate.compareTo(secondDate);
        }
    }

    /**
     * The class implements a method for working with comparator
     * for {@link Article} by price.
     */
    public final static class ByPrice implements Comparator<Article> {

        /**
         * Compares two articles by price.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new Article()) = -1
         *     compare(new Article(), null) = 1
         *     compare(new Article(), new Article()) = compares by price
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first article price is less than, equal to, or greater than the
         * second article price.
         */
        @Override
        public int compare(final Article first, final Article second) {
            int result = ArticleComparator.compare(first, second);
            if (result == 2) {
                result = compareByPrice(first, second);
            }
            return result;
        }

        /**
         * Compares two articles by number.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first article price is less than, equal to, or greater than the
         * second article price.
         */
        private int compareByPrice(final Article first, final Article second) {
            final int firstPrice = first.getSortPrice();
            final int secondPrice = second.getSortPrice();
            return firstPrice - secondPrice;
        }
    }
}
