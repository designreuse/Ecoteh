package ua.com.ecoteh.util.sender;

/**
 * The interface describes a set of methods for sending message.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface Sender extends Runnable {

    /**
     * Sends a message.
     */
    void send();
}
