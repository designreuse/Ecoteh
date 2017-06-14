package ua.com.ecoteh.controller.authorization;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public class IllegalAccessControllerTest {

    private static IllegalAccessController controller;

    @BeforeClass
    public static void setUp() {
        controller = new IllegalAccessController();
    }

    @Test(expected = IllegalAccessException.class)
    public void whenGetIllegalAccessExceptionThenThrowsIllegalAccessException() throws IllegalAccessException {
        controller.getIllegalAccessException();
    }
}