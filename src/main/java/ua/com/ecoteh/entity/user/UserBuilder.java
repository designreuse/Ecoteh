package ua.com.ecoteh.entity.user;

import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilder;
import ua.com.ecoteh.util.translator.Translator;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class UserBuilder extends ModelBuilder<User, UserBuilder> {

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
    private boolean mailing;

    /**
     * Locked the userEntity or not.
     */
    private boolean locked;

    UserBuilder() {
    }

    @Override
    public User build() {
        return new User(
                getId(), isValidated(),
                getName(), getUrl(), getLogin(), getPassword(),
                getDescription(), getContacts(), getPhoto(),
                getRole(), isMailing(), isLocked()
        );
    }

    public UserBuilder addName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder addUrl(String url) {
        this.url = url;
        return this;
    }

    public UserBuilder addLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder addPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder addDescription(String description) {
        this.description = description;
        return this;
    }

    public UserBuilder addContacts(Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    public UserBuilder addPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public UserBuilder addRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserBuilder addMailing(final boolean mailing) {
        this.mailing = mailing;
        return this;
    }

    public UserBuilder isMail() {
        return addMailing(true);
    }

    public UserBuilder isNotMail() {
        return addMailing(false);
    }

    public UserBuilder addLocked(final boolean locked) {
        this.locked = locked;
        return this;
    }

    public UserBuilder isLock() {
        return this.addLocked(true);
    }

    public UserBuilder isNotLock() {
        return this.addLocked(false);
    }

    @Override
    protected boolean isValidated() {
        return !this.locked && super.isValidated();
    }

    private String getName() {
        return isNotEmpty(this.name) ? this.name : "";
    }

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

    private String getLogin() {
        return isNotEmpty(this.login) ? this.login : "";
    }

    private String getPassword() {
        return isNotEmpty(this.password) ? this.password : "";
    }

    private String getDescription() {
        return isNotEmpty(this.description) ? this.description : "";
    }

    private Contacts getContacts() {
        return isNotNull(this.contacts) ? this.contacts : Contacts.getBuilder().build();
    }

    private File getPhoto() {
        return isNotNull(this.photo) ? this.photo : File.getBuilder().build();
    }

    private UserRole getRole() {
        return isNotNull(this.role) ? this.role : UserRole.ANOTHER;
    }

    private boolean isMailing() {
        return !this.locked && this.mailing;
    }

    private boolean isLocked() {
        return this.locked;
    }
}
