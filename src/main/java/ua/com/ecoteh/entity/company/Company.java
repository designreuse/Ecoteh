package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.content.Content;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.util.time.Time;

/**
 * The class implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CompanyEntity
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
     * The tagline of this company.
     */
    private final String tagline;

    /**
     * The domain of this company.
     */
    private final String domain;

    /**
     * The sender E-mail of this company.
     */
    private final String senderEmail;

    /**
     * The sender password of this company.
     */
    private final String senderPass;

    /**
     * The start work time of this company.
     */
    private final String workTimeFrom;

    /**
     * The finish work time of this company.
     */
    private final String workTimeTo;

    /**
     * The company contacts.
     */
    private final Contacts contacts;

    /**
     * The company address.
     */
    private final Address address;

    /**
     * The type of this company.
     */
    private final CompanyType type;

    /**
     * Constructor.
     *
     * @param id           the unique identifier for each company.
     * @param validated    the validations of a new company.
     * @param title        the title of a new company.
     * @param url          the URL of a new company.
     * @param text         the text of a new company.
     * @param description  the description of a new company.
     * @param keywords     the keywords of a new company.
     * @param logo         the logo of a new company.
     * @param tagline      the tagline of a new company.
     * @param domain       the domain of a new company.
     * @param senderEmail  the sender E-mail of a new company.
     * @param senderPass   the sender password of a new company.
     * @param workTimeFrom the start work time of a new company.
     * @param workTimeTo   the finish work time of a new company.
     * @param contacts     the contacts of a new company.
     * @param address      the address of a new company.
     * @param type         the type of a new company.
     */
    public Company(
            final long id, final boolean validated,
            final String title, final String url, final String text,
            final String description, final String keywords,
            final String tagline, final String domain,
            final String senderEmail, final String senderPass,
            final String workTimeFrom, final String workTimeTo,
            final File logo, final Contacts contacts, final Address address,
            final CompanyType type
    ) {
        super(id, validated, title, url, text, description, keywords, logo);
        this.tagline = tagline;
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
                    this.tagline.equals(other.tagline);
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
        return super.hashCode() + this.type.hashCode() +
                this.domain.hashCode() + this.tagline.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance (newer null).
     */
    @Override
    public Company clone() {
        final Company clone = (Company) super.clone();
        final CompanyEditor companyEditor = clone.getEditor();
        companyEditor.addContacts(this.contacts.clone())
                .addAddress(this.address.clone());
        return companyEditor.update();
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain or empty string (newer null).
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Returns a tagline of the company.
     *
     * @return The company tagline or empty string (newer null).
     */
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Returns a sender E-mail of the company.
     *
     * @return The company sender E-mail or empty string (newer null).
     */
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password or empty string (newer null).
     */
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time (newer null).
     */
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time (newer null).
     */
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Returns a company contacts.
     *
     * @return The company contacts (newer null).
     */
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Returns a company address.
     *
     * @return The company address (newer null).
     */
    public Address getAddress() {
        return this.address;
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns true if the company is opened now,
     * otherwise returns false.
     */
    public boolean isOpen() {
        return Time.isWorkTime(this.workTimeFrom, this.workTimeTo);
    }

    /**
     * Converts this object and returns an entity
     * of the {@link CompanyEntity} class.
     *
     * @return The entity of the {@link CompanyEntity} class (newer null).
     * @see CompanyConverter
     */
    @Override
    public CompanyEntity convert() {
        return new CompanyConverter(this).convert();
    }

    /**
     * Returns a editor for updating this company.
     *
     * @return the company editor (newer null).
     */
    @Override
    public CompanyEditor getEditor() {
        return new CompanyEditor(this);
    }

    /**
     * Returns a builder for creating a new company.
     *
     * @return the company builder (newer null).
     */
    public static CompanyBuilder getBuilder() {
        return new CompanyBuilder();
    }
}
