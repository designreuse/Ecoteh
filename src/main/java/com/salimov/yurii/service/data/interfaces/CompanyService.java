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
public interface CompanyService extends ContentService<Company, Long> {

    /**
     * Initializes, saves and returns a new company.
     *
     * @param company
     * @return The new saving company.
     * @see Company
     */
    Company initAndAdd(final Company company);

    /**
     * Initializes, updates and returns object of class {@link Company}.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url           a url of the company to update.
     * @param company
     * @return The updating company with parameter id.
     * @see Company
     */
    Company initAndUpdate(
            final String url,
            final Company company
    );

    /**
     * Updates the main company.
     *
     * @return The updating main company.
     */
    Company initAndEditMainCompany(final Company company);

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
