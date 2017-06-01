package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.file.FileEntity;

import java.util.Comparator;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link FileEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class FileComparator extends AbstractComparator {

    /**
     * The class implements a method for working 
     * with comparator for {@link FileEntity} by title.
     */
    public final static class ByTitle implements Comparator<FileEntity> {

        /**
         * Compares two files by title.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new FileEntity()) = -1
         *     compare(new FileEntity(), null) = 1
         *     compare(new FileEntity(), new FileEntity()) = compares by title
         * </pre>
         *
         * @param first  the first file to be compared.
         * @param second the second file to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first file title is less than, equal to, or greater than the
         * second file title.
         */
        @Override
        public int compare(final FileEntity first, final FileEntity second) {
            int result = FileComparator.compare(first, second);
            if (result == 2) {
                result = compareByTitle(first, second);
            }
            return result;
        }

        /**
         * Compares two files by title.
         *
         * @param first  the first file to be compared (newer null).
         * @param second the second file to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first file title is less than, equal to, or greater than the
         * second file title.
         */
        private static int compareByTitle(final FileEntity first, final FileEntity second) {
            return first.getTitle().compareToIgnoreCase(second.getTitle());
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link FileEntity} by URL.
     */
    public final static class ByUrl implements Comparator<FileEntity> {

        /**
         * Compares two files by URL.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new FileEntity()) = -1
         *     compare(new FileEntity(), null) = 1
         *     compare(new FileEntity(), new FileEntity()) = compares by URL
         * </pre>
         *
         * @param first  the first file to be compared.
         * @param second the second file to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first file URL is less than, equal to, or greater than the
         * second file URL.
         */
        @Override
        public int compare(final FileEntity first, final FileEntity second) {
            int result = FileComparator.compare(first, second);;
            if (result == 2) {
                result = compareByUrl(first, second);
            }
            return result;
        }

        /**
         * Compares two files by URL.
         *
         * @param first  the first file to be compared (newer null).
         * @param second the second file to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first file URL is less than, equal to, or greater than the
         * second file URL.
         */
        private static int compareByUrl(final FileEntity first, final FileEntity second) {
            return first.getUrl().compareToIgnoreCase(second.getUrl());
        }
    }
}
