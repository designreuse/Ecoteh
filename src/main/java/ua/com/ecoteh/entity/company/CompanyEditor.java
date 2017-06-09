package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.address.AddressEditor;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.contacts.ContactsEditor;
import ua.com.ecoteh.entity.content.ContentEditor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements a set of methods
 * for editing an objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Company
 */
public final class CompanyEditor extends ContentEditor<Company, CompanyEditor> {

    /**
     * The company to edit.
     */
    private final Company company;

    /**
     * The new tagline of the company.
     */
    private String tagline;

    /**
     * The new information of the company.
     */
    private String information;

    /**
     * The new domain of the company.
     */
    private String domain;

    /**
     * The new sender E-mail of the company.
     */
    private String senderEmail;

    /**
     * The new sender password of the company.
     */
    private String senderPass;

    /**
     * The new start work time of the company.
     */
    private String workTimeFrom;

    /**
     * The new finish work time of the company.
     */
    private String workTimeTo;

    /**
     * The new company contacts.
     */
    private Contacts contacts;

    /**
     * The new company address.
     */
    private Address address;

    /**
     * The new type of the company.
     */
    private CompanyType type;

    /**
     * Constructor.
     *
     * @param company the company to edit.
     */
    CompanyEditor(final Company company) {
        super(company);
        this.company = company;
    }

    /**
     * Updates and returns a new company.
     *
     * @return The updated company.
     */
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

    /**
     * Copies the incoming company.
     *
     * @param company the company to copy.
     * @return the company editor.
     */
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

    /**
     * Adds a new tagline to the company.
     *
     * @param tagline the new tagline to the company.
     * @return the company editor.
     */
    public CompanyEditor addTagline(final String tagline) {
        this.tagline = tagline;
        return this;
    }

    /**
     * Adds a new information to the company.
     *
     * @param information the new information to the company.
     * @return the company editor.
     */
    public CompanyEditor addInformation(final String information) {
        this.information = information;
        return this;
    }

    /**
     * Adds a new domain to the company.
     *
     * @param domain the new domain to the company.
     * @return the company editor.
     */
    public CompanyEditor addDomain(final String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Adds a new sender E-mail to the company.
     *
     * @param senderEmail the new sender E-mail to the company.
     * @return the company editor.
     */
    public CompanyEditor addSenderEmail(final String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    /**
     * Adds a new sender password to the company.
     *
     * @param senderPass the new sender password to the company.
     * @return the company editor.
     */
    public CompanyEditor addSenderPass(final String senderPass) {
        this.senderPass = senderPass;
        return this;
    }

    /**
     * Adds a new start work time to the company.
     *
     * @param workTimeFrom the new start work time to the company.
     * @return the company editor.
     */
    public CompanyEditor addWorkTimeFrom(final String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
        return this;
    }

    /**
     * Adds a new finish work time to the company.
     *
     * @param workTimeTo the new finish work time to the company.
     * @return the company editor.
     */
    public CompanyEditor addWorkTimeTo(final String workTimeTo) {
        this.workTimeTo = workTimeTo;
        return this;
    }

    /**
     * Adds a new contacts to the company.
     *
     * @param contacts the new contacts to the company.
     * @return the company editor.
     */
    public CompanyEditor addContacts(final Contacts contacts) {
        this.contacts = contacts;
        return this;
    }

    /**
     * Adds a new address to the company.
     *
     * @param address the new address to the company.
     * @return the company editor.
     */
    public CompanyEditor addAddress(final Address address) {
        this.address = address;
        return this;
    }

    /**
     * Adds a new type to the company.
     *
     * @param type the new type to the company.
     * @return the company editor.
     */
    public CompanyEditor addType(final CompanyType type) {
        this.type = type;
        return this;
    }

    /**
     * Returns a tagline of a new company.
     * Returns the company tagline if the tagline is null.
     *
     * @return The tagline (newer null).
     */
    private String getTagline() {
        return isNotEmpty(this.tagline) ? this.tagline : this.company.getTagline();
    }

    /**
     * Returns a information of a new company.
     * Returns the company information if the information is null.
     *
     * @return The information (newer null).
     */
    private String getInformation() {
        return isNotEmpty(this.information) ? this.information : this.company.getInformation();
    }

    /**
     * Returns a domain of a new company.
     * Returns the company domain if the domain is null.
     *
     * @return The domain (newer null).
     */
    private String getDomain() {
        return isNotNull(this.domain) ? this.domain : this.company.getDomain();
    }

    /**
     * Returns a sender E-mail of a new company.
     * Returns the company sender E-mail if the sender E-mail is null.
     *
     * @return The sender E-mail (newer null).
     */
    private String getSenderEmail() {
        return isNotNull(this.senderEmail) ? this.senderEmail : this.company.getSenderEmail();
    }

    /**
     * Returns a sender password of a new company.
     * Returns the company sender password if the sender password is null.
     *
     * @return The sender password (newer null).
     */
    private String getSenderPass() {
        return isNotEmpty(this.senderPass) ? this.senderPass : this.company.getSenderPass();
    }

    /**
     * Returns a start work time of a new company.
     * Returns the company start work time if the start work time is null.
     *
     * @return The start work time (newer null).
     */
    private String getWorkTimeFrom() {
        return isNotNull(this.workTimeFrom) ? this.workTimeFrom : this.company.getWorkTimeFrom();
    }

    /**
     * Returns a finish work time of a new company.
     * Returns the company finish work time if the finish work time is null.
     *
     * @return The finish work time (newer null).
     */
    private String getWorkTimeTo() {
        return isNotNull(this.workTimeTo) ? this.workTimeTo : this.company.getWorkTimeTo();
    }

    /**
     * Returns a contacts of a new company.
     * Returns the company contacts if the contacts is null.
     *
     * @return The contacts (newer null).
     * @see ContactsEditor
     */
    private Contacts getContacts() {
        final ContactsEditor contactsEditor = this.company.getContacts().getEditor();
        contactsEditor.copy(this.contacts);
        return contactsEditor.update();
    }

    /**
     * Returns a address of a new company.
     * Returns the company address if the address is null.
     *
     * @return The address (newer null).
     * @see AddressEditor
     */
    private Address getAddress() {
        final AddressEditor addressEditor = this.company.getAddress().getEditor();
        addressEditor.copy(this.address);
        return addressEditor.update();
    }

    /**
     * Returns a contacts of a new company.
     * Returns the company type if the type is null.
     *
     * @return The company type (newer null).
     */
    private CompanyType getType() {
        return isNotNull(this.type) ? this.type : this.company.getType();
    }
}
