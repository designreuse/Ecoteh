package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The class implements a set of standard methods for working {@link Company}
 * objects with a database.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 * @see CompanyDao
 * @see Company
 * @see CompanyRepository
 * @see DataDaoImpl
 */
@Repository
@ComponentScan(basePackages = "com.salimov.yurii.repository")
public final class CompanyDaoImpl
        extends DataDaoImpl<Company, Long>
        implements CompanyDao {

    /**
     * The interface provides a set of JPA methods
     * for working {@link Company} objects with a database.
     *
     * @see CompanyRepository
     */
    private final CompanyRepository repository;

    /**
     * Constructor.
     *
     * @param repository a implementation of the interface provides
     *                   a set of JPA methods for working {@link Company}
     *                   objects with a database.
     * @see CompanyRepository
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
     * @see Company
     */
    @Override
    public Company getByTitle(final String title) {
        return this.repository.findByTitle(title);
    }

    /**
     * Returns company with the parameter url from a database.
     *
     * @param url a url of the company to return.
     * @return The company with the parameter url.
     * @see Company
     */
    @Override
    public Company getByUrl(final String url) {
        return this.repository.findByUrl(url);
    }

    /**
     * Removes company with the parameter title from a database.
     *
     * @param title a title of the company to remove.
     * @see Company
     */
    @Override
    public void removeByTitle(final String title) {
        this.repository.deleteByTitle(title);
    }

    /**
     * Removes company with the parameter url from a database.
     *
     * @param url a url of the company to remove.
     * @see Company
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
     * @see Company
     * @see Company.Type
     */
    @Override
    public List<Company> getByType(final Company.Type type) {
        return this.repository.findByType(type);
    }
}
