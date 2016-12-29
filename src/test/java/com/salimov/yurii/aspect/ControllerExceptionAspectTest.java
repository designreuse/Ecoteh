package com.salimov.yurii.aspect;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public final class ControllerExceptionAspectTest {

    @Test
    public void afterThrowingAdvice() throws Exception {
        final ControllerExceptionAspect aspect = new ControllerExceptionAspect();
        assertNotNull(aspect);
        aspect.afterThrowingAdvice(new Exception());
    }
}
