package ua.com.ecoteh.entity.user;

import org.junit.BeforeClass;
import ua.com.ecoteh.entity.model.ModelConverterTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserConverterTest extends ModelConverterTest<User, UserEntity> {

    private static UserConverter converter;
    private static User user;

    @BeforeClass
    public static void beforeClass() {
        user = getUser();
        converter = new UserConverter(user);
    }

    @Override
    protected void checkEntity(final UserEntity entity) {
        super.checkEntity(entity);
        assertEquals(entity.getName(), user.getName());
        assertEquals(entity.getUrl(), user.getUrl());
        assertEquals(entity.getLogin(), encrypt(user.getLogin()));
        assertEquals(entity.getPassword(), encrypt(user.getPassword()));
        assertEquals(entity.getDescription(), user.getDescription());
        assertEquals(entity.getRole(), user.getRole());
        assertEquals(entity.isMailing(), user.isMailing());
        assertEquals(entity.isLocked(), user.isLocked());
        assertNotNull(entity.getContactsEntity());
        assertNotNull(entity.getPhotoEntity());
    }

    @Override
    protected UserConverter getConverter() {
        return converter;
    }

    @Override
    protected User getModel() {
        return user;
    }

    private String encrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.encrypt(value);
    }
}
