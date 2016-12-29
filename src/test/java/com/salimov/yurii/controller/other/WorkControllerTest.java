package com.salimov.yurii.controller.other;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.salimov.yurii.mocks.controller.MockController.getWorkController;

public final class WorkControllerTest {

    private static WorkController controller;

    @BeforeClass
    public static void setUp() {
        controller = getWorkController();
    }

    @Test(expected = IllegalAccessException.class)
    public void whenGetIllegalAccessExceptionThenReturnIt()
            throws IllegalAccessException {
        controller.getIllegalAccessException();
    }
}
