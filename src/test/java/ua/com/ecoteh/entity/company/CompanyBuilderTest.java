package ua.com.ecoteh.entity.company;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.content.ContentBuilderTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getAddress;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyBuilderTest extends ContentBuilderTest<Company> {

    private CompanyBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new CompanyBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Company company = this.builder.build();
        assertNotNull(company.getTagline());
        assertNotNull(company.getDomain());
        assertNotNull(company.getSenderEmail());
        assertNotNull(company.getSenderPass());
        assertNotNull(company.getWorkTimeFrom());
        assertNotNull(company.getWorkTimeTo());
        assertNotNull(company.getContacts());
        assertNotNull(company.getAddress());
        assertNotNull(company.getType());
    }

    @Test
    public void whenAddTaglineThenBuildWithIt() {
        Company company;
        String tagline;
        for (int i = 0; i < 5; i++) {
            tagline = TAGLINE + i;
            this.builder.addTagline(tagline);
            company = this.builder.build();
            assertEquals(company.getTagline(), tagline);
        }
    }

    @Test
    public void whenAddNullTaglineThenBuildWithEmptyIt() {
        this.builder.addTagline(null);
        final Company company = this.builder.build();
        assertTrue(company.getTagline().isEmpty());
    }

    @Test
    public void whenAddDomainThenBuildWithIt() {
        Company company;
        String domain;
        for (int i = 0; i < 5; i++) {
            domain = DOMAIN + i;
            this.builder.addDomain(domain);
            company = this.builder.build();
            assertEquals(company.getDomain(), domain);
        }
    }

    @Test
    public void whenAddNullDomainThenBuildWithEmptyIt() {
        this.builder.addDomain(null);
        final Company company = this.builder.build();
        assertTrue(company.getDomain().isEmpty());
    }

    @Test
    public void whenAddSenderEmailThenBuildWithIt() {
        Company company;
        String senderEmail;
        for (int i = 0; i < 5; i++) {
            senderEmail = EMAIL + i;
            this.builder.addSenderEmail(senderEmail);
            company = this.builder.build();
            assertEquals(company.getSenderEmail(), senderEmail);
        }
    }

    @Test
    public void whenAddNullSenderEmailThenBuildWithEmptyIt() {
        this.builder.addSenderEmail(null);
        final Company company = this.builder.build();
        assertTrue(company.getSenderEmail().isEmpty());
    }

    @Test
    public void whenAddSenderPassThenBuildWithIt() {
        Company company;
        String senderPass;
        for (int i = 0; i < 5; i++) {
            senderPass = PASSWORD + i;
            this.builder.addSenderPass(senderPass);
            company = this.builder.build();
            assertEquals(company.getSenderPass(), senderPass);
        }
    }

    @Test
    public void whenAddNullSenderPassThenBuildWithEmptyIt() {
        this.builder.addSenderPass(null);
        final Company company = this.builder.build();
        assertTrue(company.getSenderPass().isEmpty());
    }

    @Test
    public void whenAddWorkTimeFromThenBuildWithIt() {
        this.builder.addWorkTimeFrom(TIME);
        final Company company = this.builder.build();
        assertEquals(company.getWorkTimeFrom(), TIME);
    }

    @Test
    public void whenAddNullWorkTimeFromThenBuildWithEmptyIt() {
        this.builder.addWorkTimeFrom(null);
        final Company company = this.builder.build();
        assertEquals(company.getWorkTimeFrom(), "00:00");
    }

    @Test
    public void whenAddWorkTimeToThenBuildWithIt() {
        this.builder.addWorkTimeTo(TIME);
        final Company company = this.builder.build();
        assertEquals(company.getWorkTimeTo(), TIME);
    }

    @Test
    public void whenAddNullWorkTimeToThenBuildWithEmptyIt() {
        this.builder.addWorkTimeTo(null);
        final Company company = this.builder.build();
        assertEquals(company.getWorkTimeTo(), "00:00");
    }

    @Test
    public void whenAddContactsThenBuildWithIt() {
        final Contacts contacts = getContacts();
        this.builder.addContacts(contacts);
        final Company company = this.builder.build();
        assertEquals(company.getContacts(), contacts);
    }

    @Test
    public void whenAddNullContactsThenBuildWithEmptyIt() {
        this.builder.addContacts(null);
        final Company company = this.builder.build();
        assertNotNull(company.getContacts());
    }

    @Test
    public void whenAddAddressThenBuildWithIt() {
        final Address address = getAddress();
        this.builder.addAddress(address);
        final Company company = this.builder.build();
        assertEquals(company.getAddress(), address);
    }

    @Test
    public void whenAddNullAddressThenBuildWithEmptyIt() {
        this.builder.addAddress(null);
        final Company company = this.builder.build();
        assertNotNull(company.getAddress());
    }

    @Test
    public void whenAddTypeThenBuildWithIt() {
        Company company;
        for (CompanyType type : CompanyType.values()) {
            this.builder.addType(type);
            company = this.builder.build();
            assertEquals(company.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeThenBuildWithEmptyIt() {
        this.builder.addType((CompanyType) null);
        final Company company = this.builder.build();
        assertNotNull(company.getType());
    }

    @Test
    public void whenAddTypeNameThenBuildWithIt() {
        Company company;
        for (CompanyType type : CompanyType.values()) {
            this.builder.addType(type.name());
            company = this.builder.build();
            assertEquals(company.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeNameThenBuildWithEmptyIt() {
        this.builder.addType((String) null);
        final Company company = this.builder.build();
        assertNotNull(company.getType());
    }

    @Test
    public void whenAddEmptyTypeNameThenBuildWithEmptyIt() {
        this.builder.addType("");
        final Company company = this.builder.build();
        assertNotNull(company.getType());
    }

    @Override
    protected CompanyBuilder getBuilder() {
        return this.builder;
    }
}