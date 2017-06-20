package ua.com.ecoteh.entity.company;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.address.Address;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.content.ContentEditorTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class CompanyEditorTest extends ContentEditorTest<Company> {

    private CompanyEditor editor;
    private Company company;

    @Before
    public void beforeTests() {
        this.company = getCompany();
        this.editor = new CompanyEditor(this.company);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Company updatedCompany = this.editor.update();
        assertEquals(updatedCompany.getTagline(), this.company.getTagline());
        assertEquals(updatedCompany.getInformation(), this.company.getInformation());
        assertEquals(updatedCompany.getDomain(), this.company.getDomain());
        assertEquals(updatedCompany.getSenderEmail(), this.company.getSenderEmail());
        assertEquals(updatedCompany.getSenderPass(), this.company.getSenderPass());
        assertEquals(updatedCompany.getWorkTimeFrom(), this.company.getWorkTimeFrom());
        assertEquals(updatedCompany.getWorkTimeTo(), this.company.getWorkTimeTo());
        assertEquals(updatedCompany.getContacts(), this.company.getContacts());
        assertEquals(updatedCompany.getAddress(), this.company.getAddress());
        assertEquals(updatedCompany.getType(), this.company.getType());
    }

    @Test
    public void whenAddTaglineThenBuildWithIt() {
        Company company;
        String tagline;
        for (int i = 0; i < 5; i++) {
            tagline = TAGLINE + i;
            this.editor.addTagline(tagline);
            company = this.editor.update();
            assertEquals(company.getTagline(), tagline);
        }
    }

    @Test
    public void whenAddNullTaglineThenBuildWithEmptyIt() {
        this.editor.addTagline(null);
        final Company company = this.editor.update();
        assertEquals(company.getTagline(), this.company.getTagline());
    }

    @Test
    public void whenAddInformationThenBuildWithIt() {
        Company company;
        String information;
        for (int i = 0; i < 5; i++) {
            information = INFORMATION + i;
            this.editor.addInformation(information);
            company = this.editor.update();
            assertEquals(company.getInformation(), information);
        }
    }

    @Test
    public void whenAddNullInformationThenBuildWithEmptyIt() {
        this.editor.addInformation(null);
        final Company company = this.editor.update();
        assertEquals(company.getInformation(), this.company.getInformation());
    }

    @Test
    public void whenAddDomainThenBuildWithIt() {
        Company company;
        String domain;
        for (int i = 0; i < 5; i++) {
            domain = DOMAIN + i;
            this.editor.addDomain(domain);
            company = this.editor.update();
            assertEquals(company.getDomain(), domain);
        }
    }

    @Test
    public void whenAddNullDomainThenBuildWithEmptyIt() {
        this.editor.addDomain(null);
        final Company company = this.editor.update();
        assertEquals(company.getDomain(), this.company.getDomain());
    }

    @Test
    public void whenAddSenderEmailThenBuildWithIt() {
        Company company;
        String senderEmail;
        for (int i = 0; i < 5; i++) {
            senderEmail = EMAIL + i;
            this.editor.addSenderEmail(senderEmail);
            company = this.editor.update();
            assertEquals(company.getSenderEmail(), senderEmail);
        }
    }

    @Test
    public void whenAddNullSenderEmailThenBuildWithEmptyIt() {
        this.editor.addSenderEmail(null);
        final Company company = this.editor.update();
        assertEquals(company.getSenderEmail(), this.company.getSenderEmail());
    }

    @Test
    public void whenAddSenderPassThenBuildWithIt() {
        Company company;
        String senderPass;
        for (int i = 0; i < 5; i++) {
            senderPass = PASSWORD + i;
            this.editor.addSenderPass(senderPass);
            company = this.editor.update();
            assertEquals(company.getSenderPass(), senderPass);
        }
    }

    @Test
    public void whenAddNullSenderPassThenBuildWithEmptyIt() {
        this.editor.addSenderPass(null);
        final Company company = this.editor.update();
        assertEquals(company.getSenderPass(), this.company.getSenderPass());
    }

    @Test
    public void whenAddWorkTimeFromThenBuildWithIt() {
        this.editor.addWorkTimeFrom(TIME);
        final Company company = this.editor.update();
        assertEquals(company.getWorkTimeFrom(), TIME);
    }

    @Test
    public void whenAddNullWorkTimeFromThenBuildWithEmptyIt() {
        this.editor.addWorkTimeFrom(null);
        final Company company = this.editor.update();
        assertEquals(company.getWorkTimeFrom(), this.company.getWorkTimeFrom());
    }

    @Test
    public void whenAddWorkTimeToThenBuildWithIt() {
        this.editor.addWorkTimeTo(TIME);
        final Company company = this.editor.update();
        assertEquals(company.getWorkTimeTo(), TIME);
    }

    @Test
    public void whenAddNullWorkTimeToThenBuildWithEmptyIt() {
        this.editor.addWorkTimeTo(null);
        final Company company = this.editor.update();
        assertEquals(company.getWorkTimeTo(), this.company.getWorkTimeTo());
    }

    @Test
    public void whenAddContactsThenBuildWithIt() {
        final Contacts contacts = getContacts();
        this.editor.addContacts(contacts);
        final Company company = this.editor.update();
        assertEquals(company.getContacts(), contacts);
    }

    @Test
    public void whenAddNullContactsThenBuildWithEmptyIt() {
        this.editor.addContacts(null);
        final Company company = this.editor.update();
        assertEquals(company.getContacts(), this.company.getContacts());
    }

    @Test
    public void whenAddAddressThenBuildWithIt() {
        final Address address = getAddress();
        this.editor.addAddress(address);
        final Company company = this.editor.update();
        assertEquals(company.getAddress(), address);
    }

    @Test
    public void whenAddNullAddressThenBuildWithEmptyIt() {
        this.editor.addAddress(null);
        final Company company = this.editor.update();
        assertEquals(company.getAddress(), this.company.getAddress());
    }

    @Test
    public void whenAddTypeThenBuildWithIt() {
        Company company;
        for (CompanyType type : CompanyType.values()) {
            this.editor.addType(type);
            company = this.editor.update();
            assertEquals(company.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeThenBuildWithEmptyIt() {
        this.editor.addType((CompanyType) null);
        final Company company = this.editor.update();
        assertEquals(company.getType(), this.company.getType());
    }

    @Test
    public void whenAddTypeNameThenBuildWithIt() {
        Company company;
        for (CompanyType type : CompanyType.values()) {
            this.editor.addType(type.name());
            company = this.editor.update();
            assertEquals(company.getType(), type);
        }
    }

    @Test
    public void whenAddNullTypeNameThenBuildWithEmptyIt() {
        this.editor.addType((String) null);
        final Company company = this.editor.update();
        assertNotNull(company.getType());
    }

    @Test
    public void whenAddEmptyTypeNameThenBuildWithEmptyIt() {
        this.editor.addType("");
        final Company company = this.editor.update();
        assertNotNull(company.getType());
    }

    @Override
    protected CompanyEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Company getModel() {
        return this.company;
    }
}