package com.salimov.ecoteh.repository;

import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.enums.CompanyType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link Company} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface CompanyRepository extends DataRepository<Company> {

    /**
     * Returns company from a database,
     * which matches the parameter title.
     *
     * @param title a title of the company to return.
     * @return The object of class {@link Company}.
     */
    Company findByTitle(final String title);

    /**
     * Returns company from a database,
     * which matches the parameter url.
     *
     * @param url a URL of the company to return.
     * @return The object of class {@link Company}.
     */
    Company findByUrl(final String url);

    /**
     * Returns companies from a database,
     * which matches the parameter type.
     *
     * @param type a type of the company to return.
     * @return The object of class {@link Company}.
     */
    List<Company> findByType(final CompanyType type);

    /**
     * Removes company from a database,
     * which matches the parameter title.
     *
     * @param title a title of the company to remove.
     */
    void deleteByTitle(final String title);

    /**
     * Removes company from a database,
     * which matches the parameter title.
     *
     * @param url a URL of the company to remove.
     */
    void deleteByUrl(final String url);
}
