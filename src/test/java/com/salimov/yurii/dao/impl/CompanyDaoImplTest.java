package com.salimov.yurii.dao.impl;

import com.salimov.yurii.config.RootConfig;
import com.salimov.yurii.config.WebConfig;
import com.salimov.yurii.dao.interfaces.CompanyDao;
import com.salimov.yurii.entity.Company;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.salimov.yurii.mocks.enity.MockEntity.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CompanyDaoImplTest extends DataDAOImplTest<Company> {

    @Autowired
    private CompanyDao dao;

    @Test
    public void whenGetByInvalidTypeThenThrowException() {
        final Collection<Company> companies = getDao().getByType(null);
        assertNotNull(companies);
        assertTrue(companies.isEmpty());
    }

    @Test
    @Transactional
    public void whenGetByValidTypeThenThrowException() {
        final Company company = getObject();
        this.dao.add(company);
        final Collection<Company> companies = this.dao.getByType(
                company.getType()
        );
        assertNotNull(companies);
        assertFalse(companies.isEmpty());
    }

    @Ignore
    @Override
    protected CompanyDao getDao() {
        return this.dao;
    }

    @Ignore
    @Override
    protected Company getObject() {
        final Company company = getCompany();
        company.setId(Long.MAX_VALUE);
        company.setTitle(company.getTitle() + " " + getRandomInt(10));
        return company;
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return getCompanies();
    }
}
