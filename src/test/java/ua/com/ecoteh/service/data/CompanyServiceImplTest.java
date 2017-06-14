package ua.com.ecoteh.service.data;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.entity.company.CompanyBuilder;
import ua.com.ecoteh.repository.CompanyRepository;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.mocks.MockConstants.DOMAIN;
import static ua.com.ecoteh.mocks.MockConstants.UNKNOWN_ID;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompanies;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompany;
import static ua.com.ecoteh.mocks.repository.MockRepository.getCompanyRepository;
import static ua.com.ecoteh.mocks.service.data.MockServices.getFileService;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyServiceImplTest extends ContentServiceImplTest<Company> {

    private static CompanyService service;
    private static Company company;
    private static Collection<Company> companies;

    @BeforeClass
    public static void beforeClass() {
        final CompanyRepository repository = getCompanyRepository();
        final FileService fileService = getFileService();
        service = new CompanyServiceImpl(repository, fileService);
        company = getCompany();
        companies = getCompanies();
    }

    @Test
    public void whenUpdateMainCompanyThenReturnNotNull() {
        final Company updatedCompany = service.updateMainCompany(company);
        assertNotNull(updatedCompany);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenUpdateNullMainCompanyThenThrownIllegalArgumentException() {
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

    @Test
    public void whenRemoveMainThenDoIt() {
        service.removeMain();
    }

    @Override
    protected CompanyService getService() {
        return service;
    }

    @Override
    protected Company getModel() {
        return company;
    }

    @Override
    protected Collection<Company> getModels() {
        return companies;
    }

    @Override
    protected Company getUnknownModel() {
        final CompanyBuilder builder = Company.getBuilder();
        builder.addId(UNKNOWN_ID).addTitle(ANY_STRING).addUrl(ANY_STRING);
        return builder.build();
    }
}
