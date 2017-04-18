package com.salimov.ecoteh.mocks.enity.test;

import com.salimov.ecoteh.entity.Company;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompanies;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompany;
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
        assertNotNull(company.getContacts());
        assertNotNull(company.getSenderEmail());
        assertNotNull(company.getSenderPass());
        assertNotNull(company.getAddress());
        assertNotNull(company.getKeywords());
        assertNotNull(company.getAddress());
        assertNotNull(company.getWorkTimeFrom());
        assertNotNull(company.getWorkTimeTo());
    }

    @Override
    protected Company getObject() {
        return getCompany();
    }

    @Override
    protected Collection<Company> getObjects() {
        return getCompanies();
    }
}
