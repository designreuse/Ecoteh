package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;

import java.util.Collection;

/**
 * The interface provides a set of JPA methods
 * for working {@link CompanyEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CompanyEntity
 */
public interface CompanyRepository extends ContentRepository<CompanyEntity> {

    /**
     * Returns all companies from a database,
     * which matches the parameter type.
     *
     * @param type the type of a company to return.
     * @return The objects of class {@link CompanyEntity}.
     * @see CompanyType
     */
    Collection<CompanyEntity> findAllByType(CompanyType type);

    /**
     * Returns last company from a database,
     * which matches the parameter type.
     *
     * @param type the type of a company to return.
     * @return The object of class {@link CompanyEntity}.
     * @see CompanyType
     */
    CompanyEntity findLastByType(CompanyType type);

    /**
     * Returns company from a database,
     * which matches the parameter domain.
     *
     * @param domain the domain of a company to return.
     * @return The object of class {@link CompanyEntity}.
     */
    CompanyEntity findByDomain(String domain);
}
