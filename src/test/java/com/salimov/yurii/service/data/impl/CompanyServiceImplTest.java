package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.enums.CompanyType;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.dao.MockDao.getCompanyDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public final class CompanyServiceImplTest
        extends ContentServiceImplTest<Company, Long> {

    private CompanyService service;

    @Before
    public void beforeTest() {
        this.service = new CompanyServiceImpl(getCompanyDao());
    }

    @Test
    public void whenInitAndAddThenReturnSomeCompany() {
        assertNotNull(
                this.service.initAndAdd(
                        TITLE, DOMAIN,
                        TAGLINE, DESCRIPTION, INFORMATION,
                        KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS,
                        URL, true
                )
        );
    }

    @Test
    public void whenInitAndUpdateThenReturnSomeCompany() {
        assertNotNull(
                this.service.initAndUpdate(
                        URL, TITLE, DOMAIN,
                        TAGLINE, DESCRIPTION, INFORMATION,
                        KEYWORDS,
                        PHONE, PHONE, PHONE, EMAIL,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS,
                        URL, true
                )
        );
    }

    @Test
    public void whenInitAndEditMainCompanyThenReturnSomeCompany() {
        assertNotNull(
                this.service.initAndEditMainCompany(
                        TITLE, DOMAIN,
                        TAGLINE, DESCRIPTION, INFORMATION,
                        KEYWORDS, TIME, TIME,
                        PHONE, PHONE, PHONE, EMAIL,
                        EMAIL, PASSWORD,
                        VKONTAKTE, FACEBOOK, TWITTER, SKYPE,
                        ADDRESS, GOOGLE_MAPS,
                        PHOTO_URL, PHOTO_URL, PHOTO_URL
                )
        );
    }

    @Test
    public void whenGetMainCompanyThenReturnMainCompany() {
        assertNotNull(this.service.getMainCompany());
    }

    @Test
    public void whenGetValidPartnersThenReturnNotEmptyList() {
        assertFalse(this.service.getPartners(true).isEmpty());
    }

    @Test
    public void whenGetInvalidPartnersThenReturnNotEmptyList() {
        assertFalse(this.service.getPartners(false).isEmpty());
    }

    @Test
    public void whenRemoveCompanyWithMainTypeThenDoNothing() {
        final Company company = getObject();
        company.setType(CompanyType.MAIN);
        this.service.remove(company);
    }

    @Test
    public void whenRemoveCompanyWithNotMainTypeThenDoIt() {
        final Company company = getObject();
        company.setType(CompanyType.PARTNER);
        this.service.remove(company);
    }

    @Test
    public void whenRemoveMainCompanyThenDoIt() {
        this.service.removeMain();
    }

    @Ignore
    @Override
    protected CompanyService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Company getObject() {
        return getCompany();
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return getCompanies();
    }

    @Ignore
    @Override
    protected Company getInvalidObject() {
        return new Company();
    }
}
