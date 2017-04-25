package ua.com.ecoteh.entity;

import ua.com.ecoteh.enums.UserRole;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link User} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface IUser extends IModel {

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
     * Returns a encrypted login.
     *
     * @return The encrypted login.
     */
    String getEncryptedLogin();

    /**
     * Sets a new encrypted login to the user.
     *
     * @param login a new encrypted login to the user.
     */
    void setEncryptedLogin(final String login);

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
     * Returns a encrypted password.
     *
     * @return The encrypted password..
     */
    String getEncryptedPassword();

    /**
     * Sets a new encrypted password to the user.
     *
     * @param password a new encrypted password to the user.
     */
    void setEncryptedPassword(final String password);

    /**
     * Translates value and sets to url.
     *
     * @param value a value to translate.
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
    File getPhoto();

    /**
     * Sets a new photo to the user.
     *
     * @param photoUrl a new photo URL to the user.
     */
    void setPhoto(final File photoUrl);

    /**
     * Returns a user contacts.
     *
     * @return The user contacts.
     */
    Contacts getContacts();

    /**
     * Sets a new contacts to the user.
     *
     * @param contacts a new contacts to the user.
     */
    void setContacts(final Contacts contacts);

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     */
    UserRole getRole();

    /**
     * Sets a new role to the user.
     *
     * @param role a new role to the user.
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

    /**
     * Initializes the user.
     *
     * @param user a user to copy.
     * @return The this user with new fields.
     */
    User initialize(final User user);
}
