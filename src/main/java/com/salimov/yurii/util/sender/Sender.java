package com.salimov.yurii.util.sender;

/**
 * The interface describes a set of methods for sending letters by e-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 * @version 1.0
 */
public interface Sender extends Runnable {

    /**
     * Sends a message to an E-mail in new thread.
     */
    void send();
}
