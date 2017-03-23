package com.salimov.ecoteh.config;

import com.salimov.ecoteh.entity.User;
import com.salimov.ecoteh.enums.UserRole;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to work with the default users.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class DefaultConfig {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(DefaultConfig.class);

    /**
     * The map of a default users.
     */
    private final static Map<String, User> USERS;

    /**
     * Static block for initialization of map
     * with a default users.
     */
    static {
        USERS = new HashMap<>();
        addDefaultAdmin();
        addSuperAdmin();
    }

    /**
     * Returns a default user with the username.
     *
     * @param username a name of user to return.
     * @return The user if it exist,
     * {@code null} otherwise.
     */
    public static User getDefaultUser(final String username) {
        User user;
        try {
            user = USERS.get(username).clone();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * Returns a default admin.
     *
     * @return The default admin.
     */
    public static User getDefaultAdmin() {
        return getDefaultUser("admin");
    }

    /**
     * Returns a default super admin.
     *
     * @return The default super admin.
     */
    public static User getSuperAdmin() {
        return getDefaultUser("superadmin");
    }

    /**
     * Checks a role of an authorized user.
     * Authorized user must have the ADMIN role.
     *
     * @return {@code true} if an authorized user has
     * the ADMIN role, {@code false} otherwise.
     */
    private static boolean checkUser() {
        return check(UserRole.ADMIN);
    }

    /**
     * Checks a role of an authorized user.
     * Authorized user must have the SUPERMAN role.
     *
     * @return {@code true} if an authorized user has
     * the SUPERMAN role, {@code false} otherwise.
     */
    private static boolean checkSuperUser() {
        return check(UserRole.SUPERADMIN);
    }

    /**
     * Checks a role of an authorized user.
     *
     * @param role a role to check.
     * @return {@code true} if an authorized user has the role,
     * {@code false} otherwise.
     */
    private static boolean check(final UserRole role) {
        final User user = getAuthenticatedUser();
        return (user != null) && (user.getRole().equals(role));
    }

    /**
     * Returns authenticated user.
     *
     * @return The authenticated user.
     */
    private static User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (NullPointerException ex) {
            LOGGER.error(ex.getMessage(), ex);
            user = null;
        }
        return user;
    }

    /**
     * Adds a default admin to global map.
     */
    private static void addDefaultAdmin() {
        final User user = createUser(
                "Default Admin",
                "login", "password",
                UserRole.ADMIN
        );
        USERS.put(user.getLogin(), user);
    }

    /**
     * Adds a super admin to global map.
     */
    private static void addSuperAdmin() {
        final User user = createUser(
                "Super Admin",
                "login", "password",
                UserRole.SUPERADMIN
        );
        USERS.put(user.getLogin(), user);
    }

    /**
     * Creates and returns a new user.
     *
     * @param name     a name of the new user.
     * @param login    a login of the new user.
     * @param password a password of the new user.
     * @param role     a role of the new user.
     * @return The new user.
     */
    private static User createUser(
            final String name,
            final String login,
            final String password,
            final UserRole role
    ) {
        final User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }
}
