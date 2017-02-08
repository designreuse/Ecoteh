package com.salimov.yurii.config;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public final class SecurityInitializerTest {

    @Test
    public void ConstructorTest() throws Exception {
        assertNotNull(new SecurityInitializer());
    }
}
