package ua.com.ecoteh.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.ecoteh.enums.UserRole;
import ua.com.ecoteh.util.encryption.Encryptor;
import ua.com.ecoteh.util.translator.Translator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User extends Model implements IUser, UserDetails {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of a user.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The URL of a user.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * The login of a user.
     */
    @Column(name = "login", nullable = false)
    private String encryptedLogin;

    /**
     * The password of a user.
     */
    @Column(name = "password", nullable = false)
    private String encryptedPassword;

    /**
     * The tagline of a user.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The user contacts.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "contacts_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private Contacts contacts;

    /**
     * The user contacts.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private File photo;

    /**
     * The role of a user.
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * The permit to send a letters on the user email.
     */
    @Column(name = "mailing")
    private boolean isMailing;

    /**
     * Locked the user or not.
     */
    @Column(name = "locked")
    private boolean isLocked;

    /**
     * Default constructor.
     */
    public User() {
        this.name = "";
        this.url = "";
        this.encryptedLogin = "";
        this.encryptedPassword = "";
        this.description = "";
        this.photo = new File();
        this.contacts = new Contacts();
        this.role = UserRole.ANOTHER;
    }

    /**
     * Constructor.
     *
     * @param name        the name of a new user.
     * @param description the description of a new user.
     */
    public User(
            final String name,
            final String description
    ) {
        this();
        setName(name);
        setDescription(description);
    }

    /**
     * Constructor.
     *
     * @param name        the name of a new user.
     * @param description the description of a new user.
     * @param contacts    the contacts to a new user.
     */
    public User(
            final String name,
            final String description,
            final Contacts contacts
    ) {
        this(name, description);
        setContacts(contacts);
    }

    /**
     * Constructor.
     *
     * @param name     the name of a new user.
     * @param contacts the contacts to a new user.
     */
    public User(
            final String name,
            final Contacts contacts
    ) {
        this();
        setName(name);
        setContacts(contacts);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "User{" + super.toString() +
                ", name='" + getName() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", encryptedLogin='" + getEncryptedLogin() + '\'' +
                ", encryptedPassword='" + getEncryptedPassword() + '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Password='" + getPassword() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", contacts=" + getContacts() +
                ", photo=" + getPhoto() +
                ", role=" + getRole() +
                ", isMailing=" + isMailing() +
                ", isLocked=" + isLocked() +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj
     * argument, false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = false;
        if (super.equals(object)) {
            final User other = (User) object;
            result = this.getName().equalsIgnoreCase(other.getName()) &&
                    this.getDescription().equalsIgnoreCase(other.getDescription()) &&
                    this.getRole().equals(other.getRole());
        }
        return result;
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit
     * of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return getName().hashCode() +
                getDescription().hashCode() +
                getRole().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public User clone() {
        return (User) super.clone();
    }

    /**
     * Indicates whether the user's account has expired.
     * An expired account cannot be authenticated.
     *
     * @return true if the user's account is valid (ie non-expired),
     * false if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * A locked user cannot be authenticated.
     *
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * Expired credentials prevent authentication.
     *
     * @return true if the user's credentials are valid
     * (ie non-expired), false if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * A disabled user cannot be authenticated.
     *
     * @return true if the user's credentials are valid
     * (ie non-expired), false if no longer valid (ie expired)
     */
    @Override
    public boolean isEnabled() {
        return !this.isLocked;
    }

    /**
     * Returns the authorities granted to the user.
     * Cannot return null.
     *
     * @return the authorities, sorted by natural key (never null)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> roles = new ArrayList<>(1);
        roles.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
        return roles;
    }

    /**
     * Returns the username used to authenticate the user.
     * Cannot return null. Return the empty string
     * if username is null.
     *
     * @return the username or the empty string (never null).
     */
    @Override
    public String getUsername() {
        return getLogin();
    }

    /**
     * Returns a name of the user.
     *
     * @return The user name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name to the user.
     * If parameter name is blank, then sets empty string.
     * Also, name translates and sets to URL if this URL is empty.
     * <pre>
     *     setName(null) - name = ""
     *     setName("") - name = ""
     *     setName(" ") - name = ""
     *     setName("bob") - name = "bob"
     *     setName(" bob ") - name = "bob"
     * </pre>
     *
     * @param name the new name to the user.
     */
    @Override
    public void setName(final String name) {
        this.name = isNotEmpty(name) ? name : "";
        if (isEmpty(this.url)) {
            translateAndSetUrl(this.name);
        }
    }

    /**
     * Encrypts and sets a new login to the user.
     * <pre>
     *     setLogin(null) - login = ""
     *     setLogin("") - login = ""
     *     setLogin(" ") - login = ""
     *     setLogin("bob") - login = "bob"
     *     setLogin(" bob ") - login = "bob"
     * </pre>
     *
     * @param login the new login to the user.
     */
    @Override
    public void setLogin(final String login) {
        setEncryptedLogin(
                isNotEmpty(login) ? new Encryptor(login).encrypt() : ""
        );
    }

    /**
     * Decrypts and returns a login of the user.
     *
     * @return The user login.
     */
    @Override
    public String getLogin() {
        return isNotEmpty(this.encryptedLogin) ? new Encryptor(this.encryptedLogin).decrypt() : "";
    }

    /**
     * Returns a encrypted login.
     *
     * @return The encrypted login.
     */
    @Override
    public String getEncryptedLogin() {
        return this.encryptedLogin;
    }

    /**
     * Sets a new encrypted login to the user.
     * If parameter login is blank then sets empty string.
     * <pre>
     *     setEncryptedLogin(null) - encryptedLogin = ""
     *     setEncryptedLogin("") - encryptedLogin = ""
     *     setEncryptedLogin(" ") - encryptedLogin = ""
     *     setEncryptedLogin("bob") - encryptedLogin = "bob"
     *     setEncryptedLogin(" bob ") - encryptedLogin = "bob"
     * </pre>
     *
     * @param login a new encrypted login to the user.
     */
    @Override
    public void setEncryptedLogin(final String login) {
        this.encryptedLogin = isNotEmpty(login) ? login : "";
    }

    /**
     * Decrypts and returns a password of the user.
     *
     * @return The user password.
     */
    @Transient
    @Override
    public String getPassword() {
        return isNotEmpty(this.encryptedPassword) ?
                new Encryptor(this.encryptedPassword).decrypt() : "";
    }

    /**
     * Encrypts and sets a new password to the user.
     * <pre>
     *     setPassword(null) - password = ""
     *     setPassword("") - password = ""
     *     setPassword(" ") - password = ""
     *     setPassword("bob") - password = "bob"
     *     setPassword(" bob ") - password = "bob"
     * </pre>
     *
     * @param password the new password to the user.
     */
    @Transient
    @Override
    public void setPassword(final String password) {
        setEncryptedPassword(
                isNotEmpty(password) ? new Encryptor(password).encrypt() : ""
        );
    }

    /**
     * Returns a encrypted password.
     *
     * @return The encrypted password.
     */
    @Override
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Sets a new encrypted password to the user.
     * If parameter password is blank then sets empty string.
     * <pre>
     *     setEncryptedPassword(null) - encryptedPassword = ""
     *     setEncryptedPassword("") - encryptedPassword = ""
     *     setEncryptedPassword(" ") - encryptedPassword = ""
     *     setEncryptedPassword("bob") - encryptedPassword = "bob"
     *     setEncryptedPassword(" bob ") - encryptedPassword = "bob"
     * </pre>
     *
     * @param password the new encrypted password to the user.
     */
    @Override
    public void setEncryptedPassword(final String password) {
        this.encryptedPassword = isNotEmpty(password) ? password : "";
    }

    /**
     * Translates value and sets to url.
     * For translate used {@link Translator} method "fromCyrillicToLatin".
     *
     * @param value the value to translate.
     */
    @Override
    public void translateAndSetUrl(final String value) {
        setUrl(Translator.fromCyrillicToLatin(value));
    }

    /**
     * Returns a url of the user.
     *
     * @return The user url.
     */
    @Override
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the user.
     * If parameter url is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = "bob"
     * </pre>
     *
     * @param url the new url to the user.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    /**
     * Returns a description of the user.
     *
     * @return The user description.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the user.
     * If parameter description is blank, then sets empty string.
     * <pre>
     *     setDescription(null) - description = ""
     *     setDescription("") - description = ""
     *     setDescription(" ") - description = ""
     *     setDescription("bob") - description = "bob"
     *     setDescription(" bob ") - description = "bob"
     * </pre>
     *
     * @param description the new description to the user.
     */
    @Override
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    /**
     * Returns a photo URL of the user.
     *
     * @return The user photo URL.
     */
    @Override
    public File getPhoto() {
        return this.photo;
    }

    /**
     * Sets a new photo to the user.
     *
     * @param photo the new photo URL to the user.
     */
    @Override
    public void setPhoto(final File photo) {
        if (isNull(this.photo)) {
            this.photo = new File();
        }
        this.photo.initialize(photo);
    }

    /**
     * Returns a user contacts.
     *
     * @return The user contacts.
     */
    @Override
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Sets a new contacts to the user.
     *
     * @param contacts the new contacts to the user.
     */
    @Override
    public void setContacts(final Contacts contacts) {
        if (isNull(this.contacts)) {
            this.contacts = new Contacts();
        }
        this.contacts.initialize(contacts);
    }

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     */
    @Override
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Sets a new role to the user.
     * Sets default role if incoming role is null.
     * <pre>
     *     setRole(null) - role = UserRole.ANOTHER
     *     setRole(UserRole.ADMIN) - role = UserRole.ADMIN
     * </pre>
     *
     * @param role the new role to the user.
     */
    @Override
    public void setRole(final UserRole role) {
        this.role = isNotNull(role) ? role : UserRole.ANOTHER;
    }

    /**
     * Returns the value of permit to send a letters on the user email.
     *
     * @return The permit to send a letters on the user email.
     */
    @Override
    public boolean isMailing() {
        return this.isMailing;
    }

    /**
     * Sets value of permit to send a letters on the user email.
     *
     * @param isMailing a permit to send a letters on the user email.
     */
    @Override
    public void setMailing(final boolean isMailing) {
        this.isMailing = isMailing;
    }

    /**
     * Returns the value of the locked user or not.
     *
     * @return The value of the locked user or not.
     */
    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Sets the value of the locked user or not.
     * If incoming value is true then, also,
     * set validated and mailing false.
     *
     * @param locked a value of locked the user or not.
     */
    @Override
    public void setLocked(final boolean locked) {
        this.isLocked = locked;
        if (locked) {
            setValidated(false);
            this.isMailing = false;
        }
    }

    /**
     * Initializes the user.
     * Returns this v with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this user
     *     initialize(new User()) - does nothing, returns this
     *     user with a new copied fields
     * </pre>
     *
     * @param user the user to copy.
     * @return The this user with a new fields.
     */
    @Override
    public User initialize(final User user) {
        if (isNotNull(user)) {
            this.setName(user.getName());
            this.setUrl(user.getUrl());
            this.setEncryptedLogin(user.getEncryptedLogin());
            this.setEncryptedPassword(user.getEncryptedPassword());
            this.setDescription(user.getDescription());
            this.setPhoto(user.getPhoto());
            this.setRole(user.getRole());
            this.setMailing(user.isMailing());
            this.setLocked(user.isLocked());
            this.setValidated(user.isValidated());
            this.setContacts(user.getContacts());
        }
        return this;
    }
}
