package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Company;

import java.util.List;

/**
 * The interface of the service layer, describes a set of methods
 * for working with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface CompanyService extends ContentService<Company> {

    /**
     * Updates the main company.
     *
     * @param company the main company to update.
     * @return The updating main company.
     */
    Company updateMainCompany(Company company);

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
     * Returns all partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     */
    List<Company> getPartners(boolean isValid);

    /**
     * Returns company with the category domain.
     *
     * @param domain the domain of a company to return.
     * @return The object of class {@link Company}.
     */
    Company getByDomain(String domain);
}
