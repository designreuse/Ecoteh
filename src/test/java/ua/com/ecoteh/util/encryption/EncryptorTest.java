package ua.com.ecoteh.util.encryption;

import org.junit.Test;

import javax.crypto.SecretKey;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static ua.com.ecoteh.mocks.MockConstants.ANY_STRING;
import static ua.com.ecoteh.util.validator.ObjectValidator.isEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;

public final class EncryptorTest {

    @Test
    public void callDefaultConstructor() {
        new Encryptor();
    }

    @Test
    public void callConstructorWithOneParameter() {
        new Encryptor(ANY_STRING);
    }

    @Test
    public void callConstructorWithTwoParameter() {
        new Encryptor(ANY_STRING, ANY_STRING);
        new Encryptor(ANY_STRING, ANY_STRING.getBytes());
    }

    @Test
    public void whenEncryptByNullValueThenReturnEmptyString() {
        assertTrue(isEmpty(new Encryptor(null).encrypt()));
    }

    @Test
    public void whenEncryptThenReturnSomeValue() {
        assertTrue(isNotEmpty(new Encryptor(ANY_STRING).encrypt()));
    }

    @Test
    public void whenDecryptByNullValueThenReturnEmptyString() {
        assertTrue(isEmpty(new Encryptor(null).decrypt()));
    }

    @Test
    public void whenDecryptByUnknownStringThenReturnEmptyString() {
        assertTrue(isEmpty(new Encryptor(ANY_STRING).decrypt()));
    }

    @Test
    public void whenDecryptThenReturnSomeValue() {
        assertNotNull(new Encryptor("tw/uslp2VOg").decrypt());
    }

    @Test
    public void whenGetValueThenReturnSomeValue() {
        assertNotNull(new Encryptor(ANY_STRING).getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInitByIllegalParameterThenThrowIllegalArgumentException() {
        new Encryptor(null, null, null);
    }

    @Test
    public void whenSetNullSecretKeyThenSteDefaultCharsetName() {
        Encryptor.setSecretKey((String) null);
        Encryptor.setSecretKey((byte[]) null);
        Encryptor.setSecretKey((SecretKey) null);
    }

    @Test
    public void whenSetBlankSecretKeyThenSteDefaultCharsetName() {
        Encryptor.setSecretKey("");
        Encryptor.setSecretKey(" ");
        Encryptor.setSecretKey("   ");
        Encryptor.setSecretKey(new byte[]{});
    }

    @Test
    public void whenSetNormalSecretKeyThenSetIt() {
        Encryptor.setSecretKey("6Lex4goUACmMLbkjLR2GQvVceS61rF4G");
        Encryptor.setSecretKey(("6Lex4goUACmMLbkjLR2GQvVceS61rF4G").getBytes());
    }

    @Test
    public void whenSetNullCharsetNameThenSteDefaultCharsetName() {
        Encryptor.setCharsetName(null);
    }

    @Test
    public void whenSetBlankCharsetNameThenSteDefaultCharsetName() {
        Encryptor.setCharsetName("");
        Encryptor.setCharsetName(" ");
        Encryptor.setCharsetName("   ");
    }

    @Test
    public void whenSetNormalCharsetNameThenSetIt() {
        Encryptor.setCharsetName("UTF8");
    }
}