package com.salimov.yurii.entity;

import com.salimov.yurii.enums.UserRole;
import com.salimov.yurii.util.translator.Translator;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static com.salimov.yurii.mocks.MockConstants.*;
import static com.salimov.yurii.mocks.enity.MockEntity.getUser;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public final class UserTest extends ModelTest<User> {

    @Test
    public void whenPassInvalidParametersInConstructorThenSaveNull() {
        User user = new User();
        assertNotNull(user.getRole());
        user = new User(null, null, null);
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());

        user = new User("", "", "");
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());

        user = new User(" ", " ", " ");
        assertNotNull(user.getRole());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getUrl());
        assertNotNull(user.getRole());

        user = new User("   ", "   ", "   ");
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
        assertEquals(
                user.getName(),
                NAME
        );
        assertEquals(
                user.getEmail(),
                EMAIL
        );
        assertEquals(
                user.getPhone(),
                PHONE
        );
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
        assertEquals(
                user.toString(),
                sb.toString()
        );
    }

    @Test
    public void equalsInvalidObjects() {
        final User user1 = new User();
        final User user2 = new User();
        assertTrue(user1.equals(user2));
        user1.setName(NAME);
        user2.setName(NAME);
        assertTrue(
                user1.equals(user2)
        );
        user1.setPhone(PHONE);
        user2.setPhone(PHONE);
        assertTrue(
                user1.equals(user2)
        );
        user1.setEmail(EMAIL);
        user2.setEmail(EMAIL);
        assertTrue(
                user1.equals(user2)
        );
    }

    @Test
    @Override
    public void equalsValidObjects() {
        super.equalsValidObjects();
        final User user1 = getUser();
        final User user2 = (User) user1.clone();
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
        assertEquals(
                user1.equals(user2),
                value
        );
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
        User user = getUser();
        int hash = isNotBlank(user.getName()) ? user.getName().hashCode() : 0;
        hash += isNotBlank(user.getPhone()) ? user.getPhone().hashCode() : 0;
        hash += isNotBlank(user.getEmail()) ? user.getEmail().hashCode() : 0;
        assertEquals(user.hashCode(), hash);
    }

    @Test
    public void userDetailsMethodsTest() {
        final User user = getUser();
        assertNotNull(user.isAccountNonExpired());
        assertNotNull(user.isAccountNonLocked());
        assertNotNull(user.isCredentialsNonExpired());
        assertNotNull(user.isEnabled());
        assertTrue(
                user.isAccountNonExpired() || !user.isAccountNonExpired()
        );
        assertTrue(
                user.isAccountNonLocked() || !user.isAccountNonLocked()
        );
        assertTrue(
                user.isCredentialsNonExpired() || !user.isCredentialsNonExpired()
        );
        assertTrue(
                user.isEnabled() || !user.isEnabled()
        );
        final Collection roles = user.getAuthorities();
        assertNotNull(roles);
        assertFalse(roles.isEmpty());
        assertTrue(
                roles.contains(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRole().name()
                        )
                )
        );
        assertNotNull(user.getUsername());
        assertEquals(
                user.getUsername(),
                user.getLogin()
        );
        assertNotNull(user.getPassword());
    }

    @Test
    public void whenInitializeObjectWithInvalidParametersThenGetNull() {
        final User user = new User();
        user.initialize(null, null, null, null);
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());

        user.initialize("", "", "", "");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());

        user.initialize(" ", " ", " ", " ");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());

        user.initialize("   ", "   ", "   ", "   ");
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());

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

        user.initialize(null, null, null, null, null, null, null, null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());

        user.initialize("", "", "", "", "", "", "", null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());

        user.initialize(" ", " ", " ", " ", " ", " ", " ", null);
        assertNull(user.getName());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertNull(user.getDescription());
        assertNull(user.getPhotoUrl());
        assertNull(user.getRole());

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
    public void whenInitializeObjectWithValidParametersThenGetThisValue() {
        final User user = new User();
        user.initialize(NAME, EMAIL, PHONE, DESCRIPTION);
        assertNotNull(user.getName());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getDescription());
        assertEquals(
                user.getName(),
                NAME
        );
        assertEquals(
                user.getEmail(),
                EMAIL
        );
        assertEquals(
                user.getPhone(),
                PHONE
        );
        assertEquals(
                user.getDescription(),
                DESCRIPTION
        );

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
        assertEquals(user.getName(), NAME
        );
        assertEquals(
                user.getLogin(),
                LOGIN
        );
        assertEquals(
                user.getPassword(),
                PASSWORD
        );
        assertEquals(
                user.getEmail(),
                EMAIL
        );
        assertEquals(
                user.getPhone(),
                PHONE
        );
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        assertEquals(
                user.getSkype(),
                SKYPE
        );
        assertEquals(
                user.getDescription(),
                DESCRIPTION
        );
        final UserRole role = UserRole.ANOTHER;
        user.initialize(
                NAME, LOGIN, PASSWORD, EMAIL,
                PHONE, DESCRIPTION, PHOTO_URL, role
        );
        assertNotNull(user.getName());
        assertNotNull(user.getLogin());
        assertNotNull(user.getPassword());
        assertNotNull(user.getEmail());
        assertNotNull(user.getPhone());
        assertNotNull(user.getDescription());
        assertNotNull(user.getPhotoUrl());
        assertNotNull(user.getRole());
        assertEquals(
                user.getName(),
                NAME
        );
        assertEquals(
                user.getLogin(),
                LOGIN
        );
        assertEquals(
                user.getPassword(),
                PASSWORD
        );
        assertEquals(
                user.getEmail(),
                EMAIL
        );
        assertEquals(
                user.getPhone(),
                PHONE
        );
        assertEquals(
                user.getDescription(),
                DESCRIPTION
        );
        assertEquals(
                user.getPhotoUrl(),
                PHOTO_URL
        );
        assertEquals(
                user.getRole(),
                role
        );
    }

    @Test
    public void whenSetInvalidNameThenGetNull() {
        final User user = new User();
        user.setName(null);
        assertNull(user.getName());
        assertNull(user.getUrl());
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
        assertEquals(
                user.getName(),
                NAME
        );
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetInvalidUrlThenGetNull() {
        final User user = getUser();
        user.setUrl(null);
        assertNull(user.getUrl());
        user.setUrl("");
        assertNull(user.getUrl());
        user.setUrl(" ");
        assertNull(user.getUrl());

        user.translateAndSetUrl(null);
        assertNull(user.getUrl());
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
        assertEquals(
                user.getUrl(),
                NAME
        );

        user.translateAndSetUrl(NAME);
        assertNotNull(user.getUrl());
        assertEquals(
                user.getUrl(),
                Translator.fromCyrillicToLatin(NAME)
        );
    }

    @Test
    public void whenSetInvalidLoginThenGetNull() {
        final User user = new User();
        user.setLogin(null);
        assertNull(user.getLogin());
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
        assertEquals(
                user.getLogin(),
                LOGIN
        );
    }

    @Test
    public void whenSetInvalidPasswordThenGetNull() {
        final User user = getUser();
        user.setPassword(null);
        assertNull(user.getPassword());
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
        assertEquals(
                user.getPassword(),
                PASSWORD
        );
    }

    @Test
    public void whenSetInvalidEmailThenGetNull() {
        final User user = getUser();
        user.setEmail(null);
        assertNull(user.getEmail());
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
        assertEquals(
                user.getEmail(),
                EMAIL
        );
    }

    @Test
    public void whenSetInvalidPhoneThenGetNull() {
        final User user = getUser();
        user.setPhone(null);
        assertNull(user.getPhone());
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
        assertEquals(
                user.getPhone(),
                PHONE
        );
    }

    @Test
    public void whenSetInvalidVkontakteThenGetNull() {
        final User user = getUser();
        user.setVkontakte(null);
        assertNull(user.getVkontakte());
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
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("http://" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("https://" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("m.vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("http://m.vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("https://m.vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("http://vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
        user.setVkontakte("https://vk.com" + VKONTAKTE);
        assertEquals(
                user.getVkontakte(),
                VKONTAKTE
        );
    }

    @Test
    public void whenSetInvalidFacebookThenGetNull() {
        final User user = getUser();
        user.setFacebook(null);
        assertNull(user.getFacebook());
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
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("http://" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("https://" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("m.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("www.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("http://m.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("https://m.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("http://www.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
        user.setFacebook("https://www.facebook.com" + FACEBOOK);
        assertEquals(
                user.getFacebook(),
                FACEBOOK
        );
    }

    @Test
    public void whenSetInvalidTwitterThenGetNull() {
        final User user = getUser();
        user.setTwitter(null);
        assertNull(user.getTwitter());
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
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("http://" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("https://" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("mobile.TWITTER.com" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("TWITTER.com" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("http://mobile.TWITTER.com" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("https://mobile.TWITTER.com" + TWITTER);
        assertEquals(user.getTwitter(), TWITTER);
        user.setTwitter("http://TWITTER.com" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
        user.setTwitter("https://TWITTER.com" + TWITTER);
        assertEquals(
                user.getTwitter(),
                TWITTER
        );
    }

    @Test
    public void whenSetInvalidSkypeThenGetNull() {
        final User user = getUser();
        user.setSkype(null);
        assertNull(user.getSkype());
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
    public void whenSetInvalidDescriptionThenGetNull() {
        final User user = getUser();
        user.setDescription(null);
        assertNull(user.getDescription());
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
        assertEquals(
                user.getDescription(),
                DESCRIPTION
        );
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
        assertEquals(
                user.getPhotoUrl(),
                PHOTO_URL
        );
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
        assertEquals(
                user.getRole(),
                role
        );
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
        final  UserRole[] roles = UserRole.getEnumConstants();
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
    public void whenCallUserDetailsMethodsThenReturnsSomeBoolean() {
        final User user = new User();
        user.setLocked(false);
        assertFalse(user.isLocked());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
        user.setLocked(true);
        assertTrue(user.isLocked());
        assertFalse(user.isAccountNonExpired());
        assertFalse(user.isAccountNonLocked());
        assertFalse(user.isCredentialsNonExpired());
        assertFalse(user.isEnabled());
    }

    @Test
    public void whenGetUsernameThenGetsLogin() {
        final User user = new User();
        assertNotNull(user.getUsername());
        assertEquals(user.getUsername(), "");
        user.setLogin(LOGIN);
        assertNotNull(user.getUsername());
        assertEquals(user.getUsername(), user.getLogin());
    }

    @Test
    public void whenSetLockedThenGetsItValue() {
        final User user = new User();
        user.setLocked(true);
        assertTrue(user.isLocked());
        assertFalse(user.isValidated());
        assertFalse(user.isMailing());
        user.setLocked(false);
        assertFalse(user.isLocked());
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
