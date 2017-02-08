package com.salimov.yurii.util.encryption;

import org.junit.Test;

import static com.salimov.yurii.mocks.MockConstants.ANY_STRING;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class EncryptionTest {

    @Test
    public void whenEncryptByNullValueThenReturnNull() {
        assertNull(new Encryption(null).encrypt());
    }

    @Test
    public void whenEncryptThenReturnSomeValue() {
        assertNotNull(new Encryption(ANY_STRING).encrypt());
    }

    @Test
    public void whenDecryptByNullValueThenReturnNull() {
        assertNull(new Encryption(null).decrypt());
    }

    @Test
    public void whenDecryptByUnknownStringThenReturnNull() {
        assertNull(new Encryption(ANY_STRING).decrypt());
    }

    @Test
    public void whenDecryptThenReturnSomeValue() {
        assertNotNull(new Encryption("123").decrypt());
    }

    @Test
    public void whenGetValueThenReturnSomeValue() {
        assertNotNull(new Encryption(ANY_STRING).getValue());
    }
}