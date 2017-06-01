package ua.com.ecoteh.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.company.CompanyType;
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
public final class CompanyServiceImpl extends ContentServiceImpl<CompanyEntity> implements CompanyService {

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
    public Collection<CompanyEntity> getAll(final boolean isValid) {
        return getPartners(isValid);
    }

    /**
     * Saves a incoming companyEntity and returns saving companyEntity.
     *
     * @param companyEntity a companyEntity to add.
     * @return The new saving companyEntity (newer null).
     */
    @Override
    @Transactional
    public CompanyEntity add(final CompanyEntity companyEntity) {
        if (isNotNull(companyEntity)) {
            companyEntity.setType(CompanyType.PARTNER);
        }
        return super.add(companyEntity);
    }

    /**
     * Updates the main companyEntity.
     *
     * @param companyEntity a main companyEntity to update.
     * @return The updating main companyEntity (newer null).
     */
    @Override
    @Transactional
    public CompanyEntity updateMainCompany(final CompanyEntity companyEntity) {
        final CompanyEntity mainCompanyEntity = getMainCompany();
        final FileEntity newLogo = companyEntity.getLogoEntity();
        final FileEntity oldLogo = mainCompanyEntity.getLogoEntity();
        if (isNewLogo(newLogo, oldLogo)) {
            this.fileService.deleteFile(oldLogo.getUrl());
        }
        copy(companyEntity, mainCompanyEntity);
        return update(mainCompanyEntity);
    }

    /**
     * Returns main companyEntity.
     * If can`t find main companyEntity then returns new CompanyEntity().
     *
     * @return The main companyEntity (newer null).
     */
    @Override
    @Transactional(readOnly = true)
    public CompanyEntity getMainCompany() {
        CompanyEntity mainCompanyEntity;
        try {
            mainCompanyEntity = this.repository.findByType(CompanyType.MAIN).get(0);
        } catch (IndexOutOfBoundsException ex) {
            logException(ex);
            mainCompanyEntity = new CompanyEntity();
        }
        return mainCompanyEntity;
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
    public List<CompanyEntity> getPartners(final boolean isValid) {
        List<CompanyEntity> companies = this.repository.findByType(CompanyType.PARTNER);
        if (isValid) {
            companies = companies.stream()
                    .filter(CompanyEntity::isValidated)
                    .collect(Collectors.toList());
        }
        return companies;
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
    public CompanyEntity getByDomain(final String domain)
            throws IllegalArgumentException, NullPointerException {
        if (isEmpty(domain)) {
            throw getIllegalArgumentException(
                    ExceptionMessage.BLANK_DOMAIN_MESSAGE,
                    getClassSimpleName()
            );
        }
        CompanyEntity companyEntity = this.repository.findByDomain(domain);
        if (isNull(companyEntity)) {
            throw getNullPointerException(
                    ExceptionMessage.FINDING_BY_NUMBER_OBJECT_IS_NULL_MESSAGE,
                    getClassSimpleName(), domain
            );
        }
        return companyEntity;
    }

    /**
     * Removes companyEntity.
     * Removes companyEntity if it not null
     * and has not type CompanyType.MAIN.
     *
     * @param companyEntity the companyEntity to remove.
     */
    @Override
    @Transactional
    public void remove(final CompanyEntity companyEntity) {
        if (isNotMainCompany(companyEntity)) {
            super.remove(companyEntity);
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
     * Copies the object "from" to object "to".
     * Incoming objects must be not null.
     *
     * @param from the copied object
     * @param to   the object to copy
     */
    @Override
    protected void copy(final CompanyEntity from, final CompanyEntity to) {
        to.initialize(from);
    }

    /**
     * Return Class object of {@link CompanyEntity} class.
     *
     * @return The Class object of {@link CompanyEntity} class.
     */
    @Override
    protected Class<CompanyEntity> getModelClass() {
        return CompanyEntity.class;
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
     * @param companyEntity the companyEntity to check.
     * @return true if the companyEntity is not null and
     * it has not MAIN companyEntity type.
     */
    private boolean isNotMainCompany(final CompanyEntity companyEntity) {
        return isNotNull(companyEntity) && !companyEntity.getType().equals(CompanyType.MAIN);
    }
}
