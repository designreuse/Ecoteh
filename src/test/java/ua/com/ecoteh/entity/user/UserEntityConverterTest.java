package ua.com.ecoteh.entity.user;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelEntityConverterTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserEntityConverterTest extends ModelEntityConverterTest<UserEntity, User> {

    private static UserEntityConverter converter;
    private static UserEntity entity;

    @BeforeClass
    public static void beforeClass() {
        entity = getUserEntity();
        converter = new UserEntityConverter(entity);
    }

    @Override
    protected void checkEntity(final User user) {
        super.checkEntity(user);
        assertEquals(user.getName(), entity.getName());
        assertEquals(user.getUrl(), entity.getUrl());
        assertEquals(user.getLogin(), decrypt(entity.getLogin()));
        assertEquals(user.getPassword(), decrypt(entity.getPassword()));
        assertEquals(user.getDescription(), entity.getDescription());
        assertEquals(user.getRole(), entity.getRole());
        assertEquals(user.isMailing(), entity.isMailing());
        assertEquals(user.isLocked(), entity.isLocked());
        assertNotNull(user.getContacts());
        assertNotNull(user.getPhoto());
    }

    @Override
    protected UserEntityConverter getConverter() {
        return converter;
    }

    @Override
    protected UserEntity getEntity() {
        return entity;
    }

    private String decrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.decrypt(value);
    }
}