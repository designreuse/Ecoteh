package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.company.CompanyEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockCompanyEntityTest extends MockContentEntityTest<CompanyEntity> {

    private static CompanyEntity company;
    private static Collection<CompanyEntity> companies;

    @BeforeClass
    public static void beforeClass() {
        company = getCompanyEntity();
        companies = getCompanyEntities();
    }

    @Test
    public void whenGetTaglineThenReturnNotEmpty() {
        assertFalse(company.getTagline().isEmpty());
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
        assertNotNull(company.getContactsEntity());
    }

    @Test
    public void whenGetAddressThenReturnNotNull() {
        assertNotNull(company.getAddressEntity());
    }

    @Test
    public void whenGetTypeThenReturnNotNull() {
        assertNotNull(company.getType());
    }

    @Override
    protected CompanyEntity getObject() {
        return company;
    }

    @Override
    protected Collection<CompanyEntity> getObjects() {
        return companies;
    }
}
