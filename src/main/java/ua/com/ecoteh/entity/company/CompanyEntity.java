package ua.com.ecoteh.entity.company;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.content.ContentEntity;

import javax.persistence.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link CompanyEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Company
 */
@Entity
@Table(name = "companies")
public class CompanyEntity extends ContentEntity {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The tagline of this company entity.
     */
    @Column(name = "tagline", nullable = false)
    private String tagline;

    /**
     * The information of this company entity.
     */
    @Column(name = "information", nullable = false)
    private String information;

    /**
     * The domain of this company entity.
     */
    @Column(name = "domain", nullable = false)
    private String domain;

    /**
     * The sender e-mail of this company entity.
     */
    @Column(name = "sender_email", nullable = false)
    private String senderEmail;

    /**
     * The sender password of this company entity.
     */
    @Column(name = "sender_pass", nullable = false)
    private String senderPass;

    /**
     * The start work time of this company entity.
     */
    @Column(name = "work_time_from", nullable = false)
    private String workTimeFrom;

    /**
     * The finish work time of this company entity.
     */
    @Column(name = "work_time_to", nullable = false)
    private String workTimeTo;

    /**
     * The contacts entity of this company entity.
     *
     * @see ContactsEntity
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
     * The address entity of this company entity.
     *
     * @see AddressEntity
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "id"
    )
    @Fetch(FetchMode.JOIN)
    private AddressEntity address;

    /**
     * The type of this company entity.
     *
     * @see CompanyType
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Constructor.
     */
    protected CompanyEntity() {
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "CompanyEntity{" + super.toString() +
                ", contactsEntity=" + this.contacts +
                ", addressEntity=" + this.address +
                ", tagline='" + this.tagline + '\'' +
                ", information='" + this.information + '\'' +
                ", domain='" + this.domain + '\'' +
                ", senderEmail='" + this.senderEmail + '\'' +
                ", senderPass='" + this.senderPass + '\'' +
                ", workTimeFrom='" + this.workTimeFrom + '\'' +
                ", workTimeTo='" + this.workTimeTo + '\'' +
                ", type=" + this.type +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(final Object object) {
        boolean result = super.equals(object);
        if (result) {
            final CompanyEntity other = (CompanyEntity) object;
            result = (this.type.equals(other.type)) &&
                    this.domain.equalsIgnoreCase(other.domain) &&
                    this.tagline.equals(other.tagline) &&
                    this.information.equals(other.information);
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
        return super.hashCode() +
                this.type.hashCode() +
                this.domain.hashCode() +
                this.tagline.hashCode() +
                this.information.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public CompanyEntity clone() {
        final CompanyEntity clone = (CompanyEntity) super.clone();
        clone.contacts = this.contacts.clone();
        clone.address = this.address.clone();
        return clone;
    }

    /**
     * Returns a domain of the company entity.
     *
     * @return The company entity domain or empty string (newer null).
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the company entity.
     *
     * @param domain a new domain to the company entity.
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * Returns a tagline of the company entity.
     *
     * @return The company entity tagline or empty string (newer null).
     */
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the company entity.
     *
     * @param tagline a new tagline to the company entity.
     */
    public void setTagline(final String tagline) {
        this.tagline = tagline;
    }

    /**
     * Returns a information of the company entity.
     *
     * @return The information domain or empty string (newer null).
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the company entity.
     *
     * @param information a new information to the company entity.
     */
    public void setInformation(final String information) {
        this.information = information;
    }

    /**
     * Returns a sender E-mail of the company entity.
     *
     * @return The company entity sender E-mail or empty string (newer null).
     */
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender E-mail to the company entity.
     *
     * @param senderEmail a new sender E-mail to the company entity.
     */
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
    }

    /**
     * Returns a sender password of the company entity.
     *
     * @return The company entity sender password or empty string (newer null).
     */
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Sets a new sender password to the company entity.
     *
     * @param senderPass a new sender password to the company entity.
     */
    public void setSenderPass(final String senderPass) {
        this.senderPass = senderPass;
    }

    /**
     * Returns a start work time of the company entity.
     *
     * @return The company entity start work time (newer null).
     */
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the company entity.
     *
     * @param workTimeFrom the new start work time to the company entity.
     */
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    /**
     * Returns a finish work time of the company entity.
     *
     * @return The company entity finish work time (newer null).
     */
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the company entity.
     *
     * @param workTimeTo the new finish work time to the company entity.
     */
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
    }

    /**
     * Returns a contacts entity of this company entity.
     *
     * @return The contacts entity of this company entity (newer null).
     * @see ContactsEntity
     */
    public ContactsEntity getContactsEntity() {
        return this.contacts;
    }

    /**
     * Sets a new contacts entity to the company entity.
     *
     * @param contacts the new contacts entity to the company entity.
     * @see ContactsEntity
     */
    public void setContactsEntity(final ContactsEntity contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns a address entity of this company entity.
     *
     * @return The contacts entity to the company entity (newer null).
     * @see AddressEntity
     */
    public AddressEntity getAddressEntity() {
        return this.address;
    }

    /**
     * Sets a new address entity to the company entity.
     *
     * @param address the new address entity to the company entity.
     * @see AddressEntity
     */
    public void setAddressEntity(final AddressEntity address) {
        this.address = address;
    }

    /**
     * Returns a domain of the company entity.
     * Returns a enum object of the {@link CompanyType} class.
     *
     * @return The company entity domain.
     * @see CompanyType
     */
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the company entity.
     *
     * @param type the new type to the company entity.
     * @see CompanyType
     */
    public void setType(final CompanyType type) {
        this.type = type;
    }

    /**
     * Converts this entity and returns a object
     * of the {@link Company} class.
     *
     * @return The object of the {@link Company} class (newer null).
     * @see Company
     */
    @Override
    public Company convert() {
        return new CompanyEntityConverter(this).convert();
    }
}
