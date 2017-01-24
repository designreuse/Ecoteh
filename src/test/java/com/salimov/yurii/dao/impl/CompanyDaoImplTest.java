package com.salimov.yurii.dao.impl;

import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.dao.interfaces.DataDao;
import com.salimov.yurii.entity.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static com.salimov.yurii.mocks.MockConstants.TITLE;
import static com.salimov.yurii.mocks.MockConstants.URL;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompanies;
import static com.salimov.yurii.mocks.enity.MockEntity.getCompany;
import static com.salimov.yurii.mocks.repository.MockRepository.getCompanyRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class CompanyDaoImplTest
        extends DataDAOImplTest<Company, Long> {

    private CompanyDao dao;

    @Before
    public void beforeTest() {
        this.dao = new CompanyDaoImpl(
                getCompanyRepository()
        );
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(
                this.dao.getByTitle(null)
        );
        assertNull(
                this.dao.getByTitle(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeCompany() {
        assertNotNull(
                this.dao.getByTitle(TITLE)
        );
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(
                this.dao.getByUrl(null)
        );
        assertNull(
                this.dao.getByUrl(ANY_STRING)
        );
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeCompany() {
        assertNotNull(
                this.dao.getByUrl(URL)
        );
    }

    @Ignore
    @Override
    protected DataDao<Company, Long> getDao() {
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
