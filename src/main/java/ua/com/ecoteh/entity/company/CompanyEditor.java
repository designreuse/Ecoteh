package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressEditor;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEditor;
import ua.com.ecoteh.entity.content.ContentEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class CompanyEditor extends ContentEditor<Company, CompanyEditor> {

    private final Company company;

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

    /**
     * Constructor.
     * @param company
     */
    CompanyEditor(final Company company) {
        super(company);
        this.company = company;
    }

    @Override
    public Company update() {
        final CompanyBuilder builder = Company.getBuilder();
        builder.addId(getId())
                .addValidated(isValidated())
                .addTitle(getTitle())
                .addUrl(getUrl())
                .addDescription(getDescription())
                .addKeywords(getKeywords())
                .addTagline(getTagline())
                .addInformation(getInformation())
                .addDomain(getDomain())
                .addSenderEmail(getSenderEmail())
                .addSenderPass(getSenderPass())
                .addWorkTimeFrom(getWorkTimeFrom())
                .addWorkTimeTo(getWorkTimeTo())
                .addType(getType())
                .addLogo(getLogo())
                .addContacts(getContacts())
                .addAddress(getAddress());
        return builder.build();
    }

    @Override
    public CompanyEditor copy(final Company company) {
        if (isNotNull(company)) {
            super.copy(company)
                    .addTagline(company.getTagline())
                    .addInformation(company.getInformation())
                    .addDomain(company.getDomain())
                    .addSenderEmail(company.getSenderEmail())
                    .addSenderPass(company.getSenderPass())
                    .addWorkTimeFrom(company.getWorkTimeFrom())
                    .addWorkTimeTo(company.getWorkTimeTo())
                    .addType(company.getType())
                    .addContacts(company.getContacts())
                    .addAddress(company.getAddress());
        }
        return this;
    }

    public CompanyEditor addTagline(final String tagline) {
        this.tagline = tagline;
        return this;
    }

    public CompanyEditor addInformation(final String information) {
        this.information = information;
        return this;
    }

    public CompanyEditor addDomain(final String domain) {
        this.domain = domain;
        return this;
    }

    public CompanyEditor addSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    public CompanyEditor addSenderPass(final String senderPass) {
        this.senderPass = senderPass;
        return this;
    }

    public CompanyEditor addWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
        return this;
    }

    public CompanyEditor addWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
        return this;
    }

    public CompanyEditor addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    public CompanyEditor addAddress(final Address address) {
        this.address = address;
        return this;
    }

    public CompanyEditor addType(final CompanyType type) {
        this.type = type;
        return this;
    }

    private String getTagline() {
        return isNotEmpty(this.tagline) ? this.tagline : this.company.getTagline();
    }

    private String getInformation() {
        return isNotEmpty(this.information) ? this.information : this.company.getInformation();
    }

    private String getDomain() {
        return isNotNull(this.domain) ? this.domain : this.company.getDomain();
    }

    private String getSenderEmail() {
        return isNotNull(this.senderEmail) ? this.senderEmail : this.company.getSenderEmail();
    }

    private String getSenderPass() {
        return isNotEmpty(this.senderPass) ? this.senderPass : this.company.getSenderPass();
    }

    private String getWorkTimeFrom() {
        return isNotNull(this.workTimeFrom) ? this.workTimeFrom : this.company.getWorkTimeFrom();
    }

    private String getWorkTimeTo() {
        return isNotNull(this.workTimeTo) ? this.workTimeTo : this.company.getWorkTimeTo();
    }

    private Contacts getContacts() {
        final ContactsEditor contactsRedactor = this.company.getContacts().getEditor();
        contactsRedactor.copy(this.contacts);
        return contactsRedactor.update();
    }

    private Address getAddress() {
        final AddressEditor addressRedactor = this.company.getAddress().getEditor();
        addressRedactor.copy(this.address);
        return addressRedactor.update();
    }

    private CompanyType getType() {
        return isNotNull(this.type) ? this.type : this.company.getType();
    }
}
