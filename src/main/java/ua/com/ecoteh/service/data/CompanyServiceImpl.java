package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyBuilder;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.exception.ExceptionMessage;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

import static ua.com.ecoteh.util.validator.ObjectValidator.*;

/**
 * The class of the service layer, implements a set of methods for working
 * with objects of the {@link Company} class.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Company
 * @see CompanyEntity
 */
@Service
@ComponentScan(
        basePackages = {
                "ua.com.ecoteh.repository",
                "ua.com.ecoteh.service.data"
        }
)
public final class CompanyServiceImpl extends ContentServiceImpl<Company, CompanyEntity> implements CompanyService {

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
     * Updates the main company.
     *
     * @param company a main company to update.
     * @return The updating main company (newer null).
     * @throws IllegalArgumentException Throw exception when input company is null.
     */
    @Override
    @Transactional
    public Company updateMainCompany(final Company company) throws IllegalArgumentException {
        if (isNull(company)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.INCOMING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        final Company mainCompany = getMainCompany();
        final CompanyEntity companyEntity = updateContent(company, mainCompany);
        final CompanyEntity savingEntity = this.repository.save(companyEntity);
        if (isNull(savingEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.SAVING_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName()
            );
        }
        return convertToModel(savingEntity);
    }

    /**
     * Returns main company.
     * If can`t find main company then returns new company.
     *
     * @return The main company (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public Company getMainCompany() {
        Company mainCompany;
        try {
            final CompanyEntity mainCompanyEntity = this.repository.findLastByType(CompanyType.MAIN);
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
        Collection<CompanyEntity> entities = this.repository.findAllByType(CompanyType.PARTNER);
        if (isValid) {
            entities = entities.stream()
                    .filter(CompanyEntity::isValidated)
                    .collect(Collectors.toList());
        }
        return convertToModels(entities);
    }

    /**
     * Returns company with the incoming domain.
     * If a incoming domain is null or empty then throws IllegalArgumentException.
     * If can`t find company by incoming domain then throws NullPointerException.
     *
     * @param domain a domain of the company to return.
     * @return The company with the incoming domain (newer null).
     * @throws IllegalArgumentException Throw exception when parameter domain is blank.
     * @throws NullPointerException     Throw exception when company with parameter domain
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
        final CompanyEntity entity = this.repository.findByDomain(domain);
        if (isNull(entity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), domain
            );
        }
        return convertToModel(entity);
    }

    /**
     * Removes company.
     * Removes company if it not null
     * and has not type CompanyType.MAIN.
     *
     * @param company the company to remove.
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean remove(final Company company) {
        boolean result = isNotMainCompany(company);
        if (result) {
            result = super.remove(company);
        }
        return result;
    }

    /**
     * Removes main company.
     *
     * @return true if model is deleted, false otherwise.
     */
    @Override
    @Transactional
    public boolean removeMain() {
        final Company main = getMainCompany();
        return super.remove(main);
    }

    /**
     * Removes all partners companies.
     */
    @Override
    @Transactional
    public void removeAll() {
        Collection<Company> partners = getPartners(false);
        remove(partners);
    }

    /**
     * Return Class object of {@link Company} class.
     *
     * @return The Class object of {@link Company} class.
     */
    @Override
    Class<Company> getModelClass() {
        return Company.class;
    }

    /**
     * Check if a incoming company is not main company.
     * <pre>
     *     isNotMainCompany(null) = false
     *
     *     if the incoming company has MAIN type.
     *     isNotMainCompany(company) = false
     *
     *     if the incoming company has PARTNER type.
     *     isNotMainCompany(company) = true
     * </pre>
     *
     * @param company the company to check.
     * @return true if the company is not null and
     * it has not MAIN company type.
     */
    private boolean isNotMainCompany(final Company company) {
        final boolean result;
        if (isNotNull(company)) {
            final CompanyType companyType = company.getType();
            result = !companyType.equals(CompanyType.MAIN);
        } else {
            result = false;
        }
        return result;
    }
}
