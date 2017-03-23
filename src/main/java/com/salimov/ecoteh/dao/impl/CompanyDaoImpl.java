package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.CompanyDao;
import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.enums.CompanyType;
import com.salimov.ecoteh.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class implements a set of standard methods for working {@link Company}
 * objects with a database.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
@Repository
@ComponentScan(basePackages = "com.salimov.ecoteh.repository")
public final class CompanyDaoImpl extends DataDaoImpl<Company> implements CompanyDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Company} objects with a database.
     */
    private final CompanyRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides a set of JPA methods
     *                   for working {@link Company} objects with a database.
     */
    @Autowired
    public CompanyDaoImpl(final CompanyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Returns company with the parameter title from a database.
     *
     * @param title a title of the company to return.
     * @return The company with the parameter title.
     */
    @Override
    public Company getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns company with the parameter url from a database.
     *
     * @param url a URL of the company to return.
     * @return The company with the parameter url.
     */
    @Override
    public Company getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes company with the parameter title from a database.
     *
     * @param title a title of the company to remove.
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes company with the parameter url from a database.
     *
     * @param url a URL of the company to remove.
     */
    @Override
    public void removeByUrl(final String url) {
        this.repository.deleteByUrl(url);
    }

    /**
     * Returns companies with the parameter type from a database.
     *
     * @param type a type of the company to return.
     * @return The companies with parameter type.
     */
    @Override
    public List<Company> getByType(final CompanyType type) {
        return this.repository.findByType(type);
    }
}
