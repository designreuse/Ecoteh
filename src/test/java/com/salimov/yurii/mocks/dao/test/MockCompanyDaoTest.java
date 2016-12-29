package com.salimov.yurii.mocks.dao.test;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.yurii.mocks.MockConstants.COMPANY_TYPE;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;
import static com.salimov.yurii.mocks.dao.MockDAO.getCompanyDao;

public class MockCompanyDaoTest extends MockContentDAOTest<Company> {

    private CompanyDao dao;

    @Before
    public void initCompanyDao() {
        this.dao = getCompanyDao();
    }

    @Test
    public void whenGetByInvalidTypeThenReturnNull() {
        Collection<Company> companies = this.dao.getByType(null);
        assertNull(companies);
    }

    @Test
    public void whenGetByValidTypeThenReturnSomeCompanies() {
        Collection<Company> companies = this.dao.getByType(COMPANY_TYPE);
        assertNotNull(companies);
    }

    @Ignore
    @Override
    protected CompanyDao getDao() {
        return this.dao;
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
