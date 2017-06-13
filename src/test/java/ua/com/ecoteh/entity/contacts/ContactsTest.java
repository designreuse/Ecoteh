package ua.com.ecoteh.entity.contacts;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.model.ModelTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsTest extends ModelTest {

    private static Contacts contacts;

    @BeforeClass
    public static void beforeClass() {
        contacts = new Contacts(
                ID, VALIDATION, EMAIL, PHONE, PHONE, PHONE,
                VKONTAKTE, FACEBOOK, TWITTER, SKYPE
        );
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "Contacts{" +
                "Model{id=" + contacts.getId() +
                ", validated=" + contacts.isValidated() + '}' +
                ", email='" + contacts.getEmail() + '\'' +
                ", mobilePhone='" + contacts.getMobilePhone() + '\'' +
                ", landlinesPhone='" + contacts.getLandlinesPhone() + '\'' +
                ", fax='" + contacts.getFax() + '\'' +
                ", vkontakte='" + contacts.getVkontakte() + '\'' +
                ", facebook='" + contacts.getFacebook() + '\'' +
                ", twitter='" + contacts.getTwitter() + '\'' +
                ", skype='" + contacts.getSkype() + '\'' +
                '}';
        assertEquals(contacts.toString(), testString);
    }

    @Test
    public void getEmail() throws Exception {
        assertFalse(contacts.getEmail().isEmpty());
    }

    @Test
    public void getMobilePhone() throws Exception {
        assertFalse(contacts.getMobilePhone().isEmpty());
    }

    @Test
    public void getLandlinesPhone() throws Exception {
        assertFalse(contacts.getLandlinesPhone().isEmpty());
    }

    @Test
    public void getFax() throws Exception {
        assertFalse(contacts.getFax().isEmpty());
    }

    @Test
    public void getVkontakte() throws Exception {
        assertFalse(contacts.getVkontakte().isEmpty());
    }

    @Test
    public void getFacebook() throws Exception {
        assertFalse(contacts.getFacebook().isEmpty());
    }

    @Test
    public void getTwitter() throws Exception {
        assertFalse(contacts.getTwitter().isEmpty());
    }

    @Test
    public void getSkype() throws Exception {
        assertFalse(contacts.getSkype().isEmpty());
    }

    @Test
    public void convert() throws Exception {
        super.convert();
        final ContactsEntity entity = contacts.convert();
        assertEquals(entity.getId(), contacts.getId());
        assertEquals(entity.isValidated(), contacts.isValidated());
        assertEquals(entity.getEmail(), contacts.getEmail());
        assertEquals(entity.getMobilePhone(), contacts.getMobilePhone());
        assertEquals(entity.getLandlinesPhone(), contacts.getLandlinesPhone());
        assertEquals(entity.getFax(), contacts.getFax());
        assertEquals(entity.getVkontakte(), contacts.getVkontakte());
        assertEquals(entity.getFacebook(), contacts.getFacebook());
        assertEquals(entity.getTwitter(), contacts.getTwitter());
        assertEquals(entity.getSkype(), contacts.getSkype());
    }

    @Test
    public void getBuilder() throws Exception {
        final ContactsBuilder builder = Contacts.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Contacts getInstance() {
        return contacts;
    }
}