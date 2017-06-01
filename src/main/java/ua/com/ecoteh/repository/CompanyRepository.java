package ua.com.ecoteh.repository;

import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;

import java.util.List;

/**
 * The interface provides a set of JPA methods
 * for working {@link CompanyEntity} objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface CompanyRepository extends ContentRepository<CompanyEntity> {

    /**
     * Returns all companies from a database,
     * which matches the parameter type.
     *
     * @param type the type of a company to return.
     * @return The objects of class {@link CompanyEntity}.
     */
    List<CompanyEntity> findByType(CompanyType type);

    /**
     * Returns company from a database,
     * which matches the parameter domain.
     *
     * @param domain the domain of a company to return.
     * @return The object of class {@link CompanyEntity}.
     */
    CompanyEntity findByDomain(String domain);
}
