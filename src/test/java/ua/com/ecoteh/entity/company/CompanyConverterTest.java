package ua.com.ecoteh.entity.company;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.content.ContentConverterTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getCompany;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyConverterTest extends ContentConverterTest<Company, CompanyEntity> {

    private static CompanyConverter converter;
    private static Company company;

    @BeforeClass
    public static void beforeClass() {
        company = getCompany();
        converter = new CompanyConverter(company);
    }

    @Override
    protected void checkEntity(final CompanyEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getTagline(), company.getTagline());
        assertEquals(entity.getDomain(), company.getDomain());
        assertEquals(entity.getSenderEmail(), company.getSenderEmail());
        assertEquals(entity.getSenderPass(), encrypt(company.getSenderPass()));
        assertEquals(entity.getWorkTimeFrom(), company.getWorkTimeFrom());
        assertEquals(entity.getWorkTimeTo(), company.getWorkTimeTo());
        assertEquals(entity.getType(), company.getType());
        assertNotNull(entity.getAddressEntity());
        assertNotNull(entity.getContactsEntity());
    }

    @Override
    protected CompanyConverter getConverter() {
        return converter;
    }

    @Override
    protected Company getModel() {
        return company;
    }

    private String encrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.encrypt(value);
    }
}
