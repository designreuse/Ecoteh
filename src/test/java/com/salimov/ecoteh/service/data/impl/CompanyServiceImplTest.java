package com.salimov.ecoteh.service.data.impl;

import com.salimov.ecoteh.entity.Company;
import com.salimov.ecoteh.enums.CompanyType;
import com.salimov.ecoteh.service.data.interfaces.CompanyService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Collection;

import static com.salimov.ecoteh.mocks.MockConstants.URL;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompanies;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getCompany;
import static com.salimov.ecoteh.mocks.repository.MockRepository.getCompanyRepository;
import static com.salimov.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class CompanyServiceImplTest extends ContentServiceImplTest<Company> {

    private CompanyService service;

    @Before
    public void beforeTest() {
        this.service = new CompanyServiceImpl(
                getCompanyRepository(),
                getFileService()
        );
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
    public void whenGetValidPartnersThenReturnSomeList() {
        assertFalse(this.service.getPartners(true).isEmpty());
    }

    @Test
    public void whenGetPartnersThenReturnSomeList() {
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
