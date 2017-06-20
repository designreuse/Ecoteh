package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.content.ContentBuilder;
import ua.com.ecoteh.util.time.Time;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for building an objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Company
 */
public final class CompanyBuilder extends ContentBuilder<Company, CompanyBuilder> {

    /**
     * The tagline of a new company.
     */
    private String tagline;

    /**
     * The information of a new company.
     */
    private String information;

    /**
     * The domain of a new company.
     */
    private String domain;

    /**
     * The sender E-mail of a new company.
     */
    private String senderEmail;

    /**
     * The sender password of a new company.
     */
    private String senderPass;

    /**
     * The start work time of a new company.
     */
    private String workTimeFrom;

    /**
     * The finish work time of a new company.
     */
    private String workTimeTo;

    /**
     * The company contacts.
     */
    private Contacts contacts;

    /**
     * The company address.
     */
    private Address address;

    /**
     * The type of a new company.
     */
    private CompanyType type;

    /**
     * Constructor.
     */
    CompanyBuilder() {
    }

    /**
     * Builds and returns a new company.
     *
     * @return The new company.
     * @see Company
     */
    @Override
    public Company build() {
        return new Company(
                getId(), isValidated(),
                getTitle(), getUrl(),
                getDescription(), getKeywords(),
                getTagline(), getInformation(), getDomain(),
                getSenderEmail(), getSenderPass(),
                getWorkTimeFrom(), getWorkTimeTo(),
                getLogo(), getContacts(), getAddress(),
                getType()
        );
    }

    /**
     * Adds a new tagline to a new company.
     *
     * @param tagline the new tagline to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addTagline(final String tagline) {
        this.tagline = tagline;
        return this;
    }

    /**
     * Adds a new information to a new company.
     *
     * @param information the new information to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addInformation(final String information) {
        this.information = information;
        return this;
    }

    /**
     * Adds a new domain to a new company.
     *
     * @param domain the new domain to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addDomain(final String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Adds a new sender E-mail to a new company.
     *
     * @param senderEmail the new sender E-mail to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    /**
     * Adds a new sender password to a new company.
     *
     * @param senderPass the new sender password to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addSenderPass(final String senderPass) {
        this.senderPass = senderPass;
        return this;
    }

    /**
     * Adds a new start work time to a new company.
     *
     * @param workTimeFrom the new start work time to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
        return this;
    }

    /**
     * Adds a new finish work time to a new company.
     *
     * @param workTimeTo the new finish work time to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
        return this;
    }

    /**
     * Adds a new contacts to a new company.
     *
     * @param contacts the new contacts to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Adds a new address to a new company.
     *
     * @param address the new address to a new company.
     * @return the company builder.
     */

    public CompanyBuilder addAddress(final Address address) {
        this.address = address;
        return this;
    }

    /**
     * Adds a new type to a new company.
     *
     * @param type the new type to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addType(final CompanyType type) {
        this.type = type;
        return this;
    }

    /**
     * Adds a new type to a new company.
     *
     * @param name the new type name to a new company.
     * @return the company builder.
     */
    public CompanyBuilder addType(final String name) {
        final CompanyType type = getType(name);
        return addType(type);
    }

    /**
     * Returns a tagline of a new company.
     * Returns an empty string if the tagline is null or empty.
     *
     * @return The tagline or empty string (newer null).
     */
    private String getTagline() {
        return isNotEmpty(this.tagline) ? this.tagline : "";
    }

    /**
     * Returns a information of a new company.
     * Returns an empty string if the information is null or empty.
     *
     * @return The information or empty string (newer null).
     */
    private String getInformation() {
        return isNotEmpty(this.information) ? this.information : "";
    }

    /**
     * Returns a domain of a new company.
     * Returns an empty string if the domain is null or empty.
     *
     * @return The domain or empty string (newer null).
     */
    private String getDomain() {
        String result;
        if (isNotEmpty(this.domain)) {
            result = this.domain.replace("http://", "").replace("https://", "");
            result = isNotEmpty(result) ? result : "";
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Returns a sender E-mail of a new company.
     * Returns an empty string if the sender E-mail is null or empty.
     *
     * @return The sender E-mail or empty string (newer null).
     */
    private String getSenderEmail() {
        return isNotEmpty(this.senderEmail) ? this.senderEmail : "";
    }

    /**
     * Returns a sender password of a new company.
     * Returns an empty string if the sender password is null or empty.
     *
     * @return The sender password or empty string (newer null).
     */
    private String getSenderPass() {
        return isNotEmpty(this.senderPass) ? this.senderPass : "";
    }

    /**
     * Returns a start work time of a new company.
     * Returns a "00:00" if the start work time is null or empty.
     *
     * @return The start work time (newer null).
     */
    private String getWorkTimeFrom() {
        return new Time(this.workTimeFrom).getCorrectTime();
    }

    /**
     * Returns a finish work time of a new company.
     * Returns a "00:00" if the finish work time is null or empty.
     *
     * @return The finish work time (newer null).
     */
    private String getWorkTimeTo() {
        return new Time(this.workTimeTo).getCorrectTime();
    }

    /**
     * Returns a contacts of a new company.
     * Returns a new contacts if the contacts is null.
     *
     * @return The company contacts (newer null).
     */
    private Contacts getContacts() {
        return isNotNull(this.contacts) ? this.contacts : Contacts.getBuilder().build();
    }

    /**
     * Returns a address of a new company.
     * Returns a new address type if the address is null.
     *
     * @return The company address (newer null).
     */
    private Address getAddress() {
        return isNotNull(this.address) ? this.address : Address.getBuilder().build();
    }

    /**
     * Returns a type of a new company.
     * Returns an ANOTHER company type if the type is null.
     *
     * @return The company type (newer null).
     */
    private CompanyType getType() {
        return isNotNull(this.type) ? this.type : CompanyType.ANOTHER;
    }

    /**
     * Returns a company type by name.
     *
     * @param name the type name of a new company.
     * @return The company type.
     */
    private CompanyType getType(final String name) {
        CompanyType type;
        try {
            type = CompanyType.valueOf(name);
        } catch (Exception ex) {
            type = CompanyType.ANOTHER;
        }
        return type;
    }
}
