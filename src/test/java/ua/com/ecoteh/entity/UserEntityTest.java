package ua.com.ecoteh.entity;

import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.entity.user.UserRole;
import ua.com.ecoteh.util.translator.Translator;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static ua.com.ecoteh.mocks.MockConstants.*;
import static ua.com.ecoteh.mocks.enity.MockEntity.getFileEntity;
import static ua.com.ecoteh.mocks.enity.MockEntity.getUserEntity;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public final class UserEntityTest extends ModelTest<UserEntity> {

    @Test
    public void whenInitializeByConstructorThenSetNotNullRole() {
        final UserEntity userEntity = new UserEntity();
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveEmptyString() {
        final UserEntity userEntity = new UserEntity(null, null, null);
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_1() {
        final UserEntity userEntity = new UserEntity("", "", null);
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getContactsEntity());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
        assertNotNull(userEntity.getRole());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_2() {
        final UserEntity userEntity = new UserEntity(" ", " ", null);
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getContactsEntity());
        assertNotNull(userEntity.getUrl());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveEmptyString_3() {
        final UserEntity userEntity = new UserEntity("   ", "   ", null);
        assertNotNull(userEntity.getRole());
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getContactsEntity());
        assertNotNull(userEntity.getUrl());
        assertNotNull(userEntity.getRole());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final UserEntity userEntity = new UserEntity(NAME, EMAIL, null);
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getContactsEntity());
        assertNotNull(userEntity.getUrl());
        assertNotNull(userEntity.getRole());
        assertEquals(userEntity.getName(), NAME);
        assertEquals(
                userEntity.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void toStringTest() {
        final UserEntity userEntity = getUserEntity();
        assertNotNull(userEntity.toString());
        final String userToString = "UserEntity{" +
                "ModelEntity{" +
                "id=" + userEntity.getId() +
                ", validated=" + userEntity.isValidated() +
                '}' +
                ", name='" + userEntity.getName() + '\'' +
                ", url='" + userEntity.getUrl() + '\'' +
                ", encryptedLogin='" + userEntity.getEncryptedLogin() + '\'' +
                ", encryptedPassword='" + userEntity.getEncryptedPassword() + '\'' +
                ", Login='" + userEntity.getLogin() + '\'' +
                ", Password='" + userEntity.getPassword() + '\'' +
                ", description='" + userEntity.getDescription() + '\'' +
                ", contacts=" + userEntity.getContactsEntity() +
                ", photo=" + userEntity.getPhotoEntity() +
                ", role=" + userEntity.getRole() +
                ", isMailing=" + userEntity.isMailing() +
                ", isLocked=" + userEntity.isLocked() +
                '}';
        assertEquals(userEntity.toString(), userToString);
    }

    @Test
    public void equalsInvalidObjects() {
        final UserEntity userEntity1 = new UserEntity();
        final UserEntity userEntity2 = new UserEntity();
        assertTrue(userEntity1.equals(userEntity2));
        userEntity1.setName(NAME);
        userEntity2.setName(NAME);
        assertTrue(userEntity1.equals(userEntity2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final UserEntity userEntity1 = getUserEntity();
        final UserEntity userEntity2 = userEntity1.clone();
        assertEquals(userEntity1, userEntity2);
        final boolean value = userEntity1.getName().equalsIgnoreCase(userEntity2.getName());
        assertEquals(userEntity1.equals(userEntity2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(NAME);
        int value = userEntity.getName().hashCode() +
                userEntity.getDescription().hashCode() +
                userEntity.getRole().hashCode();
        assertEquals(userEntity.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final UserEntity userEntity = getUserEntity();
        int value = userEntity.getName().hashCode() +
                userEntity.getDescription().hashCode() +
                userEntity.getRole().hashCode();
        assertEquals(userEntity.hashCode(), value);
    }

    @Test
    public void whenIsAccountNonExpiredThenReturnsSomeBoolean() {
        final UserEntity userEntity = getUserEntity();
        assertNotNull(userEntity.isAccountNonExpired());
        assertTrue(userEntity.isAccountNonExpired() || !userEntity.isAccountNonExpired());
    }

    @Test
    public void whenIsAccountNonLockedThenReturnsSomeBoolean() {
        final UserEntity userEntity = getUserEntity();
        assertNotNull(userEntity.isAccountNonLocked());
        assertTrue(userEntity.isAccountNonLocked() || !userEntity.isAccountNonLocked());
    }

    @Test
    public void whenIsCredentialsNonExpiredThenReturnsSomeBoolean() {
        final UserEntity userEntity = getUserEntity();
        assertNotNull(userEntity.isCredentialsNonExpired());
        assertTrue(userEntity.isCredentialsNonExpired() || !userEntity.isCredentialsNonExpired());
    }

    @Test
    public void whenIsEnabledNonExpiredThenReturnsSomeBoolean() {
        final UserEntity userEntity = getUserEntity();
        assertNotNull(userEntity.isEnabled());
        assertTrue(userEntity.isEnabled() || !userEntity.isEnabled());
    }

    @Test
    public void whenGetAuthoritiesThenReturnsNotEmptyCollections() {
        assertFalse(getUserEntity().getAuthorities().isEmpty());
    }

    @Test
    public void whenGetAuthoritiesThenItMustContainsUserRole() {
        final UserEntity userEntity = getUserEntity();
        assertTrue(
                userEntity.getAuthorities().contains(
                        new SimpleGrantedAuthority(
                                "ROLE_" + userEntity.getRole().name()
                        )
                )
        );
    }

    @Test
    public void whenGetUsernameThenReturnsNotNull() {
        assertNotNull(getUserEntity().getUsername());
    }

    @Test
    public void whenGetLoginThenReturnsNotNull() {
        assertNotNull(getUserEntity().getLogin());
    }

    @Test
    public void usernameMustEqualsLogin() {
        final UserEntity userEntity = getUserEntity();
        assertEquals(userEntity.getUsername(), userEntity.getLogin());
    }

    @Test
    public void whenGetPasswordThenReturnsNotNull() {
        assertNotNull(getUserEntity().getPassword());
    }

    @Test
    public void whenSetNullNameThenGetEmptyString() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(null);
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
    }

    @Test
    public void whenSetBlankNameThenGetEmptyString() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName("");
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
        userEntity.setName(" ");
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getName(), "");
    }

    @Test
    public void whenSetValidNameThenGetThisName() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setName(NAME);
        assertNotNull(userEntity.getName());
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getName(), NAME);
        assertEquals(
                userEntity.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetNullUrlThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setUrl(null);
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getUrl(), "");
    }

    @Test
    public void whenSetBlankUrlThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setUrl(null);
        assertNotNull(userEntity.getUrl());
        userEntity.setUrl("");
        assertNotNull(userEntity.getUrl());
        userEntity.setUrl(" ");
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.translateAndSetUrl(null);
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getUrl(), "");
    }

    @Test
    public void whenTranslateAndSetBlankUrlThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.translateAndSetUrl("");
        assertNotNull(userEntity.getUrl());
        userEntity.translateAndSetUrl(" ");
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getUrl(), "");
    }

    @Test
    public void whenSetValidUrlThenGetThisUrl() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setUrl(NAME);
        assertNotNull(userEntity.getUrl());
        assertEquals(userEntity.getUrl(), NAME);
    }

    @Test
    public void whenTranslateAndSetValidUrlThenGetThisUrl() {
        final UserEntity userEntity = getUserEntity();
        userEntity.translateAndSetUrl(NAME);
        assertNotNull(userEntity.getUrl());
        assertEquals(
                userEntity.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetNullLoginThenGetEmptyString() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(null);
        assertNotNull(userEntity.getLogin());
        assertEquals(userEntity.getLogin(), "");
    }

    @Test
    public void whenSetBlankLoginThenGetEmptyString() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin("");
        assertNotNull(userEntity.getLogin());
        userEntity.setLogin(" ");
        assertNotNull(userEntity.getLogin());
    }

    @Test
    public void whenSetValidLoginThenGetThisLogin() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(LOGIN);
        assertNotNull(userEntity.getLogin());
        assertEquals(userEntity.getLogin(), LOGIN);
    }

    @Test
    public void whenSetNullPasswordThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setPassword(null);
        assertNotNull(userEntity.getPassword());
        assertEquals(userEntity.getPassword(), "");
    }

    @Test
    public void whenSetBlankPasswordThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setPassword("");
        assertNotNull(userEntity.getPassword());
        userEntity.setPassword(" ");
        assertNotNull(userEntity.getPassword());
        assertEquals(userEntity.getPassword(), "");
    }

    @Test
    public void whenSetValidPasswordThenGetThisPassword() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setPassword(PASSWORD);
        assertNotNull(userEntity.getPassword());
        assertEquals(userEntity.getPassword(), PASSWORD);
    }

    @Test
    public void whenSetNullDescriptionThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setDescription(null);
        assertNotNull(userEntity.getDescription());
        assertEquals(userEntity.getDescription(), "");
    }

    @Test
    public void whenSetBlankDescriptionThenGetEmptyString() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setDescription("");
        assertNotNull(userEntity.getDescription());
        userEntity.setDescription(" ");
        assertNotNull(userEntity.getDescription());
        userEntity.setDescription("   ");
        assertNotNull(userEntity.getDescription());
        assertEquals(userEntity.getDescription(), "");
    }

    @Test
    public void whenSetValidDescriptionThenGetThisDescription() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setDescription(DESCRIPTION);
        assertNotNull(userEntity.getDescription());
        assertEquals(userEntity.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNotNull() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setPhotoEntity(null);
        assertNotNull(userEntity.getPhotoEntity());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setPhotoEntity(getFileEntity());
        assertNotNull(userEntity.getPhotoEntity());
        assertEquals(userEntity.getPhotoEntity(), getFileEntity());
    }

    @Test
    public void whenSetNullRoleThenGetUserRoleANOTHER() {
        final UserEntity userEntity = getUserEntity();
        userEntity.setRole(null);
        assertNotNull(userEntity.getRole());
        assertEquals(userEntity.getRole(), UserRole.ANOTHER);
    }

    @Test
    public void whenSetNotNullRoleThenGetThisRole() {
        final UserEntity userEntity = getUserEntity();
        final UserRole role = UserRole.ANOTHER;
        userEntity.setRole(role);
        assertNotNull(userEntity.getRole());
        assertEquals(userEntity.getRole(), role);
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
        final UserEntity userEntity = new UserEntity();
        assertNotNull(userEntity.getUsername());
        assertEquals(userEntity.getUsername(), "");
    }

    @Test
    public void whenGetUsernameThenGetsLogin() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(LOGIN);
        assertNotNull(userEntity.getUsername());
        assertEquals(
                userEntity.getUsername(),
                userEntity.getLogin()
        );
    }

    @Test
    public void whenSetLockedThenGetsItValue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertTrue(userEntity.isLocked());
        userEntity.setLocked(false);
        assertFalse(userEntity.isLocked());
    }

    @Test
    public void whenSetLockedTrueThenSetAccountNonExpiredFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isAccountNonExpired());
    }

    @Test
    public void whenSetLockedFalseThenSetAccountNonExpiredTrue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(false);
        assertTrue(userEntity.isAccountNonExpired());
    }

    @Test
    public void whenSetLockedTrueThenSetAccountNonLockedFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isAccountNonLocked());
    }

    @Test
    public void whenSetLockedFalseThenSetAccountNonLockedTrue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(false);
        assertTrue(userEntity.isAccountNonLocked());
    }

    @Test
    public void whenSetLockedTrueThenSetCredentialsNonExpiredFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isCredentialsNonExpired());
    }

    @Test
    public void whenSetLockedFalseThenSetCredentialsNonExpiredTrue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(false);
        assertTrue(userEntity.isCredentialsNonExpired());
    }

    @Test
    public void whenSetLockedTrueThenSetEnabledFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isEnabled());
    }

    @Test
    public void whenSetLockedFalseThenSetEnabledTrue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(false);
        assertTrue(userEntity.isEnabled());
    }

    @Test
    public void whenSetLockedTrueThenSetValidatedFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isValidated());
    }

    @Test
    public void whenSetLockedTrueThenSetMailingFalse() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLocked(true);
        assertFalse(userEntity.isMailing());
    }

    @Test
    public void whenSetMailingThenGetsItValue() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setMailing(true);
        assertTrue(userEntity.isMailing());
        userEntity.setMailing(false);
        assertFalse(userEntity.isMailing());
    }

    @Test
    public void whenInitializeByNullThenDoNothing() {
        final UserEntity model1 = getInstance();
        final UserEntity model2 = model1.initialize(null);
        assertEquals(model1, model2);
    }

    @Test
    public void whenInitializeByValidObjectThenCopyIt() {
        final UserEntity model1 = getInstance();
        final UserEntity model2 = getObject();
        model1.initialize(model2);
        assertEquals(model1, model2);
    }

    @Ignore
    @Override
    protected UserEntity getObject() {
        return getUserEntity();
    }

    @Override
    protected UserEntity getInstance() {
        return new UserEntity();
    }
}
