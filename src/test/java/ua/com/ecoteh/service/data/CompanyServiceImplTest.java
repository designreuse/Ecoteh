package ua.com.ecoteh.service.data;

import ua.com.ecoteh.entity.Company;
import ua.com.ecoteh.enums.CompanyType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.mocks.enity.MockEntity;
import ua.com.ecoteh.mocks.repository.MockRepository;

import java.util.Collection;

import static ua.com.ecoteh.mocks.MockConstants.URL;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;
import static org.junit.Assert.*;

public final class CompanyServiceImplTest extends ContentServiceImplTest<Company> {

    private CompanyService service;

    @Before
    public void beforeTest() {
        this.service = new CompanyServiceImpl(
                MockRepository.getCompanyRepository(),
                getFileService()
        );
    }

    @Test
    public void whenAddThenReturnSomeCompany() {
        assertNotNull(this.service.add(MockEntity.getCompany()));
    }

    @Test
    public void whenInitAndUpdateThenReturnSomeCompany() {
        assertNotNull(this.service.update(URL, MockEntity.getCompany()));
    }

    @Test
    public void whenUpdateMainCompanyThenReturnSomeCompany() {
        assertNotNull(this.service.updateMainCompany(MockEntity.getCompany()));
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
        return MockEntity.getCompany();
    }

    @Ignore
    @Override
    protected Collection<Company> getObjects() {
        return MockEntity.getCompanies();
    }

    @Ignore
    @Override
    protected Company getInvalidObject() {
        return new Company();
    }
}
