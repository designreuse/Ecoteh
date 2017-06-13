package ua.com.ecoteh.entity.contacts;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsConverterTest extends ModelConverterTest<Contacts, ContactsEntity> {

    private static ContactsConverter converter;
    private static Contacts contacts;

    @BeforeClass
    public static void beforeClass() {
        contacts = getContacts();
        converter = new ContactsConverter(contacts);
    }

    @Override
    protected void checkEntity(final ContactsEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getEmail(), contacts.getEmail());
        assertEquals(entity.getMobilePhone(), contacts.getMobilePhone());
        assertEquals(entity.getLandlinesPhone(), contacts.getLandlinesPhone());
        assertEquals(entity.getFax(), contacts.getFax());
        assertEquals(entity.getVkontakte(), contacts.getVkontakte());
        assertEquals(entity.getFacebook(), contacts.getFacebook());
        assertEquals(entity.getTwitter(), contacts.getTwitter());
        assertEquals(entity.getSkype(), contacts.getSkype());
    }

    @Override
    protected ContactsConverter getConverter() {
        return converter;
    }

    @Override
    protected Contacts getModel() {
        return contacts;
    }
}
