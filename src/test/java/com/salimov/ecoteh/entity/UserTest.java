package com.salimov.ecoteh.entity;

import com.salimov.ecoteh.enums.UserRole;
import com.salimov.ecoteh.util.translator.Translator;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.salimov.ecoteh.mocks.MockConstants.*;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getFile;
import static com.salimov.ecoteh.mocks.enity.MockEntity.getUser;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public final class UserTest extends ModelTest<User> {

    @Test
    public void whenInitializeByConstructorThenSetNotNullRole() {
        final User user = new User();
        assertNotNull(user.getRole());
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final User user = new User(null, null, null);
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final User user = new User("", "", null);
        assertNotNull(user.getRole());
        assertNotNull(user.getContacts());
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final User user = new User(" ", " ", null);
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getContacts());
        assertNotNull(user.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final User user = new User("   ", "   ", null);
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getContacts());
        assertNotNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final User user = new User(NAME, EMAIL, null);
        assertNotNull(user.getName());
        assertNotNull(user.getContacts());
        assertNotNull(user.getUrl());
        assertNotNull(user.getRole());
        assertEquals(user.getName(), NAME);
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void toStringTest() {
        final User user = getUser();
        assertNotNull(user.toString());
        final String userToString = "User{" +
                "Model{" +
                "id=" + user.getId() +
                ", validated=" + user.isValidated() +
                '}' +
                ", name='" + user.getName() + '\'' +
                ", url='" + user.getUrl() + '\'' +
                ", encryptedLogin='" + user.getEncryptedLogin() + '\'' +
                ", encryptedPassword='" + user.getEncryptedPassword() + '\'' +
                ", Login='" + user.getLogin() + '\'' +
                ", Password='" + user.getPassword() + '\'' +
                ", description='" + user.getDescription() + '\'' +
                ", " + user.getContacts() +
                ", " + user.getPhoto() +
                ", role=" + user.getRole() +
                ", isMailing=" + user.isMailing() +
                ", isLocked=" + user.isLocked() +
                '}';
        assertEquals(user.toString(), userToString);
    }

    @Test
    public void equalsInvalidObjects() {
        final User user1 = new User();
        final User user2 = new User();
        assertTrue(user1.equals(user2));
        user1.setName(NAME);
        user2.setName(NAME);
        assertTrue(user1.equals(user2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final User user1 = getUser();
        final User user2 = user1.clone();
        assertEquals(user1, user2);
        final boolean value = user1.getName().equalsIgnoreCase(user2.getName());
        assertEquals(user1.equals(user2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final User user = new User();
        user.setName(NAME);
        int value = user.getName().hashCode() +
                user.getDescription().hashCode() +
                user.getRole().hashCode();
        assertEquals(user.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final User user = getUser();
        int value = user.getName().hashCode() +
                user.getDescription().hashCode() +
                user.getRole().hashCode();
        assertEquals(user.hashCode(), value);
    }

    @Test
    public void whenIsAccountNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isAccountNonExpired());
        assertTrue(user.isAccountNonExpired() || !user.isAccountNonExpired());
    }

    @Test
    public void whenIsAccountNonLockedThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isAccountNonLocked());
        assertTrue(user.isAccountNonLocked() || !user.isAccountNonLocked());
    }

    @Test
    public void whenIsCredentialsNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isCredentialsNonExpired());
        assertTrue(user.isCredentialsNonExpired() || !user.isCredentialsNonExpired());
    }

    @Test
    public void whenIsEnabledNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isEnabled());
        assertTrue(user.isEnabled() || !user.isEnabled());
    }

    @Test
    public void whenGetAuthoritiesThenReturnsNotEmptyCollections() {
        assertFalse(getUser().getAuthorities().isEmpty());
    }

    @Test
    public void whenGetAuthoritiesThenItMustContainsUserRole() {
        final User user = getUser();
        assertTrue(
                user.getAuthorities().contains(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole().name()
                        )
                )
        );
    }

    @Test
    public void whenGetUsernameThenReturnsNotNull() {
        assertNotNull(getUser().getUsername());
    }

    @Test
    public void whenGetLoginThenReturnsNotNull() {
        assertNotNull(getUser().getLogin());
    }

    @Test
    public void usernameMustEqualsLogin() {
        final User user = getUser();
        assertEquals(user.getUsername(), user.getLogin());
    }

    @Test
    public void whenGetPasswordThenReturnsNotNull() {
        assertNotNull(getUser().getPassword());
    }

    @Test
    public void whenSetNullNameThenGetEmptyString() {
        final User user = new User();
        user.setName(null);
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
    }

    @Test
    public void whenSetBlankNameThenGetEmptyString() {
        final User user = new User();
        user.setName("");
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
        user.setName(" ");
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
        assertEquals(user.getName(), "");
    }

    @Test
    public void whenSetValidNameThenGetThisName() {
        final User user = new User();
        user.setName(NAME);
        assertNotNull(user.getName());
        assertNotNull(user.getUrl());
        assertEquals(user.getName(), NAME);
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetNullUrlThenGetEmptyString() {
        final User user = getUser();
        user.setUrl(null);
        assertNotNull(user.getUrl());
        assertEquals(user.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final User user = getUser();
        user.setUrl(null);
        assertNotNull(user.getUrl());
        user.setUrl("");
        assertNotNull(user.getUrl());
        user.setUrl(" ");
        assertNotNull(user.getUrl());
        assertEquals(user.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetEmptyString() {
        final User user = getUser();
        user.translateAndSetUrl(null);
        assertNotNull(user.getUrl());
        assertEquals(user.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetBlankUrlThenGetEmptyString() {
        final User user = getUser();
        user.translateAndSetUrl("");
        assertNotNull(user.getUrl());
        user.translateAndSetUrl(" ");
        assertNotNull(user.getUrl());
        assertEquals(user.getUrl(), "");
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final User user = getUser();
        user.setUrl(NAME);
        assertNotNull(user.getUrl());
        assertEquals(user.getUrl(), NAME);
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final User user = getUser();
        user.translateAndSetUrl(NAME);
        assertNotNull(user.getUrl());
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetNullLoginThenGetEmptyString() {
        final User user = new User();
        user.setLogin(null);
        assertNotNull(user.getLogin());
        assertEquals(user.getLogin(), "");
    }

    @Test
    public void whenSetBlankLoginThenGetEmptyString() {
        final User user = new User();
        user.setLogin("");
        assertNotNull(user.getLogin());
        user.setLogin(" ");
        assertNotNull(user.getLogin());
    }

    @Test
    public void whenSetValidLoginThenGetThisLogin() {
        final User user = new User();
        user.setLogin(LOGIN);
        assertNotNull(user.getLogin());
        assertEquals(user.getLogin(), LOGIN);
    }

    @Test
    public void whenSetNullPasswordThenGetEmptyString() {
        final User user = getUser();
        user.setPassword(null);
        assertNotNull(user.getPassword());
        assertEquals(user.getPassword(), "");
    }

    @Test
    public void whenSetBlankPasswordThenGetEmptyString() {
        final User user = getUser();
        user.setPassword("");
        assertNotNull(user.getPassword());
        user.setPassword(" ");
        assertNotNull(user.getPassword());
        assertEquals(user.getPassword(), "");
    }

    @Test
    public void whenSetValidPasswordThenGetThisPassword() {
        final User user = getUser();
        user.setPassword(PASSWORD);
        assertNotNull(user.getPassword());
        assertEquals(user.getPassword(), PASSWORD);
    }

    @Test
    public void whenSetNullDescriptionThenGetEmptyString() {
        final User user = getUser();
        user.setDescription(null);
        assertNotNull(user.getDescription());
        assertEquals(user.getDescription(), "");
    }

    @Test
    public void whenSetBlankDescriptionThenGetEmptyString() {
        final User user = getUser();
        user.setDescription("");
        assertNotNull(user.getDescription());
        user.setDescription(" ");
        assertNotNull(user.getDescription());
        user.setDescription("   ");
        assertNotNull(user.getDescription());
        assertEquals(user.getDescription(), "");
    }

    @Test
    public void whenSetValidDescriptionThenGetThisDescription() {
        final User user = getUser();
        user.setDescription(DESCRIPTION);
        assertNotNull(user.getDescription());
        assertEquals(user.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNotNull() {
        final User user = getUser();
        user.setPhoto(null);
        assertNotNull(user.getPhoto());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final User user = getUser();
        user.setPhoto(getFile());
        assertNotNull(user.getPhoto());
        assertEquals(user.getPhoto(), getFile());
    }

    @Test
    public void whenSetNullRoleThenGetUserRoleANOTHER() {
        final User user = getUser();
        user.setRole(null);
        assertNotNull(user.getRole());
        assertEquals(user.getRole(), UserRole.ANOTHER);
    }

    @Test
    public void whenSetNotNullRoleThenGetThisRole() {
        final User user = getUser();
        final UserRole role = UserRole.ANOTHER;
        user.setRole(role);
        assertNotNull(user.getRole());
        assertEquals(user.getRole(), role);
    }

    @Test
    public void getRoleEnums() {
        final UserRole[] roles = UserRole.getEnumConstants();
        assertNotNull(roles);
        assertFalse(roles.length == 0);
    }

    @Test
    public void userRoleValueOf() {
        for (UserRole temp : UserRole.values()) {
            final UserRole role = UserRole.valueOf(temp.name());
            assertNotNull(role);
            assertEquals(role, temp);
        }
    }

    @Test
    public void whenGetUsernameThenReturnNotNull() {
        final User user = new User();
        assertNotNull(user.getUsername());
        assertEquals(user.getUsername(), "");
    }

    @Test
    public void whenGetUsernameThenGetsLogin() {
        final User user = new User();
        user.setLogin(LOGIN);
        assertNotNull(user.getUsername());
        assertEquals(
                user.getUsername(),
                user.getLogin()
        );
    }

    @Test
    public void whenSetLockedThenGetsItValue() {
        final User user = new User();
        user.setLocked(true);
        assertTrue(user.isLocked());
        user.setLocked(false);
        assertFalse(user.isLocked());
    }

    @Test
    public void whenSetLockedTrueThenSetAccountNonExpiredFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isAccountNonExpired());
    }

    @Test
    public void whenSetLockedFalseThenSetAccountNonExpiredTrue() {
        final User user = new User();
        user.setLocked(false);
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void whenSetLockedTrueThenSetAccountNonLockedFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isAccountNonLocked());
    }

    @Test
    public void whenSetLockedFalseThenSetAccountNonLockedTrue() {
        final User user = new User();
        user.setLocked(false);
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void whenSetLockedTrueThenSetCredentialsNonExpiredFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isCredentialsNonExpired());
    }

    @Test
    public void whenSetLockedFalseThenSetCredentialsNonExpiredTrue() {
        final User user = new User();
        user.setLocked(false);
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void whenSetLockedTrueThenSetEnabledFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isEnabled());
    }

    @Test
    public void whenSetLockedFalseThenSetEnabledTrue() {
        final User user = new User();
        user.setLocked(false);
        assertTrue(user.isEnabled());
    }

    @Test
    public void whenSetLockedTrueThenSetValidatedFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isValidated());
    }

    @Test
    public void whenSetLockedTrueThenSetMailingFalse() {
        final User user = new User();
        user.setLocked(true);
        assertFalse(user.isMailing());
    }

    @Test
    public void whenSetMailingThenGetsItValue() {
        final User user = new User();
        user.setMailing(true);
        assertTrue(user.isMailing());
        user.setMailing(false);
        assertFalse(user.isMailing());
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final User model1 = getInstance();
        final User model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final User model1 = getInstance();
        final User model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected User getObject() {
        return getUser();
    }

    @Override
    protected User getInstance() {
        return new User();
    }
}
