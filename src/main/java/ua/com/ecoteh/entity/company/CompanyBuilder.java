package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.content.ContentBuilder;
import ua.com.ecoteh.util.time.Time;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CompanyBuilder extends ContentBuilder<Company, CompanyBuilder> {

    /**
     * The tagline of a companyEntity.
     */
    private String tagline;

    /**
     * The information of a companyEntity.
     */
    private String information;

    /**
     * The domain of a companyEntity.
     */
    private String domain;

    /**
     * The sender e-mail of a companyEntity.
     */
    private String senderEmail;

    /**
     * The sender password of a companyEntity.
     */
    private String senderPass;

    /**
     * The start work time of a companyEntity.
     */
    private String workTimeFrom;

    /**
     * The finish work time of a companyEntity.
     */
    private String workTimeTo;

    /**
     * The companyEntity contactsEntity.
     */
    private Contacts contacts;

    /**
     * The companyEntity addressEntity.
     */
    private Address address;

    /**
     * The type of a companyEntity.
     */
    private CompanyType type;

    CompanyBuilder() {
    }

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

    public CompanyBuilder addTagline(final String tagline) {
        this.tagline = tagline;
        return this;
    }

    public CompanyBuilder addInformation(final String information) {
        this.information = information;
        return this;
    }

    public CompanyBuilder addDomain(final String domain) {
        this.domain = domain;
        return this;
    }

    public CompanyBuilder addSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    public CompanyBuilder addSenderPass(final String senderPass) {
        this.senderPass = senderPass;
        return this;
    }

    public CompanyBuilder addWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
        return this;
    }

    public CompanyBuilder addWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
        return this;
    }

    public CompanyBuilder addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    public CompanyBuilder addAddress(final Address address) {
        this.address = address;
        return this;
    }

    public CompanyBuilder addType(final CompanyType type) {
        this.type = type;
        return this;
    }

    private String getTagline() {
        return isNotEmpty(this.tagline) ? this.tagline : "";
    }

    private String getInformation() {
        return isNotEmpty(this.information) ? this.information : "";
    }

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

    private String getSenderEmail() {
        return isNotEmpty(this.senderEmail) ? this.senderEmail : "";
    }

    private String getSenderPass() {
        return isNotEmpty(this.senderPass) ? this.senderPass : "";
    }

    private String getWorkTimeFrom() {
        return new Time(this.workTimeFrom).getCorrectTime();
    }

    private String getWorkTimeTo() {
        return new Time(this.workTimeTo).getCorrectTime();
    }

    private Contacts getContacts() {
        return isNotNull(this.contacts) ? this.contacts : Contacts.getBuilder().build();
    }

    private Address getAddress() {
        return isNotNull(this.address) ? this.address : Address.getBuilder().build();
    }

    private CompanyType getType() {
        return isNotNull(this.type) ? this.type : CompanyType.ANOTHER;
    }
}
