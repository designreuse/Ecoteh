package com.salimov.yurii.service.data.interfaces;

import com.salimov.yurii.entity.Company;

import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Company
 * @see ContentService
 * @see DataService
 */
public interface CompanyService
        extends ContentService<Company, Long> {

    /**
     * Initializes, saves and returns a new company.
     *
     * @param title         a title of the new company.
     * @param domain        a domain of the new company.
     * @param tagline       a tagline of the new company.
     * @param description   a description of the new company.
     * @param information   a information of the new company.
     * @param keywords      a keywords of the new company.
     * @param mobilePhone   a mobile phone of the new company.
     * @param landlinePhone a landline phone of the new company.
     * @param fax           a fax of the new company.
     * @param email         a e-mail of the new company.
     * @param vkontakte     a vkontakte url of the new company.
     * @param facebook      a facebook url of the new company.
     * @param twitter       a twitter url of the new company.
     * @param skype         a skype username of the new company.
     * @param address       a address of the new company.
     * @param googleMaps    a google maps url of the new company.
     * @param logoUrl       a logo URL to the new company.
     * @param isValid       a validated of the new company.
     * @return The new saving company.
     * @see Company
     */
    Company initAndAdd(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String googleMaps,
            final String logoUrl,
            final boolean isValid
    );

    /**
     * Initializes, updates and returns object of class {@link Company}.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url           a url of the company to update.
     * @param title         a new title to the company.
     * @param domain        a new domain to the company.
     * @param tagline       a new tagline to the company.
     * @param description   a new description to the company.
     * @param information   a new information to the company.
     * @param keywords      a new keywords to the company.
     * @param mobilePhone   a new mobile phone to the company.
     * @param landlinePhone a new landline phone to the company.
     * @param fax           a new fax to the company.
     * @param email         a new e-mail to the company.
     * @param vkontakte     a new vkontakte url to the company.
     * @param facebook      a new facebook url to the company.
     * @param twitter       a new twitter url to the company.
     * @param skype         a new skype username to the company.
     * @param address       a new address to the company.
     * @param googleMaps    a new google maps url to the company.
     * @param logoUrl       a new logo URL to the company.
     * @param isValid       a validated of the article.
     * @return The updating company with parameter id.
     * @see Company
     */
    Company initAndUpdate(
            final String url,
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String googleMaps,
            final String logoUrl,
            final boolean isValid
    );

    /**
     * Updates the main company.
     *
     * @param title         a new title to the main company.
     * @param domain        a new domain to the main company.
     * @param tagline       a new tagline to the main company.
     * @param description   a new description to the main company.
     * @param information   a new information to the main company.
     * @param keywords      a new keywords to the main company.
     * @param workTimeFrom  a new start work time to the main company.
     * @param workTimeTo    a new finish work time to the main company.
     * @param mobilePhone   a new mobile phone to the main company.
     * @param landlinePhone a new landline phone to the main company.
     * @param fax           a new fax to the main company.
     * @param email         a new e-mail to the main company.
     * @param senderEmail   a new sender e-mail to the main company.
     * @param senderPass    a new sender password to the main company.
     * @param vkontakte     a new vkontakte url to the main company.
     * @param facebook      a new facebook url to the main company.
     * @param twitter       a new twitter url to the main company.
     * @param skype         a new skype username to the main company.
     * @param address       a new address to the main company.
     * @param googleMaps    a new google maps url to the main company.
     * @param logoUrl       a new logo URL to the main company.
     * @param faviconUrl    a new favicon URL to the main company.
     * @param slides        a slides to the main company.
     * @return The updating main company.
     */
    Company initAndEditMainCompany(
            final String title,
            final String domain,
            final String tagline,
            final String description,
            final String information,
            final String keywords,
            final String workTimeFrom,
            final String workTimeTo,
            final String mobilePhone,
            final String landlinePhone,
            final String fax,
            final String email,
            final String senderEmail,
            final String senderPass,
            final String vkontakte,
            final String facebook,
            final String twitter,
            final String skype,
            final String address,
            final String googleMaps,
            final String logoUrl,
            final String faviconUrl,
            final String slides
    );

    /**
     * Returns main company.
     *
     * @return The main company.
     * @see Company
     */
    Company getMainCompany();

    /**
     * Removes main company.
     *
     * @see Company
     */
    void removeMain();

    /**
     * Returns partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     * @see Company
     */
    List<Company> getPartners(final boolean isValid);
}
