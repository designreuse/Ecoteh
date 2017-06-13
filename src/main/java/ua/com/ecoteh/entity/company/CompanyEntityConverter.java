package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentEntityConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNull;

/**
 * The class implements a set of methods
 * for converting company entities to companies.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @see CompanyEntity
 * @see Company
 */
final class CompanyEntityConverter extends ContentEntityConverter<CompanyEntity, Company> {

    /**
     * The company entity for converting to company.
     */
    private final CompanyEntity entity;

    /**
     * The instance of the interface for data encryption.
     */
    private Encryptor encryptor;

    /**
     * Constructor.
     *
     * @param entity the company entity for converting to company.
     */
    CompanyEntityConverter(final CompanyEntity entity) {
        super(entity);
        this.entity = entity;
    }

    /**
     * Prepares and returns a article builder for creating
     * a new converted company.
     *
     * @return the prepared company builder.
     */
    @Override
    protected CompanyBuilder prepareBuilder() {
        final CompanyBuilder builder = Company.getBuilder();
        builder.addId(this.entity.getId())
                .addValidated(this.entity.isValidated())
                .addTitle(this.entity.getTitle())
                .addUrl(this.entity.getUrl())
                .addDomain(this.entity.getDomain())
                .addTagline(this.entity.getTagline())
                .addInformation(this.entity.getInformation())
                .addDescription(this.entity.getDescription())
                .addKeywords(this.entity.getKeywords())
                .addSenderEmail(this.entity.getSenderEmail())
                .addSenderPass(decrypt(this.entity.getSenderPass()))
                .addWorkTimeFrom(this.entity.getWorkTimeFrom())
                .addWorkTimeTo(this.entity.getWorkTimeTo())
                .addType(this.entity.getType())
                .addLogo(this.entity.getLogoEntity().convert())
                .addContacts(this.entity.getContactsEntity().convert())
                .addAddress(this.entity.getAddressEntity().convert());
        return builder;
    }

    /**
     * Decrypts the incoming value and returns it.
     * <pre>
     *     decrypt(null) - empty string
     *     decrypt("") - empty string
     *     decrypt(" ") - empty string
     *     decrypt("   ") - empty string
     *     decrypt("value") - some decrypted value
     * </pre>
     *
     * @param value the value to decrypt.
     * @return the decrypted value or empty string (newer null).
     * @see Base64Encryptor
     */
    private String decrypt(final String value) {
        final Encryptor encryptor = getEncryptor();
        return encryptor.decrypt(value);
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
