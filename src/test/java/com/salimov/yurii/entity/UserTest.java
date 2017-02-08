package com.salimov.yurii.entity;

import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class UserTest extends ModelTest<User> {

    @Test
    public void whenInitializeByConstructorThenSetNotNullRole() {
        final User user = new User();
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassNullParametersInConstructorThenSaveNull() {
        final User user = new User(null, null, null);
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_1() {
        final User user = new User("", "", "");
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_2() {
        final User user = new User(" ", " ", " ");
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassBlankParametersInConstructorThenSaveNull_3() {
        final User user = new User("   ", "   ", "   ");
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());
    }

    @Test
    public void whenPassValidParametersInConstructorThenSaveThisValues() {
        final User user = new User(NAME, EMAIL, PHONE);
        assertNotNull(user.getRole());
        assertNotNull(user.getName());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getUrl());
        assertNotNull(user.getRole());
        assertEquals(user.getName(), NAME);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getPhone(), PHONE);
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void toStringTest() {
        final User user = getUser();
        assertNotNull(user.toString());
        final StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(user.getName())
                .append(" \nE-mail: ").append(user.getEmail())
                .append(" \nPhone: ").append(user.getPhone());
        if (user.getRole() != null) {
            sb.append(" \nRole: ").append(user.getRole().name());
        }
        sb.append(" ");
        assertEquals(user.toString(), sb.toString());
    }

    @Test
    public void equalsInvalidObjects() {
        final User user1 = new User();
        final User user2 = new User();
        assertTrue(user1.equals(user2));
        user1.setName(NAME);
        user2.setName(NAME);
        assertTrue(user1.equals(user2));
        user1.setPhone(PHONE);
        user2.setPhone(PHONE);
        assertTrue(user1.equals(user2));
        user1.setEmail(EMAIL);
        user2.setEmail(EMAIL);
        assertTrue(user1.equals(user2));
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final User user1 = getUser();
        final User user2 = user1.clone();
        assertEquals(user1, user2);
        final boolean value = (
                isNotBlank(user1.getName()) ?
                        user1.getName()
                                .equalsIgnoreCase(
                                        user2.getName()
                                ) :
                        isBlank(user2.getName())
        ) && (
                isNotBlank(user1.getPhone()) ?
                        user1.getPhone()
                                .equals(
                                        user2.getPhone()
                                ) :
                        isBlank(user2.getPhone())
        ) && (
                isNotBlank(user1.getEmail()) ?
                        user1.getEmail()
                                .equalsIgnoreCase(
                                        user2.getEmail()
                                ) :
                        isBlank(user2.getEmail())
        );
        assertEquals(user1.equals(user2), value);
    }

    @Test
    public void hashCodeInvalidObject() {
        final User user = new User();
        int value = 0;
        assertEquals(user.hashCode(), value);
        user.setName(NAME);
        value += isNotBlank(user.getName()) ? user.getName().hashCode() : 0;
        assertEquals(user.hashCode(), value);
        user.setPhone(PHONE);
        value += isNotBlank(user.getPhone()) ? user.getPhone().hashCode() : 0;
        assertEquals(user.hashCode(), value);
        user.setEmail(EMAIL);
        value += isNotBlank(user.getEmail()) ? user.getEmail().hashCode() : 0;
        assertEquals(user.hashCode(), value);
    }

    @Test
    @Override
    public void hashCodeValidObject() {
        super.hashCodeValidObject();
        final User user = getUser();
        assertEquals(
                user.hashCode(),
                (isNotBlank(user.getName()) ? user.getName().hashCode() : 0)
                        + (isNotBlank(user.getPhone()) ? user.getPhone().hashCode() : 0)
                        + (isNotBlank(user.getEmail()) ? user.getEmail().hashCode() : 0)
        );
    }

    @Test
    public void whenIsAccountNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isAccountNonExpired());
        assertTrue(
                user.isAccountNonExpired() || !user.isAccountNonExpired()
        );
    }

    @Test
    public void whenIsAccountNonLockedThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isAccountNonLocked());
        assertTrue(
                user.isAccountNonLocked() || !user.isAccountNonLocked()
        );
    }

    @Test
    public void whenIsCredentialsNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isCredentialsNonExpired());
        assertTrue(
                user.isCredentialsNonExpired() || !user.isCredentialsNonExpired()
        );
    }

    @Test
    public void whenIsEnabledNonExpiredThenReturnsSomeBoolean() {
        final User user = getUser();
        assertNotNull(user.isEnabled());
        assertTrue(
                user.isEnabled() || !user.isEnabled()
        );
    }

    @Test
    public void whenGetAuthoritiesThenReturnsNotEmptyCollections() {
        assertFalse(
                getUser()
                        .getAuthorities()
                        .isEmpty()
        );
    }

    @Test
    public void whenGetAuthoritiesThenItMustContainsUserRole() {
        final User user = getUser();
        assertTrue(
                user.getAuthorities()
                        .contains(
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
    public void whenInitializeObjectWithNullParametersThenGetNull_1() {
        final User user = new User();
        user.initialize(null, null, null, null);
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_1() {
        final User user = new User();
        user.initialize("", "", "", "");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_2() {
        final User user = new User();
        user.initialize(" ", " ", " ", " ");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_3() {
        final User user = new User();
        user.initialize("   ", "   ", "   ", "   ");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithNullParametersThenGetNull_2() {
        final User user = new User();
        user.initialize(null, null, null, null, null, null, null, null, null, null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getVkontakte());
        assertNull(user.getFacebook());
        assertNull(user.getTwitter());
        assertNull(user.getSkype());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_4() {
        final User user = new User();
        user.initialize("", "", "", "", "", "", "", "", "", "");
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getVkontakte());
        assertNull(user.getFacebook());
        assertNull(user.getTwitter());
        assertNull(user.getSkype());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_5() {
        final User user = new User();
        user.initialize(" ", " ", " ", " ", " ", " ", " ", " ", " ", " ");
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getVkontakte());
        assertNull(user.getFacebook());
        assertNull(user.getTwitter());
        assertNull(user.getSkype());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_6() {
        final User user = new User();
        user.initialize("   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ");
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getVkontakte());
        assertNull(user.getFacebook());
        assertNull(user.getTwitter());
        assertNull(user.getSkype());
        assertNull(user.getDescription());
    }

    @Test
    public void whenInitializeObjectWithNullParametersThenGetNull_3() {
        final User user = new User();
        user.initialize(null, null, null, null, null, null, null, null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_7() {
        final User user = new User();
        user.initialize("", "", "", "", "", "", "", null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_8() {
        final User user = new User();
        user.initialize(" ", " ", " ", " ", " ", " ", " ", null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());
    }

    @Test
    public void whenInitializeObjectWithBlankParametersThenGetNull_9() {
        final User user = new User();
        user.initialize("   ", "   ", "   ", "   ", "   ", "   ", "   ", null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue_1() {
        final User user = new User();
        user.initialize(NAME, EMAIL, PHONE, DESCRIPTION);
        assertNotNull(user.getName());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getDescription());
        assertEquals(user.getName(), NAME);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getPhone(), PHONE);
        assertEquals(user.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue_2() {
        final User user = new User();
        user.initialize(
                NAME, LOGIN, PASSWORD, EMAIL,
                PHONE, VKONTAKTE, FACEBOOK,
                TWITTER, SKYPE, DESCRIPTION
        );
        assertNotNull(user.getName());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getVkontakte());
        assertNotNull(user.getFacebook());
        assertNotNull(user.getTwitter());
        assertNotNull(user.getSkype());
        assertNotNull(user.getDescription());
        assertEquals(user.getName(), NAME);
        assertEquals(user.getLogin(), LOGIN);
        assertEquals(user.getPassword(), PASSWORD);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getPhone(), PHONE);
        assertEquals(user.getVkontakte(), VKONTAKTE);
        assertEquals(user.getFacebook(), FACEBOOK);
        assertEquals(user.getTwitter(), TWITTER);
        assertEquals(user.getSkype(), SKYPE);
        assertEquals(user.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenInitializeObjectWithValidParametersThenGetThisValue_3() {
        final User user = new User();
        final UserRole role = UserRole.ANOTHER;
        user.initialize(
                NAME, LOGIN, PASSWORD,
                EMAIL, PHONE,
                DESCRIPTION,
                PHOTO_URL, role
        );
        assertNotNull(user.getName());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getDescription());
        assertNotNull(user.getPhotoUrl());
        assertNotNull(user.getRole());
        assertEquals(user.getName(), NAME);
        assertEquals(user.getLogin(), LOGIN);
        assertEquals(user.getPassword(), PASSWORD);
        assertEquals(user.getEmail(), EMAIL);
        assertEquals(user.getPhone(), PHONE);
        assertEquals(user.getDescription(), DESCRIPTION);
        assertEquals(user.getPhotoUrl(), PHOTO_URL);
        assertEquals(user.getRole(), role);
    }

    @Test
    public void whenSetNullNameThenGetNull() {
        final User user = new User();
        user.setName(null);
        assertNull(user.getName());
        assertNull(user.getUrl());
    }

    @Test
    public void whenSetBlankNameThenGetNull() {
        final User user = new User();
        user.setName("");
        assertNull(user.getName());
        assertNull(user.getUrl());
        user.setName(" ");
        assertNull(user.getName());
        assertNull(user.getUrl());
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
    public void whenSetNullUrlThenGetNull() {
        final User user = getUser();
        user.setUrl(null);
        assertNull(user.getUrl());
    }

    @Test
    public void whenSetBlankUrlThenGetNull() {
        final User user = getUser();
        user.setUrl(null);
        assertNull(user.getUrl());
        user.setUrl("");
        assertNull(user.getUrl());
        user.setUrl(" ");
        assertNull(user.getUrl());
    }

    @Test
    public void whenTranslateAndSetNullUrlThenGetNull() {
        final User user = getUser();
        user.translateAndSetUrl(null);
        assertNull(user.getUrl());
    }

    @Test
    public void whenTranslateAndSetBlankUrlThenGetNull() {
        final User user = getUser();
        user.translateAndSetUrl("");
        assertNull(user.getUrl());
        user.translateAndSetUrl(" ");
        assertNull(user.getUrl());
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
    public void whenSetNullLoginThenGetNull() {
        final User user = new User();
        user.setLogin(null);
        assertNull(user.getLogin());
    }

    @Test
    public void whenSetBlankLoginThenGetNull() {
        final User user = new User();
        user.setLogin("");
        assertNull(user.getLogin());
        user.setLogin(" ");
        assertNull(user.getLogin());
    }

    @Test
    public void whenSetValidLoginThenGetThisLogin() {
        final User user = new User();
        user.setLogin(LOGIN);
        assertNotNull(user.getLogin());
        assertEquals(user.getLogin(), LOGIN);
    }

    @Test
    public void whenSetNullPasswordThenGetNull() {
        final User user = getUser();
        user.setPassword(null);
        assertNull(user.getPassword());
    }

    @Test
    public void whenSetBlankPasswordThenGetNull() {
        final User user = getUser();
        user.setPassword("");
        assertNull(user.getPassword());
        user.setPassword(" ");
        assertNull(user.getPassword());
    }

    @Test
    public void whenSetValidPasswordThenGetThisPassword() {
        final User user = getUser();
        user.setPassword(PASSWORD);
        assertNotNull(user.getPassword());
        assertEquals(user.getPassword(), PASSWORD);
    }

    @Test
    public void whenSetNullEmailThenGetNull() {
        final User user = getUser();
        user.setEmail(null);
        assertNull(user.getEmail());
    }

    @Test
    public void whenSetBlankEmailThenGetNull() {
        final User user = getUser();
        user.setEmail("");
        assertNull(user.getEmail());
        user.setEmail(" ");
        assertNull(user.getEmail());
    }

    @Test
    public void whenSetValidEmailThenGetThisEmail() {
        final User user = getUser();
        user.setEmail(EMAIL);
        assertNotNull(user.getEmail());
        assertEquals(user.getEmail(), EMAIL);
    }

    @Test
    public void whenSetNullPhoneThenGetNull() {
        final User user = getUser();
        user.setPhone(null);
        assertNull(user.getPhone());
    }

    @Test
    public void whenSetBlankPhoneThenGetNull() {
        final User user = getUser();
        user.setPhone("");
        assertNull(user.getPhone());
        user.setPhone(" ");
        assertNull(user.getPhone());
    }

    @Test
    public void whenSetValidPhoneThenGetThisPhone() {
        final User user = getUser();
        user.setPhone(PHONE);
        assertNotNull(user.getPhone());
        assertEquals(user.getPhone(), PHONE);
    }

    @Test
    public void whenSetNullVkontakteThenGetNull() {
        final User user = getUser();
        user.setVkontakte(null);
        assertNull(user.getVkontakte());
    }

    @Test
    public void whenSetBlankVkontakteThenGetNull() {
        final User user = getUser();
        user.setVkontakte("");
        assertNull(user.getVkontakte());
        user.setVkontakte(" ");
        assertNull(user.getVkontakte());
        user.setVkontakte("   ");
        assertNull(user.getVkontakte());
    }

    @Test
    public void whenSetValidVkontakteThenGetThisVkontakte() {
        final User user = getUser();
        user.setVkontakte(VKONTAKTE);
        assertNotNull(user.getVkontakte());
        assertEquals(user.getVkontakte(), VKONTAKTE);
    }

    @Test
    public void whenSetNullFacebookThenGetNull() {
        final User user = getUser();
        user.setFacebook(null);
        assertNull(user.getFacebook());
    }

    @Test
    public void whenSetBlankFacebookThenGetNull() {
        final User user = getUser();
        user.setFacebook("");
        assertNull(user.getFacebook());
        user.setFacebook(" ");
        assertNull(user.getFacebook());
        user.setFacebook("   ");
        assertNull(user.getFacebook());
    }

    @Test
    public void whenSetValidFacebookThenGetThisFacebook() {
        final User user = getUser();
        user.setFacebook(FACEBOOK);
        assertNotNull(user.getFacebook());
        assertEquals(user.getFacebook(), FACEBOOK);
    }

    @Test
    public void whenSetNullTwitterThenGetNull() {
        final User user = getUser();
        user.setTwitter(null);
        assertNull(user.getTwitter());
    }

    @Test
    public void whenSetBlankTwitterThenGetNull() {
        final User user = getUser();
        user.setTwitter("");
        assertNull(user.getTwitter());
        user.setTwitter(" ");
        assertNull(user.getTwitter());
        user.setTwitter("   ");
        assertNull(user.getTwitter());
    }

    @Test
    public void whenSetValidTwitterThenGetThisTwitter() {
        final User user = getUser();
        user.setTwitter(TWITTER);
        assertNotNull(user.getTwitter());
        assertEquals(user.getTwitter(), TWITTER);
    }

    @Test
    public void whenSetNullSkypeThenGetNull() {
        final User user = getUser();
        user.setSkype(null);
        assertNull(user.getSkype());
    }

    @Test
    public void whenSetBlankSkypeThenGetNull() {
        final User user = getUser();
        user.setSkype("");
        assertNull(user.getSkype());
        user.setSkype(" ");
        assertNull(user.getSkype());
        user.setSkype("   ");
        assertNull(user.getSkype());
    }

    @Test
    public void whenSetValidSkypeThenGetThisSkype() {
        final User user = getUser();
        user.setSkype(SKYPE);
        assertNotNull(user.getSkype());
        assertEquals(user.getSkype(), SKYPE);
    }

    @Test
    public void whenSetNullDescriptionThenGetNull() {
        final User user = getUser();
        user.setDescription(null);
        assertNull(user.getDescription());
    }

    @Test
    public void whenSetBlankDescriptionThenGetNull() {
        final User user = getUser();
        user.setDescription("");
        assertNull(user.getDescription());
        user.setDescription(" ");
        assertNull(user.getDescription());
        user.setDescription("   ");
        assertNull(user.getDescription());
    }

    @Test
    public void whenSetValidDescriptionThenGetThisDescription() {
        final User user = getUser();
        user.setDescription(DESCRIPTION);
        assertNotNull(user.getDescription());
        assertEquals(user.getDescription(), DESCRIPTION);
    }

    @Test
    public void whenSetInvalidPhotoThenGetNull() {
        final User user = getUser();
        user.setPhotoUrl(null);
        assertNull(user.getPhotoUrl());
    }

    @Test
    public void whenSetValidPhotoThenGetThisPhoto() {
        final User user = getUser();
        user.setPhotoUrl(PHOTO_URL);
        assertNotNull(user.getPhotoUrl());
        assertEquals(user.getPhotoUrl(), PHOTO_URL);
    }

    @Test
    public void whenSetNullRoleThenGetNull() {
        final User user = getUser();
        user.setRole(null);
        assertNull(user.getRole());
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
    @Override
    public void validObject() {
        super.validObject();
        final User user = getUser();
        assertTrue(User.isValidated(user));
        assertTrue(user.isValidated());
        assertFalse(User.isValidated(null));
        final String name = user.getName();
        user.setName(null);
        assertFalse(User.isValidated(user));
        user.setName(name);
        final String phone = user.getPhone();
        user.setPhone(null);
        assertFalse(User.isValidated(user));
        user.setPhone(phone);
        user.setEmail(null);
        assertFalse(User.isValidated(user));
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

    @Ignore
    @Override
    protected User getObject() {
        return getUser();
    }
}
