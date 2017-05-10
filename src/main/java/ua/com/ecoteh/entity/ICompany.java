package ua.com.ecoteh.entity;

import ua.com.ecoteh.enums.CompanyType;

/**
 * The interface describes a set of methods
 * for working with objects of
 * the {@link Company} class.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface ICompany extends IContent {

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    String getDomain();

    /**
     * Sets a new domain to the company.
     *
     * @param domain the new domain to the company.
     */
    void setDomain(String domain);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    String getTagline();

    /**
     * Sets a new tagline to the company.
     *
     * @param tagline the new domain to the company.
     */
    void setTagline(String tagline);

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    String getInformation();

    /**
     * Sets a new information to the company.
     *
     * @param information the new information to the company.
     */
    void setInformation(String information);

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    String getSenderEmail();

    /**
     * Sets a new sender e-mail to the company.
     *
     * @param senderEmail the new sender e-mail to the company.
     */
    void setSenderEmail(String senderEmail);

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
    String getSenderPass();

    /**
     * Sets a new sender password to the company.
     *
     * @param senderPass the new sender password to the company.
     */
    void setSenderPass(String senderPass);

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    String getWorkTimeFrom();

    /**
     * Sets a new start work time to the company.
     *
     * @param workTimeFrom the new start work time to the company.
     */
    void setWorkTimeFrom(String workTimeFrom);

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    String getWorkTimeTo();

    /**
     * Sets a new finish work time to the company.
     *
     * @param workTimeTo the new finish work time to the company.
     */
    void setWorkTimeTo(String workTimeTo);

    /**
     * Returns a company contacts.
     *
     * @return The company contacts.
     */
    Contacts getContacts();

    /**
     * Sets a new contacts to the company.
     *
     * @param contacts the new contacts to the company.
     */
    void setContacts(Contacts contacts);

    /**
     * Returns a company address.
     *
     * @return The company address.
     */
    Address getAddress();

    /**
     * Sets a new address to the company.
     *
     * @param address the new address to the company.
     */
    void setAddress(Address address);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    CompanyType getType();

    /**
     * Sets a new type to the company.
     *
     * @param type the new logo to the company.
     */
    void setType(CompanyType type);

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns true if the company is opened now,
     * otherwise returns false.
     */
    boolean isOpen();

    /**
     * Initializes the company.
     * Returns this company with a new copied fields.
     *
     * @param company the company to copy.
     * @return This company with new fields.
     */
    Company initialize(Company company);
}
