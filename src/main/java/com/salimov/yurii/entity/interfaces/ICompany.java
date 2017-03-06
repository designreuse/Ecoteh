package com.salimov.yurii.entity.interfaces;

import com.salimov.yurii.entity.Address;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.Contacts;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.enums.CompanyType;

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
     * @param domain a new domain to the company.
     */
    void setDomain(final String domain);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    String getTagline();

    /**
     * Sets a new tagline to the company.
     *
     * @param tagline a new domain to the company.
     */
    void setTagline(final String tagline);

    /**
     * Returns a information of the company.
     *
     * @return The information domain.
     */
    String getInformation();

    /**
     * Sets a new information to the company.
     *
     * @param information a new information to the company.
     */
    void setInformation(final String information);

    /**
     * Returns a sender e-mail of the company.
     *
     * @return The company sender e-mail.
     */
    String getSenderEmail();

    /**
     * Sets a new sender e-mail to the company.
     *
     * @param senderEmail a new sender e-mail to the company.
     */
    void setSenderEmail(final String senderEmail);

    /**
     * Returns a sender password of the company.
     *
     * @return The company sender password.
     */
    String getSenderPass();

    /**
     * Sets a new sender password to the company.
     *
     * @param senderPass a new sender password to the company.
     */
    void setSenderPass(final String senderPass);

    /**
     * Returns a start work time of the company.
     *
     * @return The company start work time.
     */
    String getWorkTimeFrom();

    /**
     * Sets a new start work time to the company.
     *
     * @param workTimeFrom a new start work time to the company.
     */
    void setWorkTimeFrom(final String workTimeFrom);

    /**
     * Returns a finish work time of the company.
     *
     * @return The company finish work time.
     */
    String getWorkTimeTo();

    /**
     * Sets a new finish work time to the company.
     *
     * @param workTimeTo a new finish work time to the company.
     */
    void setWorkTimeTo(final String workTimeTo);

    /**
     * Returns a company contacts.
     *
     * @return The company contacts.
     */
    Contacts getContacts();

    /**
     * Sets a new contacts to the company.
     *
     * @param contacts a new contacts to the company.
     */
    void setContacts(final Contacts contacts);

    /**
     * Returns a company address.
     *
     * @return The company address.
     */
    Address getAddress();

    /**
     * Sets a new address to the company.
     *
     * @param address a new address to the company.
     */
    void setAddress(final Address address);

    /**
     * Returns a domain of the company.
     *
     * @return The company domain.
     */
    CompanyType getType();

    /**
     * Sets a new type to the company.
     *
     * @param type a new logo to the company.
     */
    void setType(final CompanyType type);

    /**
     * Checks whether the company is opened now.
     *
     * @return Returns {@code true} if the company is opened now,
     * otherwise returns {@code false}.
     */
    boolean isOpen();

    /**
     * Initializes the company.
     *
     * @param company a company to copy.
     * @return The this company with new fields.
     */
    Company initialize(final Company company);
}
