package com.salimov.yurii.controller.other;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.yurii.mocks.controller.MockController.getSpecialController;

public final class SpecialControllerTest {

    private static SpecialController controller;

    @BeforeClass
    public static void setUp() {
        controller = getSpecialController();
    }

    @Test(expected = IllegalAccessException.class)
    public void whenGetIllegalAccessExceptionThenReturnIt()
            throws IllegalAccessException {
        controller.getIllegalAccessException();
    }
}
