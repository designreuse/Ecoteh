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
     * @param name the new name to the user.
     */
    void setName(String name);

    /**
     * Sets a new login to the user.
     *
     * @param login the new login to the user.
     */
    void setLogin(String login);

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
     * @param login the new encrypted login to the user.
     */
    void setEncryptedLogin(String login);

    /**
     * Returns a password of the user.
     *
     * @return The user password.
     */
    String getPassword();

    /**
     * Sets a new password to the user.
     *
     * @param password the new password to the user.
     */
    void setPassword(String password);

    /**
     * Returns a encrypted password.
     *
     * @return The encrypted password..
     */
    String getEncryptedPassword();

    /**
     * Sets a new encrypted password to the user.
     *
     * @param password the new encrypted password to the user.
     */
    void setEncryptedPassword(String password);

    /**
     * Translates value and sets to url.
     *
     * @param value the value to translate.
     */
    void translateAndSetUrl(String value);

    /**
     * Returns a url of the user.
     *
     * @return The user url.
     */
    String getUrl();

    /**
     * Sets a new url to the user.
     *
     * @param url the new url to the user.
     */
    void setUrl(String url);

    /**
     * Returns a description of the user.
     *
     * @return The user description.
     */
    String getDescription();

    /**
     * Sets a new description to the user.
     *
     * @param description the new description to the user.
     */
    void setDescription(String description);

    /**
     * Returns a photo of the user.
     *
     * @return The user photo.
     */
    File getPhoto();

    /**
     * Sets a new photo to the user.
     *
     * @param photo the new photo to the user.
     */
    void setPhoto(File photo);

    /**
     * Returns a user contacts.
     *
     * @return The user contacts.
     */
    Contacts getContacts();

    /**
     * Sets a new contacts to the user.
     *
     * @param contacts the new contacts to the user.
     */
    void setContacts(Contacts contacts);

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     */
    UserRole getRole();

    /**
     * Sets a new role to the user.
     *
     * @param role the new role to the user.
     */
    void setRole(UserRole role);

    /**
     * Returns the value of permit to send a letters on the user email.
     *
     * @return The permit to send a letters on the user email.
     */
    boolean isMailing();

    /**
     * Sets value of permit to send a letters on the user E-mail.
     *
     * @param isMailing the permit to send a letters
     *                  on the user E-mail.
     */
    void setMailing(boolean isMailing);

    /**
     * Returns the value of the locked user or not.
     *
     * @return The value of the locked user or not.
     */
    boolean isLocked();

    /**
     * Sets the value of the locked user or not.
     *
     * @param locked the value of locked the user or not.
     */
    void setLocked(boolean locked);

    /**
     * Initializes the user.
     * Returns this v with a new copied fields.
     *
     * @param user the user to copy.
     * @return The this user with a new fields.
     */
    User initialize(User user);
}
