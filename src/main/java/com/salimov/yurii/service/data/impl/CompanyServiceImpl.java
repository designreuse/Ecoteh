package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.entity.interfaces.IModel;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Company
 * @see CompanyService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see CompanyDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class CompanyServiceImpl
        extends ContentServiceImpl<Company, Long>
        implements CompanyService {

    /**
     * The interface provides a set of standard methods for working
     * {@link Company} objects with the database.
     *
     * @see CompanyDao
     * @see Company
     */
    private final CompanyDao dao;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation
     *            of the {@link CompanyDao} interface.
     * @see CompanyDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyServiceImpl(
            final CompanyDao dao
    ) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Returns all or valid partners
     * depending on the parameter value.
     *
     * @param valid is returns all or valid companies.
     * @return The all models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Company> getAll(final boolean valid) {
        return getPartners(valid);
    }

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
     * @see File
     */
    @Override
    @Transactional
    public Company initAndAdd(
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
    ) {
        final Company company = new Company();
        company.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                null, null,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps,
                logoUrl, null
        );
        company.setType(
                CompanyType.PARTNER
        );
        company.setValidated(isValid);
        return add(company);
    }

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
     * @param logoUrl       a new logo Url to the company.
     * @param isValid       a validated of the article.
     * @return The updating company with parameter id.
     * @see Company
     * @see File
     */
    @Override
    @Transactional
    public Company initAndUpdate(
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
    ) {
        final Company company = getByUrl(url, false);
        company.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                null, null,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps,
                logoUrl, null
        );
        company.setValidated(isValid);
        return update(company);
    }

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
     * @param slides        a slides URL to the main company.
     * @return The updating main company.
     */
    @Override
    @Transactional
    public Company initAndEditMainCompany(
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
    ) {
        final Company mainCompany = getMainCompany();
        mainCompany.initialize(
                title, domain,
                tagline, description, information,
                mobilePhone, landlinePhone, fax, email,
                senderEmail, senderPass,
                vkontakte, facebook, twitter, skype,
                address, keywords, googleMaps,
                logoUrl, faviconUrl
        );
        mainCompany.setSlides(slides);
        mainCompany.setWorkTimeFrom(workTimeFrom);
        mainCompany.setWorkTimeTo(workTimeTo);
        return update(mainCompany);
    }

    /**
     * Returns main company.
     *
     * @return The main company.
     * @throws NullPointerException Throws exception if main company is absent.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompany() throws NullPointerException {
        try {
            final Company mainCompany =
                    this.dao.getByType(
                            CompanyType.MAIN
                    ).get(0);
            if (!Company.isValidated(mainCompany)) {
                throw new NullPointerException(
                        "Information about main company is absent!"
                );
            }
            return mainCompany;
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new NullPointerException(
                    "Information about main company is absent!"
            );
        }
    }

    /**
     * Returns partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public List<Company> getPartners(final boolean isValid) {
        List<Company> companies = this.dao.getByType(
                CompanyType.PARTNER
        );
        if (isValid) {
            companies = companies.stream()
                    .filter(
                            IModel::isValidated
                    )
                    .collect(
                            Collectors.toList()
                    );
        }
        return companies;
    }

    /**
     * Removes company.
     * Removes company if it not {@code null}
     * and has not type {@code MAIN}.
     *
     * @param company the company to remove.
     * @see Company
     */
    @Override
    @Transactional
    public void remove(final Company company) {
        if ((
                company != null
        ) && (
                !company.getType().equals(
                        CompanyType.MAIN
                )
        )) {
            super.remove(company);
        }
    }

    /**
     * Removes main company.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeMain() {
        super.remove(
                getMainCompany()
        );
    }

    /**
     * Removes all partners companies.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(
                getPartners(false)
        );
    }

    /**
     * Return Class object of {@link Company} class.
     *
     * @return The Class object of {@link Company} class.
     */
    @Override
    protected Class<Company> getModelClass() {
        return Company.class;
    }
}
