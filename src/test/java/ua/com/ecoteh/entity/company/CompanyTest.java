package ua.com.ecoteh.entity.company;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.content.ContentTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyTest extends ContentTest {

    private static Company company;

    @BeforeClass
    public static void beforeClass() {
        company = new Company(
                ID, VALIDATION, TITLE, URL, TEXT, DESCRIPTION, KEYWORDS,
                TAGLINE, DOMAIN, EMAIL, PASSWORD, TIME, TIME,
                getFile(), getContacts(), getAddress(), COMPANY_TYPE
        );
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Company{" +
                "Content{" +
                "Model{id=" + company.getId() +
                ", validated=" + company.isValidated() + '}' +
                ", title='" + company.getTitle() + '\'' +
                ", url='" + company.getUrl() + '\'' +
                ", text='" + company.getText() + '\'' +
                ", description='" + company.getDescription() + '\'' +
                ", keywords='" + company.getKeywords() + '\'' +
                ", logo=" + company.getLogo() +
                '}' +
                ", contacts=" + company.getContacts() +
                ", address=" + company.getAddress() +
                ", tagline='" + company.getTagline() + '\'' +
                ", domain='" + company.getDomain() + '\'' +
                ", senderEmail='" + company.getSenderEmail() + '\'' +
                ", senderPass='" + company.getSenderPass() + '\'' +
                ", workTimeFrom='" + company.getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + company.getWorkTimeTo() + '\'' +
                ", type=" + company.getType() +
                '}';
        assertEquals(company.toString(), testString);
    }

    @Test
    public void getDomain() throws Exception {
        assertFalse(company.getDomain().isEmpty());
    }

    @Test
    public void getTagline() throws Exception {
        assertFalse(company.getTagline().isEmpty());
    }

    @Test
    public void getInformation() throws Exception {
        assertFalse(company.getDomain().isEmpty());
    }

    @Test
    public void getSenderEmail() throws Exception {
        assertFalse(company.getSenderEmail().isEmpty());
    }

    @Test
    public void getSenderPass() throws Exception {
        assertFalse(company.getSenderPass().isEmpty());
    }

    @Test
    public void getWorkTimeFrom() throws Exception {
        assertFalse(company.getWorkTimeFrom().isEmpty());
    }

    @Test
    public void getWorkTimeTo() throws Exception {
        assertFalse(company.getWorkTimeTo().isEmpty());
    }

    @Test
    public void getContactsTest() throws Exception {
        assertNotNull(company.getContacts());
    }

    @Test
    public void getAddressTest() throws Exception {
        assertNotNull(company.getAddress());
    }

    @Test
    public void getType() throws Exception {
        assertNotNull(company.getType());
    }

    @Test
    public void isOpen() throws Exception {
        company.isOpen();
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final CompanyEntity entity = company.convert();
        assertTrue(entity.getId() >= 0);
        assertEquals(entity.isValidated(), company.isValidated());
        assertEquals(entity.getTitle(), company.getTitle());
        assertEquals(entity.getUrl(), company.getUrl());
        assertEquals(entity.getText(), company.getText());
        assertEquals(entity.getDescription(), company.getDescription());
        assertEquals(entity.getKeywords(), company.getKeywords());
        assertEquals(entity.getTagline(), company.getTagline());
        assertEquals(entity.getDomain(), company.getDomain());
        assertEquals(entity.getSenderEmail(), company.getSenderEmail());
        assertFalse(entity.getSenderPass().isEmpty());
        assertEquals(entity.getWorkTimeFrom(), company.getWorkTimeFrom());
        assertEquals(entity.getWorkTimeTo(), company.getWorkTimeTo());
        assertNotNull(entity.getLogoEntity());
        assertNotNull(entity.getContactsEntity());
        assertNotNull(entity.getAddressEntity());
        assertEquals(entity.getType(), company.getType());
    }

    @Test
    public void getBuilder() throws Exception {
        final CompanyBuilder builder = Company.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Company getInstance() {
        return company;
    }
}