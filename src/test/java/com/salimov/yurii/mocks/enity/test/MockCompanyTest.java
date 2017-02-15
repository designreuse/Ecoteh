package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.Company;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;
import static org.junit.Assert.assertNotNull;

public final class MockCompanyTest extends MockModelTest<Company> {


    @Override
    public void whenGetModelThenReturnValidModel() {
        final Company company = getCompany();
        assertNotNull(company.getTitle());
        assertNotNull(company.getUrl());
        assertNotNull(company.getDomain());
        assertNotNull(company.getTagline());
        assertNotNull(company.getDescription());
        assertNotNull(company.getInformation());
        assertNotNull(company.getMobilePhone());
        assertNotNull(company.getLandlinePhone());
        assertNotNull(company.getFax());
        assertNotNull(company.getEmail());
        assertNotNull(company.getSenderEmail());
        assertNotNull(company.getSenderPass());
        assertNotNull(company.getVkontakte());
        assertNotNull(company.getFacebook());
        assertNotNull(company.getTwitter());
        assertNotNull(company.getSkype());
        assertNotNull(company.getAddress());
        assertNotNull(company.getKeywords());
        assertNotNull(company.getGoogleMaps());
        assertNotNull(company.getLogoUrl());
        assertNotNull(company.getFaviconUrl());
        assertNotNull(company.getWorkTimeFrom());
        assertNotNull(company.getWorkTimeTo());
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
}
