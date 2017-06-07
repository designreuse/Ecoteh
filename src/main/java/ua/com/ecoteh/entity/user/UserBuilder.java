package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.util.translator.Translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see User
 */
public final class UserBuilder extends ModelBuilder<User, UserBuilder> {

    /**
     * The name of this user.
     */
    private String name;

    /**
     * The URL of this user.
     */
    private String url;

    /**
     * The login of this user.
     */
    private String login;

    /**
     * The password of this user.
     */
    private String password;

    /**
     * The description of this user.
     */
    private String description;

    /**
     * The user contacts.
     */
    private Contacts contacts;

    /**
     * The user photo.
     */
    private File photo;

    /**
     * The role of this user.
     */
    private UserRole role;

    /**
     * The permit to send a letters on the user E-mail.
     */
    private boolean mailing;

    /**
     * Locked the user or not.
     */
    private boolean locked;

    /**
     * Constructor.
     */
    UserBuilder() {
    }

    /**
     * Builds and returns a new user.
     *
     * @return The new user.
     * @see User
     */
    @Override
    public User build() {
        return new User(
                getId(), isValidated(),
                getName(), getUrl(), getLogin(), getPassword(),
                getDescription(), getContacts(), getPhoto(),
                getRole(), isMailing(), isLocked()
        );
    }

    /**
     * Adds a new name to a new user.
     *
     * @param name the new name to a new user.
     * @return the user builder.
     */
    public UserBuilder addName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Adds a new URL to a new user.
     *
     * @param url the new URL to a new user.
     * @return the user builder.
     */
    public UserBuilder addUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Adds a new login to a new user.
     *
     * @param login the new login to a new user.
     * @return the user builder.
     */
    public UserBuilder addLogin(final String login) {
        this.login = login;
        return this;
    }

    /**
     * Adds a new password to a new user.
     *
     * @param password the new password to a new user.
     * @return the user builder.
     */
    public UserBuilder addPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Adds a new description to a new user.
     *
     * @param description the new description to a new user.
     * @return the user builder.
     */
    public UserBuilder addDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Adds a new contacts to a new user.
     *
     * @param contacts the new contacts to a new user.
     * @return the user builder.
     */
    public UserBuilder addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Adds a new photo to a new user.
     *
     * @param photo the new photo to a new user.
     * @return the user builder.
     */
    public UserBuilder addPhoto(final File photo) {
        this.photo = photo;
        return this;
    }

    /**
     * Adds a new role to a new user.
     *
     * @param role the new role to a new user.
     * @return the user builder.
     */
    public UserBuilder addRole(final UserRole role) {
        this.role = role;
        return this;
    }

    /**
     * Adds a new mailing to a new user.
     * Adds true if the user is mailing, false is invalid.
     *
     * @param mailing the mailing of the user.
     * @return the user builder.
     */
    public UserBuilder addMailing(final boolean mailing) {
        this.mailing = mailing;
        return this;
    }

    /**
     * Adds mailing user.
     *
     * @return the user builder.
     */
    public UserBuilder isMail() {
        return addMailing(true);
    }

    /**
     * Adds not mailing user.
     *
     * @return the user builder.
     */
    public UserBuilder isNotMail() {
        return addMailing(false);
    }

    /**
     * Adds a new locked to a new user.
     * Adds true if the user is locked, false is invalid.
     *
     * @param locked the locked of the user.
     * @return the user builder.
     */
    public UserBuilder addLocked(final boolean locked) {
        this.locked = locked;
        return this;
    }

    /**
     * Adds locked user.
     *
     * @return the user builder.
     */
    public UserBuilder isLock() {
        return this.addLocked(true);
    }

    /**
     * Adds not locked user.
     *
     * @return the user builder.
     */
    public UserBuilder isNotLock() {
        return this.addLocked(false);
    }

    /**
     * Validates the user.
     * User is valid if it valid and not locked.
     *
     * @return true if the user is valid, false otherwise.
     */
    @Override
    protected boolean isValidated() {
        return !this.locked && super.isValidated();
    }

    /**
     * Returns a name of a new user.
     * Returns an empty string if the name is null or empty.
     *
     * @return The name or empty string (newer null).
     */
    private String getName() {
        return isNotEmpty(this.name) ? this.name : "";
    }

    /**
     * Returns a URL of a new user.
     * Returns a translated user name if the URL is null or empty.
     * Returns an empty string if the user name is null or empty.
     *
     * @return The user URL or empty string (newer null).
     */
    private String getUrl() {
        String result;
        if (isNotEmpty(this.url)) {
            result = this.url;
        } else if (isNotEmpty(this.name)) {
            result = Translator.fromCyrillicToLatin(this.name);
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Returns a login of a new user.
     * Returns an empty string if the login is null or empty.
     *
     * @return The login or empty string (newer null).
     */
    private String getLogin() {
        return isNotEmpty(this.login) ? this.login : "";
    }

    /**
     * Returns a password of a new user.
     * Returns an empty string if the password is null or empty.
     *
     * @return The password or empty string (newer null).
     */
    private String getPassword() {
        return isNotEmpty(this.password) ? this.password : "";
    }

    /**
     * Returns a description of a new user.
     * Returns an empty string if the description is null or empty.
     *
     * @return The description or empty string (newer null).
     */
    private String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    /**
     * Returns a contacts of a new user.
     * Returns a new contacts if the contacts is null.
     *
     * @return The user contacts (newer null).
     */
    private Contacts getContacts() {
        return isNotNull(this.contacts) ? this.contacts : Contacts.getBuilder().build();
    }

    /**
     * Returns a photo of a new user.
     * Returns a new file if the photo is null.
     *
     * @return The user photo (newer null).
     */
    private File getPhoto() {
        return isNotNull(this.photo) ? this.photo : File.getBuilder().build();
    }

    /**
     * Returns a role of a new user.
     * Returns an ANOTHER user role if the type is null.
     *
     * @return The user role (newer null).
     */
    private UserRole getRole() {
        return isNotNull(this.role) ? this.role : UserRole.ANOTHER;
    }

    /**
     * Mailing the user.
     * User is mailing if it mailing and not locked.
     *
     * @return true if the user is mailing, false otherwise.
     */
    private boolean isMailing() {
        return !this.locked && this.mailing;
    }

    /**
     * Locked the user.
     *
     * @return true if the user is locked, false otherwise.
     */
    private boolean isLocked() {
        return this.locked;
    }
}
