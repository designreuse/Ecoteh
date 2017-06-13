package ua.com.ecoteh.entity.contacts;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelEntityTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsEntityTest extends ModelEntityTest {

    private ContactsEntity contacts;

    @Before
    public void beforeTests() {
        this.contacts = new ContactsEntity();
        this.contacts.setId(ID);
        this.contacts.setValidated(VALIDATION);
        this.contacts.setEmail(EMAIL);
        this.contacts.setMobilePhone(PHONE);
        this.contacts.setLandlinesPhone(PHONE);
        this.contacts.setFax(PHONE);
        this.contacts.setVkontakte(VKONTAKTE);
        this.contacts.setFacebook(FACEBOOK);
        this.contacts.setTwitter(TWITTER);
        this.contacts.setSkype(SKYPE);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "ContactsEntity{" +
                "ModelEntity{" +
                "id=" + this.contacts.getId() +
                ", validated=" + this.contacts.isValidated() +
                '}' +
                ", email='" + this.contacts.getEmail() + '\'' +
                ", mobilePhone='" + this.contacts.getMobilePhone() + '\'' +
                ", landlinesPhone='" + this.contacts.getLandlinesPhone() + '\'' +
                ", fax='" + this.contacts.getFax() + '\'' +
                ", vkontakte='" + this.contacts.getVkontakte() + '\'' +
                ", facebook='" + this.contacts.getFacebook() + '\'' +
                ", twitter='" + this.contacts.getTwitter() + '\'' +
                ", skype='" + this.contacts.getSkype() + '\'' +
                '}';
        assertEquals(this.contacts.toString(), toStringTest);
    }

    @Test
    public void whenSetEmailThenGetIt() {
        String email;
        for (int i = 0; i < 5; i++) {
            email = EMAIL + i;
            this.contacts.setEmail(email);
            assertEquals(this.contacts.getEmail(), email);
        }
    }

    @Test
    public void whenSetMobilePhoneThenGetIt() {
        String mobilePhone;
        for (int i = 0; i < 5; i++) {
            mobilePhone = PHONE + i;
            this.contacts.setMobilePhone(mobilePhone);
            assertEquals(this.contacts.getMobilePhone(), mobilePhone);
        }
    }

    @Test
    public void whenSetLandlinesPhoneThenGetIt() {
        String landlinesPhone;
        for (int i = 0; i < 5; i++) {
            landlinesPhone = PHONE + i;
            this.contacts.setLandlinesPhone(landlinesPhone);
            assertEquals(this.contacts.getLandlinesPhone(), landlinesPhone);
        }
    }

    @Test
    public void whenSetFaxThenGetIt() {
        String fax;
        for (int i = 0; i < 5; i++) {
            fax = PHONE + i;
            this.contacts.setFax(fax);
            assertEquals(this.contacts.getFax(), fax);
        }
    }

    @Test
    public void whenSetVkontakteThenGetIt() {
        String vkontakte;
        for (int i = 0; i < 5; i++) {
            vkontakte = VKONTAKTE + i;
            this.contacts.setVkontakte(vkontakte);
            assertEquals(this.contacts.getVkontakte(), vkontakte);
        }
    }

    @Test
    public void whenSetFacebookThenGetIt() {
        String facebook;
        for (int i = 0; i < 5; i++) {
            facebook = FACEBOOK + i;
            this.contacts.setFacebook(facebook);
            assertEquals(this.contacts.getFacebook(), facebook);
        }
    }

    @Test
    public void whenSetTwitterThenGetIt() {
        String twitter;
        for (int i = 0; i < 5; i++) {
            twitter = TWITTER + i;
            this.contacts.setTwitter(twitter);
            assertEquals(this.contacts.getTwitter(), twitter);
        }
    }

    @Test
    public void whenSetSkypeThenGetIt() {
        String skype;
        for (int i = 0; i < 5; i++) {
            skype = SKYPE + i;
            this.contacts.setSkype(skype);
            assertEquals(this.contacts.getSkype(), skype);
        }
    }
    
    @Test
    public void whenConvertThenReturnValidModel() {
        final Contacts contacts = this.contacts.convert();
        assertEquals(contacts.getId(), this.contacts.getId());
        assertEquals(contacts.isValidated(), this.contacts.isValidated());
        assertEquals(contacts.getEmail(), this.contacts.getEmail());
        assertEquals(contacts.getMobilePhone(), this.contacts.getMobilePhone());
        assertEquals(contacts.getLandlinesPhone(), this.contacts.getLandlinesPhone());
        assertEquals(contacts.getFax(), this.contacts.getFax());
        assertEquals(contacts.getVkontakte(), this.contacts.getVkontakte());
        assertEquals(contacts.getFacebook(), this.contacts.getFacebook());
        assertEquals(contacts.getTwitter(), this.contacts.getTwitter());
        assertEquals(contacts.getSkype(), this.contacts.getSkype());
    }

    @Override
    protected ContactsEntity getInstance() {
        return this.contacts;
    }
}