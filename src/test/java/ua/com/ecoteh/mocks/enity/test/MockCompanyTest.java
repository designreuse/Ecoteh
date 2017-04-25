package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockCompanyTest extends MockModelTest<Company> {


    @Override
    public void whenGetModelThenReturnValidModel() {
        final Company company = MockEntity.getCompany();
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
        return MockEntity.getCompany();
    }

    @Override
    protected Collection<Company> getObjects() {
        return MockEntity.getCompanies();
    }
}
