package com.salimov.yurii.service.data.impl;

import com.salimov.yurii.entity.Company;
import com.salimov.yurii.service.data.interfaces.CompanyService;
import org.junit.Before;
import org.junit.Ignore;

import java.util.Collection;

import static com.salimov.yurii.mocks.dao.MockDao.getCompanyDao;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;

public final class CompanyServiceImplTest
        extends ContentServiceImplTest<Company, Long> {

    private CompanyService service;

    @Before
    public void beforeTest() {
        this.service = new CompanyServiceImpl(
                getCompanyDao()
        );
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
