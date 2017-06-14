package ua.com.ecoteh.entity.user;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import ua.com.ecoteh.entity.model.Model;
import ua.com.ecoteh.entity.model.ModelTest;

import java.util.Collection;

import static org.junit.Assert.*;
import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockModels.getContacts;
import static ua.com.ecoteh.mocks.enity.MockModels.getFile;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserTest extends ModelTest {

    private static User user;

    @BeforeClass
    public static void beforeClass() {
        user = new User(
                ID, VALIDATION, NAME, URL, LOGIN, PASSWORD, DESCRIPTION,
                getContacts(), getFile(), USER_ROLE, MAILING, LOCKED
        );
    }

    @Test
    @Override
    public void toStringTest() throws Exception {
        final String testString = "User{" +
                "Model{id=" + user.getId() +
                ", validated=" + user.isValidated() + '}'+
                ", name='" + user.getName() + '\'' +
                ", url='" + user.getUrl() + '\'' +
                ", login='" + user.getLogin() + '\'' +
                ", password='" + user.getPassword() + '\'' +
                ", description='" + user.getDescription() + '\'' +
                ", contacts=" + user.getContacts() +
                ", photo=" + user.getPhoto() +
                ", role=" + user.getRole() +
                ", isMailing=" + user.isMailing() +
                ", isLocked=" + user.isLocked() +
                '}';
        assertEquals(user.toString(), testString);
    }

    @Test
    public void isAccountNonExpired() throws Exception {
        final UserEditor editor = user.getEditor();
        editor.isLock();
        final User lockedUser = editor.update();
        assertFalse(lockedUser.isAccountNonExpired());
        editor.isNotLock();
        final User notLockedUser = editor.update();
        assertTrue(notLockedUser.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() throws Exception {
        final UserEditor editor = user.getEditor();
        editor.isLock();
        final User lockedUser = editor.update();
        assertFalse(lockedUser.isAccountNonLocked());
        editor.isNotLock();
        final User notLockedUser = editor.update();
        assertTrue(notLockedUser.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() throws Exception {
        final UserEditor editor = user.getEditor();
        editor.isLock();
        final User lockedUser = editor.update();
        assertFalse(lockedUser.isCredentialsNonExpired());
        editor.isNotLock();
        final User notLockedUser = editor.update();
        assertTrue(notLockedUser.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() throws Exception {
        final UserEditor editor = user.getEditor();
        editor.isLock();
        final User lockedUser = editor.update();
        assertFalse(lockedUser.isEnabled());
        editor.isNotLock();
        final User notLockedUser = editor.update();
        assertTrue(notLockedUser.isEnabled());
    }

    @Test
    public void getAuthorities() throws Exception {
        final Collection<? extends GrantedAuthority> roles =  user.getAuthorities();
        assertNotNull(roles);
    }

    @Test
    public void getUsername() throws Exception {
        assertFalse(user.getUsername().isEmpty());
    }

    @Test
    public void getName() throws Exception {
        assertFalse(user.getName().isEmpty());
    }

    @Test
    public void getLogin() throws Exception {
        assertFalse(user.getLogin().isEmpty());
    }

    @Test
    public void getPassword() throws Exception {
        assertFalse(user.getPassword().isEmpty());
    }

    @Test
    public void getUrl() throws Exception {
        assertFalse(user.getUrl().isEmpty());
    }

    @Test
    public void getDescription() throws Exception {
        assertFalse(user.getDescription().isEmpty());
    }

    @Test
    public void getPhotoTest() throws Exception {
        assertNotNull(user.getPhoto());
    }

    @Test
    public void getContactsTest() throws Exception {
        assertNotNull(user.getContacts());
    }

    @Test
    public void getRole() throws Exception {
        assertNotNull(user.getRole());
    }

    @Test
    public void isMailing() throws Exception {
        user.isMailing();
    }

    @Test
    public void isLocked() throws Exception {
        user.isLocked();
    }

    @Test
    @Override
    public void convert() throws Exception {
        super.convert();
        final UserEntity entity = user.convert();
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.isValidated(), user.isValidated());
        assertEquals(entity.getName(), user.getName());
        assertEquals(entity.getUrl(), user.getUrl());
        assertFalse(entity.getLogin().isEmpty());
        assertFalse(entity.getPassword().isEmpty());
        assertEquals(entity.getDescription(), user.getDescription());
        assertEquals(entity.getRole(), user.getRole());
        assertEquals(entity.isMailing(), user.isMailing());
        assertEquals(entity.isLocked(), user.isLocked());
        assertNotNull(entity.getContactsEntity());
        assertNotNull(entity.getPhotoEntity());
    }

    @Test
    public void getBuilder() throws Exception {
        final UserBuilder builder = User.getBuilder();
        assertNotNull(builder);
    }

    @Override
    protected Model getInstance() {
        return user;
    }
}