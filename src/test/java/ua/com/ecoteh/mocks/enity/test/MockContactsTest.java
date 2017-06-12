package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.contacts.Contacts;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;
import static ua.com.ecoteh.mocks.enity.MockModels.getContactses;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockContactsTest extends MockModelTest<Contacts> {

    private static Contacts contacts;
    private static Collection<Contacts> contactses;

    @BeforeClass
    public static void beforeClass() {
        contacts = getContacts();
        contactses = getContactses();
    }

    @Test
    public void whenGetEmailThenReturnNotEmpty() {
        assertFalse(contacts.getEmail().isEmpty());
    }

    @Test
    public void whenGetMobilePhoneThenReturnNotEmpty() {
        assertFalse(contacts.getMobilePhone().isEmpty());
    }

    @Test
    public void whenGetLandlinesPhoneThenReturnNotEmpty() {
        assertFalse(contacts.getLandlinesPhone().isEmpty());
    }

    @Test
    public void whenGetFaxThenReturnNotEmpty() {
        assertFalse(contacts.getFax().isEmpty());
    }

    @Test
    public void whenGetVkontakteThenReturnNotEmpty() {
        assertFalse(contacts.getVkontakte().isEmpty());
    }

    @Test
    public void whenGetFacebookThenReturnNotEmpty() {
        assertFalse(contacts.getFacebook().isEmpty());
    }

    @Test
    public void whenGetTwitterThenReturnNotEmpty() {
        assertFalse(contacts.getTwitter().isEmpty());
    }

    @Test
    public void whenGetSkypeThenReturnNotEmpty() {
        assertFalse(contacts.getSkype().isEmpty());
    }

    @Override
    protected Contacts getObject() {
        return contacts;
    }

    @Override
    protected Collection<Contacts> getObjects() {
        return contactses;
    }
}
