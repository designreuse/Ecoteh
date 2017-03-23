package com.salimov.ecoteh.dao.interfaces;

import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.enums.CompanyType;

import java.util.List;

/**
 * The interface provides a set of standard methods for working {@link Company}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface CompanyDao extends ContentDao<Company> {

    /**
     * Returns companies with the parameter type from a database.
     *
     * @param type a type of the company to return.
     * @return The companies with parameter type.
     */
    List<Company> getByType(final CompanyType type);
}
