package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentEntityConverter;
import ua.com.ecoteh.util.encryption.Base64Encryptor;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class CompanyEntityConverter extends ContentEntityConverter<Company> {

    private final CompanyEntity entity;

    CompanyEntityConverter(final CompanyEntity entity) {
        this.entity = entity;
    }

    @Override
    protected CompanyBuilder prepareBuilder() {
        final CompanyBuilder builder = new CompanyBuilder();
        if (isNotNull(this.entity)) {
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
        }
        return null;
    }

    private String decrypt(final String value) {
        return isNotEmpty(value) ? new Base64Encryptor(value).decrypt() : "";
    }
}
