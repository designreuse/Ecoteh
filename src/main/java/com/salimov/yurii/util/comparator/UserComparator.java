package com.salimov.yurii.util.comparator;

import com.salimov.yurii.entity.User;

import java.util.Comparator;

/**
 * The class implements a set of methods
 * for working with comparators for {@link User}.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see User
 */
public final class UserComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link User} by name.
     *
     * @see User
     */
    public final static class ByName implements Comparator<User> {

        /**
         * Compares its two arguments for order.
         *
         * @param user1 The first object to be compared.
         * @param user2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see User
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result;
            if ((user1 == null) && (user2 == null)) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null) {
                result = 1;
            } else {
                result = user1.getName()
                        .compareToIgnoreCase(
                                user2.getName()
                        );
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link User} by url.
     *
     * @see User
     */
    public final static class ByUrl implements Comparator<User> {

        /**
         * Compares its two arguments for order.
         *
         * @param user1 The first object to be compared.
         * @param user2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see User
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result;
            if (user1 == null && user2 == null) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null) {
                result = 1;
            } else {
                result = user1.getUrl()
                        .compareToIgnoreCase(
                                user2.getUrl()
                        );
            }
            return result;
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link User} by role.
     *
     * @see User
     */
    public final static class ByRole implements Comparator<User> {

        /**
         * The role comparing.
         */
        private final User.Role role;

        /**
         * Default constructor.
         *
         * @param role The role comparing.
         */
        public ByRole(final User.Role role) {
            this.role = role;
        }

        /**
         * Compares its two arguments for order.
         *
         * @param user1 The first object to be compared.
         * @param user2 The second object to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @see User
         */
        @Override
        public int compare(final User user1, final User user2) {
            int result = 0;
            if (user1 == null && user2 == null) {
                result = 0;
            } else if (user1 == null) {
                result = -1;
            } else if (user2 == null ||
                    ((user1.getRole() != null) && (user1.getRole().equals(role)))) {
                result = 1;
            } else if ((user2.getRole() != null) &&
                    (user2.getRole().equals(role))) {
                result = -1;
            }
            return result;
        }
    }
}
