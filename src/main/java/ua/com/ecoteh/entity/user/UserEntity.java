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
public class UserEntity extends ModelEntity {

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
    private String login;

    /**
     * The password of a userEntity.
     */
    @Column(name = "password", nullable = false)
    private String password;

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
    private ContactsEntity contacts;

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
    protected UserEntity() {
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
            final UserEntity other = (UserEntity) object;
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
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public UserEntity clone() {
        final UserEntity clone = (UserEntity) super.clone();
        clone.contacts = this.contacts.clone();
        clone.photo = this.photo;
        return clone;
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
        this.name = name;
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
        this.login = login;
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
    public String getPassword() {
        return this.password;
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
        this.password = password;
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
        this.url = url;
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
        this.description = description;
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
        this.photo = photo;
    }

    /**
     * Returns a userEntity contactsEntity.
     *
     * @return The userEntity contactsEntity (newer null).
     */
    public ContactsEntity getContactsEntity() {
        return this.contacts;
    }

    /**
     * Sets a new contactsEntity to the userEntity.
     *
     * @param contactsEntity the new contactsEntity to the userEntity.
     */
    public void setContactsEntity(final ContactsEntity contacts) {
        this.contacts = contacts;
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
        this.role = role;
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
     *
     * @return
     */
    public User convert() {
        return new UserEntityConverter(this).convert();
    }
}
