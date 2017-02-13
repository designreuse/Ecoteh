package com.salimov.yurii.util.generator;

/**
 * The interface describes a set of methods for generating Object.
 *
 * @param <T> extends Object.
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Generator<T> {

    /**
     * Generates object.
     *
     * @return The generated object.
     */
    T generate();

    /**
     * Returns generated object.
     *
     * @return The generated object.
     */
    T get();
}
