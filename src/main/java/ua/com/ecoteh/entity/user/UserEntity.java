package ua.com.ecoteh.entity.user;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntity;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link UserEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see User
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
     * The name of this user entity.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The URL of this user entity.
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * The login of this user entity.
     */
    @Column(name = "login", nullable = false)
    private String login;

    /**
     * The password of this user entity.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The tagline of this user entity.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The contacts entity of this user entity.
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
     * The photo entity of this user entity.
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
     * The role of this user entity.
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * The permit to send a letters on the user entity email.
     */
    @Column(name = "mailing")
    private boolean mailing;

    /**
     * Locked the user entity or not.
     */
    @Column(name = "locked")
    private boolean locked;

    /**
     * Constructor.
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
     * Returns a name of the user entity.
     *
     * @return The user entity name (newer null).
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets a new name to the user entity.
     *
     * @param name the new name to the user entity.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets a new login to the user entity.
     *
     * @param login the new login to the user entity.
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Decrypts and returns a login of the user entity.
     *
     * @return The user entity login (newer null).
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Returns a password of the user entity.
     *
     * @return The user entity password (newer null).
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets a new password to the user entity.
     *
     * @param password the new password to the user entity.
     */
    @Transient
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Returns a URL of the user entity.
     *
     * @return The user entity URL (newer null).
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets a new URL to the user entity.
     *
     * @param url the new url to the user entity.
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * Returns a description of the user entity.
     *
     * @return The user entity description (newer null).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets a new description to the user entity.
     *
     * @param description the new description to the user entity.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Returns a photo of the user entity.
     *
     * @return The user entity photo (newer null).
     */
    public FileEntity getPhotoEntity() {
        return this.photo;
    }

    /**
     * Sets a new photo to the user entity.
     *
     * @param photo the new photo to the user entity.
     */
    public void setPhotoEntity(final FileEntity photo) {
        this.photo = photo;
    }

    /**
     * Returns this user entity contactsEntity.
     *
     * @return The user entity contacts entity (newer null).
     */
    public ContactsEntity getContactsEntity() {
        return this.contacts;
    }

    /**
     * Sets a new contacts entity to the user entity.
     *
     * @param contacts the new contacts entity to the user entity.
     */
    public void setContactsEntity(final ContactsEntity contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns a role of the user entity.
     *
     * @return The user entity role (newer null).
     */
    public UserRole getRole() {
        return this.role;
    }

    /**
     * Sets a new role to the user entity.
     *
     * @param role the new role to the user entity.
     */
    public void setRole(final UserRole role) {
        this.role = role;
    }

    /**
     * Returns the value of permit to send a letters on the user entity email.
     *
     * @return The permit to send a letters on the user entity email.
     */
    public boolean isMailing() {
        return this.mailing;
    }

    /**
     * Sets value of permit to send a letters on the user entity email.
     *
     * @param mailing the permit to send a letters on the user entity email.
     */
    public void setMailing(final boolean mailing) {
        this.mailing = mailing;
    }

    /**
     * Returns the value of the locked user entity or not.
     *
     * @return The value of the locked user entity or not.
     */
    public boolean isLocked() {
        return this.locked;
    }

    /**
     * Sets the value of the locked user entity or not.
     *
     * @param locked a value of locked the user entity or not.
     */
    public void setLocked(final boolean locked) {
        this.locked = locked;

    }

    /**
     * Converts this entity and returns a object
     * of the {@link User} class.
     *
     * @return The object of the {@link User} class (newer null).
     * @see UserEntityConverter
     */
    public User convert() {
        return new UserEntityConverter(this).convert();
    }
}
