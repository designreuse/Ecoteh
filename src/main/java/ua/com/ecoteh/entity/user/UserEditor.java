package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEditor;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEditor;
import ua.com.ecoteh.entity.model.ModelEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class UserEditor extends ModelEditor<User, UserEditor> {

    /**
     *
     */
    private final User user;

    /**
     * The name of a userEntity.
     */
    private String name;

    /**
     * The URL of a userEntity.
     */
    private String url;

    /**
     * The login of a userEntity.
     */
    private String login;

    /**
     * The password of a userEntity.
     */
    private String password;

    /**
     * The tagline of a userEntity.
     */
    private String description;

    /**
     * The userEntity contactsEntity.
     */
    private Contacts contacts;

    /**
     * The userEntity contactsEntity.
     */
    private File photo;

    /**
     * The role of a userEntity.
     */
    private UserRole role;

    /**
     * The permit to send a letters on the userEntity email.
     */
    private int mailing;

    /**
     * Locked the userEntity or not.
     */
    private int locked;

    /**
     * Constructor.
     * @param user
     */
    UserEditor(final User user) {
        super(user);
        this.user = user;
    }

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

    public UserEditor addName(final String name) {
        this.name = name;
        return this;
    }

    public UserEditor addUrl(final String url) {
        this.url = url;
        return this;
    }

    public UserEditor addLogin(final String login) {
        this.login = login;
        return this;
    }

    public UserEditor addPassword(final String password) {
        this.password = password;
        return this;
    }

    public UserEditor addDescription(final String description) {
        this.description = description;
        return this;
    }

    public UserEditor addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    public UserEditor addPhoto(final File photo) {
        this.photo = photo;
        return this;
    }

    public UserEditor addRole(final UserRole role) {
        this.role = role;
        return this;
    }

    public UserEditor addMailing(final boolean mailing) {
        this.mailing = mailing ? 1 : 2;
        return this;
    }

    public UserEditor isMail() {
        return addMailing(true);
    }

    public UserEditor isNotMail() {
        return addMailing(false);
    }

    public UserEditor addLocked(final boolean locked) {
        this.locked = locked ? 1 : 2;
        return this;
    }

    public UserEditor isLock() {
        return this.addLocked(true);
    }

    public UserEditor isNotLock() {
        return this.addLocked(false);
    }

    @Override
    protected boolean isValidated() {
        return !isLocked() && super.isValidated();
    }

    private String getName() {
        return isNotNull(this.name) ? this.name : this.user.getName();
    }

    private String getUrl() {
        return isNotNull(this.url) ? this.url : this.user.getUrl();
    }

    private String getLogin() {
        return isNotNull(this.login) ? this.login : this.user.getLogin();
    }

    private String getPassword() {
        return isNotNull(this.password) ? this.password : this.user.getPassword();
    }

    private String getDescription() {
        return isNotNull(this.description) ? this.description : this.user.getDescription();
    }

    private Contacts getContacts() {
        final ContactsEditor contactsRedactor = this.user.getContacts().getEditor();
        contactsRedactor.copy(this.contacts);
        return contactsRedactor.update();
    }

    private File getPhoto() {
        final FileEditor fileRedactor = this.user.getPhoto().getEditor();
        fileRedactor.copy(this.photo);
        return fileRedactor.update();
    }

    private UserRole getRole() {
        return isNotNull(this.role) ? this.role : this.user.getRole();
    }

    private boolean isMailing() {
        boolean result;
        if (this.mailing > 0) {
            result = (this.mailing == 1);
        } else {
            result = this.user.isMailing();
        }
        return !isLocked() && result;
    }

    private boolean isLocked() {
        boolean result;
        if (this.locked > 0) {
            result = (this.locked == 1);
        } else {
            result = this.user.isValidated();
        }
        return result;
    }
}
