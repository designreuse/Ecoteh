package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CompanyConverter extends ContentConverter<Company, CompanyEntity> {

    CompanyConverter(final Company model) {
        super(model);
    }

    @Override
    public CompanyEntity convert() {
        final CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setId(this.model.getId());
            companyEntity.setValidated(this.model.isValidated());
            companyEntity.setTitle(this.model.getTitle());
            companyEntity.setUrl(this.model.getUrl());
            companyEntity.setDomain(this.model.getDomain());
            companyEntity.setDescription(this.model.getDescription());
            companyEntity.setKeywords(this.model.getKeywords());
            companyEntity.setTagline(this.model.getTagline());
            companyEntity.setInformation(this.model.getInformation());
            companyEntity.setSenderEmail(this.model.getSenderEmail());
            companyEntity.setSenderPass(encrypt(this.model.getSenderPass()));
            companyEntity.setWorkTimeFrom(this.model.getWorkTimeFrom());
            companyEntity.setWorkTimeTo(this.model.getWorkTimeTo());
            companyEntity.setLogoEntity(this.model.getLogo().convert());
            companyEntity.setContactsEntity(this.model.getContacts().convert());
            companyEntity.setAddressEntity(this.model.getAddress().convert());
        return companyEntity;
    }

    private String encrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).encrypt() : "";
    }
}
