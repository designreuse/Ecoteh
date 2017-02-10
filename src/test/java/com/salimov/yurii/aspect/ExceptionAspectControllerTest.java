package com.salimov.yurii.aspect;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public final class ExceptionAspectControllerTest {

    @Test
    public void afterThrowingAdvice() throws Exception {
        final ExceptionAspectController aspect = new ExceptionAspectController();
        assertNotNull(aspect);
        aspect.afterThrowingAdvice(new Exception());
    }
}
