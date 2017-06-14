package ua.com.ecoteh.entity.user;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class UserRoleTest {

    @Test
    public void whenGetEnumConstantsThenReturnNotEmptyArray() {
        final UserRole[] roles = UserRole.getEnumConstants();
        assertTrue(roles.length > 0);
    }
}