package com.salimov.yurii.util.encryption;

/**
 * The interface describes the methods for data encryption.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 * @see Encryption
 */
public interface IEncryption {

    /**
     * Encrypts a data.
     *
     * @return The encrypted data.
     */
    String encrypt();

    /**
     * Decrypts a date.
     *
     * @return The decrypted data
     */
    String decrypt();
}
