package ua.com.ecoteh.mocks.enity.test;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.contacts.ContactsEntity;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getContactsEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getContactsEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockContactsEntityTest extends MockModelEntityTest<ContactsEntity> {

    private static ContactsEntity contacts;
    private static Collection<ContactsEntity> contactses;

    @BeforeClass
    public static void beforeClass() {
        contacts = getContactsEntity();
        contactses = getContactsEntities();
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
    protected ContactsEntity getObject() {
        return contacts;
    }

    @Override
    protected Collection<ContactsEntity> getObjects() {
        return contactses;
    }
}
