package com.salimov.yurii.util.encryption;

import com.salimov.yurii.util.translator.AsciiImpl;

/**
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public class Encryption implements IEncryption {

    /**
     *
     */
    private final String value;

    /**
     *
     * @param value
     */
    public Encryption(final String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public String encrypt() {
        return new AsciiImpl(this.value).to();
    }

    /**
     *
     * @return
     */
    @Override
    public String decrypt() {
        return new AsciiImpl(this.value).from();
    }

    public String getValue() {
        return this.value;
    }
}
