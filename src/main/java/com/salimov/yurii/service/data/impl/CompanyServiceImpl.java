package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.entity.interfaces.IModel;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Company
 * @see CompanyService
 * @see ContentServiceImpl
 * @see DataServiceImpl
 * @see CompanyDao
 */
@Service
@ComponentScan(
        basePackages = {
                "com.salimov.yurii.dao",
                "com.salimov.yurii.service.data"
        }
)
public final class CompanyServiceImpl extends ContentServiceImpl<Company> implements CompanyService {

    /**
     * The interface provides a set of standard methods for working
     * {@link Company} objects with the database.
     *
     * @see CompanyDao
     * @see Company
     */
    private final CompanyDao dao;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao a implementation of the {@link CompanyDao} interface.
     * @see CompanyDao
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyServiceImpl(final CompanyDao dao) {
        super(dao);
        this.dao = dao;
    }

    /**
     * Returns all or valid partners
     * depending on the parameter value.
     *
     * @param valid is returns all or valid companies.
     * @return The all models.
     * @see Model
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Company> getAll(final boolean valid) {
        return getPartners(valid);
    }

    /**
     * Initializes, saves and returns a new company.
     *
     * @param company
     * @return The new saving company.
     * @see Company
     * @see File
     */
    @Override
    @Transactional
    public Company add(final Company company) {
        if (company != null) {
            company.setType(CompanyType.PARTNER);
        }
        return super.add(company);
    }

    /**
     * Initializes, updates and returns object of class {@link Company}.
     * Returns {@code null} if id is {@code null}.
     *
     * @param url     a url of the company to update.
     * @param company
     * @return The updating company with parameter id.
     * @see Company
     * @see File
     */
    @Override
    @Transactional
    public Company update(
            final String url,
            final Company company
    ) {
        return update(
                getByUrl(url, false)
                        .initialize(company)
        );
    }

    /**
     * Updates the main company.
     *
     * @param company
     * @return The updating main company.
     */
    @Override
    @Transactional
    public Company updateMainCompany(final Company company) {
        final Company mainCompany = getMainCompany();
        mainCompany.initialize(company);
        mainCompany.setType(CompanyType.MAIN);
        return update(mainCompany);
    }

    /**
     * Returns main company.
     *
     * @return The main company.
     * @throws NullPointerException Throws exception if main company is absent.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompany() throws NullPointerException {
        try {
            return this.dao.getByType(CompanyType.MAIN).get(0);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            throw new NullPointerException("Information about main company is absent!");
        }
    }

    /**
     * Returns partners companies.
     *
     * @param isValid is get valid company or not.
     * @return The partners companies.
     * @see Company
     */
    @Override
    @Transactional(readOnly = true)
    public List<Company> getPartners(final boolean isValid) {
        List<Company> companies = this.dao.getByType(CompanyType.PARTNER);
        if (isValid) {
            companies = companies.stream()
                    .filter(IModel::isValidated)
                    .collect(Collectors.toList());
        }
        return companies;
    }

    /**
     * Removes company.
     * Removes company if it not {@code null} and has not type {@code MAIN}.
     *
     * @param company the company to remove.
     * @see Company
     */
    @Override
    @Transactional
    public void remove(final Company company) {
        if ((company != null) && !company.getType().equals(CompanyType.MAIN)) {
            super.remove(company);
        }
    }

    /**
     * Removes main company.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeMain() {
        super.remove(getMainCompany());
    }

    /**
     * Removes all partners companies.
     *
     * @see Company
     */
    @Override
    @Transactional
    public void removeAll() {
        remove(getPartners(false));
    }

    /**
     * Return Class object of {@link Company} class.
     *
     * @return The Class object of {@link Company} class.
     */
    @Override
    protected Class<Company> getModelClass() {
        return Company.class;
    }
}
