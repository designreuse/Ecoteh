package ua.com.ecoteh.mocks.service.data.test;

import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.service.data.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.MockConstants;

import java.util.Collection;

import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;
import static org.junit.Assert.assertNotNull;

public final class MockCompanyServiceTest extends MockContentServiceTest<Company> {

    private CompanyService service;

    @Before
    public void initCompanyService() {
        this.service = getCompanyService();
    }

    @Test
    public void whenAddCompanyThenReturnThisCompany() {
        assertNotNull(this.service.add(MockEntity.getCompany()));
    }

    @Test
    public void whenUpdateCompanyThenReturnCompany() {
        assertNotNull(this.service.update(MockConstants.URL, MockEntity.getCompany()));
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
    protected Company getObject() {
        return MockEntity.getCompany();
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return MockEntity.getCompanies();
    }
}
