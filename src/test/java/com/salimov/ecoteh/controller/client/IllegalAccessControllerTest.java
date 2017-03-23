package com.salimov.ecoteh.controller.client;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
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
