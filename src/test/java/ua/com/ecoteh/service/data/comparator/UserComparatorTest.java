package ua.com.ecoteh.service.data.comparator;

import org.junit.Test;
import ua.com.ecoteh.entity.user.User;
import ua.com.ecoteh.entity.user.UserBuilder;

import java.util.Comparator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.USER_ROLE;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserComparatorTest {

    @Test
    public void whenGetByNameComparatorThenReturnNotNull() {
        final Comparator<User> byNameComparator = new UserComparator.ByName();
        assertNotNull(byNameComparator);
    }

    @Test
    public void compareByNameTest() {
        final Comparator<User> byNameComparator = new UserComparator.ByName();
        comparatorTest(byNameComparator);
    }

    @Test
    public void whenGetByUrlComparatorThenReturnNotNull() {
        final Comparator<User> byUrlComparator = new UserComparator.ByUrl();
        assertNotNull(byUrlComparator);
    }

    @Test
    public void compareByUrlTest() {
        final Comparator<User> byUrlComparator = new UserComparator.ByUrl();
        comparatorTest(byUrlComparator);
    }

    @Test
    public void whenGetByRoleComparatorThenReturnNotNull() {
        final Comparator<User> byRoleComparator = new UserComparator.ByRole(USER_ROLE);
        assertNotNull(byRoleComparator);
    }

    @Test
    public void compareByRoleTest() {
        final Comparator<User> byRoleComparator = new UserComparator.ByRole(USER_ROLE);
        comparatorTest(byRoleComparator);
    }

    @Test
    public void whenSetNullRoleInByRoleComparatorConstructorThenSetAnotherUserRole() {
        new UserComparator.ByRole(null);
    }

    private void comparatorTest(final Comparator<User> comparator) {
        final UserBuilder builder = User.getBuilder();
        final User first = builder.build();
        final User second = builder.build();
        int compareValue;
        int temp = comparator.compare(first, second);
        for (int i = 0; i < 10; i++) {
            compareValue = comparator.compare(first, second);
            assertTrue(compareValue == temp);
            temp = compareValue;
        }
    }
}
