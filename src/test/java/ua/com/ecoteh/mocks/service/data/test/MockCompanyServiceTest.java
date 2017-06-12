package ua.com.ecoteh.mocks.service.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.service.data.CompanyService;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompanies;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompany;
import static ua.com.ecoteh.mocks.service.data.MockServices.getCompanyService;

public final class MockCompanyServiceTest extends MockContentServiceTest<Company> {

    private static CompanyService service;
    private static Company company;
    private static Collection<Company> companies;

    @BeforeClass
    public static void beforeClass() {
        service = getCompanyService();
        company = getCompany();
        companies = getCompanies();
    }

    @Test
    public void whenGetAllThenReturnNotEmptyCollection() {
        Collection<Company> companies = service.getAll(true);
        assertFalse(companies.isEmpty());
        companies = service.getAll(false);
        assertFalse(companies.isEmpty());
        companies = service.getAll();
        assertFalse(companies.isEmpty());
    }

    @Test
    public void whenUpdateMainCompanyThenReturnNotNull() {
        final Company mainCompany = service.updateMainCompany(company);
        assertNotNull(mainCompany);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateNullMainCompanyThenThrowIllegalArgumentException() {
        service.updateMainCompany(null);
    }

    @Test
    public void whenGetMainCompanyThenReturnNotNull() {
        final Company mainCompany = service.getMainCompany();
        assertNotNull(mainCompany);
    }

    @Test
    public void whenGetPartnersThenReturnNotEmptyCollection() {
        Collection<Company> partners = service.getPartners(true);
        assertFalse(partners.isEmpty());
        partners = service.getPartners(false);
        assertFalse(partners.isEmpty());
    }

    @Test
    public void whenGetPartnersThenReturnCollectionWithNotNullObjects() {
        Collection<Company> partners = service.getPartners(true);
        partners.forEach(Assert::assertNotNull);
        partners = service.getPartners(false);
        partners.forEach(Assert::assertNotNull);
    }

    @Test
    public void whenGetByDomainThenReturnNotNull() {
        final Company company = service.getByDomain(DOMAIN);
        assertNotNull(company);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByNullDomainThenThrowIllegalArgumentException() {
        service.getByDomain(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetByEmptyDomainThenThrowIllegalArgumentException() {
        service.getByDomain("");
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByUnknownDomainThenThrowNullPointerException() {
        service.getByDomain(ANY_STRING);
    }

    @Ignore
    @Override
    protected CompanyService getService() {
        return service;
    }

    @Ignore
    @Override
    protected Company getObject() {
        return company;
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return companies;
    }
}
