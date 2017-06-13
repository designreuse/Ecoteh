package ua.com.ecoteh.entity.contacts;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsBuilderTest extends ModelBuilderTest<Contacts> {

    private ContactsBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new ContactsBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final Contacts contacts = this.builder.build();
        assertNotNull(contacts.getEmail());
        assertNotNull(contacts.getMobilePhone());
        assertNotNull(contacts.getLandlinesPhone());
        assertNotNull(contacts.getFax());
        assertNotNull(contacts.getVkontakte());
        assertNotNull(contacts.getFacebook());
        assertNotNull(contacts.getTwitter());
        assertNotNull(contacts.getSkype());
    }

    @Test
    public void whenAddEmailThenBuildWithIt() {
        Contacts contacts;
        String email;
        for (int i = 0; i < 5; i++) {
            email = EMAIL + i;
            this.builder.addEmail(email);
            contacts = this.builder.build();
            assertEquals(contacts.getEmail(), email);
        }
    }

    @Test
    public void whenAddNullEmailThenBuildWithEmptyIt() {
        this.builder.addEmail(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getEmail().isEmpty());
    }

    @Test
    public void whenAddMobilePhoneThenBuildWithIt() {
        Contacts contacts;
        String mobilePhone;
        for (int i = 0; i < 5; i++) {
            mobilePhone = PHONE + i;
            this.builder.addMobilePhone(mobilePhone);
            contacts = this.builder.build();
            assertEquals(contacts.getMobilePhone(), mobilePhone);
        }
    }

    @Test
    public void whenAddNullMobilePhoneThenBuildWithEmptyIt() {
        this.builder.addMobilePhone(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getMobilePhone().isEmpty());
    }

    @Test
    public void whenAddLandlinesPhoneThenBuildWithIt() {
        Contacts contacts;
        String landlinesPhone;
        for (int i = 0; i < 5; i++) {
            landlinesPhone = PHONE + i;
            this.builder.addLandlinesPhone(landlinesPhone);
            contacts = this.builder.build();
            assertEquals(contacts.getLandlinesPhone(), landlinesPhone);
        }
    }

    @Test
    public void whenAddNullLandlinesPhoneThenBuildWithEmptyIt() {
        this.builder.addLandlinesPhone(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getLandlinesPhone().isEmpty());
    }

    @Test
    public void whenAddFaxThenBuildWithIt() {
        Contacts contacts;
        String fax;
        for (int i = 0; i < 5; i++) {
            fax = PHONE + i;
            this.builder.addFax(fax);
            contacts = this.builder.build();
            assertEquals(contacts.getFax(), fax);
        }
    }

    @Test
    public void whenAddNullFaxThenBuildWithEmptyIt() {
        this.builder.addFax(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getFax().isEmpty());
    }

    @Test
    public void whenAddVkontakteThenBuildWithIt() {
        Contacts contacts;
        String vkontakte;
        for (int i = 0; i < 5; i++) {
            vkontakte = VKONTAKTE + i;
            this.builder.addVkontakte(vkontakte);
            contacts = this.builder.build();
            assertEquals(contacts.getVkontakte(), vkontakte);
        }
    }

    @Test
    public void whenAddNullVkontakteThenBuildWithEmptyIt() {
        this.builder.addVkontakte(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getVkontakte().isEmpty());
    }

    @Test
    public void whenAddFacebookThenBuildWithIt() {
        Contacts contacts;
        String facebook;
        for (int i = 0; i < 5; i++) {
            facebook = FACEBOOK + i;
            this.builder.addFacebook(facebook);
            contacts = this.builder.build();
            assertEquals(contacts.getFacebook(), facebook);
        }
    }

    @Test
    public void whenAddNullFacebookThenBuildWithEmptyIt() {
        this.builder.addFacebook(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getFacebook().isEmpty());
    }

    @Test
    public void whenAddTwitterThenBuildWithIt() {
        Contacts contacts;
        String twitter;
        for (int i = 0; i < 5; i++) {
            twitter = TWITTER + i;
            this.builder.addTwitter(twitter);
            contacts = this.builder.build();
            assertEquals(contacts.getTwitter(), twitter);
        }
    }

    @Test
    public void whenAddNullTwitterThenBuildWithEmptyIt() {
        this.builder.addTwitter(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getTwitter().isEmpty());
    }

    @Test
    public void whenAddSkypeThenBuildWithIt() {
        Contacts contacts;
        String skype;
        for (int i = 0; i < 5; i++) {
            skype = SKYPE + i;
            this.builder.addSkype(skype);
            contacts = this.builder.build();
            assertEquals(contacts.getSkype(), skype);
        }
    }

    @Test
    public void whenAddNullSkypeThenBuildWithEmptyIt() {
        this.builder.addSkype(null);
        final Contacts contacts = this.builder.build();
        assertTrue(contacts.getSkype().isEmpty());
    }

    @Override
    protected ContactsBuilder getBuilder() {
        return this.builder;
    }
}