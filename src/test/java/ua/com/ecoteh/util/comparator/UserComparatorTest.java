package ua.com.ecoteh.util.comparator;

import org.junit.Ignore;
import org.junit.Test;
import ua.com.ecoteh.entity.User;
import ua.com.ecoteh.mocks.MockConstants;
import ua.com.ecoteh.mocks.enity.MockEntity;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public final class UserComparatorTest {

    @Test
    public void userComparator() {
        UserComparator comparator = new UserComparator();
        assertNotNull(comparator);
    }

    @Test
    public void getUserComparatorByName() {
        Comparator<User> comparator = new UserComparator.ByName();
        assertNotNull(comparator);

        User user1 = MockEntity.getUser();
        User user2 = MockEntity.getUser();
        int value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String name1 = MockConstants.NAME + "1";
        final String name2 = MockConstants.NAME + "2";

        user1.setName(name1);
        user2.setName(name2);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, -1);

        user1.setName(name2);
        user2.setName(name1);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getUserComparatorByNameWithInvalidUsers() {
        Comparator<User> comparator = new UserComparator.ByName();
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByUrl() {
        Comparator<User> comparator = new UserComparator.ByUrl();
        assertNotNull(comparator);

        User user1 = MockEntity.getUser();
        User user2 = MockEntity.getUser();
        int value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 0);

        final String url1 = MockConstants.URL + "1";
        final String url2 = MockConstants.URL + "2";

        user1.setUrl(url1);
        user2.setUrl(url2);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, -1);

        user1.setUrl(url2);
        user2.setUrl(url1);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 1);
    }

    @Test
    public void getUserComparatorByUrlWithInvalidUsers() {
        Comparator<User> comparator = new UserComparator.ByUrl();
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByRole() {
        Comparator<User> comparator = new UserComparator.ByRole(MockConstants.USER_ROLE);
        assertNotNull(comparator);

        User user1 = MockEntity.getUser();
        User user2 = MockEntity.getUser();
        user1.setRole(null);
        user2.setRole(null);
        int value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 0);

        user1.setRole(MockConstants.USER_ROLE);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, 1);

        user1.setRole(null);
        user2.setRole(MockConstants.USER_ROLE);
        value = comparator.compare(user1, user2);
        assertNotNull(value);
        assertEquals(value, -1);
    }

    @Test
    public void getUserComparatorByRoleWithInvalidUsers() {
        Comparator<User> comparator = new UserComparator.ByRole(MockConstants.USER_ROLE);
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Test
    public void getUserComparatorByRoleWithNullRole() {
        Comparator<User> comparator = new UserComparator.ByRole(null);
        getUserComparatorWithInvalidUsers(comparator);
    }

    @Ignore
    private static void getUserComparatorWithInvalidUsers(Comparator<User> comparator) {
        int value = comparator.compare(null, null);
        assertNotNull(value);
        assertEquals(value, 0);

        final User user = MockEntity.getUser();
        value = comparator.compare(user, null);
        assertNotNull(value);
        assertEquals(value, 1);

        value = comparator.compare(null, user);
        assertNotNull(value);
        assertEquals(value, -1);
    }
}
