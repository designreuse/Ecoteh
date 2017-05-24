package ua.com.ecoteh.util.encryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;

import static ua.com.ecoteh.util.validator.ObjectValidator.isNotEmpty;
import static ua.com.ecoteh.util.validator.ObjectValidator.isNotNull;

/**
 * The class implements methods for data encryption.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public final class Base64Encryptor implements Encryptor {

    /**
     * The object for logging information.
     */
    private final static Logger LOGGER = Logger.getLogger(Base64Encryptor.class);

    /**
     * Default key.
     */
    private final static String KEY = "6Lex4goUAAAAACmMLbkj";

    /**
     * Default secret key.
     */
    private final static SecretKey DEFAULT_KEY = new DESSecretKey(KEY.getBytes());

    /**
     * Name of a default supported Charset.
     */
    private final static String DEFAULT_CHARSET_NAME = "UTF8";

    /**
     * Primary encoding format.
     */
    private static SecretKey staticSecretKey = DEFAULT_KEY;
    ;

    /**
     * Name of a default supported Charset.
     */
    private static String staticCharsetName = DEFAULT_CHARSET_NAME;
    ;

    /**
     * Name of a supported Charset.
     */
    private final String charsetName;

    /**
     * Encrypt cipher.
     */
    private final Cipher encryptCipher;

    /**
     * Decrypt cipher.
     */
    private final Cipher decryptCipher;

    /**
     * The value to encrypt or to decrypt.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param value the value to encrypt or to decrypt.
     */
    public Base64Encryptor(final String value) {
        this(value, Base64Encryptor.staticSecretKey);
    }

    /**
     * Constructor.
     *
     * @param value     the value to encrypt or to decrypt.
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final String value, final String secretKey) {
        this(value, secretKey.getBytes());
    }

    /**
     * Constructor.
     *
     * @param value     the value to encrypt or to decrypt.
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final String value, final byte[] secretKey) {
        this(value, new DESSecretKey(secretKey));
    }

    /**
     * Constructor.
     *
     * @param value     the value to encrypt or to decrypt.
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final String value, final SecretKey secretKey) {
        this(value, secretKey, Base64Encryptor.staticCharsetName);
    }

    /**
     * Constructor.
     *
     * @param value       the value to encrypt or to decrypt.
     * @param secretKey   the primary encoding format.
     * @param charsetName the name of supported Charset.
     * @throws IllegalArgumentException Throw exception when
     *                                  input parameters is illegal.
     */
    public Base64Encryptor(
            final String value,
            final SecretKey secretKey,
            final String charsetName
    ) throws IllegalArgumentException {
        try {
            this.value = value;
            this.encryptCipher = Cipher.getInstance(secretKey.getAlgorithm());
            this.encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            this.decryptCipher = Cipher.getInstance(secretKey.getAlgorithm());
            this.decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
            if (isNotEmpty(charsetName)) {
                this.charsetName = charsetName;
            } else {
                this.charsetName = Base64Encryptor.staticCharsetName;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Encrypts a data.
     *
     * @return The encrypted data or empty string (newer null).
     */
    @Override
    public String encrypt() {
        String result;
        try {
            result = getEncryptedString();
        } catch (Exception ex) {
            logException(ex);
            result = "";
        }
        return result;
    }

    /**
     * Decrypts a date.
     *
     * @return The decrypted data or empty string (newer null).
     */
    @Override
    public String decrypt() {
        String result;
        try {
            result = getDecryptedString();
        } catch (Exception ex) {
            logException(ex);
            result = "";
        }
        return result;
    }

    /**
     * Returns a value to encrypt or to decrypt.
     *
     * @return The value to encrypt or to decrypt (newer null).
     */
    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * Sets a primary encoding format.
     *
     * @param secretKey the primary encoding format.
     */
    public static void setSecretKey(final String secretKey) {
        if (isNotEmpty(secretKey)) {
            setSecretKey(secretKey.getBytes());
        } else {
            setSecretKey(DEFAULT_KEY);
        }
    }

    /**
     * Sets a primary encoding format.
     *
     * @param secretKey the primary encoding format.
     */
    public static void setSecretKey(final byte[] secretKey) {
        if (isNotEmpty(secretKey)) {
            setSecretKey(new DESSecretKey(secretKey));
        } else {
            setSecretKey(DEFAULT_KEY);
        }
    }

    /**
     * Sets a primary encoding format.
     *
     * @param secretKey the primary encoding format.
     */
    public static void setSecretKey(final SecretKey secretKey) {
        if (isNotNull(secretKey)) {
            Base64Encryptor.staticSecretKey = secretKey;
        } else {
            Base64Encryptor.staticSecretKey = DEFAULT_KEY;
        }
    }

    /**
     * Sets name of a default supported Charset.
     *
     * @param charsetName the name of a default supported Charset.
     */
    public static void setCharsetName(final String charsetName) {
        if (isNotEmpty(charsetName)) {
            Base64Encryptor.staticCharsetName = charsetName;
        } else {
            Base64Encryptor.staticCharsetName = DEFAULT_CHARSET_NAME;
        }
    }

    /**
     * Encrypts a string and returns it.
     *
     * @return The encrypted string (newer null).
     * @throws BadPaddingException          if this cipher is in decryption mode,
     *                                      and (un)padding has been requested,
     *                                      but the decrypted data is not bounded
     *                                      by the appropriate padding bytes
     * @throws IllegalBlockSizeException    if this cipher is a block cipher,
     *                                      no padding has been requested (only
     *                                      in encryption mode), and the total
     *                                      input length of the data processed
     *                                      by this cipher is not a multiple of
     *                                      block size; or if this encryption
     *                                      algorithm is unable to process the
     *                                      input data provided.
     * @throws UnsupportedEncodingException If the named charset is not supported.
     */
    private String getEncryptedString() throws BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException {
        return Base64.encodeBase64String(
                this.encryptCipher.doFinal(
                        this.value.getBytes(this.charsetName)
                )
        ).replace("=", "");
    }

    /**
     * Decrypts a string and returns it.
     *
     * @return The decrypted string (newer null).
     * @throws BadPaddingException          if this cipher is in decryption mode,
     *                                      and (un)padding has been requested,
     *                                      but the decrypted data is not bounded
     *                                      by the appropriate padding bytes
     * @throws IllegalBlockSizeException    if this cipher is a block cipher,
     *                                      no padding has been requested (only
     *                                      in encryption mode), and the total
     *                                      input length of the data processed
     *                                      by this cipher is not a multiple of
     *                                      block size; or if this encryption
     *                                      algorithm is unable to process the
     *                                      input data provided.
     * @throws UnsupportedEncodingException If the named charset is not supported.
     */
    private String getDecryptedString() throws BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException {
        return new String(
                this.decryptCipher.doFinal(Base64.decodeBase64(this.value)),
                this.charsetName
        ).replace("=", "");
    }

    /**
     * Error logging.
     *
     * @param ex the intercepted exception.
     */
    private static void logException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }
}
