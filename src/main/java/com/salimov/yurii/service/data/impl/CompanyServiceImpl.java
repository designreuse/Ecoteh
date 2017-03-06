package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import com.salimov.yurii.entity.File;
import com.salimov.yurii.entity.Model;
import com.salimov.yurii.entity.interfaces.IModel;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import com.salimov.yurii.service.data.interfaces.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
     */
    private final CompanyDao dao;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link File}.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param dao         a implementation of the {@link CompanyDao} interface.
     * @param fileService a implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyServiceImpl(
            final CompanyDao dao,
            final FileService fileService
    ) {
        super(dao, fileService);
        this.dao = dao;
        this.fileService = fileService;
    }

    /**
     * Returns all or valid partners
     * depending on the parameter value.
     *
     * @param valid is returns all or valid companies.
     * @return The all models.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Company> getAll(final boolean valid) {
        return getPartners(valid);
    }

    /**
     * Initializes, saves and returns a new company.
     *
     * @param company a company to add.
     * @return The new saving company.
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
     * Updates the main company.
     *
     * @param company a main company to update.
     * @return The updating main company.
     */
    @Override
    @Transactional
    public Company updateMainCompany(final Company company) {
        final Company mainCompany = getMainCompany();
        final File newLogo = company.getLogo();
        final File oldLogo = mainCompany.getLogo();
        if (!newLogo.equals(oldLogo) && isNotBlank(newLogo.getUrl())) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        mainCompany.initialize(company);
        return update(mainCompany);
    }

    /**
     * Returns main company.
     *
     * @return The main company.
     * @throws NullPointerException Throws exception if main company is absent.
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
     * Removes company if it not {@code null} and has not type {@code CompanyType.MAIN}.
     *
     * @param company the company to remove.
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
     */
    @Override
    @Transactional
    public void removeMain() {
        super.remove(getMainCompany());
    }

    /**
     * Removes all partners companies.
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
