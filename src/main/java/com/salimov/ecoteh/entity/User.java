package com.salimov.ecoteh.entity;

import com.salimov.ecoteh.entity.interfaces.IUser;
import com.salimov.ecoteh.enums.UserRole;
import com.salimov.ecoteh.util.encryption.Encryptor;
import com.salimov.ecoteh.util.translator.Translator;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "users")
public final class User extends Model implements IUser, UserDetails {

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
     * The url of a user.
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
     * @param name        a name of the new user.
     * @param description a description of the new user.
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
     * @param name        a name of the new user.
     * @param description a description of the new user.
     * @param contacts    a contacts to the new user.
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
     * @param name     a name of the new user.
     * @param contacts a contacts to the new user.
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
                ", " + getContacts() +
                ", " + getPhoto() +
                ", role=" + getRole() +
                ", isMailing=" + isMailing() +
                ", isLocked=" + isLocked() +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = false;
        if (super.equals(object)) {
            final User other = (User) object;
            result = this.name.equalsIgnoreCase(other.name) &&
                    this.description.equalsIgnoreCase(other.description) &&
                    this.role.equals(other.role);
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
        return this.name.hashCode() + this.description.hashCode() + this.role.hashCode();
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
     * @return {@code true} if the user's account is valid (ie non-expired),
     * {@code false} if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * A locked user cannot be authenticated.
     *
     * @return {@code true} if the user is not locked, {@code false} otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * Expired credentials prevent authentication.
     *
     * @return {@code true} if the user's credentials are valid
     * (ie non-expired), {@code false} if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isLocked;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * A disabled user cannot be authenticated.
     *
     * @return {@code true} if the user's credentials are valid
     * (ie non-expired), {@code false} if no longer valid (ie expired)
     */
    @Override
    public boolean isEnabled() {
        return !this.isLocked;
    }

    /**
     * Returns the authorities granted to the user.
     * Cannot return {@code null}.
     *
     * @return the authorities, sorted by natural key (never {@code null})
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> roles = new ArrayList<>(1);
        roles.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));
        return roles;
    }

    /**
     * Returns the username used to authenticate the user.
     * Cannot return {@code null}. Return the empty string
     * if username is {@code null}.
     *
     * @return the username or the empty string (never {@code null}).
     */
    @Override
    public String getUsername() {
        final String username = getLogin();
        return isNotBlank(username) ? username : "";
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
     * Also name translates and sets to url.
     *
     * @param name a new name to the user.
     */
    @Override
    public void setName(final String name) {
        this.name = isNotBlank(name) ? name : "";
        if (isBlank(this.url)) {
            translateAndSetUrl(this.name);
        }
    }

    /**
     * Sets a new login to the user.
     *
     * @param login a new login to the user.
     */
    @Override
    public void setLogin(final String login) {
        setEncryptedLogin(
                isNotBlank(login) ? new Encryptor(login).encrypt() : ""
        );
    }

    /**
     * Returns a login of the user.
     *
     * @return The user login.
     */
    @Override
    public String getLogin() {
        return isNotBlank(this.encryptedLogin) ?
                new Encryptor(this.encryptedLogin).decrypt() : "";
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
     *
     * @param login a new encrypted login to the user.
     */
    @Override
    public void setEncryptedLogin(final String login) {
        this.encryptedLogin = isNotBlank(login) ? login : "";
    }

    /**
     * Returns a password of the user.
     *
     * @return The user password.
     */
    @Transient
    @Override
    public String getPassword() {
        return isNotBlank(this.encryptedPassword) ?
                new Encryptor(this.encryptedPassword).decrypt() : "";
    }

    /**
     * Sets a new password to the user.
     *
     * @param password a new password to the user.
     */
    @Transient
    @Override
    public void setPassword(final String password) {
        setEncryptedPassword(
                isNotBlank(password) ? new Encryptor(password).encrypt() : ""
        );
    }

    /**
     * Returns a encrypted password.
     *
     * @return The encrypted password..
     */
    @Override
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Sets a new encrypted password to the user.
     * If parameter password is blank then sets empty string.
     *
     * @param password a new encrypted password to the user.
     */
    @Override
    public void setEncryptedPassword(final String password) {
        this.encryptedPassword = isNotBlank(password) ? password : "";
    }

    /**
     * Translates value and sets to url.
     * For translate used {@link Translator} method "fromCyrillicToLatin".
     *
     * @param value a value to translate.
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
     *
     * @param url a new url to the user.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : "";
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
     *
     * @param description a new description to the user.
     */
    @Override
    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : "";
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
     * @param photo a new photo URL to the user.
     */
    @Override
    public void setPhoto(final File photo) {
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
     * @param contacts a new contacts to the user.
     */
    @Override
    public void setContacts(final Contacts contacts) {
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
     * If parameter role is blank, then sets empty string.
     *
     * @param role a new role to the user.
     */
    @Override
    public void setRole(final UserRole role) {
        this.role = role != null ? role : UserRole.ANOTHER;
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
     *
     * @param user a user to copy.
     * @return The this user with new fields.
     */
    @Override
    public User initialize(final User user) {
        if (user != null) {
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
