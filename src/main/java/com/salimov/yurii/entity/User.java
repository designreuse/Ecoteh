package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IUser;
import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.util.encryption.Encryptor;
import com.salimov.yurii.util.translator.Translator;
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
 * @see Model
 * @see IUser
 */
@Entity
@Table(name = "users")
public final class User extends Model<Long> implements IUser<Long>, UserDetails {

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
    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    /**
     * The url of a user.
     */
    @Column(
            name = "url",
            nullable = false,
            unique = true
    )
    private String url;

    /**
     * The login of a user.
     */
    @Column(
            name = "login",
            unique = true
    )
    private String encryptedLogin;

    /**
     * The password of a user.
     */
    @Column(
            name = "password",
            unique = true
    )
    private String encryptedPassword;

    /**
     * The e-mail of a user.
     */
    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    /**
     * The phone of a user.
     */
    @Column(
            name = "phone",
            nullable = false
    )
    private String phone;

    /**
     * The vkontakte url of a user.
     */
    @Column(name = "vkontakte")
    private String vkontakte;

    /**
     * The facebook url of a user.
     */
    @Column(name = "facebook")
    private String facebook;

    /**
     * The twitter url of a user.
     */
    @Column(name = "twitter")
    private String twitter;

    /**
     * The skype username of a user.
     */
    @Column(name = "skype")
    private String skype;

    /**
     * The tagline of a user.
     */
    @Column(name = "description")
    private String description;

    /**
     * The photo URL of a user.
     */
    @Column(name = "photo")
    private String photoUrl;

    /**
     * The role of a user.
     *
     * @see UserRole
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
     *
     * @see UserRole
     */
    public User() {
        setRole(UserRole.ANOTHER);
    }

    /**
     * Constructor.
     *
     * @param name  a name of the new user.
     * @param email a email of the new user.
     * @param phone a phone of the new user.
     */
    public User(
            final String name,
            final String email,
            final String phone
    ) {
        this();
        setName(name);
        setEmail(email);
        setPhone(phone);
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
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", vkontakte='" + getVkontakte() + '\'' +
                ", facebook='" + getFacebook() + '\'' +
                ", twitter='" + getTwitter() + '\'' +
                ", skype='" + getSkype() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", photoUrl='" + getPhotoUrl() + '\'' +
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
            result = (
                    isNotBlank(this.name) ?
                            this.name.equalsIgnoreCase(other.name) :
                            isBlank(other.name)
            ) && (
                    isNotBlank(this.phone) ?
                            this.phone.equals(other.phone) :
                            isBlank(other.phone)
            ) && (
                    isNotBlank(this.email) ?
                            this.email.equalsIgnoreCase(other.email) :
                            isBlank(other.email)
            );
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
        return (isNotBlank(this.name) ? this.name.hashCode() : 0)
                + (isNotBlank(this.phone) ? this.phone.hashCode() : 0)
                + (isNotBlank(this.email) ? this.email.hashCode() : 0);
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
        roles.add(
                new SimpleGrantedAuthority(
                        "ROLE_" + this.role.name()
                )
        );
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
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param email       a new email of the user.
     * @param phone       a new phone of the user.
     * @param description a new description of the user.
     */
    @Override
    public void initialize(
            final String name,
            final String email,
            final String phone,
            final String description
    ) {
        setName(name);
        setEmail(email);
        setPhone(phone);
        setDescription(description);
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param login       a new login of the user.
     * @param password    a new password of the user.
     * @param email       a new e-mail of the user.
     * @param phone       a new phone of the user.
     * @param vkontakte   a new vkontakte url of the user.
     * @param facebook    a new facebook url of the user.
     * @param twitter     a new twitter url of the user.
     * @param skype       a new skype username of the user.
     * @param description a new description of the user.
     */
    @Override
    public void initialize(
            final String name,
            final String login,
            final String password,
            final String email,
            final String phone,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String description
    ) {
        initialize(name, email, phone, description);
        setLogin(login);
        setPassword(password);
        setVkontakte(vkontakte);
        setFacebook(facebook);
        setTwitter(twitter);
        setSkype(skype);
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param login       a new login of the user.
     * @param password    a new password of the user.
     * @param email       a new e-mail of the user.
     * @param phone       a new phone of the user.
     * @param description a new description of the user.
     * @param photoUrl    a new photo URL of the user.
     * @param role        a new role of the user.
     * @see File
     * @see UserRole
     */
    @Override
    public void initialize(
            final String name,
            final String login,
            final String password,
            final String email,
            final String phone,
            final String description,
            final String photoUrl,
            final UserRole role
    ) {
        initialize(name, email, phone, description);
        setLogin(login);
        setPassword(password);
        setPhotoUrl(photoUrl);
        setRole(role);
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
     * If parameter name is blank, then sets {@code null}.
     * Also name translates and sets to url.
     *
     * @param name a new name to the user.
     */
    @Override
    public void setName(final String name) {
        this.name = isNotBlank(name) ? name : null;
        translateAndSetUrl(this.name);
    }

    /**
     * Sets a new login to the user.
     *
     * @param login a new login to the user.
     */
    @Override
    public void setLogin(final String login) {
        final String encryptedLogin = isNotBlank(login) ?
                new Encryptor(login).encrypt() : null;
        setEncryptedLogin(encryptedLogin);
    }

    /**
     * Returns a login of the user.
     *
     * @return The user login.
     */
    @Override
    public String getLogin() {
        return isNotBlank(this.encryptedLogin) ?
                new Encryptor(this.encryptedLogin).decrypt() : null;
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
     * If parameter login is blank
     * then sets {@code null}.
     *
     * @param login a new encrypted login to the user.
     */
    @Override
    public void setEncryptedLogin(final String login) {
        this.encryptedLogin = isNotBlank(login) ? login : null;
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
                new Encryptor(this.encryptedPassword).decrypt() : null;
    }

    /**
     * Sets a new password to the user.
     *
     * @param password a new password to the user.
     */
    @Transient
    @Override
    public void setPassword(final String password) {
        final String encryptedPassword = isNotBlank(password) ?
                new Encryptor(password).encrypt() : null;
        setEncryptedPassword(encryptedPassword);
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
     * If parameter password is blank
     * then sets {@code null}.
     *
     * @param password a new encrypted password to the user.
     */
    @Override
    public void setEncryptedPassword(final String password) {
        this.encryptedPassword = isNotBlank(password) ? password : password;
    }

    /**
     * Translates value and sets to url.
     * For translate used {@link Translator} method "fromCyrillicToLatin".
     *
     * @param value a value to translate.
     * @see Translator
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
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the user.
     */
    @Override
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : null;
    }

    /**
     * Returns a e-mail of the user.
     *
     * @return The user e-mail.
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new e-mail to the user.
     * If parameter e-mail is blank, then sets {@code null}.
     *
     * @param email a new e-mail to the user.
     */
    @Override
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : null;
    }

    /**
     * Returns a phone of the user.
     *
     * @return The user phone.
     */
    @Override
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets a new phone to the user.
     * If parameter phone is blank, then sets {@code null}.
     *
     * @param phone a new phone to the user.
     */
    @Override
    public void setPhone(final String phone) {
        this.phone = isNotBlank(phone) ? phone : null;
    }

    /**
     * Returns a vkontakte url of the user.
     *
     * @return The user vkontakte url.
     */
    @Override
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new vkontakte url to the user.
     * If parameter vkontakte url is blank, then sets {@code null}.
     *
     * @param vkontakte a new vkontakte url to the user.
     */
    @Override
    public void setVkontakte(final String vkontakte) {
        this.vkontakte = isNotBlank(vkontakte) ? vkontakte : null;
    }

    /**
     * Returns a facebook url of the user.
     *
     * @return The user facebook url.
     */
    @Override
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new facebook url to the user.
     * If parameter facebook url is blank, then sets {@code null}.
     *
     * @param facebook a new facebook url to the user.
     */
    @Override
    public void setFacebook(final String facebook) {
        this.facebook = isNotBlank(facebook) ? facebook : null;
    }

    /**
     * Returns a twitter url of the user.
     *
     * @return The user twitter url.
     */
    @Override
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new twitter url to the user.
     * If parameter twitter url is blank, then sets {@code null}.
     *
     * @param twitter a new twitter url to the user.
     */
    @Override
    public void setTwitter(final String twitter) {
        this.twitter = isNotBlank(twitter) ? twitter : null;
    }

    /**
     * Returns a skype username of the user.
     *
     * @return The user skype username.
     */
    @Override
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new skype username to the user.
     * If parameter skype username is blank, then sets {@code null}.
     *
     * @param skype a new skype username to the user.
     */
    @Override
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : null;
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
     * If parameter description is blank, then sets {@code null}.
     *
     * @param description a new description to the user.
     */
    @Override
    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : null;
    }

    /**
     * Returns a photo URL of the user.
     *
     * @return The user photo URL.
     */
    @Override
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * Sets a new photo to the user.
     * If photo URL is blank, then sets {@code null}.
     *
     * @param photoUrl a new photo URL to the user.
     */
    @Override
    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = isNotBlank(photoUrl) ? photoUrl : null;
    }

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     * @see UserRole
     */
    @Override
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Sets a new role to the user.
     * If parameter role is blank, then sets {@code null}.
     *
     * @param role a new role to the user.
     * @see UserRole
     */
    @Override
    public void setRole(final UserRole role) {
        this.role = role;
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
}
