package ua.com.ecoteh.entity.user;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.contacts.ContactsEntity;
import ua.com.ecoteh.entity.file.FileEntity;
import ua.com.ecoteh.entity.model.ModelEntityTest;
import ua.com.ecoteh.util.encryption.Base64Encryptor;
import ua.com.ecoteh.util.encryption.Encryptor;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getContactsEntity;
import static ua.com.ecoteh.mocks.enity.MockModelEntities.getFileEntity;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserEntityTest extends ModelEntityTest {

    private UserEntity user;

    @Before
    public void beforeTests() {
        this.user = new UserEntity();
        this.user.setId(ID);
        this.user.setValidated(VALIDATION);
        this.user.setName(NAME);
        this.user.setUrl(URL);
        this.user.setLogin(LOGIN);
        this.user.setPassword(PASSWORD);
        this.user.setDescription(DESCRIPTION);
        this.user.setContactsEntity(getContactsEntity());
        this.user.setPhotoEntity(getFileEntity());
        this.user.setRole(USER_ROLE);
        this.user.setMailing(MAILING);
        this.user.setLocked(LOCKED);
    }

    @Test
    @Override
    public void toStringTest() {
        final String toStringTest = "UserEntity{" +
                "ModelEntity{" +
                "id=" + this.user.getId() +
                ", validated=" + this.user.isValidated() +
                '}' +
                ", name='" + this.user.getName() + '\'' +
                ", url='" + this.user.getUrl() + '\'' +
                ", login='" + this.user.getLogin() + '\'' +
                ", password='" + this.user.getPassword() + '\'' +
                ", description='" + this.user.getDescription() + '\'' +
                ", contacts=" + this.user.getContactsEntity() +
                ", photo=" + this.user.getPhotoEntity() +
                ", role=" + this.user.getRole() +
                ", isMailing=" + this.user.isMailing() +
                ", isLocked=" + this.user.isLocked() +
                '}';
        assertEquals(this.user.toString(), toStringTest);
    }

    @Test
    public void whenSetNameThenGetIt() {
        String name;
        for (int i = 0; i < 5; i++) {
            name = NAME + i;
            this.user.setName(name);
            assertEquals(this.user.getName(), name);
        }
    }

    @Test
    public void whenSetLoginThenGetIt() {
        String login;
        for (int i = 0; i < 5; i++) {
            login = LOGIN + i;
            this.user.setLogin(login);
            assertEquals(this.user.getLogin(), login);
        }
    }

    @Test
    public void whenSetPasswordThenGetIt() {
        String password;
        for (int i = 0; i < 5; i++) {
            password = PASSWORD + i;
            this.user.setPassword(password);
            assertEquals(this.user.getPassword(), password);
        }
    }

    @Test
    public void whenSetUrlThenGetIt() {
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.user.setUrl(url);
            assertEquals(this.user.getUrl(), url);
        }
    }

    @Test
    public void whenSetDescriptionThenGetIt() {
        String description;
        for (int i = 0; i < 5; i++) {
            description = DESCRIPTION + i;
            this.user.setDescription(description);
            assertEquals(this.user.getDescription(), description);
        }
    }

    @Test
    public void whenSetPhotoThenGetIt() {
        final FileEntity photo = getFileEntity();
        this.user.setPhotoEntity(photo);
        assertNotNull(this.user.getPhotoEntity());
        assertEquals(this.user.getPhotoEntity(), photo);
    }

    @Test
    public void whenSetContactsThenGetIt() {
        final ContactsEntity contacts = getContactsEntity();
        this.user.setContactsEntity(contacts);
        assertNotNull(this.user.getContactsEntity());
        assertEquals(this.user.getContactsEntity(), contacts);
    }

    @Test
    public void whenSetRoleThenGetIt() {
        for (UserRole role : UserRole.values()) {
            this.user.setRole(role);
            assertEquals(this.user.getRole(), role);
        }
    }

    @Test
    public void whenSetMailingThenGetIt() {
        this.user.setMailing(true);
        assertTrue(this.user.isMailing());
        this.user.setMailing(false);
        assertFalse(this.user.isMailing());
    }

    @Test
    public void whenSetLockedThenGetIt() {
        this.user.setLocked(true);
        assertTrue(this.user.isLocked());
        this.user.setLocked(false);
        assertFalse(this.user.isLocked());
    }

    @Test
    public void whenConvertThenReturnValidModel() {
        final User user = this.user.convert();
        assertEquals(user.getId(), this.user.getId());
        assertEquals(user.isValidated(), this.user.isValidated());
        assertEquals(user.getName(), this.user.getName());
        assertEquals(user.getUrl(), this.user.getUrl());
        assertEquals(user.getLogin(), decrypt(this.user.getLogin()));
        assertEquals(user.getPassword(), decrypt(this.user.getPassword()));
        assertEquals(user.getDescription(), this.user.getDescription());
        assertEquals(user.getRole(), this.user.getRole());
        assertEquals(user.isMailing(), this.user.isMailing());
        assertEquals(user.isLocked(), this.user.isLocked());
        assertNotNull(user.getContacts());
        assertNotNull(user.getPhoto());
    }

    @Override
    protected UserEntity getInstance() {
        return this.user;
    }

    private String decrypt(final String value) {
        final Encryptor encryptor = new Base64Encryptor();
        return encryptor.decrypt(value);
    }
}
