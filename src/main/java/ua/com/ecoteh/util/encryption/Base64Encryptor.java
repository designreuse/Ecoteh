package ua.com.ecoteh.util.encryption;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

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
     * Name of a supported Charset.
     */
    private final String charsetName;

    /**
     * Secret key.
     */
    private final SecretKey secretKey;

    /**
     * Encrypt cipher.
     */
    private final Cipher cipher;

    /**
     * Constructor.
     */
    public Base64Encryptor() {
        this(DEFAULT_KEY);
    }

    /**
     * Constructor.
     *
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final String secretKey) {
        this(isNotEmpty(secretKey) ? secretKey.getBytes() : KEY.getBytes());
    }

    /**
     * Constructor.
     *
     * @param secretKey   the primary encoding format.
     * @param charsetName the name of supported Charset.
     */
    public Base64Encryptor(final String secretKey, String charsetName) {
        this(isNotEmpty(secretKey) ? secretKey.getBytes() : KEY.getBytes(), charsetName);
    }

    /**
     * Constructor.
     *
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final byte[] secretKey) {
        this(isNotEmpty(secretKey) ? new DESSecretKey(secretKey) : DEFAULT_KEY);
    }

    /**
     * Constructor.
     *
     * @param secretKey   the primary encoding format.
     * @param charsetName the name of supported Charset.
     */
    public Base64Encryptor(final byte[] secretKey, String charsetName) {
        this(
                isNotEmpty(secretKey) ? new DESSecretKey(secretKey) : DEFAULT_KEY,
                charsetName
        );
    }

    /**
     * Constructor.
     *
     * @param secretKey the primary encoding format.
     */
    public Base64Encryptor(final SecretKey secretKey) {
        this(secretKey, DEFAULT_CHARSET_NAME);
    }

    /**
     * Constructor.
     *
     * @param secretKey   the primary encoding format.
     * @param charsetName the name of supported Charset.
     * @throws IllegalArgumentException Throw exception when
     *                                  input parameters is illegal.
     */
    public Base64Encryptor(final SecretKey secretKey, final String charsetName) throws IllegalArgumentException {
        try {
            this.secretKey = isNotNull(secretKey) ? secretKey : DEFAULT_KEY;
            this.charsetName = isNotEmpty(charsetName) ? charsetName : DEFAULT_CHARSET_NAME;
            this.cipher = Cipher.getInstance(this.secretKey.getAlgorithm());
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Encrypts a data.
     *
     * @param value the value to encrypt.
     * @return The encrypted data or empty string (newer null).
     */
    @Override
    public String encrypt(final String value) {
        String result;
        if (isNotEmpty(value)) {
            try {
                result = getEncryptedString(value);
            } catch (Exception ex) {
                logException(ex);
                result = "";
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Decrypts a date.
     *
     * @param value the value to decrypt.
     * @return The decrypted data or empty string (newer null).
     */
    @Override
    public String decrypt(final String value) {
        String result;
        if (isNotEmpty(value)) {
            try {
                result = getDecryptedString(value);
            } catch (Exception ex) {
                logException(ex);
                result = "";
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * Encrypts a string and returns it.
     *
     * @param value the value to encrypt.
     * @return The encrypted string (newer null).
     * @throws InvalidKeyException          if the secret key is invalid.
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
    private String getEncryptedString(final String value)
            throws InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        final byte[] valueBytes = value.getBytes(this.charsetName);
        final byte[] encryptBytes = this.cipher.doFinal(valueBytes);
        return Base64.encodeBase64String(encryptBytes).replace("=", "");
    }

    /**
     * Decrypts a string and returns it.
     *
     * @param value the value to decrypt.
     * @return The decrypted string (newer null).
     * @throws InvalidKeyException          if the secret key is invalid.
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
    private String getDecryptedString(final String value)
            throws InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, UnsupportedEncodingException {
        this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        final byte[] decodeBytes = Base64.decodeBase64(value);
        final byte[] decryptBytes = this.cipher.doFinal(decodeBytes);
        return new String(decryptBytes, this.charsetName).replace("=", "");
    }

    /**
     * Error logging.
     *
     * @param ex the intercepted exception.
     */
    private void logException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }
}
