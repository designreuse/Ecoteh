package com.salimov.ecoteh.dao.impl;

import com.salimov.ecoteh.dao.interfaces.CompanyDao;
import com.salimov.ecoteh.dao.interfaces.DataDao;
import com.salimov.ecoteh.entity.Company;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.ANY_STRING;
import static com.salimov.ecoteh.mocks.MockConstants.TITLE;
import static com.salimov.ecoteh.mocks.MockConstants.URL;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompanies;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompany;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getCompanyRepository;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class CompanyDaoImplTest extends DataDAOImplTest<Company> {

    private CompanyDao dao;

    @Before
    public void beforeTest() {
        this.dao = new CompanyDaoImpl(getCompanyRepository());
    }

    @Test
    public void whenGetByNullTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(ANY_STRING));
    }

    @Test
    public void whenGetByInvalidTitleThenReturnsNull() {
        assertNull(this.dao.getByTitle(null));
    }

    @Test
    public void whenGetByValidTitleThenReturnsSomeCompany() {
        assertNotNull(this.dao.getByTitle(TITLE));
    }

    @Test
    public void whenGetByNullUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(null));
    }

    @Test
    public void whenGetByInvalidUrlThenReturnsNull() {
        assertNull(this.dao.getByUrl(ANY_STRING));
    }

    @Test
    public void whenGetByValidUrlThenReturnsSomeCompany() {
        assertNotNull(this.dao.getByUrl(URL));
    }

    @Test
    public void whenRemoveByTitleThenDoIt() {
        this.dao.removeByTitle(TITLE);
    }

    @Test
    public void whenRemoveByUrlThenDoIt() {
        this.dao.removeByUrl(URL);
    }

    @Ignore
    @Override
    protected DataDao<Company> getDao() {
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
