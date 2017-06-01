package ua.com.ecoteh.service.sender;

import ua.com.ecoteh.entity.user.UserEntity;

import java.util.Collection;

/**
 * The interface describes a set of methods for working with E-mail.
 *
 * @author Yurii Salimov (yuriy.alex.salimov@gmail.com)
 */
public interface SenderService {

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmail  a recipient e-mail.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    void send(
            String subject,
            String text,
            String recipientEmail,
            String senderEmail,
            String senderEmailPass
    );

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject        a sender subject.
     * @param text           a sender text.
     * @param recipientEmail a recipient e-mail.
     * @param sender         a sender user.
     */
    void send(
            String subject,
            String text,
            String recipientEmail,
            UserEntity sender
    );

    /**
     * Creates and sends sender to recipient.
     *
     * @param subject   a sender subject.
     * @param text      a sender text.
     * @param recipient a recipient user.
     * @param sender    a sender user.
     */
    void send(
            String subject,
            String text,
            UserEntity recipient,
            UserEntity sender
    );

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmails a recipients e-mails.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    void send(
            String subject,
            String text,
            String[] recipientEmails,
            String senderEmail,
            String senderEmailPass
    );

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipientEmails a recipients e-mails.
     * @param sender          a sender user.
     */
    void send(
            String subject,
            String text,
            String[] recipientEmails,
            UserEntity sender
    );

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject    a sender subject.
     * @param text       a sender text.
     * @param recipients a recipients users.
     * @param sender     a sender user.
     */
    void send(
            String subject,
            String text,
            Collection<UserEntity> recipients,
            UserEntity sender
    );

    /**
     * Creates and sends sender to recipients.
     *
     * @param subject         a sender subject.
     * @param text            a sender text.
     * @param recipients      a recipients users.
     * @param senderEmail     a sender e-mail.
     * @param senderEmailPass a sender e-mail password.
     */
    void send(
            String subject,
            String text,
            Collection<UserEntity> recipients,
            String senderEmail,
            String senderEmailPass
    );
}
