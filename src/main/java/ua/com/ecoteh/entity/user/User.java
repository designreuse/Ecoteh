package ua.com.ecoteh.entity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.Model;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The name of a userEntity.
     */
    private final String name;

    /**
     * The URL of a userEntity.
     */
    private final String url;

    /**
     * The login of a userEntity.
     */
    private final String login;

    /**
     * The password of a userEntity.
     */
    private final String password;

    /**
     * The tagline of a userEntity.
     */
    private final String description;

    /**
     * The userEntity contactsEntity.
     */
    private final Contacts contacts;

    /**
     * The userEntity contactsEntity.
     */
    private final File photo;

    /**
     * The role of a userEntity.
     */
    private final UserRole role;

    /**
     * The permit to send a letters on the userEntity email.
     */
    private final boolean mailing;

    /**
     * Locked the userEntity or not.
     */
    private final boolean locked;

    /**
     * @param id
     * @param validated
     * @param name
     * @param url
     * @param login
     * @param password
     * @param description
     * @param contacts
     * @param photo
     * @param role
     * @param mailing
     * @param locked
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
        return "UserEntity{" + super.toString() +
                ", name='" + this.name + '\'' +
                ", url='" + this.url + '\'' +
                ", login='" + this.login + '\'' +
                ", password='" + this.password + '\'' +
                ", description='" + this.description + '\'' +
                ", contactsEntity=" + this.contacts +
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
     * Indicates whether the userEntity's account has expired.
     * An expired account cannot be authenticated.
     *
     * @return true if the userEntity's account is valid (ie non-expired),
     * false if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return !isLocked();
    }

    /**
     * Indicates whether the userEntity is locked or unlocked.
     * A locked userEntity cannot be authenticated.
     *
     * @return true if the userEntity is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    /**
     * Indicates whether the userEntity's credentials (password) has expired.
     * Expired credentials prevent authentication.
     *
     * @return true if the userEntity's credentials are valid
     * (ie non-expired), false if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return !isLocked();
    }

    /**
     * Indicates whether the userEntity is enabled or disabled.
     * A disabled userEntity cannot be authenticated.
     *
     * @return true if the userEntity's credentials are valid
     * (ie non-expired), false if no longer valid (ie expired)
     */
    @Override
    public boolean isEnabled() {
        return !isLocked();
    }

    /**
     * Returns the authorities granted to the userEntity.
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
     * Returns the username used to authenticate the userEntity.
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
     * Returns a name of the userEntity.
     *
     * @return The userEntity name (newer null).
     */
    public String getName() {
        return this.name;
    }

    /**
     * Decrypts and returns a login of the userEntity.
     *
     * @return The userEntity login (newer null).
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Decrypts and returns a password of the userEntity.
     *
     * @return The userEntity password (newer null).
     */
    @Transient
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns a URL of the userEntity.
     *
     * @return The userEntity URL (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Returns a description of the userEntity.
     *
     * @return The userEntity description (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a photo of the userEntity.
     *
     * @return The userEntity photo (newer null).
     */
    public File getPhoto() {
        return this.photo;
    }

    /**
     * Returns a userEntity contactsEntity.
     *
     * @return The userEntity contactsEntity (newer null).
     */
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Returns a role of the userEntity.
     *
     * @return The userEntity role (newer null).
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Returns the value of permit to send a letters on the userEntity email.
     *
     * @return The permit to send a letters on the userEntity email.
     */
    public boolean isMailing() {
        return this.mailing;
    }

    /**
     * Returns the value of the locked userEntity or not.
     *
     * @return The value of the locked userEntity or not.
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     *
     * @return
     */
    public UserEntity convert() {
        return new UserConverter(this).convert();
    }

    /**
     *
     * @return
     */
    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }
}
