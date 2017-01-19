package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.IUser;
import com.salimov.yurii.enums.UserRole;
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
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see Model
 */
@Entity
@Table(name = "users")
public final class User
        extends Model<Long>
        implements IUser<Long>, UserDetails {

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
    private String login;

    /**
     * The password of a user.
     */
    @Column(
            name = "password",
            unique = true
    )
    private String password;

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
        final StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name)
                .append(" \nE-mail: ").append(this.email)
                .append(" \nPhone: ").append(this.phone);
        if (this.role != null) {
            sb.append(" \nRole: ").append(this.role.name());
        }
        return sb.append(" ").toString();
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
        return (
                isNotBlank(this.name) ? this.name.hashCode() : 0
        ) + (
                isNotBlank(this.phone) ? this.phone.hashCode() : 0
        ) + (
                isNotBlank(this.email) ? this.email.hashCode() : 0
        );
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
        return getLogin() != null ? getLogin() : "";
    }

    /**
     * Initializes some parameter of the article.
     *
     * @param name        a new name of the user.
     * @param email       a new email of the user.
     * @param phone       a new phone of the user.
     * @param description a new description of the user.
     */
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
    public void setName(final String name) {
        this.name = isNotBlank(name) ? name : null;
        translateAndSetUrl(name);
    }

    /**
     * Sets a new login to the user.
     * If parameter login is blank, then sets {@code null}.
     *
     * @param login a new login to the user.
     */
    public void setLogin(final String login) {
        this.login = isNotBlank(login) ? login : null;
    }

    /**
     * Returns a login of the user.
     *
     * @return The user login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Translates value and sets to url.
     * For translate used {@link Translator} method "fromCyrillicToLatin".
     *
     * @param value a value to translate.
     * @see Translator
     */
    public void translateAndSetUrl(final String value) {
        setUrl(
                Translator.fromCyrillicToLatin(value)
        );
    }

    /**
     * Returns a url of the user.
     *
     * @return The user url.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new url to the user.
     * If parameter url is blank, then sets {@code null}.
     *
     * @param url a new url to the user.
     */
    public void setUrl(final String url) {
        this.url = isNotBlank(url) ? url : null;
    }

    /**
     * Returns a password of the user.
     *
     * @return The user password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets a new password to the user.
     * If parameter password is blank, then sets {@code null}.
     *
     * @param password a new password to the user.
     */
    public void setPassword(final String password) {
        this.password = isNotBlank(password) ? password : null;
    }

    /**
     * Returns a e-mail of the user.
     *
     * @return The user e-mail.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets a new e-mail to the user.
     * If parameter e-mail is blank, then sets {@code null}.
     *
     * @param email a new e-mail to the user.
     */
    public void setEmail(final String email) {
        this.email = isNotBlank(email) ? email : null;
    }

    /**
     * Returns a phone of the user.
     *
     * @return The user phone.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Sets a new phone to the user.
     * If parameter phone is blank, then sets {@code null}.
     *
     * @param phone a new phone to the user.
     */
    public void setPhone(final String phone) {
        this.phone = isNotBlank(phone) ? phone : null;
    }

    /**
     * Returns a vkontakte url of the user.
     *
     * @return The user vkontakte url.
     */
    public String getVkontakte() {
        return this.vkontakte;
    }

    /**
     * Sets a new vkontakte url to the user.
     * If parameter vkontakte url is blank, then sets {@code null}.
     *
     * @param vkontakte a new vkontakte url to the user.
     */
    public void setVkontakte(final String vkontakte) {
        String temp = null;
        if (isNotBlank(vkontakte)) {
            temp = vkontakte.toLowerCase()
                    .replace("http://", "")
                    .replace("https://", "")
                    .replace("m.vk.com", "")
                    .replace("vk.com", "");
        }
        this.vkontakte = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a facebook url of the user.
     *
     * @return The user facebook url.
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets a new facebook url to the user.
     * If parameter facebook url is blank, then sets {@code null}.
     *
     * @param facebook a new facebook url to the user.
     */
    public void setFacebook(final String facebook) {
        String temp = null;
        if (isNotBlank(facebook)) {
            temp = facebook.toLowerCase()
                    .replace("http://", "")
                    .replace("https://", "")
                    .replace("m.facebook.com", "")
                    .replace("www.facebook.com", "");
        }
        this.facebook = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a twitter url of the user.
     *
     * @return The user twitter url.
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets a new twitter url to the user.
     * If parameter twitter url is blank, then sets {@code null}.
     *
     * @param twitter a new twitter url to the user.
     */
    public void setTwitter(final String twitter) {
        String temp = null;
        if (isNotBlank(twitter)) {
            temp = twitter.toLowerCase()
                    .replace("http://", "")
                    .replace("https://", "")
                    .replace("mobile.twitter.com", "")
                    .replace("twitter.com", "");
        }
        this.twitter = isNotBlank(temp) ? temp : null;
    }

    /**
     * Returns a skype username of the user.
     *
     * @return The user skype username.
     */
    public String getSkype() {
        return this.skype;
    }

    /**
     * Sets a new skype username to the user.
     * If parameter skype username is blank, then sets {@code null}.
     *
     * @param skype a new skype username to the user.
     */
    public void setSkype(final String skype) {
        this.skype = isNotBlank(skype) ? skype : null;
    }

    /**
     * Returns a description of the user.
     *
     * @return The user description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the user.
     * If parameter description is blank, then sets {@code null}.
     *
     * @param description a new description to the user.
     */
    public void setDescription(final String description) {
        this.description = isNotBlank(description) ? description : null;
    }

    /**
     * Returns a photo URL of the user.
     *
     * @return The user photo URL.
     */
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    /**
     * Sets a new photo to the user.
     * If photo URL is blank, then sets {@code null}.
     *
     * @param photoUrl a new photo URL to the user.
     */
    public void setPhotoUrl(final String photoUrl) {
        this.photoUrl = isNotBlank(photoUrl) ? photoUrl : null;
    }

    /**
     * Returns a role of the user.
     *
     * @return The user role.
     * @see UserRole
     */
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
    public void setRole(final UserRole role) {
        this.role = role;
    }

    /**
     * Returns the value of permit to send a letters on the user email.
     *
     * @return The permit to send a letters on the user email.
     */
    public boolean isMailing() {
        return this.isMailing;
    }

    /**
     * Sets value of permit to send a letters on the user email.
     *
     * @param isMailing a permit to send a letters on the user email.
     */
    public void setMailing(final boolean isMailing) {
        this.isMailing = isMailing;
    }

    /**
     * Returns the value of the locked user or not.
     *
     * @return The value of the locked user or not.
     */
    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Sets the value of the locked user or not.
     *
     * @param locked a value of locked the user or not.
     */
    public void setLocked(final boolean locked) {
        this.isLocked = locked;
        if (locked) {
            setValidated(false);
            this.isMailing = false;
        }
    }

    /**
     * Statically validates the user.
     * User is valid if it is a valid model object
     * and it has name, phone and email.
     *
     * @param user an user to validate.
     * @return {@code true} if the user is valid, {@code false} otherwise.
     */
    public static boolean isValidated(final User user) {
        boolean result = false;
        if (Model.isValidated(user)) {
            result = (
                    isNotBlank(user.getName())
            ) && (
                    isNotBlank(user.getPhone())
            ) && (
                    isNotBlank(user.getEmail())
            );
        }
        return result;
    }
}
