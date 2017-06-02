package ua.com.ecoteh.entity.company;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.content.ContentEntity;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link CompanyEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
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
     * The tagline of a companyEntity.
     */
    @Column(name = "tagline", nullable = false)
    private String tagline;

    /**
     * The information of a companyEntity.
     */
    @Column(name = "information", nullable = false)
    private String information;

    /**
     * The domain of a companyEntity.
     */
    @Column(name = "domain", nullable = false)
    private String domain;

    /**
     * The sender e-mail of a companyEntity.
     */
    @Column(name = "sender_email", nullable = false)
    private String senderEmail;

    /**
     * The sender password of a companyEntity.
     */
    @Column(name = "sender_pass", nullable = false)
    private String senderPass;

    /**
     * The start work time of a companyEntity.
     */
    @Column(name = "work_time_from", nullable = false)
    private String workTimeFrom;

    /**
     * The finish work time of a companyEntity.
     */
    @Column(name = "work_time_to", nullable = false)
    private String workTimeTo;

    /**
     * The companyEntity contactsEntity.
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
     * The companyEntity addressEntity.
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
     * The type of a companyEntity.
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Default constructor.
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
     * Returns a domain of the companyEntity.
     *
     * @return The companyEntity domain or empty string (newer null).
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the companyEntity.
     * If parameter domain is blank, then sets empty string.
     * <pre>
     *     setDomain(null) - domain = ""
     *     setDomain("") - domain = ""
     *     setDomain(" ") - domain = ""
     *     setDomain("bob") - domain = "bob"
     *     setDomain(" bob ") - domain = " bob "
     *     setDomain("http://") - domain = ""
     *     setDomain("https://") - domain = ""
     *     setDomain("http://site.com") - domain = "site.com"
     *     setDomain("https://site.com") - domain = "site.com"
     * </pre>
     *
     * @param domain a new domain to the companyEntity.
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * Returns a tagline of the companyEntity.
     *
     * @return The companyEntity tagline or empty string (newer null).
     */
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the companyEntity.
     * If parameter tagline is blank, then sets empty string.
     * <pre>
     *     setTagline(null) - tagline = ""
     *     setTagline("") - tagline = ""
     *     setTagline(" ") - tagline = ""
     *     setTagline("bob") - tagline = "bob"
     *     setTagline(" bob ") - tagline = " bob "
     * </pre>
     *
     * @param tagline a new tagline to the companyEntity.
     */
    public void setTagline(final String tagline) {
        this.tagline = tagline;
    }

    /**
     * Returns a information of the companyEntity.
     *
     * @return The information domain or empty string (newer null).
     */
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the companyEntity.
     * If parameter information is blank, then sets empty string.
     * <pre>
     *     setInformation(null) - information = ""
     *     setInformation("") - information = ""
     *     setInformation(" ") - information = ""
     *     setInformation("bob") - information = "bob"
     *     setInformation(" bob ") - information = " bob "
     * </pre>
     *
     * @param information a new information to the companyEntity.
     */
    public void setInformation(final String information) {
        this.information = information;
    }

    /**
     * Returns a sender E-mail of the companyEntity.
     *
     * @return The companyEntity sender E-mail or empty string (newer null).
     */
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender E-mail to the companyEntity.
     * If parameter sender E-mail is blank, then sets empty string.
     * <pre>
     *     setSenderEmail(null) - senderEmail = ""
     *     setSenderEmail("") - senderEmail = ""
     *     setSenderEmail(" ") - senderEmail = ""
     *     setSenderEmail("bob") - senderEmail = "bob"
     *     setSenderEmail(" bob ") - senderEmail = " bob "
     * </pre>
     *
     * @param senderEmail a new sender E-mail to the companyEntity.
     */
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
    }

    /**
     * Returns a sender password of the companyEntity.
     *
     * @return The companyEntity sender password or empty string (newer null).
     */
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Sets a new sender password to the companyEntity.
     * If parameter sender password is blank, then sets empty string.
     * <pre>
     *     setSenderPass(null) - senderPass = ""
     *     setSenderPass("") - senderPass = ""
     *     setSenderPass(" ") - senderPass = ""
     *     setSenderPass("bob") - senderPass = "bob"
     *     setSenderPass(" bob ") - senderPass = " bob "
     * </pre>
     *
     * @param senderPass a new sender password to the companyEntity.
     */
    public void setSenderPass(final String senderPass) {
        this.senderPass = senderPass;
    }

    /**
     * Returns a start work time of the companyEntity.
     *
     * @return The companyEntity start work time (newer null).
     */
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the companyEntity.
     * <pre>
     *     setWorkTimeFrom(null) - workTimeFrom = "00:00"
     *     setSenderPass("") - workTimeFrom = "00:00"
     *     setSenderPass(" ") - workTimeFrom = "00:00"
     *     setSenderPass("bob") - workTimeFrom = "00:00"
     *     setSenderPass("12:34") - workTimeFrom = "12:34"
     * </pre>
     *
     * @param workTimeFrom the new start work time to the companyEntity.
     */
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    /**
     * Returns a finish work time of the companyEntity.
     *
     * @return The companyEntity finish work time (newer null).
     */
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the companyEntity.
     * <pre>
     *     setWorkTimeTo(null) - workTimeTo = "00:00"
     *     setWorkTimeTo("") - workTimeTo = "00:00"
     *     setWorkTimeTo(" ") - workTimeTo = "00:00"
     *     setWorkTimeTo("bob") - workTimeTo = "00:00"
     *     setWorkTimeTo("12:34") - workTimeTo = "12:34"
     * </pre>
     *
     * @param workTimeTo the new finish work time to the companyEntity.
     */
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
    }

    /**
     * Returns a companyEntity contactsEntity.
     * Returns a object of the {@link ContactsEntity} class.
     *
     * @return The companyEntity contactsEntity (newer null).
     */
    public ContactsEntity getContactsEntity() {
        return this.contacts;
    }

    /**
     * Sets a new contactsEntity to the companyEntity.
     *
     * @param contacts the new contactsEntity to the companyEntity.
     */
    public void setContactsEntity(final ContactsEntity contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns a companyEntity addressEntity.
     * Returns a object of the {@link AddressEntity} class.
     *
     * @return The companyEntity addressEntity (newer null).
     */
    public AddressEntity getAddressEntity() {
        return this.address;
    }

    /**
     * Sets a new addressEntity to the companyEntity.
     *
     * @param address the new addressEntity to the companyEntity.
     */
    public void setAddressEntity(final AddressEntity address) {
        this.address = address;
    }

    /**
     * Returns a domain of the companyEntity.
     * Returns a enum object of the {@link CompanyType} class.
     *
     * @return The companyEntity domain.
     */
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the companyEntity.
     * Sets default type if incoming type is null.
     * <pre>
     *     setType(null) - type = CompanyType.PARTNER
     *     setType(CompanyType.MAIN) - type = CompanyType.MAIN
     * </pre>
     *
     * @param type the new type to the companyEntity.
     */
    public void setType(final CompanyType type) {
        this.type = isNotNull(type) ? type : CompanyType.PARTNER;
    }

    /**
     *
     * @return
     */
    public Company convert() {
        return new CompanyEntityConverter(this).convert();
    }
}
