package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.mocks.service.data.MockServices;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public class MockCompanyServiceTest extends MockContentServiceTest<Company> {

    private CompanyService service;

    @Before
    public void initCompanyService() {
        this.service = MockServices.getCompanyService();
    }

    /*@Test
    public void whenInitAndAddCompanyThenReturnThisCompany() {
        final Photo photo = MockEntity.getFile();
        Company company = this.service.initAndAdd(MockConstants.TITLE, MockConstants.DOMAIN, MockConstants.TAGLINE, MockConstants.DESCRIPTION, MockConstants.INFORMATION, MockConstants.ADVANTAGES,
                MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL, MockConstants.EMAIL, MockConstants.PASSWORD, MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE, MockConstants.ADDRESS, MockConstants.KEYWORDS,
                MockConstants.GOOGLE_MAPS, photo, photo);
        assertNotNull(company);
    }*/

    /*@Test
    public void whenInitAndUpdateCompanyThenReturnCompany() {
        final Photo photo = MockEntity.getFile();
        Company company = this.service.initAndUpdate(MockConstants.ID, MockConstants.TITLE, MockConstants.DOMAIN, MockConstants.TAGLINE, MockConstants.DESCRIPTION, MockConstants.INFORMATION, MockConstants.ADVANTAGES,
                MockConstants.PHONE, MockConstants.PHONE, MockConstants.PHONE, MockConstants.EMAIL, MockConstants.EMAIL, MockConstants.PASSWORD, MockConstants.VKONTAKTE, MockConstants.FACEBOOK, MockConstants.TWITTER, MockConstants.SKYPE, MockConstants.ADDRESS, MockConstants.KEYWORDS,
                MockConstants.GOOGLE_MAPS, photo, photo);
        assertNotNull(company);
    }*/

    @Test
    public void whenGetMainCompanyThenReturnSomeCompany() {
        Company company = this.service.getMainCompany();
        assertNotNull(company);
    }

    @Test
    public void whenGetPartnersThenReturnSomeCompanies() {
        Collection<Company> companies = this.service.getPartners(true);
        assertNotNull(companies);
    }

    @Ignore
    @Override
    protected CompanyService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected Company getObject() {
        return MockEntity.getCompany();
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return MockEntity.getCompanies();
    }
}
