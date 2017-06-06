package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CompanyConverter extends ContentConverter<Company, CompanyEntity> {

    private final Company company;

    /**
     * Constructor.
     * @param company
     */
    CompanyConverter(final Company company) {
        super(company);
        this.company = company;
    }

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

    private String encrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).encrypt() : "";
    }
}
