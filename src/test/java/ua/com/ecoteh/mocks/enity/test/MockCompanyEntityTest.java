package ua.com.ecoteh.mocks.enity.test;

import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;

public final class MockCompanyEntityTest extends MockModelTest<CompanyEntity> {


    @Override
    public void whenGetModelThenReturnValidModel() {
        final CompanyEntity companyEntity = MockEntity.getCompanyEntity();
        assertNotNull(companyEntity.getTitle());
        assertNotNull(companyEntity.getUrl());
        assertNotNull(companyEntity.getDomain());
        assertNotNull(companyEntity.getTagline());
        assertNotNull(companyEntity.getDescription());
        assertNotNull(companyEntity.getInformation());
        assertNotNull(companyEntity.getContactsEntity());
        assertNotNull(companyEntity.getSenderEmail());
        assertNotNull(companyEntity.getSenderPass());
        assertNotNull(companyEntity.getAddressEntity());
        assertNotNull(companyEntity.getKeywords());
        assertNotNull(companyEntity.getAddressEntity());
        assertNotNull(companyEntity.getWorkTimeFrom());
        assertNotNull(companyEntity.getWorkTimeTo());
    }

    @Override
    protected CompanyEntity getObject() {
        return MockEntity.getCompanyEntity();
    }

    @Override
    protected Collection<CompanyEntity> getObjects() {
        return MockEntity.getCompanies();
    }
}
