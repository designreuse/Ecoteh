package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.util.time.Time;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class Company extends Content {

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
    private final String tagline;

    /**
     * The information of a companyEntity.
     */
    private final String information;

    /**
     * The domain of a companyEntity.
     */
    private final String domain;

    /**
     * The sender e-mail of a companyEntity.
     */
    private final String senderEmail;

    /**
     * The sender password of a companyEntity.
     */
    private final String senderPass;

    /**
     * The start work time of a companyEntity.
     */
    private final String workTimeFrom;

    /**
     * The finish work time of a companyEntity.
     */
    private final String workTimeTo;

    /**
     * The companyEntity contactsEntity.
     */
    private final Contacts contacts;

    /**
     * The companyEntity addressEntity.
     */
    private final Address address;

    /**
     * The type of a companyEntity.
     */
    private final CompanyType type;

    /**
     * @param id
     * @param validated
     * @param title
     * @param url
     * @param description
     * @param keywords
     * @param logo
     * @param tagline
     * @param information
     * @param domain
     * @param senderEmail
     * @param senderPass
     * @param workTimeFrom
     * @param workTimeTo
     * @param contacts
     * @param address
     * @param type
     */
    public Company(
            final long id, final boolean validated,
            final String title, final String url,
            final String description, final String keywords,
            final String tagline, final String information, final String domain,
            final String senderEmail, final String senderPass,
            final String workTimeFrom, final String workTimeTo,
            final File logo, final Contacts contacts, final Address address,
            final CompanyType type
    ) {
        super(id, validated, title, url, description, keywords, logo);
        this.tagline = tagline;
        this.information = information;
        this.domain = domain;
        this.senderEmail = senderEmail;
        this.senderPass = senderPass;
        this.workTimeFrom = workTimeFrom;
        this.workTimeTo = workTimeTo;
        this.contacts = contacts;
        this.address = address;
        this.type = type;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object (newer null).
     */
    @Override
    public String toString() {
        return "Company{" + super.toString() +
                ", contacts=" + this.contacts +
                ", address=" + this.address +
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
            final Company other = (Company) object;
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
     * Returns a domain of the companyEntity.
     *
     * @return The companyEntity domain or empty string (newer null).
     */
    public String getDomain() {
        return this.domain;
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
     * Returns a information of the companyEntity.
     *
     * @return The information domain or empty string (newer null).
     */
    public String getInformation() {
        return this.information;
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
     * Returns a sender password of the companyEntity.
     *
     * @return The companyEntity sender password or empty string (newer null).
     */
    public String getSenderPass() {
        return this.senderPass;
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
     * Returns a finish work time of the companyEntity.
     *
     * @return The companyEntity finish work time (newer null).
     */
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Returns a companyEntity contactsEntity.
     * Returns a object of the {@link ContactsEntity} class.
     *
     * @return The companyEntity contactsEntity (newer null).
     */
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Returns a companyEntity addressEntity.
     * Returns a object of the {@link AddressEntity} class.
     *
     * @return The companyEntity addressEntity (newer null).
     */
    public Address getAddress() {
        return this.address;
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
     * Checks whether the companyEntity is opened now.
     *
     * @return Returns true if the companyEntity is opened now,
     * otherwise returns false.
     */
    public boolean isOpen() {
        return Time.isWorkTime(this.workTimeFrom, this.workTimeTo);
    }

    /**
     *
     * @return
     */
    public static CompanyBuilder getBuilder() {
        return new CompanyBuilder();
    }
}
