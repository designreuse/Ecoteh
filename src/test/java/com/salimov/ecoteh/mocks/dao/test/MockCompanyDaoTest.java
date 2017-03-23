package com.salimov.ecoteh.mocks.dao.test;

import com.salimov.ecoteh.dao.interfaces.CompanyDao;
import com.salimov.ecoteh.entity.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static com.salimov.ecoteh.mocks.MockConstants.COMPANY_TYPE;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompanies;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompany;
import static com.salimov.ecoteh.mocks.dao.MockDao.getCompanyDao;

public class MockCompanyDaoTest extends MockContentDAOTest<Company> {

    private CompanyDao dao;

    @Before
    public void initCompanyDao() {
        this.dao = getCompanyDao();
    }

    @Test
    public void whenGetByInvalidTypeThenReturnNull() {
        assertNull(this.dao.getByType(null));
    }

    @Test
    public void whenGetByValidTypeThenReturnSomeCompanies() {
        assertNotNull(this.dao.getByType(COMPANY_TYPE));
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
