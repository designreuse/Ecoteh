package ua.com.ecoteh.entity.user;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntity;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.translator.Translator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link UserEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Entity
@Table(name = "users")
public class UserEntity extends ModelEntity implements UserDetails {

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
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The URL of a userEntity.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * The login of a userEntity.
     */
    @Column(name = "login", nullable = false)
    private String encryptedLogin;

    /**
     * The password of a userEntity.
     */
    @Column(name = "password", nullable = false)
    private String encryptedPassword;

    /**
     * The tagline of a userEntity.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The userEntity contactsEntity.
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
    private ContactsEntity contactsEntity;

    /**
     * The userEntity contactsEntity.
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
    private FileEntity photo;

    /**
     * The role of a userEntity.
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * The permit to send a letters on the userEntity email.
     */
    @Column(name = "mailing")
    private boolean mailing;

    /**
     * Locked the userEntity or not.
     */
    @Column(name = "locked")
    private boolean locked;

    /**
     * Default constructor.
     */
    public UserEntity() {
        this.name = "";
        this.url = "";
        this.encryptedLogin = "";
        this.encryptedPassword = "";
        this.description = "";
        this.photo = new FileEntity();
        this.contactsEntity = new ContactsEntity();
        this.role = UserRole.ANOTHER;
    }

    /**
     * Constructor.
     *
     * @param name        the name of a new userEntity.
     * @param description the description of a new userEntity.
     */
    public UserEntity(
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
     * @param name        the name of a new userEntity.
     * @param description the description of a new userEntity.
     * @param contactsEntity    the contactsEntity to a new userEntity.
     */
    public UserEntity(
            final String name,
            final String description,
            final ContactsEntity contactsEntity
    ) {
        this(name, description);
        setContactsEntity(contactsEntity);
    }

    /**
     * Constructor.
     *
     * @param name     the name of a new userEntity.
     * @param contactsEntity the contactsEntity to a new userEntity.
     */
    public UserEntity(
            final String name,
            final ContactsEntity contactsEntity
    ) {
        this();
        setName(name);
        setContactsEntity(contactsEntity);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "UserEntity{" + super.toString() +
                ", name='" + getName() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", encryptedLogin='" + getEncryptedLogin() + '\'' +
                ", encryptedPassword='" + getEncryptedPassword() + '\'' +
                ", Login='" + getLogin() + '\'' +
                ", Password='" + getPassword() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", contactsEntity=" + getContactsEntity() +
                ", photoEntity=" + getPhotoEntity() +
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
            final UserEntity other = (UserEntity) object;
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
     * @return A clone of this instance (newer null).
     */
    @Override
    public UserEntity clone() {
        final UserEntity userEntity = (UserEntity) super.clone();
        userEntity.setContactsEntity(getContactsEntity().clone());
        userEntity.setPhotoEntity(getPhotoEntity().clone());
        return userEntity;
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
     * Sets a new name to the userEntity.
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
     * @param name the new name to the userEntity.
     */
    public void setName(final String name) {
        this.name = isNotEmpty(name) ? name : "";
        if (isEmpty(this.url)) {
            translateAndSetUrl(this.name);
        }
    }

    /**
     * Encrypts and sets a new login to the userEntity.
     * <pre>
     *     setLogin(null) - login = ""
     *     setLogin("") - login = ""
     *     setLogin(" ") - login = ""
     *     setLogin("bob") - login = "bob"
     *     setLogin(" bob ") - login = "bob"
     * </pre>
     *
     * @param login the new login to the userEntity.
     */
    public void setLogin(final String login) {
        setEncryptedLogin(
                isNotEmpty(login) ? new Base64Encryptor(login).encrypt() : ""
        );
    }

    /**
     * Decrypts and returns a login of the userEntity.
     *
     * @return The userEntity login (newer null).
     */
    public String getLogin() {
        return isNotEmpty(this.encryptedLogin) ? new Base64Encryptor(this.encryptedLogin).decrypt() : "";
    }

    /**
     * Returns a encrypted login.
     *
     * @return The encrypted login (newer null).
     */
    public String getEncryptedLogin() {
        return this.encryptedLogin;
    }

    /**
     * Sets a new encrypted login to the userEntity.
     * If parameter login is blank then sets empty string.
     * <pre>
     *     setEncryptedLogin(null) - encryptedLogin = ""
     *     setEncryptedLogin("") - encryptedLogin = ""
     *     setEncryptedLogin(" ") - encryptedLogin = ""
     *     setEncryptedLogin("bob") - encryptedLogin = "bob"
     *     setEncryptedLogin(" bob ") - encryptedLogin = "bob"
     * </pre>
     *
     * @param login a new encrypted login to the userEntity.
     */
    public void setEncryptedLogin(final String login) {
        this.encryptedLogin = isNotEmpty(login) ? login : "";
    }

    /**
     * Decrypts and returns a password of the userEntity.
     *
     * @return The userEntity password (newer null).
     */
    @Transient
    @Override
    public String getPassword() {
        return isNotEmpty(this.encryptedPassword) ?
                new Base64Encryptor(this.encryptedPassword).decrypt() : "";
    }

    /**
     * Encrypts and sets a new password to the userEntity.
     * <pre>
     *     setPassword(null) - password = ""
     *     setPassword("") - password = ""
     *     setPassword(" ") - password = ""
     *     setPassword("bob") - password = "bob"
     *     setPassword(" bob ") - password = "bob"
     * </pre>
     *
     * @param password the new password to the userEntity.
     */
    @Transient
    public void setPassword(final String password) {
        setEncryptedPassword(
                isNotEmpty(password) ? new Base64Encryptor(password).encrypt() : ""
        );
    }

    /**
     * Returns a encrypted password.
     *
     * @return The encrypted password (newer null).
     */
    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    /**
     * Sets a new encrypted password to the userEntity.
     * If parameter password is blank then sets empty string.
     * <pre>
     *     setEncryptedPassword(null) - encryptedPassword = ""
     *     setEncryptedPassword("") - encryptedPassword = ""
     *     setEncryptedPassword(" ") - encryptedPassword = ""
     *     setEncryptedPassword("bob") - encryptedPassword = "bob"
     *     setEncryptedPassword(" bob ") - encryptedPassword = "bob"
     * </pre>
     *
     * @param password the new encrypted password to the userEntity.
     */
    public void setEncryptedPassword(final String password) {
        this.encryptedPassword = isNotEmpty(password) ? password : "";
    }

    /**
     * Translates value and sets to url.
     * For translate used {@link Translator} method "fromCyrillicToLatin".
     *
     * @param value the value to translate.
     */
    public void translateAndSetUrl(final String value) {
        setUrl(Translator.fromCyrillicToLatin(value));
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
     * Sets a new url to the userEntity.
     * If parameter url is blank, then sets empty string.
     * <pre>
     *     setUrl(null) - url = ""
     *     setUrl("") - url = ""
     *     setUrl(" ") - url = ""
     *     setUrl("bob") - url = "bob"
     *     setUrl(" bob ") - url = "bob"
     * </pre>
     *
     * @param url the new url to the userEntity.
     */
    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
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
     * Sets a new description to the userEntity.
     * If parameter description is blank, then sets empty string.
     * <pre>
     *     setDescription(null) - description = ""
     *     setDescription("") - description = ""
     *     setDescription(" ") - description = ""
     *     setDescription("bob") - description = "bob"
     *     setDescription(" bob ") - description = "bob"
     * </pre>
     *
     * @param description the new description to the userEntity.
     */
    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    /**
     * Returns a photo of the userEntity.
     *
     * @return The userEntity photo (newer null).
     */
    public FileEntity getPhotoEntity() {
        return this.photo;
    }

    /**
     * Sets a new photo to the userEntity.
     *
     * @param photo the new photo to the userEntity.
     */
    public void setPhotoEntity(final FileEntity photo) {
        if (isNull(this.photo)) {
            this.photo = new FileEntity();
        }
        this.photo.initialize(photo);
    }

    /**
     * Returns a userEntity contactsEntity.
     *
     * @return The userEntity contactsEntity (newer null).
     */
    public ContactsEntity getContactsEntity() {
        return this.contactsEntity;
    }

    /**
     * Sets a new contactsEntity to the userEntity.
     *
     * @param contactsEntity the new contactsEntity to the userEntity.
     */
    public void setContactsEntity(final ContactsEntity contactsEntity) {
        if (isNull(this.contactsEntity)) {
            this.contactsEntity = new ContactsEntity();
        }
        this.contactsEntity.initialize(contactsEntity);
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
     * Sets a new role to the userEntity.
     * Sets default role if incoming role is null.
     * <pre>
     *     setRole(null) - role = UserRole.ANOTHER
     *     setRole(UserRole.ADMIN) - role = UserRole.ADMIN
     * </pre>
     *
     * @param role the new role to the userEntity.
     */
    public void setRole(final UserRole role) {
        this.role = isNotNull(role) ? role : UserRole.ANOTHER;
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
     * Sets value of permit to send a letters on the userEntity email.
     *
     * @param mailing the permit to send a letters on the userEntity email.
     */
    public void setMailing(final boolean mailing) {
        this.mailing = mailing;
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
     * Sets the value of the locked userEntity or not.
     * If incoming value is true then, also,
     * set validated and mailing false.
     *
     * @param locked a value of locked the userEntity or not.
     */
    public void setLocked(final boolean locked) {
        this.locked = locked;
        if (locked) {
            setValidated(false);
            setMailing(false);
        }
    }

    /**
     * Initializes the userEntity.
     * Returns this v with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this userEntity
     *     initialize(new UserEntity()) - does nothing, returns this
     *     userEntity with a new copied fields
     * </pre>
     *
     * @param userEntity the userEntity to copy.
     * @return The this userEntity with a new fields (newer null).
     */
    public UserEntity initialize(final UserEntity userEntity) {
        if (isNotNull(userEntity)) {
            this.setName(userEntity.getName());
            this.setUrl(userEntity.getUrl());
            this.setEncryptedLogin(userEntity.getEncryptedLogin());
            this.setEncryptedPassword(userEntity.getEncryptedPassword());
            this.setDescription(userEntity.getDescription());
            this.setPhotoEntity(userEntity.getPhotoEntity());
            this.setRole(userEntity.getRole());
            this.setMailing(userEntity.isMailing());
            this.setLocked(userEntity.isLocked());
            this.setValidated(userEntity.isValidated());
            this.setContactsEntity(userEntity.getContactsEntity());
        }
        return this;
    }

    /**
     *
     * @return
     */
    public User convert() {
        return new UserEntityConverter(this).convert();
    }
}
