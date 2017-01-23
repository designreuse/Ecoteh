package com.salimov.yurii.config;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to work with the default users.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see User
 */
public final class DefaultConfig {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER
            = Logger.getLogger(DefaultConfig.class);

    /**
     * It is admin requests is enabled.
     */
    private static boolean adminEnabled;

    /**
     * It is client requests is enabled.
     */
    private static boolean clientEnabled;

    /**
     * The map of a default users.
     */
    private final static Map<String, User> USERS;

    /**
     * Static block for initialization of map
     * with a default users.
     */
    static {
        adminEnabled = true;
        clientEnabled = true;
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
        return USERS.get(username);
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
        return getDefaultUser("super");
    }

    /**
     * Returns {@code true} if admin requests is enabled,
     * {@code false} otherwise.
     *
     * @return {@code true} if admin requests is enabled,
     * {@code false} otherwise.
     */
    public static boolean isAdminEnabled() {
        return adminEnabled;
    }

    /**
     * Returns {@code true} if client requests is enabled,
     * {@code false} otherwise.
     *
     * @return {@code true} if client requests is enabled,
     * {@code false} otherwise.
     */
    public static boolean isClientEnabled() {
        return clientEnabled;
    }

    /**
     * Enables requests for clients.
     */
    public static void on() {
        if (checkUser() || checkSuperUser()) {
            clientEnabled = true;
        }
    }

    /**
     * Disables requests for clients.
     */
    public static void off() {
        if (checkUser() || checkSuperUser()) {
            clientEnabled = false;
        }
    }

    /**
     * Enables requests for clients and admins.
     */
    public static void superOn() {
        if (checkSuperUser()) {
            adminEnabled = true;
            clientEnabled = true;
        }
    }

    /**
     * Disables requests for clients and admins.
     */
    public static void superOff() {
        if (checkSuperUser()) {
            adminEnabled = false;
            clientEnabled = false;
        }
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
        return check(UserRole.SUPERMAN);
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
        return (
                user != null
        ) && (
                user.getRole().equals(role)
        );
    }

    /**
     * Returns authenticated user.
     *
     * @return The authenticated user.
     * @see User
     */
    private static User getAuthenticatedUser() {
        User user;
        try {
            user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
        } catch (NullPointerException ex) {
            LOGGER.error(ex.getMessage(), ex);
            //ex.printStackTrace();
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
                "97,100,109,105,110",
                "97,100,109,105,110,112,97,115,115",
                UserRole.ADMIN
        );
        USERS.put(
                user.getLogin(),
                user
        );
    }

    /**
     * Adds a super admin to global map.
     */
    private static void addSuperAdmin() {
        final User user = createUser(
                "Super Admin",
                "115,117,112,101,114",
                "121,117,114,105,105,115,97,108,105,109,111,118",
                UserRole.SUPERMAN
        );
        USERS.put(
                user.getLogin(),
                user
        );
    }

    /**
     * Creates and returns a new user.
     *
     * @param name     a name of the new user.
     * @param login    a login of the new user.
     * @param password a password of the new user.
     * @param role     a role of the new user.
     * @return The new user.
     * @see User
     */
    private static User createUser(
            final String name,
            final String login,
            final String password,
            final UserRole role
    ) {
        final User user = new User();
        user.setName(name);
        user.setEncryptedLogin(login);
        user.setEncryptedPassword(password);
        user.setRole(role);
        return user;
    }
}
