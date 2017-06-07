package ua.com.ecoteh.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The class implements a set of methods for working
 * with objects of the {@link User} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see UserEntity
 */
public final class User extends Model implements UserDetails {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The name of this user.
     */
    private final String name;

    /**
     * The URL of this user.
     */
    private final String url;

    /**
     * The login of this user.
     */
    private final String login;

    /**
     * The password of this user.
     */
    private final String password;

    /**
     * The description of this user.
     */
    private final String description;

    /**
     * The user contacts.
     */
    private final Contacts contacts;

    /**
     * The user photo.
     */
    private final File photo;

    /**
     * The role of this user.
     */
    private final UserRole role;

    /**
     * The permit to send a letters on the user E-mail.
     */
    private final boolean mailing;

    /**
     * Locked the user or not.
     */
    private final boolean locked;

    /**
     * Constructor.
     *
     * @param id          the unique identifier for each user.
     * @param validated   the validations of a new user.
     * @param name        the name of a new user.
     * @param url         the URL of a new user.
     * @param login       the login of a new user.
     * @param password    the password of a new user.
     * @param description the description of a new user.
     * @param contacts    the contacts of a new user.
     * @param photo       the photo of a new user.
     * @param role        the role of a new user.
     * @param mailing     the mailing of a new user.
     * @param locked      the locked of a new user.
     */
    User(
            final long id, final boolean validated,
            final String name, final String url, final String login, final String password,
            final String description, final Contacts contacts, final File photo,
            final UserRole role, final boolean mailing, final boolean locked
    ) {
        super(id, validated);
        this.name = name;
        this.url = url;
        this.login = login;
        this.password = password;
        this.description = description;
        this.contacts = contacts;
        this.photo = photo;
        this.role = role;
        this.mailing = mailing;
        this.locked = locked;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "User{" + super.toString() +
                ", name='" + this.name + '\'' +
                ", url='" + this.url + '\'' +
                ", login='" + this.login + '\'' +
                ", password='" + this.password + '\'' +
                ", description='" + this.description + '\'' +
                ", contacts=" + this.contacts +
                ", photo=" + this.photo +
                ", role=" + this.role +
                ", isMailing=" + this.mailing +
                ", isLocked=" + this.locked +
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
        return this.name.hashCode() +
                this.description.hashCode() +
                this.role.hashCode();
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
        return !isLocked();
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * A locked user cannot be authenticated.
     *
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
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
        return !isLocked();
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
        return !isLocked();
    }

    /**
     * Returns the authorities granted to the user.
     * Cannot return null.
     *
     * @return the authorities, sorted by natural key (never null).
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
     * @return The user name (newer null).
     */
    public String getName() {
        return this.name;
    }

    /**
     * Decrypts and returns a login of the user.
     *
     * @return The user login (newer null).
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Decrypts and returns a password of the user.
     *
     * @return The user password (newer null).
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns a URL of the user.
     *
     * @return The user URL (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a description of the user.
     *
     * @return The user description (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a photo of the user.
     *
     * @return The user photo (newer null).
     */
    public File getPhoto() {
        return this.photo;
    }

    /**
     * Returns a user contactsEntity.
     *
     * @return The user contactsEntity (newer null).
     */
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Returns a role of the user.
     *
     * @return The user role (newer null).
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Returns the value of permit to send a letters on the user email.
     *
     * @return The permit to send a letters on the user email.
     */
    public boolean isMailing() {
        return this.mailing;
    }

    /**
     * Returns the value of the locked user or not.
     *
     * @return The value of the locked user or not.
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Converts this object and returns an entity
     * of the {@link UserEntity} class.
     *
     * @return The entity of the {@link UserEntity} class (newer null).
     * @see UserConverter
     */
    @Override
    public UserEntity convert() {
        return new UserConverter(this).convert();
    }

    /**
     * Returns a editor for updating this user.
     *
     * @return the user editor (newer null).
     */
    @Override
    public UserEditor getEditor() {
        return new UserEditor(this);
    }

    /**
     * Returns a builder for creating a new user.
     *
     * @return the user builder (newer null).
     */
    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }
}
