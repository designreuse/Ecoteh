package ua.com.ecoteh.entity.company;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentEntityConverterTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getCompanyEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyEntityConverterTest extends ContentEntityConverterTest<CompanyEntity, Company> {

    private static CompanyEntityConverter converter;
    private static CompanyEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getCompanyEntity();
        converter = new CompanyEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Company company) {
        super.checkEntity(company);
        assertEquals(company.getTagline(), entity.getTagline());
        assertEquals(company.getDomain(), entity.getDomain());
        assertEquals(company.getSenderEmail(), entity.getSenderEmail());
        assertEquals(company.getSenderPass(), decrypt(entity.getSenderPass()));
        assertEquals(company.getWorkTimeFrom(), entity.getWorkTimeFrom());
        assertEquals(company.getWorkTimeTo(), entity.getWorkTimeTo());
        assertEquals(company.getType(), entity.getType());
        assertNotNull(company.getAddress());
        assertNotNull(company.getContacts());
    }

    @Override
    protected CompanyEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected CompanyEntity getEntity() {
        return entity;
    }

    private String decrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.decrypt(value);
    }
}