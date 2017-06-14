package ua.com.ecoteh.entity.contacts;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;

import static org.junit.Assert.assertEquals;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getContactsEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class ContactsEntityConverterTest extends ModelEntityConverterTest<ContactsEntity, Contacts> {

    private static ContactsEntityConverter converter;
    private static ContactsEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getContactsEntity();
        converter = new ContactsEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final Contacts contacts) {
        super.checkEntity(contacts);
        assertEquals(contacts.getEmail(), entity.getEmail());
        assertEquals(contacts.getMobilePhone(), entity.getMobilePhone());
        assertEquals(contacts.getLandlinesPhone(), entity.getLandlinesPhone());
        assertEquals(contacts.getFax(), entity.getFax());
        assertEquals(contacts.getVkontakte(), entity.getVkontakte());
        assertEquals(contacts.getFacebook(), entity.getFacebook());
        assertEquals(contacts.getTwitter(), entity.getTwitter());
        assertEquals(contacts.getSkype(), entity.getSkype());
    }

    @Override
    protected ContactsEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected ContactsEntity getEntity() {
        return entity;
    }
}