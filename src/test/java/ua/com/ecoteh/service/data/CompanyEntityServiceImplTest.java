package ua.com.ecoteh.service.data;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.company.CompanyEntity;
import ua.com.ecoteh.entity.company.CompanyType;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

public final class CompanyEntityServiceImplTest extends ContentServiceImplTest<CompanyEntity> {

    private static CompanyService service;

    @BeforeClass
    public static void beforeTest() {
        service = new CompanyServiceImpl(
                MockRepository.getCompanyRepository(),
                getFileService()
        );
    }

    @Test
    public void whenAddThenReturnSomeCompany() {
        assertNotNull(service.add(MockEntity.getCompanyEntity()));
    }

    @Test
    public void whenInitAndUpdateThenReturnSomeCompany() {
        assertNotNull(service.update(URL, MockEntity.getCompanyEntity()));
    }

    @Test
    public void whenUpdateMainCompanyThenReturnSomeCompany() {
        assertNotNull(service.updateMainCompany(MockEntity.getCompanyEntity()));
    }

    @Test
    public void whenThisServiceGetMainCompanyThenReturnMainCompany() {
        CompanyRepository repository = mock(CompanyRepository.class);
        List<CompanyEntity> companies = new ArrayList<>(getObjects());
        when(repository.findByType(CompanyType.MAIN)).thenReturn(companies);
        CompanyService service = new CompanyServiceImpl(repository, null);
        assertNotNull(service.getMainCompany());
    }

    @Test
    public void whenGetMainCompanyThenReturnMainCompany() {
        assertNotNull(service.getMainCompany());
    }

    @Test
    public void whenGetValidPartnersThenReturnSomeList() {
        assertFalse(service.getPartners(true).isEmpty());
    }

    @Test
    public void whenGetPartnersThenReturnSomeList() {
        assertFalse(service.getPartners(false).isEmpty());
    }

    @Test
    public void whenRemoveCompanyWithMainTypeThenDoNothing() {
        final CompanyEntity companyEntity = getObject();
        companyEntity.setType(CompanyType.MAIN);
        service.remove(companyEntity);
    }

    @Test
    public void whenRemoveCompanyWithNotMainTypeThenDoIt() {
        final CompanyEntity companyEntity = getObject();
        companyEntity.setType(CompanyType.PARTNER);
        service.remove(companyEntity);
    }

    @Test
    public void whenRemoveMainCompanyThenDoIt() {
        service.removeMain();
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

    @Test
    public void whenGetByDomainThenReturnSomeCompany() {
        assertNotNull(service.getByDomain(DOMAIN));
    }

    @Ignore
    @Override
    protected CompanyService getService() {
        return service;
    }

    @Ignore
    @Override
    protected CompanyEntity getObject() {
        return MockEntity.getCompanyEntity();
    }

    @Ignore
    @Override
    protected Collection<CompanyEntity> getObjects() {
        return MockEntity.getCompanies();
    }

    @Ignore
    @Override
    protected CompanyEntity getInvalidObject() {
        return new CompanyEntity();
    }
}
