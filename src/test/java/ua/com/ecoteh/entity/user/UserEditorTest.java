package ua.com.ecoteh.entity.user;

import org.junit.Before;
import org.junit.Test;
import ua.com.ecoteh.entity.contacts.Contacts;
import ua.com.ecoteh.entity.file.File;
import ua.com.ecoteh.entity.model.ModelEditorTest;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;
import static ua.com.ecoteh.mocks.enity.MockModels.getUser;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserEditorTest extends ModelEditorTest<User> {

    private UserEditor editor;
    private User user;

    @Before
    public void beforeTests() {
        this.user = getUser();
        this.editor = new UserEditor(this.user);
    }

    @Test
    @Override
    public void whenBuildEmptyThenReturnValidModel() {
        super.whenBuildEmptyThenReturnValidModel();
        final User updatedUser = this.editor.update();
        assertEquals(updatedUser.getName(), this.user.getName());
        assertEquals(updatedUser.getUrl(), this.user.getUrl());
        assertEquals(updatedUser.getLogin(), this.user.getLogin());
        assertEquals(updatedUser.getPassword(), this.user.getPassword());
        assertEquals(updatedUser.getDescription(), this.user.getDescription());
        assertEquals(updatedUser.getContacts(), this.user.getContacts());
        assertEquals(updatedUser.getPhoto(), this.user.getPhoto());
        assertEquals(updatedUser.getRole(), this.user.getRole());
    }

    @Test
    public void whenAddNameThenBuildWithIt() {
        User user;
        String name;
        for (int i = 0; i < 5; i++) {
            name = NAME + i;
            this.editor.addName(name);
            user = this.editor.update();
            assertEquals(user.getName(), name);
        }
    }

    @Test
    public void whenAddNullNameThenBuildWithEmptyIt() {
        this.editor.addName(null);
        final User user = this.editor.update();
        assertEquals(user.getName(), this.user.getName());
    }

    @Test
    public void whenAddUrlThenBuildWithIt() {
        User user;
        String url;
        for (int i = 0; i < 5; i++) {
            url = URL + i;
            this.editor.addUrl(url);
            user = this.editor.update();
            assertEquals(user.getUrl(), url);
        }
    }

    @Test
    public void whenAddNullUrlThenBuildWithEmptyIt() {
        this.editor.addUrl(null);
        final User user = this.editor.update();
        assertEquals(user.getUrl(), this.user.getUrl());
    }

    @Test
    public void whenAddLoginThenBuildWithIt() {
        User user;
        String login;
        for (int i = 0; i < 5; i++) {
            login = URL + i;
            this.editor.addLogin(login);
            user = this.editor.update();
            assertEquals(user.getLogin(), login);
        }
    }

    @Test
    public void whenAddNullLoginThenBuildWithEmptyIt() {
        this.editor.addLogin(null);
        final User user = this.editor.update();
        assertEquals(user.getLogin(), this.user.getLogin());
    }

    @Test
    public void whenAddPasswordThenBuildWithIt() {
        User user;
        String password;
        for (int i = 0; i < 5; i++) {
            password = PASSWORD + i;
            this.editor.addPassword(password);
            user = this.editor.update();
            assertEquals(user.getPassword(), password);
        }
    }

    @Test
    public void whenAddNullPasswordThenBuildWithEmptyIt() {
        this.editor.addPassword(null);
        final User user = this.editor.update();
        assertEquals(user.getPassword(), this.user.getPassword());
    }

    @Test
    public void whenAddDescriptionThenBuildWithIt() {
        User user;
        String description;
        for (int i = 0; i < 5; i++) {
            description = DESCRIPTION + i;
            this.editor.addDescription(description);
            user = this.editor.update();
            assertEquals(user.getDescription(), description);
        }
    }

    @Test
    public void whenAddNullDescriptionThenBuildWithEmptyIt() {
        this.editor.addDescription(null);
        final User user = this.editor.update();
        assertEquals(user.getDescription(), this.user.getDescription());
    }

    @Test
    public void whenAddContactsThenBuildWithIt() {
        final Contacts contacts = getContacts();
        this.editor.addContacts(contacts);
        final User user = this.editor.update();
        assertEquals(user.getContacts(), contacts);
    }

    @Test
    public void whenAddNullContactsThenBuildWithNew() {
        this.editor.addContacts(null);
        final User user = this.editor.update();
        assertEquals(user.getContacts(), this.user.getContacts());
    }

    @Test
    public void whenAddPhotoThenBuildWithIt() {
        final File photo = getFile();
        this.editor.addPhoto(photo);
        final User user = this.editor.update();
        assertEquals(user.getPhoto(), photo);
    }

    @Test
    public void whenAddNullPhotoThenBuildWithNew() {
        this.editor.addPhoto(null);
        final User user = this.editor.update();
        assertEquals(user.getPhoto(), this.user.getPhoto());
    }

    @Test
    public void whenAddRoleThenBuildWithIt() {
        User user;
        for (UserRole role : UserRole.values()) {
            this.editor.addRole(role);
            user = this.editor.update();
            assertEquals(user.getRole(), role);
        }
    }

    @Test
    public void whenAddNullRoleThenBuildWithNew() {
        this.editor.addRole((UserRole) null);
        final User user = this.editor.update();
        assertEquals(user.getRole(), this.user.getRole());
    }

    @Test
    public void whenAddUserRoleNameThenBuildWithIt() {
        User user;
        for (UserRole role : UserRole.values()) {
            this.editor.addRole(role.name());
            user = this.editor.update();
            assertEquals(user.getRole(), role);
        }
    }

    @Test
    public void whenAddNullUserRoleNameThenBuildWithNew() {
        this.editor.addRole((String) null);
        final User user = this.editor.update();
        assertNotNull(user.getRole());
    }

    @Test
    public void whenAddEmptyUserRoleNameThenBuildWithNew() {
        this.editor.addRole("");
        final User user = this.editor.update();
        assertNotNull(user.getRole());
    }

    @Test
    public void whenAddMailingThenBuildWithIt() {
        this.editor.addMailing(true);
        User user = this.editor.update();
        assertTrue(user.isMailing());
        this.editor.addMailing(false);
        user = this.editor.update();
        assertFalse(user.isMailing());
    }

    @Test
    public void whenSetMailingThenBuildWithIt() {
        this.editor.isMail();
        User user = this.editor.update();
        assertTrue(user.isMailing());
    }

    @Test
    public void whenSetNotMailingThenBuildWithIt() {
        this.editor.isNotMail();
        User user = this.editor.update();
        assertFalse(user.isMailing());
    }

    @Test
    public void whenAddMLockedThenBuildWithIt() {
        this.editor.addLocked(true);
        User user = this.editor.update();
        assertTrue(user.isLocked());
        this.editor.addLocked(false);
        user = this.editor.update();
        assertFalse(user.isLocked());
    }

    @Test
    public void whenSetLockedThenBuildWithIt() {
        this.editor.isLock();
        User user = this.editor.update();
        assertTrue(user.isLocked());
    }

    @Test
    public void whenSetNotLockedThenBuildWithIt() {
        this.editor.isNotLock();
        User user = this.editor.update();
        assertFalse(user.isLocked());
    }

    @Override
    protected UserEditor getEditor() {
        return this.editor;
    }

    @Override
    protected User getModel() {
        return this.user;
    }
}