package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEditor;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see User
 */
public final class UserEditor extends ModelEditor<User, UserEditor> {

    /**
     * The user to edit.
     */
    private final User user;

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
    private int mailing;

    /**
     * Locked the user or not.
     */
    private int locked;

    /**
     * Constructor.
     *
     * @param user the user to edit.
     */
    UserEditor(final User user) {
        super(user);
        this.user = user;
    }

    /**
     * Updates and returns a new user.
     *
     * @return The updated user.
     */
    @Override
    public User update() {
        final UserBuilder builder = User.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addName(getName())
                .addUrl(getUrl())
                .addLogin(getLogin())
                .addPassword(getPassword())
                .addDescription(getDescription())
                .addContacts(getContacts())
                .addPhoto(getPhoto())
                .addRole(getRole())
                .addMailing(isMailing())
                .addLocked(isLocked());
        return builder.build();
    }

    /**
     * Copies the incoming user.
     *
     * @param user the user to copy.
     * @return the user editor.
     */
    @Override
    public UserEditor copy(final User user) {
        if (isNotNull(user)) {
            super.copy(user)
                    .addValidated(user.isValidated())
                    .addName(user.getName())
                    .addUrl(user.getUrl())
                    .addLogin(user.getLogin())
                    .addPassword(user.getPassword())
                    .addDescription(user.getDescription())
                    .addContacts(user.getContacts())
                    .addPhoto(user.getPhoto())
                    .addRole(user.getRole())
                    .addMailing(user.isMailing())
                    .addLocked(user.isLocked());
        }
        return this;
    }

    /**
     * Adds a new name to the user.
     *
     * @param name the new name to the user.
     * @return the user editor.
     */
    public UserEditor addName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Adds a new URL to the user.
     *
     * @param url the new URL to the user.
     * @return the user editor.
     */
    public UserEditor addUrl(final String url) {
        this.url = url;
        return this;
    }

    /**
     * Adds a new login to the user.
     *
     * @param login the new login to the user.
     * @return the user editor.
     */
    public UserEditor addLogin(final String login) {
        this.login = login;
        return this;
    }

    /**
     * Adds a new password to the user.
     *
     * @param password the new password to the user.
     * @return the user editor.
     */
    public UserEditor addPassword(final String password) {
        this.password = password;
        return this;
    }

    /**
     * Adds a new description to the user.
     *
     * @param description the new description to the user.
     * @return the user editor.
     */
    public UserEditor addDescription(final String description) {
        this.description = description;
        return this;
    }

    /**
     * Adds a new contacts to the user.
     *
     * @param contacts the new contacts to the user.
     * @return the user editor.
     */
    public UserEditor addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Adds a new photo to the user.
     *
     * @param photo the new photo to the user.
     * @return the user editor.
     */
    public UserEditor addPhoto(final File photo) {
        this.photo = photo;
        return this;
    }

    /**
     * Adds a new role to the user.
     *
     * @param role the new role to the user.
     * @return the user editor.
     */
    public UserEditor addRole(final UserRole role) {
        this.role = role;
        return this;
    }

    /**
     * Adds a new mailing to the user.
     * Adds true if the user is mailing, false is not mailing.
     *
     * @param mailing the mailing of the user.
     * @return the user editor.
     */
    public UserEditor addMailing(final boolean mailing) {
        this.mailing = mailing ? 1 : 2;
        return this;
    }

    /**
     * Adds mailing user.
     *
     * @return the user builder.
     */
    public UserEditor isMail() {
        return addMailing(true);
    }

    /**
     * Adds not mailing user.
     *
     * @return the user builder.
     */
    public UserEditor isNotMail() {
        return addMailing(false);
    }

    /**
     * Adds a new locked to the user.
     * Adds true if the user is locked, false is invalid.
     *
     * @param locked the locked of the user.
     * @return the user editor.
     */
    public UserEditor addLocked(final boolean locked) {
        this.locked = locked ? 1 : 2;
        return this;
    }

    /**
     * Adds locked user.
     *
     * @return the user builder.
     */
    public UserEditor isLock() {
        return this.addLocked(true);
    }

    /**
     * Adds not locked user.
     *
     * @return the user builder.
     */
    public UserEditor isNotLock() {
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
        return !isLocked() && super.isValidated();
    }

    /**
     * Returns a new name of the user.
     * Returns the user name if the name is null.
     *
     * @return The name (newer null).
     */
    private String getName() {
        return isNotNull(this.name) ? this.name : this.user.getName();
    }

    /**
     * Returns a new URL of the user.
     * Returns the user URL if the URL is null.
     *
     * @return The URL (newer null).
     */
    private String getUrl() {
        return isNotNull(this.url) ? this.url : this.user.getUrl();
    }

    /**
     * Returns a new login of the user.
     * Returns the user login if the login is null.
     *
     * @return The login (newer null).
     */
    private String getLogin() {
        return isNotNull(this.login) ? this.login : this.user.getLogin();
    }

    /**
     * Returns a new password of the user.
     * Returns the user password if the password is null.
     *
     * @return The password (newer null).
     */
    private String getPassword() {
        return isNotNull(this.password) ? this.password : this.user.getPassword();
    }

    /**
     * Returns a new description of the user.
     * Returns the user description if the description is null.
     *
     * @return The description (newer null).
     */
    private String getDescription() {
        return isNotNull(this.description) ? this.description : this.user.getDescription();
    }

    /**
     * Returns a new contacts of the user.
     * Returns the user contacts if the contacts is null.
     *
     * @return The contacts (newer null).
     * @see ContactsEditor
     */
    private Contacts getContacts() {
        final ContactsEditor contactsEditor = this.user.getContacts().getEditor();
        contactsEditor.copy(this.contacts);
        return contactsEditor.update();
    }

    /**
     * Returns a new file of the user.
     * Returns the user file if the file is null.
     *
     * @return The file (newer null).
     * @see FileEditor
     */
    private File getPhoto() {
        final FileEditor fileEditor = this.user.getPhoto().getEditor();
        fileEditor.copy(this.photo);
        return fileEditor.update();
    }

    /**
     * Returns a new role of the user.
     * Returns the user role if the role is null.
     *
     * @return The role (newer null).
     */
    private UserRole getRole() {
        return isNotNull(this.role) ? this.role : this.user.getRole();
    }

    /**
     * Mailing the user.
     * User is mailing if it mailing and not locked.
     *
     * @return true if the user is mailing, false otherwise.
     */
    private boolean isMailing() {
        boolean result;
        if (this.mailing > 0) {
            result = (this.mailing == 1);
        } else {
            result = this.user.isMailing();
        }
        return !isLocked() && result;
    }

    /**
     * Locked the user.
     *
     * @return true if the user is locked, false otherwise.
     */
    private boolean isLocked() {
        boolean result;
        if (this.locked > 0) {
            result = (this.locked == 1);
        } else {
            result = this.user.isLocked();
        }
        return result;
    }
}
