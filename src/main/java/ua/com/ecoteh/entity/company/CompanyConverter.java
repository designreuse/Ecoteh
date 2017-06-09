package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods
 * for converting companies to company entities.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see Company
 * @see CompanyEntity
 */
final class CompanyConverter extends ContentConverter<Company, CompanyEntity> {

    /**
     * The company for converting to company entity.
     */
    private final Company company;

    /**
     * The instance of the interface for data encryption.
     */
    private Encryptor encryptor;

    /**
     * Constructor.
     *
     * @param company the company for converting to company entity.
     */
    CompanyConverter(final Company company) {
        super(company);
        this.company = company;
    }

    /**
     * Converts the company and returns a new company entity.
     *
     * @return The converted company entity (newer null).
     */
    @Override
    public CompanyEntity convert() {
        final CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(this.company.getId());
        companyEntity.setValidated(this.company.isValidated());
        companyEntity.setTitle(this.company.getTitle());
        companyEntity.setUrl(this.company.getUrl());
        companyEntity.setDomain(this.company.getDomain());
        companyEntity.setDescription(this.company.getDescription());
        companyEntity.setKeywords(this.company.getKeywords());
        companyEntity.setTagline(this.company.getTagline());
        companyEntity.setInformation(this.company.getInformation());
        companyEntity.setSenderEmail(this.company.getSenderEmail());
        companyEntity.setSenderPass(encrypt(this.company.getSenderPass()));
        companyEntity.setWorkTimeFrom(this.company.getWorkTimeFrom());
        companyEntity.setWorkTimeTo(this.company.getWorkTimeTo());
        companyEntity.setLogoEntity(this.company.getLogo().convert());
        companyEntity.setContactsEntity(this.company.getContacts().convert());
        companyEntity.setAddressEntity(this.company.getAddress().convert());
        return companyEntity;
    }

    /**
     * Encrypts the incoming value and returns it.
     * <pre>
     *     encrypt(null) - empty string
     *     encrypt("") - empty string
     *     encrypt(" ") - empty string
     *     encrypt("   ") - empty string
     *     encrypt("value") - some encrypted value
     * </pre>
     *
     * @param value the value to encrypt.
     * @return the encrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    private String encrypt(final String value) {
        final Encryptor encryptor = getEncryptor();
        return encryptor.encrypt(value);
    }

    /**
     * Creates and returns the object for data encryption.
     *
     * @return The object for data encryption.
     * @see Base64Encryptor
     */
    private Encryptor getEncryptor() {
        if (isNull(this.encryptor)) {
            this.encryptor = new Base64Encryptor();
        }
        return this.encryptor;
    }
}
