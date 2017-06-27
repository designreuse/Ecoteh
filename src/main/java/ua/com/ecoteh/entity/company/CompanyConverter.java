package ua.com.ecoteh.entity.company;

import ua.com.ecoteh.entity.content.ContentConverter;

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
        final CompanyEntity entity = new CompanyEntity();
        entity.setId(this.company.getId());
        entity.setValidated(this.company.isValidated());
        entity.setTitle(this.company.getTitle());
        entity.setUrl(this.company.getUrl());
        entity.setText(this.company.getText());
        entity.setDomain(this.company.getDomain());
        entity.setDescription(this.company.getDescription());
        entity.setKeywords(this.company.getKeywords());
        entity.setTagline(this.company.getTagline());
        entity.setSenderEmail(this.company.getSenderEmail());
        entity.setSenderPass(encrypt(this.company.getSenderPass()));
        entity.setWorkTimeFrom(this.company.getWorkTimeFrom());
        entity.setWorkTimeTo(this.company.getWorkTimeTo());
        entity.setLogoEntity(this.company.getLogo().convert());
        entity.setContactsEntity(this.company.getContacts().convert());
        entity.setAddressEntity(this.company.getAddress().convert());
        entity.setType(this.company.getType());
        return entity;
    }
}
