package com.salimov.yurii.util.encryption;

import com.salimov.yurii.util.translator.AsciiImpl;

/**
 * The class implements methods for data encryption.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see IEncryption
 */
public class Encryption implements IEncryption {

    /**
     * The value to encrypt or to decrypt.
     */
    private final String value;

    /**
     * Constructor.
     *
     * @param value a value to encrypt or to decrypt.
     */
    public Encryption(final String value) {
        this.value = value;
    }

    /**
     * Encrypts a data.
     *
     * @return The encrypted data.
     */
    @Override
    public String encrypt() {
        return new AsciiImpl(this.value).to();
    }

    /**
     * Decrypts a date.
     *
     * @return The decrypted data
     */
    @Override
    public String decrypt() {
        return new AsciiImpl(this.value).from();
    }

    /**
     * Returns a value to encrypt or to decrypt.
     *
     * @return The value to encrypt or to decrypt.
     */
    public String getValue() {
        return this.value;
    }
}
