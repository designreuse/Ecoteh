package ua.com.ecoteh.util.encryption;

import org.junit.Test;

import javax.crypto.SecretKey;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

public final class Base64EncryptorTest {

    @Test
    public void callConstructorWithOneParameter() {
        new Base64Encryptor(ANY_STRING);
    }

    @Test
    public void callConstructorWithTwoParameter() {
        new Base64Encryptor(ANY_STRING, ANY_STRING);
        new Base64Encryptor(ANY_STRING, ANY_STRING.getBytes());
    }

    @Test
    public void whenEncryptByNullValueThenReturnEmptyString() {
        assertTrue(isEmpty(new Base64Encryptor(null).encrypt()));
    }

    @Test
    public void whenEncryptThenReturnSomeValue() {
        assertTrue(isNotEmpty(new Base64Encryptor(ANY_STRING).encrypt()));
    }

    @Test
    public void whenDecryptByNullValueThenReturnEmptyString() {
        assertTrue(isEmpty(new Base64Encryptor(null).decrypt()));
    }

    @Test
    public void whenDecryptByUnknownStringThenReturnEmptyString() {
        assertTrue(isEmpty(new Base64Encryptor(ANY_STRING).decrypt()));
    }

    @Test
    public void whenDecryptThenReturnSomeValue() {
        assertNotNull(new Base64Encryptor("tw/uslp2VOg").decrypt());
    }

    @Test
    public void whenGetValueThenReturnSomeValue() {
        assertNotNull(new Base64Encryptor(ANY_STRING).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitByIllegalParameterThenThrowIllegalArgumentException() {
        new Base64Encryptor(null, null, null);
    }

    @Test
    public void whenSetNullSecretKeyThenSteDefaultCharsetName() {
        Base64Encryptor.setSecretKey((String) null);
        Base64Encryptor.setSecretKey((byte[]) null);
        Base64Encryptor.setSecretKey((SecretKey) null);
    }

    @Test
    public void whenSetBlankSecretKeyThenSteDefaultCharsetName() {
        Base64Encryptor.setSecretKey("");
        Base64Encryptor.setSecretKey(" ");
        Base64Encryptor.setSecretKey("   ");
        Base64Encryptor.setSecretKey(new byte[]{});
    }

    @Test
    public void whenSetNormalSecretKeyThenSetIt() {
        Base64Encryptor.setSecretKey("6Lex4goUACmMLbkjLR2GQvVceS61rF4G");
        Base64Encryptor.setSecretKey(("6Lex4goUACmMLbkjLR2GQvVceS61rF4G").getBytes());
    }

    @Test
    public void whenSetNullCharsetNameThenSteDefaultCharsetName() {
        Base64Encryptor.setCharsetName(null);
    }

    @Test
    public void whenSetBlankCharsetNameThenSteDefaultCharsetName() {
        Base64Encryptor.setCharsetName("");
        Base64Encryptor.setCharsetName(" ");
        Base64Encryptor.setCharsetName("   ");
    }

    @Test
    public void whenSetNormalCharsetNameThenSetIt() {
        Base64Encryptor.setCharsetName("UTF8");
    }
}