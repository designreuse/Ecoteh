package ua.com.ecoteh.util.sender;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * {@inheritDoc}
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
final class SenderAuthenticator extends Authenticator {

    /**
     * The sender E-mail.
     */
    private final String email;

    /**
     * The sender E-mail password.
     */
    private final String password;

    /**
     * Constructor.
     *
     * @param email    the sender E-mail (newer null).
     * @param password the sender E-mail password (newer null).
     */
    SenderAuthenticator(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.email, this.password);
    }
}
