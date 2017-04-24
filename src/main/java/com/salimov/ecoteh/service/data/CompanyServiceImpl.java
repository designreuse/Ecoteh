package com.salimov.ecoteh.service.data;

import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.entity.File;
import com.salimov.ecoteh.entity.Model;
import com.salimov.ecoteh.enums.CompanyType;
import com.salimov.ecoteh.repository.CompanyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
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
                "com.salimov.ecoteh.repository",
                "com.salimov.ecoteh.service.data"
        }
)
public final class CompanyServiceImpl extends ContentServiceImpl<Company> implements CompanyService {

    /**
     * The object for logging information.
     */
    private static final Logger LOGGER = Logger.getLogger(CompanyServiceImpl.class);

    /**
     * The interface provides a set of standard methods for working
     * {@link Company} objects with the database.
     */
    private final CompanyRepository repository;

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
     * @param repository  a implementation of the {@link CompanyRepository} interface.
     * @param fileService a implementation of the {@link FileService} interface.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CompanyServiceImpl(
            final CompanyRepository repository,
            final FileService fileService
    ) {
        super(repository, fileService);
        this.repository = repository;
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
        Company mainCompany;
        try {
            mainCompany = this.repository.findByType(CompanyType.MAIN).get(0);
        } catch (IndexOutOfBoundsException ex) {
            LOGGER.error(ex.getMessage(), ex);
            mainCompany = new Company();
        }
        return mainCompany;
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
        List<Company> companies = this.repository.findByType(CompanyType.PARTNER);
        if (isValid) {
            companies = companies.stream()
                    .filter(Model::isValidated)
                    .collect(Collectors.toList());
        }
        return companies;
    }

    /**
     * Returns article with the category domain.
     *
     * @param domain a domain of the company to return.
     * @return The object of class {@link Company}.
     * @throws IllegalArgumentException Throw exception when parameter domain is blank.
     * @throws NullPointerException     Throw exception when company with parameter domain
     *                                  is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public Company getByDomain(final String domain) throws IllegalArgumentException {
        if (isBlank(domain)) {
            throw new IllegalArgumentException("Company domain is blank!");
        }
        Company company = this.repository.findByDomain(domain);
        if (company == null) {
            throw new NullPointerException("Can`t find company by domain \"" + domain + "\"!");
        }
        return company;
    }

    /**
     * Removes company.
     * Removes company if it not null and has not type CompanyType.MAIN.
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

    /**
     * Copies the object "from" to object "to".
     *
     * @param from a copied object
     * @param to   a object to copy
     */
    @Override
    protected void copy(final Company from, final Company to) {
        to.initialize(from);
    }
}
