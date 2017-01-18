package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.File;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.util.translator.Translator;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link com.salimov.yurii.entity.User} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.entity.User
 * @see IModel
 */
public interface IUser<E extends Number> extends IModel<E> {

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param email       a new email of the user.
     * @param phone       a new phone of the user.
     * @param description a new description of the user.
     */
    void initialize(
            final String name,
            final String email,
            final String phone,
            final String description
    );

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param login       a new login of the user.
     * @param password    a new password of the user.
     * @param email       a new e-mail of the user.
     * @param phone       a new phone of the user.
     * @param vkontakte   a new vkontakte url of the user.
     * @param facebook    a new facebook url of the user.
     * @param twitter     a new twitter url of the user.
     * @param skype       a new skype username of the user.
     * @param description a new description of the user.
     */
    void initialize(
            final String name,
            final String login,
            final String password,
            final String email,
            final String phone,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String description
    );

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param login       a new login of the user.
     * @param password    a new password of the user.
     * @param email       a new e-mail of the user.
     * @param phone       a new phone of the user.
     * @param description a new description of the user.
     * @param photoUrl    a new photo URL of the user.
     * @param role        a new role of the user.
     * @see File
     * @see UserRole
     */
    void initialize(
            final String name,
            final String login,
            final String password,
            final String email,
            final String phone,
            final String description,
            final String photoUrl,
            final UserRole role
    );

    /**
     * Returns a name of the user.
     *
     * @return The user name.
     */
    String getName();

    /**
     * Sets a new name to the user.
     *
     * @param name a new name to the user.
     */
    void setName(final String name);

    /**
     * Sets a new login to the user.
     *
     * @param login a new login to the user.
     */
    void setLogin(final String login);

    /**
     * Returns a login of the user.
     *
     * @return The user login.
     */
    String getLogin();

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
     * @see Translator
     */
    void translateAndSetUrl(final String value);

    /**
     * Returns a url of the user.
     *
     * @return The user url.
     */
    String getUrl();

    /**
     * Sets a new url to the user.
     *
     * @param url a new url to the user.
     */
    void setUrl(final String url);

    /**
     * Returns a password of the user.
     *
     * @return The user password.
     */
    String getPassword();

    /**
     * Sets a new password to the user.
     *
     * @param password a new password to the user.
     */
    void setPassword(final String password);

    /**
     * Returns a e-mail of the user.
     *
     * @return The user e-mail.
     */
    String getEmail();

    /**
     * Sets a new e-mail to the user.
     *
     * @param email a new e-mail to the user.
     */
    void setEmail(final String email);

    /**
     * Returns a phone of the user.
     *
     * @return The user phone.
     */
    String getPhone();

    /**
     * Sets a new phone to the user.
     *
     * @param phone a new phone to the user.
     */
    void setPhone(final String phone);

    /**
     * Returns a vkontakte url of the user.
     *
     * @return The user vkontakte url.
     */
    String getVkontakte();

    /**
     * Sets a new vkontakte url to the user.
     *
     * @param vkontakte a new vkontakte url to the user.
     */
    void setVkontakte(final String vkontakte);

    /**
     * Returns a facebook url of the user.
     *
     * @return The user facebook url.
     */
    String getFacebook();

    /**
     * Sets a new facebook url to the user.
     *
     * @param facebook a new facebook url to the user.
     */
    void setFacebook(final String facebook);

    /**
     * Returns a twitter url of the user.
     *
     * @return The user twitter url.
     */
    String getTwitter();

    /**
     * Sets a new twitter url to the user.
     *
     * @param twitter a new twitter url to the user.
     */
    void setTwitter(final String twitter);

    /**
     * Returns a skype username of the user.
     *
     * @return The user skype username.
     */
    String getSkype();

    /**
     * Sets a new skype username to the user.
     *
     * @param skype a new skype username to the user.
     */
    void setSkype(final String skype);

    /**
     * Returns a description of the user.
     *
     * @return The user description.
     */
    String getDescription();

    /**
     * Sets a new description to the user.
     *
     * @param description a new description to the user.
     */
    void setDescription(final String description);

    /**
     * Returns a photo URL of the user.
     *
     * @return The user photo URL.
     */
    String getPhotoUrl();

    /**
     * Sets a new photo to the user.
     * If photo URL is blank, then sets {@code null}.
     *
     * @param photoUrl a new photo URL to the user.
     */
    void setPhotoUrl(final String photoUrl);

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     * @see UserRole
     */
    UserRole getRole();

    /**
     * Sets a new role to the user.
     *
     * @param role a new role to the user.
     * @see UserRole
     */
    void setRole(final UserRole role);

    /**
     * Returns the value of permit to send a letters on the user email.
     *
     * @return The permit to send a letters on the user email.
     */
    boolean isMailing();

    /**
     * Sets value of permit to send a letters on the user email.
     *
     * @param isMailing a permit to send a letters on the user email.
     */
    void setMailing(final boolean isMailing);

    /**
     * Returns the value of the locked user or not.
     *
     * @return The value of the locked user or not.
     */
    boolean isLocked();

    /**
     * Sets the value of the locked user or not.
     *
     * @param locked a value of locked the user or not.
     */
    void setLocked(final boolean locked);
}
