package com.salimov.yurii.config;

import com.salimov.yurii.entity.User;
import com.salimov.yurii.enums.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * Class with default configuration.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class DefaultConfig {

    private static boolean adminEnabled;
    private static boolean clientEnabled;

    private final static Map<String, User> USERS;

    static {
        adminEnabled = true;
        clientEnabled = true;
        USERS = new HashMap<>();
        addDefaultAdmin();
        addSuperAdmin();
    }

    public static User getDefaultUser(final String username) {
        return USERS.get(username);
    }

    public static User getDefaultAdmin() {
        return getDefaultUser("admin");
    }

    public static User getSuperAdmin() {
        return getDefaultUser("super");
    }

    public static boolean isAdminEnabled() {
        return adminEnabled;
    }

    public static boolean isClientEnabled() {
        return clientEnabled;
    }

    public static void superOn() {
        if (checkSuperUser()) {
            adminEnabled = true;
            clientEnabled = true;
        }
    }

    public static void on() {
        if (checkUser() || checkSuperUser()) {
            clientEnabled = true;
        }
    }

    public static void superOff() {
        if (checkSuperUser()) {
            adminEnabled = false;
            clientEnabled = false;
        }
    }

    public static void off() {
        if (checkUser() || checkSuperUser()) {
            clientEnabled = false;
        }
    }

    private static boolean checkSuperUser() {
        return check(UserRole.SUPERMAN);
    }

    private static boolean checkUser() {
        return check(UserRole.ADMIN);
    }

    private static boolean check(final UserRole role) {
        boolean result = false;
        final Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();
        if (authentication != null) {
            final User user = (User) authentication.getPrincipal();
            result = (
                    user != null
            ) && (
                    user.getRole()
                            .equals(role)
            );
        }
        return result;
    }

    private static void addDefaultAdmin() {
        final User user = createUser(
                "Default Admin",
                "login",
                "password",
                UserRole.ADMIN
        );
        USERS.put(
                user.getLogin(),
                user
        );
    }

    private static void addSuperAdmin() {
        final User user = createUser(
                "Super Admin",
                "login",
                "password",
                UserRole.SUPERMAN
        );
        USERS.put(
                user.getLogin(),
                user
        );
    }

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
