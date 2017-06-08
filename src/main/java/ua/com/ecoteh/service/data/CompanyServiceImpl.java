package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyBuilder;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link CompanyEntity} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class CompanyServiceImpl
        extends ContentServiceImpl<Company, CompanyEntity>
        implements CompanyService {

    /**
     * The interface provides a set of standard methods for working
     * {@link CompanyEntity} objects with the database.
     */
    private final CompanyRepository repository;

    /**
     * The interface of the service layer,
     * describes a set of methods for working
     * with objects of the class {@link FileEntity}.
     */
    private final FileService fileService;

    /**
     * Constructor.
     * Initializes a implementations of the interfaces.
     *
     * @param repository  the implementation of the {@link CompanyRepository} interface.
     * @param fileService the implementation of the {@link FileService} interface.
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
     * Returns all or valid partners depending on the incoming value.
     * <pre>
     *     getAll(false) = all partners companies
     *     getAll(true) = all valid partners companies
     * </pre>
     *
     * @param isValid is returns all or valid companies.
     * @return The all or valid partners companies (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Company> getAll(final boolean isValid) {
        return getPartners(isValid);
    }

    /**
     * Updates the main companyEntity.
     *
     * @param company a main companyEntity to update.
     * @return The updating main companyEntity (newer null).
     */
    @Override
    @Transactional
    public Company updateMainCompany(final Company company) {
        final Company mainCompany = getMainCompany();
        final File newLogo = company.getLogo();
        final File oldLogo = mainCompany.getLogo();
        if (isNewLogo(newLogo, oldLogo)) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        return update(company);
    }

    /**
     * Returns main companyEntity.
     * If can`t find main companyEntity then returns new CompanyEntity().
     *
     * @return The main companyEntity (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompany() {
        Company mainCompany;
        try {
            final CompanyEntity mainCompanyEntity = this.repository.findByType(CompanyType.MAIN).get(0);
            mainCompany = convertToModel(mainCompanyEntity);
        } catch (IndexOutOfBoundsException ex) {
            logException(ex);
            final CompanyBuilder builder = Company.getBuilder();
            builder.addType(CompanyType.MAIN);
            mainCompany = builder.build();
        }
        return mainCompany;
    }

    /**
     * Returns all or valid partners depending on the incoming value.
     * <pre>
     *     getPartners(false) = all partners companies
     *     getPartners(true) = all valid partners companies
     * </pre>
     *
     * @param isValid is returns all or valid companies.
     * @return The all or valid partners companies (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Company> getPartners(final boolean isValid) {
        List<CompanyEntity> companyEntities = this.repository.findByType(CompanyType.PARTNER);
        if (isValid) {
            companyEntities = companyEntities.stream()
                    .filter(CompanyEntity::isValidated)
                    .collect(Collectors.toList());
        }
        return convertToModels(companyEntities);
    }

    /**
     * Returns companyEntity with the incoming domain.
     * If a incoming domain is null or empty then throws IllegalArgumentException.
     * If can`t find companyEntity by incoming domain then throws NullPointerException.
     *
     * @param domain a domain of the companyEntity to return.
     * @return The companyEntity with the incoming domain (newer null).
     * @throws IllegalArgumentException Throw exception when parameter domain is blank.
     * @throws NullPointerException     Throw exception when companyEntity with parameter domain
     *                                  is not exist.
     */
    @Override
    @Transactional(readOnly = true)
    public Company getByDomain(final String domain) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(domain)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_DOMAIN_MESSAGE,
                    getClassSimpleName()
            );
        }
        final CompanyEntity companyEntity = this.repository.findByDomain(domain);
        if (isNull(companyEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), domain
            );
        }
        return convertToModel(companyEntity);
    }

    /**
     * Removes companyEntity.
     * Removes companyEntity if it not null
     * and has not type CompanyType.MAIN.
     *
     * @param company the companyEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final Company company) {
        if (isNotMainCompany(company)) {
            super.remove(company);
        }
    }

    /**
     * Removes main companyEntity.
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
     * Return Class object of {@link CompanyEntity} class.
     *
     * @return The Class object of {@link CompanyEntity} class.
     */
    @Override
    protected Class<Company> getModelClass() {
        return Company.class;
    }

    /**
     * Check if a incoming companyEntity is not main companyEntity.
     * <pre>
     *     isNotMainCompany(null) = false
     *
     *     CompanyEntity companyEntity = new CompanyEntity();
     *     companyEntity.setType(CompanyType.MAIN);
     *     isNotMainCompany(companyEntity) = false
     *
     *     companyEntity.setType(CompanyType.PARTNER);
     *     isNotMainCompany(companyEntity) = true
     * </pre>
     *
     * @param company the companyEntity to check.
     * @return true if the companyEntity is not null and
     * it has not MAIN companyEntity type.
     */
    private boolean isNotMainCompany(final Company company) {
        return isNotNull(company) && !company.getType().equals(CompanyType.MAIN);
    }
}
