package com.salimov.yurii.repository;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.enums.CompanyType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link Company} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see DataRepository
 * @see Company
 */
public interface CompanyRepository
        extends DataRepository<Company, Long> {

    /**
     * Returns company from a database,
     * which matches the parameter title.
     *
     * @param title a title of the company to return.
     * @return The object of class {@link Company}.
     * @see Company
     */
    Company findByTitle(final String title);

    /**
     * Returns company from a database,
     * which matches the parameter url.
     *
     * @param url a title of the company to return.
     * @return The object of class {@link Company}.
     * @see Company
     */
    Company findByUrl(final String url);

    /**
     * Returns companies from a database,
     * which matches the parameter type.
     *
     * @param type a type of the company to return.
     * @return The object of class {@link Company}.
     * @see Company
     * @see CompanyType
     */
    List<Company> findByType(final CompanyType type);

    /**
     * Removes company from a database,
     * which matches the parameter title.
     *
     * @param title a title of the company to remove.
     * @see Company
     */
    void deleteByTitle(final String title);

    /**
     * Removes company from a database,
     * which matches the parameter title.
     *
     * @param url a title of the company to remove.
     * @see Company
     */
    void deleteByUrl(final String url);
}
