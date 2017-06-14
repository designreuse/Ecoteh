package ua.com.ecoteh.entity.contacts;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsEditorTest extends ModelEditorTest<Contacts> {

    private ContactsEditor editor;
    private Contacts contacts;

    @Before
    public void beforeTests() {
        this.contacts = getContacts();
        this.editor = new ContactsEditor(this.contacts);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Contacts updatedContacts = this.editor.update();
        assertEquals(updatedContacts.getEmail(), this.contacts.getEmail());
        assertEquals(updatedContacts.getMobilePhone(), this.contacts.getMobilePhone());
        assertEquals(updatedContacts.getLandlinesPhone(), this.contacts.getLandlinesPhone());
        assertEquals(updatedContacts.getFax(), this.contacts.getFax());
        assertEquals(updatedContacts.getVkontakte(), this.contacts.getVkontakte());
        assertEquals(updatedContacts.getFacebook(), this.contacts.getFacebook());
        assertEquals(updatedContacts.getTwitter(), this.contacts.getTwitter());
        assertEquals(updatedContacts.getSkype(), this.contacts.getSkype());
    }

    @Test
    public void whenAddEmailThenBuildWithIt() {
        Contacts contacts;
        String email;
        for (int i = 0; i < 5; i++) {
            email = EMAIL + i;
            this.editor.addEmail(email);
            contacts = this.editor.update();
            assertEquals(contacts.getEmail(), email);
        }
    }

    @Test
    public void whenAddNullEmailThenBuildWithEmptyIt() {
        this.editor.addEmail(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getEmail(), this.contacts.getEmail());
    }

    @Test
    public void whenAddMobilePhoneThenBuildWithIt() {
        Contacts contacts;
        String mobilePhone;
        for (int i = 0; i < 5; i++) {
            mobilePhone = PHONE + i;
            this.editor.addMobilePhone(mobilePhone);
            contacts = this.editor.update();
            assertEquals(contacts.getMobilePhone(), mobilePhone);
        }
    }

    @Test
    public void whenAddNullMobilePhoneThenBuildWithEmptyIt() {
        this.editor.addMobilePhone(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getMobilePhone(), this.contacts.getMobilePhone());
    }

    @Test
    public void whenAddLandlinesPhoneThenBuildWithIt() {
        Contacts contacts;
        String landlinesPhone;
        for (int i = 0; i < 5; i++) {
            landlinesPhone = PHONE + i;
            this.editor.addLandlinesPhone(landlinesPhone);
            contacts = this.editor.update();
            assertEquals(contacts.getLandlinesPhone(), landlinesPhone);
        }
    }

    @Test
    public void whenAddNullLandlinesPhoneThenBuildWithEmptyIt() {
        this.editor.addLandlinesPhone(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getLandlinesPhone(), this.contacts.getLandlinesPhone());
    }

    @Test
    public void whenAddFaxThenBuildWithIt() {
        Contacts contacts;
        String fax;
        for (int i = 0; i < 5; i++) {
            fax = PHONE + i;
            this.editor.addFax(fax);
            contacts = this.editor.update();
            assertEquals(contacts.getFax(), fax);
        }
    }

    @Test
    public void whenAddNullFaxThenBuildWithEmptyIt() {
        this.editor.addFax(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getFax(), this.contacts.getFax());
    }

    @Test
    public void whenAddVkontakteThenBuildWithIt() {
        Contacts contacts;
        String vkontakte;
        for (int i = 0; i < 5; i++) {
            vkontakte = VKONTAKTE + i;
            this.editor.addVkontakte(vkontakte);
            contacts = this.editor.update();
            assertEquals(contacts.getVkontakte(), vkontakte);
        }
    }

    @Test
    public void whenAddNullVkontakteThenBuildWithEmptyIt() {
        this.editor.addVkontakte(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getVkontakte(), this.contacts.getVkontakte());
    }

    @Test
    public void whenAddFacebookThenBuildWithIt() {
        Contacts contacts;
        String facebook;
        for (int i = 0; i < 5; i++) {
            facebook = FACEBOOK + i;
            this.editor.addFacebook(facebook);
            contacts = this.editor.update();
            assertEquals(contacts.getFacebook(), facebook);
        }
    }

    @Test
    public void whenAddNullFacebookThenBuildWithEmptyIt() {
        this.editor.addFacebook(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getFacebook(), this.contacts.getFacebook());
    }

    @Test
    public void whenAddTwitterThenBuildWithIt() {
        Contacts contacts;
        String twitter;
        for (int i = 0; i < 5; i++) {
            twitter = TWITTER + i;
            this.editor.addTwitter(twitter);
            contacts = this.editor.update();
            assertEquals(contacts.getTwitter(), twitter);
        }
    }

    @Test
    public void whenAddNullTwitterThenBuildWithEmptyIt() {
        this.editor.addTwitter(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getTwitter(), this.contacts.getTwitter());
    }

    @Test
    public void whenAddSkypeThenBuildWithIt() {
        Contacts contacts;
        String skype;
        for (int i = 0; i < 5; i++) {
            skype = SKYPE + i;
            this.editor.addSkype(skype);
            contacts = this.editor.update();
            assertEquals(contacts.getSkype(), skype);
        }
    }

    @Test
    public void whenAddNullSkypeThenBuildWithEmptyIt() {
        this.editor.addSkype(null);
        final Contacts contacts = this.editor.update();
        assertEquals(contacts.getSkype(), this.contacts.getSkype());
    }

    @Override
    protected ContactsEditor getEditor() {
        return this.editor;
    }

    @Override
    protected Contacts getModel() {
        return this.contacts;
    }
}