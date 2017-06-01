package ua.com.ecoteh.util.comparator;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;

import java.util.Comparator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods for working
 * with comparators for objects of the {@link UserEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserComparator extends AbstractComparator {

    /**
     * The class implements a method
     * for working with comparator for {@link UserEntity} by name.
     */
    public final static class ByName implements Comparator<UserEntity> {

        /**
         * Compares two users by name.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new UserEntity()) = -1
         *     compare(new UserEntity(), null) = 1
         *     compare(new UserEntity(), new UserEntity()) = compares by name
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity name is less than, equal to, or greater than the
         * second userEntity name.
         */
        @Override
        public int compare(final UserEntity first, final UserEntity second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByName(first, second);
            }
            return result;
        }

        /**
         * Compares two users by name.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity name is less than, equal to, or greater than the
         * second userEntity name.
         */
        private static int compareByName(final UserEntity first, final UserEntity second) {
            return first.getName().compareToIgnoreCase(second.getName());
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link UserEntity} by URL.
     */
    public final static class ByUrl implements Comparator<UserEntity> {

        /**
         * Compares two users by URL.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new UserEntity()) = -1
         *     compare(new UserEntity(), null) = 1
         *     compare(new UserEntity(), new UserEntity()) = compares by URL
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity URL is less than, equal to, or greater than the
         * second userEntity URL.
         */
        @Override
        public int compare(final UserEntity first, final UserEntity second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByUrl(first, second);
            }
            return result;
        }

        /**
         * Compares two users by URL.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity URL is less than, equal to, or greater than the
         * second userEntity URL.
         */
        private static int compareByUrl(final UserEntity first, final UserEntity second) {
            return first.getUrl().compareToIgnoreCase(second.getUrl());
        }
    }

    /**
     * The class implements a method for working
     * with comparator for {@link UserEntity} by role.
     */
    public final static class ByRole implements Comparator<UserEntity> {

        /**
         * The role comparing.
         */
        private final UserRole role;

        /**
         * Default constructor.
         *
         * @param role The role comparing.
         */
        public ByRole(final UserRole role) {
            this.role = isNotNull(role) ? role : UserRole.ANOTHER;
        }

        /**
         * Compares two users by role.
         * <pre>
         *     compare(null, null) = 0
         *     compare(null, new UserEntity()) = -1
         *     compare(new UserEntity(), null) = 1
         *     compare(new UserEntity(), new UserEntity()) = compares by role
         * </pre>
         *
         * @param first  the first article to be compared.
         * @param second the second article to be compared.
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity role is less than, equal to, or greater than the
         * second userEntity role.
         */
        @Override
        public int compare(final UserEntity first, final UserEntity second) {
            int result = UserComparator.compare(first, second);
            if (result == 2) {
                result = compareByRole(first, second);
            }
            return result;
        }

        /**
         * Compares two users by role.
         *
         * @param first  the first article to be compared (newer null).
         * @param second the second article to be compared (newer null).
         * @return A negative integer, zero, or a positive integer as the
         * first userEntity role is less than, equal to, or greater than the
         * second userEntity role.
         */
        private int compareByRole(final UserEntity first, final UserEntity second) {
            int result;
            if (equalsRole(first, this.role)) {
                result = 1;
            } else if (equalsRole(second, this.role)) {
                result = -1;
            } else {
                result = 0;
            }
            return result;
        }

        /**
         * Returns true if the userEntity role is equal to the incoming role.
         * <pre>
         *     UserEntity userEntity = new UserEntity();
         *     userEntity.setRole(UserRole.ANOTHER);
         *     equalsRole(userEntity, UserRole.ANOTHER) = true
         *     equalsRole(userEntity, UserRole.ADMIN) = false
         * </pre>
         *
         * @param userEntity the userEntity to equals to role (newer null).
         * @param role the role to equals (newer null).
         * @return true if the userEntity role is equal to the incoming role.
         */
        private static boolean equalsRole(final UserEntity userEntity, final UserRole role) {
            return userEntity.getRole().equals(role);
        }
    }
}
