package com.salimov.yurii.dao.interfaces;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.enums.CompanyType;

import java.util.List;

/**
 * The interface provides a set of standard methods for working {@link Company}
 * objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see com.salimov.yurii.dao.impl.CompanyDaoImpl
 * @see ContentDao
 * @see Company
 */
public interface CompanyDao
        extends ContentDao<Company, Long> {

    /**
     * Returns companies with the parameter type from a database.
     *
     * @param type a type of the company to return.
     * @return The companies with parameter type.
     * @see Company
     * @see CompanyType
     */
    List<Company> getByType(final CompanyType type);
}
