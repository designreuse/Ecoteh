package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.enums.CompanyType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link Company} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface CompanyRepository extends ContentRepository<Company> {

    /**
     * Returns companies from a database,
     * which matches the parameter type.
     *
     * @param type a type of the company to return.
     * @return The objects of class {@link Company}.
     */
    List<Company> findByType(final CompanyType type);

    /**
     * Returns company from a database,
     * which matches the parameter domain.
     *
     * @param domain a domain of the company to return.
     * @return The object of class {@link Company}.
     */
    Company findByDomain(final String domain);
}
