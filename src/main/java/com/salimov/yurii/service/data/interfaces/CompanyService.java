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
public interface CompanyService extends ContentService<Company> {

    /**
     * Initializes, updates and returns object of class {@link Company}.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url     a url of the company to update.
     * @param company a company to update.
     * @return The updating company with parameter id.
     * @see Company
     */
    Company update(
            final String url,
            final Company company
    );

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
