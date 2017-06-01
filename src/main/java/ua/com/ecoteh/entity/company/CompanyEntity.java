package ua.com.ecoteh.entity.company;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.content.ContentEntity;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

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
    private ContactsEntity contactsEntity;

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
    private AddressEntity addressEntity;

    /**
     * The type of a companyEntity.
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Default constructor.
     */
    public CompanyEntity() {
        this.tagline = "";
        this.information = "";
        this.domain = "";
        this.senderEmail = "";
        this.senderPass = "";
        this.workTimeFrom = "";
        this.workTimeTo = "";
        this.type = CompanyType.PARTNER;
        this.contactsEntity = new ContactsEntity();
        this.addressEntity = new AddressEntity();
    }

    /**
     * Constrictor.
     *
     * @param title       the title of a new companyEntity.
     * @param description the description of a new companyEntity.
     * @param keywords    the keywords of a new companyEntity.
     */
    public CompanyEntity(
            final String title,
            final String description,
            final String keywords
    ) {
        this();
        setTitle(title);
        setDescription(description);
        setKeywords(keywords);
    }

    /**
     * Constrictor.
     *
     * @param title       the title of a new companyEntity.
     * @param description the description of a new companyEntity.
     * @param keywords    the keywords of a new companyEntity.
     * @param addressEntity     the addressEntity of a new companyEntity.
     * @param contactsEntity    the contactsEntity of a new companyEntity.
     */
    public CompanyEntity(
            final String title,
            final String description,
            final String keywords,
            final AddressEntity addressEntity,
            final ContactsEntity contactsEntity
    ) {
        this(title, description, keywords);
        setAddressEntity(addressEntity);
        setContactsEntity(contactsEntity);
    }

    /**
     * Constrictor.
     *
     * @param title       the title of a new companyEntity.
     * @param description the description of a new companyEntity.
     * @param information the information of a new companyEntity.
     * @param keywords    the keywords of a new companyEntity.
     */
    public CompanyEntity(
            final String title,
            final String description,
            final String information,
            final String keywords
    ) {
        this(title, description, keywords);
        setInformation(information);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "CompanyEntity{" + super.toString() +
                ", contactsEntity=" + getContactsEntity() +
                ", addressEntity=" + getAddressEntity() +
                ", tagline='" + getTagline() + '\'' +
                ", information='" + getInformation() + '\'' +
                ", domain='" + getDomain() + '\'' +
                ", senderEmail='" + getSenderEmail() + '\'' +
                ", senderPass='" + getSenderPass() + '\'' +
                ", workTimeFrom='" + getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + getWorkTimeTo() + '\'' +
                ", type=" + getType() +
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
            result = (this.getType().equals(other.getType())) &&
                    this.getDomain().equalsIgnoreCase(other.getDomain()) &&
                    this.getTagline().equals(other.getTagline()) &&
                    this.getInformation().equals(other.getInformation());
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
                getType().hashCode() +
                getDomain().hashCode() +
                getTagline().hashCode() +
                getInformation().hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public CompanyEntity clone() {
        final CompanyEntity companyEntity = (CompanyEntity) super.clone();
        companyEntity.setContactsEntity(getContactsEntity().clone());
        companyEntity.setAddressEntity(getAddressEntity().clone());
        return companyEntity;
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
        if (isNotEmpty(domain)) {
            final String temp = domain.replace("http://", "").replace("https://", "");
            this.domain = isNotEmpty(temp) ? temp : "";
        } else {
            this.domain = "";
        }
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
        this.tagline = isNotEmpty(tagline) ? tagline : "";
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
        this.information = isNotEmpty(information) ? information : "";
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
        this.senderEmail = isNotEmpty(senderEmail) ? senderEmail : "";
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
        this.senderPass = isNotEmpty(senderPass) ? senderPass : "";
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
        this.workTimeFrom = new Time(workTimeFrom).getCorrectTime();
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
        this.workTimeTo = new Time(workTimeTo).getCorrectTime();
    }

    /**
     * Returns a companyEntity contactsEntity.
     * Returns a object of the {@link ContactsEntity} class.
     *
     * @return The companyEntity contactsEntity (newer null).
     */
    public ContactsEntity getContactsEntity() {
        return this.contactsEntity;
    }

    /**
     * Sets a new contactsEntity to the companyEntity.
     *
     * @param contactsEntity the new contactsEntity to the companyEntity.
     */
    public void setContactsEntity(final ContactsEntity contactsEntity) {
        if (isNull(this.contactsEntity)) {
            this.contactsEntity = new ContactsEntity();
        }
        this.contactsEntity.initialize(contactsEntity);
    }

    /**
     * Returns a companyEntity addressEntity.
     * Returns a object of the {@link AddressEntity} class.
     *
     * @return The companyEntity addressEntity (newer null).
     */
    public AddressEntity getAddressEntity() {
        return this.addressEntity;
    }

    /**
     * Sets a new addressEntity to the companyEntity.
     *
     * @param addressEntity the new addressEntity to the companyEntity.
     */
    public void setAddressEntity(final AddressEntity addressEntity) {
        if (isNull(this.addressEntity)) {
            this.addressEntity = new AddressEntity();
        }
        this.addressEntity.initialize(addressEntity);
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
     * Returns a URL of the companyEntity.
     *
     * @return The companyEntity URL or empty string (newer null).
     */
    @Override
    public String getUrl() {
        String url = super.getUrl();
        if (isNull(url)) {
            url = getDomain();
        }
        return url;
    }

    /**
     * Checks whether the companyEntity is opened now.
     *
     * @return Returns true if the companyEntity is opened now,
     * otherwise returns false.
     */
    public boolean isOpen() {
        return Time.isWorkTime(this.workTimeFrom, this.workTimeTo);
    }

    /**
     * Initializes the companyEntity.
     * Returns this companyEntity with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this companyEntity
     *     initialize(new CompanyEntity()) - does nothing, returns this
     *     companyEntity with a new copied fields
     * </pre>
     *
     * @param companyEntity the companyEntity to copy.
     * @return This companyEntity with a new fields (newer null).
     */
    public CompanyEntity initialize(final CompanyEntity companyEntity) {
        if (isNotNull(companyEntity)) {
            super.initialize(companyEntity);
            this.setInformation(companyEntity.getInformation());
            this.setDomain(companyEntity.getDomain());
            this.setTagline(companyEntity.getTagline());
            this.setSenderEmail(companyEntity.getSenderEmail());
            this.setSenderPass(companyEntity.getSenderPass());
            this.setWorkTimeFrom(companyEntity.getWorkTimeFrom());
            this.setWorkTimeTo(companyEntity.getWorkTimeTo());
            this.setType(companyEntity.getType());
            this.setContactsEntity(companyEntity.getContactsEntity());
            this.setAddressEntity(companyEntity.getAddressEntity());
        }
        return this;
    }
}
