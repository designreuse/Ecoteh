package ua.com.ecoteh.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ua.com.ecoteh.enums.CompanyType;
import ua.com.ecoteh.util.time.Time;

import javax.persistence.*;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of standard methods for working
 * with entity of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Entity
@Table(name = "companies")
public class Company extends Content implements ICompany {

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
     * The company contacts.
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
    private Contacts contacts;

    /**
     * The company address.
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
    private Address address;

    /**
     * The type of a company.
     */
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    /**
     * Default constructor.
     */
    public Company() {
        this.tagline = "";
        this.information = "";
        this.domain = "";
        this.senderEmail = "";
        this.senderPass = "";
        this.workTimeFrom = "";
        this.workTimeTo = "";
        this.type = CompanyType.PARTNER;
        this.contacts = new Contacts();
        this.address = new Address();
    }

    /**
     * Constrictor.
     *
     * @param title       the title of a new company.
     * @param description the description of a new company.
     * @param keywords    the keywords of a new company.
     */
    public Company(
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
     * @param title       the title of a new company.
     * @param description the description of a new company.
     * @param keywords    the keywords of a new company.
     * @param address     the address of a new company.
     * @param contacts    the contacts of a new company.
     */
    public Company(
            final String title,
            final String description,
            final String keywords,
            final Address address,
            final Contacts contacts
    ) {
        this(title, description, keywords);
        setAddress(address);
        setContacts(contacts);
    }

    /**
     * Constrictor.
     *
     * @param title       the title of a new company.
     * @param description the description of a new company.
     * @param information the information of a new company.
     * @param keywords    the keywords of a new company.
     */
    public Company(
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
        return "Company{" + super.toString() +
                ", contacts=" + getContacts() +
                ", address=" + getAddress() +
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
            final Company other = (Company) object;
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
    public Company clone() {
        final Company company = (Company) super.clone();
        company.setContacts(getContacts().clone());
        company.setAddress(getAddress().clone());
        return company;
    }

    /**
     * Returns a domain of the company.
     *
     * @return The company domain or empty string (newer null).
     */
    @Override
    public String getDomain() {
        return this.domain;
    }

    /**
     * Sets a new domain to the company.
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
     * @param domain a new domain to the company.
     */
    @Override
    public void setDomain(final String domain) {
        if (isNotEmpty(domain)) {
            final String temp = domain.replace("http://", "").replace("https://", "");
            this.domain = isNotEmpty(temp) ? temp : "";
        } else {
            this.domain = "";
        }
    }

    /**
     * Returns a tagline of the company.
     *
     * @return The company tagline or empty string (newer null).
     */
    @Override
    public String getTagline() {
        return this.tagline;
    }

    /**
     * Sets a new tagline to the company.
     * If parameter tagline is blank, then sets empty string.
     * <pre>
     *     setTagline(null) - tagline = ""
     *     setTagline("") - tagline = ""
     *     setTagline(" ") - tagline = ""
     *     setTagline("bob") - tagline = "bob"
     *     setTagline(" bob ") - tagline = " bob "
     * </pre>
     *
     * @param tagline a new tagline to the company.
     */
    @Override
    public void setTagline(final String tagline) {
        this.tagline = isNotEmpty(tagline) ? tagline : "";
    }

    /**
     * Returns a information of the company.
     *
     * @return The information domain or empty string (newer null).
     */
    @Override
    public String getInformation() {
        return this.information;
    }

    /**
     * Sets a new information to the company.
     * If parameter information is blank, then sets empty string.
     * <pre>
     *     setInformation(null) - information = ""
     *     setInformation("") - information = ""
     *     setInformation(" ") - information = ""
     *     setInformation("bob") - information = "bob"
     *     setInformation(" bob ") - information = " bob "
     * </pre>
     *
     * @param information a new information to the company.
     */
    @Override
    public void setInformation(final String information) {
        this.information = isNotEmpty(information) ? information : "";
    }

    /**
     * Returns a sender E-mail of the company.
     *
     * @return The company sender E-mail or empty string (newer null).
     */
    @Override
    public String getSenderEmail() {
        return this.senderEmail;
    }

    /**
     * Sets a new sender E-mail to the company.
     * If parameter sender E-mail is blank, then sets empty string.
     * <pre>
     *     setSenderEmail(null) - senderEmail = ""
     *     setSenderEmail("") - senderEmail = ""
     *     setSenderEmail(" ") - senderEmail = ""
     *     setSenderEmail("bob") - senderEmail = "bob"
     *     setSenderEmail(" bob ") - senderEmail = " bob "
     * </pre>
     *
     * @param senderEmail a new sender E-mail to the company.
     */
    @Override
    public void setSenderEmail(final String senderEmail) {
        this.senderEmail = isNotEmpty(senderEmail) ? senderEmail : "";
    }

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password or empty string (newer null).
     */
    @Override
    public String getSenderPass() {
        return this.senderPass;
    }

    /**
     * Sets a new sender password to the company.
     * If parameter sender password is blank, then sets empty string.
     * <pre>
     *     setSenderPass(null) - senderPass = ""
     *     setSenderPass("") - senderPass = ""
     *     setSenderPass(" ") - senderPass = ""
     *     setSenderPass("bob") - senderPass = "bob"
     *     setSenderPass(" bob ") - senderPass = " bob "
     * </pre>
     *
     * @param senderPass a new sender password to the company.
     */
    public void setSenderPass(final String senderPass) {
        this.senderPass = isNotEmpty(senderPass) ? senderPass : "";
    }

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time (newer null).
     */
    @Override
    public String getWorkTimeFrom() {
        return this.workTimeFrom;
    }

    /**
     * Sets a new start work time to the company.
     * <pre>
     *     setWorkTimeFrom(null) - workTimeFrom = "00:00"
     *     setSenderPass("") - workTimeFrom = "00:00"
     *     setSenderPass(" ") - workTimeFrom = "00:00"
     *     setSenderPass("bob") - workTimeFrom = "00:00"
     *     setSenderPass("12:34") - workTimeFrom = "12:34"
     * </pre>
     *
     * @param workTimeFrom the new start work time to the company.
     */
    @Override
    public void setWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = new Time(workTimeFrom).getCorrectTime();
    }

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time (newer null).
     */
    @Override
    public String getWorkTimeTo() {
        return this.workTimeTo;
    }

    /**
     * Sets a new finish work time to the company.
     * <pre>
     *     setWorkTimeTo(null) - workTimeTo = "00:00"
     *     setWorkTimeTo("") - workTimeTo = "00:00"
     *     setWorkTimeTo(" ") - workTimeTo = "00:00"
     *     setWorkTimeTo("bob") - workTimeTo = "00:00"
     *     setWorkTimeTo("12:34") - workTimeTo = "12:34"
     * </pre>
     *
     * @param workTimeTo the new finish work time to the company.
     */
    @Override
    public void setWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = new Time(workTimeTo).getCorrectTime();
    }

    /**
     * Returns a company contacts.
     * Returns a object of the {@link Contacts} class.
     *
     * @return The company contacts (newer null).
     */
    @Override
    public Contacts getContacts() {
        return this.contacts;
    }

    /**
     * Sets a new contacts to the company.
     *
     * @param contacts the new contacts to the company.
     */
    @Override
    public void setContacts(final Contacts contacts) {
        if (isNull(this.contacts)) {
            this.contacts = new Contacts();
        }
        this.contacts.initialize(contacts);
    }

    /**
     * Returns a company address.
     * Returns a object of the {@link Address} class.
     *
     * @return The company address (newer null).
     */
    @Override
    public Address getAddress() {
        return this.address;
    }

    /**
     * Sets a new address to the company.
     *
     * @param address the new address to the company.
     */
    @Override
    public void setAddress(final Address address) {
        if (isNull(this.address)) {
            this.address = new Address();
        }
        this.address.initialize(address);
    }

    /**
     * Returns a domain of the company.
     * Returns a enum object of the {@link CompanyType} class.
     *
     * @return The company domain.
     */
    @Override
    public CompanyType getType() {
        return this.type;
    }

    /**
     * Sets a new type to the company.
     * Sets default type if incoming type is null.
     * <pre>
     *     setType(null) - type = CompanyType.PARTNER
     *     setType(CompanyType.MAIN) - type = CompanyType.MAIN
     * </pre>
     *
     * @param type the new type to the company.
     */
    @Override
    public void setType(final CompanyType type) {
        this.type = isNotNull(type) ? type : CompanyType.PARTNER;
    }

    /**
     * Returns a URL of the company.
     *
     * @return The company URL or empty string (newer null).
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
     * Checks whether the company is opened now.
     *
     * @return Returns true if the company is opened now,
     * otherwise returns false.
     */
    @Override
    public boolean isOpen() {
        return Time.isWorkTime(this.workTimeFrom, this.workTimeTo);
    }

    /**
     * Initializes the company.
     * Returns this company with a new copied fields.
     * <pre>
     *     initialize(null) - does nothing, returns this company
     *     initialize(new Company()) - does nothing, returns this
     *     company with a new copied fields
     * </pre>
     *
     * @param company the company to copy.
     * @return This company with a new fields (newer null).
     */
    @Override
    public Company initialize(final Company company) {
        if (isNotNull(company)) {
            super.initialize(company);
            this.setInformation(company.getInformation());
            this.setDomain(company.getDomain());
            this.setTagline(company.getTagline());
            this.setSenderEmail(company.getSenderEmail());
            this.setSenderPass(company.getSenderPass());
            this.setWorkTimeFrom(company.getWorkTimeFrom());
            this.setWorkTimeTo(company.getWorkTimeTo());
            this.setType(company.getType());
            this.setContacts(company.getContacts());
            this.setAddress(company.getAddress());
        }
        return this;
    }
}
