package ua.com.ecoteh.entity.company;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.address.AddressEntity;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.content.ContentEntityTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyEntityTest extends ContentEntityTest {

    private CompanyEntity company;

    @Before
    public void beforeTest() {
        this.company = new CompanyEntity();
        this.company.setId(ID);
        this.company.setValidated(VALIDATION);
        this.company.setTitle(TITLE);
        this.company.setUrl(URL);
        this.company.setDescription(DESCRIPTION);
        this.company.setKeywords(KEYWORDS);
        this.company.setLogoEntity(getFileEntity());
        this.company.setTagline(TAGLINE);
        this.company.setInformation(INFORMATION);
        this.company.setDomain(DOMAIN);
        this.company.setSenderEmail(EMAIL);
        this.company.setSenderPass(PASSWORD);
        this.company.setWorkTimeFrom(TIME);
        this.company.setWorkTimeTo(TIME);
        this.company.setContactsEntity(getContactsEntity());
        this.company.setAddressEntity(getAddressEntity());
        this.company.setType(COMPANY_TYPE);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "CompanyEntity{" +
                "ContentEntity{" +
                "ModelEntity{" +
                "id=" + this.company.getId() +
                ", validated=" + this.company.isValidated() +
                '}' +
                ", title='" + this.company.getTitle() + '\'' +
                ", url='" + this.company.getUrl() + '\'' +
                ", description='" + this.company.getDescription() + '\'' +
                ", keywords='" + this.company.getKeywords() + '\'' +
                ", logo=" + this.company.getLogoEntity() +
                '}' +
                ", contactsEntity=" + this.company.getContactsEntity() +
                ", addressEntity=" + this.company.getAddressEntity() +
                ", tagline='" + this.company.getTagline() + '\'' +
                ", information='" + this.company.getInformation() + '\'' +
                ", domain='" + this.company.getDomain() + '\'' +
                ", senderEmail='" + this.company.getSenderEmail() + '\'' +
                ", senderPass='" + this.company.getSenderPass() + '\'' +
                ", workTimeFrom='" + this.company.getWorkTimeFrom() + '\'' +
                ", workTimeTo='" + this.company.getWorkTimeTo() + '\'' +
                ", type=" + this.company.getType() +
                '}';
        assertEquals(this.company.toString(), toStringTest);
    }

    @Test
    public void whenSetDomainThenGetIt() {
        String domain;
        for (int i = 0; i < 5; i++) {
            domain = DOMAIN + i;
            this.company.setDomain(domain);
            assertEquals(this.company.getDomain(), domain);
        }
    }

    @Test
    public void whenSetTaglineThenGetIt() {
        String tagline;
        for (int i = 0; i < 5; i++) {
            tagline = TAGLINE + i;
            this.company.setTagline(tagline);
            assertEquals(this.company.getTagline(), tagline);
        }
    }

    @Test
    public void whenSetInformationThenGetIt() {
        String information;
        for (int i = 0; i < 5; i++) {
            information = INFORMATION + i;
            this.company.setInformation(information);
            assertEquals(this.company.getInformation(), information);
        }
    }

    @Test
    public void whenSetSenderEmailThenGetIt() {
        String senderEmail;
        for (int i = 0; i < 5; i++) {
            senderEmail = EMAIL + i;
            this.company.setSenderEmail(senderEmail);
            assertEquals(this.company.getSenderEmail(), senderEmail);
        }
    }

    @Test
    public void whenSetSenderPassThenGetIt() {
        String senderPass;
        for (int i = 0; i < 5; i++) {
            senderPass = PASSWORD + i;
            this.company.setSenderPass(senderPass);
            assertEquals(this.company.getSenderPass(), senderPass);
        }
    }

    @Test
    public void whenSetWorkTimeFromThenGetIt() {
        String workTimeFrom;
        for (int i = 0; i < 5; i++) {
            workTimeFrom = TIME + i;
            this.company.setWorkTimeFrom(workTimeFrom);
            assertEquals(this.company.getWorkTimeFrom(), workTimeFrom);
        }
    }

    @Test
    public void whenSetWorkTimeToThenGetIt() {
        String workTimeTo;
        for (int i = 0; i < 5; i++) {
            workTimeTo = TIME + i;
            this.company.setWorkTimeTo(workTimeTo);
            assertEquals(this.company.getWorkTimeTo(), workTimeTo);
        }
    }

    @Test
    public void whenSetContactsThenGetIt() {
        final ContactsEntity contacts = getContactsEntity();
        this.company.setContactsEntity(contacts);
        assertEquals(this.company.getContactsEntity(), contacts);
    }

    @Test
    public void whenSetAddressThenGetIt() {
        final AddressEntity address = getAddressEntity();
        this.company.setAddressEntity(address);
        assertEquals(this.company.getAddressEntity(), address);
    }

    @Test
    public void whenSetTypeThenGetIt() {
        for (CompanyType type : CompanyType.values()) {
            this.company.setType(type);
            assertEquals(this.company.getType(), type);
        }
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final Company company = this.company.convert();
        assertEquals(company.getId(), this.company.getId());
        assertEquals(company.isValidated(), this.company.isValidated());
        assertEquals(company.getTitle(), this.company.getTitle());
        assertEquals(company.getUrl(), this.company.getUrl());
        assertEquals(company.getDescription(), this.company.getDescription());
        assertEquals(company.getKeywords(), this.company.getKeywords());
        assertEquals(company.getTagline(), this.company.getTagline());
        assertEquals(company.getInformation(), this.company.getInformation());
        assertEquals(company.getDomain(), this.company.getDomain());
        assertEquals(company.getSenderEmail(), this.company.getSenderEmail());
        assertEquals(company.getWorkTimeFrom(), this.company.getWorkTimeFrom());
        assertEquals(company.getWorkTimeTo(), this.company.getWorkTimeTo());
        assertEquals(company.getType(), this.company.getType());
        assertNotNull(company.getLogo());
        assertNotNull(company.getContacts());
        assertNotNull(company.getAddress());
        assertEquals(company.getSenderPass(), decrypt(this.company.getSenderPass()));
    }

    @Override
    protected CompanyEntity getInstance() {
        return this.company;
    }

    private String decrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.encrypt(value);
    }
}