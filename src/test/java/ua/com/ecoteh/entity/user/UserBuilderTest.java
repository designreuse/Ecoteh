package ua.com.ecoteh.entity.user;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelBuilderTest;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserBuilderTest extends ModelBuilderTest<User> {

    private UserBuilder builder;

    @Before
    public void beforeTests() {
        this.builder = new UserBuilder();
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final User user = this.builder.build();
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getDescription());
        assertNotNull(user.getContacts());
        assertNotNull(user.getPhoto());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenAddNameThenBuildWithIt() {
        User user;
        String name;
        for (int i = 0; i < 5; i++) {
            name = NAME + i;
            this.builder.addName(name);
            user = this.builder.build();
            assertEquals(user.getName(), name);
        }
    }

    @Test
    public void whenAddNullNameThenBuildWithEmptyIt() {
        this.builder.addName(null);
        final User user = this.builder.build();
        assertEquals(user.getName(), "");
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        User user;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.builder.addUrl(url);
            user = this.builder.build();
            assertEquals(user.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        this.builder.addUrl(null);
        final User user = this.builder.build();
        assertEquals(user.getUrl(), "");
    }

    @Test
    public void whenAddLoginThenBuildWithIt() {
        User user;
        String login;
        for (int i = 0; i < 5; i++) {
            login = URL + i;
            this.builder.addLogin(login);
            user = this.builder.build();
            assertEquals(user.getLogin(), login);
        }
    }

    @Test
    public void whenAddNullLoginThenBuildWithEmptyIt() {
        this.builder.addLogin(null);
        final User user = this.builder.build();
        assertEquals(user.getLogin(), "");
    }

    @Test
    public void whenAddPasswordThenBuildWithIt() {
        User user;
        String password;
        for (int i = 0; i < 5; i++) {
            password = PASSWORD + i;
            this.builder.addPassword(password);
            user = this.builder.build();
            assertEquals(user.getPassword(), password);
        }
    }

    @Test
    public void whenAddNullPasswordThenBuildWithEmptyIt() {
        this.builder.addPassword(null);
        final User user = this.builder.build();
        assertEquals(user.getPassword(), "");
    }

    @Test
    public void whenAddDescriptionThenBuildWithIt() {
        User user;
        String description;
        for (int i = 0; i < 5; i++) {
            description = DESCRIPTION + i;
            this.builder.addDescription(description);
            user = this.builder.build();
            assertEquals(user.getDescription(), description);
        }
    }

    @Test
    public void whenAddNullDescriptionThenBuildWithEmptyIt() {
        this.builder.addDescription(null);
        final User user = this.builder.build();
        assertEquals(user.getDescription(), "");
    }

    @Test
    public void whenAddContactsThenBuildWithIt() {
        final Contacts contacts = getContacts();
        this.builder.addContacts(contacts);
        final User user = this.builder.build();
        assertEquals(user.getContacts(), contacts);
    }

    @Test
    public void whenAddNullContactsThenBuildWithNew() {
        this.builder.addContacts(null);
        final User user = this.builder.build();
        assertNotNull(user.getContacts());
    }

    @Test
    public void whenAddPhotoThenBuildWithIt() {
        final File photo = getFile();
        this.builder.addPhoto(photo);
        final User user = this.builder.build();
        assertEquals(user.getPhoto(), photo);
    }

    @Test
    public void whenAddNullPhotoThenBuildWithNew() {
        this.builder.addPhoto(null);
        final User user = this.builder.build();
        assertNotNull(user.getPhoto());
    }

    @Test
    public void whenAddRoleThenBuildWithIt() {
        User user;
        for (UserRole role : UserRole.values()) {
            this.builder.addRole(role);
            user = this.builder.build();
            assertEquals(user.getRole(), role);
        }
    }

    @Test
    public void whenAddNullRoleThenBuildWithNew() {
        this.builder.addRole(null);
        final User user = this.builder.build();
        assertNotNull(user.getRole());
    }

    @Test
    public void whenAddMailingThenBuildWithIt() {
        this.builder.addMailing(true);
        User user = this.builder.build();
        assertTrue(user.isMailing());
        this.builder.addMailing(false);
        user = this.builder.build();
        assertFalse(user.isMailing());
    }

    @Test
    public void whenSetMailingThenBuildWithIt() {
        this.builder.isMail();
        User user = this.builder.build();
        assertTrue(user.isMailing());
    }

    @Test
    public void whenSetNotMailingThenBuildWithIt() {
        this.builder.isNotMail();
        User user = this.builder.build();
        assertFalse(user.isMailing());
    }

    @Test
    public void whenAddMLockedThenBuildWithIt() {
        this.builder.addLocked(true);
        User user = this.builder.build();
        assertTrue(user.isLocked());
        this.builder.addLocked(false);
        user = this.builder.build();
        assertFalse(user.isLocked());
    }

    @Test
    public void whenSetLockedThenBuildWithIt() {
        this.builder.isLock();
        User user = this.builder.build();
        assertTrue(user.isLocked());
    }

    @Test
    public void whenSetNotLockedThenBuildWithIt() {
        this.builder.isNotLock();
        User user = this.builder.build();
        assertFalse(user.isLocked());
    }

    @Override
    protected UserBuilder getBuilder() {
        return this.builder;
    }
}
