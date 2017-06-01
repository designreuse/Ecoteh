package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.service.data.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;

import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;
import static org.junit.Assert.assertNotNull;

public final class MockCompanyEntityServiceTest extends MockContentServiceTest<CompanyEntity> {

    private CompanyService service;

    @Before
    public void initCompanyService() {
        this.service = getCompanyService();
    }

    @Test
    public void whenAddCompanyThenReturnThisCompany() {
        assertNotNull(this.service.add(MockEntity.getCompanyEntity()));
    }

    @Test
    public void whenUpdateCompanyThenReturnCompany() {
        assertNotNull(this.service.update(MockConstants.URL, MockEntity.getCompanyEntity()));
    }

    @Test
    public void whenGetMainCompanyThenReturnSomeCompany() {
        assertNotNull(this.service.getMainCompany());
    }

    @Test
    public void whenGetPartnersThenReturnSomeCompanies() {
        assertNotNull(this.service.getPartners(true));
    }

    @Ignore
    @Override
    protected CompanyService getService() {
        return this.service;
    }

    @Ignore
    @Override
    protected CompanyEntity getObject() {
        return MockEntity.getCompanyEntity();
    }

    @Ignore
    @Override
    protected Collection<CompanyEntity> getObjects() {
        return MockEntity.getCompanies();
    }
}
