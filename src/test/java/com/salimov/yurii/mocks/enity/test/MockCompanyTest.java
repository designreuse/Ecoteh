package com.salimov.yurii.mocks.enity.test;

import com.salimov.yurii.entity.Company;
import org.junit.Ignore;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;

public class MockCompanyTest extends MockModelTest<Company> {


    @Override
    public void whenGetModelThenReturnValidModel() {
        final Company company = getCompany();
        assertTrue(Company.isValidated(company));
        assertNotNull(company.getTitle());
        assertNotNull(company.getUrl());
        assertNotNull(company.getDomain());
        assertNotNull(company.getTagline());
        assertNotNull(company.getDescription());
        assertNotNull(company.getInformation());
        assertNotNull(company.getAdvantages());
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
        assertNotNull(company.getLogo());
        assertNotNull(company.getFavicon());
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

    @Ignore
    @Override
    protected Collection<Company> getObjects(int size) {
        return getCompanies(size);
    }
}
