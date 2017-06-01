package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.company.CompanyEntity;

import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link CompanyEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface CompanyService extends ContentService<CompanyEntity> {

    /**
     * Updates the main companyEntity.
     *
     * @param companyEntity the main companyEntity to update.
     * @return The updating main companyEntity.
     */
    CompanyEntity updateMainCompany(CompanyEntity companyEntity);

    /**
     * Returns main companyEntity.
     *
     * @return The main companyEntity.
     */
    CompanyEntity getMainCompany();

    /**
     * Removes main companyEntity.
     */
    void removeMain();

    /**
     * Returns all partners companies.
     *
     * @param isValid is get valid companyEntity or not.
     * @return The partners companies.
     */
    List<CompanyEntity> getPartners(boolean isValid);

    /**
     * Returns companyEntity with the category domain.
     *
     * @param domain the domain of a companyEntity to return.
     * @return The object of class {@link CompanyEntity}.
     */
    CompanyEntity getByDomain(String domain);
}
