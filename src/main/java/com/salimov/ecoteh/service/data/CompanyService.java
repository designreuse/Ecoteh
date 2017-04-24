package com.salimov.ecoteh.service.data;

import com.salimov.ecoteh.entity.Company;

import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface CompanyService extends ContentService<Company> {

    /**
     * Updates the main company.
     *
     * @param company a main company to update.
     * @return The updating main company.
     */
    Company updateMainCompany(final Company company);

    /**
     * Returns main company.
     *
     * @return The main company.
     */
    Company getMainCompany();

    /**
     * Removes main company.
     */
    void removeMain();

    /**
     * Returns partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     */
    List<Company> getPartners(final boolean isValid);

    /**
     * Returns company with the category domain.
     *
     * @param domain a domain of the company to return.
     * @return The object of class {@link Company}.
     */
    Company getByDomain(final String domain);
}
