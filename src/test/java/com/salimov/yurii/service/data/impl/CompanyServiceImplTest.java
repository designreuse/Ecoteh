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

public final class CompanyServiceImplTest extends ContentServiceImplTest<Company> {

    private CompanyService service;

    @Before
    public void beforeTest() {
        this.service = new CompanyServiceImpl(getCompanyDao());
    }

    @Test
    public void whenAddThenReturnSomeCompany() {
        assertNotNull(this.service.add(getCompany()));
    }

    @Test
    public void whenInitAndUpdateThenReturnSomeCompany() {
        assertNotNull(this.service.update(URL, getCompany()));
    }

    @Test
    public void whenUpdateMainCompanyThenReturnSomeCompany() {
        assertNotNull(this.service.updateMainCompany(getCompany()));
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
