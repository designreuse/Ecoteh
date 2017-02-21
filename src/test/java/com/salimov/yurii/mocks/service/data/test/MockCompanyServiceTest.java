package com.salimov.yurii.mocks.service.data.test;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.mocks.enity.MockEntity;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;
import static com.salimov.yurii.mocks.service.data.MockServices.getCompanyService;
import static org.junit.Assert.assertNotNull;

public final class MockCompanyServiceTest extends MockContentServiceTest<Company> {

    private CompanyService service;

    @Before
    public void initCompanyService() {
        this.service = getCompanyService();
    }

    @Test
    public void whenAddCompanyThenReturnThisCompany() {
        assertNotNull(this.service.add(getCompany()));
    }

    @Test
    public void whenUpdateCompanyThenReturnCompany() {
        assertNotNull(this.service.update(URL, getCompany()));
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
        return getCompany();
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return MockEntity.getCompanies();
    }
}
