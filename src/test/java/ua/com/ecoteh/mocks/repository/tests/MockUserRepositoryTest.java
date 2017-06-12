package ua.com.ecoteh.mocks.repository.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.repository.UserRepository;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntities;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getUserEntity;
import static ua.com.ecoteh.mocks.repository.MockRepository.getUserRepository;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class MockUserRepositoryTest extends MockDataRepositoryTest<UserEntity> {

    private static UserEntity user;
    private static Collection<UserEntity> users;
    private static UserRepository repository;

    @BeforeClass
    public static void beforeClass() {
        user = getUserEntity();
        users = getUserEntities();
        repository = getUserRepository();
    }

    @Test
    public void whenFindByNameThenReturnNotNull() {
        final UserEntity entity = repository.findByName(NAME);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownNameThenReturnNull() {
        final UserEntity entity = repository.findByName(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullNameThenReturnNull() {
        final UserEntity entity = repository.findByName(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByUrlThenReturnNotNull() {
        final UserEntity entity = repository.findByUrl(URL);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownUrlThenReturnNull() {
        final UserEntity entity = repository.findByUrl(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullUrlThenReturnNull() {
        final UserEntity entity = repository.findByUrl(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByContactsEmailThenReturnNotNull() {
        final UserEntity entity = repository.findByContactsEmail(EMAIL);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownContactsEmailThenReturnNull() {
        final UserEntity entity = repository.findByContactsEmail(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullContactsEmailThenReturnNull() {
        final UserEntity entity = repository.findByContactsEmail(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByContactsMobilePhoneThenReturnNotNull() {
        final UserEntity entity = repository.findByContactsMobilePhone(PHONE);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownContactsMobilePhoneThenReturnNull() {
        final UserEntity entity = repository.findByContactsMobilePhone(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullContactsMobilePhoneThenReturnNull() {
        final UserEntity entity = repository.findByContactsMobilePhone(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByContactsLandlinesPhoneThenReturnNotNull() {
        final UserEntity entity = repository.findByContactsLandlinesPhone(PHONE_2);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownContactsLandlinesPhoneThenReturnNull() {
        final UserEntity entity = repository.findByContactsLandlinesPhone(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullContactsLandlinesPhoneThenReturnNull() {
        final UserEntity entity = repository.findByContactsLandlinesPhone(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByContactsFaxThenReturnNotNull() {
        final UserEntity entity = repository.findByContactsFax(FAX);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownContactsFaxThenReturnNull() {
        final UserEntity entity = repository.findByContactsFax(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByNullContactsFaxThenReturnNull() {
        final UserEntity entity = repository.findByContactsFax(null);
        assertNull(entity);
    }

    @Test
    public void whenFindByLoginThenReturnNotNull() {
        final UserEntity entity = repository.findByLogin(LOGIN);
        assertNotNull(entity);
    }

    @Test
    public void whenFindByEncryptLoginThenReturnNotNull() {
        final Encryptor encryptor = new Base64Encryptor();
        final UserEntity entity = repository.findByLogin(encryptor.encrypt(LOGIN));
        assertNotNull(entity);
    }

    @Test
    public void whenFindByUnknownLoginThenReturnNull() {
        final UserEntity entity = repository.findByLogin(ANY_STRING);
        assertNull(entity);
    }

    @Test
    public void whenFindByEncryptUnknownLoginThenReturnNull() {
        final Encryptor encryptor = new Base64Encryptor();
        final UserEntity entity = repository.findByLogin(encryptor.encrypt(ANY_STRING));
        assertNull(entity);
    }

    @Test
    public void whenFindByNullLoginThenReturnNull() {
        final UserEntity entity = repository.findByLogin(null);
        assertNull(entity);
    }

    @Override
    protected UserRepository getRepository() {
        return repository;
    }

    @Override
    protected UserEntity getObject() {
        return user;
    }

    @Override
    protected Collection<UserEntity> getObjects() {
        return users;
    }
}
