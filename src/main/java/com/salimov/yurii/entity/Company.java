package com.salimov.yurii.entity;

import com.salimov.yurii.entity.interfaces.ICompany;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.util.time.Time;

import javax.persistence.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Content
 * @see ICompany
 */
@Entity
@Table(name = "companies")
public final class Company extends Content implements ICompany {

    /**
     * It is used during deserialization to verify that
     * the sender and receiver of a serialized object have
     * loaded classes for that object that are compatible
     * with respect to serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The tagline of a company.
     */
    @Column(name = "tagline", nullable = false)
    private String tagline;

    /**
     * The information of a company.
     */
    @Column(name = "information", nullable = false)
    private String information;

    /**
     * The domain of a company.
     */
    @Column(name = "domain", nullable = false)
    private String domain;

    /**
     * The sender e-mail of a company.
     */
    @Column(name = "sender_email", nullable = false)
    private String senderEmail;

    /**
     * The sender password of a company.
     */
    @Column(name = "sender_pass", nullable = false)
    private String senderPass;

    /**
     * The start work time of a company.
     */
    @Column(name = "work_time_from", nullable = false)
    private String workTimeFrom;

    /**
     * The finish work time of a company.
     */
    @Column(name = "work_time_to", nullable = false)
    private String workTimeTo;

    /**
     * The logo URL of a company.
     */
    @Column(name = "logo", nullable = false)
    private String logoUrl;

    /**
     * The favicon URL of a company.
     */
    @Column(name = "favicon", nullable = false)
    private String faviconUrl;

    /**
     * The company contacts.
     *
     * @see Category
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "id_contacts",
            referencedColumnName = "id"
    )
    private Contacts contacts;

    /**
     * The company address.
     *
     * @see Category
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "id_address",
            referencedColumnName = "id"
    )
    private Address address;

    /**
     * The type of a company.
     *
     * @see CompanyType
     */
    @Column(
            name = "type",
            nullable = false
    )
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Default constructor.
     * Sets default company type {@link CompanyType#PARTNER}.
     */
    public Company() {
        this.tagline = "";
        this.information = "";
        this.domain = "";
        this.senderEmail = "";
        this.senderPass = "";
        this.workTimeFrom = "";
        this.workTimeTo = "";
        this.logoUrl = "";
        this.faviconUrl = "";
        this.type = CompanyType.PARTNER;
        this.contacts = new Contacts();
        this.address = new Address();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Company{" + super.toString() +
                getContacts() +
                getAddress() +
                ", tagline='" + getTagline() + '\'' +
                ", information='" + getInformation() + '\'' +
                ", domain='" + getDomain() + '\'' +
                ", senderEmail='" + getSenderEmail() + '\'' +
                ", senderPass='" + getSenderPass() + '\'' +
                ", workTimeFrom='" + getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + getWorkTimeTo() + '\'' +
                ", logoUrl='" + getLogoUrl() + '\'' +
                ", faviconUrl='" + getFaviconUrl() + '\'' +
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
            final Company other = (Company) object;
            result = (this.type.equals(other.type)) &&
                    this.domain.equalsIgnoreCase(other.domain) &&
                    this.tagline.equals(other.tagline) &&
                    this.getDescription().equalsIgnoreCase(other.getDescription()) &&
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
        return super.hashCode() + this.type.hashCode() + this.domain.hashCode()
                + this.tagline.hashCode() + getDescription().hashCode()
                + this.information.hashCode();
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return A clone of this instance.
     */
    @Override
    public Company clone() {
        return (Company) super.clone();
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the company.
     * If parameter domain is blank, then sets {@code null}.
     *
     * @param domain a new domain to the company.
     */
    @Override
    public void setDomain(final String domain) {
        if (isNotBlank(domain)) {
            final String temp = domain.replace("http://", "")
                    .replace("https://", "");
            this.domain = isNotBlank(temp) ? temp : "";
        } else {
            this.domain = null;
        }
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the company.
     * If parameter tagline is blank, then sets {@code null}.
     *
     * @param tagline a new domain to the company.
     */
    @Override
    public void setTagline(final String tagline) {
        this.tagline = isNotBlank(tagline) ? tagline : "";
    }

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    @Override
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the company.
     * If parameter information is blank, then sets {@code null}.
     *
     * @param information a new information to the company.
     */
    @Override
    public void setInformation(final String information) {
        this.information = isNotBlank(information) ? information : "";
    }

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    @Override
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender e-mail to the company.
     * If parameter sender e-mail is blank, then sets {@code null}.
     *
     * @param senderEmail a new sender e-mail to the company.
     */
    @Override
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = isNotBlank(senderEmail) ? senderEmail : "";
    }

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
    @Override
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Sets a new sender password to the company.
     * If parameter sender password is blank, then sets {@code null}.
     *
     * @param senderPass a new sender password to the company.
     */
    public void setSenderPass(final String senderPass) {
        this.senderPass = isNotBlank(senderPass) ? senderPass : "";
    }

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    @Override
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the company.
     *
     * @param workTimeFrom a new start work time to the company.
     */
    @Override
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = new Time(workTimeFrom).getCorrectTime();
    }

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    @Override
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the company.
     *
     * @param workTimeTo a new finish work time to the company.
     */
    @Override
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = new Time(workTimeTo).getCorrectTime();
    }

    /**
     * Returns a company contacts.
     *
     * @return The company contacts.
     */
    @Override
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Sets a new contacts to the company.
     *
     * @param contacts a new contacts to the company.
     */
    @Override
    public void setContacts(final Contacts contacts) {
        this.contacts.initialize(contacts);
    }

    /**
     * Returns a company address.
     *
     * @return The company address.
     */
    @Override
    public Address getAddress() {
        return this.address;
    }

    /**
     * Sets a new address to the company.
     *
     * @param address a new address to the company.
     */
    @Override
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Returns a logo URL of the company.
     *
     * @return The company logo URL.
     */
    @Override
    public String getLogoUrl() {
        return this.logoUrl;
    }

    /**
     * Sets a new logo URL to the article.
     * If parameter logo URL is invalid
     * then sets {@code null}.
     *
     * @param logoUrl a new logo to the article.
     * @see File
     */
    @Override
    public void setLogoUrl(final String logoUrl) {
        this.logoUrl = isNotBlank(logoUrl) ? logoUrl : "";
    }

    /**
     * Returns a favicon URL of the company.
     *
     * @return The company favicon URL.
     */
    @Override
    public String getFaviconUrl() {
        return this.faviconUrl;
    }

    /**
     * Sets a new favicon URL to the article.
     * If parameter favicon URL is invalid
     * then sets {@code null}.
     *
     * @param faviconUrl a new favicon to the article.
     */
    @Override
    public void setFaviconUrl(final String faviconUrl) {
        this.faviconUrl = isNotBlank(faviconUrl) ? faviconUrl : "";
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     * @see CompanyType
     */
    @Override
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the company.
     *
     * @param type a new logo to the company.
     * @see CompanyType
     */
    @Override
    public void setType(final CompanyType type) {
        this.type = type;
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    @Override
    public String getUrl() {
        String url = super.getUrl();
        if (url == null) {
            url = getDomain();
        }
        return url;
    }

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns {@code true} if the company is opened now,
     * otherwise returns {@code false}.
     */
    @Override
    public boolean isOpen() {
        return Time.isWorkTime(this.workTimeFrom, this.workTimeTo);
    }

    /**
     * Initializes the company.
     *
     * @param company a company to copy.
     * @return The this company with new fields.
     */
    @Override
    public Company initialize(final Company company) {
        if (company != null) {
            super.initialize(company);
            this.setDomain(company.getDomain());
            this.setTagline(company.getTagline());
            this.setSenderEmail(company.getSenderEmail());
            this.setSenderPass(company.getSenderPass());
            this.setWorkTimeFrom(company.getWorkTimeFrom());
            this.setWorkTimeTo(company.getWorkTimeTo());
            this.setLogoUrl(company.getLogoUrl());
            this.setFaviconUrl(company.getFaviconUrl());
            this.setType(company.getType());
            this.setContacts(company.getContacts());
            this.setAddress(company.getAddress());
        }
        return this;
    }
}
