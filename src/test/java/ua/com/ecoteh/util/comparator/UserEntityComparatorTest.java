package ua.com.ecoteh.util.comparator;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.user.UserEntity;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class UserEntityComparatorTest {

    @Test
    public void userComparator() {
        UserComparator comparator = new UserComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getUserComparatorByName() {
        Comparator<UserEntity> comparator = new UserComparator.ByName();
        assertNotNull(comparator);

        UserEntity userEntity1 = MockEntity.getUserEntity();
        UserEntity userEntity2 = MockEntity.getUserEntity();
        int value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String name1 = MockConstants.NAME + "1";
        final String name2 = MockConstants.NAME + "2";

        userEntity1.setName(name1);
        userEntity2.setName(name2);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        userEntity1.setName(name2);
        userEntity2.setName(name1);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getUserComparatorByNameWithInvalidUsers() {
        Comparator<UserEntity> comparator = new UserComparator.ByName();
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByUrl() {
        Comparator<UserEntity> comparator = new UserComparator.ByUrl();
        assertNotNull(comparator);

        UserEntity userEntity1 = MockEntity.getUserEntity();
        UserEntity userEntity2 = MockEntity.getUserEntity();
        int value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String url1 = MockConstants.URL + "1";
        final String url2 = MockConstants.URL + "2";

        userEntity1.setUrl(url1);
        userEntity2.setUrl(url2);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, -1);

        userEntity1.setUrl(url2);
        userEntity2.setUrl(url1);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getUserComparatorByUrlWithInvalidUsers() {
        Comparator<UserEntity> comparator = new UserComparator.ByUrl();
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByRole() {
        Comparator<UserEntity> comparator = new UserComparator.ByRole(MockConstants.USER_ROLE);
        assertNotNull(comparator);

        UserEntity userEntity1 = MockEntity.getUserEntity();
        UserEntity userEntity2 = MockEntity.getUserEntity();
        userEntity1.setRole(null);
        userEntity2.setRole(null);
        int value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 0);

        userEntity1.setRole(MockConstants.USER_ROLE);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, 1);

        userEntity1.setRole(null);
        userEntity2.setRole(MockConstants.USER_ROLE);
        value = comparator.compare(userEntity1, userEntity2);
        assertNotNull(value);
        assertEquals(value, -1);
    }

    @Test
    public void getUserComparatorByRoleWithInvalidUsers() {
        Comparator<UserEntity> comparator = new UserComparator.ByRole(MockConstants.USER_ROLE);
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByRoleWithNullRole() {
        Comparator<UserEntity> comparator = new UserComparator.ByRole(null);
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Ignore
    private static void getUserComparatorWithInvalidUsers(Comparator<UserEntity> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final UserEntity userEntity = MockEntity.getUserEntity();
        value = comparator.compare(userEntity, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, userEntity);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
