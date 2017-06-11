package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.company.Company;
import ua.com.ecoteh.mocks.enity.MockModels;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public final class MockCompanyTest extends MockContentTest<Company> {

    private static Company company;
    private static Collection<Company> companies;

    @BeforeClass
    public static void beforeClass() {
        company = MockModels.getCompany();
        companies = MockModels.getCompanies();
    }

    @Test
    public void whenGetTaglineThenReturnNotEmpty() {
        assertFalse(company.getTagline().isEmpty());
    }

    @Test
    public void whenGetInformationThenReturnNotEmpty() {
        assertFalse(company.getInformation().isEmpty());
    }

    @Test
    public void whenGetDomainThenReturnNotEmpty() {
        assertFalse(company.getDomain().isEmpty());
    }

    @Test
    public void whenGetSenderEmailThenReturnNotEmpty() {
        assertFalse(company.getSenderEmail().isEmpty());
    }

    @Test
    public void whenGetSenderPassThenReturnNotEmpty() {
        assertFalse(company.getSenderPass().isEmpty());
    }

    @Test
    public void whenGetWorkTimeFromThenReturnNotEmpty() {
        assertFalse(company.getWorkTimeFrom().isEmpty());
    }

    @Test
    public void whenGetWorkTimeToThenReturnNotEmpty() {
        assertFalse(company.getWorkTimeTo().isEmpty());
    }

    @Test
    public void whenGetContactsThenReturnNotNull() {
        assertNotNull(company.getContacts());
    }

    @Test
    public void whenGetAddressThenReturnNotNull() {
        assertNotNull(company.getAddress());
    }

    @Test
    public void whenGetTypeThenReturnNotNull() {
        assertNotNull(company.getType());
    }

    @Override
    protected Company getObject() {
        return company;
    }

    @Override
    protected Collection<Company> getObjects() {
        return companies;
    }
}
